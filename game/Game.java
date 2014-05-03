package game;

import game.blocks.Shape;
import game.blocks.ShapeFactory;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Timer;

import client.KeyListener;
import client.NetworkOutputHandler;

public class Game extends Observable implements Observer {
	// TODO change to private
	public GameBoard gameBoard;
	public ShapeBoard shapeBoard;
	private Timer timer;

	private ShapeFactory shapeFactory;
	private int score, level = 1;

	// TODO implement the score system.

	public Game(int row, int col, long randomSeed) {
		this(row, col, randomSeed, null);
	}

	/**
	 * 
	 * @param row
	 * @param col
	 * @param randomSeed
	 * @param nout
	 */
	public Game(int row, int col, long randomSeed, NetworkOutputHandler nout) {
		shapeFactory = new ShapeFactory(randomSeed);
		score = 0;
		gameBoard = new GameBoard(row, col);
		shapeBoard = new ShapeBoard(row, col);
		shapeBoard.addObserver(this);
		shapeBoard.setShape(shapeFactory.getShape());
		if (nout != null) {
			addTimer(new TetrisTimer(this, nout));
		}
	}

	// TODO ta bort innan release
	public Game(int row, int col) {
		this(row, col, 1000);
	}

	private void addTimer(TetrisTimer timerListener) {
		timer = new Timer(750 / level, timerListener);
		timer.setRepeats(true);
	}

	public int getWidth() {
		return gameBoard.getWidth();

	}

	public int getHeight() {
		return gameBoard.getHeight();
	}

	public int getScore() {
		return score;
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

	/**
	 * 
	 * @return true if the move was possible, otherwise false
	 */
	// TODO private
	public boolean checkHit() {
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

	// TODO private
	public boolean canMoveDown() {
		if (shapeBoard.moveDown()) {
			if (checkHit()) {
				shapeBoard.rollBack();
				return true;
			} else {
				shapeBoard.rollBack();
				return false;
			}
		}
		return false;
	}

	public void start() {
		if (timer != null)
			timer.start();
	}

	public void update(Observable o, Object arg) {
		if (o instanceof GameBoard) {
			update();
		}

	}

	private void update() {
		setChanged();
		notifyObservers();
	}

	private void checkMove() {
		if (timer != null)
			timer.restart();
		if (checkHit()) {
			update();
		} else {
			shapeBoard.rollBack();
		}
	}

	// ----------------------------------- INTERATCTIONS

	/**
	 * 
	 */
	public void moveLeft() {

		shapeBoard.moveLeft();
		checkMove();
	}

	public void moveRight() {
		shapeBoard.moveRight();
		checkMove();
	}

	public void moveDown() {
		if (canMoveDown()) {
			shapeBoard.moveDown();
		} else {
			Shape s = shapeBoard.getShape();
			gameBoard.setShape(new Point(shapeBoard.getX(), shapeBoard.getY()),
					s);
			shapeBoard.setShape(shapeFactory.getShape());
		}
		update();
	}

	public void moveBottom() {
		while (canMoveDown()) {
			shapeBoard.moveDown();
		}
		checkMove();
	}

	public void rotateClockwise() {
		shapeBoard.rotateClockwise();
		checkMove();
	}

	public void rotateCounterClockwise() {
		shapeBoard.rotateCounterClockwise();
		checkMove();
	}

	public void usePowerup() {
		// TODO implementera
		System.out.println("powerup should be sent to opponent");
	}

}
