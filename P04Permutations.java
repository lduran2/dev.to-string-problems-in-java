/**
 * ./P04Permutations.java
 * This program prints all permutations of a given string.
 * by: Leomar Durán <https://github.com/lduran2>
 * date: 2019-06-04T23:03-05
 * for: https://dev.to/javinpaul/top-20-string-coding-problems-from-programming-job-interviews-493m
 */
public class P04Permutations {

	public static void main(String... argv) {
		final String TEST = "boot";

		final Function <CharSequence,Void> PRINT = ((cs) -> {
			System.out.printf("%s\n", cs);
			return null;
		});

		permute(TEST, 0, TEST.length(), PRINT);
	}

	public static void permute(
			final CharSequence cs, final int off, final int end,
			final Function<CharSequence,?> function
	) {
		StringBuilder permutation; /* stores new permutations */
		/* bc: if done swapping, call the function and return */
		if (off == end) {
			function.call(cs);
			return;
		} /* end if (off == end) */
		/* rc */
		/* permute the current permutation after the current offset */
		permute(cs, (off + 1), end, function);
		/* loop through all following characters */
		for (int k = (off + 1); (k < end); ++k) {
			permutation = new StringBuilder(cs);
			swap(permutation, off, k); /* swap with the original offset character */
			permute(permutation, (off + 1), end, function); /* permute after the current offset*/
		} /* end for (int k = (off + 1); (k < end); ++k) */
	} /* end */

	public static void swap(StringBuilder sb, int j, int k) {
		final char TEMP = sb.charAt(j);
		sb.setCharAt(j, sb.charAt(k));
		sb.setCharAt(k, TEMP);
	}

	public interface Function<TInput,TOutput> {
		TOutput call(TInput input);
	}

} /* end class P04Permutations */
