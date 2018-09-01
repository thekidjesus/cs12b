//QueueTest.java
//Patrick Stumps && Jesus Munoz
//pstumps@ucsc.edu && jmunoz10@ucsc.edu
//CS12M
//Test to make sure all Queue functions work
public class QueueTest {
	public static void main(String[] args) {
		Queue A = new Queue();
		System.out.print(A.isEmpty());
		System.out.println();
		System.out.print(A.length());
		System.out.println();
		
		for(int i=0; i<=10; i++) {
			A.enqueue(i*i);
		}
		
		System.out.println(A);
		System.out.println();
		System.out.println(A.dequeue());
		System.out.println();
		System.out.println(A.peek());
		System.out.println();
		System.out.println(A.isEmpty());
		System.out.println();
		System.out.println(A.length());
		System.out.println();
		A.dequeueAll();
		System.out.println(A.isEmpty());
		
	}

}
