package org.halbasch.sudoku;

import static org.halbasch.sudoku.SudokuStringUtils.fromArray;
import static org.halbasch.sudoku.SudokuStringUtils.toArray;
import static org.halbasch.sudoku.dlx.Group.blocks;
import static org.halbasch.sudoku.dlx.Group.columns;
import static org.halbasch.sudoku.dlx.Group.crosses;
import static org.halbasch.sudoku.dlx.Group.positions;
import static org.halbasch.sudoku.dlx.Group.rows;
import static org.halbasch.sudoku.dlx.Group.windows;
import static org.halbasch.sudoku.dlx.SudokuDLX.AnswerStatus.SOLVED;
import static org.halbasch.sudoku.dlx.SudokuDLX.ClueStatus.VALID;

import org.halbasch.sudoku.dlx.SudokuDLX;

/**
 * This is just a sample of using the library to solve and test Sudokus and to
 * print possibilities.
 * 
 * @author rhalbasch
 */
public class SudokuSolving {
	public static final String WORLDS_HARDEST = "8..........36......7..9.2...5...7.......457.....1...3...1....68..85...1..9....4..";
	public static final String GOLDEN_NUGGET = ".......39....1...5..3..58....8..9..6.7..2....1..4.......9..8.5..2....6..4..7.....";
	public static final String EASTER_MONSTER = "1.......2.9.4...5...6...7...5.9.3.......7.......85..4.7.....6...3...9.8...2.....1";
	public static final String TOO_MANY = "8.1............92..........47.1.6..........9....5........63.7...2..9....5........";
	public static final String NOT_POSSIBLE = "8..........36......7..9.2...5...7.......457.....1...3...1....68..85..31..9....4..";
	public static final String ILLEGAL = "8..........36......7..9.2...5...7.......457.....1...3...1....68..85..31..9....4.4";
	public static final String SUDOKU_X_UNSOLVABLE = "...378......1.4............93.....541...3...242.....13............8.1......596...";
	public static final String WINDOKU_CRAZY_EXTREME = "....54..........1...6...7..69...8.2.3.......8.8.....9...5...4...6.....3..........";
	public static final String ALL_RULES_8_CLUES = "................1.2..3....................4..........5.....6.7........8..........";

	public static void main(String[] args) {
		SudokuDLX sudoku = new SudokuDLX();

		System.out.println("Attempting the worlds hardest sudoku:");
		attemptToSolve(sudoku, WORLDS_HARDEST);

		System.out.println("Attempting a sudoku with multiple solutions:");
		attemptToSolve(sudoku, TOO_MANY);

		System.out.println("Attempting a sudoku with no solutions:");
		attemptToSolve(sudoku, NOT_POSSIBLE);

		System.out.println("Attempting a sudoku with invalid clues:");
		attemptToSolve(sudoku, ILLEGAL);

		System.out.println("Attempting the Unsolvable Sudoku X example form SudokuWiki.org:");
		attemptToSolve(SudokuDLX.getSudokuX(), SUDOKU_X_UNSOLVABLE);

		System.out.println("Attempting the Crazy Extreme Windoku example form SudokuWiki.org:");
		attemptToSolve(SudokuDLX.getWindoku(), WINDOKU_CRAZY_EXTREME);

		System.out.println("Printing possibilities for worlds hardest sudoku:");
		printPossibilities(sudoku, WORLDS_HARDEST);
		
		System.out.println("Attempting All Rules 8 Clues:");
		timedSolve(new SudokuDLX(rows, columns, blocks, positions, windows, crosses), ALL_RULES_8_CLUES, 2000);
		
		System.out.println("Attempting the Weekly Unsolvable #331 form SudokuWiki.org:");
		timedSolve(sudoku, "98.7..6..5...8......7..9.8.7....845....4....6....3.2...7...1.....1.6.3.....2....4", 2000);
	}

	public static void timedSolve(SudokuDLX sudoku, String board, int count) {
		attemptToSolve(sudoku, board);
		int[] array = toArray(board);
		long start = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
			sudoku.setClues(array);
		long time = System.currentTimeMillis() - start;
		System.out.println("Solved " + count + " times in " + time / 1000.0 + " seconds.");
		System.out.println("For an average solve time of " + time / (double) count + " ms.\n");
	}

	public static void attemptToSolve(SudokuDLX sudoku, String board) {
		sudoku.setClues(toArray(board));
		System.out.println("ClueStatus: " + sudoku.getClueStatus() + " AnswerStatus: " + sudoku.getAnswerStatus());
		System.out.println(board);
		if (sudoku.getClueStatus() == VALID && sudoku.getAnswerStatus() == SOLVED)
			System.out.println(fromArray(sudoku.getAnswers()));
		System.out.println();
	}

	public static void printPossibilities(SudokuDLX sudoku, String board) {
		System.out.println(board);
		sudoku.setClues(toArray(board));
		int[][] posibilities = sudoku.getCandidates();
		int max = 0;
		for (int i = 0; i < 81; i++)
			max = Math.max(max, posibilities[i].length);
		for (int i = 0; i < max; i++) {
			for (int j = 0; j < 81; j++) {
				if (i < posibilities[j].length)
					System.out.print(posibilities[j][i]);
				else
					System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
