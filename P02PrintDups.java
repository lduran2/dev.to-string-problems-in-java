/**
 * ./P02PrintDups.java
 * This program accepts a string and prints all duplicate values and
  * their count to standard output.
 * by: Leomar Durán <https://github.com/lduran2>
 * date: 2019-06-03T13:54
 * for: https://dev.to/javinpaul/top-20-string-coding-problems-from-programming-job-interviews-493m
 */
public class P02PrintDups {

	public static void main(String... argv) {
		String question = "How do you print duplicate characters from a string?"; /* example string */
		System.out.printf("%s\n%s", question, duplicatesIn(question)); /* print the example string and its duplicates */
	} /* end static void main(String... argv) */


	/******************************************************************//**
	 * Creates a new string reporting the duplicates in the specified
	 * character sequence.
	 */
	public static String duplicatesIn(CharSequence cs) {
		/* make enough room for any unicode character in the count array */
		int[] counts = new int[Character.MAX_VALUE];
		final String REPORT;

		/* count the characters in the sequence */
		countChars(counts, cs);
		/* build the report string */
		REPORT = duplicatesToString(counts);
		/* return the report */
		return REPORT;
	} /* end static String duplicatesIn(CharSequence cs) */

	/******************************************************************//**
	 * Converts the given count array into a report string. Each line
	 * is formatted as "'%c' : %d\n".
	 */
	public static String duplicatesToString(int[] counts) {
		StringBuilder sb = new StringBuilder(); /* builds the report string */
		final String RESULT;

		/* loops through the counts */
		for (int k = 0, len = counts.length; k < len; ++k) {
			if (2 <= counts[k]) { /* append a count to the report if >= 2 */
				sb.append(String.format("'%c' : %d\n", ((char)k), counts[k]));
			} /* end if (2 <= counts[k]) */
		} /* end for (int k = 0, len = counts.length; k < len; ++k) */

		RESULT = sb.toString(); /* build the string */
		return RESULT; /* return the string */
	} /* end static String duplicatesToString(int[] counts) */


	/******************************************************************//**
	 * Counts all characters in the specified character sequence.
	 *
	 * This method works by first converting each character into an
	 * integer, then using it as an index in a count array.
	 * (I.e., this is a count sort.)
	 */
	public static void countChars(int[] counts, CharSequence cs) {
		/* loop through the string */
		for (int k = 0, len = cs.length(); k < len; ++k) {
			int i = ((int)cs.charAt(k)); /* get the next character as an integer value */
			++counts[i]; /* increase the corresponding count */
		} /* end for (int k = 0; k < cs.length(); ++k) */
	} /* end static void countChars(int[] counts, CharSequence cs) */ 

} /* end public class P02PrintDups */
