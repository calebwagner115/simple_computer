//Do not edit this class, just add JavaDocs
//You may edit the main method for testing things if you want
//but do not change the actual class structure.

/**
 * Stucture that builds up Linked List Structures
 * @param <T> Generic Type  of the value stored in the Node
 * @author Caleb Wagner
 * @version 1.0
 */
class Node<T> {
	/**
	 * value of type T that node contains
	 */
	private T value;

	/**
	 * the next node in the List
	 */
	private Node<T> next;

	/**
	 * Previous node in the List
	 */
	private Node<T> prev;

	/**
	 * Constuctor for a Node
	 * @param value the Value of the node you would like stored ( generic)
	 */
	public Node(T value) {
		//assign value for node
		this.value = value;
	}

	/**
	 * get the stored value
	 * @return generic type return of stored value
	 */
	public T getValue() {
		return value;
	}

	/**
	 * set a new value to a given node
	 * @param value generic type assignment to node
	 */
	public void setValue(T value) {
		this.value = value;
	}

	/**
	 * get next value in List
	 * @return Node that is next in the List
	 */
	public Node<T> getNext() {
		return this.next;
	}

	/**
	 * set given node to be the next in the Linked List of 'this'
	 * @param next set a node type t
	 */
	public void setNext(Node<T> next) {
		this.next = next;
	}

	/**
	 * get prev value in List
	 * @return Node that is prev in the Lsit
	 *
	 */
	public Node<T> getPrev() {
		return this.prev;
	}

	/**
	 * set given node to be the prev in the Linked List of 'this'
	 * @param prev set a node type t
	 */
	public void setPrev(Node<T> prev) {
		this.prev = prev;
	}

	/**
	 * string repr of of all the nodes
	 * @param head head of the linked list
	 * @return String of all the nodes
	 */
	public static String listToString(Node<?> head) {
		StringBuilder ret = new StringBuilder();
		Node<?> current = head;
		while(current != null) {
			ret.append(current.value);
			ret.append(" ");
			current = current.getNext();
		}
		return ret.toString().trim();
	}

	/**
	 *
	 * string repr of of all the nodes Backwards
	 * @param head head of the LL
	 * @return
	 */
	public static String listToStringBackward(Node<?> head) {
		Node<?> current = head;
		StringBuilder ret = new StringBuilder();


		while(current.getNext() != null) {
			ret.insert(0,current.value.toString() + " ");
			current = current.getNext();
		}
		ret.insert(0,current.value.toString() + " ");

		return ret.toString().trim();
	}

	/**
	 *String repr of node
	 * @return String of the Node
	 */
	public String toString(){
		return value.toString();
	}

	/**
	 *Testing
	 * @param args Testing line of codez
	 */
	public static void main(String[] args) {
		//main method for testing, edit as much as you want
		
		//make nodes
		Node<String> n1 = new Node<>("A");
		Node<String> n2 = new Node<>("B");
		Node<String> n3 = new Node<>("C");
		
		//connect forward references
		n1.setNext(n2);
		n2.setNext(n3);
		
		//connect backward references
		n3.setPrev(n2);
		n2.setPrev(n1);
		
		//print forward and backward
		System.out.println(Node.listToString(n1));
		System.out.println(Node.listToStringBackward(n1));
	}
}