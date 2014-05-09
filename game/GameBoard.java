package game;

import game.shapes.Shape;

import java.awt.Point;

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
	 * saving the shape s in the cell p in to the big array with all the blocks.
	 * 
	 * @param p
	 *            , point of the shape
	 * @param s
	 *            the shape to burn in.
	 */
	public int setShape(Point p, Shape s) {
		int[] rowsAffected = new int[s.getHeight()];
		for (int row = 0; row < s.getWidth(); row++) {
			for (int col = 0; col < s.getHeight(); col++) {
				if (s.checkSlot(row, col)) {
					setSlot(p.y + row, p.x + col, s.getType());
					rowsAffected[row] = p.y + row;
				}
			}
		}
		int rowsDeleted = 0;
		for (int i = 0; i < rowsAffected.length; i++) {
			if (isRowFull(rowsAffected[i])) {
				removeRow(rowsAffected[i]);
				rowsDeleted++;
			}
		}
		return rowsDeleted;
	}

	/**
	 * Moves all rows down one step. Starting at startRow
	 * 
	 * @param startRow
	 */
	public void removeRow(int startRow) {
		for (int col = 0; col < getWidth(); col++) {
			board[startRow][col] = 0;
		}
		for (int row = startRow - 1; row >= 0; row--) {
			for (int col = 0; col < getWidth(); col++) {
				if (row == 0) { // the most highest rows
					board[row][col] = 0;
				} else {
					board[row + 1][col] = board[row][col];
					board[row][col] = 0;
				}
			}
		}
	}

	private boolean isRowFull(int row) {
		for (int col = 0; col < getWidth(); col++) {
			if (!checkSlot(row, col)) {
				return false;
			}
		}
		return true;
	}

}
