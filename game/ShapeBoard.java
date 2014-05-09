package game;

import game.shapes.NullShape;
import game.shapes.Shape;

import java.util.ArrayList;
import java.util.List;

public class ShapeBoard extends Board {
	private int currentX, oldX;
	private int currentY, oldY;
	private Shape shape;
	private boolean turnedClockwise = true;
	private String excecuteHistory;
	private static final String MOVE_DOWN = "mvdn", ROTATE_CLOCKWISE = "rtcl",
			ROTATE_COUNTERCLOCKWISE = "rtccl", MOVE_RIGHT = "mvrght", MOVE_LEFT = "mvlft";

	public ShapeBoard(int row, int col) {
		super(row, col);
		currentX = currentY = 0;
	}

	public void setShape(Shape s) {
		if (this.shape != null)
			clear();
		this.shape = s;
		currentX = (int) Math.round(((double) getWidth() / 2)
				- ((double) s.getWidth() / 2));
		currentY = 0;
		printShape();
	}

	public int getX() {
		return currentX;
	}

	public int getY() {
		return currentY;
	}

	public Shape getShape() {
		return shape;
	}

	public void removeShape() {
		clear();
		shape = new NullShape();
	}

	private void printShape() {
		for (int r = 0; r < shape.getHeight(); r++) {
			for (int c = 0; c < shape.getWidth(); c++) {
				if (shape.checkSlot(r, c)) {
					setSlot(r + currentY, c + currentX, shape.getType());
				}
			}
		}
	}

	private void clear() {
		for (int r = 0; r < shape.getHeight(); r++) {
			for (int c = 0; c < shape.getWidth(); c++) {
				if (shape.checkSlot(r, c)) {
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
		shape.rotate(clockwise);
		// left
		while (currentX + shape.getMostWest() < 0) {
			currentX++;
		}
		// right
		while (currentX + shape.getMostEast() > width - 1) {
			currentX--;
		}
		// down
		while (currentY + shape.getMostSouth() > height - 1) {
			currentY--;
		}
		printShape();
	}

	public void rotateClockwise() {
		excecuteHistory = ROTATE_CLOCKWISE;
		turnedClockwise = true;
		rotate(turnedClockwise);
	}

	public void rotateCounterClockwise() {
		excecuteHistory = ROTATE_COUNTERCLOCKWISE;
		turnedClockwise = false;
		rotate(turnedClockwise);
	}

	public void rollBack() {
		clear();
		if(excecuteHistory.equals(MOVE_DOWN)) {
			currentY--;
		}else if(excecuteHistory.equals(MOVE_LEFT)) {
			currentX++;
		}else if(excecuteHistory.equals(MOVE_RIGHT)) {
			currentX--;
		}else if(excecuteHistory.equals(ROTATE_CLOCKWISE)) {
			shape.rotate(false);
		}else if(excecuteHistory.equals(ROTATE_COUNTERCLOCKWISE)) {
			shape.rotate(true);
		}
		excecuteHistory = "";
		printShape();
//		if (oldX == currentX && oldY == currentY) {
//			clear();
//			shape.rotate(!turnedClockwise);
//		} else {
//
//			clear();
//			currentX = oldX;
//			currentY = oldY;
//		}
//		printShape();

	}

	public void moveLeft() {
		excecuteHistory = MOVE_LEFT;
		if (currentX + shape.getMostWest() > 0) {
			clear();
			updateOld();
			currentX--;
			printShape();
		}
	}

	public void moveRight() {
		excecuteHistory = MOVE_RIGHT;
		if (currentX + shape.getMostEast() < width - 1) {
			clear();
			updateOld();
			currentX++;
			printShape();
		}
	}

	public boolean moveDown() {
		excecuteHistory = MOVE_DOWN;
		if (currentY + shape.getMostSouth() < height - 1) {
			clear();
			updateOld();
			currentY++;
			printShape();
			return true;
		}
		return false;
	}
}
