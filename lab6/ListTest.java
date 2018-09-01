//Jesus Munoz jmunoz10@ucsc.edu	12M
//Patrick Stumps pstumps@ucsc.edu 12M
//ListTest.java

public class ListTest {
	public static void main(String[] args) {
		List<Integer> a=new List<Integer>();
		
		a.add(1, 1);
		a.add(2, 1);
		a.add(3, 2);
		a.add(4, 3);
		a.add(5, 5);
		a.add(6, 8);
		
		//false
		System.out.println(a.isEmpty());
		
		//6
		System.out.println(a.size());
		
		//8
		System.out.println(a.get(6));
		
		//0
		a.removeAll();
		System.out.println(a.size());
		
		
		List<String> b =new List<String>();
		
		b.add(1, "apples");
		
		//1
		System.out.println(b.size());
		
		b.add(2, "bananas");
		b.add(3, "cranberries");
		b.add(4, "dates");
		
		//cranberries
		System.out.println(b.get(3));
		
		b.remove(3);
		
		
		//dates
		System.out.println(b.get(3));
		
		
		
		
		
		
	}
}
