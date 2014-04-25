/**
 * Copyright 2012  Bo Zhou <B.Zhou@ljmu.ac.uk>
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

package eu.aniketos.scpm;

import eu.aniketos.data.ICompositionPlan;

public class SelectResult implements ISelectResult{
	private ICompositionPlan plan;
	private int result;
	private String reason;
	@Override
	public int getResult() {
		
		return result;
	}
	@Override
	public void setResult(int value) {
		
		result = value;
	}
	@Override
	public ICompositionPlan getPlan() {

		return plan;
	}
	@Override
	public void setPlan(ICompositionPlan inputplan) {

		plan = inputplan;
	}
	@Override
	public String getExplanation() {
		return reason;
	}
	@Override
	public void setExplanation(String explain) {
		reason = explain;
		
	}
	
}
