/**
 * ./P04Permutations.java
 * This program prints all permutations of a given string.
 * by: Leomar Durán <https://github.com/lduran2>
 * date: 2019-06-04T23:03-05
 * for: https://dev.to/javinpaul/top-20-string-coding-problems-from-programming-job-interviews-493m
 */
 
import java.util.Scanner;

public class P04Permutations {

	public static void main(String... argv) {
		final Scanner CIN; /* standard input scanner */

		final String INPUT; /* user's input */
		final boolean SHOULD_MERGE; /* whether duplicates should be merged */

		/* Printing functor -- prints out a given character sequence */
		final Function <CharSequence,Void> PRINT = ((cs) -> {
			System.out.printf("%s\n", cs);
			return null;
		}); /* end Function<Character,Void> PRINT(cs) */

		CIN = new Scanner(System.in); /* creates a scanner on standard input */

		/* */
		System.err.printf("Type in a string to permute.\n> ");
		INPUT = CIN.nextLine();

		System.err.printf("Should duplicates be merged? [yN] ");
		SHOULD_MERGE = ("Y".equals(CIN.nextLine().toUpperCase()));

		permute(INPUT, 0, INPUT.length(), PRINT, SHOULD_MERGE);
	}


	/******************************************************************//**
	 * Permutes the specified character sequence, between the range
	 * specified by [off, end[, then performs the specified function on
	 * each permutation.
	 *
	 * @param shouldMergeDuplicates -- if true, all duplicates will be
	 *   merged; otherwise, duplicates will appear in the permutations
	 */
	public static void permute(
			final CharSequence cs, final int off, final int end,
			final Function<CharSequence,?> function,
			final boolean shouldMergeDuplicates
	) {
		StringBuilder permutation; /* stores new permutations */
		boolean should_swap; /* whether the current iteration should
		                      * be swapped */

		/* bc: if done swapping, call the function and return */
		if (off == end) {
			function.call(cs);
			return;
		} /* end if (off == end) */

		/* rc:.. */

		/* permute the current permutation after the current offset */
		permute(cs, (off + 1), end, function, shouldMergeDuplicates);

		/* loop through all following characters */
		for (int k = (off + 1); (k < end); ++k) {
			/* by default all characters should be swapped */
			should_swap = true;
			/* on if shouldMergeDuplicates flag is set */
			if (shouldMergeDuplicates) {
				/* search for the current character in the previous
				 * characters swapped */
				for (int l = off; (should_swap && (l < k)); ++l) {
					if (cs.charAt(k) == cs.charAt(l)) {
						should_swap = false;
					} /* end if (cs.charAt(k) == cs.charAt(l)) */
				} /* end for (int l = off; (should_swap && (l < k)); ++l) */
			} /* end if (shouldMergeDuplicates) */

			/* if the current character was not previously swapped */
			if (should_swap) {
				/* copy the character sequence */
				permutation = new StringBuilder(cs); 
				/* swap with the original offset character */
				swap(permutation, off, k);
				/* permute after the current offset*/
				permute(permutation, (off + 1), end, function, shouldMergeDuplicates);
			} /* if (should_swap) */
		} /* end for (int k = (off + 1); (k < end); ++k) */
	} /* end */


	/******************************************************************//**
	 * Swaps the characters at j and k in the given string builder.
	 */
	public static void swap(StringBuilder sb, int j, int k) {
		final char TEMP = sb.charAt(j);
		sb.setCharAt(j, sb.charAt(k));
		sb.setCharAt(k, TEMP);
	} /* end static void swap(StringBuilder sb, int j, int k) */


	/******************************************************************//**
	 * Defines a function on a generic input to a generic output.
	 */
	public interface Function<TInput,TOutput> {
		TOutput call(TInput input);
	} /* end interface Function<TInput,TOutput> */

} /* end class P04Permutations */
