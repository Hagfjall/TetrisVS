package game;

import game.blocks.I;
import game.blocks.Shape;
import game.blocks.ShapeFactory;

import java.util.Observable;
import java.util.Observer;

import test.TestMethods;

public class Game extends Observable implements Observer {
	private GameBoard gameBoard;
	private ShapeBoard shapeBoard;
	protected static final byte NORTH = 1;
	protected static final byte EAST = 2;
	protected static final byte SOUTH = 3;
	protected static final byte WEST = 4;

	private ShapeFactory factory;
	private int score;

	public Game(int row, int col) {
		gameBoard = new GameBoard(row, col);
		shapeBoard = new ShapeBoard(row, col);
		this.factory = factory;
	}

	public void test() {
		gameBoard = new GameBoard(6, 10);
		shapeBoard = new ShapeBoard(6, 10);
		for (int i = 0; i < 10; i++)
			gameBoard.setSlot(3, i, (byte) 1);
		shapeBoard.setShape(new I());
		shapeBoard.print();
		while (canMoveDown()) {
			shapeBoard.moveDown();
			TestMethods.printMatrix(getBoard());
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public byte[][] getBoard() {
		int width = gameBoard.getWidth();
		int height = gameBoard.getHeight();
		byte[][] ret = new byte[height][width];
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c++) {
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

	private boolean canMoveDown() {
		shapeBoard.moveDown();
		if (checkMove())
			return true;
		else {
			shapeBoard.rollBack();
			return false;
		}
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
