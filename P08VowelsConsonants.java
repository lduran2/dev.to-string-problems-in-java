/**
 * ./P08VowelsConsonants.java
 * This program counts the number of vowels, consonants, letters and characters in a string.
 * by: Leomar Durán <https://github.com/lduran2>
 * date: 2019-06-09T20:52-05
 * for: https://dev.to/javinpaul/top-20-string-coding-problems-from-programming-job-interviews-493m
 */

import java.util.Scanner;

public class P08VowelsConsonants {

	/** A string containing the vowels in uppercase */
	public static final String VOWELS = "AEIOU";
	/** Value of shift from uppercase to lowercase in ASCII */
	public static final int SHIFT = ((int)('a' - 'A'));


	public static void main(String... argv) {
		final Scanner CIN; /* standard input scanner */
		final String SENTINEL = "Done."; /* string to end REPL */

		String input; /* user input */
		IConsonantVowelCount counts; /* the letter class count */

		CIN = new Scanner(System.in); /* creates a scanner on standard input */

		System.out.printf("Vowel and Consonant Counter\n\n");

		/* REPL: */
		while (!SENTINEL.equals(input = nextLine(CIN, "Please enter a string to count.\n> ", SENTINEL))) {
			/* count letter classes in each input string */
			counts = countConsonantsVowels(input);
			/* print the results and string length */
			System.out.printf("%d vowels\n", counts.vowelCount());
			System.out.printf("%d consonants\n", counts.consonantCount());
			System.out.printf("%d letters total\n", counts.letterCount());
			System.out.printf("%d characters total\n\n", input.length());
		} /* end while (!SENTINEL.equals(input = nextLine(CIN, "Please enter a string to count.\n> ", SENTINEL))) */

		/* Done. */
		System.out.printf("%s\n", SENTINEL);
	} /* end static void main(String... argv) */


	/******************************************************************//**
	 * Convenience method to print a prompt and accept the next line
	 * of a scanner.
	 */
	public static String nextLine(Scanner in, String prompt, Object... objs) {
		System.err.printf(prompt, objs);
		return in.nextLine();
	} /* end static String nextLine(Scanner in, String prompt, Object... objs) */


	/**
	 * Defines an object representing vowel, consonant and letter counts.
	 */
	public interface IConsonantVowelCount {
		/**
		 * @return the number of vowels
		 */
		int vowelCount();
		/**
		 * @return the number of consonants
		 */
		int consonantCount();
		/**
		 * @return the number of letters
		 */
		int letterCount();
	} /* interface IConsonantVowelCount */


	/******************************************************************//**
	 * Counts the consonants and vowels in the specified character
	 * sequence.
	 */
	public static IConsonantVowelCount countConsonantsVowels(CharSequence cs) {
		/* final results for counts */
		final int TOTAL_N_VOWELS;
		final int TOTAL_N_CONSONANTS;

		int n_vowels = 0; /* number of vowels found */
		int n_consonants = 0; /* number of consonants found */
		char next; /* the next character in upper case */

		/* for each character in the character sequence */
		for (int k = 0, len = cs.length(); (k < len); ++k) {
			/* ignore case */
			next = toUpperCase(cs.charAt(k));
			/* check if a letter */
			if (isUpperCaseLetter(next)) {
				/* increase n_vowel if it's a vowel */
				if (isVowel(next)) {
					++n_vowels;
				} /* end if (isVowel(next)) */
				/* otherwise, increase consonants */
				else {
					++n_consonants;
				} /* end else (isVowel(next)) */
			} /* end if (isUpperCaseLetter(next)) */
		} /* end for (int k = 0, len = cs.length(); (k < len); ++k) */

		/* place counts in constants for use in an IConsonantVowelCount object */
		TOTAL_N_VOWELS = n_vowels;
		TOTAL_N_CONSONANTS = n_consonants;

		/* return counts in an object of IConsonantVowelCount interface */
		return new IConsonantVowelCount() {
			public int vowelCount() {
				return TOTAL_N_VOWELS;
			} /* end int vowelCount() */

			public int consonantCount() {
				return TOTAL_N_CONSONANTS;
			} /* end int consonantCount() */

			public int letterCount() {
				return (TOTAL_N_VOWELS + TOTAL_N_CONSONANTS);
			} /* end int letterCount() */
		};
	} /* end static IConsonantVowelCount countConsonantsVowels(CharSequence cs) */


	/******************************************************************//**
	 * Converts the specified character to upper case if it's a lower
	 * case ltter.
	 */
	public static char toUpperCase(char c) {
		final char RESULT = isLowerCaseLetter(c)
			? ((char)(c - SHIFT))
			: c;
		return RESULT;
	} /* end static char toUpperCase(char c) */


	/******************************************************************//**
	 * @return true if characters the specified character is an
	 * uppercase letter; false otherwise
	 */
	public static boolean isUpperCaseLetter(char c) {
		return inOrder('A', c, 'Z');
	} /* end static boolean isUpperCaseLetter(char c)*/


	/******************************************************************//**
	 * @return true if characters the specified character is a
	 * lowercase letter; false otherwise
	 */
	public static boolean isLowerCaseLetter(char c) {
		return inOrder('a', c, 'z');
	} /* end static boolean isLowerCaseLetter(char c) */


	/******************************************************************//**
	 * @return true if characters a, b, and c are in correct order;
	 * false otherwise
	 */
	public static boolean inOrder(char a, char b, char c) {
		return ((a <= b) && (b <= c));
	} /* end static boolean inOrder(char a, char b, char c) */


	/******************************************************************//**
	 * @return true if the given character is a vowel; false otherwise
	 */
	public static boolean isVowel(char c) {
		return contains(VOWELS, c);
	} /* end static boolean isVowel(char c) */


	/******************************************************************//**
	 * Searches the specified character sequence for the specified key
	 * character.
	 *
	 * This is an optimized version of
	 * contains(CharSequence cs, ICharPredicate predicate) for
	 * situations where the key is variable.
	 *
	 * @return true if such a character is found; false otherwise
	 */
	public static boolean contains(CharSequence cs, char key) {
		boolean is_found = false;
		for (int k = 0, len = cs.length(); (!is_found && (k < len)); ++k) {
			if (key == cs.charAt(k)) {
				is_found = true;
			} /* end if (predicate.test(cs.charAt(k))) */
		} /* end for (int k = 0, len = cs.length(); (!is_found && (k < len)); ++k) */
		return is_found;
	} /* end static void contains(CharSequence cs, char key) */

} /* end class P08VowelsConsonants */
