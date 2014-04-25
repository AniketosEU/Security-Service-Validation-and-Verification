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

package eu.aniketos.ncvm;

// Errors
//  0 : No error
//  1 : Could not access SPDM
//  2 : Could not access CSVM
//  3 : Could not access PVM
//  4 : Could not access Marketplace
// 21 : Invalid service
// 22 : Invalid policy

public interface IVerificationResult {
	public int getResult();
	public void setResult(int value);
	public String getErrorExplanation();
	public void setError(int code, String explanation);
	public void clearError();
	public int getErrorValue();
}
