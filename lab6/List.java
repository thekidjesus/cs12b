//Jesus Munoz jmunoz10 12M
//Patrick Stumps pstumps@ucsc.edu 12M
//List.java

class List<T> implements ListInterface<T> {

	//node 
	private class Node {
		T item;
		Node next;

		Node(T x) {
			item = x;
			next = null;
		}
	}

	//linked list
	private Node head;
	private int numItems;

	public List() {
		head = null;
		numItems = 0;
	}

	// helper function
	private Node find(int index) {
		Node N = head;
		for (int i = 1; i < index; i++) {
			N = N.next;
		}
		return N;
	}

	// ADT operations

	public boolean isEmpty() {
		return numItems == 0;
	}

	public int size() {
		return numItems;
	}

	public T get(int index) throws ListIndexOutOfBoundsException {
		if (index < 1 || index > numItems) {
			throw new ListIndexOutOfBoundsException(" get(): invalid index: " + index);
		}
		Node N = find(index);
		return N.item;
	}

	public void add(int index, T newItem) throws ListIndexOutOfBoundsException {
		if (index < 1 || index > (numItems + 1)) {
			throw new ListIndexOutOfBoundsException("check index in add()");
		}
		if (index == 1) {
			Node N = new Node(newItem);
			N.next = head;
			head = N;
		} else {
			Node toAdd = find(index - 1); // at this point index >= 2
			Node C = toAdd.next;
			toAdd.next = new Node(newItem);
			toAdd = toAdd.next;
			toAdd.next = C;
		}
		numItems++;

	}

	public void remove(int index) throws ListIndexOutOfBoundsException {
		if (index < 1 || index > numItems) {
			throw new ListIndexOutOfBoundsException("check index at remove()");
		}
		if (index == 1) {
			Node N = head;
			head = head.next;
			N.next = null;
		} else {
			Node P = find(index - 1);
			Node N = P.next;
			P.next = N.next;
			N.next = null;
		}
		numItems--;
	}

	public void removeAll() {
		head = null;
		numItems = 0;
	}
	public String toString() {
		 StringBuffer sb = new StringBuffer();
	      Node N = head;

	      for( ; N!=null; N=N.next){
	         sb.append(N.item).append(" ");
	      }
	      return new String(sb);
	}

	
	

}
