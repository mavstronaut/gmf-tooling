/*
 * Copyright (c) 2008 Borland Software Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Artem Tikhomirov (Borland) - initial API and implementation
 *     Alexander Shatalin (Borland) - initial API and implementation
 */
package org.eclipse.gmf.graphdef.editor.sheet;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gmf.gmfgraph.BasicFont;
import org.eclipse.gmf.gmfgraph.ColorConstants;
import org.eclipse.gmf.gmfgraph.ConstantColor;
import org.eclipse.gmf.gmfgraph.Figure;
import org.eclipse.gmf.gmfgraph.FontStyle;
import org.eclipse.gmf.gmfgraph.GMFGraphFactory;
import org.eclipse.gmf.gmfgraph.GMFGraphPackage;
import org.eclipse.gmf.gmfgraph.LineKind;
import org.eclipse.gmf.gmfgraph.RGBColor;
import org.eclipse.gmf.gmfgraph.RoundedRectangle;
import org.eclipse.gmf.gmfgraph.Shape;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public class FigureSection extends AbstractPropertySection implements ChangeTracker, Listener {

	private org.eclipse.emf.common.notify.Adapter[] myModelListeners;

	private boolean myIsCommit;
	private boolean myIsRefresh;
	private Object myInput;

	private Group myStyleRadios;
	private Group myCommonStyle;
	private Group myRoundedRectStyle;
	private Group myForegroundColor;
	private Group myBackgroundColor;
	private Group myFont;
	private Button myR1;
	private Button myR2;
	private Button myR3;
	private Button myR4;
	private Button myR5;
	private Button myR6;
	private Spinner myLineWidth;
	private Button myFill;
	private Button myOutline;
	private Button myFillXor;
	private Button myOutlineXor;
	private Spinner myCornerWidth;
	private Spinner myCornerHeight;
	private Button myForegroundRgbRadio;
	private Button myForegroundPredeinedRadio;
	private Button myForegroundNoValueRadio;
	private Group myForegroundRGBValues;
	private Group myForegroundPredefinedValue;
	private Button myBackgroundRgbRadio;
	private Button myBackgroundPredeinedRadio;
	private Button myBackgroundNoRadio;
	private Group myBackgroundRGBValues;
	private Group myBackgroundPredefinedValue;
	private Button myFontSetFont;
	private Text myFontFaceName;
	private Spinner myFontHeight;
	private Combo myFontStyle;
	private Spinner myForegroundRed;
	private Spinner myForegroundGreen;
	private Spinner myForegroundBlue;
	private Combo myForegroundPredefinedColor;
	private Spinner myBackgroundRed;
	private Spinner myBackgroundGreen;
	private Spinner myBackgroundBlue;
	private Combo myBackgroundPredefinedColor;

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage page) {
		super.createControls(parent, page);
		getWidgetFactory().paintBordersFor(parent);
		myStyleRadios = createGroup(parent, "Line Style");
		myR1 = getWidgetFactory().createButton(myStyleRadios, "Solid", SWT.RADIO);
		myR2 = getWidgetFactory().createButton(myStyleRadios, "- - -", SWT.RADIO);
		myR3 = getWidgetFactory().createButton(myStyleRadios, ". . .", SWT.RADIO);
		myR4 = getWidgetFactory().createButton(myStyleRadios, "- . - .", SWT.RADIO);
		myR5 = getWidgetFactory().createButton(myStyleRadios, "- . . - . .", SWT.RADIO);
		myR6 = getWidgetFactory().createButton(myStyleRadios, "Custom", SWT.RADIO);
		myStyleRadios.setLayout(new org.eclipse.swt.layout.FillLayout(org.eclipse.swt.SWT.VERTICAL));
		myCommonStyle = createGroup(parent, "Draw");
		createLabel(myCommonStyle, "Line width");
		myLineWidth = new Spinner(myCommonStyle, SWT.FLAT);
		myLineWidth.setMinimum(0);
		myLineWidth.setMaximum(100);
		myLineWidth.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER); // @see #145837
		myFill = getWidgetFactory().createButton(myCommonStyle, "Fill", SWT.CHECK);
		myOutline = getWidgetFactory().createButton(myCommonStyle, "Outline", SWT.CHECK);
		myFillXor = getWidgetFactory().createButton(myCommonStyle, "XOR Fill", SWT.CHECK);
		myOutlineXor = getWidgetFactory().createButton(myCommonStyle, "XOR Outline", SWT.CHECK);
		myCommonStyle.setLayout(new org.eclipse.swt.layout.GridLayout(2, true));
		myRoundedRectStyle = createGroup(parent, "Corners");
		createLabel(myRoundedRectStyle, "Width:");
		myCornerWidth = new Spinner(myRoundedRectStyle, SWT.FLAT);
		myCornerWidth.setMinimum(0);
		myCornerWidth.setMaximum(100);
		myCornerWidth.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER); // @see #145837
		createLabel(myRoundedRectStyle, "Height:");
		myCornerHeight = new Spinner(myRoundedRectStyle, SWT.FLAT);
		myCornerHeight.setMinimum(0);
		myCornerHeight.setMaximum(100);
		myCornerHeight.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER); // @see #145837
		myRoundedRectStyle.setLayout(new org.eclipse.swt.layout.GridLayout(2, true));
		myForegroundColor = createGroup(parent, "Foreground Color");
		myForegroundRgbRadio = getWidgetFactory().createButton(myForegroundColor, "RGB", SWT.RADIO);
		myForegroundPredeinedRadio = getWidgetFactory().createButton(myForegroundColor, "Predefined", SWT.RADIO);
		myForegroundNoValueRadio = getWidgetFactory().createButton(myForegroundColor, "None", SWT.RADIO);
		myForegroundRGBValues = createGroup(myForegroundColor, "RGB Values");
		createLabel(myForegroundRGBValues, "Red");
		myForegroundRed = new Spinner(myForegroundRGBValues, SWT.FLAT);
		myForegroundRed.setMinimum(0);
		myForegroundRed.setMaximum(255);
		myForegroundRed.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER); // @see #145837
		createLabel(myForegroundRGBValues, "Green");
		myForegroundGreen = new Spinner(myForegroundRGBValues, SWT.FLAT);
		myForegroundGreen.setMinimum(0);
		myForegroundGreen.setMaximum(255);
		myForegroundGreen.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER); // @see #145837
		createLabel(myForegroundRGBValues, "Blue");
		myForegroundBlue = new Spinner(myForegroundRGBValues, SWT.FLAT);
		myForegroundBlue.setMinimum(0);
		myForegroundBlue.setMaximum(255);
		myForegroundBlue.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER); // @see #145837
		myForegroundRGBValues.setLayout(new org.eclipse.swt.layout.GridLayout(2, false));
		myForegroundPredefinedValue = createGroup(myForegroundColor, "Predefined Colors");
		myForegroundPredefinedColor = new Combo(myForegroundPredefinedValue, SWT.FLAT | SWT.READ_ONLY);
		myForegroundPredefinedColor.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		getWidgetFactory().adapt(myForegroundPredefinedColor, false, false);
		myForegroundPredefinedValue.setLayout(new org.eclipse.swt.layout.GridLayout(1, false));
		myForegroundColor.setLayout(new org.eclipse.swt.layout.FormLayout());
		org.eclipse.swt.layout.FormData myForegroundColorFD;
		myForegroundColorFD = new org.eclipse.swt.layout.FormData();
		myForegroundRgbRadio.setLayoutData(myForegroundColorFD);
		myForegroundColorFD = new org.eclipse.swt.layout.FormData();
		myForegroundColorFD.top = new org.eclipse.swt.layout.FormAttachment(myForegroundRgbRadio, 5, org.eclipse.swt.SWT.BOTTOM);
		myForegroundPredeinedRadio.setLayoutData(myForegroundColorFD);
		myForegroundColorFD = new org.eclipse.swt.layout.FormData();
		myForegroundColorFD.top = new org.eclipse.swt.layout.FormAttachment(myForegroundPredeinedRadio, 5, org.eclipse.swt.SWT.BOTTOM);
		myForegroundNoValueRadio.setLayoutData(myForegroundColorFD);
		myForegroundColorFD = new org.eclipse.swt.layout.FormData();
		myForegroundColorFD.left = new org.eclipse.swt.layout.FormAttachment(myForegroundPredeinedRadio, 10, org.eclipse.swt.SWT.RIGHT);
		myForegroundRGBValues.setLayoutData(myForegroundColorFD);
		myForegroundColorFD = new org.eclipse.swt.layout.FormData();
		myForegroundColorFD.left = new org.eclipse.swt.layout.FormAttachment(myForegroundPredeinedRadio, 10, org.eclipse.swt.SWT.RIGHT);
		myForegroundPredefinedValue.setLayoutData(myForegroundColorFD);
		myBackgroundColor = createGroup(parent, "Background Color");
		myBackgroundRgbRadio = getWidgetFactory().createButton(myBackgroundColor, "RGB", SWT.RADIO);
		myBackgroundPredeinedRadio = getWidgetFactory().createButton(myBackgroundColor, "Predefined", SWT.RADIO);
		myBackgroundNoRadio = getWidgetFactory().createButton(myBackgroundColor, "None", SWT.RADIO);
		myBackgroundRGBValues = createGroup(myBackgroundColor, "RGB Values");
		createLabel(myBackgroundRGBValues, "Red");
		myBackgroundRed = new Spinner(myBackgroundRGBValues, SWT.FLAT);
		myBackgroundRed.setMinimum(0);
		myBackgroundRed.setMaximum(255);
		myBackgroundRed.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER); // @see #145837
		createLabel(myBackgroundRGBValues, "Green");
		myBackgroundGreen = new Spinner(myBackgroundRGBValues, SWT.FLAT);
		myBackgroundGreen.setMinimum(0);
		myBackgroundGreen.setMaximum(255);
		myBackgroundGreen.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER); // @see #145837
		createLabel(myBackgroundRGBValues, "Blue");
		myBackgroundBlue = new Spinner(myBackgroundRGBValues, SWT.FLAT);
		myBackgroundBlue.setMinimum(0);
		myBackgroundBlue.setMaximum(255);
		myBackgroundBlue.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER); // @see #145837
		myBackgroundRGBValues.setLayout(new org.eclipse.swt.layout.GridLayout(2, false));
		myBackgroundPredefinedValue = createGroup(myBackgroundColor, "Predefined Colors");
		myBackgroundPredefinedColor = new Combo(myBackgroundPredefinedValue, SWT.FLAT | SWT.READ_ONLY);
		myBackgroundPredefinedColor.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		getWidgetFactory().adapt(myBackgroundPredefinedColor, false, false);
		myBackgroundPredefinedValue.setLayout(new org.eclipse.swt.layout.GridLayout(1, false));
		myBackgroundColor.setLayout(new org.eclipse.swt.layout.FormLayout());
		org.eclipse.swt.layout.FormData myBackgroundColorFD;
		myBackgroundColorFD = new org.eclipse.swt.layout.FormData();
		myBackgroundRgbRadio.setLayoutData(myBackgroundColorFD);
		myBackgroundColorFD = new org.eclipse.swt.layout.FormData();
		myBackgroundColorFD.top = new org.eclipse.swt.layout.FormAttachment(myBackgroundRgbRadio, 5, org.eclipse.swt.SWT.BOTTOM);
		myBackgroundPredeinedRadio.setLayoutData(myBackgroundColorFD);
		myBackgroundColorFD = new org.eclipse.swt.layout.FormData();
		myBackgroundColorFD.top = new org.eclipse.swt.layout.FormAttachment(myBackgroundPredeinedRadio, 5, org.eclipse.swt.SWT.BOTTOM);
		myBackgroundNoRadio.setLayoutData(myBackgroundColorFD);
		myBackgroundColorFD = new org.eclipse.swt.layout.FormData();
		myBackgroundColorFD.left = new org.eclipse.swt.layout.FormAttachment(myBackgroundPredeinedRadio, 10, org.eclipse.swt.SWT.RIGHT);
		myBackgroundRGBValues.setLayoutData(myBackgroundColorFD);
		myBackgroundColorFD = new org.eclipse.swt.layout.FormData();
		myBackgroundColorFD.left = new org.eclipse.swt.layout.FormAttachment(myBackgroundPredeinedRadio, 10, org.eclipse.swt.SWT.RIGHT);
		myBackgroundPredefinedValue.setLayoutData(myBackgroundColorFD);
		myFont = createGroup(parent, "Font");
		myFontSetFont = getWidgetFactory().createButton(myFont, "Set Font", SWT.CHECK);
		createLabel(myFont, "Face Name");
		myFontFaceName = getWidgetFactory().createText(myFont, null);
		createLabel(myFont, "Height");
		myFontHeight = new Spinner(myFont, SWT.FLAT);
		myFontHeight.setMinimum(0);
		myFontHeight.setMaximum(2147483647);
		myFontHeight.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER); // @see #145837
		createLabel(myFont, "Style");
		myFontStyle = new Combo(myFont, SWT.FLAT | SWT.READ_ONLY);
		myFontStyle.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		getWidgetFactory().adapt(myFontStyle, false, false);
		myFont.setLayout(new org.eclipse.swt.layout.GridLayout(2, false));
		org.eclipse.jface.layout.GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.BEGINNING).span(2, 1).applyTo(myFontSetFont);
		org.eclipse.jface.layout.GridDataFactory.swtDefaults().align(SWT.FILL, SWT.BEGINNING).grab(true, false).applyTo(myFontFaceName);
		org.eclipse.jface.layout.GridDataFactory.swtDefaults().align(SWT.FILL, SWT.BEGINNING).grab(true, false).applyTo(myFontHeight);
		org.eclipse.jface.layout.GridDataFactory.swtDefaults().align(SWT.FILL, SWT.BEGINNING).grab(true, false).applyTo(myFontStyle);

		parent.setLayout(new org.eclipse.swt.layout.FormLayout());
		org.eclipse.swt.layout.FormData parentFD;
		parentFD = new org.eclipse.swt.layout.FormData();
		parentFD.left = new org.eclipse.swt.layout.FormAttachment(0, 10);
		myStyleRadios.setLayoutData(parentFD);
		parentFD = new org.eclipse.swt.layout.FormData();
		parentFD.left = new org.eclipse.swt.layout.FormAttachment(myStyleRadios, 10, org.eclipse.swt.SWT.RIGHT);
		myCommonStyle.setLayoutData(parentFD);
		parentFD = new org.eclipse.swt.layout.FormData();
		parentFD.left = new org.eclipse.swt.layout.FormAttachment(myStyleRadios, 10, org.eclipse.swt.SWT.RIGHT);
		parentFD.top = new org.eclipse.swt.layout.FormAttachment(myCommonStyle, 5, org.eclipse.swt.SWT.BOTTOM);
		myRoundedRectStyle.setLayoutData(parentFD);
		parentFD = new org.eclipse.swt.layout.FormData();
		parentFD.left = new org.eclipse.swt.layout.FormAttachment(myCommonStyle, 10, org.eclipse.swt.SWT.RIGHT);
		myForegroundColor.setLayoutData(parentFD);
		parentFD = new org.eclipse.swt.layout.FormData();
		parentFD.left = new org.eclipse.swt.layout.FormAttachment(myCommonStyle, 10, org.eclipse.swt.SWT.RIGHT);
		parentFD.top = new org.eclipse.swt.layout.FormAttachment(myForegroundColor, 5, org.eclipse.swt.SWT.BOTTOM);
		myBackgroundColor.setLayoutData(parentFD);
		parentFD = new org.eclipse.swt.layout.FormData();
		parentFD.left = new org.eclipse.swt.layout.FormAttachment(myForegroundColor, 10, org.eclipse.swt.SWT.RIGHT);
		myFont.setLayoutData(parentFD);
		// TODO myFontStyle.setItems(VALUES.toString().toArray());
		for (org.eclipse.emf.common.util.Enumerator e : FontStyle.VALUES) {
			myFontStyle.add(e.getName());
		}
		// TODO myForegroundPredefinedColor.setItems(VALUES.toString().toArray());
		for (org.eclipse.emf.common.util.Enumerator e : ColorConstants.VALUES) {
			myForegroundPredefinedColor.add(e.getName());
		}
		// TODO myBackgroundPredefinedColor.setItems(VALUES.toString().toArray());
		for (org.eclipse.emf.common.util.Enumerator e : ColorConstants.VALUES) {
			myBackgroundPredefinedColor.add(e.getName());
		}

		for (Text t : new Text[] { myFontFaceName }) {
			t.addListener(SWT.Modify, this);
			t.addListener(SWT.FocusOut, this);
			t.addListener(SWT.KeyDown, this);
		}
		for (Spinner s : new Spinner[] { myLineWidth, myCornerWidth, myCornerHeight, myFontHeight, myForegroundRed, myForegroundGreen, myForegroundBlue, myBackgroundRed, myBackgroundGreen,
				myBackgroundBlue }) {
			s.addListener(SWT.Modify, this);
			s.addListener(SWT.FocusOut, this);
		}
		for (Widget w : new Widget[] { myR1, myR2, myR3, myR4, myR5, myR6, myForegroundRgbRadio, myForegroundPredeinedRadio, myForegroundNoValueRadio, myBackgroundRgbRadio,
				myBackgroundPredeinedRadio, myBackgroundNoRadio, myFill, myOutline, myFillXor, myOutlineXor, myFontSetFont, myFontStyle, myForegroundPredefinedColor, myBackgroundPredefinedColor }) {
			w.addListener(SWT.Selection, this);
		}
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		Object newInput = null;
		if (selection instanceof IStructuredSelection && ((IStructuredSelection) selection).size() == 1) {
			newInput = unwrap(((IStructuredSelection) selection).getFirstElement());
		}
		if (newInput != myInput) {
			if (myInput != null) {
				detach();
			}
			myInput = newInput;
			if (newInput != null) {
				attach();
			}
		}
	}

	protected void commit() {

		((Shape) getInput()).setLineWidth(myLineWidth.getSelection());
		((Shape) getInput()).setFill(myFill.getSelection());
		((Shape) getInput()).setOutline(myOutline.getSelection());
		((Shape) getInput()).setXorFill(myFillXor.getSelection());
		((Shape) getInput()).setXorOutline(myOutlineXor.getSelection());

		if (myR1.getSelection()) {
			((Shape) getInput()).setLineKind(LineKind.LINE_SOLID_LITERAL);
		}
		if (myR2.getSelection()) {
			((Shape) getInput()).setLineKind(LineKind.LINE_DASH_LITERAL);
		}
		if (myR3.getSelection()) {
			((Shape) getInput()).setLineKind(LineKind.LINE_DOT_LITERAL);
		}
		if (myR4.getSelection()) {
			((Shape) getInput()).setLineKind(LineKind.LINE_DASHDOT_LITERAL);
		}
		if (myR5.getSelection()) {
			((Shape) getInput()).setLineKind(LineKind.LINE_DASHDOTDOT_LITERAL);
		}
		if (myR6.getSelection()) {
			((Shape) getInput()).setLineKind(LineKind.LINE_CUSTOM_LITERAL);
		}
		if (myRoundedRectStyle.isVisible()) {
			((RoundedRectangle) getInput()).setCornerWidth(myCornerWidth.getSelection());
			((RoundedRectangle) getInput()).setCornerHeight(myCornerHeight.getSelection());
		}
		if (myForegroundRGBValues.isVisible()) {
			getInput().setForegroundColor(GMFGraphFactory.eINSTANCE.createRGBColor());
			((RGBColor) getInput().getForegroundColor()).setRed(myForegroundRed.getSelection());
			((RGBColor) getInput().getForegroundColor()).setGreen(myForegroundGreen.getSelection());
			((RGBColor) getInput().getForegroundColor()).setBlue(myForegroundBlue.getSelection());
		}
		if (myForegroundPredefinedValue.isVisible()) {
			getInput().setForegroundColor(GMFGraphFactory.eINSTANCE.createConstantColor());
			((ConstantColor) getInput().getForegroundColor()).setValue(ColorConstants.get(myForegroundPredefinedColor.getSelectionIndex()));
		}
		if (myForegroundNoValueRadio.getSelection()) {
			getInput().setForegroundColor(null);
		}
		if (myBackgroundRGBValues.isVisible()) {
			getInput().setBackgroundColor(GMFGraphFactory.eINSTANCE.createRGBColor());
			((RGBColor) getInput().getBackgroundColor()).setRed(myBackgroundRed.getSelection());
			((RGBColor) getInput().getBackgroundColor()).setGreen(myBackgroundGreen.getSelection());
			((RGBColor) getInput().getBackgroundColor()).setBlue(myBackgroundBlue.getSelection());
		}
		if (myBackgroundPredefinedValue.isVisible()) {
			getInput().setBackgroundColor(GMFGraphFactory.eINSTANCE.createConstantColor());
			((ConstantColor) getInput().getBackgroundColor()).setValue(ColorConstants.get(myBackgroundPredefinedColor.getSelectionIndex()));
		}
		if (myBackgroundNoRadio.getSelection()) {
			getInput().setBackgroundColor(null);
		}
		if (myFontFaceName.isEnabled() && myFontHeight.isEnabled() && myFontStyle.isEnabled()) {
			getInput().setFont(GMFGraphFactory.eINSTANCE.createBasicFont());
			((BasicFont) getInput().getFont()).setFaceName(/*Bridge.fieldGet(myFontFaceName)*/myFontFaceName.getText());
			((BasicFont) getInput().getFont()).setHeight(myFontHeight.getSelection());
			((BasicFont) getInput().getFont()).setStyle(FontStyle.get(myFontStyle.getSelectionIndex()));
		} else {
			getInput().setFont(null);
		}

	}

	@Override
	public void refresh() {
		myIsRefresh = true;

		myLineWidth.setSelection(((Shape) getInput()).getLineWidth());
		myFill.setSelection(((Shape) getInput()).isFill());
		myOutline.setSelection(((Shape) getInput()).isOutline());
		myFillXor.setSelection(((Shape) getInput()).isXorFill());
		myOutlineXor.setSelection(((Shape) getInput()).isXorOutline());

		if (((Shape) getInput()).getLineKind() == LineKind.LINE_SOLID_LITERAL) {
			myR1.setSelection(true);
		} else {
			myR1.setSelection(false);
		}
		if (((Shape) getInput()).getLineKind() == LineKind.LINE_DASH_LITERAL) {
			myR2.setSelection(true);
		} else {
			myR2.setSelection(false);
		}
		if (((Shape) getInput()).getLineKind() == LineKind.LINE_DOT_LITERAL) {
			myR3.setSelection(true);
		} else {
			myR3.setSelection(false);
		}
		if (((Shape) getInput()).getLineKind() == LineKind.LINE_DASHDOT_LITERAL) {
			myR4.setSelection(true);
		} else {
			myR4.setSelection(false);
		}
		if (((Shape) getInput()).getLineKind() == LineKind.LINE_DASHDOTDOT_LITERAL) {
			myR5.setSelection(true);
		} else {
			myR5.setSelection(false);
		}
		if (((Shape) getInput()).getLineKind() == LineKind.LINE_CUSTOM_LITERAL) {
			myR6.setSelection(true);
		} else {
			myR6.setSelection(false);
		}
		if (getInput() instanceof RoundedRectangle) {
			myCornerWidth.setSelection(((RoundedRectangle) getInput()).getCornerWidth());
			myCornerHeight.setSelection(((RoundedRectangle) getInput()).getCornerHeight());
			myRoundedRectStyle.setVisible(true);
		} else {
			myRoundedRectStyle.setVisible(false);
		}
		if (getInput().getForegroundColor() instanceof RGBColor) {
			if (getInput().getForegroundColor() != null) {
				myForegroundRed.setSelection(((RGBColor) getInput().getForegroundColor()).getRed());
				myForegroundGreen.setSelection(((RGBColor) getInput().getForegroundColor()).getGreen());
				myForegroundBlue.setSelection(((RGBColor) getInput().getForegroundColor()).getBlue());
			}
			myForegroundRgbRadio.setSelection(true);
			myForegroundRGBValues.setVisible(true);
		} else {
			myForegroundRgbRadio.setSelection(false);
			myForegroundRGBValues.setVisible(false);
		}
		if (getInput().getForegroundColor() instanceof ConstantColor) {
			if (getInput().getForegroundColor() != null) {
				myForegroundPredefinedColor.select(((ConstantColor) getInput().getForegroundColor()).getValue().getValue());
			}
			myForegroundPredeinedRadio.setSelection(true);
			myForegroundPredefinedValue.setVisible(true);
		} else {
			myForegroundPredeinedRadio.setSelection(false);
			myForegroundPredefinedValue.setVisible(false);
		}
		if (getInput().getForegroundColor() == null) {
			myForegroundNoValueRadio.setSelection(true);
		} else {
			myForegroundNoValueRadio.setSelection(false);
		}
		if (getInput().getBackgroundColor() instanceof RGBColor) {
			if (getInput().getBackgroundColor() != null) {
				myBackgroundRed.setSelection(((RGBColor) getInput().getBackgroundColor()).getRed());
				myBackgroundGreen.setSelection(((RGBColor) getInput().getBackgroundColor()).getGreen());
				myBackgroundBlue.setSelection(((RGBColor) getInput().getBackgroundColor()).getBlue());
			}
			myBackgroundRgbRadio.setSelection(true);
			myBackgroundRGBValues.setVisible(true);
		} else {
			myBackgroundRgbRadio.setSelection(false);
			myBackgroundRGBValues.setVisible(false);
		}
		if (getInput().getBackgroundColor() instanceof ConstantColor) {
			if (getInput().getBackgroundColor() != null) {
				myBackgroundPredefinedColor.select(((ConstantColor) getInput().getBackgroundColor()).getValue().getValue());
			}
			myBackgroundPredeinedRadio.setSelection(true);
			myBackgroundPredefinedValue.setVisible(true);
		} else {
			myBackgroundPredeinedRadio.setSelection(false);
			myBackgroundPredefinedValue.setVisible(false);
		}
		if (getInput().getBackgroundColor() == null) {
			myBackgroundNoRadio.setSelection(true);
		} else {
			myBackgroundNoRadio.setSelection(false);
		}
		if (getInput().getFont() instanceof BasicFont) {
			if (getInput().getFont() != null) {
				myFontFaceName.setText(((BasicFont) getInput().getFont()).getFaceName());/*Bridge.fieldSet(myFontFaceName, ((BasicFont) getInput().getFont()).getFaceName());*/
				myFontHeight.setSelection(((BasicFont) getInput().getFont()).getHeight());
				myFontStyle.select(((BasicFont) getInput().getFont()).getStyle().getValue());
			}
			myFontSetFont.setSelection(true);
			myFontFaceName.setEnabled(true);
			myFontHeight.setEnabled(true);
			myFontStyle.setEnabled(true);
		} else {
			myFontSetFont.setSelection(false);
			myFontFaceName.setEnabled(false);
			myFontHeight.setEnabled(false);
			myFontStyle.setEnabled(false);
		}

		myIsRefresh = false;
	}

	@Override
	public void aboutToBeHidden() {
		if (myInput != null) {
			detach();
		}
	}

	public void handleEvent(Event event) {
		if (myIsRefresh) {
			return;
		}

		if (event.type == SWT.Modify) {
			// XXX also override isDirty to compare values if they
			// match model's and to clear dirty state in case like aaa^H^H^H 
			markDirty();
		} else if (event.type == SWT.FocusOut) {
			applyChanges();
		} else if (event.type == SWT.KeyDown) {
			if (event.keyCode == SWT.ESC) {
				discardChanges();
			} else if (event.keyCode == SWT.CR) {
				applyChanges();
			}
		}
		if (event.type == SWT.Selection) {
			if (myFill == event.widget) {
				applyChanges(); // Commit; View to Model
				// Optimization? Instead of full refresh, just dependant widgets should get updated
			} else if (myOutline == event.widget) {
				applyChanges(); // Commit; View to Model
				// Optimization? Instead of full refresh, just dependant widgets should get updated
			} else if (myFillXor == event.widget) {
				applyChanges(); // Commit; View to Model
				// Optimization? Instead of full refresh, just dependant widgets should get updated
			} else if (myOutlineXor == event.widget) {
				applyChanges(); // Commit; View to Model
				// Optimization? Instead of full refresh, just dependant widgets should get updated
			}
			if (myR1 == event.widget) {
				if (myR1.getSelection()) {
					applyChanges(); // Commit; View to Model
				}
			} else if (myR2 == event.widget) {
				if (myR2.getSelection()) {
					applyChanges(); // Commit; View to Model
				}
			} else if (myR3 == event.widget) {
				if (myR3.getSelection()) {
					applyChanges(); // Commit; View to Model
				}
			} else if (myR4 == event.widget) {
				if (myR4.getSelection()) {
					applyChanges(); // Commit; View to Model
				}
			} else if (myR5 == event.widget) {
				if (myR5.getSelection()) {
					applyChanges(); // Commit; View to Model
				}
			} else if (myR6 == event.widget) {
				if (myR6.getSelection()) {
					applyChanges(); // Commit; View to Model
				}
			} else if (myForegroundRgbRadio == event.widget) {
				if (myForegroundRgbRadio.getSelection()) {
					myForegroundRGBValues.setVisible(true);
					myForegroundPredefinedValue.setVisible(false);
					applyChanges(); // Commit; View to Model
					if (getInput().getForegroundColor() != null) {
						myForegroundRed.setSelection(((RGBColor) getInput().getForegroundColor()).getRed());
						myForegroundGreen.setSelection(((RGBColor) getInput().getForegroundColor()).getGreen());
						myForegroundBlue.setSelection(((RGBColor) getInput().getForegroundColor()).getBlue());
					}
				} else {
					myForegroundRGBValues.setVisible(false);
				}
			} else if (myForegroundPredeinedRadio == event.widget) {
				if (myForegroundPredeinedRadio.getSelection()) {
					myForegroundPredefinedValue.setVisible(true);
					myForegroundRGBValues.setVisible(false);
					applyChanges(); // Commit; View to Model
					if (getInput().getForegroundColor() != null) {
						myForegroundPredefinedColor.select(((ConstantColor) getInput().getForegroundColor()).getValue().getValue());
					}
				} else {
					myForegroundPredefinedValue.setVisible(false);
				}
			} else if (myForegroundNoValueRadio == event.widget) {
				if (myForegroundNoValueRadio.getSelection()) {
					myForegroundRGBValues.setVisible(false);
					myForegroundPredefinedValue.setVisible(false);
					applyChanges(); // Commit; View to Model
				}
			} else if (myBackgroundRgbRadio == event.widget) {
				if (myBackgroundRgbRadio.getSelection()) {
					myBackgroundRGBValues.setVisible(true);
					myBackgroundPredefinedValue.setVisible(false);
					applyChanges(); // Commit; View to Model
					if (getInput().getBackgroundColor() != null) {
						myBackgroundRed.setSelection(((RGBColor) getInput().getBackgroundColor()).getRed());
						myBackgroundGreen.setSelection(((RGBColor) getInput().getBackgroundColor()).getGreen());
						myBackgroundBlue.setSelection(((RGBColor) getInput().getBackgroundColor()).getBlue());
					}
				} else {
					myBackgroundRGBValues.setVisible(false);
				}
			} else if (myBackgroundPredeinedRadio == event.widget) {
				if (myBackgroundPredeinedRadio.getSelection()) {
					myBackgroundPredefinedValue.setVisible(true);
					myBackgroundRGBValues.setVisible(false);
					applyChanges(); // Commit; View to Model
					if (getInput().getBackgroundColor() != null) {
						myBackgroundPredefinedColor.select(((ConstantColor) getInput().getBackgroundColor()).getValue().getValue());
					}
				} else {
					myBackgroundPredefinedValue.setVisible(false);
				}
			} else if (myBackgroundNoRadio == event.widget) {
				if (myBackgroundNoRadio.getSelection()) {
					myBackgroundRGBValues.setVisible(false);
					myBackgroundPredefinedValue.setVisible(false);
					applyChanges(); // Commit; View to Model
				}
			} else if (myFontSetFont == event.widget) {
				if (myFontSetFont.getSelection()) {
					myFontFaceName.setEnabled(true);
					myFontHeight.setEnabled(true);
					myFontStyle.setEnabled(true);
					myFontFaceName.setEnabled(true);
					myFontHeight.setEnabled(true);
					myFontStyle.setEnabled(true);
					applyChanges(); // Commit; View to Model
					if (getInput().getFont() != null) {
						myFontFaceName.setText(((BasicFont) getInput().getFont()).getFaceName());/*Bridge.fieldSet(myFontFaceName, ((BasicFont) getInput().getFont()).getFaceName());*/
						myFontHeight.setSelection(((BasicFont) getInput().getFont()).getHeight());
						myFontStyle.select(((BasicFont) getInput().getFont()).getStyle().getValue());
					}
				} else {
					myFontFaceName.setEnabled(false);
					myFontHeight.setEnabled(false);
					myFontStyle.setEnabled(false);
					applyChanges(); // Commit; View to Model
				}
			}
			if (myFontStyle == event.widget || myForegroundPredefinedColor == event.widget || myBackgroundPredefinedColor == event.widget) {
				applyChanges();
			}

		}
	}

	public void modelChanged(Notification msg) {
		if (!myIsCommit && Display.getCurrent() != null) {
			refresh();
		}
	}

	private void markDirty() {
		// NO-OP, need that to share Update::handleEvent template with IFormPart which has same method
		// Perhaps, clients may find this method useful for some purpose?
	}

	protected void applyChanges() {
		try {
			myIsCommit = true;
			commit();
		} finally {
			myIsCommit = false;
		}
	}

	protected void discardChanges() {
		refresh();
	}

	protected Object unwrap(Object element) {
		// TODO may need to adapt selected element to smth else,
		// do it here
		return element;
	}

	private void attach() {
		myModelListeners = new org.eclipse.emf.common.notify.Adapter[] {
				new FeatureTracker(this, GMFGraphPackage.eINSTANCE.getShape_LineKind(), GMFGraphPackage.eINSTANCE.getShape_LineWidth(), GMFGraphPackage.eINSTANCE.getShape_Fill(),
						GMFGraphPackage.eINSTANCE.getShape_Outline(), GMFGraphPackage.eINSTANCE.getShape_XorFill(), GMFGraphPackage.eINSTANCE.getShape_XorOutline(), GMFGraphPackage.eINSTANCE
								.getRoundedRectangle_CornerWidth(), GMFGraphPackage.eINSTANCE.getRoundedRectangle_CornerHeight()),
				new AttachAdapter(GMFGraphPackage.eINSTANCE.getFigure_ForegroundColor(), new ChangeTracker() {

					public void modelChanged(org.eclipse.emf.common.notify.Notification n) {
						// FIXME enable/disable widget(s) -- HOWEVER, need access to Binding/Widget here, so can't share the template with e.g. Alex's ItemProviders
					}
				}, new FeatureTracker(this, GMFGraphPackage.eINSTANCE.getRGBColor_Red(), GMFGraphPackage.eINSTANCE.getRGBColor_Green(), GMFGraphPackage.eINSTANCE.getRGBColor_Blue(),
						GMFGraphPackage.eINSTANCE.getConstantColor_Value())),
				new AttachAdapter(GMFGraphPackage.eINSTANCE.getFigure_BackgroundColor(), new ChangeTracker() {

					public void modelChanged(org.eclipse.emf.common.notify.Notification n) {
						// FIXME enable/disable widget(s) -- HOWEVER, need access to Binding/Widget here, so can't share the template with e.g. Alex's ItemProviders
					}
				}, new FeatureTracker(this, GMFGraphPackage.eINSTANCE.getRGBColor_Red(), GMFGraphPackage.eINSTANCE.getRGBColor_Green(), GMFGraphPackage.eINSTANCE.getRGBColor_Blue(),
						GMFGraphPackage.eINSTANCE.getConstantColor_Value())), new AttachAdapter(GMFGraphPackage.eINSTANCE.getFigure_Font(), new ChangeTracker() {

					public void modelChanged(org.eclipse.emf.common.notify.Notification n) {
						// FIXME enable/disable widget(s) -- HOWEVER, need access to Binding/Widget here, so can't share the template with e.g. Alex's ItemProviders
					}
				}, new FeatureTracker(this, GMFGraphPackage.eINSTANCE.getBasicFont_FaceName(), GMFGraphPackage.eINSTANCE.getBasicFont_Height(), GMFGraphPackage.eINSTANCE.getBasicFont_Style())) };
		getInput().eAdapters().addAll(java.util.Arrays.asList(myModelListeners));

	}

	private void detach() {
		if (myModelListeners != null) {
			getInput().eAdapters().removeAll(java.util.Arrays.asList(myModelListeners));
			myModelListeners = null;
		}

	}

	protected Figure getInput() {
		// TODO implement;
		return (Figure) myInput;
	}

	private org.eclipse.swt.widgets.Label createLabel(org.eclipse.swt.widgets.Composite parent, String label) {
		org.eclipse.swt.widgets.Label l = new org.eclipse.swt.widgets.Label(parent, SWT.NONE);
		if (label != null)
			l.setText(label);
		getWidgetFactory().adapt(l, false, false);
		return l;
	}

	private org.eclipse.swt.widgets.Group createGroup(org.eclipse.swt.widgets.Composite parent, String label) {
		org.eclipse.swt.widgets.Group g = new org.eclipse.swt.widgets.Group(parent, SWT.SHADOW_NONE);
		if (label != null)
			g.setText(label);
		getWidgetFactory().adapt(g, false, false);
		getWidgetFactory().paintBordersFor(g);
		return g;
	}

}
