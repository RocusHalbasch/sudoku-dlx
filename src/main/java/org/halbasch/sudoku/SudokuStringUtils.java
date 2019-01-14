package org.halbasch.sudoku;

/**
 * Tinay class for converting between string and array representations of Sudoku
 * boards.
 * 
 * @author rhalbasch
 */
public class SudokuStringUtils {
	public static int[] toArray(String string) {
		String temp = string.trim().replace('.', '0');
		if (temp.length() != 81)
			throw new IllegalArgumentException("String must be 81 chars long");
		int[] vals = new int[81];
		for (int i = 0; i < 81; i++) {
			final int val = temp.charAt(i) - '0';
			if (val < 0 || val > 9)
				throw new IllegalArgumentException("String must only contain numerals");
			vals[i] = val;
		}
		return vals;
	}

	public static String fromArray(int[] array) {
		StringBuilder builder = new StringBuilder();
		for (Integer val : array)
			builder.append(val);
		return builder.toString().replace('0', '.');
	}
}
