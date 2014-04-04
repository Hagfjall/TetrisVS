package game;

public class Board {

	private byte[][] board;
	private int width;
	private int height;

	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		board = new byte[width][height];
//		for (int i = 0; i < x; i++)
//			for (int j = 0; j < y; j++)
//				board[i][j] = 0;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}

	public Board(Board b) {
		// board = new byte[x][y];
	}

	public void setSlot(int x, int y, byte type) {
		if (insideMatrix(x,y))
			board[x][y] = type;
	}

	public byte getType(int x, int y) {
		if (insideMatrix(x, y))
			return board[x][y];
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
	public boolean checkSlot(int x, int y) {
		if (insideMatrix(x, y)) {
			return (board[x][y] != 0);
		} else
			return true;
	}

	private boolean insideMatrix(int x, int y) {
		return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
	}
}
