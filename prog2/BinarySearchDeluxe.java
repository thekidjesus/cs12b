//Jesus Munoz jmunoz10 12M
//BinarySearchDeluxe.java 
//sets up BinarySearchDeluxe object and search methods
//firstIndexOf and lastIndexOf based off of binary search algorithm found online


import java.util.*;
import java.lang.*;

public class BinarySearchDeluxe {

	// Returns the index of the first key in a[] that equals the search key,
	// or -1 if no such key.
	public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
		
		if (a == null || key == null || comparator == null) 
			throw new IllegalArgumentException("one or more of the arguments in firstIndexOf() is null");
		
		if (a.length == 0) {
			return -1;
		}
		//starting setup
		int low = 0;
		//int result=-1; //not working
		int high = a.length - 1;
		int m = low + (high - low) / 2;
        
        while (low < high) {
            if (comparator.compare(a[m], key) == 0) {//this part should be working now
                high = m;
            } else if (comparator.compare(a[m], key) < 0) {
                low = m + 1;
            } else {
                high = m - 1;
               
            } 
            //resets the midpoint
            m = ((high-low)/ 2)+ low;
            
        }
        
        
        if (comparator.compare(a[m], key) == 0) {
            return m;
        }else{
            return -1;
        }
		
	}

	// Returns the index of the last key in a[] that equals the search key,
	// or -1 if no such key.
	public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {

		if (a == null || key == null || comparator == null) 
			throw new IllegalArgumentException("one or more of the arguments in lastIndexOf() is null");
		//basic setup
		 int low = 0;
		 int high = a.length - 1;
		 int result = -1;//different method
         int m = low+(high-low)/2;
         
         while (low <= high) { 
         
             if (comparator.compare(a[m], key) > 0) { //should work
                 high = m-1;
             }else if (comparator.compare(a[m], key) < 0) {  
                 low = m+1;
             } else {
                 low = m + 1;
                 result = m;
             }
             m = ((high-low)/2)+low;
         }
     return result; 
	}

	// unit testing (required)
	public static void main(String[] args) {
		Term[] arrayOfTerms = { new Term("charlie", 4), new Term("chas", 5), new Term("charlene", 10),
				new Term("charlie", 4), new Term("charlie", 4), };

		Term someTerm = new Term("charlie", 4);
		Arrays.sort(arrayOfTerms, Term.byPrefixOrder(3));
		System.out.println(arrayOfTerms[0] + "  " + arrayOfTerms[1] + "   " + arrayOfTerms[2] + "  " + arrayOfTerms[3]
				+ "  " + arrayOfTerms[4]);
		int first = BinarySearchDeluxe.firstIndexOf(arrayOfTerms, someTerm, Term.byPrefixOrder(3));
		System.out.println("Answer is " + first);
		int last = BinarySearchDeluxe.lastIndexOf(arrayOfTerms, someTerm, Term.byPrefixOrder(3));
		System.out.println("Answer is " + last);

		// int last=BinarySearchDeluxe.lastIndexOf(arrayOfTerms2, otherTerm,
		// Term.byPrefixOrder(2));
		// System.out.println(last);

	}
}
