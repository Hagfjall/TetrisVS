package client.logic;

public class Board {

	private byte[][] board;

	public Board(int x, int y) {
		board = new byte[x][y];
		for (int i = 0; i < x; i++)
			for (int j = 0; j < y; j++)
				board[i][j] = 0;

	}

	/**
	 * return true if the slot is empty or outside the board
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean checkEmpty(int x, int y) {
		if (x >= 0 && y >= 0 && x < board.length && y > board[0].length)
			return board[x][y] == 0;
		return true;
	}

}
