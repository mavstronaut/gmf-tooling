/*
 * Copyright (c) 2005 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Dmitri Stadnik (Borland) - initial API and implementation
 */
package org.eclipse.gmf.examples.taipan.gmf.editor.edit.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.emf.ecore.EAnnotation;

import org.eclipse.gmf.examples.taipan.gmf.editor.part.TaiPanDiagramEditorPlugin;

import org.eclipse.gmf.examples.taipan.gmf.editor.providers.TaiPanSemanticHints;

/**
 * @generated
 */
public class TaiPanEditPartFactory implements EditPartFactory {

	/**
	 * @generated
	 */
	public static final String EXTERNAL_NODE_LABELS_LAYER = "External Node Labels";

	/**
	 * @generated
	 */
	private String getModelID(View containerView) {
		EAnnotation annotation = containerView.getEAnnotation("ViewIdentifier"); //$NON-NLS-1$
		if (annotation == null) {
			return null;
		}
		return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private static int getVisualID(View containerView) {
		EAnnotation annotation = containerView.getEAnnotation("ViewIdentifier"); //$NON-NLS-1$
		if (annotation == null) {
			return -1;
		}
		String visualID = (String) annotation.getDetails().get("visualID"); //$NON-NLS-1$
		if (visualID == null) {
			return -1;
		}
		try {
			return Integer.parseInt(visualID);
		} catch (NumberFormatException e) {
			TaiPanDiagramEditorPlugin.getInstance().logError("Unable to parse \"visualID\" annotation: " + visualID, e);
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			if (!"TaiPan".equals(getModelID(view))) {
				return null;
			}

			int viewVisualID = getVisualID(view);
			switch (viewVisualID) {
			case 1001:
				return new PortEditPart(view);
			case 4001:
				return new Port_locationEditPart(view);
			case 1002:
				return new ShipEditPart(view);
			case 4003:
				return new Ship_nameEditPart(view);
			case 2001:
				return new ItemEditPart(view);
			case 5001:
				return new Ship_CargoCompartmentEditPart(view);
			case 79:
				return new AquatoryEditPart(view);
			case 3001:
				return new RouteEditPart(view);
			case 4004:
				if (TaiPanSemanticHints.Route_3001Labels.ROUTEDESCRIPTION_4004_LABEL.equals(view.getType())) {
					return new Route_descriptionEditPart(view);
				} else {
					return new Route_descriptionTextEditPart(view);
				}
			case 4005:
				if (TaiPanSemanticHints.Route_3001Labels.ROUTERELIABILITY_4005_LABEL.equals(view.getType())) {
					return new Route_reliabilityEditPart(view);
				} else {
					return new Route_reliabilityTextEditPart(view);
				}
			case 3002:
				return new DestinationEditPart(view);
			case 4006:
				if (TaiPanSemanticHints.ShipDestination_3002Labels.SHIPDESTINATIONLABEL15275681_4006_LABEL.equals(view.getType())) {
					return new Destination_destinatEditPart(view);
				} else {
					return new Destination_destinatTextEditPart(view);
				}
			}
		}
		return createUnrecognizedEditPart(context, model);
	}

	/**
	 * @generated
	 */
	private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
		// Handle creation of unrecognized child node EditParts here
		return null;
	}

}
