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
	//TODO change to private
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
		timer = new Timer(500 / level, new ActionListener() {
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
//				TestMethods.printMatrix(getBoard());
			}
		});

		timer.setRepeats(true);
		timer.start();
	}

	// TODO remove
	public void testRollback() {
		gameBoard.setSlot(getHeight() - 2, (getWidth() / 2) + 1, (byte) 1);
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
	//TODO private
	public boolean checkMove() {
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
	//TODO private
	public boolean canMoveDown() {
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
		//TODO bugg här troligen, rollBack roterar fastän den egentligen ska fastna
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
		shapeBoard.moveLeft();
		update();
	}

	public void moveRight() {
		shapeBoard.moveRight();
		update();

	}

	public void moveBottom() {
		shapeBoard.moveBottom();
		update();
	}

	public void rotateClockwise() {
		shapeBoard.rotateClockwise();
		update();
	}

	public void rotateCounterClockwise() {
		shapeBoard.rotateCounterClockwise();
		update();
	}

	public void usePowerup() {
		// TODO implementera
		System.out.println("powerup should be sent to opponent");
	}

}
