//Jesus Munoz jmunoz10 12M
//This is the Recursion function that can be ran
//Recursion.java

import java.util.Arrays;
public class Recursion {
	public static void main(String[] args) {

		int[] A = { -1, 2, 6, 12, 9, 2, -5, -2, 8, 5, 7 };
		int[] B = new int[A.length];
		int[] C = new int[A.length];
		 int minIndex = minArrayIndex(A, 0, A.length - 1);
		 int maxIndex = maxArrayIndex(A, 0, A.length - 1);

		for (int x : A)
		System.out.print(x + " ");
		System.out.println();

		 System.out.println("minIndex = " + minIndex);
		 System.out.println("maxIndex = " + maxIndex);
		
		 reverseArray1(A, A.length, B); for (int x : B) System.out.print(x + " ");
		 System.out.println();
		 

		
		 reverseArray2(A, A.length, C); for (int x : C) System.out.print(x + " ");
		 System.out.println();
		 

		reverseArray3(A, 0, A.length - 1);
		for (int x : A)
			System.out.print(x + " ");
		System.out.println();

	}

	static void reverseArray1(int[] X, int n, int[] Y) {

		if (n == 0)
			return;
		if (n > 0) {
			Y[n - 1] = X[X.length - n];
			reverseArray1(X, n - 1, Y);
		}

	}

	static void reverseArray2(int[] X, int n, int[] Y) {

		if (n == 0)
			return;
		if (n > 0) {
			Y[X.length - n] = X[n - 1];
			reverseArray2(X, n - 1, Y);
		}
	}

	static void reverseArray3(int[] X, int i, int j) {
		int a;
		if (i < j) {
			a=X[i];
			X[i] = X[j];
			X[j] = a;
			reverseArray3(X, ++i, --j);
		}
	}

	static int maxArrayIndex(int[] X, int p, int r) {
		int q;
		if (p==r) {return X[p];}
		if (r-p==2) {
			if (X[p]>X[r])return p;
			else return r;
		}
		q=(p+r)/2;
		if (X[maxArrayIndex(X,p,q)]> X[maxArrayIndex(X,q+1,r)]) return maxArrayIndex(X,p,q);
		else return maxArrayIndex(X,q+1,r);
			
	}

	static int minArrayIndex(int[] X, int p, int r) {
		int q;
		if (p==r) {return X[p];}
		if (r-p==2) {
			if (X[p]<X[r])return p;
			else return r;
		}
		q=(p+r)/2;
		if (X[minArrayIndex(X,p,q)]< X[minArrayIndex(X,q+1,r)]) return minArrayIndex(X,p,q);
		else return minArrayIndex(X,q+1,r);
		
	}

}
