package game;

public class Board {

	private byte[][] board;

	public Board(int x, int y) {
		board = new byte[x][y];
		for (int i = 0; i < x; i++)
			for (int j = 0; j < y; j++)
				board[i][j] = 0;

	}

	public void setSlot(int x, int y, byte type) {
		if (x >= 0 && y >= 0 && x < board.length && y < board[0].length)
			board[x][y] = type;
	}

	/**
	 * returns true if the slot is empty
	 * 
	 * @param x
	 * @param y
	 * @return true when the square is empty ( == 0) or the coordinate is
	 *         outside the matrix
	 */
	public boolean checkSlot(int x, int y) {
		if (x >= 0 && y >= 0 && x < board.length && y > board[0].length) {
			if (board[x][y] == 0)
				return true;
			else {
				return false;
			}
		}
		return false;
	}

}
