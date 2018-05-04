// Jesus Munoz jmunoz10 12M
// Term.java 
// sets up Term object and compare methods

import java.util.*;
import java.lang.*;

public class Term implements Comparable<Term> {

	private String query;
	private long weight;

	// Initializes a term with the given query string and weight.
	public Term(String query, long weight) {
		// throws exceptions is query is null or weight is negative
		if (query == null)
			throw new IllegalArgumentException("query can't be null in Term()");
		if (weight < 0)
			throw new IllegalArgumentException("weight can't be negative in Term()");

		this.query = query;
		this.weight = weight;

	}

	// Compares the two terms in descending order by weight.
	public static Comparator<Term> byReverseWeightOrder() {
		return new Comparator<Term>() {
			public int compare(Term t1, Term t2) {
				return (int) (t2.weight - t1.weight);
			}
		};
	}

	// Compares the two terms in lexicographic order but using only the first r
	// characters of each query.
	public static Comparator<Term> byPrefixOrder(int r) {
		// checks that r is positive, else throws IllegalArgumentException
		if (r < 0)
			throw new IllegalArgumentException("paramter \"r\" can't be negative in byPrefixOrder().");

		return new Comparator<Term>() {
			public int compare(Term t1, Term t2) {
				String a;
				String b;
				// checks if length is less than r for both terms, if so tests entire query
				if (t1.query.length() < r)
					a = t1.query;
				else
					a = t1.query.substring(0, r);
				if (t2.query.length() < r)
					b = t2.query;
				else
					b = t2.query.substring(0, r);

				return a.compareTo(b);
			}

		};
	}

	// Compares the two terms in lexicographic order by query.

	@Override
	public int compareTo(Term that) {
		// TODO Auto-generated method stub
		return this.query.compareTo(that.query);
		//return 0;

	}

	public String toString() {
		return weight + "\t" + query;
	}

	// unit testing (required)
	public static void main(String[] args) {
		Term[] terms = { new Term("charlie", 3), new Term("chas", 5), new Term("charlene", 10) };
		System.out.println(Arrays.toString(terms));
		Arrays.sort(terms);
		System.out.println(Arrays.toString(terms));
		Arrays.sort(terms, Term.byReverseWeightOrder());
		System.out.println(Arrays.toString(terms));
		System.out.println(Term.byPrefixOrder(3).compare(terms[0], terms[1]));
	}
}
