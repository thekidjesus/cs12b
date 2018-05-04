//Jesus Munoz jmunoz10 12M
//Autocomplete.java 
//sets up Autocomplete object in order to find matches
import java.util.*;
import java.lang.*;

public class Autocomplete {
	Term[] terms;

	// Initializes the data structure from the given array of terms.
	public Autocomplete(Term[] terms) {
		if (terms == null)
			throw new IllegalArgumentException("argument in Autocomplete() is null");
		//error for some reason?
		//this.terms=terms;
		
		//fixes issue
		this.terms = new Term[terms.length];
		 for(int i=0; i<terms.length; i++) {
			 this.terms[i] = terms[i];
		 }
		 //sorting is needed i think?
		  Arrays.sort(this.terms); 
		
		
	}

	// Returns all terms that start with the given prefix,
	// in descending order of weight.
	public Term[] allMatches(String prefix) {

		if (prefix == null)
			throw new IllegalArgumentException("argument in allMatches() is null");

		Term toFind = new Term(prefix, 0);

		int first = BinarySearchDeluxe.firstIndexOf(terms, toFind, Term.byPrefixOrder(prefix.length()));
		int last = BinarySearchDeluxe.lastIndexOf(terms, toFind, Term.byPrefixOrder(prefix.length()));
		
		if (first == -1 || last == -1) {
			throw new NullPointerException("prefix cant be found. error at allMatches()");
		}
		
		Term[] toReturn = new Term[last-first+1];	     
	     int j = 0;
	     for (int i = first; i <= last; i++) {
	    	 toReturn[j++] = terms[i];
	     }
	     Arrays.sort(toReturn, Term.byReverseWeightOrder());
	     return toReturn;
		//issue should be fixed
	     
		/*
		Term[] compatibleTerms = new Term[last - first + 1];
		compatibleTerms = Arrays.copyOfRange(terms, first, last);
		Arrays.sort(compatibleTerms, Term.byReverseWeightOrder());
		return compatibleTerms;
*/
	}

	// Returns the number of terms that start with the given prefix.
	public int numberOfMatches(String prefix) {
		if (prefix == null)
			throw new IllegalArgumentException("argument in numberOfMatches() is null");

		// works but full credit?
		
		return allMatches(prefix).length + 1;

		// manual method
		// Term toFind=new Term(prefix,0);
		// int first=BinarySearchDeluxe.firstIndexOf(terms, toFind,
		// Term.byPrefixOrder(prefix.length()));
		// int last=BinarySearchDeluxe.lastIndexOf(terms, toFind,
		// Term.byPrefixOrder(prefix.length()));
		// return (last-first+1);

	}

	// unit testing (required)
	public static void main(String[] args) {
		Term[] arrayOfTerms = { new Term("charlie", 4), new Term("chas", 5), new
		Term("charlene", 10),new Term("charlie", 4), new Term("charlie", 4), new Term("charlie",8) };
		
		Term[] arrayOfTerms2 = { new Term("frog", 4), new Term("trife", 5), new
				Term("swoop", 10),new Term("charlie", 4), new Term("chowder", 4), new Term("challenge",8) };
		Autocomplete a= new Autocomplete(arrayOfTerms2);
		Arrays.sort(arrayOfTerms2, Term.byPrefixOrder(6));
		System.out.println(a.numberOfMatches("frog"));
		System.out.println(a.numberOfMatches("trife"));
		System.out.println(a.numberOfMatches("cha"));
		System.out.println(a.numberOfMatches("c"));

	}
}
