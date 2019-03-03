//Do not edit this class, just add JavaDocs

/**
 *KEy value pair class with generics
 * @param <K> Key which is one type
 * @param <V> Value which is another type
 * @author Caleb Wagner
 * @version 1.0
 */
class TableEntry<K,V> {
	/**
	 *the key field
	 */
	private K key;
	/**
	 *value field
	 */
	private V value;

	/**
	 *Constructor for this key value pair
	 * @param key the key part being assigned
	 * @param value value that will be assigned
	 */
	public TableEntry(K key, V value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * return the key
	 * @return K type and returns the key
	 */
	public K getKey() {
		return key;
	}

	/**
	 * return the Value
	 * @return K tyoe and returns value
	 */
	public V getValue() {
		return value;
	}

	/**
	 *String repr
	 * @return String of the obj
	 */
	public String toString() {
		return key.toString()+":"+value.toString();
	}
}