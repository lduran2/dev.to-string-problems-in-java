/**
 * ./P03Anagrams.java
 * This program returns whether a pair of strings are anagrams.
 * by: Leomar Durán <https://github.com/lduran2>
 * date: 2019-06-03T15:01
 * for: https://dev.to/javinpaul/top-20-string-coding-problems-from-programming-job-interviews-493m
 */

import java.util.Scanner;

public class P03Anagrams {

	/** Integer value of Unicode character 'A' */
	public static final int A = ((int)'A');
	/** Integer value of Unicode character 'Z' */
	public static final int Z = ((int)'Z');
	/** Value of shift from uppercase to lowercase in ASCII */
	public static final int SHIFT = ((int)('a' - 'A'));
	/** The minimum length to contain all letters in a count array. */
	public static final int ALPHA_LEN = (((int)'z') + 1);


	/******************************************************************//**
	 * The main methods first shows the user some examples before
	 * letting them try.
	 */
	public static void main(String... argv) {
		mainExamples();
		mainRepl();
		System.out.print("Done.\n");
	}


	/******************************************************************//**
	 * Three examples of anagrams.
	 */
	public static void mainExamples() {
		String[][] examples = { /* the examples */
			{"Astronomer", "Moon starer"}, /* mixed case, different word count */
			{"twelve + one", "two + eleven"}, /* symbols */
			{"The eyes,", "they see."} /* mixed case and symbols */
		};

		/* explain */
		System.out.printf("Two strings are anagrams if they contain the same letters in different orders.\n");
		System.out.printf("In other words, they are anagrams if they contain the same counts for the same\nletters.\n");

		/* show the examples */
		System.out.printf("Here are some examples:\n\n");
		for (String[] example : examples) {
			/* print only if they are anagrams */
			if (areAnagrams(example[0], example[1])) {
				System.out.printf("%s = %s\n", example[0], example[1]);
			} /* end if (areAnagrams(example[0], example[1])) */
		} /* end for (String[] example : examples) */
	} /* end static void mainExamples()*/


	/******************************************************************//**
	 * Reads user inputs, evaluates if they are anagrams, prints out
	 * whether they are anagrams.
	 */
	public static void mainRepl() {
		final Scanner CIN; /* scanner on standard console input */
		int n_inputs = 2;
		String[] inputs = new String[n_inputs]; /* first of each pair of strings */
		String input1; /* second of each pair of strings */
		String qualifier; /* " NOT" if not anagrams, empty otherwise */

		CIN = new Scanner(System.in); /* create a scanner on standard console input */

		while (true) {
			/* ask for a pair of strings */
			System.out.printf("\nTry more of your own! [-1 to stop]\n");

			/* accept each string and check if '-1'; if so, stop */
			for (int k = 0; k < n_inputs; ++k) {
				System.out.printf("> "); /* prompt */
				inputs[k] = CIN.nextLine(); /* accept */
				if ("-1".equals(inputs[k])) { /* check */
					return;
				} /* end if ("-1".equals(inputs[k])) */
			} /* end for (int k = 0; k < n_inputs; ++k) */

			/* calculate qualifier */
			qualifier = (areAnagrams(inputs[0], inputs[1]) ? "" : " NOT");
			/* print whether the two strings are anagrams */
			System.out.printf("These two are%s anagrams!\n", qualifier);
		} /* end while (true) */
	} /* end static void mainRepl() */


	/******************************************************************//**
	 * Returns whether two character sequences are anagrams.
	 */
	public static boolean areAnagrams(CharSequence cs0, CharSequence cs1) {
		/* group the 2 character sequences and counts into an
		 * associative array */
		CharSequence[] css = { cs0, cs1 };
		final int n_css = css.length;
		int[][] counts = new int[n_css][];

		/* for each character sequence,
		 * count the number of letters, combining uppercase and
		 * lowercase */
		for (int k = 0; k < n_css; ++k) {
			counts[k] = countLetters(css[k]);
			addLowerToUpperCase(counts[k]);
		} /* for (int k = 0; k < n_css; ++k) */

		/* return whether the resulting upper case counts are equal */
		return areEqual(counts[0], counts[1], A, Z);
	}


	/******************************************************************//**
	 * Returns true if two integer arrays, ints0 and ints1, are equal,
	 * starting at the given offset and ending at the given enting.
	 */
	public static boolean areEqual(int[] ints0, int[] ints1, int off, int end) {
		/* loop through both arrays */
		for (int j = off; (j < end); ++j) {
			/* return false at the first unequal element */
			if (ints0[j] != ints1[j]) {
				return false;
			} /* end if (ints0[j] != ints1[j]) */
		} /* end for (int j = off; (j < end); ++j) */
		return true; /* otherwise, return true. All relevant elements are equal */
	} /* end static boolean areEqual(int[] ints0, int[] ints1, int off, int end)*/ 


	/******************************************************************//**
	 * Adds the count of each lowercase letter to its uppercase
	 * equivalent.
	 */
	public static void addLowerToUpperCase(int[] counts) {
		/* for each uppercase letter */
		for (int j = A; j <= Z; ++j) {
			/* add the count of the corresponding lowercase letter to
			 * its own count */
			counts[j] += counts[j + SHIFT];
		} /* end for (int j = A; j <= Z; ++j) */
	} /* end static void addLowerToUpperCase(int[] counts) */


	/******************************************************************//**
	 * Counts all letters in the specified character sequence. All
	 * non-letters are ignored.
	 *
	 * This method works by first converting each character into an
	 * integer, then using it as an index in a count array.
	 * (I.e., this is a count sort.)
	 */
	public static int[] countLetters(CharSequence cs) {
		int[] counts = new int[ALPHA_LEN]; /* The last letter is lowercase Z. */
		char next; /* the next character */
		/* loop through the string */
		for (int k = 0, len = cs.length(); k < len; ++k) {
			next = cs.charAt(k); /* get next character */
			/* check if a letter */
			if (isLetter(next)) {
				++counts[((int)next)]; /* increase the corresponding count */
			} /* end if (isLetter(NEXT)) */
		} /* end for (int k = 0; k < cs.length(); ++k) */
		return counts;
	} /* end static void countChars(int[] counts, CharSequence cs) */ 

	/******************************************************************//**
	 * Returns true if the given character is between 'A' and 'Z'
	 * (uppercase) or between 'a' and 'z' (lowercase).
	 */
	public static boolean isLetter(char c) {
		/* c is between 'A' and 'Z' (uppercase) or between 'a' and 'z' (lowercase) */
		return (inOrder('A', c, 'Z') || inOrder('a', c, 'z'));
	}

	/******************************************************************//**
	 * Returns true if characters a, b, and c are in correct order.
	 */
	public static boolean inOrder(char a, char b, char c) {
		return ((a <= b) && (b <= c));
	}

}
