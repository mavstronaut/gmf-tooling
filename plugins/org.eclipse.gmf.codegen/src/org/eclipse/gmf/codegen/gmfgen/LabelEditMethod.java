/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.codegen.gmfgen;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Label Edit Method</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.gmf.codegen.gmfgen.GMFGenPackage#getLabelEditMethod()
 * @model
 * @generated
 */
public enum LabelEditMethod implements Enumerator {
	/**
	 * The '<em><b>MESSAGE FORMAT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MESSAGE_FORMAT_VALUE
	 * @generated
	 * @ordered
	 */
	MESSAGE_FORMAT(0, "MESSAGE_FORMAT", "MESSAGE_FORMAT"),

	/**
	 * The '<em><b>REGEXP</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REGEXP_VALUE
	 * @generated
	 * @ordered
	 */
	REGEXP(1, "REGEXP", "REGEXP"),

	/**
	 * The '<em><b>NATIVE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NATIVE_VALUE
	 * @generated
	 * @ordered
	 */
	NATIVE(2, "NATIVE", "NATIVE");

	/**
	 * The '<em><b>MESSAGE FORMAT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MESSAGE FORMAT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MESSAGE_FORMAT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int MESSAGE_FORMAT_VALUE = 0;

	/**
	 * The '<em><b>REGEXP</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>REGEXP</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REGEXP
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int REGEXP_VALUE = 1;

	/**
	 * The '<em><b>NATIVE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NATIVE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NATIVE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NATIVE_VALUE = 2;

	/**
	 * An array of all the '<em><b>Label Edit Method</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final LabelEditMethod[] VALUES_ARRAY =
		new LabelEditMethod[] {
			MESSAGE_FORMAT,
			REGEXP,
			NATIVE,
		};

	/**
	 * A public read-only list of all the '<em><b>Label Edit Method</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<LabelEditMethod> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Label Edit Method</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LabelEditMethod get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LabelEditMethod result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Label Edit Method</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LabelEditMethod getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LabelEditMethod result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Label Edit Method</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LabelEditMethod get(int value) {
		switch (value) {
			case MESSAGE_FORMAT_VALUE: return MESSAGE_FORMAT;
			case REGEXP_VALUE: return REGEXP;
			case NATIVE_VALUE: return NATIVE;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private LabelEditMethod(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //LabelEditMethod
