//Queue.java
//Patrick Stumps && Jesus Munoz
//pstumps@ucsc.edu && jmunoz10@ucsc.edu
//CS12M
//Constructor and functions for Queue ADT
public class Queue implements QueueInterface{
	private class Node{
		Object data;
		Node next;
		Node(Object data){
			this.data = data;
			next = null;
		}
	}
	
	private Node head;
	private int numItems;
	
	public Queue(){
		head = null;
		numItems = 0;
	}
	
	public boolean isEmpty() {
		if(numItems == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void enqueue(Object newItem) {
		if (head == null) {
			head = new Node(newItem);
		}
		else{
			Node N = head;
			while(N.next!=null) {
				N=N.next;
			}
			N.next = new Node(newItem);
		}
		numItems++;
	}
	
	public Object dequeue() throws QueueEmptyException{
		if (head == null) {
			return null;
		}
		Node n = head;
		head = n.next;
		numItems --;
		return n.data;
	}
	
	public Object peek() throws QueueEmptyException{
		if (head == null) {
			throw new QueueEmptyException("Cannot peek empty queue");
		}
		else {
			return head.data;
		}
	}
	
	public void dequeueAll() throws QueueEmptyException{
		if (head == null) {
			return;
		}
		for(Node n=head; n!=null; n=n.next) {
			head=null;
			numItems = 0;
		}
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Node n = head;
		for( ; n!=null; n=n.next) {
			sb.append(n.data).append(" ");
		}
		return new String(sb);
	}

	public int length() {
		return numItems;
	}
}
