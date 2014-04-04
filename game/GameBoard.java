package game;

public class GameBoard extends Board {

	public GameBoard(int width, int height) {
		super(width, height);
	}

	public void removeRows(int nbrrows) {
		byte[][] temp = new byte[width][height];
		for (int r = 0; r < height-nbrrows; r++) {
			for (int c = 0; c < width; c++) {
				temp[c][r+nbrrows] = board[c][r];
			}
		}
		board = temp;
	}

}
