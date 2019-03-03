import java.util.Iterator;

/**
 * is a running stack within out program, first in first out
 * @param <T> the generic type of the node
 * @author Caleb Wagner
 * @version 1.0
 */
class ProgramStack<T> implements Iterable<T> {
	// You'll want some instance variables here
	/**
	 *size of the stack
	 */
	int size;

	/**
	 *pointer to the head of the stack
	 */
	Node<T> head;

	/**
	 *pointer to the tail of the stack, good for appending Linked Lists
	 */
	Node<T> tail;

	/**
	 * constructor initializes stack
	 */
	public ProgramStack() {
		//setup what you need
		size =0;
	}

	/**
	 * push an item onto the top of the stack
	 * @param item
	 */
	public void push(T item) {
		//push an item onto the stack
		//you may assume the item is not null
		//O(1)

		//if stack is empty
		if(head==null){
			head = new Node<T>(item);
			tail = head;
			size++;
			return;
		}

		//append on the end of tail and move the pointers for a doubly linked list
		tail.setNext( new Node<>(item));
		tail.getNext().setPrev(tail);
		tail = tail.getNext();
		size++;

	}

	/**
	 * remove the top of the stack and return the value
	 * @return the value of node that was removed from stack
	 */
	public T pop() {
		//pop an item off the stack
		//if there are no items on the stack, return null
		//O(1)

		//if you try to pop an empty stack
		if (size==0){
			return null;
		}
		//the tail is going to be removed but we need to return the value of it though
		Node<T> temp = tail;

		//is the head is the only node in the LL, make the LL empty and return value
		if (head.getNext()==null){
			temp = head;
			head = null;
			tail =null;
			size--;
			return temp.getValue();

		}

		//set the next item in the stack's next to null
		tail.getPrev().setNext(null);
		//set the tail to the current tail's previous
		tail = tail.getPrev();

		//decrement size
		size--;

		//return the popped value
		return temp.getValue();
	}

	/**
	 *look at the top of the stack and return that value without popping it
	 * @return the top of the stacks value
	 */
	public T peek() {
		//return the top of the stack (but don't remove it)
		//if there are no items on the stack, return null
		//O(1)

		//if empty LL
		if(size ==0){
			return null;
		}
		//return top of stack value
		return tail.getValue();
	}

	/**
	 *String method
	 * @return String Repr
	 */
	public String toString() {
		//Create a string of the stack where each item
		//is separated by a space. The top of the stack
		//should be shown to the right and the bottom of
		//the stack on the left.
		
		//Hint: Reuse the provided code from another class
		//instead of writing this yourself!
		
		//O(n)

		String s = "";
		Node<T> temp = head;

		//empty ll return ""
		if (temp ==null){
			return s;
		}

		//get the heads string and add it to string
		s += temp.getValue().toString();
		temp = temp.getNext();

		//if only 1 item is in stack
		if(size ==1){
			return s;
		}

		//loop through LL getting all the toStrings and appending them
		while( temp.getNext() !=null){
			s+= " " + temp.getValue().toString();
			temp = temp.getNext();
		}
		s+= " " + temp.getValue().toString();

		//return tostring
		return s;
	}

	/**
	 *empty the stack
	 */
	public void clear() {
		//remove everything from the stack
		//O(1)
		head = null;
		tail = null;
	}

	/**
	 *return size of the stack, important to keep size in a Linked List
	 * @return int Size of the stack
	 */

	public int size() {
		//return the number of items on the stack
		//O(1)
		return size;
	}

	/**
	 * checks to see if the stack is empty
	 * @return boolean whether stack is empty or not
	 */
	public boolean isEmpty() {
		//return whether or not the stack is empty
		//O(1)

		if (head==null){
			return true;
		}
		return false;
	}

	/**
	 *turns the stack into a copy of an array and returns it
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object[] toArray() {
		//Return an array representation of the stack.
		//The top of the stack should be element 0
		//in the array.
		
		//O(n)
		//gets the tail
		Node <T>currNode = tail;
		//creates an array so of stack size
		T[] lltoarray = (T[]) new Object[size];
		int index = 0;

		//for all in the size of the stack
		for (int i=0;i<size;i++){
			//get value of current node and assign it in the array
			lltoarray[i] = currNode.getValue();
			//go to next node
			currNode = currNode.getPrev();
		}
		//return array
		return lltoarray;
	}

	/**
	 *The Iterator to make it iterable
	 * @return iterator obj
	 */
	public Iterator<T> iterator() {
		//Return an iterator that traverses from
		//the top of the stack to the bottom of
		//the stack.

		//ananymous class
		return new Iterator<T>() {
			/**
			 *index it is before
			 */
			private int index = size;
			/**
			 * start at the top
			 */
			Node<T>temp = tail;

			//The iterator's hasNext() and next() methods
			//must both be O(1)

			/**
			 *return next in the array
			 * @return next item in the stack
			 */
			@Override
			public T next() {
				if (temp==null){
					throw new NullPointerException("null");
				}

				if (temp ==tail){
					temp = temp.getPrev();
					return tail.getValue();
				}
				T ans = temp.getValue();
				temp = temp.getPrev();
				return ans;
			}

			/**
			 *does the next index exist
			 * @return boolean of next index exist
			 */
			@Override
			public boolean hasNext() {
				return temp != null;
			}

		};
		//The iterator's hasNext() and next() methods
		//must both be O(1)
		
		//next() should throw a NullPointerException
		//if you try to use next when there are no
		//more items
		

	}

	/**
	 *tests
	 * @param args test lines
	 */
	public static void main(String[] args) {
		ProgramStack<String> s1 = new ProgramStack<>();
		s1.push("a");
		s1.push("b");
		
		ProgramStack<Integer> s2 = new ProgramStack<>();
		s2.push(1);
		s2.push(2);
		s2.push(3);
		
		if(s1.toString().equals("a b") && s1.toArray()[0].equals("b") && s1.toArray()[1].equals("a") && s1.toArray().length == 2) {
			System.out.println("Yay 1");
		}
		
		if(s1.peek().equals("b") && s2.peek().equals(3) && s1.size() == 2 && s2.size() == 3) {
			System.out.println("Yay 2");
		}
		
		if(s1.pop().equals("b") && s2.pop().equals(3) && s1.size() == 1 && s2.size() == 2) {
			System.out.println("Yay 3");
		}
		
		if(s1.toString().equals("a") && s1.peek().equals("a") && s2.peek().equals(2) && s1.pop().equals("a") && s2.pop().equals(2) && s1.size() == 0 && s2.size() == 1) {
			System.out.println("Yay 4");
		}
		
		if(s1.toString().equals("") && s1.peek() == null && s2.peek().equals(1) && s1.pop() == null && s2.pop().equals(1) && s1.size() == 0 && s2.size() == 0) {
			System.out.println("Yay 5");
		}
		
		s2.push(10);
		s2.push(20);
		s2.push(30);
		if(s1.isEmpty() && s1.toArray().length == 0 && !s2.isEmpty()) {
			s2.clear();
			if(s2.isEmpty()) {
				System.out.println("Yay 6");
			}
		}
		
		ProgramStack<Integer> s3 = new ProgramStack<>();
		s3.push(3);
		s3.push(2);
		s3.push(1);

		int i = 1;
		for(Integer item : s3) {
			if(i == item) System.out.println("Yay " + (6+i));
			else
				System.out.println(item);
			i++;
		}
	}
}