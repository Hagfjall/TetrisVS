package game;

import game.blocks.Shape;
import game.blocks.ShapeFactory;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Timer;

import test.TestMethods;

public class Game extends Observable implements Observer {
	private GameBoard gameBoard;
	private ShapeBoard shapeBoard;
	private Timer timer;

	/* ta bort?? */
	protected static final byte NORTH = 1;
	protected static final byte EAST = 2;
	protected static final byte SOUTH = 3;
	protected static final byte WEST = 4;

	private ShapeFactory shapeFactory;
	private int score, level = 1;

	// TODO implement the score system.

	public Game(int row, int col) {
		shapeFactory = new ShapeFactory(1000);
		gameBoard = new GameBoard(row, col);
		shapeBoard = new ShapeBoard(row, col);
		shapeBoard.setShape(shapeFactory.getShape());
		timer = new Timer(1000 / level, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (canMoveDown()) {
					shapeBoard.moveDown();
				} else {
					Shape s = shapeBoard.getShape();
					gameBoard.setShape(
							new Point(shapeBoard.getX(), shapeBoard.getY()), s);
					shapeBoard.setShape(shapeFactory.getShape());
				}
				TestMethods.printMatrix(getBoard());
			}

		});

		timer.setRepeats(true);
		timer.start();
	}

	public int getWidth() {
		return gameBoard.getWidth();

	}

	public int getHeight() {
		return gameBoard.getHeight();
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
		if (shapeBoard.moveDown()) {
			if (checkMove()) {
				shapeBoard.rollBack();
				return true;
			} else {
				shapeBoard.rollBack();
				return false;
			}
		} 
		return false;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof ShapeBoard) {
			if (checkMove()) {
				update();
			} else {
				shapeBoard.rollBack();
			}

		} else if (o instanceof GameBoard) {
			update();
		}

	}

	private void update() {
		setChanged();
		notifyObservers();
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

	public void rotateClockwise() {

	}

	public void rotateCounterClockwise() {

	}

	public void usePowerup() {

	}

}
