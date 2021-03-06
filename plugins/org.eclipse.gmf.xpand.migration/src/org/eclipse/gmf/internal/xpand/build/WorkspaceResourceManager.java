/*
 * Copyright (c) 2006, 2008 Borland Software Corporation
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Artem Tikhomirov (Borland)
 *     Boris Blajer (Borland) - support for composite resources
 */
package org.eclipse.gmf.internal.xpand.build;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gmf.internal.xpand.expression.SyntaxConstants;
import org.eclipse.gmf.internal.xpand.migration.Activator;
import org.eclipse.gmf.internal.xpand.model.XpandResource;
import org.eclipse.gmf.internal.xpand.util.ParserException;
import org.eclipse.gmf.internal.xpand.util.ResourceManagerImpl;
import org.eclipse.gmf.internal.xpand.util.StreamConverter;
import org.eclipse.gmf.internal.xpand.xtend.ast.XtendResource;
import org.osgi.framework.Bundle;

// FIXME package-local?, refactor Activator.getResourceManager uses
public class WorkspaceResourceManager extends ResourceManagerImpl {
	private final IProject contextProject;
	private final IPath[] myConfiguredRoots;

	public WorkspaceResourceManager(IProject context, IPath[] configuredRoots) {
		this.contextProject = context;
		myConfiguredRoots = configuredRoots;
	}

	public XtendResource loadXtendResource(IFile file) throws CoreException, IOException, ParserException {
		String fullyQualifiedName;
		if (file == null || (fullyQualifiedName = toFullyQualifiedName(file)) == null) {
			return null;
		}
		// try file directly, to get IO/Parse exceptions, if any.
		Reader r = new StreamConverter().toContentsReader(file);
		loadXtendResources(new Reader[] { r }, fullyQualifiedName);
		//
		try {
			return loadXtendThroughCache(fullyQualifiedName);
		} catch (FileNotFoundException ex) {
			return null;	//Missing resource is an anticipated situation, not a error that should be handled
		}
	}

	public XpandResource loadXpandResource(IFile file) throws CoreException, IOException, ParserException {
		String fullyQualifiedName;
		if (file == null || (fullyQualifiedName = toFullyQualifiedName(file)) == null) {
			return null;
		}
		// try file directly, to get IO/Parse exceptions, if any.
		Reader r = new StreamConverter().toContentsReader(file);
		loadXpandResources(new Reader[] { r }, fullyQualifiedName);
		//
		fullyQualifiedName = getNonAspectsTemplateName(fullyQualifiedName);
		try {
			return loadXpandThroughCache(fullyQualifiedName);
		} catch (FileNotFoundException ex) {
			return null;	//Missing resource is an anticipated situation, not a error that should be handled
		}
	}

	@Override
	protected void handleParserException(ParserException ex) {
		// may get here only when some referenced template/xtend file is
		// broken. Since it's expected to get compiled anyway (either prior
		// to compilation of its use or afterwards), error messages should get
		// into problems view sooner or later.
		Activator.logWarn(ex.getClass().getSimpleName() + ":" + ex.getResourceName());
	}

	@Override
	protected boolean shouldCache() {
		// we don't cache workspace resources for now (for the sake of reducing 
		// underemined problems that may arise), although may do this later
		return false;
	}

	public void forget(IFile resource) {
		// implement when caching
	}

	public Reader[] resolveMultiple(String fqn, String ext) throws IOException {
		IPath fp = new Path(fqn.replaceAll(SyntaxConstants.NS_DELIM, "/")).addFileExtension(ext);
		IPath[] resolutions = getResolutions(fp);
		ArrayList<Reader> result = new ArrayList<Reader>(resolutions.length);
		for (IPath p : getResolutions(fp)) {
			Reader nextReader = getReader(p);
			if (nextReader != null) {
				result.add(nextReader);
			}
		}
		if (result.isEmpty()) {
			throw new FileNotFoundException(fp.toString());
		}
		return result.toArray(new Reader[result.size()]);
	}

	private Reader getReader(IPath p) throws IOException {
		if (p.isAbsolute()) {
			assert p.segmentCount() > 1;
			//Try workspace-relative first.
			IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(p.segment(0));
			if (project.isAccessible()) {
				return getWorkspaceFileReader(project, p.removeFirstSegments(1));
			}
			//Fallback to platform location
			Bundle platformBundle = Platform.getBundle(p.segment(0));
			if (platformBundle != null) {
				URL url = platformBundle.getEntry(p.removeFirstSegments(1).toString());
				if (url != null) {
					InputStream is = url.openStream();
					return new InputStreamReader(is, Charset.forName("ISO-8859-1"));	//$NON-NLS-1$
				}
			}
		} else {
			return getWorkspaceFileReader(contextProject, p);
		}
		return null;
	}

	private Reader getWorkspaceFileReader(IProject project, IPath path) throws IOException {
		IResource r = project.findMember(path);
		if (r instanceof IFile) {
			try {
				return new StreamConverter().toContentsReader((IFile) r);
			} catch (CoreException ex) {
				IOException wrap = new IOException(ex.getStatus().getMessage());
				wrap.initCause(ex);
				throw wrap;
			}
		}
		return null;
	}

	private IPath[] getResolutions(IPath p) {
		IPath[] rv = new IPath[myConfiguredRoots.length];
		for (int i = 0; i < myConfiguredRoots.length; i++) {
			rv[i] = myConfiguredRoots[i].append(p);
		}
		return rv;
	}
	private String toFullyQualifiedName(IFile file) {
		for (IPath nextRoot : myConfiguredRoots) {
			if (!nextRoot.isAbsolute()) {
				if (file.getProject().equals(contextProject) && nextRoot.isPrefixOf(file.getProjectRelativePath())) {
					return toFullyQualifiedName(file.getProjectRelativePath().removeFirstSegments(nextRoot.segmentCount()));
				}
			} else {
				if (nextRoot.isPrefixOf(file.getFullPath())) {
					return toFullyQualifiedName(file.getFullPath().removeFirstSegments(nextRoot.segmentCount()));
				}
			}
		}
		return null;
	}

	private static String toFullyQualifiedName(IPath filePath) {
		return filePath.removeFileExtension().toString().replace("/", SyntaxConstants.NS_DELIM);
	}
}