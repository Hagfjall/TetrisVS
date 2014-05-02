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
	// TODO change to private
	public GameBoard gameBoard;
	public ShapeBoard shapeBoard;
	private Timer timer;

	private ShapeFactory shapeFactory;
	private int score, level = 1;

	// TODO implement the score system.

	public Game(int row, int col, long randomSeed) {
		shapeFactory = new ShapeFactory(randomSeed);
		score = 0;
		gameBoard = new GameBoard(row, col);
		shapeBoard = new ShapeBoard(row, col);
		shapeBoard.addObserver(this);
		shapeBoard.setShape(shapeFactory.getShape());
		timer = new Timer(750 / level, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (canMoveDown()) {
					shapeBoard.moveDown();
				} else {
					Shape s = shapeBoard.getShape();
					gameBoard.setShape(
							new Point(shapeBoard.getX(), shapeBoard.getY()), s);
					shapeBoard.setShape(shapeFactory.getShape());
				}
				update();
				// TestMethods.printMatrix(getBoard());
			}
		});

		timer.setRepeats(true);
		timer.start();
	}

	public Game(int row, int col) {
		this(row, col, 1000);
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

	// running when ShapeBoard notify game
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
			System.out.println("checkMove: " + checkHit());
			if (checkHit()) {
				shapeBoard.rollBack();
				return true;
			} else {
				shapeBoard.rollBack();
				return false;
			}
		}
		System.out.println("false");
		return false;
	}

	public void update(Observable o, Object arg) {
		// TODO bugg här troligen, rollBack roterar fastän den egentligen ska
		// fastna
		if (o instanceof GameBoard) {
			update();
		}

	}

	private void update() {
		setChanged();
		notifyObservers();
	}

	private void checkMove() {
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

	public void moveBottom() {
		shapeBoard.moveBottom();
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
