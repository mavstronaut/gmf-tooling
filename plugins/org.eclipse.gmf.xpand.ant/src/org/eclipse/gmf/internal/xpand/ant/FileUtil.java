/*
 * Copyright (c) 2008 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.gmf.internal.xpand.ant;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;

public class FileUtil {

	public static IFile getFile(URI uri, URIConverter converter) {
		IFile result = null;

		if ("platform".equals(uri.scheme()) && (uri.segmentCount() > 2)) { //$NON-NLS-1$
			if ("resource".equals(uri.segment(0))) { //$NON-NLS-1$
				IPath path = new Path(URI.decode(uri.path())).removeFirstSegments(1);

				result = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
			}
		} else if (uri.isFile() && !uri.isRelative()) {
			result = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(new Path(uri.toFileString()));
		} else {
			// normalize, to see whether may we can resolve it this time
			if (converter != null) {
				URI normalized = converter.normalize(uri);

				if (!uri.equals(normalized)) {
					// recurse on the new URI
					result = getFile(normalized, converter);
				}
			}
		}

		return result;
	}
}
