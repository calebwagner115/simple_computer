import java.util.Hashtable;

/**
 *The structure that holds the values that correstpond to a variable (string)
 * storage is in hashtable
 * @param <T> type TableEntry<String,T>[] for the table
 *
 * @author Caleb Wagner
 * @version 1.0
 */
class SymbolTable<T> {
	/**
	 * the actual array that has a pair, the String is what gets hashed
	 */
	private TableEntry<String,T>[] storage;

	/**
	 *constructor that makes the size of the hashtable
	 * @param size size of the hashtable
	 */
	@SuppressWarnings("unchecked")
	public SymbolTable(int size) {
		//Create a hash table where the initial storage
		//is size and string keys can be mapped to T values.
		//You may assume size is >= 2

		//create new hashtable
		TableEntry<String,T>[] storage = new TableEntry[size];
		//set it to the field
		this.storage =storage;

	}

	/**
	 *returns the max capacity of the current hashtable
	 * @return int of max capacity
	 */
	public int getCapacity() {
		//return how big the storage is
		//O(1)
		return storage.length;
	}

	/**
	 *returns the current occupied spots in the hashtable, not inculding tombstones
	 * @return int size of occupuied indexes
	 */
	public int size() {
		//return the number of elements in the table
		//O(1)
		int i;
		int count=0;

		//for all spots in the hashtable
		for (i=0;i<getCapacity();i++){
			//if the index is not null or a tombstone, add one to the counter
			if( storage[i]!=null && storage[i].getKey()!="\0"){
				count++;
			}
		}
		return count;
	}


	/**
	 * Adds a key (String) and Value pair to the hashable
	 * if the occupied spaces becomes at or greater than 80%, capacity doubles
	 * @param k  a string that is the hashed key that determines
	 * @param v the value that wants to be stored of type T
	 */
	public void put(String k, T v) {
		//Place value v at the location of key k.
		//Use linear probing if that location is in use.
		//You may assume both k and v will not be null.

		//Hint: Make a TableEntry to store in storage
		//and use the absolute value of k.hashCode() for
		//the probe start.

		//If the key already exists in the table
		//replace the current value with v.

		//If the key isn't in the table and the table
		//is >= 80% full, expand the table to twice
		//the size and rehash

		//Worst case: O(n), Average case: O(1)


		//make table larger if need be, will grow x2 if more than or '=' to 80% filled
		if (((size()+1.0)/getCapacity()*1.0) >= 0.8){
			//call rehash function
			rehash(getCapacity()*2);
		}

		//absolute value of the hashcode ( which can return (-) num)
		int hash = Math.abs(k.hashCode());
		//make it a valid index
		int index = hash % getCapacity();

		//for the capacity  (at max)
		for (int i=0; i<getCapacity();i++){
			//if the spot is unoccupied or it has the same key, replace it with the new pair
			//the index starts at the desired area
			if (storage[index%getCapacity()] != null && storage[index%getCapacity()].getKey().equals(k)){
				storage[index%getCapacity()] = new TableEntry<>(k,v);
				return;
			}

			//if the new spot is a tombstone and/ot it is empty replace it
			if(storage[index%getCapacity()]==null || isTombstone(index%getCapacity()) ){
				storage[index%getCapacity()] = new TableEntry<>(k,v);
				return;
			}

			//increase the starting index by one for Linear Probing
			else{
				index++;
			}
		}
	}

	/**
	 *removes the key value pair from the hashtable,
	 * (marked as tombstone first; unless replaced or rehashed)
	 * @param k String of the key pair desired to remove
	 * @return returns the correstponding value pair that was removed
	 */
	public T remove(String k) {
		//Remove the given key (and associated value)
		//from the table. Return the value removed.
		//If the value is not in the table, return null.

		//Hint 1: Remember to leave a tombstone!
		//Hint 2: Does it matter what a tombstone is?
		//   Yes and no... You need to be able to tell
		//   the difference between an empty spot and
		//   a tombstone and you also need to be able
		//   to tell the difference between a "real"
		//   element and a tombstone.

		//Worst case: O(n), Average case: O(1)
		T temp;

		//get the hashcode of the given key int the hashtable
		int index = Math.abs(k.hashCode()%getCapacity());

		//if the area isnt occupied, null
		if (storage[index] == null){
			return null;
		}


		//if the first guess for the index is correct, set it tombstone and return the value
		if(storage[index].getKey().equals(k)){
			temp = storage[index].getValue();
			storage[index] = new TableEntry<>("\0",temp);
			return temp;
		}

		//if the first spot isnt correct search until null or searched all
		for (int i=0;i<getCapacity();i++){

			if (storage[index%getCapacity()].getKey().equals(k)){
				temp = storage[index].getValue();
				storage[index] = new TableEntry<>("\0",temp);
				if (size()/getCapacity() <= 0.2){
					rehash(getCapacity()/2);
				}
				return temp;
			}
			//go to next index for linear probing
			index++;
		}

		if (size()/getCapacity() <= 0.2){
			rehash(getCapacity()/2);
		}
		//if reaches this, it doesnt exist on the hashtable
		return null;
	}

	/**
	 *gets the value stored at the given k pair
	 * @param k the key that is used for the hash table
	 * @return the corresponding value for the pair
	 */
	public T get(String k) {
		//Given a key, return the value from the table.

		//If the value is not in the table, return null.

		//Worst case: O(n), Average case: O(1)
		int index = Math.abs(k.hashCode()%getCapacity());

		//if empty return null
		if (storage[index] == null){
			return null;
		}

		//if the first index is the key
		if(storage[index].getKey().equals(k)){
			return storage[index].getValue();
		}

		//search the table in a linear probing format
		for (int i=0;i<getCapacity();i++){

			if (storage[index%getCapacity()]==null){
				return null;
			}
			//if found return value
			if (storage[index%getCapacity()].getKey().equals(k)){
				return storage[index].getValue();
			}
			index++;
		}
		//return null if it isnt found
		return null;
	}

	/**
	 *checks if the given index is a tombstone
	 * @param index int index desired to see if it is a tombsone
	 * @return boolean if the given index is a tombstone
	 */
	public boolean isTombstone(int index) {
		//this is a helper method needed for printing

		//return whether or not there is a tombstone at the
		//given index

		//O(1)
		if(storage[index] != null && storage[index].getKey().equals("\0")){
			return true;
		}
		return false;
	}

	/**
	 * rehashes the table larger or smaller
	 * @param size the capacity size of the hashable desired
	 * @return boolean of weather it was successful or not
	 */
	@SuppressWarnings("unchecked")
	public boolean rehash(int size) {
		//Increase or decrease the size of the storage,
		//rehashing all values.

		//If the new size won't fit all the elements,
		//return false and do not rehash. Return true
		//if you were able to rehash.

		//if the size is smaller than the actual number of kv pairs then return false
		if(size()>size){
			return false;
		}

		//for shrinking the array
		if (size<getCapacity()){
			TableEntry<String,T>[] newstorage = new TableEntry[size];
			int rehashindex=0;
			//for all spaces in the old hashtable
			for (int i=0;i<getCapacity();i++){

				//if null or tombstone, ignore
				if (this.storage[i] == null || isTombstone(i)){
					continue;
				}

				//get a rehashed key
				rehashindex = Math.abs(this.storage[i].getKey().hashCode() %size);

				//if the new space is not open
				if(newstorage[rehashindex]!=null  ){

					//go through all the linear spacing probing till one is found
					while(newstorage[rehashindex% newstorage.length]!=null ){
						rehashindex++;
					}
					newstorage[rehashindex] = this.storage[i];
				}

				//if the space is open, fill it
				else{
					newstorage[rehashindex] = this.storage[i];
				}
			}
			//assign the new storage
			this.storage = newstorage;
			return true;
		}

		TableEntry<String,T>[] newstorage = new TableEntry[size];
		int rehashindex=0;

		//for all spaces in the old hashtable
		for (int i=0;i<getCapacity();i++){

			//if null or tombstone, ignore
			if (this.storage[i] == null || isTombstone(i)){
				continue;
			}

			//get a rehashed key
			rehashindex = Math.abs(this.storage[i].getKey().hashCode() %size);

			//if the new space is not open
			if(newstorage[rehashindex]!=null  ){

				//go through all the linear spacing probing till one is found
				while(newstorage[rehashindex% newstorage.length]!=null ){
					rehashindex++;
				}
				newstorage[rehashindex] = this.storage[i];
			}

			//if the space is open, fill it
			else{
				newstorage[rehashindex] = this.storage[i];
			}
		}
		//assign the new storage
		this.storage = newstorage;
		return true;
	}

	/**
	 *testing
	 * @param args lines of codez
	 */
	public static void main(String[] args) {
		//main method for testing, edit as much as you want
		SymbolTable<String> st1 = new SymbolTable<>(10);
		SymbolTable<Integer> st2 = new SymbolTable<>(5);

		if(st1.getCapacity() == 10 && st2.getCapacity() == 5 && st1.size() == 0 && st2.size() == 0) {
			System.out.println("Yay 1");
		}

		st1.put("a","apple");
		st1.put("b","banana");
		st1.put("banana","b");
		st1.put("b","butter");

		if(st1.toString().equals("a:apple\nb:butter\nbanana:b") && st1.toStringDebug().equals("[0]: null\n[1]: null\n[2]: null\n[3]: null\n[4]: null\n[5]: null\n[6]: null\n[7]: a:apple\n[8]: b:butter\n[9]: banana:b")) {
			System.out.println("Yay 2");
		}

		if(st1.getCapacity() == 10 && st1.size() == 3 && st1.get("a").equals("apple") && st1.get("b").equals("butter") && st1.get("banana").equals("b")) {
			System.out.println("Yay 3");
		}

		st2.put("a",1);
		st2.put("b",2);
		st2.put("e",3);
		st2.put("y",4);

		if(st2.toString().equals("e:3\ny:4\na:1\nb:2") && st2.toStringDebug().equals("[0]: null\n[1]: e:3\n[2]: y:4\n[3]: null\n[4]: null\n[5]: null\n[6]: null\n[7]: a:1\n[8]: b:2\n[9]: null")) {
			System.out.println("Yay 4");
		}

		if(st2.getCapacity() == 10 && st2.size() == 4 && st2.get("a").equals(1) && st2.get("b").equals(2) && st2.get("e").equals(3) && st2.get("y").equals(4)) {
			System.out.println("Yay 5");
		}

		if(st2.remove("e").equals(3) && st2.getCapacity() == 10 && st2.size() == 3 && st2.get("e") == null && st2.get("y").equals(4)) {
			System.out.println("Yay 6");
		}

		if(st2.toString().equals("y:4\na:1\nb:2") && st2.toStringDebug().equals("[0]: null\n[1]: tombstone\n[2]: y:4\n[3]: null\n[4]: null\n[5]: null\n[6]: null\n[7]: a:1\n[8]: b:2\n[9]: null")) {
			System.out.println("Yay 7");
		}

		if(st2.rehash(2) == false && st2.size() == 3 && st2.getCapacity() == 10) {
			System.out.println("Yay 8");
		}

		if(st2.rehash(4) == true && st2.size() == 3 && st2.getCapacity() == 4) {
			System.out.println("Yay 9");
		}

		if(st2.toString().equals("y:4\na:1\nb:2") && st2.toStringDebug().equals("[0]: null\n[1]: y:4\n[2]: a:1\n[3]: b:2")) {
			System.out.println("Yay 10");
		}

		SymbolTable<String> st3 = new SymbolTable<>(2);
		st3.put("a","a");
		st3.remove("a");

		if(st3.toString().equals("") && st3.toStringDebug().equals("[0]: null\n[1]: tombstone")) {
			st3.put("a","a");
			if(st3.toString().equals("a:a") && st3.toStringDebug().equals("[0]: null\n[1]: a:a") && st3.toStringDebug().equals("[0]: null\n[1]: a:a")) {
				System.out.println("Yay 11");
			}
		}
	}

	//--------------Provided methods below this line--------------
	//Add JavaDocs, but do not change the methods.

	/**
	 *string representation of obj
	 * @return String of obj
	 */
	public String toString() {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < storage.length; i++) {
			if(storage[i] != null && !isTombstone(i)) {
				s.append(storage[i] + "\n");
			}
		}
		return s.toString().trim();
	}

	/**
	 *String debug
	 * @return
	 */
	public String toStringDebug() {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < storage.length; i++) {
			if(!isTombstone(i)) {
				s.append("[" + i + "]: " + storage[i] + "\n");
			}
			else {
				s.append("[" + i + "]: tombstone\n");
			}

		}
		return s.toString().trim();
	}
}