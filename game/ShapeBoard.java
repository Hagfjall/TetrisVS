package game;

import test.TestMethods;
import game.blocks.Shape;

public class ShapeBoard extends Board {
	private int currentX, oldX;
	private int currentY, oldY;
	private Shape s;
	private boolean turnedClockwise = true;

	public ShapeBoard(int row, int col) {
		super(row, col);
		currentX = currentY = 0;

	}

	public void setShape(Shape s) {
		clearTheENTIREFUCKEINGMATRIX();
		this.s = s;
		currentX = (int) Math.round(((double) getWidth() / 2)
				- ((double) s.getWidth() / 2));
		currentY = 0;
		printShape();
		TestMethods.printMatrix(board);
	}

	public int getX() {
		return currentX;
	}

	public int getY() {
		return currentY;
	}

	public Shape getShape() {
		return s;
	}

	private void printShape() {
		for (int r = 0; r < s.getHeight(); r++) {
			for (int c = 0; c < s.getWidth(); c++) {
				if (s.checkSlot(r, c)) {
					setSlot(r + currentY, c + currentX, s.getType());
				}
			}
		}
	}
	private void clearTheENTIREFUCKEINGMATRIX(){
		for(int r = 0; r < getHeight(); r++){
			for(int c = 0; c < getWidth(); c++){
				setSlot(r, c, (byte)0);
			}
		}
	}

	private void clear() {
		for (int r = 0; r < s.getHeight(); r++) {
			for (int c = 0; c < s.getWidth(); c++) {
				if (s.checkSlot(r, c)) {
					setSlot(r + currentY, c + currentX, (byte) 0);
				}
			}
		}
	}

	private void updateOld() {
		oldX = currentX;
		oldY = currentY;
	}

	private void rotate(boolean clockwise) {

		clear();
		s.rotate(clockwise);
		// left
		while (currentX + s.getMostWest() < 0) {
			currentX++;
		}
		// right
		while (currentX + s.getMostEast() > width - 1) {
			currentX--;
		}
		// down
		while (currentY + s.getMostSouth() > height - 1) {
			currentY--;
		}
		printShape();
	}

	public void rotateClockwise() {
		turnedClockwise = true;
		rotate(turnedClockwise);
	}

	public void rotateCounterClockwise() {
		turnedClockwise = false;
		rotate(turnedClockwise);
	}

	public void rollBack() {
		if (oldX == currentX && oldY == currentY) {
			clear();
			s.rotate(!turnedClockwise);
		} else {

			clear();
			currentX = oldX;
			currentY = oldY;
		}
		printShape();

	}

	public void moveLeft() {
		if (currentX + s.getMostWest() > 0) {
			clear();
			updateOld();
			currentX--;
			printShape();
		}
	}

	public void moveRight() {
		if (currentX + s.getMostEast() < width - 1) {
			clear();
			updateOld();
			currentX++;
			printShape();
		}
	}

	public boolean moveDown() {
		if (currentY + s.getMostSouth() < height - 1) {
			clear();
			updateOld();
			currentY++;
			printShape();
			return true;
		}
		return false;
	}
}
