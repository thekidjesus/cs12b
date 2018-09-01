//QueueEmptyException.java
//Patrick Stumps && Jesus Munoz
//pstumps@ucsc.edu && jmunoz10@ucsc.edu
//CS12M
//Exception for empty Queue
public class QueueEmptyException extends RuntimeException{
	public QueueEmptyException(String s) {
		super(s);
	}
}
