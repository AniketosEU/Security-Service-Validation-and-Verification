/**
 * Copyright 2012  David Llewellyn-Jones <D.Llewellyn-Jones@ljmu.ac.uk>
 * Liverpool John Moores University <http://www.ljmu.ac.uk/cmp/>
 * Aniketos Project <http://www.aniketos.eu>
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

/**
 * Encapsulates the results from performing the verification on a service.
 * @author LJMU/David Llewellyn-Jones
 *
 */
public interface IVerificationResult {

	/**
	 * Returns the result from the verification process. A positive result suggests that ALL tests were successfully passed.
	 * A zero result suggests that at least one of the tests failed.
	 * A result of -1 suggests there was an error.
	 * The magnitude of the result may signify information depending on the properties being tested against. In general, the
	 * larger the positive number, the stronger the security.
	 * @return The result of the verification process.
	 */
	public int getResult();

	/**
	 * Used to set the result of the verification. A positive result suggests that ALL tests were successfully passed.
	 * A zero or negative result suggests that at least one of the tests failed or there was an error.
	 * The magnitude of the result may signify information depending on the properties being tested against. In general, the
	 * larger the positive number, the stronger the security.
	 * Calling this method will also set the error value to 0.
	 * @param value the result of the verification process to set.
	 */
	public void setResult(int value);
	
	/**
	 * In case of error, this will return a human-readable textual description of the error.
	 * This can be used for feedback to the user, but you shouldn't assume it can be parsed
	 * in any general machine-interpretable way.
	 * @return a human-readable description of the error.
	 */
	public String getErrorExplanation();

	/**
	 * Set details of any errors generated. This will also set the result to be -1.
	 * @param code the error code to set.
	 * @param explanation a human-readable textual description of the error.
	 */
	public void setError(int code, String explanation);

	/**
	 * Sets both the error value and result to be 0. Clears the error explanation.
	 */
	public void clearError();
	
	/**
	 * Returns a value representing the type of error that occurred.
	 * 
	 * Possible error values:
	 *   0 : No error
	 *   1 : Could not access SPDM
	 *   2 : Could not access CSVM
	 *   3 : Could not access PVM
	 *   4 : Could not access Marketplace
	 *  21 : Invalid service
	 *  22 : Invalid policy
	 * 
	 * @return a code representing the error that occurred.
	 */
	public int getErrorValue();
}
