package org.halbasch.sudoku.dlx;

public class CustomGroup implements Group {
	private final int[][] cells;
	private final String name;

	public CustomGroup(String name, int[][] cells) {
		this.name = name;
		this.cells = cells;
	}

	public int[][] getCells() {
		return cells;
	}

	public String getName() {
		return name;
	}
}
