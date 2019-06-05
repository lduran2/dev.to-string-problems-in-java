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
		final boolean SHOULD_DUPLICATE; /* whether duplicates should be displayed */

		/* Printing functor -- prints out a given character sequence */
		final Function <CharSequence,Void> PRINT = ((cs) -> {
			System.out.printf("%s\n", cs);
			return null;
		}); /* end Function<Character,Void> PRINT(cs) */

		CIN = new Scanner(System.in); /* creates a scanner on standard input */

		/* ask for the string to permute */
		System.err.printf("Type in a string to permute.\n> ");
		INPUT = CIN.nextLine();

		/* ask whether to display duplicates
		 * (anything other than 'N' or 'n' is yes) */
		System.err.printf("Allow duplicates? [Yn] ");
		SHOULD_DUPLICATE = (!("N".equals(CIN.nextLine().toUpperCase())));

		/* permute the input */
		permute(INPUT, 0, INPUT.length(), PRINT, SHOULD_DUPLICATE);
	} /* end public static void main(String... argv) */


	/******************************************************************//**
	 * Permutes the specified character sequence, between the range
	 * specified by [off, end[, then performs the specified function on
	 * each permutation.
	 *
	 * @param allowDuplicates -- if true, duplicates will appear
	 *   in the permutations; otherwise, they will not
	 */
	public static void permute(
			final CharSequence cs, final int off, final int end,
			final Function<CharSequence,?> function,
			final boolean allowDuplicates
	) {
		/* bc: if done swapping, call the function and return */
		if (off == end) {
			function.call(cs);
			return;
		} /* end if (off == end) */

		/* rc: perform permutations */
		/* permutations without the current character swapped */
		permute(cs, (off + 1), end, function, allowDuplicates);
		/* permutations with the current character swapped with each
		 * following character */
		permuteSwapWithFollowing(cs, off, end, function, allowDuplicates);
	} /* end static void permute(
	   * 	final CharSequence cs, final int off, final int end,
	   * 	final Function<CharSequence,?> function,
	   * 	final boolean allowDuplicates
	   * )
	   */


	/******************************************************************//**
	 * Swaps the current character with each of the characters
	 * following it, then performs permutation after the current character.
	 *
	 * @param allowDuplicates -- if true, duplicates will appear
	 *   in the permutations; otherwise, they will not
	 */
	public static void permuteSwapWithFollowing(
			final CharSequence cs, final int off, final int end,
			final Function<CharSequence,?> function,
			final boolean allowDuplicates
	) {
		StringBuilder permutation; /* stores new permutations */
		boolean was_swapped; /* whether each current character has
		                      * already been swapped */

		/* loop through all following characters */
		for (int k = (off + 1); (k < end); ++k) {
			/* search for the current character in the previous
			 * characters swapped.
			 * the character is always counted as swapped if duplicates
			 * are allowed */
			was_swapped =
				(allowDuplicates || !contains(cs, off, k, cs.charAt(k)));
			/* if the current character was not previously swapped */
			if (was_swapped) {
				/* copy the character sequence */
				permutation = new StringBuilder(cs); 
				/* swap with the original offset character */
				swap(permutation, off, k);
				/* permute after the current offset*/
				permute(permutation, (off + 1), end, function, allowDuplicates);
			} /* if (was_swapped) */
		} /* end for (int k = (off + 1); (k < end); ++k) */
	} /* end static void permuteSwapWithFollowing(
	   * 	final CharSequence cs, final int off, final int end,
	   * 	final Function<CharSequence,?> function,
	   * 	final boolean allowDuplicates
	   * )
	   */


	/******************************************************************//**
	 * Searches the specified character sequence within the range
	 * [off, end[ for the given key.
	 * @return true if found; false otherwise
	 */
	public static boolean contains(
		CharSequence cs, int off, int end, char key
	) {
		/* by default all characters should be swapped */
		boolean is_found = false;
		/* search for the current character in the previous
		 * characters swapped */
		for (int l = off; ((!is_found) && (l < end)); ++l) {
			if (key == cs.charAt(l)) {
				is_found = true;
			} /* end if (key == cs.charAt(l)) */
		} /* end for (int l = off; (is_found && (l < k)); ++l) */
		return is_found;
	} /* end static boolean contains(
	   * 	CharSequence cs, int off, int end, char key
	   * )*/


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
