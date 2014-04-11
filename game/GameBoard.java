package game;

import java.awt.Point;

import game.blocks.Shape;

public class GameBoard extends Board {

	public GameBoard(int width, int height) {
		super(width, height);
	}

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
		for (int i = 0; i < rowsAffected.length; i++) {
			if (isRowFull(rowsAffected[i])) {
				removeRow(rowsAffected[i]);
			}
		}
	}

	private boolean isRowFull(int row) {
		for (int col = 0; col < width; col++) {
			if (!checkSlot(row, col)) {
				return false;
			}
		}
		return true;
	}

	public void removeRow(int startRow) {
		byte[][] temp = new byte[width][height];
		for(int row = startRow; row > 0; row--){
			for(int col = 0; col < getWidth(); col++){
				if(checkSlot(row - 1, col)){
					setSlot(row, col, type)
				}
				this.setSlot(row, col, type)
		}
		}
	}
}
