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

public class VerificationResult implements IVerificationResult {

	public int result;
	public String errorExplanation;
	public int valueError;

	public VerificationResult() {
		result = 0;
		valueError = 1;
		errorExplanation = "Value not set";
	}

	@Override
	public int getResult() {
		return result;
	}

	@Override
	public void setResult(int value) {
		valueError = 0;
		errorExplanation = "";
		result = value;
	}

	@Override
	public String getErrorExplanation() {
		String explanation = "";
		if (valueError != 0) {
			explanation = errorExplanation;
		}
		return explanation;
	}

	@Override
	public void setError(int code, String explanation) {
		valueError = code;
		errorExplanation = explanation;
		result = -1;
	}

	@Override
	public void clearError() {
		valueError = 0;
		errorExplanation = "";
		result = 0;
	}

	@Override
	public int getErrorValue() {
		return valueError;
	}

}
