package game;

public class Board {

	private byte[][] board;

	public Board(int x, int y) {
		board = new byte[y][x];
		for (int i = 0; i < y; i++)
			for (int j = 0; j < x; j++)
				board[i][j] = 0;

	}
	
	public Board(Board b){
//		board = new byte[x][y];
	}
	

	public void setSlot(int x, int y, byte type) {
		if (insideMatrix(x,y))
			board[y][x] = type;
	}
	
	public byte getType(int x, int y){
		if(insideMatrix(x,y))
			return board[y][x];
		else
			return 0;
	}

	/**
	 * returns true if the slot is filled
	 * 
	 * @param x
	 * @param y
	 * @return true when the slot is filled ( == 0) or the coordinate is
	 *         outside the matrix
	 */
	public boolean checkSlot(int x, int y) {
		if (insideMatrix(x,y)) {
			return (board[y][x] != 0);
		} else
			return true;
	}
	
	private boolean insideMatrix(int x, int y) {
		return y >= 0 && y < board.length && x >= 0 && x < board[0].length;
	}
}
