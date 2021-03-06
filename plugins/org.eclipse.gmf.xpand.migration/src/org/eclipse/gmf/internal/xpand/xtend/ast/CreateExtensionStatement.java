/*******************************************************************************
 * Copyright (c) 2005, 2006 committers of openArchitectureWare and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     committers of openArchitectureWare - initial API and implementation
 *******************************************************************************/
package org.eclipse.gmf.internal.xpand.xtend.ast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.gmf.internal.xpand.BuiltinMetaModel;
import org.eclipse.gmf.internal.xpand.expression.AnalysationIssue;
import org.eclipse.gmf.internal.xpand.expression.EvaluationException;
import org.eclipse.gmf.internal.xpand.expression.ExecutionContext;
import org.eclipse.gmf.internal.xpand.expression.Variable;
import org.eclipse.gmf.internal.xpand.expression.ast.DeclaredParameter;
import org.eclipse.gmf.internal.xpand.expression.ast.Expression;
import org.eclipse.gmf.internal.xpand.expression.ast.Identifier;


public class CreateExtensionStatement extends Extension {

    private final Expression expression;

    private final String returnVarName;

    public CreateExtensionStatement(final int start, final int end, final int line, final int startOffset, final int endOffset, final Identifier name,
            final Identifier returnType, final Identifier rtName, final List<DeclaredParameter> params, final Expression expr,
            final boolean isPrivate) {
        super(start, end, line, startOffset, endOffset, name, returnType, params, true, isPrivate);
        expression = expr;
        returnVarName = rtName!=null ? rtName.getValue() : "this";
    }

    @Override
    protected EClassifier internalGetReturnType(final EClassifier[] parameters, final ExecutionContext ctx, final Set<AnalysationIssue> issues) {
        return ctx.getTypeForName(getReturnTypeIdentifier().getValue());
    }

    @Override
    public void analyzeInternal(ExecutionContext ctx, final Set<AnalysationIssue> issues) {
        final EClassifier t = ctx.getTypeForName(returnType.getValue());
        if (t == null) {
            issues.add(new AnalysationIssue(AnalysationIssue.Type.TYPE_NOT_FOUND, "Couldn't resolve type " + returnType
                    + "!", returnType));
            return;
        }
        ctx = ctx.cloneWithVariable(new Variable(returnVarName, t));
        expression.analyze(ctx, issues);
    }

    private final Map<List<Object>, Object> cache = new HashMap<List<Object>, Object>();

    @Override
    public Object evaluate(final Object[] parameters, ExecutionContext ctx) {
        final List<Object> l = Arrays.asList(parameters);
        if (cache.containsKey(l)) {
            return cache.get(l);
        }
        ctx = ctx.cloneWithResource(getExtensionFile());
        final EClassifier t = ctx.getTypeForName(returnType.getValue());
        if (t == null) {
            throw new EvaluationException("Couldn't resolve type " + returnType, returnType);
        }
        final Object inst = BuiltinMetaModel.newInstance(t);
        cache.put(l, inst);
        ctx = ctx.cloneWithVariable(new Variable(returnVarName, inst));
        for (int i = 0; i < parameters.length; i++) {
            final Object object = parameters[i];
            ctx = ctx.cloneWithVariable(new Variable(getParameterNames().get(i), object));
        }
        expression.evaluate(ctx);
        return inst;
    }

    @Override
    protected Object evaluateInternal(final Object[] parameters, final ExecutionContext ctx) {
        throw new UnsupportedOperationException();
    }

}
