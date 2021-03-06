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
package org.eclipse.gmf.tooling.simplemap.model.setting;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.BasicSettingDelegate.Stateless;
import org.eclipse.gmf.gmfgraph.DiagramLabel;
import org.eclipse.gmf.gmfgraph.Node;
import org.eclipse.gmf.mappings.FeatureLabelMapping;
import org.eclipse.gmf.mappings.NodeMapping;
import org.eclipse.gmf.mappings.NodeReference;
import org.eclipse.gmf.tooldef.AbstractTool;
import org.eclipse.gmf.tooling.simplemap.simplemappings.SimpleNode;
import org.eclipse.gmf.tooling.simplemap.simplemappings.SimplemappingsPackage;

public class SimpleNodeSettingDelegate extends Stateless {

	public SimpleNodeSettingDelegate(EStructuralFeature arg0) {
		super(arg0);
	}

	@Override
	protected Object get(InternalEObject owner, boolean resolve, boolean coreType) {

		if (eStructuralFeature.getEContainingClass() == SimplemappingsPackage.Literals.SIMPLE_NODE) {
			switch (eStructuralFeature.getEContainingClass().getEAllStructuralFeatures().indexOf(eStructuralFeature)) {
			case SimplemappingsPackage.SIMPLE_NODE__DOMAIN_META_ELEMENT:
				return getDomainMetaElement((SimpleNode) owner);
			case SimplemappingsPackage.SIMPLE_NODE__TOOL:
				return getTool((SimpleNode) owner);
			case SimplemappingsPackage.SIMPLE_NODE__LABEL_ATTRIBUTES:
				return getLabelAttributes((SimpleNode) owner);
			case SimplemappingsPackage.SIMPLE_NODE__DIAGRAM_NODE:
				return getDiagramNode((SimpleNode) owner);
			case SimplemappingsPackage.SIMPLE_NODE__DIAGRAM_LABEL:
				return getDiagramLabel((SimpleNode) owner);
			}

		}

		return null;
	}

	/**
	 * Devuelve el label por defecto (El primero definido)
	 * @param owner
	 * @return
	 */
	private DiagramLabel getDiagramLabel(SimpleNode owner) {

		NodeReference nodeRef = owner.getNodeReference();

		if (nodeRef != null && nodeRef.getChild() != null) {
			NodeMapping nodeMapping = nodeRef.getChild();

			//Devolvemos el primer label definido
			if (!nodeMapping.getLabelMappings().isEmpty())
				return nodeMapping.getLabelMappings().get(0).getDiagramLabel();
		}

		return null;
	}

	private Node getDiagramNode(SimpleNode owner) {

		NodeReference nodeRef = owner.getNodeReference();

		if (nodeRef != null && nodeRef.getChild() != null) {
			NodeMapping nodeMapping = nodeRef.getChild();

			return nodeMapping.getDiagramNode();
		}

		return null;
	}

	private Object getLabelAttributes(SimpleNode owner) {

		FeatureLabelMapping featureLabelMapping = !owner.getNodeReference().getChild().getLabelMappings().isEmpty() ? (FeatureLabelMapping) owner.getNodeReference().getChild().getLabelMappings()
				.get(0) : null;
		return featureLabelMapping != null ? featureLabelMapping.getFeatures() : new BasicEList<EAttribute>();
	}

	private AbstractTool getTool(SimpleNode owner) {

		NodeReference nodeRef = owner.getNodeReference();

		if (nodeRef != null && nodeRef.getChild() != null) {
			NodeMapping nodeMapping = nodeRef.getChild();

			return nodeMapping.getTool();
		}

		return null;
	}

	private Object getDomainMetaElement(SimpleNode owner) {

		NodeReference nodeReference = owner.getNodeReference();

		if (nodeReference != null && nodeReference.getChild() != null)
			return nodeReference.getChild().getDomainMetaElement();

		return null;
	}

	private Object getContainmentFeature(SimpleNode owner) {
		return owner.getNodeReference() != null ? owner.getNodeReference().getContainmentFeature() : null;
	}

	@Override
	protected void set(InternalEObject owner, Object newValue) {

		if (eStructuralFeature.getEContainingClass() == SimplemappingsPackage.Literals.SIMPLE_NODE) {
			switch (eStructuralFeature.getEContainingClass().getEAllStructuralFeatures().indexOf(eStructuralFeature)) {
			case SimplemappingsPackage.SIMPLE_NODE__CONTAINMENT_FEATURE:
				setContainmentFeature((SimpleNode) owner, (EReference) newValue);
				break;
			case SimplemappingsPackage.SIMPLE_NODE__DOMAIN_META_ELEMENT:
				setDomainMetaElement((SimpleNode) owner, (EClass) newValue);
				break;
			}

		}

	}

	private void setDomainMetaElement(SimpleNode owner, EClass newValue) {
		if (owner.getNodeReference() != null && owner.getNodeReference().getChild() != null)
			owner.getNodeReference().getChild().setDomainMetaElement(newValue);
	}

	private void setContainmentFeature(SimpleNode owner, EReference newValue) {
		if (owner.getNodeReference() != null)
			owner.getNodeReference().setContainmentFeature(newValue);
	}

	@Override
	protected boolean isSet(InternalEObject owner) {

		if (eStructuralFeature.getEContainingClass() == SimplemappingsPackage.Literals.SIMPLE_NODE) {
			switch (eStructuralFeature.getEContainingClass().getEAllStructuralFeatures().indexOf(eStructuralFeature)) {
			case SimplemappingsPackage.SIMPLE_NODE__CONTAINMENT_FEATURE:
				return getContainmentFeature((SimpleNode) owner) != null;
			}

		}

		return false;
	}

}
