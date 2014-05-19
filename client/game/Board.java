package client.game;


public abstract class Board {

	protected byte[][] board;
	protected int width;
	protected int height;

	protected Board(int row, int col) {
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

	/**
	 * Setting the value type (from Shape) in cell [row][col]
	 * @param row 
	 * @param col
	 * @param type
	 */
	protected void setSlot(int row, int col, byte type) {
		if (insideMatrix(row, col))
			board[row][col] = type;
	}

	/**
	 * returning the type from cell [row][col], 0 equals empty. 
	 * @param row
	 * @param col
	 * @return
	 */
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
	 * @return true when the slot is filled ( not equals 0) or the coordinate is outside
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

}
