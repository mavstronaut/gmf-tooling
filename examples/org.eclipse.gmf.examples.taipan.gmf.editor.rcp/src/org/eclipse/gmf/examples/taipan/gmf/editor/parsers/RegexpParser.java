/*
 * Copyright (c) 2006, 2007 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Dmitry Stadnik (Borland) - initial API and implementation
 */
package org.eclipse.gmf.examples.taipan.gmf.editor.parsers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserEditStatus;

/**
 * @generated
 */
public class RegexpParser extends AbstractParser {

	/**
	 * @generated
	 */
	public RegexpParser(EAttribute[] features) {
		super(features);
	}

	/**
	 * @generated
	 */
	public String getEditPattern() {
		String pattern = super.getEditPattern();
		return pattern != null ? pattern : " "; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	public String getPrintString(IAdaptable adapter, int flags) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	public String getEditString(IAdaptable adapter, int flags) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	public IParserEditStatus isValidEditString(IAdaptable adapter, String editString) {
		if (editString == null) {
			return ParserEditStatus.UNEDITABLE_STATUS;
		}
		Object[] values = editString.split(getEditPattern());
		return validateNewValues(values);
	}

	/**
	 * @generated
	 */
	public ICommand getParseCommand(IAdaptable adapter, String newString, int flags) {
		if (newString == null) {
			return UnexecutableCommand.INSTANCE;
		}
		Object[] values = newString.split(getEditPattern());
		return super.getParseCommand(adapter, values, flags);
	}
}