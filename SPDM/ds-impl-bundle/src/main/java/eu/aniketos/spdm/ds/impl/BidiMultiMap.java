/*
Copyright (c) 2013, Aneel Rahim,Bernard Butler and Arif Fareed (Waterford Institute of Technology, Ireland), Project: FP7-ICT-257930 Aniketos
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

 -  Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 -  Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 -  Neither the name of WATERFORD INSTITUTE OF TECHNOLOGY nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
/*
 * Copyright 2011 Patrik Dufresne. 
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package eu.aniketos.spdm.ds.impl;

import java.util.Map;
import java.util.Set;

/**
 * Defines a map that holds a many-to-many relation between a key and it's
 * value.
 * <p>
 * A MultiMap is a Map with slightly different semantics. Putting a key-value
 * into the map will add a new entry to the map.
 * <p>
 * For example:
 * <p>
 * 
 * <pre>
 * BidiMultiMap&lt;String, String&gt; map = new BidiMultiHashMap&lt;String, String&gt;();
 * map.put(&quot;a&quot;, &quot;1&quot;);
 * map.put(&quot;a&quot;, &quot;2&quot;);
 * map.put(&quot;a&quot;, &quot;3&quot;);
 * map.put(&quot;b&quot;, &quot;1&quot;);
 * map.put(&quot;b&quot;, &quot;2&quot;);
 * int size = map.size();
 * Set&lt;K&gt; keys = mhm.keySet();
 * Set&lt;V&gt; values = mhm.valueSet();
 * </pre>
 * 
 * <ul>
 * <li><code>size</code> will be 5.</li>
 * <li><code>keys</code> will be a set containing "a", "b".</li>
 * <li><code>values</code> will be a set containing "1", "2", "3".</li>
 * </ul>
 * NOTE: Additional methods were added to this interface in Commons Collections
 * 3.1. These were added solely for documentation purposes and do not change the
 * interface as they were defined in the superinterface Map anyway.
 * 
 * @author Patrik Dufresne
 * 
 * @param <K>
 * @param <V>
 */
public interface BidiMultiMap<K, V> extends Map<K, V> {

	/**
	 * Checks whether the map contains the specified entry.
	 * 
	 * @param key
	 *            the key to search for
	 * @param value
	 *            the value to search for
	 * @return true if the map contains the entry
	 */
	public abstract boolean containsEntry(K key, V value);

	/**
	 * Gets the keySet view of the map. Changes made to the view affect this
	 * map. To simply iterate through the keys, use {@link #mapIterator()}.
	 * 
	 * @param value
	 *            the value
	 * @return the keySet view
	 */
	public abstract  Set<K> keySet(V value);

	/**
	 * Removes the specified mapping from this map.
	 * 
	 * @param key
	 *            the mapping to remove
	 * @param value
	 *            the mapping to remove
	 * @return False if key not in map
	 */
	public abstract  boolean removeEntry(Object key, Object value);

	/**
	 * Remove the specified key from this map.
	 * 
	 * @param key
	 *            the key to remove
	 * @return the values mapped to the removed key, null if key not in map
	 */
	public abstract  Set<V> removeKey(Object key);

	/**
	 * Removes the specified value from this map.
	 * 
	 * @param value
	 *            the value to remove
	 * @return the keys mapped to the removed value, null if value not in map
	 */
	public abstract  Set<K> removeValue(Object value);

	/**
	 * Gets the values view of the map. Changes made to the view affect this
	 * map.
	 * 
	 * @return the values view
	 */
	public abstract  Set<V> valueSet();

	/**
	 * Gets the values view of the map for the key specified. Changes made to
	 * the view affect this map.
	 * 
	 * @param key
	 * @return
	 */	
	public abstract Set<V> valueSet(K key);
	
//	public abstract  void put(K key, V value);
	
	

}