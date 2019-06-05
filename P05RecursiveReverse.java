/**
 * ./P05RecursiveReverse.java
 * This program accepts a character array and reverses it in place using recursion.
 * A convenience method is provided that accepts and returns a string.
 * The implementation is recursive rather than iterative.
 * by: Leomar Durán <https://github.com/lduran2>
 * date: 2019-06-04T22:25-05
 * for: https://dev.to/javinpaul/top-20-string-coding-problems-from-programming-job-interviews-493m
 */
public class P05RecursiveReverse {

	public static void main(String... argc) {
		String[] tests = {
			"Hello world!", /* test with 12 characters (even count) */
			"Hello, world!" /* test with 13 characters (odd count) */
		};

		/* loop through the tests */
		for (String test : tests) {
			/* call the convenience method */
			System.out.println(reverse(test));
		} /* end for ( : tests) */
	} /* end static void main(String... argc) */


	/******************************************************************//**
	 * Reverses the input string given, returning the newly reversed
	 * string.  This is a convenience method for the char array
	 * reverse method.
	 */
	public static String reverse(String input) {
		char[] cs; /* character array representing input string */
		final int LEN; /* length of the string */
		String OUTPUT; /* the output reversed string */

		cs = input.toCharArray(); /* gets the character array */
		LEN = input.length();     /* and the string length */

		reverse(cs, 0, LEN); /* calls the reverse method on the char array */
		OUTPUT = new String(cs); /* converts to a string and returns */
		return OUTPUT; /* returns the output string */
	} /* end static String reverse(String input) */


	/******************************************************************//**
	 * Reverses the input string given character array in place.  The
	 * original contents of the array are modified.
	 *
	 * This implementation is recursive.
	 *
	 * @param off -- the offset in the character array where to
	 *               start swapping
	 * @param end -- the ending position in the character array
	 *               where to stop swapping
	 */
	public static void reverse(final char[] cs, final int off, final int end) {
		if (off >= end) {
			return;
		} /* end if (off >= end) */
		swap(cs, off, (end - 1));
		reverse(cs, (off + 1), (end - 1));
	} /* end static void reverse(final char[] cs, final int off, final int end) */

	/******************************************************************//**
	 * Swaps the two characters in character array cs, at indices j
	 * and k.
	 */
	public static void swap(char[] cs, int j, int k) {
		final char TEMP = cs[j]; /* temporary variable for swapping */
		cs[j] = cs[k];
		cs[k] = TEMP;
	} /* end static void swap(char[] cs, int j, int k) */

} /* end class P05RecursiveReverse */
