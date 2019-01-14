package org.halbasch.sudoku;

import static org.halbasch.sudoku.SudokuSolving.attemptToSolve;
import static org.halbasch.sudoku.SudokuStringUtils.toArray;

import org.halbasch.sudoku.dlx.SudokuDLX;

public class AISudokuTop10 {
	public static final String ESCARGOT = "1....7.9..3..2...8..96..5....53..9...1..8...26....4...3......1..4......7..7...3..";
	public static final String KILLER = ".......7..6..1...4..34..2..8....3.5...29..7...4..8...9.2..6...7...1..9..7....8.6.";
	public static final String LUCKY_DIAMOND = "1..5..4....9.3.....7...8..5..1....3.8..6..5...9...7..8..4.2..1.2..8..6.......1..2";
	public static final String WORM_HOLE = ".8......1..7..4.2.6..3..7....2..9...1...6...8.3.4.......17..6...9...8..5.......4.";
	public static final String LABYRINTH = "1..4..8...4..3...9..9..6.5..5.3..........16......7...2..4.1.9..7..8....4.2...4.8.";
	public static final String CIRCLES = "..5..97...6.....2.1..8....6.1.7....4..7.6..3.6....32.......6.4..9..5.1..8..1....2";
	public static final String SQUADRON = "6.....2...9...1..5..8.3..4......2..15..6..9....7.9.....7...3..2...4..5....6.7..8.";
	public static final String HONEYPOT = "1......6....1....3..5..29....9..1...7...4..8..3.5....25..4....6..8.6..7..7...5...";
	public static final String TWEEZERS = "....1...4.3.2.....6....8.9...7.6...59....5.8....8..4...4.9..1..7....2.4...5.3...7";
	public static final String BROKEN_BRICK = "4...6..7.......6...3...2..17....85...1.4......2.95..........7.5..91...3...3.4..8.";

	public static void main(String[] args) {
		SudokuDLX sudoku = new SudokuDLX();
		
		System.out.println("Attempting AI escargot:");
		attemptToSolve(sudoku, ESCARGOT);

		System.out.println("Attempting AI killer:");
		attemptToSolve(sudoku, KILLER);

		System.out.println("Attempting AI lucky diamond:");
		attemptToSolve(sudoku, LUCKY_DIAMOND);

		System.out.println("Attempting AI worm hole:");
		attemptToSolve(sudoku, WORM_HOLE);

		System.out.println("Attempting AI labyrinth:");
		attemptToSolve(sudoku, LABYRINTH);

		System.out.println("Attempting AI circles:");
		attemptToSolve(sudoku, CIRCLES);

		System.out.println("Attempting AI squadron:");
		attemptToSolve(sudoku, SQUADRON);

		System.out.println("Attempting AI honeypot:");
		attemptToSolve(sudoku, HONEYPOT);

		System.out.println("Attempting AI tweezers:");
		attemptToSolve(sudoku, TWEEZERS);

		System.out.println("Attempting AI broken brick:");
		attemptToSolve(sudoku, BROKEN_BRICK);
		
		int[] escagot = toArray(ESCARGOT);
		int[] killer = toArray(KILLER);
		int[] luckyDiamond = toArray(LUCKY_DIAMOND);
		int[] wormHole = toArray(WORM_HOLE);
		int[] labyrinth = toArray(LABYRINTH);
		int[] circles = toArray(CIRCLES);
		int[] squadron = toArray(SQUADRON);
		int[] honeypot = toArray(HONEYPOT);
		int[] tweezers = toArray(TWEEZERS);
		int[] brokenBrick = toArray(BROKEN_BRICK);

		int count = 2000;
		long start = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			sudoku.setClues(escagot);
			sudoku.setClues(killer);
			sudoku.setClues(luckyDiamond);
			sudoku.setClues(wormHole);
			sudoku.setClues(labyrinth);
			sudoku.setClues(circles);
			sudoku.setClues(squadron);
			sudoku.setClues(honeypot);
			sudoku.setClues(tweezers);
			sudoku.setClues(brokenBrick);
		}

		long time = System.currentTimeMillis() - start;
		System.out.println("Solved AI Sudoku Top 10 boards " + count + " times in " + time / 1000.0 + " seconds.");
		System.out.println("For an avereage solve time of " + time / (double) count + " ms for all 10.");
		System.out.println("For an average solve time per puzzle of " + time / ((double) 10 * count) + " ms each.");
	}
}
