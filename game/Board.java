package game;

import java.util.Observable;

public abstract class Board extends Observable {

	protected byte[][] board;
	protected int width;
	protected int height;

	public Board(int row, int col) {
		this.width = col;
		this.height = row;
		board = new byte[row][col];
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setSlot(int row, int col, byte type) {
		if (insideMatrix(row, col))
			board[row][col] = type;
	}

	public byte getType(int row, int col) {
		if (insideMatrix(row, col))
			return board[row][col];
		else
			return 0;
	}

	/**
	 * returns true if the slot is filled
	 * 
	 * @param x
	 * @param y
	 * @return true when the slot is filled ( == 0) or the coordinate is outside
	 *         the matrix
	 */
	public boolean checkSlot(int row, int col) {
		if (insideMatrix(row, col)) {
			return (board[row][col] != 0);
		} else
			return true;
	}

	private boolean insideMatrix(int row, int col) {
		return row >= 0 && row < getHeight() && col >= 0 && col < getWidth();
	}

	//TODO ta bort innan release
	public void print() {
		for (int r = 0; r < getHeight(); r++) {
			for (int c = 0; c < getWidth(); c++) {
				if (checkSlot(r, c))
					System.out.print(getType(r, c));
				else
					System.out.print(".");
			}
			System.out.println(" ");
		}
		System.out.println();
	}

	protected void updated() {
		setChanged();
		notifyObservers();
	}
	
}
