/**
 * Copyright 2014 Liverpool John Moores University <http://www.ljmu.ac.uk/cmp/>
 * Aniketos Project FP7-ICT-257930 <http://www.aniketos.eu>
 * David Llewellyn-Jones <D.Llewellyn-Jones@ljmu.ac.uk>
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either 
 * version 3 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package eu.aniketos.ncvm.impl;

import eu.aniketos.ncvm.IVerificationResult;

/**
 * Encapsulates the results from performing the verification on a service.
 * @author LJMU/David Llewellyn-Jones
 *
 */
public class VerificationResult implements IVerificationResult {

	/**
	 * Stores the result of the verification process.
	 * -1 indicates an error has occurred.
	 * 0 indicates that one or more verification steps has failed.
	 * A positive value indicates that all verification steps have been successful. Large values imply greated security where this makes sense.
	 */
	public int result;

	/**
	 * A human-readable textual description of any error that has occurred.
	 * An empty string if no error has occurred.
	 */
	public String errorExplanation;

	/**
	 * Indicates any error conditions.
	 * 0 represents no error has occurred
	 * Any other value indicates an error.
	 * See the getErrorValue interface description for possilble error values.
	 * @see eu.aniketos.ncvm.IVerificationResult#getErrorValue()
	 */
	public int valueError;

	/**
	 * Initialise the object.
	 */
	public VerificationResult() {
		result = 0;
		valueError = 1;
		errorExplanation = "Value not set";
	}

	/* (non-Javadoc)
	 * @see eu.aniketos.ncvm.IVerificationResult#getResult()
	 */
	@Override
	public int getResult() {
		return result;
	}

	/* (non-Javadoc)
	 * @see eu.aniketos.ncvm.IVerificationResult#setResult(int)
	 */
	@Override
	public void setResult(int value) {
		valueError = 0;
		errorExplanation = "";
		result = value;
	}

	/* (non-Javadoc)
	 * @see eu.aniketos.ncvm.IVerificationResult#getErrorExplanation()
	 */
	@Override
	public String getErrorExplanation() {
		String explanation = "";
		if (valueError != 0) {
			explanation = errorExplanation;
		}
		return explanation;
	}

	/* (non-Javadoc)
	 * @see eu.aniketos.ncvm.IVerificationResult#setError(int, java.lang.String)
	 */
	@Override
	public void setError(int code, String explanation) {
		valueError = code;
		errorExplanation = explanation;
		result = -1;
	}

	/* (non-Javadoc)
	 * @see eu.aniketos.ncvm.IVerificationResult#clearError()
	 */
	@Override
	public void clearError() {
		valueError = 0;
		errorExplanation = "";
		result = 0;
	}

	/* (non-Javadoc)
	 * @see eu.aniketos.ncvm.IVerificationResult#getErrorValue()
	 */
	@Override
	public int getErrorValue() {
		return valueError;
	}
}
