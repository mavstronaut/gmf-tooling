/*
 * Copyright (c) 2005,2006 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Artem Tikhomirov (Borland) - initial API and implementation
 */
package org.eclipse.gmf.internal.codegen.lite;

import java.util.Iterator;

import org.eclipse.core.runtime.Path;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.gmf.codegen.gmfgen.GMFGenPackage;
import org.eclipse.gmf.codegen.gmfgen.GenChildLabelNode;
import org.eclipse.gmf.codegen.gmfgen.GenCompartment;
import org.eclipse.gmf.codegen.gmfgen.GenDiagram;
import org.eclipse.gmf.codegen.gmfgen.GenEditorGenerator;
import org.eclipse.gmf.codegen.gmfgen.GenExpressionInterpreter;
import org.eclipse.gmf.codegen.gmfgen.GenExpressionProviderBase;
import org.eclipse.gmf.codegen.gmfgen.GenExpressionProviderContainer;
import org.eclipse.gmf.codegen.gmfgen.GenLanguage;
import org.eclipse.gmf.codegen.gmfgen.GenLink;
import org.eclipse.gmf.codegen.gmfgen.GenLinkLabel;
import org.eclipse.gmf.codegen.gmfgen.GenNode;
import org.eclipse.gmf.codegen.gmfgen.GenNodeLabel;
import org.eclipse.gmf.common.UnexpectedBehaviourException;
import org.eclipse.gmf.internal.common.codegen.GeneratorBase;
import org.eclipse.gmf.internal.common.codegen.ImportUtil;
import org.eclipse.gmf.internal.common.codegen.TextEmitter;
import org.eclipse.gmf.internal.common.codegen.TextMerger;

/**
 * Invokes templates to populate diagram editor project.
 * TODO reuse fullRT.Generator as much as possible
 * @author artem
 */
public class Generator extends GeneratorBase implements Runnable {

	private final GenEditorGenerator myEditorGen; 

	private final GenDiagram myDiagram;

	private final CodegenEmitters myEmitters;

	public Generator(GenEditorGenerator genModel) {
		this(genModel, Activator.getInstance().getEmitters(genModel));
	}

	public Generator(GenEditorGenerator genModel, CodegenEmitters emitters) {
		assert genModel != null && emitters != null;
		myDiagram = genModel.getDiagram();
		myEditorGen = genModel;
		myEmitters = emitters;
	}

	@Override
	protected TextMerger createMergeService() {
		return myEmitters.createMergeService();
	}
	
	protected void customRun() throws InterruptedException, UnexpectedBehaviourException {
		final String pluginID = myEditorGen.getPlugin().getID();
		final Path examplaryLocation = new Path(myEditorGen.getDomainGenModel().getModelDirectory());
		initializeEditorProject(pluginID, guessNewProjectLocation(examplaryLocation, pluginID));

		doGenerateFile(myEmitters.getManifestGenerator(), new Path("META-INF/MANIFEST.MF"), new Object[] { myEditorGen.getPlugin() });
		doGenerateFile(myEmitters.getBuildPropertiesGenerator(), new Path("build.properties"), new Object[] { myEditorGen.getPlugin() });
		doGenerateFile(myEmitters.getPluginXML(), new Path("plugin.xml"), new Object[] { myEditorGen.getPlugin() });
		doGenerateFile(myEmitters.getPluginPropertiesGenerator(), new Path("plugin.properties"), new Object[] { myEditorGen.getPlugin() });

		internalGenerateJavaClass(myEmitters.getCreationWizardGenerator(), myDiagram.getCreationWizardQualifiedClassName(), myDiagram);
		internalGenerateJavaClass(myEmitters.getCreationWizardPageGenerator(), myDiagram.getCreationWizardPageQualifiedClassName(), myDiagram);
		internalGenerateJavaClass(myEmitters.getPluginGenerator(), myEditorGen.getPlugin().getActivatorQualifiedClassName(), myEditorGen.getPlugin());
		internalGenerateJavaClass(myEmitters.getInitDiagramFileActionGenerator(), myDiagram.getInitDiagramFileActionQualifiedClassName(), myDiagram);
		internalGenerateJavaClass(myEmitters.getNewDiagramFileWizardGenerator(), myDiagram.getNewDiagramFileWizardQualifiedClassName(), myDiagram);

		internalGenerateJavaClass(myEmitters.getActionBarContributorGenerator(), myEditorGen.getEditor().getActionBarContributorQualifiedClassName(), myEditorGen.getEditor());
		internalGenerateJavaClass(myEmitters.getDiagramEditorUtilGenerator(), myDiagram.getDiagramEditorUtilQualifiedClassName(), myDiagram);
		internalGenerateJavaClass(myEmitters.getEditorGenerator(), myEditorGen.getEditor().getQualifiedClassName(), myEditorGen.getEditor());
		if (myDiagram.getPalette() != null) {
			internalGenerateJavaClass(myEmitters.getPaletteFactoryGenerator(), myDiagram.getPalette().getFactoryQualifiedClassName(), myDiagram);
		}
		internalGenerateJavaClass(myEmitters.getEditPartFactoryGenerator(), myDiagram.getEditPartFactoryQualifiedClassName(), myDiagram);
		internalGenerateJavaClass(myEmitters.getDiagramEditPartGenerator(), myDiagram.getEditPartQualifiedClassName(), myDiagram);

		for (Iterator it = myDiagram.getAllNodes().iterator(); it.hasNext(); ) {
			final GenNode next = (GenNode) it.next();
			if (!(next instanceof GenChildLabelNode)) {
				internalGenerateJavaClass(myEmitters.getNodeEditPartGenerator(), next.getEditPartQualifiedClassName(), next);
				for (Iterator it2 = next.getLabels().iterator(); it2.hasNext();) {
					final GenNodeLabel label = (GenNodeLabel) it2.next();
					internalGenerateJavaClass(myEmitters.getNodeLabelEditPartGenerator(), label.getEditPartQualifiedClassName(), label);
					internalGenerateJavaClass(myEmitters.getLabelViewFactoryGenerator(), label.getNotationViewFactoryQualifiedClassName(), label);
				}
				internalGenerateJavaClass(myEmitters.getNodeViewFactoryGenerator(), next.getNotationViewFactoryQualifiedClassName(), next);
			} else {
				internalGenerateJavaClass(myEmitters.getChildNodeEditPartGenerator(), next.getEditPartQualifiedClassName(), next);
				internalGenerateJavaClass(myEmitters.getLabelViewFactoryGenerator(), next.getNotationViewFactoryQualifiedClassName(), next);
			}
		}
		for (Iterator it = myDiagram.getLinks().iterator(); it.hasNext();) {
			final GenLink next = (GenLink) it.next();
			internalGenerateJavaClass(myEmitters.getLinkEditPartGenerator(), next.getEditPartQualifiedClassName(), next);
			for (Iterator it2 = next.getLabels().iterator(); it2.hasNext();) {
				final GenLinkLabel label = (GenLinkLabel) it2.next();
				internalGenerateJavaClass(myEmitters.getLinkLabelEditPartGenerator(), label.getEditPartQualifiedClassName(), label);
				internalGenerateJavaClass(myEmitters.getLabelViewFactoryGenerator(), label.getNotationViewFactoryQualifiedClassName(), label);
			}
			internalGenerateJavaClass(myEmitters.getLinkViewFactoryGenerator(), next.getNotationViewFactoryQualifiedClassName(), next);
		}
		for (Iterator it = myDiagram.getCompartments().iterator(); it.hasNext(); ) {
			final GenCompartment next = (GenCompartment) it.next();
			internalGenerateJavaClass(myEmitters.getCompartmentEditPartGenerator(), next.getEditPartQualifiedClassName(), next);
			internalGenerateJavaClass(myEmitters.getCompartmentViewFactoryGenerator(), next.getNotationViewFactoryQualifiedClassName(), next);
		}
		internalGenerateJavaClass(myEmitters.getDiagramViewFactoryGenerator(), myDiagram.getNotationViewFactoryQualifiedClassName(), myDiagram);
		internalGenerateJavaClass(myEmitters.getDomainElementInitializerGenerator(), myDiagram.getNotationViewFactoriesPackageName(), "DomainElementInitializer",myDiagram); // XXX: allow customization!
		internalGenerateJavaClass(myEmitters.getVisualIDRegistryGenerator(), myDiagram.getVisualIDRegistryQualifiedClassName(), myDiagram);
		if(myDiagram.getEditorGen().getExpressionProviders() != null) {
			generateExpressionProviders();
		}
	}

	private void generateExpressionProviders() throws UnexpectedBehaviourException, InterruptedException {
		GenExpressionProviderContainer providerContainer = myEditorGen.getExpressionProviders();
		internalGenerateJavaClass(
			myEmitters.getAbstractExpressionEmitter(),
			providerContainer.getExpressionsPackageName(), 
			providerContainer.getAbstractExpressionClassName(),
			myDiagram
		);

		for (Iterator it = providerContainer.getProviders().iterator(); it.hasNext();) {
			GenExpressionProviderBase nextProvider = (GenExpressionProviderBase) it.next();
			if(nextProvider instanceof GenExpressionInterpreter) {
				TextEmitter providerEmitter = null;
				if(GenLanguage.OCL_LITERAL.equals(nextProvider.getLanguage())) {
					providerEmitter = myEmitters.getOCLExpressionFactoryEmitter();
				} else if(GenLanguage.REGEXP_LITERAL.equals(nextProvider.getLanguage()) || GenLanguage.NREGEXP_LITERAL.equals(nextProvider.getLanguage())) {
					providerEmitter = myEmitters.getRegexpExpressionFactoryEmitter();
				}
				GenExpressionInterpreter interpreter = (GenExpressionInterpreter)nextProvider;
				if(providerEmitter != null) {
					internalGenerateJavaClass(
							providerEmitter,
							providerContainer.getExpressionsPackageName(),
							interpreter.getClassName(),
							interpreter);
				}
			}
		}
	}
	
	private void internalGenerateJavaClass(TextEmitter emitter, String qualifiedClassName, Object argument) throws InterruptedException {
		internalGenerateJavaClass(emitter, CodeGenUtil.getPackageName(qualifiedClassName), CodeGenUtil.getSimpleClassName(qualifiedClassName), argument);
	}

	/**
	 * Passes initialized ImportManager as second template argument
	 */
	private void internalGenerateJavaClass(TextEmitter emitter, String packageName, String className, Object argument) throws InterruptedException {
		ImportUtil importUtil = new ImportUtil(packageName, className);
		doGenerateJavaClass(emitter, packageName, className, new Object[] { new Object[] {argument, importUtil } });
	}

	protected void setupProgressMonitor() {
		Counter c = new Counter();
		c.registerFactor(GMFGenPackage.eINSTANCE.getGenNode(), 2);
		c.registerFactor(GMFGenPackage.eINSTANCE.getGenCompartment(), 2);
		c.registerFactor(GMFGenPackage.eINSTANCE.getGenLink(), 2);
		c.registerFactor(GMFGenPackage.eINSTANCE.getGenNodeLabel(), 2);
		c.registerFactor(GMFGenPackage.eINSTANCE.getGenLinkLabel(), 2);
		int total = c.getTotal(myDiagram);
		total++; // init
		total += 4; // text files
		total += 15; // out-of-cycle doGenerateJava... <- genDiagram + genEditor
		setupProgressMonitor(null, total);
	}
}
