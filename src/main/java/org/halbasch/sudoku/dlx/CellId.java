package org.halbasch.sudoku.dlx;

public class CellId {
	private final int cell;

	public int getCell() {
		return cell;
	}

	public CellId(int cell) {
		this.cell = cell;
	}

	@Override
	public String toString() {
		return "Cell #" + cell + " @ Row #" + (cell / 9 + 1) + " Column #" + (cell % 9 + 1);
	}
}