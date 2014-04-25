package eu.aniketos.scpm.data;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Criteria that define how a set of ICompositionPlan can be ordered into a list.
 * 
 * @author Bo Zhou, LJMU
 */
public class OrderCriteria
{
	/**
	 * A Map that contains all criteria. The criterion threshold is mapped together with its ID.
	 * The first parameter is the ID. The second parameter is the threshold.
	 */
	private Map<String, String> criteria;
	//There will be a certificate object here in the future.
	
	/** 
	 * Constructor that initiates the criteria ID-threshold Map.
	 *  
	 */	
	public OrderCriteria(){		
		criteria = new HashMap<String, String>();
	}

	/** Get the criteria map.
	 *
	 * @return All criteria. 
	 * It's in a map from where threshold can be retrieved given the criterion ID.
	 * 
	 */
	public Map<String, String> getCriteria() {
		return criteria;
	}

	/** Get the threshold value of a criterion given its ID.
	 *
	 * @param criterionID The ID of a criterion.
	 * @return The threshold value of the criterion that specified with ID.
	 * 
	 */
	public String getCriterion(String criterionID) {
		return criteria.get(criterionID);
	}
	
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
	
	/** Set the criteria map.
	 *
	 * @param criteria A map that contains the criteria ID-threshold pairs.
	 * 
	 */
	public void setCriteria(Map<String, String> criteria) {
		this.criteria = criteria;
	}

	/** Add a new criterion to the criteria map.
	 *
	 * @param criterionID The criterion ID that to be added into the map. 
	 * @param criterionValue The threshold value for the specified criterion. 
	 *  
	 */
	public void addCriterion(String criterionID, String criterionValue) {
		criteria.put(criterionID, criterionValue);
	}

	/** Remove a criterion from the criteria map.
	 *
	 * @param criterionID The criterion ID that to be removed from the map. 
	 *   
	 */
	public void removeCriterion(String criterionID) {
		criteria.remove(criterionID);
	}


}
