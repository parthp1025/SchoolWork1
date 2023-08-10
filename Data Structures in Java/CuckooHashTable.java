import java.util.*;

/**
 * 
 * @author Kenny Wu
 * @author Parth Patel
 *
 * @param <K> Key
 * @param <V> Value
 */
public class CuckooHashTable<K, V> {
	private final double MAX_LOAD_FACTOR = 0.51;
	private final int INITIAL_TABLE_SIZE = 11;
	private MapEntry<K,V>[] table, table2;
	private int size;
	private static int check;
	
	@SuppressWarnings("unchecked")
	public CuckooHashTable() {
		table = new MapEntry[INITIAL_TABLE_SIZE];
		table2 = new MapEntry[INITIAL_TABLE_SIZE];
		size = 0;
	}
	
	public void put(K key, V value){
		if(((double)size/table.length) >= MAX_LOAD_FACTOR)
		{
			regrow();
		}
		if(((double)size/table2.length) >= MAX_LOAD_FACTOR)
		{
			regrow2();
		}
		
		int hashKey = Math.abs(key.hashCode());
		//compression
		int index = hashKey % table.length;
		//int index2 = (hashKey/table2.length) % table2.length;
		//int index2 = (hashKey + 1) % table2.length;
		int index2 = hashKey % (table2.length/2);
				
		if((table[index] == null) || (table[index].isRemoved()))
		{
			table[index] = new MapEntry<K,V>(key, value);
			size++;
			check = 0;
		}
		else if((table2[index2] == null) || (table2[index2].isRemoved()))
		{
			table2[index2] = table[index];
			table[index] = new MapEntry<K,V>(key, value);
			size++;
		}
		else
		{	
			if(check < 3)
			{
				//rehash the table
				regrow2();
				check++;
				put(key, value);
			}
			else
			{
				//the oldest key get replaced
				table2[index2] = table[index];
				table[index] = new MapEntry<K,V>(key, value);
			}
		}
		
		/*else
		{
			inc = index;
			//find the index in table 1 that is empty
			while(table[inc] != null)
			{
				if(inc == table.length-1)
				{
					inc = 0;
				}
				else
				{
					inc++;
				}
			}
			//table[inc] = table2[index2];
			table2[index2] = table[index];
			table[index] = new MapEntry<K,V>(key, value);
		}*/
			
		//size++;
	}
	
	@SuppressWarnings("unchecked")
	private void regrow()
	{
		MapEntry<K,V>[] temp = table;
		
		table = new MapEntry[(table.length * 2) + 1];
		size = 0;
		
		for (int i = 0; i < temp.length; i++)
		{
			if (temp[i] != null)
			{
				put(temp[i].getKey(), temp[i].getValue());
			}
		}		
	}
	
	@SuppressWarnings("unchecked")
	private void regrow2()
	{
		MapEntry<K,V>[] temp = table2;
		//size = 0;
		
		table2 = new MapEntry[(table2.length * 2) + 1];
		
		for (int i = 0; i < temp.length; i++)
		{
			if (temp[i] != null)
			{
				put(temp[i].getKey(), temp[i].getValue());
			}
		}		
	}
	
	/**
	 * Get the value for the key.
	 * 
	 * If multiple value exist in that key, get the oldest.
	 * 
	 * @param key
	 * @return return the value associated with the key
	 */
	public V value(K key){
		int hashKey = Math.abs(key.hashCode());
		int index = hashKey % table.length;
		//int index2 = (hashKey/table2.length) % table2.length;
		int index2 = hashKey % (table2.length/2);
		
		if(table[index].getKey() == key)
		{
			return table[index].getValue();
		}
		else if(table2[index2].getKey() == key)
		{
			return table2[index2].getValue();
		}
		
		return null;
	}
	
	/**
	 * Remove the key, and get the value associated to that key
	 * 
	 * @param key
	 * @return value associated to the key
	 */
	public V remove(K key){
		int hashKey = Math.abs(key.hashCode());
		int index = hashKey % table.length;
		//int index2 = (hashKey/table2.length) % table2.length;
		int index2 = hashKey % (table2.length/2);
		
		if(table[index].getKey() == key)
		{
			table[index].setRemoved(true);
			size--;
			return table[index].getValue();
		}
		else if(table2[index2].getKey() == key)
		{
			table2[index2].setRemoved(true);
			size--;
			return table2[index2].getValue();
		}
		
		return null;
	}
	
	public boolean contains(K key){
		int hashKey = Math.abs(key.hashCode());
		int index = hashKey % table.length;
		//int index2 = (hashKey/table2.length) % table2.length;
		int index2 = hashKey % (table2.length/2);
		
		if(table[index].getKey() == key)
		{
			return true;
		}
		else if(table2[index2].getKey() == key)
		{
			return true;
		}
		
		return false;
	}
	
	public Set<MapEntry<K,V>> entrySet(){
		HashSet<MapEntry<K,V>> set = new HashSet<MapEntry<K,V>>();
		
		for(MapEntry<K,V> data : table)
		{	
			if(data != null)
			{
				set.add(data);
			}
		}
		
		for(MapEntry<K,V> data2 : table2)
		{	
			if(data2 != null)
			{
				set.add(data2);
			}
		}
		
		return set;
	}
	
	@SuppressWarnings("unchecked")
	public void clear(){
		for(int i = 0; i < table.length; i++)
		{
			table[i] = null;
		}
		
		for(int i = 0; i < table2.length; i++)
		{
			table2[i] = null;
		}
		
		table = new MapEntry[INITIAL_TABLE_SIZE];
		table2 = new MapEntry[INITIAL_TABLE_SIZE];
		size = 0;
	}
	
	public int size(){
		return size;
	}
	
	public MapEntry<K, V>[] getTable() {
		return table;
	}
	
	public void setTable(MapEntry<K, V>[] table) {
		this.table = table;
	}
	
	public MapEntry<K, V>[] getTable2() {
		return table2;
	}
	
	public void setTable2(MapEntry<K, V>[] table2) {
		this.table2 = table2;
	}
}
