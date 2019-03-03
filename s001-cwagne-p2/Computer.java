//These are all the imports you are allowed, don't add any more!
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 * computer class that:
 * 1. opens a file and reads the characters
 * 2. maintains the nodes and prefroms the correct opperations
 * 3. prints out each step by use of enter key
 * @author Caleb Wagner
 * @version 1.0
 */
class Computer {

	/**
	 * Takes an input file and turns the strings in the file to a linked list
	 * @param filename the file that will be turned into a linked list
	 * @return returns the head of the linked list
	 * @throws IOException Exception that occurs when the file does not exists
	 */
	public static Node<String> fileToNodeQueue(String filename) throws IOException {
		//given a file name, open that file in a scanner and create a queue of nodes
		//the head of the queue of nodes should be the start of the queue
		//the values in the nodes should be the strings read in each time you call
		//next() on the scanner

		//try to open the file
		try {
			Scanner sc = new Scanner(new File(filename));

			//set the head
			Node<String> head = new Node<>(sc.next());
			Node<String> prev = head;

			//link all of the Nodes while the scanner has more Strings
			while (sc.hasNext()){
				Node<String> temp = new Node<>(sc.next());
				temp.setPrev(prev);
				prev.setNext(temp);
				prev = prev.getNext();

			}

			//return the head of the Linked List
			return head;
		}
		//input output file error
		catch (IOException e){
			System.out.println(String.format("%s",e));

		}
		//if there is no more, return null
		return null;
	}

	/**
	 * goes node by node, seeing if there is an opperation that needs to be done, if if isnt true, add it to the stack
	 * @param input the  doubly linked list that is the queue
	 * @param numSymbols amount of
	 * @return returns the remaining queue of nodes
	 */
	public Node<String> process(Node<String> input, int numSymbols) {
		//Given an input queue of symbols process the number of symbols
		//specified (numSymbols) and update the progStack and symbols
		//variables appropriately to reflect the state of the "computer"
		//(see below the "do not edit" line for these variables).
		
		//Return the remaining queue items.
		
		//For example, if input is the head of a linked list 3 -> 2 -> +
		//and numSymbols=2, you would push 3 and push 2, then return the linked
		//list with just the + node remaining.


			//		//if the LL is smaller than symbols wanted, throw exception
			//		if (numSymbols > progStack.size() || numSymbols < 0){
			//			throw new RuntimeException(String.format("Size of requested numbers: %d exceeds possible size which is %d",progStack.size(),numSymbols));
			//		}

		//go through all the number of  symbols and see if the top of the stack
		for (int i=0; i<numSymbols;i++){
			int a,b,value;
			Object temp1, temp2;


			//check each symbol and do following calculation
			switch (input.getValue()){

				//if addition
				case "+":
					//pop the two items you want to add
					temp1 = progStack.pop();
					temp2 =  progStack.pop();

					//see if the node in the stack is variable or int
					if (temp1 instanceof String){
						a = symbols.get((String) temp1);
					}
					else{
						a = (int) temp1;
					}

					if (temp2 instanceof String){
						b = symbols.get((String) temp2);
					}
					else{
						b = (int) temp2;
					}

					//push the sum onto the stack
					progStack.push((Integer) a+b);
					break;

				case "-":
					//pop the two items you want to subtract
					temp1 = progStack.pop();
					temp2 =  progStack.pop();

					//see if the node in the stack is variable or int
					if (temp1 instanceof String){
						a = symbols.get((String) temp1);
					}
					else{
						a = (int) temp1;
					}

					if (temp2 instanceof String){
						b = symbols.get((String) temp2);
					}
					else{
						b = (int) temp2;
					}

					//push the difference onto the stack
					progStack.push((Integer) (b - a));
					break;

				case "*":
					//pop the two items you want to multiply
					temp1 = progStack.pop();
					temp2 =  progStack.pop();

					//see if the node in the stack is variable or int
					if (temp1 instanceof String){
						a = symbols.get((String) temp1);
					}
					else{
						a = (int) temp1;
					}

					if (temp2 instanceof String){
						b = symbols.get((String) temp2);
					}
					else{
						b = (int) temp2;
					}
					//push the product onto the stack
					progStack.push((Integer) a*b);
					break;

				case "/":
					//pop the two items you want to divide
					temp1 = progStack.pop();
					temp2 =  progStack.pop();

					//see if the node in the stack is variable or int
					if (temp1 instanceof String){
						a = symbols.get((String) temp1);
					}
					else{
						a = (int) temp1;
					}

					if (temp2 instanceof String){
						b = symbols.get((String) temp2);
					}
					else{
						b = (int) temp2;
					}
					//push the quotient onto the stack
					progStack.push((Integer) b / (Integer) a);
					break;

				case "=":
					//see if the top of the stack
					temp1 = progStack.pop();
					if (temp1 instanceof String){
						a = symbols.get((String) temp1);
					}

					else{
						a = (int) temp1;
					}

					//pop the stack and set that string '=' to the int popped before
					String variable =(String) progStack.pop();
					symbols.put(variable,a);
					break;

				case "+=":
					//pop the two items you want to add and to the variable itself
					temp1 = progStack.pop();
					temp2 =  progStack.pop();

					//if the top of the stack is a string, find a corresponding variable
					if (temp1 instanceof String){
						a = symbols.get((String) temp1);
					}
					else{
						a = (int) temp1;
					}
					//if the top of the stack is a string, find a corresponding variable
					if (temp2 instanceof String){
						b = symbols.get((String) temp2);
					}
					else{
						b = (int) temp2;
					}

					//add the int popped and the current value of that variable
					symbols.put((String) temp2,a+b);
					break;

				case "-=":
					//pop the two items you want to multiply
					temp1 = progStack.pop();
					temp2 =  progStack.pop();
					//if the top of the stack is a string, find a corresponding variable
					if (temp1 instanceof String){
						a = symbols.get((String) temp1);
					}
					else{
						a = (int) temp1;
					}
					//if the top of the stack is a string, find a corresponding variable
					if (temp2 instanceof String){
						b = symbols.get((String) temp2);
					}
					else{
						b = (int) temp2;
					}
					//subtract the int popped and the current value of that variable
					symbols.put((String) temp2,b-a);
					break;

				case "*=":
					//pop the two items you want to multiply
					temp1 = progStack.pop();
					temp2 =  progStack.pop();
					//if the top of the stack is a string, find a corresponding variable
					if (temp1 instanceof String){
						a = symbols.get((String) temp1);
					}
					else{
						a = (int) temp1;
					}
					//if the top of the stack is a string, find a corresponding variable
					if (temp2 instanceof String){
						b = symbols.get((String) temp2);
					}
					else{
						b = (int) temp2;
					}

					//multiply the int popped and the current value of that variable
					symbols.put((String) temp2,a*b);
					break;

				case "/=":
					//pop the two items you want to multiply
					temp1 = progStack.pop();
					temp2 =  progStack.pop();

					//if the top of the stack is a string, find a corresponding variable
					if (temp1 instanceof String){
						a = symbols.get((String) temp1);
					}
					else{
						a = (int) temp1;
					}
					//if the top of the stack is a string, find a corresponding variable
					if (temp2 instanceof String){
						b = symbols.get((String) temp2);
					}
					else{
						b = (int) temp2;
					}

					//divide the int popped and the current value of that variable
					symbols.put((String) temp2,b/a);
					break;

				case "print":
					temp1 = progStack.pop();

					//print all remaining in the stack
					while (temp1 != null) {

						if (temp1 instanceof String) {
							a = symbols.get((String) temp1);
						} else {
							a = (int) temp1;
						}
						System.out.println(a);
						break;
					}
					break;

				//if the next up node is a string or an integer
				default:
					//try and put it as an int
					try {
						progStack.push((Integer) Integer.parseInt(input.getValue()));
					}
					//if not it is a String
					catch(Exception e){
						progStack.push(input.getValue());
					}
			}
			//get the next node
			input = input.getNext();
		}

		return input;
	}

	/**
	 * test method to replace MAin
	 */
	public static void testMain() {
		//edit this as much as you want, if you use main without any arguments,
		//this is the method that will be run instead of the program
		//System.out.println("You need to put test code in testMain() to run Computer with no parameters.");

		//make a computer obj
		Computer c = new Computer();
		//try and open the file
		try {
			c.runProgram("prog/sample2.txt",true);

		}

		//file not found
		catch (IOException e){
			System.out.println(String.format("%s",e));
			return;
		}

	}
	
	//--------------------DON'T EDIT BELOW THIS LINE--------------------
	//----------------------EXCEPT TO ADD JAVADOCS----------------------

	/**
	 * all the integer operations that we need to test for
	 */
	public static final String[] INT_OPS = {"+","-","*","/"};

	/**
	 * all the variable assignment opperations
	 */
	public static final String[] ASSIGN_OPS = {"=","+=","-=","*=","/="};

	/**
	 * the program stack we have that we can push and pop etc...
	 */
	public ProgramStack<Object> progStack = new ProgramStack<>();

	/**
	 * the hash table that contains all the variables that are paired to a value
	 */
	public SymbolTable<Integer> symbols = new SymbolTable<>(5);

	/**
	 * command line arguements. this takes them in and prints out the computer output the way we need
	 * @param args the array of commandline arguments
	 */
	public static void main(String[] args) {
		//this is not a testing main method, so don't edit this
		//edit testMain() instead!
		
		if(args.length == 0) {
			testMain();
			return;
		}
		
		if(args.length != 2 || !(args[1].equals("false") || args[1].equals("true"))) {
			System.out.println("Usage: java Computer [filename] [true|false]");
			System.exit(0);
		}
		
		try {
			(new Computer()).runProgram(args[0], args[1].equals("true"));
		}
		catch(IOException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}
	
	//provided, don't change this
	public void runProgram(String filename, boolean debug) throws IOException {
		Node<String> input = fileToNodeQueue(filename);
		System.out.println("\nProgram: " + Node.listToString(input));

		if(!debug) {
			while(input != null) {
				input = process(input, 1);
			}
		}
		else {
			Scanner s = new Scanner(System.in);
			for(int i = 1; input != null; i++) {
				System.out.println("\n######### Step " + i + " ###############\n");
				System.out.println("----------Step Output----------");
				input = process(input, 1);
				System.out.println("----------Symbol Table---------");
				System.out.println(symbols);
				System.out.println("----------Program Stack--------");
				System.out.println(progStack);
				if(input != null) {
					System.out.println("----------Program Remaining----");
					System.out.println(Node.listToString(input));
				}
				System.out.println("\nPress Enter to Continue");
				s.nextLine();
			}
		}
	}
}