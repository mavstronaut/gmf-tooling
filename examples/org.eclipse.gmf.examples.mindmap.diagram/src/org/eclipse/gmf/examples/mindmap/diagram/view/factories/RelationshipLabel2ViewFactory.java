/*
 *
 * Copyright (c) 2006, 2007 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Richard Gronback (Borland) - initial API and implementation
 
 */
package org.eclipse.gmf.examples.mindmap.diagram.view.factories;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.view.factories.AbstractLabelViewFactory;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.diagram.ui.util.MeasurementUnitHelper;

import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;

import org.eclipse.gmf.runtime.notation.Location;

/**
 * @generated
 */
public class RelationshipLabel2ViewFactory extends AbstractLabelViewFactory {

	/**
	 * @generated
	 */
	public View createView(IAdaptable semanticAdapter, View containerView,
			String semanticHint, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Node view = (Node) super.createView(semanticAdapter, containerView,
				semanticHint, index, persisted, preferencesHint);
		Location location = (Location) view.getLayoutConstraint();
		IMapMode mapMode = MeasurementUnitHelper.getMapMode(containerView
				.getDiagram().getMeasurementUnit());
		location.setX(mapMode.DPtoLP(0));
		location.setY(mapMode.DPtoLP(40));
		return view;
	}

	/**
	 * @generated
	 */
	protected List createStyles(View view) {
		List styles = new ArrayList();
		return styles;
	}
}
