/*
Copyright (c) 2014, Luca Wiegand (CNR, Italy), Project: FP7-ICT-257930 Aniketos
Copyright (c) 2014, Bo Zhou (Liverpool John Moores University, UK), Project: FP7-ICT-257930 Aniketos
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

 -  Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 -  Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 -  Neither the name of WATERFORD INSTITUTE OF TECHNOLOGY nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package eu.aniketos.data.impl;

/**
 * This class represents a result of any status that the ContractManager module returns.
 * Each Result is composed by a return code(representing the outcome of the process)
 * and an explanation of the result.
 * @author Luca Wiegand
 * @version 12/07/2013 Bo Zhou
 */
public class Result {

	/**
	 * the code representing the process outcome.
	 */
	private int errorCode;
	/**
	 * a human readable explanation of the result.
	 */
	private String explanation;
	/**
	 * Create a Result object with given code and explanation.
	 * @param code: the code of the result.
	 * @param expl: the explanation of the result.
	 */
	public Result(int code, String expl){
		errorCode = code;
		explanation = expl;
	}
	
	public int getErrorCode (){
		return errorCode;
	}
	
	public String getExplanation (){
		return explanation;
	}
	
	
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
}
