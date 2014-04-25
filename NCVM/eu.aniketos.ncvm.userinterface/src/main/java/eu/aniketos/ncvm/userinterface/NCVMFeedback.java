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

package eu.aniketos.ncvm.userinterface;

import javax.jws.WebParam;
import javax.jws.WebService;

import eu.aniketos.ncvm.userinterface.views.NCVM;


@WebService(
		endpointInterface = "eu.aniketos.ncvm.userinterface.NCVMFeedback",
		serviceName = "NCVMFeedback"
		)
public class NCVMFeedback implements INCVMFeedback {
	NCVM ncvmLog;

	public NCVMFeedback() {
		ncvmLog = null;
	}

	public NCVMFeedback(NCVM ncvmLog) {
		this.ncvmLog = ncvmLog;
	}

	public void setNCVM (NCVM ncvmLog) {
		this.ncvmLog = ncvmLog;
	}
	
	@Override
	public void logLine(@WebParam(name="text")String text) {
		System.out.println("NCVM Feedback: " + text);
		if (ncvmLog != null) {
			if (Thread.currentThread() != ncvmLog.display.getThread()) {
				final String logText = text;
				ncvmLog.display.asyncExec(new Runnable() {
					@Override
					public void run() {
						ncvmLog.logOutputLine("# " + logText);
					}
				});
			}
			else {
				ncvmLog.logOutputLine("# " + text);
			}
		}
	}
}
