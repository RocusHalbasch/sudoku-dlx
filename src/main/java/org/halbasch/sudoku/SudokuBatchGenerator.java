package org.halbasch.sudoku;

import static org.halbasch.sudoku.SudokuStringUtils.fromArray;

import org.halbasch.sudoku.dlx.SudokuDLX;

/**
 * This is a simple class that uses the tool to search for valid Sudoku grids
 * and prints them.
 * 
 * @author rhalbasch
 */
public class SudokuBatchGenerator {
	public static void main(String[] args) {
		SudokuDLX sudoku = SudokuDLX.getSudoku();
		batchGenerate(sudoku, 2000);
	}

	public static void batchGenerate(SudokuDLX sudoku, int count) {
		int min = 81;
		long start = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			sudoku.generate();
			min = Math.min(min, sudoku.getClueCount());
			System.out.println(fromArray(sudoku.getClues()) + " " + sudoku.getClueCount());
		}
		long time = System.currentTimeMillis() - start;
		System.out.println("Built " + count + " Sudoku boards with a minimum of " + min + " clues in " + time / 1000.0 + " seconds.");
		System.out.println("For an average generation time of " + time / (double) count + " ms each.");
	}
}
