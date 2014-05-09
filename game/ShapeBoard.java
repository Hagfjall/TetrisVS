package game;

import game.shapes.NullShape;
import game.shapes.Shape;

import java.util.ArrayList;
import java.util.List;

public class ShapeBoard extends Board {
	private int currentX, currentY;
	private Shape shape;
	private String excecuteHistory;
	private static final String MOVE_DOWN = "mvdn", ROTATE_CLOCKWISE = "rtcl",
			ROTATE_COUNTERCLOCKWISE = "rtccl", MOVE_RIGHT = "mvrght",
			MOVE_LEFT = "mvlft";

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
		setShapeInMatrix();
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

	/**
	 * Rotating the current shape clockwise
	 */
	public void rotateClockwise() {
		excecuteHistory = ROTATE_CLOCKWISE;
		rotate(true);
	}

	/**
	 * Rotating the current shape counterclockwise
	 */
	public void rotateCounterClockwise() {
		excecuteHistory = ROTATE_COUNTERCLOCKWISE;
		rotate(false);
	}

	/**
	 * Undo the last used command
	 */
	public void rollBack() {
		clear();
		if (excecuteHistory.equals(MOVE_DOWN)) {
			currentY--;
		} else if (excecuteHistory.equals(MOVE_LEFT)) {
			currentX++;
		} else if (excecuteHistory.equals(MOVE_RIGHT)) {
			currentX--;
		} else if (excecuteHistory.equals(ROTATE_CLOCKWISE)) {
			shape.rotate(false);
		} else if (excecuteHistory.equals(ROTATE_COUNTERCLOCKWISE)) {
			shape.rotate(true);
		}
		excecuteHistory = "";
		setShapeInMatrix();
	}

	/**
	 * Moving the current shape to the left of possible
	 */
	public void moveLeft() {
		excecuteHistory = MOVE_LEFT;
		if (currentX + shape.getMostWest() > 0) {
			clear();
			currentX--;
			setShapeInMatrix();
		}
	}

	/**
	 * Moving the current shape to the right of possible
	 */
	public void moveRight() {
		excecuteHistory = MOVE_RIGHT;
		if (currentX + shape.getMostEast() < width - 1) {
			clear();
			currentX++;
			setShapeInMatrix();
		}
	}

	/**
	 * Return true if the move was possible otherwise false (i.e only inside
	 * this board)
	 * 
	 * @return
	 */
	public boolean moveDown() {
		excecuteHistory = MOVE_DOWN;
		if (currentY + shape.getMostSouth() < height - 1) {
			clear();
			currentY++;
			setShapeInMatrix();
			return true;
		}
		return false;
	}

	/**
	 * Saving the shape to the byte matrix inside board.
	 */
	private void setShapeInMatrix() {
		for (int r = 0; r < shape.getHeight(); r++) {
			for (int c = 0; c < shape.getWidth(); c++) {
				if (shape.checkSlot(r, c)) {
					setSlot(r + currentY, c + currentX, shape.getType());
				}
			}
		}
	}

	/**
	 * removing the shape in the byte matrix inside board.
	 */
	private void clear() {
		for (int r = 0; r < shape.getHeight(); r++) {
			for (int c = 0; c < shape.getWidth(); c++) {
				if (shape.checkSlot(r, c)) {
					setSlot(r + currentY, c + currentX, (byte) 0);
				}
			}
		}
	}

	/**
	 * Rotating the shape clockwise, moving the shape so that the whole shape
	 * stays inside the byte matrix
	 * 
	 * @param clockwise
	 */
	private void rotate(boolean clockwise) {

		clear();
		shape.rotate(clockwise);
		// lefts
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
		setShapeInMatrix();
	}

}
