package org.halbasch.sudoku.dlx;

public class GroupId {
	private final Group group;
	private final int index;
	private final int val;

	public GroupId(Group group, int index, int val) {
		this.group = group;
		this.index = index;
		this.val = val;
	}
	
	public Group getGroup() {
		return group;
	}

	public int getIndex() {
		return index;
	}

	public int getVal() {
		return val;
	}

	@Override
	public String toString() {
		return group.getName() + " #" + index + " contains " + (val == 8 ? "an " : "a ") + val;
	}
}