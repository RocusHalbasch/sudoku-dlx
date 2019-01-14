package org.halbasch.sudoku.dlx;

import static org.halbasch.sudoku.dlx.Group.blocks;
import static org.halbasch.sudoku.dlx.Group.columns;
import static org.halbasch.sudoku.dlx.Group.crosses;
import static org.halbasch.sudoku.dlx.Group.positions;
import static org.halbasch.sudoku.dlx.Group.rows;
import static org.halbasch.sudoku.dlx.Group.windows;
import static org.halbasch.sudoku.dlx.SudokuDLX.AnswerStatus.SOLVED;

import java.util.LinkedList;

import org.halbasch.dlx.Column;
import org.halbasch.dlx.DLX;
import org.halbasch.dlx.Node;

public class SudokuDLX extends DLX {
	private static int NUM_VALS = 9;
	private static int BOARD_SIZE = NUM_VALS * NUM_VALS;

	private LinkedList<Node> clues = new LinkedList<>();
	private LinkedList<Node> answers = new LinkedList<>();
	private ClueStatus clueStatus = ClueStatus.VALID;
	private AnswerStatus answerStatus = AnswerStatus.MORE_THAN_ONE;

	public static enum ClueStatus {
		VALID, INVALID
	}

	public static enum AnswerStatus {
		SOLVED, UN_SOLVABLE, MORE_THAN_ONE
	}
	
	public SudokuDLX() {
		this(rows, columns, blocks);
	}
	
	public SudokuDLX(Group... groups) {
		this(null, groups);
	}
	
	public SudokuDLX(int[] clues) {
		this(clues, rows, columns, blocks);
	}
	
	public SudokuDLX(int[] clues, Group... groups) {
		insertGroupConstraints(insertCellConstraints(), groups);
		if (clues != null)
			setClues(clues);
	}
	
	public static SudokuDLX getSudoku() {
		return getSudoku(null);
	}
	
	public static SudokuDLX getSudokuX() {
		return getSudokuX(null);
	}
	
	public static SudokuDLX getPerfectSudoku() {
		return getPerfectSudoku(null);
	}
	
	public static SudokuDLX getWindoku() {
		return getWindoku(null);
	}
	
	public static SudokuDLX getSudoku(int[] clues) {
		return new SudokuDLX(clues);
	}
	
	public static SudokuDLX getSudokuX(int[] clues) {
		return new SudokuDLX(clues, rows, columns, blocks, crosses);
	}
	
	public static SudokuDLX getPerfectSudoku(int[] clues) {
		return new SudokuDLX(clues, rows, columns, blocks, positions);
	}
	
	public static SudokuDLX getWindoku(int[] clues) {
		return new SudokuDLX(clues, rows, columns, blocks, windows);
	}

	/**
	 * Clears the Sudoku board and attempts to set the clues to the ones provided,
	 * and then solve the puzzle. This will also set the clue and answer status.
	 * 
	 * @param string
	 * @return this Sudoku
	 */
	public SudokuDLX setClues(int[] vals) {
		clear();
		if (vals.length > BOARD_SIZE)
			throw new IllegalArgumentException("Array must contain 81 values");
		Column col = this;
		for (int cell = 0; cell < BOARD_SIZE; cell++) {
			col = (Column) col.R();
			int val = vals[cell];
			if (val < 0 || val > 9)
				throw new IllegalArgumentException("Values must be 0-9");
			if (val > 0) {
				Node node = col.D();
				while (node != col && getVal(node) != val)
					node = node.D();
				if (node == col) {
					undo(clues);
					clueStatus = ClueStatus.INVALID;
					answerStatus = AnswerStatus.UN_SOLVABLE;
					return this;
				}
				removeRow(node, clues);
			}
		}
		clueStatus = ClueStatus.VALID;
		solve();
		return this;
	}

	/**
	 * Clears all answers and clues and fills the clues with a randomly generated
	 * valid Sudoku.
	 * 
	 * @return this Sudoku
	 */
	public SudokuDLX fill() {
		clear();
		clues = randomSearch();
		clueStatus = ClueStatus.VALID;
		answerStatus = AnswerStatus.SOLVED;
		return this;
	}

	/**
	 * Performs a fill then removes clues to create a randomly generated playable
	 * Sudoku
	 * 
	 * @return this Sudoku
	 */
	public SudokuDLX generate() {
		fill();
		int[] clues = getClues();

		LinkedList<Integer> cells = new LinkedList<>();
		for (int i = 0; i < 81; i++)
			cells.add(i);
		int index = 0;
		int val = 0;
		while (!cells.isEmpty()) {
			if (getAnswerStatus() != SOLVED)
				clues[index] = val;
			index = cells.remove(rand.nextInt(cells.size()));
			val = clues[index];
			clues[index] = 0;
			setClues(clues);
		}
		if (getAnswerStatus() != SOLVED) {
			clues[index] = val;
			setClues(clues);
		}
		return this;
	}

	/**
	 * This will return the current clues
	 * 
	 * @return clues
	 */
	public int[] getClues() {
		int[] output = new int[BOARD_SIZE];
		for (Node node : clues)
			output[getCell(node)] = getVal(node);
		return output;
	}

	/**
	 * This will return the current answers
	 * 
	 * @return answers
	 */
	public int[] getAnswers() {
		int[] output = new int[BOARD_SIZE];
		for (Node node : answers)
			output[getCell(node)] = getVal(node);
		return output;
	}

	/**
	 * This will return the current answers and clues
	 * 
	 * @return all cells
	 */
	public int[] getAll() {
		int[] output = new int[BOARD_SIZE];
		for (Node node : clues)
			output[getCell(node)] = getVal(node);
		for (Node node : answers)
			output[getCell(node)] = getVal(node);
		return output;
	}

	/**
	 * This will enumerate all possible choices for each cell.
	 * 
	 * @return a jagged array of possible values by cell
	 */
	public int[][] getCandidates() {
		int[][] posibilities = new int[BOARD_SIZE][];
		Column col = (Column) R();
		for (int cell = 0; cell < BOARD_SIZE; cell++) {
			CellId colId;
			if (col.getName() instanceof CellId)
				colId = (CellId) col.getName();
			else
				colId = null;
			if (colId == null || colId.getCell() > cell) {
				posibilities[cell] = new int[0];
			} else {
				posibilities[cell] = new int[col.getSize()];
				Node node = col.D();
				for (int j = 0; j < col.getSize(); j++) {
					posibilities[cell][j] = ((GroupId) node.R().C().getName()).getVal();
					node = node.D();
				}
				col = (Column) col.R();
			}
		}
		return posibilities;
	}

	/**
	 * Returns the status of the last attempt to set the clues
	 * 
	 * @return clues status
	 */
	public ClueStatus getClueStatus() {
		return clueStatus;
	}

	/**
	 * Returns the status of the last attempt to solve the Sudoku
	 * 
	 * @return answers status
	 */
	public AnswerStatus getAnswerStatus() {
		return answerStatus;
	}

	/**
	 * Removes all clues and answers
	 * 
	 * @return this Sudoku
	 */
	public SudokuDLX clear() {
		answers = new LinkedList<>();
		answerStatus = AnswerStatus.MORE_THAN_ONE;
		undo(clues);
		clueStatus = ClueStatus.VALID;
		return this;
	}

	/**
	 * Returns the number of clues for this Sudoku
	 * 
	 * @return clue count
	 */
	public int getClueCount() {
		return clues.size();
	}

	private Node[] insertCellConstraints() {
		Node[] rightMost = new Node[NUM_VALS * BOARD_SIZE];
		for (int cell = 0; cell < BOARD_SIZE; cell++) {
			this.L().insert(new Column(new CellId(cell)));
			for (int val = 0; val < NUM_VALS; val++) {
				int row = cell * NUM_VALS + val;
				rightMost[row] = ((Column) this.L()).appendNode(new Node());
			}
		}
		return rightMost;
	}

	private void insertGroupConstraints(Node[] rightMost, Group... groups) {
		for (Group group : groups) {
			int[][] cells = group.getCells();
			for (int index = 0; index < cells.length; index++) {
				for (int val = 0; val < NUM_VALS; val++) {
					this.L().insert(new Column(new GroupId(group, index + 1, val + 1)));
					for (int place = 0; place < cells[0].length; place++) {
						int row = cells[index][place] * NUM_VALS + val;
						rightMost[row] = rightMost[row].insert(((Column) this.L()).appendNode(new Node()));
					}
				}
			}
		}
	}

	private void solve() {
		answers = search();
		LinkedList<Node> second = answers.isEmpty() ? new LinkedList<>() : next(new LinkedList<>(answers));
		if (!answers.isEmpty()) {
			if (second.isEmpty()) {
				answerStatus = AnswerStatus.SOLVED;
			} else {
				undo(second);
				answers.clear();
				answerStatus = AnswerStatus.MORE_THAN_ONE;
			}
		} else {
			answerStatus = AnswerStatus.UN_SOLVABLE;
		}
	}

	private int getCell(Node node) {
		while (node.C().getName() instanceof GroupId)
			node = node.L();
		return ((CellId) node.C().getName()).getCell();
	}

	private int getVal(Node node) {
		Object name = node.C().getName();
		if (name instanceof GroupId)
			return ((GroupId) name).getVal();
		return ((GroupId) node.R().C().getName()).getVal();
	}
}
