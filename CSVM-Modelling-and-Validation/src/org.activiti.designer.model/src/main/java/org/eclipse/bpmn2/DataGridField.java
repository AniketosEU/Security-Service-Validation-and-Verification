/**
 * <copyright>
 * 
 * Copyright (c) 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Reiner Hille-Doering (SAP AG) - initial API and implementation and/or initial documentation
 * 
 * </copyright>
 */
package org.eclipse.bpmn2;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Grid Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.DataGridField#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DataGridField#getSimpleValue <em>Simple Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getDataGridField()
 * @model
 * @generated
 */
public interface DataGridField extends BaseElement {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.bpmn2.Bpmn2Package#getDataGridField_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.DataGridField#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Simple Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Simple Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Simple Value</em>' attribute.
	 * @see #setSimpleValue(String)
	 * @see org.eclipse.bpmn2.Bpmn2Package#getDataGridField_SimpleValue()
	 * @model required="true"
	 * @generated
	 */
	String getSimpleValue();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.DataGridField#getSimpleValue <em>Simple Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Simple Value</em>' attribute.
	 * @see #getSimpleValue()
	 * @generated
	 */
	void setSimpleValue(String value);

} // DataGridField
