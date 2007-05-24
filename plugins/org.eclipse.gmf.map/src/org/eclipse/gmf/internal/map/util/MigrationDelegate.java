/*
 * Copyright (c) 2007 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Borland - initial API and implementation
 */
package org.eclipse.gmf.internal.map.util;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.internal.common.migrate.MigrationDelegateImpl;
import org.eclipse.gmf.mappings.FeatureLabelMapping;
import org.eclipse.gmf.mappings.GMFMapFactory;
import org.eclipse.gmf.mappings.GMFMapPackage;
import org.eclipse.gmf.mappings.LabelMapping;
import org.eclipse.gmf.mappings.MappingEntry;

class MigrationDelegate extends MigrationDelegateImpl {
	private Map<LabelMapping, FeatureLabelMapping> myLabelMappingMigrations;
	private EAttribute myLabelMapping_ViewPattern;
	private EAttribute myLabelMapping_EditPattern;
	private EReference myLabelMapping_Features;
	
	MigrationDelegate() {
	}

	void init() {
		//registerNarrowReferenceType(GMFMapPackage.eINSTANCE.getFeatureSeqInitializer_Initializers(), GMFMapPackage.eINSTANCE.getFeatureValueSpec());
		// -->
		registerNarrowedAbstractType("FeatureInitializer", GMFMapPackage.eINSTANCE.getFeatureValueSpec());
		
		//registerNarrowReferenceType(GMFMapPackage.eINSTANCE.getMappingEntry_LabelMappings(), GMFMapPackage.eINSTANCE.getFeatureLabelMapping());
		// -->
		myLabelMapping_Features = (EReference) EcoreUtil.copy(GMFMapPackage.eINSTANCE.getFeatureLabelMapping_Features());
		myLabelMapping_ViewPattern = (EAttribute) EcoreUtil.copy(GMFMapPackage.eINSTANCE.getFeatureLabelMapping_ViewPattern());
		myLabelMapping_EditPattern = (EAttribute) EcoreUtil.copy(GMFMapPackage.eINSTANCE.getFeatureLabelMapping_EditPattern());
		{
			Map<String, EStructuralFeature> renamings = new HashMap<String, EStructuralFeature>();
			renamings.put(myLabelMapping_ViewPattern.getName(), myLabelMapping_ViewPattern);
			renamings.put(myLabelMapping_EditPattern.getName(), myLabelMapping_EditPattern);
			renamings.put(myLabelMapping_Features.getName(), myLabelMapping_Features);
			registerRenamedAttributes(GMFMapPackage.eINSTANCE.getLabelMapping(), renamings);
		}
		
		myLabelMappingMigrations = null;
	}

	@Override
	public boolean setValue(EObject object, EStructuralFeature feature, Object value, int position) {
		if (myLabelMapping_ViewPattern.equals(feature)) {
			LabelMapping mapping = (LabelMapping) object;
			String viewPattern = (String) value;
			FeatureLabelMapping migratedMapping = saveFeatureLabelMappingFor(mapping);
			migratedMapping.setViewPattern(viewPattern);
			fireMigrationApplied(true);
		} else if (myLabelMapping_EditPattern.equals(feature)) {
			LabelMapping mapping = (LabelMapping) object;
			String editPattern = (String) value;
			FeatureLabelMapping migratedMapping = saveFeatureLabelMappingFor(mapping);
			migratedMapping.setViewPattern(editPattern);
			fireMigrationApplied(true);
		} else if (myLabelMapping_Features.equals(feature)) {
			LabelMapping mapping = (LabelMapping) object;
			EAttribute attribute = (EAttribute) value;
			FeatureLabelMapping migratedMapping = saveFeatureLabelMappingFor(mapping);
			migratedMapping.getFeatures().add(attribute);
			fireMigrationApplied(true);
		} else {
			// other cases are would be processed as defaults
			return super.setValue(object, feature, value, position);
		}
		return true;
	}

	private FeatureLabelMapping saveFeatureLabelMappingFor(LabelMapping labelMapping) {
		if (myLabelMappingMigrations == null) {
			myLabelMappingMigrations = new HashMap<LabelMapping, FeatureLabelMapping>();
		}
		FeatureLabelMapping migrated = myLabelMappingMigrations.get(labelMapping);
		if (migrated == null) {
			migrated = GMFMapFactory.eINSTANCE.createFeatureLabelMapping();
			myLabelMappingMigrations.put(labelMapping, migrated);
		}
		return migrated;
	}
	
	private Map<LabelMapping, FeatureLabelMapping> getSavedLabelMappingMigrations() {
		return myLabelMappingMigrations;
	}

	@Override
	public void postProcess() {
		if (getSavedLabelMappingMigrations() == null) {
			return;
		}
		for (LabelMapping mapping : getSavedLabelMappingMigrations().keySet()) {
			FeatureLabelMapping migrated = getSavedLabelMappingMigrations().get(mapping);
			if (!migrated.getFeatures().isEmpty()) {
				MappingEntry entry = mapping.getMapEntry();
				EList<LabelMapping> labelMappings = entry.getLabelMappings();
				int originalIndex = labelMappings.indexOf(mapping);
				if (originalIndex != -1) {
					migrated.setDiagramLabel(mapping.getDiagramLabel());
					if (mapping.isReadOnly()) {
						migrated.setReadOnly(true);
					}
					labelMappings.set(originalIndex, migrated);
				}
			}
		}
	}
}
