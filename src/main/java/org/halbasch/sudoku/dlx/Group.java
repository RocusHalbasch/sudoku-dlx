package org.halbasch.sudoku.dlx;

public class Group {
	public static final Group rows = new Group("Row",
			new int[][] { { 0, 1, 2, 3, 4, 5, 6, 7, 8 }, { 9, 10, 11, 12, 13, 14, 15, 16, 17 }, { 18, 19, 20, 21, 22, 23, 24, 25, 26 },
					{ 27, 28, 29, 30, 31, 32, 33, 34, 35 }, { 36, 37, 38, 39, 40, 41, 42, 43, 44 }, { 45, 46, 47, 48, 49, 50, 51, 52, 53 },
					{ 54, 55, 56, 57, 58, 59, 60, 61, 62 }, { 63, 64, 65, 66, 67, 68, 69, 70, 71 },
					{ 72, 73, 74, 75, 76, 77, 78, 79, 80 } });
	public static final Group columns = new Group("Column",
			new int[][] { { 0, 9, 18, 27, 36, 45, 54, 63, 72 }, { 1, 10, 19, 28, 37, 46, 55, 64, 73 },
					{ 2, 11, 20, 29, 38, 47, 56, 65, 74 }, { 3, 12, 21, 30, 39, 48, 57, 66, 75 }, { 4, 13, 22, 31, 40, 49, 58, 67, 76 },
					{ 5, 14, 23, 32, 41, 50, 59, 68, 77 }, { 6, 15, 24, 33, 42, 51, 60, 69, 78 }, { 7, 16, 25, 34, 43, 52, 61, 70, 79 },
					{ 8, 17, 26, 35, 44, 53, 62, 71, 80 } });
	public static final Group blocks = new Group("Block",
			new int[][] { { 0, 1, 2, 9, 10, 11, 18, 19, 20 }, { 3, 4, 5, 12, 13, 14, 21, 22, 23 }, { 6, 7, 8, 15, 16, 17, 24, 25, 26 },
					{ 27, 28, 29, 36, 37, 38, 45, 46, 47 }, { 30, 31, 32, 39, 40, 41, 48, 49, 50 }, { 33, 34, 35, 42, 43, 44, 51, 52, 53 },
					{ 54, 55, 56, 63, 64, 65, 72, 73, 74 }, { 57, 58, 59, 66, 67, 68, 75, 76, 77 },
					{ 60, 61, 62, 69, 70, 71, 78, 79, 80 } });
	public static final Group positions = new Group("Position",
			new int[][] { { 0, 3, 6, 27, 30, 33, 54, 57, 60 }, { 1, 4, 7, 28, 31, 34, 55, 58, 61 }, { 2, 5, 8, 29, 32, 35, 56, 59, 62 },
					{ 9, 12, 15, 36, 39, 42, 63, 66, 69 }, { 10, 13, 16, 37, 40, 43, 64, 67, 70 }, { 11, 14, 17, 38, 41, 44, 65, 68, 71 },
					{ 18, 21, 24, 45, 48, 51, 72, 75, 78 }, { 19, 22, 25, 46, 49, 52, 73, 76, 79 },
					{ 20, 23, 26, 47, 50, 53, 74, 77, 80 } });
	public static final Group windows = new Group("Window",
			new int[][] { { 10, 11, 12, 19, 20, 21, 28, 29, 30 }, { 14, 15, 16, 23, 24, 25, 32, 33, 34 },
					{ 46, 47, 48, 55, 56, 57, 64, 65, 66 }, { 50, 51, 52, 59, 60, 61, 68, 69, 70 }, { 1, 2, 3, 37, 38, 39, 73, 74, 75 },
					{ 5, 6, 7, 41, 42, 43, 77, 78, 79 }, { 9, 18, 27, 13, 22, 31, 17, 26, 35 }, { 45, 54, 63, 49, 58, 67, 53, 62, 71 },
					{ 0, 4, 8, 36, 40, 44, 72, 76, 80 } });
	public static final Group crosses = new Group("Cross",
			new int[][] { { 0, 10, 20, 30, 40, 50, 60, 70, 80 }, { 8, 16, 24, 32, 40, 48, 56, 64, 72 } });
	
	private final int[][] cells;
	private final String name;
	
	public Group(String name, int[][] cells) {
		super();
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