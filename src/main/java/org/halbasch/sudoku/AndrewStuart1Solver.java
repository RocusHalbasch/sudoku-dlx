package org.halbasch.sudoku;

import static org.halbasch.sudoku.SudokuSolving.attemptToSolve;
import static org.halbasch.sudoku.dlx.Group.columns;
import static org.halbasch.sudoku.dlx.Group.rows;

import org.halbasch.sudoku.dlx.Group;
import org.halbasch.sudoku.dlx.SudokuDLX;

/**
 * This simple example creates an Andrew Stuart 1 Jigsaw Sudoku as found on SudokuWiki.org and uses it to solve all examples 
 * provided on the site. The goal is to demonstrate how easy it is to create a custom Sudoku with different rules.
 * 
 * @author rhalbasch
 */
public class AndrewStuart1Solver {
	public static final Group andrewStuart1 = new Group("Jigsaw",
			new int[][] { { 0, 1, 2, 9, 10, 11, 18, 19, 27 }, { 3, 4, 5, 6, 12, 13, 14, 15, 23 }, { 7, 8, 16, 17, 24, 25, 26, 34, 35 },
					{ 20, 21, 28, 29, 30, 36, 37, 38, 47 }, { 22, 31, 32, 39, 40, 41, 48, 49, 58 }, { 33, 42, 43, 44, 50, 51, 52, 59, 60 },
					{ 45, 46, 54, 55, 56, 63, 64, 72, 73 }, { 57, 65, 66, 67, 68, 74, 75, 76, 77 },
					{ 53, 61, 62, 69, 70, 71, 78, 79, 80 } });
	
	private static final String example1 = "4..7.9.2.....2.....9...8...1.4...3..7..4.1..2..2...1.3...6...1.....4.....1.2.7.45";
	private static final String example2 = "2.1...9.497.....56..4.........9.8...............7.91........2..89.....71..9...3.5";
	private static final String example3 = ".8.3..59.5.........1.....6.9..4....58.3...2.97....6..4.9.....3.2.......8.45..9.7.";
	private static final String example4 = ".9....43.2...8...7.......4......1....2.....59...2.6....6.......3...7...1..9.1..68";

	public static void main(String[] args) {
		SudokuDLX sudoku = new SudokuDLX(rows, columns, andrewStuart1);
		attemptToSolve(sudoku, example1);
		attemptToSolve(sudoku, example2);
		attemptToSolve(sudoku, example3);
		attemptToSolve(sudoku, example4);
	}
}
