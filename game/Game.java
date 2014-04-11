package game;

import game.blocks.Shape;
import game.blocks.ShapeFactory;

import java.util.Observable;
import java.util.Observer;

public class Game extends Observable implements Observer {
	private GameBoard gameBoard;
	private ShapeBoard shapeBoard;
	protected static final byte NORTH = 1;
	protected static final byte EAST = 2;
	protected static final byte SOUTH = 3;
	protected static final byte WEST = 4;

	private ShapeFactory factory;

	private Game(ShapeFactory factory) {
		this.factory = factory;
	}

	public byte[][] getBoard() {
		byte[][] ret = new byte[gameBoard.height][gameBoard.width];
		for (int r = 0; r < gameBoard.height; r++) {
			for (int c = 0; r < gameBoard.width; c++) {
				if (gameBoard.checkSlot(r, c)) {
					ret[r][c] = gameBoard.getType(r, c);
				} else if (shapeBoard.checkSlot(r, c)) {
					ret[r][c] = shapeBoard.getType(r, c);
				}
			}
		}
		return ret;
	}

	// running when ShapeBoard notify game
	/**
	 * 
	 * @return true if the move was possible, otherwise false
	 */
	private boolean checkMove() {
		Shape s = shapeBoard.getShape();
		int x = shapeBoard.getX();
		int y = shapeBoard.getY();
		for (int r = 0; r < s.getHeight(); r++) {
			for (int c = 0; c < s.getWidth(); c++) {
				if (gameBoard.checkSlot(r + y, c + x) && s.checkSlot(r, c))
					return false;
			}
		}
		return true;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof ShapeBoard) {
			if (checkMove()) {
				// TODO update gui
			} else {
				shapeBoard.rollBack();
			}

		} else if (o instanceof GameBoard) {
			// TODO notify gui/listener
		}

	}

	// ----------------------------------- INTERATCTIONS

	/**
	 * 
	 */
	public void moveLeft() {

	}

	public void moveRight() {

	}

	public void moveBottom() {

	}

}
