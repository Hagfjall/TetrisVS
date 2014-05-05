package game;

import java.awt.Point;

import game.blocks.Shape;

public class GameBoard extends Board {

	/**
	 * creating the GameBoard with size
	 * 
	 * @param width
	 * @param height
	 */
	public GameBoard(int width, int height) {
		super(width, height);
	}

	/**
	 * saving the shape s in the place p in to the big array with all the
	 * blocks.
	 * 
	 * @param p
	 *            , point of the shape
	 * @param s
	 *            the shape to burn in.
	 */
	public void setShape(Point p, Shape s) {
		int[] rowsAffected = new int[s.getWidth()];
		for (int row = 0; row < s.getWidth(); row++) {
			for (int col = 0; col < s.getHeight(); col++) {
				if (s.checkSlot(row, col)) {
					setSlot(p.y + row, p.x + col, s.getType());
					rowsAffected[row] = p.y + row;
				}
			}
		}
		int count = 0;
		for (int i = 0; i < rowsAffected.length; i++) {
			if (isRowFull(rowsAffected[i])) {
				removeRow(rowsAffected[i]);
				count++;
			}
		}
	}

	/**
	 * Moves all rows down one step. Starting at startRow
	 * 
	 * @param startRow
	 */
	public void removeRow(int startRow) {
		for (int row = startRow; row > 0; row--) {
			for (int col = 0; col < getWidth(); col++) {
				if (row == 0) {
					board[row][col] = 0;
				} else {
					board[row][col] = board[row - 1][col];
				}
			}
		}
	}

	/**
	 * 
	 * @param row
	 * @return
	 */
	private boolean isRowFull(int row) {
		for (int col = 0; col < width; col++) {
			if (!checkSlot(row, col)) {
				return false;
			}
		}
		return true;
	}

}
