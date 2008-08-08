/**
 * <copyright>
 * </copyright>
 *
 * $Id: SVGFigure.java,v 1.4 2008/08/08 13:10:05 dstadnik Exp $
 */
package org.eclipse.gmf.gmfgraph;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SVG Figure</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmf.gmfgraph.SVGFigure#getDocumentURI <em>Document URI</em>}</li>
 *   <li>{@link org.eclipse.gmf.gmfgraph.SVGFigure#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.eclipse.gmf.gmfgraph.SVGFigure#getAreaOfInterest <em>Area Of Interest</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmf.gmfgraph.GMFGraphPackage#getSVGFigure()
 * @model
 * @generated
 */
public interface SVGFigure extends RealFigure {
	/**
	 * Returns the value of the '<em><b>Document URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Document URI</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Document URI</em>' attribute.
	 * @see #setDocumentURI(String)
	 * @see org.eclipse.gmf.gmfgraph.GMFGraphPackage#getSVGFigure_DocumentURI()
	 * @model
	 * @generated
	 */
	String getDocumentURI();

	/**
	 * Sets the value of the '{@link org.eclipse.gmf.gmfgraph.SVGFigure#getDocumentURI <em>Document URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Document URI</em>' attribute.
	 * @see #getDocumentURI()
	 * @generated
	 */
	void setDocumentURI(String value);

	/**
	 * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gmf.gmfgraph.SVGProperty}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Properties</em>' containment reference list.
	 * @see org.eclipse.gmf.gmfgraph.GMFGraphPackage#getSVGFigure_Properties()
	 * @model containment="true"
	 * @generated
	 */
	EList<SVGProperty> getProperties();

	/**
	 * Returns the value of the '<em><b>Area Of Interest</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Area Of Interest</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Area Of Interest</em>' containment reference.
	 * @see #setAreaOfInterest(Rectangle2D)
	 * @see org.eclipse.gmf.gmfgraph.GMFGraphPackage#getSVGFigure_AreaOfInterest()
	 * @model containment="true"
	 * @generated
	 */
	Rectangle2D getAreaOfInterest();

	/**
	 * Sets the value of the '{@link org.eclipse.gmf.gmfgraph.SVGFigure#getAreaOfInterest <em>Area Of Interest</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Area Of Interest</em>' containment reference.
	 * @see #getAreaOfInterest()
	 * @generated
	 */
	void setAreaOfInterest(Rectangle2D value);

} // SVGFigure
