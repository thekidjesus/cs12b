//
public class DictionaryTest {
public static void main(String args[]) {
	Dictionary a=new Dictionary();
	System.out.println(a.isEmpty()); //true
	
	a.insert("1","a");
	System.out.println(a.lookup("1")); //a
	//a.insert("1", "b"); //throws DuplicateKeyException
	a.insert("2","b");
	System.out.println(a.size()); //2
	System.out.println(a.lookup("2")); //b
	//a.delete("3");//throws KeyNotFoundException
	a.delete("2");
	System.out.println(a.size()); //1
	System.out.println(a.lookup("2")); //null
	System.out.println(a.lookup("1")); //a
	a.insert("2", "b");
	a.insert("3", "c");
	a.insert("4", "d");
	a.insert("5", "e");
	System.out.println(a);
	a.makeEmpty();
	System.out.println(a.size()); //0
	
	
	
}
}
