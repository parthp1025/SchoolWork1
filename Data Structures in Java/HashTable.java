

import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
/**
 * 
 * @author Parth Patel
 * CS-1332-Sec-A3
 * HashTable implementation
 * LinearProbing 
 * @param <K>
 * @param <V>
 */

public class HashTable<K,V> {

	/**
	 * The maximum load factor for this hashtable
	 */
	private final double MAX_LOAD_FACTOR = .64;

	/**
	 * The number of entries in this hashtable
	 */
	private int size;

	/**
	 * The underlying array for this hashtable
	 */
	private Entry<K,V>[] table;
	
	
	public HashTable(int size, double MAX_LOAD_FACTOR){
		this.size = size;
		
	}
	public HashTable(){
		this(11, 0.64);
	}
	
	/**
	 * I suppose this is really a compress method, thus After taking the index
	 * it also compresses and returns that value
	 * @param key
	 * @return
	 */
	private int hashcomp(Object key) {
		if(key == null){
			throw new NullPointerException();
		}
		int i = key.hashCode() % size;
		return i;
	}
	/**
	 * Puts the key value pair into the table. If the
	 * key already exists in the table, replace the
	 * old value with the new one and return the old value
	 * 
	 * @param key, never null
	 * @param value, possibly null
	 * @return the replaced value, null if nothing existed previously
	 */
	public V put(K key ,V value) {
		if(key == null){
			throw new NullPointerException();
		}
		V arr3 = puthelper(key, value, hashcomp(key));
		resize();
		return arr3;
	}
	
	private V puthelper(K key, V value, int in){
		if( in >= table.length){
			in = 0;
		}
	       if(table[in] == null) {
			table[in] = new Entry<K, V>(key, value);
			size++;
			return null;
		}
		else if(table[in].isAvailable()){
			table[in] = new Entry<K, V>(key, value);
			size++;
			return null;
			
		} else if(table[in] != null && table[in].getKey().equals(key)){
			V arr2 = table[in].getValue();
			table[in].setValue(value);
			return arr2;
			
		} else if(!table[in].isAvailable()){
			++in;
			return null;
			
		} else {
			return puthelper(key, value, ++in);
		}
	}
	
	private void resize(){
		if(size / table.length >= 0.64) {
			size = 0;
			}
		int res = (2 * table.length) + 1;
		table = new Entry[res];
		Set<Entry<K, V>> set = entrySet();
		for(Entry<K,V> entry : set){
			put(entry.getKey(), entry.getValue());
		}
		
	}
	
	/**
	 * Removes the entry containing the given key
	 * 
	 * (remember that all objects have a hashCode method)
	 * 
	 * @param key, never null
	 * @return the value of the removed entry
	 */
	public V remove(Object key) {
		boolean flag = false;
		if(key == null){
			throw new NullPointerException();
		}
		int in = hashcomp(key);
		if(table[in] == null){
			return null;
		}
		while(flag){
			if(table[in] != null){
				++in;
				flag = false;
			}else if(table[in] == null){
				flag = true;
				return null;
			} 
		} if(table[in].getKey().equals(key) && !table[in].isAvailable()){
				table[in].setAvailable(true);
				size--;
				return table[in].getValue();
			}
		}
	
	
	/**
	 * Gets the value of the entry given a specific key
	 * 
	 * (remember that all objects have a hashCode method)
	 * 
	 * @param key, never null
	 * @return
	 */
	
	public V get(Object key) {
		if(key == null){
			throw new NullPointerException();
		}
		int in = hashcomp(key);
		if(in >= table.length){
			in = 0;
		}
		while(table[in] != null){
			++in;
		}
			if(table[in] == null){
				return null;
			} else if(table[in].getKey().equals(key) && !table[in].isAvailable()){
				return table[in].getValue();
			}
			
		}
			
	
	
	/**
	 * @param key, never null
	 * @return true if this table contains the given key, false otherwise
	 */
	public boolean containsKey(Object key) {
		
		int in = hashcomp(key);
		if(in >= table.length){
			in = 0;
		}
		boolean flag = false;
			while(flag){
				if(table[in] != null){
				++in;
				flag = false;
			}else if(table[in] == null){
					flag = true;
				}
			}
				if(table[in].getKey().equals(key)){
					return true;
				} else if(table[in] == null){
					return false;
				}
				else if (table[in].isAvailable()){
					return false;
				} else if (!table[in].isAvailable()){
					return true;
				}
				return false;
			}

	
	
	/**
	 * Clears this hashTable
	 */
	public void clear() {
		size = 0;
	}
	
	/**
	 * @return true if this hashtable is empty, false otherwise
	 */
	public boolean isEmpty() {
		if(size == 0){
			return true;
		}
		return false;
	}
	
	/**
	 * @return the value from this hashtable
	 */
	public Collection<V> values() {
		ArrayList<V> gathered = new ArrayList<V>();
		for(int i = 0; i < table.length; i++){
			if(table[i] != null && !table[i].isAvailable()) {
				gathered.add(table[i].getValue());
			}
		}
		return (Collection<V>) gathered;
	}
	
	/**
	 * @return the unique keys from this hashtable
	 */
	public Set<K> keySet() {
		HashSet<K> ks = new HashSet<K>();
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null & !table[i].isAvailable()) {
				ks.add(table[i].getKey());
			}
		}
		return ks;
	}
	
	/**
	 * @return the unique entries from this hashtable
	 */
	public Set<Entry<K, V>> entrySet() {
		HashSet<Entry<K, V>> ntry = new HashSet<Entry<K, V>>();
		for(int i = 0; i <table.length; i++){
			if(table[i] != null && !table[i].isAvailable())
				ntry.add(table[i]);
		}
		return ntry;
	}
	
	/**
	 * @return the size of this hashtable
	 */
	public int size() {
		return size;
	}

	/*
	 * Don't modify any code below this point
	 */
	
	public void setSize(int size) {
		this.size = size;
	}

	public Entry<K,V>[] getTable() {
		return table;
	}

	public void setTable(Entry<K,V>[] table) {
		this.table = table;
	}

	public double getMaxLoadFactor() {
		return MAX_LOAD_FACTOR;
	}

	public static class Entry<K,V> {
		private K key;
		private V value;
		private boolean available;
		
		public Entry(K key, V value) {
			this.setKey(key);
			this.setValue(value);
			this.setAvailable(true);
		}
		
		public void setKey(K key) {
			this.key = key;
		}
		
		public K getKey() {
			return this.key;
		}
		
		public void setValue(V value) {
			this.value = value;
		}
		
		public V getValue() {
			return this.value;
		}

		public boolean isAvailable() {
			return available;
		}

		public void setAvailable(boolean available) {
			this.available = available;
		}
	}
}
