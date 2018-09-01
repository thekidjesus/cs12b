//Jesus Munoz jmunoz10@ucsc.edu 12M
//Janson Chiu jaachiu@ucsc.edu 12M
//Dictionary.java
//implement Dictionary ADT with linked list 
public class Dictionary implements DictionaryInterface{
	
	//node class
	class Node{
		String key;
		String value;
		Node next;
		Node(String key,String value){
			this.key=key;
			this.value=value;
		}
		
	}
	//node class

	Node first;
	int numItems;
	
	Dictionary(){
		numItems=0;
	}
	
	public boolean isEmpty() {
		return numItems==0;
	}


	public int size() {
		return numItems;
	}


	public String lookup(String key) {
		Node current=first;
		while (current!=null) {
			if (current.key.equals(key)) return current.value;
			current=current.next;	
		}
		return null;
	}

	
	public void insert(String key, String value) throws DuplicateKeyException {
		if (lookup(key)!=null)throw new DuplicateKeyException("key is already in Dictionary");
		if (numItems==0) {
			first=new Node(key,value);
			numItems++;
		}
		
		else {
			Node toInsert=new Node(key,value);
			
			Node current=first;
			for (int i=1; i<numItems;i++) {
				current=current.next;
			}
			current.next=toInsert;
			numItems++;
		}
		
	}

	
	public void delete(String key) throws KeyNotFoundException {
	if (lookup(key)==null) throw new KeyNotFoundException("key is not present in dictionary");
	if (numItems==1) makeEmpty();
	if (first.key.equals(key)) {
		Node newFirst;
		newFirst=first.next;
		first.next=null;
		first=newFirst;
		numItems--;
	}
	
	else {
		Node current=first.next;
		Node prevNode=first;

			while (!key.equals(current.key)) {
			prevNode=current;
			current =current.next;
			}
			prevNode.next=current.next;
			numItems--;
	}
	
	
	
	}
	
	public void makeEmpty() {
		first=null;
		numItems=0;
	}
	
	public String toString() {
		String res="";
		Node current=first;
		for (int i=0;i<numItems;i++) {
			res+=current.key +" "+current.value+ "\n";
			current=current.next;
		}
		return res;
	}

}
