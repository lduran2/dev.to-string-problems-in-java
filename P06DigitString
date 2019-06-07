/**
 * ./P06DigitString.java
 * This program prints whether user inputs are strings of digits.
 * by: Leomar Durán <https://github.com/lduran2>
 * date: 2019-06-07T13:00-05
 * for: https://dev.to/javinpaul/top-20-string-coding-problems-from-programming-job-interviews-493m
 */
import java.util.Scanner;

public class P06DigitString {

	public static void main(String... args) {
		/* Predicate for non-digit characters */
		final ICharPredicate IS_NONDIGIT
			= ((input) -> (input < '0' || input > '9'));
		final Scanner CIN; /* standard input scanner */
		final String SENTINEL = "Done."; /* string to end REPL */

		String input; /* user input */
		String qualifier; /* whether the string is a digit string */

		CIN = new Scanner(System.in); /* creates a scanner on standard input */

		/* While the use does not enter the SENTINEL, */
		while (!(SENTINEL.equals(
			input = nextLine(CIN, "Enter a string, \'%s\' to stop.\n> ", SENTINEL)
		))) {
			/* qualifier depends on whether the input
			 * contains any non-digits */
			qualifier = (contains(input, IS_NONDIGIT))
				? " NOT"
				: "";
			System.out.printf("%s is%s a digit string\n", input, qualifier);
		} /* end while (!(SENTINEL.equals(input))) */
		System.out.printf("Done.\n");
	} /* end static void main(String... args) */


	/******************************************************************//**
	 * Convenience method to print a prompt and accept the next line
	 * of a scanner.
	 */
	public static String nextLine(Scanner in, String prompt, Object... objs) {
		System.err.printf(prompt, objs);
		return in.nextLine();
	} /* end static String nextLine(Scanner in, String prompt, Object... objs) */


	/******************************************************************//**
	 * Searches the specified character squence for a character
	 * satisfying the given predicate.
	 *
	 * @return true if such a character is found; false otherwise
	 */
	public static boolean contains(CharSequence cs, ICharPredicate predicate) {
		boolean is_found = false;
		for (int k = 0, len = cs.length(); (!is_found && (k < len)); ++k) {
			if (predicate.test(cs.charAt(k))) {
				is_found = true;
			} /* end if (predicate.test(cs.charAt(k))) */
		} /* end for (int k = 0, len = cs.length(); (!is_found && (k < len)); ++k) */
		return is_found;
	} /* end static void contains(CharSequence cs, CharPredicate predicate) */


	/******************************************************************//**
	 * Defines a predicate for characters.
	 */
	public interface ICharPredicate {
		/**************************************************************//**
		 * Tests character inputs for a condition.
		 * @return true if the condition is met; false otherwise
		 */
		boolean test(char input);
	} /* end interface ICharPredicate */

} /* end class P06DigitString */
