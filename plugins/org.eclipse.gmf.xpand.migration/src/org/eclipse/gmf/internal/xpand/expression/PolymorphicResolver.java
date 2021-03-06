/*
 * <copyright>
 *
 * Copyright (c) 2005-2007 Sven Efftinge and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sven Efftinge - Initial API and implementation
 *
 * </copyright>
 */
package org.eclipse.gmf.internal.xpand.expression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.gmf.internal.xpand.model.XpandDefinition;
import org.eclipse.gmf.internal.xpand.xtend.ast.Extension;

/**
 * @author Sven Efftinge
 * @author Arno Haase
 */
public class PolymorphicResolver {

	public static XpandDefinition filterDefinition(final HashMap<XpandDefinition, List<EClassifier>> resolvedDefs, EClassifier targetType, List<EClassifier> paramTypes) {
        final List<EClassifier> allParams = new ArrayList<EClassifier>();
        allParams.add(targetType);
        allParams.addAll(paramTypes);

        final List<XpandDefinition> candidateDefinition = new ArrayList<XpandDefinition>();
        for (XpandDefinition def : resolvedDefs.keySet()) {
            final List<? extends EClassifier> featureParamTypes = resolvedDefs.get(def);
            if ((featureParamTypes.size() == allParams.size())
                    && (typesComparator.compare(featureParamTypes, allParams) >= 0)) {
            	candidateDefinition.add(def);
            }
        }
		final Comparator<XpandDefinition> comparator = new Comparator<XpandDefinition>() {
	        public int compare(XpandDefinition d1, XpandDefinition d2) {
	            return typesComparator.compare(resolvedDefs.get(d1), resolvedDefs.get(d2));
	        }
	    };
	    try {
	    	return filterWithComparator(candidateDefinition, comparator);
	    } catch (IllegalStateException ex) {
			throw new RuntimeException("Ambiguous definitions " + candidateDefinition.get(0).toString() + " and "
                    + candidateDefinition.get(1).toString() + " for param types " + paramTypes);
	    }
	}

    public final static Extension getExtension(final Set<Extension> extensions, final String name, final List<EClassifier> paramTypes) {
        final List<Extension> candidateExtensions = new ArrayList<Extension>();
        for (Extension ext : extensions) {
            if (ext.getName().equals(name)) {
                final List<? extends EClassifier> featureParamTypes = ext.getParameterTypes();
                if ((featureParamTypes.size() == paramTypes.size())
                        && (typesComparator.compare(featureParamTypes, paramTypes) >= 0)) {
                    candidateExtensions.add(ext);
                }
            }
        }
		final Comparator<Extension> extensionComparator = new Comparator<Extension>() {
	        public int compare(Extension e1, Extension e2) {
	            return typesComparator.compare(e1.getParameterTypes(), e2.getParameterTypes());
	        }
	    };
	    try {
	    	return filterWithComparator(candidateExtensions, extensionComparator);
	    } catch (IllegalStateException ex) {
            // candidateExtensions was passed by reference, hence, it's already sort
			throw new RuntimeException("Ambiguous extensions " + candidateExtensions.get(0).toString() + " and "
                    + candidateExtensions.get(1).toString() + " for param types " + paramTypes);
	    }
    }
    
    @SuppressWarnings("unchecked")
	public static EOperation filterOperation(List<EOperation> allOperations, String name, EClassifier targetType, List<EClassifier> paramTypes) {
		final Map<EOperation, List<EClassifier>> candidates = new HashMap<EOperation, List<EClassifier>>();
		ArrayList<EClassifier> expectedParamsWithTarget = new ArrayList(paramTypes.size() + 1);
		expectedParamsWithTarget.add(targetType);
		expectedParamsWithTarget.addAll(paramTypes);
		for (EOperation op : allOperations) {
			if (op.getName().equals(name) && (op.getEParameters().size() == paramTypes.size())) {
				List<EClassifier> candidateOperationParams = new ArrayList<EClassifier>(paramTypes.size() + 1);
				if (op.getEContainingClass() != null) {
					candidateOperationParams.add(op.getEContainingClass());
				}
				List<EParameter> allParams = op.getEParameters();
				for (EParameter p : allParams ) {
					candidateOperationParams.add(p.getEType());
				}
				if (typesComparator.compare(candidateOperationParams, op.getEContainingClass() != null ? expectedParamsWithTarget : paramTypes) >= 0) {
					candidates.put(op, candidateOperationParams);
				}
			}
		}
		final Comparator<EOperation> comparator = new Comparator<EOperation>() {
	        public int compare(EOperation o1, EOperation o2) {
	            return typesComparator.compare(candidates.get(o1), candidates.get(o2));
	        }
	    };
    	final List<EOperation> candidates2 = new LinkedList<EOperation>();
	    try {
	    	candidates2.addAll(candidates.keySet());
	    	return filterWithComparator(candidates2, comparator);
	    } catch (IllegalStateException ex) {
			throw new RuntimeException("Ambiguous definitions " + candidates2.get(0).toString() + " and "
                    + candidates2.get(1).toString() + " for param types " + paramTypes, ex);
	    }    }
    /**
     * @throws IllegalStateException when there are more than one candidates with same priority
     */
    private static <T> T filterWithComparator(List<T> candidates, Comparator<T> comparator) throws IllegalStateException {
        if (candidates.size() == 1) {
			return candidates.get(0);
		} else if (candidates.isEmpty()) {
			return null;
		} else {
            // sort features by specialization
            Collections.sort(candidates, comparator);

            if (comparator.compare(candidates.get(1), candidates.get(0)) > 0) {
				return candidates.get(0);
			} else {
				throw new IllegalStateException();
			}
        }
    }

    private final static TypesComparator typesComparator = new TypesComparator();
}
