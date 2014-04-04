package game;

import game.blocks.Shape;
import game.blocks.ShapeFactory;

public class Game extends Thread {
	private Board board;
	private Shape currentShape;
	private int currentX;
	private int currentY;
	protected static final byte NORTH = 1;
	protected static final byte EAST = 2;
	protected static final byte SOUTH = 3;
	protected static final byte WEST = 4;

	private ShapeFactory factory;

	private Game(Board board, ShapeFactory factory) {
		this.board = board;
		this.factory = factory;
	}

	private boolean checkLeft() {
		return false;

	}

	private boolean checkRight() {
		return false;
	}

	private boolean checkOneDown() {
		boolean free = true;
		return false;

	}

	/**
	 * Tries to set a new shape. If false, game is lost.
	 * 
	 * @return false if shapes exists on the spawn-spot
	 */
	private boolean newShape() {
		currentShape = factory.getShape();
		currentX = (int) Math.round(((double) board.getWidth() / 2)
				- ((double) currentShape.getWidth() / 2));
		currentY = 0;
		for (int r = 0; r < currentShape.getHeight(); r++) {
			for (int c = 0; c < currentShape.getWidth(); c++) {
				if (board.checkSlot(currentX + r, currentY + c)
						&& currentShape.checkSlot(r, c)) {
					return false;
				}
			}
		}
		putShapeOnBoard();
		return true;
	}

	private void putShapeOnBoard() {
		for (int r = 0; r < currentShape.getHeight(); r++) {
			for (int c = 0; c < currentShape.getWidth(); c++) {
				if (currentShape.checkSlot(r, c)) {
					board.setSlot(currentX + r, currentY + c,
							currentShape.getType());
				}
			}
		}
	}

	// private boolean checkPossible(byte orientation) {
	// if (orientation < NORTH && orientation > WEST)
	// return false;
	// boolean free;
	// switch (orientation) {
	// case SOUTH:
	// for (int r = currentX; r < currentX + currentShape.getWidth(); r++) {
	// if (board.checkSlot(r, currentShape.getHeight() + currentY)) {
	// // Kolla internt i shapens matris
	// if (!currentShape.checkSlot(r - currentX,
	// currentShape.getHeight() - 1)) {
	// return true;
	// } else if (currentShape.checkSlot(r - currentX,
	// currentShape.getHeight() - 2)) {
	// free = true;
	// }
	// else{
	// free = false;
	// }
	// } else {
	// free = true;
	// }
	// }
	// }
	// }
}
