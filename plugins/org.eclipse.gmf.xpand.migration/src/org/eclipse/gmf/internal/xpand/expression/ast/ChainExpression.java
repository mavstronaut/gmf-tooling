/*
 * <copyright>
 *
 * Copyright (c) 2005-2006 Sven Efftinge and others.
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
package org.eclipse.gmf.internal.xpand.expression.ast;

import java.util.Set;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.gmf.internal.xpand.expression.AnalysationIssue;
import org.eclipse.gmf.internal.xpand.expression.ExecutionContext;

/**
 * @author Sven Efftinge
 * @author Arno Haase
 */
public class ChainExpression extends Expression {

    private Expression first;

    private Expression next;

    public ChainExpression(final int start, final int end, final int line, final int startOffset, final int endOffset, final Expression first, final Expression next) {
        super(start, end, line, startOffset, endOffset);
        this.first = first;
        this.next = next;
    }

    @Override
    protected Object evaluateInternal(final ExecutionContext ctx) {
        getFirst().evaluate(ctx);
        return getNext().evaluate(ctx);
    }

    public EClassifier analyze(final ExecutionContext ctx, final Set<AnalysationIssue> issues) {
        getFirst().analyze(ctx, issues);
        return getNext().analyze(ctx, issues);
    }

    public Expression getFirst() {
        return first;
    }

    public Expression getNext() {
        return next;
    }

    @Override
    public String toString() {
        return getFirst() + "->" + getNext();
    }
}
