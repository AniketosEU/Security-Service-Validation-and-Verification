/*
Copyright (c) 2013, Aneel Rahim, Bernard Butler and Arif Fareed (Waterford Institute of Technology, Ireland), Project: FP7-ICT-257930 Aniketos
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

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.commons.collections.iterators.EmptyIterator;


import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferencePolicy;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.Property;
import org.osgi.service.component.ComponentContext;


/**
 * This data structure allow to store pair of data.
 * 
 * @author Patrik Dufresne
 * 
 * @param <K>
 * @param <V>
 */
public class BidiMultiHashMap<K, V> extends AbstractMap<K, V> implements
		BidiMultiMap<K, V> {
	
	/**
	 * EntrySet implementation.
	 */
	public class EntrySet extends AbstractSet<Entry<K, V>> {

		@Override
		public void clear() {
			BidiMultiHashMap.this.clear();
		}

		@Override
		public boolean contains(Object entry) {
			if (entry instanceof Map.Entry) {
				@SuppressWarnings("unchecked")
				Map.Entry<K, V> e = (Entry<K, V>) entry;
				return getEntry(e.getKey(), e.getValue()) != null;
			}
			return false;
		}

		@Override
		public Iterator<Entry<K, V>> iterator() {
			return createEntrySetIterator();
		}

		@Override
		public boolean remove(Object obj) {
			if (obj instanceof Map.Entry == false) {
				return false;
			}
			@SuppressWarnings("unchecked")
			Map.Entry<K, V> entry = (Entry<K, V>) obj;
			return removeEntry(entry.getKey(), entry.getValue());
		}

		@Override
		public int size() {
			return BidiMultiHashMap.this.size();
		}
	}

	/**
	 * EntrySet iterator.
	 */
	protected class EntrySetIterator extends HashIterator<Entry<K, V>> {

		@Override
		protected HashEntry<K, V> getNext(HashEntry<K, V> e) {
			return e.entryNext;
		}

		@Override
		protected HashEntry<K, V>[] getTable() {
			return BidiMultiHashMap.this.entries;
		}

		@Override
		public Entry<K, V> next() {
			return super.nextEntry();
		}

		@Override
		public void remove() {
			if (this.last == null) {
				throw new IllegalStateException(REMOVE_INVALID);
			}
			if (BidiMultiHashMap.this.modCount != this.expectedModCount) {
				throw new ConcurrentModificationException();
			}
			removeMapping(this.last);
			this.last = null;
			this.expectedModCount = BidiMultiHashMap.this.modCount;
		}
	}

	// -----------------------------------------------------------------------
	/**
	 * HashEntry used to store the data.
	 */
	protected static class HashEntry<K, V> implements Map.Entry<K, V> {

		/** The hash code of the entry */
		int entryHashCode;
		/** The next entry in the entries table */
		HashEntry<K, V> entryNext;
		/** The previous entry in the entries table */
		HashEntry<K, V> entryPrevious;
		/** The key */
		K key;
		/** The hash code of the key */
		int keyHashCode;
		/** The next entry in the hash chain */
		HashEntry<K, V> keyNext;
		/** The previous entry in the key table */
		HashEntry<K, V> keyPrevious;
		/** The next entry with the same key */
		HashEntry<K, V> keySame;
		/** The value */
		V value;
		/** The hash code of the value */
		int valueHashCode;
		/** The next entry in the value table */
		HashEntry<K, V> valueNext;
		/** The previous entry in the value table */
		HashEntry<K, V> valuePrevious;
		/** The next entry with the same value */
		HashEntry<K, V> valueSame;

		/**
		 * Create a new entry
		 * 
		 * @param keyPrevious
		 * @param keyNext
		 * @param keySame
		 * @param valuePrevious
		 * @param valueNext
		 * @param valueSame
		 * @param entryPrevious
		 * @param entryNext
		 * @param keyHashCode
		 * @param valueHashCode
		 * @param entryHashCode
		 * @param key
		 * @param value
		 */
		protected HashEntry(HashEntry<K, V> keyPrevious,
				HashEntry<K, V> keyNext, HashEntry<K, V> keySame,
				HashEntry<K, V> valuePrevious, HashEntry<K, V> valueNext,
				HashEntry<K, V> valueSame, HashEntry<K, V> entryPrevious,
				HashEntry<K, V> entryNext, int keyHashCode, int valueHashCode,
				int entryHashCode, K key, V value) {
			this.keyPrevious = keyPrevious;
			this.keyNext = keyNext;
			this.keySame = keySame;
			this.valuePrevious = valuePrevious;
			this.valueNext = valueNext;
			this.valueSame = valueSame;
			this.entryPrevious = entryPrevious;
			this.entryNext = entryNext;
			this.keyHashCode = keyHashCode;
			this.valueHashCode = valueHashCode;
			this.entryHashCode = entryHashCode;
			this.key = key;
			this.value = value;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (obj instanceof Map.Entry == false) {
				return false;
			}
			@SuppressWarnings("unchecked")
			Map.Entry<K, V> other = (Entry<K, V>) obj;
			return (getKey() == null ? other.getKey() == null : getKey()
					.equals(other.getKey()))
					&& (getValue() == null ? other.getValue() == null
							: getValue().equals(other.getValue()));
		}

		@Override
		public K getKey() {
			return (this.key == NULL ? null : this.key);
		}

		@Override
		public V getValue() {
			return this.value;
		}

		@Override
		public int hashCode() {
			return this.entryHashCode;
		}

		@Override
		public V setValue(V value) {
			throw new UnsupportedOperationException();
		}

		@Override
		public String toString() {
			return new StringBuffer().append(getKey()).append('=')
					.append(getValue()).toString();
		}
	}

	/**
	 * Base iterator to iterate on any table: keys, values, entries.
	 */
	protected abstract class HashIterator<G> implements Iterator<G> {

		/** The modification count expected */
		protected int expectedModCount;
		/** The current index into the array of buckets */
		protected int hashIndex;
		/** The last returned entry */
		protected HashEntry<K, V> last;
		/** The next entry */
		protected HashEntry<K, V> next;

		/**
		 * Create a new iterator.
		 */
		protected HashIterator() {
			super();
			HashEntry<K, V>[] data = getTable();
			int i = data.length;
			HashEntry<K, V> next = null;
			while (i > 0 && next == null) {
				next = data[--i];
			}
			this.next = next;
			this.hashIndex = i;
			this.expectedModCount = BidiMultiHashMap.this.modCount;
		}

		/**
		 * Return the next entry.
		 * 
		 * @param e
		 *            the entry
		 * @return the next entry or null
		 */
		protected abstract HashEntry<K, V> getNext(HashEntry<K, V> e);

		/**
		 * Return the tables to iterate on.
		 * 
		 * @return the table or null
		 */
		protected abstract HashEntry<K, V>[] getTable();

		/**
		 * Check if this iterator has a next entry.
		 */
		@Override
		public boolean hasNext() {
			return (this.next != null);
		}

		/**
		 * Subclasses should call this function get the next entry.
		 * 
		 * @return
		 */
		protected HashEntry<K, V> nextEntry() {
			if (BidiMultiHashMap.this.modCount != this.expectedModCount) {
				throw new ConcurrentModificationException();
			}
			HashEntry<K, V> newCurrent = this.next;
			if (newCurrent == null) {
				throw new NoSuchElementException(NO_NEXT_ENTRY);
			}
			HashEntry<K, V>[] data = getTable();
			int i = this.hashIndex;
			HashEntry<K, V> n = getNext(newCurrent);
			while (n == null && i > 0) {
				n = data[--i];
			}
			this.next = n;
			this.hashIndex = i;
			this.last = newCurrent;
			return newCurrent;
		}

		@Override
		public String toString() {
			if (this.last != null) {
				return "Iterator[" + this.last.getKey() //$NON-NLS-1$
						+ "=" + this.last.getValue() //$NON-NLS-1$
						+ "]"; //$NON-NLS-1$
			}
			return "Iterator[]"; //$NON-NLS-1$
		}
	}

	/**
	 * Base iterator to iterate on any table: keys, values, entries.
	 */
	protected abstract class HashIteratorForSame<G> implements Iterator<G> {

		/** The modification count expected */
		protected int expectedModCount;
		/** The last returned entry */
		protected HashEntry<K, V> last;
		/** The next entry */
		protected HashEntry<K, V> next;

		protected HashIteratorForSame(Object same) {
			this.next = getFirstSame(same);
			this.expectedModCount = BidiMultiHashMap.this.modCount;
		}

		protected HashEntry<K, V> currentEntry() {
			return this.last;
		}

		/**
		 * Return the first matching entry.
		 * 
		 * @param same
		 *            the same object
		 * @return the first entry or null if none matching.
		 */
		protected abstract HashEntry<K, V> getFirstSame(Object same);

		/**
		 * Return the next same entry.
		 * 
		 * @param e
		 *            the entry
		 * @return the next entry or null
		 */
		protected abstract HashEntry<K, V> getNext(HashEntry<K, V> e);

		@Override
		public boolean hasNext() {
			return (this.next != null);
		}

		protected HashEntry<K, V> nextEntry() {
			if (BidiMultiHashMap.this.modCount != this.expectedModCount) {
				throw new ConcurrentModificationException();
			}
			HashEntry<K, V> newCurrent = this.next;
			if (newCurrent == null) {
				throw new NoSuchElementException(NO_NEXT_ENTRY);
			}
			HashEntry<K, V> n = getNext(newCurrent);
			this.next = n;
			this.last = newCurrent;
			return newCurrent;
		}

		@Override
		public void remove() {
			if (this.last == null) {
				throw new IllegalStateException(REMOVE_INVALID);
			}
			if (BidiMultiHashMap.this.modCount != this.expectedModCount) {
				throw new ConcurrentModificationException();
			}
			removeMapping(this.last);
			this.last = null;
			this.expectedModCount = BidiMultiHashMap.this.modCount;
		}

		@Override
		public String toString() {
			if (this.last != null) {
				return "Iterator[" + this.last.getKey() //$NON-NLS-1$
						+ "=" + this.last.getValue() //$NON-NLS-1$
						+ "]"; //$NON-NLS-1$
			}
			return "Iterator[]"; //$NON-NLS-1$
		}
	}

	/**
	 * KeySet implementation.
	 */
	protected class KeySet extends AbstractSet<K> {

		@Override
		public void clear() {
			BidiMultiHashMap.this.clear();
		}

		@Override
		public boolean contains(Object key) {
			return BidiMultiHashMap.this.containsKey(key);
		}

		@Override
		public Iterator<K> iterator() {
			return createKeySetIterator();
		}

		@Override
		public boolean remove(Object key) {
			return BidiMultiHashMap.this.remove(key) != null;
		}

		@Override
		public int size() {
			return BidiMultiHashMap.this.keySize;
		}
	}

	/**
	 * Keys implementation.
	 */
	protected class KeySetForValue extends AbstractSet<K> {

		protected V value;

		public KeySetForValue(V value) {
			this.value = value;
		}

		@Override
		public boolean add(K key) {
			return put(key, this.value) != null;
		}

		@Override
		public void clear() {
			removeValue(this.value);
		}

		@Override
		public boolean contains(Object key) {
			return containsEntry(key, this.value);
		}

		@Override
		public Iterator<K> iterator() {
			return new KeySetIteratorForValue(this.value);
		}

		@Override
		public boolean remove(Object key) {
			return removeEntry(key, this.value);
		}

		@Override
		public int size() {
			int size = 0;
			Iterator<K> iter = iterator();
			while (iter.hasNext()) {
				iter.next();
				size++;
			}
			return size;
		}
	}

	/**
	 * KeySet iterator.
	 */
	protected class KeySetIterator extends HashIterator<K> {

		@Override
		protected HashEntry<K, V> getNext(HashEntry<K, V> e) {
			return e.keyNext;
		}

		@Override
		protected HashEntry<K, V>[] getTable() {
			return BidiMultiHashMap.this.keys;
		}

		@Override
		public K next() {
			return super.nextEntry().getKey();
		}

		@Override
		public void remove() {
			if (this.last == null) {
				throw new IllegalStateException(REMOVE_INVALID);
			}
			if (BidiMultiHashMap.this.modCount != this.expectedModCount) {
				throw new ConcurrentModificationException();
			}
			BidiMultiHashMap.this.removeKey(this.last.getKey());
			this.last = null;
			this.expectedModCount = BidiMultiHashMap.this.modCount;
		}
	}

	/**
	 * KeySet iterator.
	 */
	protected class KeySetIteratorForValue extends HashIteratorForSame<K> {

		public KeySetIteratorForValue(Object value) {
			super(value);
		}

		@Override
		protected HashEntry<K, V> getFirstSame(Object value) {
			// Find the first entry matching the value
			int hashCode = hash(value);
			HashEntry<K, V> entry = BidiMultiHashMap.this.values[hashIndex(
					hashCode, BidiMultiHashMap.this.values.length)];
			while (entry != null) {
				if (entry.valueHashCode == hashCode
						&& isEqualValue(value, entry.value)) {
					return entry;
				}
				entry = entry.valueNext;
			}
			return null;
		}

		@Override
		protected HashEntry<K, V> getNext(HashEntry<K, V> e) {
			return e.valueSame;
		}

		@Override
		public K next() {
			return super.nextEntry().getKey();
		}
	}

	/**
	 * Values implementation.
	 */
	protected class ValueSet extends AbstractSet<V> {

		@Override
		public void clear() {
			BidiMultiHashMap.this.clear();
		}

		@Override
		public boolean contains(Object value) {
			return BidiMultiHashMap.this.containsValue(value);
		}

		@Override
		public Iterator<V> iterator() {
			return createValuesIterator();
		}

		@Override
		public boolean remove(Object value) {
			return removeValue(value) != null;
		}

		@Override
		public int size() {
			return BidiMultiHashMap.this.valueSize;
		}

	}

	/**
	 * Values implementation.
	 */
	protected class ValueSetForKey extends AbstractSet<V> {

		protected K key;

		public ValueSetForKey(K key) {
			this.key = key;
		}

		@Override
		public boolean add(V value) {
			return put(this.key, value) != null;
		}

		@Override
		public void clear() {
			BidiMultiHashMap.this.remove(this.key);
		}

		@Override
		public boolean contains(Object value) {
			return BidiMultiHashMap.this.containsEntry(this.key, value);
		}

		@Override
		public Iterator<V> iterator() {
			return new ValueSetIteratorForKey(this.key);
		}

		@Override
		public boolean remove(Object value) {
			return removeEntry(this.key, value);
		}

		@Override
		public int size() {
			int size = 0;
			Iterator<V> iter = iterator();
			while (iter.hasNext()) {
				iter.next();
				size++;
			}
			return size;
		}

	}

	/**
	 * Values iterator.
	 */
	protected class ValueSetIterator extends HashIterator<V> {

		@Override
		protected HashEntry<K, V> getNext(HashEntry<K, V> e) {
			return e.valueNext;
		}

		@Override
		protected HashEntry<K, V>[] getTable() {
			return BidiMultiHashMap.this.values;
		}

		@Override
		public V next() {
			return super.nextEntry().getValue();
		}

		@Override
		public void remove() {
			if (this.last == null) {
				throw new IllegalStateException(REMOVE_INVALID);
			}
			if (BidiMultiHashMap.this.modCount != this.expectedModCount) {
				throw new ConcurrentModificationException();
			}
			removeValue(this.last.getValue());
			this.last = null;
			this.expectedModCount = BidiMultiHashMap.this.modCount;
		}
	}

	/**
	 * KeySet iterator.
	 */
	protected class ValueSetIteratorForKey extends HashIteratorForSame<V> {

		public ValueSetIteratorForKey(K key) {
			super(key);
		}

		@Override
		protected HashEntry<K, V> getFirstSame(Object key) {
			// Find the first entry matching the value
			int hashCode = hash(key);
			HashEntry<K, V> entry = BidiMultiHashMap.this.keys[hashIndex(
					hashCode, BidiMultiHashMap.this.keys.length)];
			while (entry != null) {
				if (entry.keyHashCode == hashCode && isEqualKey(key, entry.key)) {
					return entry;
				}
				entry = entry.keyNext;
			}
			return null;
		}

		@Override
		protected HashEntry<K, V> getNext(HashEntry<K, V> e) {
			return e.keySame;
		}

		@Override
		public V next() {
			return super.nextEntry().getValue();
		}
	}

	/** The default capacity to use */
	protected static final int DEFAULT_CAPACITY = 16;
	/** The default load factor to use */
	protected static final float DEFAULT_LOAD_FACTOR = 0.75f;
	/** The default threshold to use */
	protected static final int DEFAULT_THRESHOLD = 12;
	protected static final String GETKEY_INVALID = "getKey() can only be called after next() and before remove()"; //$NON-NLS-1$
	protected static final String GETVALUE_INVALID = "getValue() can only be called after next() and before remove()"; //$NON-NLS-1$
	/** The maximum capacity allowed */
	protected static final int MAXIMUM_CAPACITY = 1 << 30;
	protected static final String NO_NEXT_ENTRY = "No next() entry in the iteration"; //$NON-NLS-1$
	protected static final String NO_PREVIOUS_ENTRY = "No previous() entry in the iteration"; //$NON-NLS-1$
	/** An object for masking null */
	protected static final Object NULL = new Object();
	protected static final String REMOVE_INVALID = "remove() can only be called once after next()"; //$NON-NLS-1$

	protected static final String SETVALUE_INVALID = "setValue() can only be called after next() and before remove()"; //$NON-NLS-1$

	protected transient HashEntry<K, V>[] entries;

	/** Entry set */
	protected transient EntrySet entrySet;

	protected transient int entryThreshold;

	/** Map entries */
	protected transient HashEntry<K, V>[] keys;

	/** Key set */
	protected transient KeySet keySet;

	/** The size of the map */
	protected transient int keySize;

	/** Size at which to rehash */
	protected transient int keyThreshold;

	/** Load factor, normally 0.75 */
	protected transient float loadFactor;

	/** Modification count for iterators */
	protected transient int modCount;

	protected transient int size;

	protected transient HashEntry<K, V>[] values;

	/** Values */
	protected transient ValueSet valueSet;

	protected transient int valueSize;

	protected transient int valueThreshold;

	/**
	 * Default constructor.
	 */
	public BidiMultiHashMap() {
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR, DEFAULT_THRESHOLD);
	}

	/**
	 * Constructs a new, empty map with the specified initial capacity and
	 * default load factor.
	 * 
	 * @param initialCapacity
	 *            the initial capacity
	 * @throws IllegalArgumentException
	 *             if the initial capacity is less than one
	 */
	public BidiMultiHashMap(int initialCapacity) {
		this(initialCapacity, DEFAULT_LOAD_FACTOR);
	}

	/**
	 * Constructs a new, empty map with the specified initial capacity and load
	 * factor.
	 * 
	 * @param initialCapacity
	 *            the initial capacity
	 * @param loadFactor
	 *            the load factor
	 * @throws IllegalArgumentException
	 *             if the initial capacity is less than one
	 * @throws IllegalArgumentException
	 *             if the load factor is less than or equal to zero
	 */
	public BidiMultiHashMap(int initialCapacity, float loadFactor) {
		super();
		if (initialCapacity < 1) {
			throw new IllegalArgumentException(
					"Initial capacity must be greater than 0"); //$NON-NLS-1$
		}
		if (loadFactor <= 0.0f || Float.isNaN(loadFactor)) {
			throw new IllegalArgumentException(
					"Load factor must be greater than 0"); //$NON-NLS-1$
		}
		this.loadFactor = loadFactor;
		this.keyThreshold = this.valueThreshold = this.entryThreshold = calculateThreshold(
				initialCapacity, loadFactor);
		initialCapacity = calculateNewCapacity(initialCapacity);
		this.keys = new HashEntry[initialCapacity];
		this.values = new HashEntry[initialCapacity];
		this.entries = new HashEntry[initialCapacity];
	}

	/**
	 * Constructor which performs no validation on the passed in parameters.
	 * 
	 * @param initialCapacity
	 *            the initial capacity, must be a power of two
	 * @param loadFactor
	 *            the load factor, must be &gt; 0.0f and generally &lt; 1.0f
	 * @param threshold
	 *            the threshold, must be sensible
	 */
	public BidiMultiHashMap(int initialCapacity, float loadFactor, int threshold) {
		super();
		this.loadFactor = loadFactor;
		this.keys = new HashEntry[initialCapacity];
		this.values = new HashEntry[initialCapacity];
		this.entries = new HashEntry[initialCapacity];
		this.keyThreshold = this.valueThreshold = this.entryThreshold = threshold;
	}

	/**
	 * Constructor copying elements from another map.
	 * 
	 * @param map
	 *            the map to copy
	 * @throws NullPointerException
	 *             if the map is null
	 */
	public BidiMultiHashMap(Map<K, V> map) {
		this(Math.max(2 * map.size(), DEFAULT_CAPACITY), DEFAULT_LOAD_FACTOR);
		putAll(map);
	}

	// -----------------------------------------------------------------------
	/**
	 * Adds a new key-value mapping into this map.
	 * <p>
	 * This implementation calls <code>createEntry()</code>,
	 * <code>addEntry()</code> and <code>checkCapacity()</code>. It also handles
	 * changes to <code>modCount</code> and <code>size</code>. Subclasses could
	 * override to fully control adds to the map.
	 * 
	 * @param key
	 *            the key to add (already converted)
	 * @param value
	 *            the value to add (already converted)
	 */
	protected void addMapping(K key, V value) {

		this.modCount++;

		/*
		 * Check for matching keys
		 */
		int keyHashCode = hash(key);
		int keyHashIndex = hashIndex(keyHashCode, this.keys.length);
		HashEntry<K, V> sameKeyEntry = this.keys[keyHashIndex];
		while (sameKeyEntry != null) {
			if (sameKeyEntry.keyHashCode == keyHashCode
					&& isEqualKey(sameKeyEntry.key, key)) {
				break;
			}
			sameKeyEntry = sameKeyEntry.keyNext;
		}

		/*
		 * Check for matching value
		 */
		int valueHashCode = hash(value);
		int valueHashIndex = hashIndex(valueHashCode, this.values.length);
		HashEntry<K, V> sameValueEntry = this.values[valueHashIndex];
		while (sameValueEntry != null) {
			if (sameValueEntry.valueHashCode == valueHashCode
					&& isEqualValue(sameValueEntry.value, value)) {
				break;
			}
			sameValueEntry = sameValueEntry.valueNext;
		}

		int entryHashCode = hash(key, value);
		int entryHashIndex = hashIndex(entryHashCode, this.entries.length);

		/*
		 * Create the entry
		 */
		HashEntry<K, V> keyPrevious = sameKeyEntry != null ? sameKeyEntry.keyPrevious
				: null;
		HashEntry<K, V> keyNext = sameKeyEntry != null ? sameKeyEntry.keyNext
				: this.keys[keyHashIndex];
		HashEntry<K, V> valuePrevious = sameValueEntry != null ? sameValueEntry.valuePrevious
				: null;
		HashEntry<K, V> valueNext = sameValueEntry != null ? sameValueEntry.valueNext
				: this.values[valueHashIndex];
		HashEntry<K, V> entry = createEntry(keyPrevious, keyNext, sameKeyEntry,
				valuePrevious, valueNext, sameValueEntry, null,
				this.entries[entryHashIndex], keyHashCode, valueHashCode,
				entryHashCode, key, value);

		/*
		 * Change links reference to insert the entry
		 */
		if (entry.keyPrevious != null) {
			entry.keyPrevious.keyNext = entry;
		} else {
			this.keys[keyHashIndex] = entry;
		}
		if (entry.keyNext != null) {
			entry.keyNext.keyPrevious = entry;
		}
		if(entry.keySame!=null) {
			entry.keySame.keyNext = null;
			entry.keySame.keyPrevious = entry;
		}

		if (entry.valuePrevious != null) {
			entry.valuePrevious.valueNext = entry;
		} else {
			this.values[valueHashIndex] = entry;
		}
		if (entry.valueNext != null) {
			entry.valueNext.valuePrevious = entry;
		}
		if (entry.valueSame != null) {
			entry.valueSame.valuePrevious = entry;
			entry.valueSame.valueNext = null;
		}

		if (entry.entryPrevious != null) {
			entry.entryPrevious.entryNext = entry;
		} else {
			this.entries[entryHashIndex] = entry;
		}
		if (entry.entryNext != null) {
			entry.entryNext.entryPrevious = entry;
		}

		/*
		 * Increase the size
		 */
		this.size++;
		if (sameKeyEntry == null) {
			this.keySize++;
		}
		if (sameValueEntry == null) {
			this.valueSize++;
		}

		checkCapacity();

	}

	/**
	 * Calculates the new capacity of the map. This implementation normalizes
	 * the capacity to a power of two.
	 * 
	 * @param proposedCapacity
	 *            the proposed capacity
	 * @return the normalized new capacity
	 */
	protected int calculateNewCapacity(int proposedCapacity) {
		int newCapacity = 1;
		if (proposedCapacity > MAXIMUM_CAPACITY) {
			newCapacity = MAXIMUM_CAPACITY;
		} else {
			while (newCapacity < proposedCapacity) {
				newCapacity <<= 1; // multiply by two
			}
			if (newCapacity > MAXIMUM_CAPACITY) {
				newCapacity = MAXIMUM_CAPACITY;
			}
		}
		return newCapacity;
	}

	/**
	 * Calculates the new threshold of the map, where it will be resized. This
	 * implementation uses the load factor.
	 * 
	 * @param newCapacity
	 *            the new capacity
	 * @param factor
	 *            the load factor
	 * @return the new resize threshold
	 */
	protected int calculateThreshold(int newCapacity, float factor) {
		return (int) (newCapacity * factor);
	}

	// -----------------------------------------------------------------------
	/**
	 * Checks the capacity of the map and enlarges it if necessary.
	 * <p>
	 * This implementation uses the threshold to check if the map needs
	 * enlarging
	 */
	protected void checkCapacity() {
		if (this.keySize >= this.keyThreshold) {
			int newCapacity = this.keys.length * 2;
			if (newCapacity <= MAXIMUM_CAPACITY) {
				ensureKeyCapacity(newCapacity);
			}
		}

		if (this.valueSize >= this.valueThreshold) {
			int newCapacity = this.values.length * 2;
			if (newCapacity <= MAXIMUM_CAPACITY) {
				ensureValueCapacity(newCapacity);
			}
		}

		if (this.size >= this.entryThreshold) {
			int newCapacity = this.entries.length * 2;
			if (newCapacity <= MAXIMUM_CAPACITY) {
				ensureEntryCapacity(newCapacity);
			}
		}
	}

	/**
	 * Clears the map, resetting the size to zero and nullifying references to
	 * avoid garbage collection issues.
	 */
	@Override
	public void clear() {
		this.modCount++;
		for (int i = this.keys.length - 1; i >= 0; i--) {
			this.keys[i] = null;
		}
		for (int i = this.values.length - 1; i >= 0; i--) {
			this.values[i] = null;
		}
		for (int i = this.entries.length - 1; i >= 0; i--) {
			this.entries[i] = null;
		}
		this.size = 0;
		this.keySize = 0;
		this.valueSize = 0;
	}

	/**
	 * Checks whether the map contains the specified entry.
	 * 
	 * @param key
	 *            the key to search for
	 * @param value
	 *            the value to search for
	 * @return true if the map contains the entry
	 */
	@Override
	public boolean containsEntry(Object key, Object value) {
		return getEntry(key, value) != null;
	}

	// -----------------------------------------------------------------------
	/**
	 * Checks whether the map contains the specified key.
	 * 
	 * @param key
	 *            the key to search for
	 * @return true if the map contains the key
	 */
	@Override
	public boolean containsKey(Object key) {
		int hashCode = hash(key);
		HashEntry<K, V> entry = this.keys[hashIndex(hashCode, this.keys.length)];
		while (entry != null) {
			if (entry.keyHashCode == hashCode && isEqualKey(key, entry.key)) {
				return true;
			}
			entry = entry.keyNext;
		}
		return false;
	}

	/**
	 * Checks whether the map contains the specified value.
	 * 
	 * @param value
	 *            the value to search for
	 * @return true if the map contains the value
	 */
	@Override
	public boolean containsValue(Object value) {
		int hashCode = hash(value);
		HashEntry<K, V> entry = this.values[hashIndex(hashCode,
				this.values.length)];
		while (entry != null) {
			if (entry.valueHashCode == hashCode
					&& isEqualValue(value, entry.value)) {
				return true;
			}
			entry = entry.valueNext;
		}
		return false;
	}

	// -----------------------------------------------------------------------

	/**
	 * Creates an entry to store the key-value data.
	 * <p>
	 * This implementation creates a new HashEntry instance. Subclasses can
	 * override this to return a different storage class, or implement caching.
	 * 
	 * @return the newly created entry
	 */
	protected HashEntry<K, V> createEntry(HashEntry<K, V> keyPrevious,
			HashEntry<K, V> keyNext, HashEntry<K, V> keySame,
			HashEntry<K, V> valuePrevious, HashEntry<K, V> valueNext,
			HashEntry<K, V> valueSame, HashEntry<K, V> entryPrevious,
			HashEntry<K, V> entryNext, int keyHashCode, int valueHashCode,
			int entryHashCode, K key, V value) {

		return new HashEntry<K, V>(keyPrevious, keyNext, keySame,
				valuePrevious, valueNext, valueSame, entryPrevious, entryNext,
				keyHashCode, valueHashCode, entryHashCode, key, value);
	}

	/**
	 * Creates an entry set iterator. Subclasses can override this to return
	 * iterators with different properties.
	 * 
	 * @return the entrySet iterator
	 */
	protected Iterator<Entry<K, V>> createEntrySetIterator() {
		if (size() == 0) {
			return EmptyIterator.INSTANCE;
		}
		return new EntrySetIterator();
	}

	/**
	 * Creates a key set iterator. Subclasses can override this to return
	 * iterators with different properties.
	 * 
	 * @return the keySet iterator
	 */
	protected Iterator<K> createKeySetIterator() {
		if (size() == 0) {
			return EmptyIterator.INSTANCE;
		}
		return new KeySetIterator();
	}

	/**
	 * Creates a values iterator. Subclasses can override this to return
	 * iterators with different properties.
	 * 
	 * @return the values iterator
	 */
	protected Iterator<V> createValuesIterator() {
		if (size() == 0) {
			return EmptyIterator.INSTANCE;
		}
		return new ValueSetIterator();
	}

	/**
	 * Kills an entry ready for the garbage collector.
	 * <p>
	 * This implementation prepares the HashEntry for garbage collection.
	 * Subclasses can override this to implement caching (override clear as
	 * well).
	 * 
	 * @param entry
	 *            the entry to destroy
	 */
	protected void destroyEntry(HashEntry<K, V> entry) {
		entry.keyNext = null;
		entry.keyPrevious = null;
		entry.keySame = null;
		entry.keyHashCode = 0;
		entry.valueNext = null;
		entry.valuePrevious = null;
		entry.valueSame = null;
		entry.valueHashCode = 0;
		entry.entryNext = null;
		entry.entryPrevious = null;
		entry.key = null;
		entry.value = null;
		entry.entryHashCode = 0;
	}

	/**
	 * Changes the size of the data structure to the capacity proposed.
	 * 
	 * @param newCapacity
	 *            the new capacity of the array (a power of two, less or equal
	 *            to max)
	 */
	protected void ensureEntryCapacity(int newCapacity) {
		int oldCapacity = this.entries.length;
		if (newCapacity <= oldCapacity) {
			return;
		}
		if (this.size == 0) {
			this.entryThreshold = calculateThreshold(newCapacity,
					this.loadFactor);
			this.entries = new HashEntry[newCapacity];
		} else {
			HashEntry<K, V> oldentrys[] = this.entries;
			HashEntry<K, V> newentries[] = new HashEntry[newCapacity];

			this.modCount++;

			// Re-hash the entrys
			for (int i = oldentrys.length - 1; i >= 0; i--) {
				HashEntry<K, V> entry = oldentrys[i];
				if (entry != null) {
					oldentrys[i] = null; // gc
					do {
						// Keep reference on the next entry
						HashEntry<K, V> next = entry.entryNext;
						// Compute the next location of the entry
						int index = hashIndex(entry.entryHashCode, newCapacity);
						// Change link reference.
						entry.entryNext = newentries[index];
						entry.entryPrevious = null;
						if (newentries[index] != null) {
							newentries[index].entryPrevious = entry;
						}
						newentries[index] = entry;
						// Continue with the next entry
						entry = next;
					} while (entry != null);
				}
			}
			this.entryThreshold = calculateThreshold(newCapacity,
					this.loadFactor);
			this.entries = newentries;
		}
	}

	/**
	 * Changes the size of the data structure to the capacity proposed.
	 * 
	 * @param newCapacity
	 *            the new capacity of the array (a power of two, less or equal
	 *            to max)
	 */
	protected void ensureKeyCapacity(int newCapacity) {
		int oldCapacity = this.keys.length;
		if (newCapacity <= oldCapacity) {
			return;
		}
		if (this.size == 0) {
			this.keyThreshold = calculateThreshold(newCapacity, this.loadFactor);
			this.keys = new HashEntry[newCapacity];
		} else {
			HashEntry<K, V> oldKeys[] = this.keys;
			HashEntry<K, V> newKeys[] = new HashEntry[newCapacity];

			this.modCount++;

			// Re-hash the keys
			for (int i = oldKeys.length - 1; i >= 0; i--) {
				HashEntry<K, V> entry = oldKeys[i];
				if (entry != null) {
					oldKeys[i] = null; // gc
					do {
						// Keep reference on the next entry
						HashEntry<K, V> next = entry.keyNext;
						// Compute the next location of the entry
						int index = hashIndex(entry.keyHashCode, newCapacity);
						// Change link reference.
						entry.keyNext = newKeys[index];
						entry.keyPrevious = null;
						if (newKeys[index] != null) {
							newKeys[index].keyPrevious = entry;
						}
						newKeys[index] = entry;
						// Continue with the next entry
						entry = next;
					} while (entry != null);
				}
			}
			this.keyThreshold = calculateThreshold(newCapacity, this.loadFactor);
			this.keys = newKeys;
		}
	}

	/**
	 * Changes the size of the data structure to the capacity proposed.
	 * 
	 * @param newCapacity
	 *            the new capacity of the array (a power of two, less or equal
	 *            to max)
	 */
	protected void ensureValueCapacity(int newCapacity) {
		int oldCapacity = this.values.length;
		if (newCapacity <= oldCapacity) {
			return;
		}
		if (this.size == 0) {
			this.valueThreshold = calculateThreshold(newCapacity,
					this.loadFactor);
			this.values = new HashEntry[newCapacity];
		} else {
			HashEntry<K, V> oldvalues[] = this.values;
			HashEntry<K, V> newvalues[] = new HashEntry[newCapacity];

			this.modCount++;

			// Re-hash the values
			for (int i = oldvalues.length - 1; i >= 0; i--) {
				HashEntry<K, V> entry = oldvalues[i];
				if (entry != null) {
					oldvalues[i] = null; // gc
					do {
						// Keep reference on the next entry
						HashEntry<K, V> next = entry.valueNext;
						// Compute the next location of the entry
						int index = hashIndex(entry.valueHashCode, newCapacity);
						// Change link reference.
						entry.valueNext = newvalues[index];
						entry.valuePrevious = null;
						if (newvalues[index] != null) {
							newvalues[index].valuePrevious = entry;
						}
						newvalues[index] = entry;
						// Continue with the next entry
						entry = next;
					} while (entry != null);
				}
			}
			this.valueThreshold = calculateThreshold(newCapacity,
					this.loadFactor);
			this.values = newvalues;
		}
	}

	// -----------------------------------------------------------------------
	/**
	 * Gets the entrySet view of the map. Changes made to the view affect this
	 * map.
	 * 
	 * @return the entrySet view
	 */
	@Override
	public Set<Entry<K, V>> entrySet() {
		if (this.entrySet == null) {
			this.entrySet = new EntrySet();
		}
		return this.entrySet;
	}

	// -----------------------------------------------------------------------
	/**
	 * Gets the value mapped to the key specified.
	 * 
	 * @param key
	 *            the key
	 * @return return the first value matching the key.
	 */
	@Override
	public V get(Object key) {
		return null;
	}

	// -----------------------------------------------------------------------
	/**
	 * Gets the entry mapped to the key specified.
	 * <p>
	 * This method exists for subclasses that may need to perform a multi-step
	 * process accessing the entry. The public methods in this class don't use
	 * this method to gain a small performance boost.
	 * 
	 * @param key
	 *            the key (not converted)
	 * @param value
	 *            the value (not converted)
	 * @return the entry, null if no match
	 */
	protected HashEntry<K, V> getEntry(Object key, Object value) {
		int hashCode = hash(key, value);
		HashEntry<K, V> entry = this.entries[hashIndex(hashCode,
				this.entries.length)];
		while (entry != null) {
			if (entry.hashCode() == hashCode && isEqualKey(key, entry.key)
					&& isEqualValue(value, entry.value)) {
				return entry;
			}
			entry = entry.entryNext;
		}
		return null;
	}

	/**
	 * Gets the hash code for the object specified. This implementation uses the
	 * additional hashing routine from JDK1.4. Subclasses can override this to
	 * return alternate hash codes.
	 * 
	 * @param obj
	 *            the object to get a hash code for
	 * @return the hash code
	 */
	protected int hash(Object obj) {
		// same as JDK 1.4
		int h = obj.hashCode();
		h += ~(h << 9);
		h ^= (h >>> 14);
		h += (h << 4);
		h ^= (h >>> 10);
		return h;
	}

	/**
	 * Gets the hash code for key-value specified.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	protected int hash(Object key, Object value) {
		return hash(key) + hash(value);
	}

	/**
	 * Gets the index into the data storage for the hashCode specified. This
	 * implementation uses the least significant bits of the hashCode.
	 * Subclasses can override this to return alternate bucketing.
	 * 
	 * @param hashCode
	 *            the hash code to use
	 * @param dataSize
	 *            the size of the data to pick a bucket from
	 * @return the bucket index
	 */
	protected int hashIndex(int hashCode, int dataSize) {
		return hashCode & (dataSize - 1);
	}

	/**
	 * Compares two keys, in internal converted form, to see if they are equal.
	 * This implementation uses the equals method and assumes neither key is
	 * null. Subclasses can override this to match differently.
	 * 
	 * @param key1
	 *            the first key to compare passed in from outside (converted
	 *            value)
	 * @param key2
	 *            the second key extracted from the entry (converted value)
	 * @return true if equal
	 */
	protected boolean isEqualKey(Object key1, Object key2) {
		return (key1 == key2 || key1.equals(key2));
	}

	/**
	 * Compares two values, in external form, to see if they are equal. This
	 * implementation uses the equals method and assumes neither value is null.
	 * Subclasses can override this to match differently.
	 * 
	 * @param value1
	 *            the first value to compare passed in from outside (converted
	 *            value)
	 * @param value2
	 *            the second value extracted from the entry (converted value)
	 * @return true if equal
	 */
	protected boolean isEqualValue(Object value1, Object value2) {
		return (value1 == value2 || value1.equals(value2));
	}

	// -----------------------------------------------------------------------
	/**
	 * Gets the keySet view of the map. Changes made to the view affect this
	 * map. To simply iterate through the keys, use {@link #mapIterator()}.
	 * 
	 * @return the keySet view
	 */
	@Override
	public Set<K> keySet() {
		if (this.keySet == null) {
			this.keySet = new KeySet();
		}
		return this.keySet;
	}

	/**
	 * Gets the keySet view of the map. Changes made to the view affect this
	 * map. To simply iterate through the keys, use {@link #mapIterator()}.
	 * 
	 * @param value
	 *            the value
	 * @return the keySet view
	 */
	@Override
	public Set<K> keySet(V value) {
		return new KeySetForValue(value);
	}

	// -----------------------------------------------------------------------
	/**
	 * Puts a key-value mapping into this map.
	 * 
	 * @param key
	 *            the key to add
	 * @param value
	 *            the value to add
	 * @return the value previously mapped to this key, null if none
	 */
	@Override
	public V put(K key, V value) {

		// Check if the entry already exists.
		int hashCode = hash(key, value);
		int index = hashIndex(hashCode, this.entries.length);
		HashEntry<K, V> entry = this.entries[index];
		while (entry != null) {
			if (entry.hashCode() == hashCode && isEqualKey(key, entry.key)
					&& isEqualValue(value, entry.value)) {
				// The entry already exists
				return entry.getValue();
			}
			entry = entry.entryNext;
		}

		// Add a mapping
		addMapping(key, value);
		return null;
	}

	@Override
	public V remove(Object key) {
		Set<V> set = removeKey(key);
		if (set == null) {
			return null;
		}
		return set.iterator().next();
	}

	/**
	 * Removes an entry from the chain stored in a particular index.
	 * <p>
	 * This implementation removes the entry from the data storage table. The
	 * size is not updated. Subclasses could override to handle changes to the
	 * map.
	 * 
	 * @param entry
	 *            the entry to remove
	 */
	protected void removeEntry(HashEntry<K, V> entry) {

		/*
		 * Change key related links
		 */
		if (entry.keyPrevious != null) {
			if (entry.keyPrevious.keySame == entry) {
				entry.keyPrevious.keySame = entry.keySame;
			} else {
				entry.keyPrevious.keyNext = entry.keySame != null ? entry.keySame
						: entry.keyNext;
			}
		} else {
			this.keys[hashIndex(entry.keyHashCode, this.keys.length)] = entry.keySame != null ? entry.keySame
					: entry.keyNext;
		}
		if (entry.keyNext != null) {
			entry.keyNext.keyPrevious = entry.keySame != null ? entry.keySame
					: entry.keyPrevious;
		}
		if (entry.keySame != null) {
			entry.keySame.keyPrevious = entry.keyPrevious;
			entry.keySame.keyNext = entry.keyNext;
		}

		/*
		 * Change value related links
		 */
		if (entry.valuePrevious != null) {
			if (entry.valuePrevious.valueSame == entry) {
				entry.valuePrevious.valueSame = entry.valueSame;
			} else {
				entry.valuePrevious.valueNext = entry.valueSame != null ? entry.valueSame
						: entry.valueNext;
			}
		} else {
			this.values[hashIndex(entry.valueHashCode, this.values.length)] = entry.valueSame != null ? entry.valueSame
					: entry.valueNext;
		}
		if (entry.valueNext != null) {
			entry.valueNext.valuePrevious = entry.valueSame != null ? entry.valueSame
					: entry.valuePrevious;
		}
		if (entry.valueSame != null) {
			entry.valueSame.valuePrevious = entry.valuePrevious;
			entry.valueSame.valueNext = entry.valueNext;
		}

		/*
		 * Change entry related links
		 */
		if (entry.entryPrevious != null) {
			entry.entryPrevious.entryNext = entry.entryNext;
		} else {
			this.entries[hashIndex(entry.entryHashCode, this.entries.length)] = entry.entryNext;
		}
		if (entry.entryNext != null) {
			entry.entryNext.entryPrevious = entry.entryPrevious;
		}

	}

	/**
	 * Removes the specified mapping from this map.
	 * 
	 * @param key
	 *            the mapping to remove
	 * @param value
	 *            the mapping to remove
	 * @return False if key not in map
	 */
	@Override
	public boolean removeEntry(Object key, Object value) {
		int hashCode = hash(key, value);
		HashEntry<K, V> entry = this.entries[hashIndex(hashCode,
				this.entries.length)];
		while (entry != null) {
			if (entry.entryHashCode == hashCode && isEqualKey(key, entry.key)
					&& isEqualValue(value, entry.value)) {
				removeMapping(entry);
				return true;
			}
			entry = entry.entryNext;
		}
		return false;
	}

	/**
	 * Removes the specified mapping from this map.
	 * 
	 * @param key
	 *            the mapping to remove
	 * @return the values mapped to the removed key, null if key not in map
	 */
	@Override
	public Set<V> removeKey(Object key) {
		Set<V> set = null;
		int hashCode = hash(key);
		HashEntry<K, V> entry = this.keys[hashIndex(hashCode, this.keys.length)];
		while (entry != null) {

			if (entry.keyHashCode == hashCode && isEqualKey(key, entry.key)) {
				if (set == null) {
					set = new HashSet<V>();
				}
				set.add(entry.getValue());
				HashEntry<K, V> next = entry.keySame;
				removeMapping(entry);
				entry = next;
			} else {
				entry = entry.keyNext;
			}
		}
		return set;
	}

	// -----------------------------------------------------------------------
	/**
	 * Removes a mapping from the map.
	 * <p>
	 * This implementation calls <code>removeEntry()</code> and
	 * <code>destroyEntry()</code>. It also handles changes to
	 * <code>modCount</code> and <code>size</code>. Subclasses could override to
	 * fully control removals from the map.
	 * 
	 * @param entry
	 *            the entry to remove
	 */
	protected void removeMapping(HashEntry<K, V> entry) {
		this.modCount++;
		boolean sameKeyExits = entry.keySame != null
				|| (entry.keyPrevious != null && entry.keyPrevious.keySame == entry);
		boolean sameValueExits = entry.valueSame != null
				|| (entry.valuePrevious != null && entry.valuePrevious.valueSame == entry);
		removeEntry(entry);
		this.size--;
		if (!sameKeyExits) {
			this.keySize--;
		}
		if (!sameValueExits) {
			this.valueSize--;
		}
		destroyEntry(entry);
	}

	/**
	 * Removes the specified mapping from this map.
	 * 
	 * @param value
	 *            the mapping to remove
	 * @return the keys mapped to the removed key, null if key not in map
	 */
	@Override
	public Set<K> removeValue(Object value) {
		Set<K> set = null;
		int hashCode = hash(value);
		HashEntry<K, V> entry = this.values[hashIndex(hashCode,
				this.values.length)];
		while (entry != null) {
			if (entry.valueHashCode == hashCode
					&& isEqualValue(value, entry.value)) {
				if (set == null) {
					set = new HashSet<K>();
				}
				set.add(entry.getKey());
				HashEntry<K, V> next = entry.valueSame;
				removeMapping(entry);
				entry = next;
			} else {
				entry = entry.valueNext;
			}
		}
		return set;
	}

	/**
	 * Gets the size of the map.
	 * 
	 * @return the size
	 */
	@Override
	public int size() {
		return this.size;
	}

	// -----------------------------------------------------------------------
	/**
	 * Gets the values view of the map. Changes made to the view affect this
	 * map.
	 * 
	 * @return the values view
	 */
	@Override
	public Collection<V> values() {
		return valueSet();
	}

	/**
	 * Gets the values view of the map. Changes made to the view affect this
	 * map.
	 * 
	 * @return the values view
	 */
	@Override
	public Set<V> valueSet() {
		if (this.valueSet == null) {
			this.valueSet = new ValueSet();
		}
		return this.valueSet;
	}

	/**
	 * Gets the values view of the map for the key specified. Changes made to
	 * the view affect this map.
	 * 
	 * @param key
	 * @return
	 */
	@Override
	public Set<V> valueSet(K key) {
		return new ValueSetForKey(key);
	}

}