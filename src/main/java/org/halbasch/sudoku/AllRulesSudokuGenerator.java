package org.halbasch.sudoku;

import static org.halbasch.sudoku.SudokuBatchGenerator.batchGenerate;
import static org.halbasch.sudoku.dlx.Groups.blocks;
import static org.halbasch.sudoku.dlx.Groups.columns;
import static org.halbasch.sudoku.dlx.Groups.crosses;
import static org.halbasch.sudoku.dlx.Groups.positions;
import static org.halbasch.sudoku.dlx.Groups.rows;
import static org.halbasch.sudoku.dlx.Groups.windows;

import org.halbasch.sudoku.dlx.SudokuDLX;

/**
 * This was fun. I wondered if a valid sudoku could be made that used all the
 * rules from Color Sudoku, Windoku, and Sudoku X. Thanks to my tool the answer
 * was easy to find. This implements a generator for valid boards following all
 * of those rules.
 * 
 * @author rhalbasch
 */
public class AllRulesSudokuGenerator {
	public static void main(String[] args) {
		SudokuDLX sudoku = new SudokuDLX(rows, columns, blocks, positions, windows, crosses);
		batchGenerate(sudoku, 2000);
	}
}
