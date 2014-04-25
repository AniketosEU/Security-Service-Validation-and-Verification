/*
Copyright (c) 2014, Luca Wiegand (CNR, Italy), Project: FP7-ICT-257930 Aniketos
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

 -  Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 -  Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 -  Neither the name of WATERFORD INSTITUTE OF TECHNOLOGY nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package eu.aniketos.data.impl;

import eu.aniketos.data.IContractStatus;

/**
 * This is a wrapper class, that wraps the result of each process of contract analysis.
 * It is returned by the Contract Manager Service as the output of the AnalyseSecureComposition method.
 * @author Luca Wiegand
 *
 */
public class ContractStatus implements IContractStatus{
	
	/**
	 * The array of result in which each result is stored.
	 */
	private Result[] results;

	/**
	 * ContractStatusImpl constructor, creates it from an array of Result objects.
	 * @param res: the array of results that the class has to wrap.
	 */
	public ContractStatus(Result[] res){
		results = res;
	}	
	
	/**
	 * get the result of the trust check process from Trustworthinness manager.
	 * @return: the TM result.
	 */
	public Result getTrustResult(){
		return results[0];
	}
	/**
	 * get the result of the SPDM.
	 * @return: the SPDM result.
	 */
	public Result getSPDMStatus(){
		return results[1];
	}

	/**
	 * get the result of the matching process.
	 * @return: the matching result.
	 */
	public Result getMatchingResult(){
		return results[2];
	}
}
