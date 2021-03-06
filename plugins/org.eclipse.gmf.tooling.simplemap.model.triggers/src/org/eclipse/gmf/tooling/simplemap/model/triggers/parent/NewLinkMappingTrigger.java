/**
 * Copyright (c) 2010-2012 ISBAN S.L
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 * 		Ruben De Dios (ISBAN S.L)
 * 		Andrez Alvarez Mattos (ISBAN S.L)
 */
package org.eclipse.gmf.tooling.simplemap.model.triggers.parent;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.gmfgraph.Connection;
import org.eclipse.gmf.gmfgraph.DiagramLabel;
import org.eclipse.gmf.gmfgraph.GMFGraphFactory;
import org.eclipse.gmf.mappings.GMFMapFactory;
import org.eclipse.gmf.mappings.LabelMapping;
import org.eclipse.gmf.mappings.LinkMapping;
import org.eclipse.gmf.tooldef.CreationTool;
import org.eclipse.gmf.tooling.simplemap.simplemappings.SimpleLinkMapping;
import org.eclipse.gmf.tooling.simplemap.simplemappings.SimpleMapping;
import org.eclipse.gmf.tooling.simplemap.simplemappings.SimpleParentNode;

class NewLinkMappingTrigger extends NewElementTrigger {

	protected SimpleLinkMapping newSimpleLinkMapping;

	protected SimpleParentNode parent;

	public NewLinkMappingTrigger(TransactionalEditingDomain domain, SimpleParentNode parent, SimpleLinkMapping newSimpleLinkMapping) {
		super(domain, newSimpleLinkMapping);

		this.newSimpleLinkMapping = newSimpleLinkMapping;
		this.parent = parent;

	}

	@Override
	public void executeTrigger() {

		Connection newConnection = GMFGraphFactory.eINSTANCE.createConnection();
		DiagramLabel newLabel = GMFGraphFactory.eINSTANCE.createDiagramLabel();

		updateCanvas(newConnection, newLabel);

		CreationTool newCreationTool = createNewTool();

		updateMapping((SimpleMapping) parent, newConnection, newLabel, newCreationTool);

	}

	protected void updateCanvas(Connection newConnection, DiagramLabel newLabel) {
		canvasFactory.createNewDefaultPolygon(newConnection, newLabel);
	}

	/**
	 * El padre es el diagrama:
	 * @param mapping
	 * @param newNode
	 * @param newLabel
	 * @param newCreationTool
	 */
	protected void updateMapping(SimpleMapping mapping, Connection newConnection, DiagramLabel newLabel, CreationTool newCreationTool) {
		LinkMapping newLinkMapping = createNewLinkMapping(newConnection, newLabel, newCreationTool);

		mapping.getMapping().getLinks().add(newLinkMapping);

		newSimpleLinkMapping.setLinkMapping(newLinkMapping);

	}

	protected LinkMapping createNewLinkMapping(Connection newConnection, DiagramLabel newLabel, CreationTool newCreationTool) {
		LinkMapping newLinkMapping = GMFMapFactory.eINSTANCE.createLinkMapping();

		//Diagram Node
		newLinkMapping.setDiagramLink(newConnection);

		//Tool
		newLinkMapping.setTool(newCreationTool);

		//Feature Label Mapping
		LabelMapping labelMapping = GMFMapFactory.eINSTANCE.createLabelMapping();
		labelMapping.setDiagramLabel(newLabel);

		newLinkMapping.getLabelMappings().add(labelMapping);

		return newLinkMapping;

	}

}
