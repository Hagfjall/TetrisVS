package game;

import game.powerups.NullPowerup;
import game.powerups.Powerup;
import game.powerups.PowerupFactory;
import game.shapes.Shape;
import game.shapes.ShapeFactory;
import game.shapes.Z_Left;

import java.awt.Point;
import java.util.Observable;

public class Game extends Observable {
	public static final int GAME_LOST = 1;

	private GameBoard gameBoard; // representing the blocks that are fixed
	private ShapeBoard shapeBoard; // the current moving block

	private ShapeFactory shapeFactory;
	private PowerupFactory powerupFactory;
	private Powerup opponentPowerup; // storing the power up sent by the opponent
	private Powerup localPowerup; // storing the earned power up which can be sent
									// to the opponent
	private int score; 
	private boolean lost; // if true the game will not react on any interactions. 

	/**
	 * Creating a game with the specified size and setting the random seed for
	 * the factories.
	 * 
	 * @param row
	 *            size
	 * @param col
	 *            size
	 * @param shapeRandomSeed
	 *            used for generating the bricks
	 * @param powerupRandomSeed
	 *            used for generating powerups
	 * 
	 */
	public Game(int row, int col, long shapeRandomSeed, long powerupRandomSeed) {
		shapeFactory = new ShapeFactory(shapeRandomSeed);
		powerupFactory = new PowerupFactory(powerupRandomSeed);
		score = 0;
		gameBoard = new GameBoard(row, col);
		shapeBoard = new ShapeBoard(row, col);
		shapeBoard.setShape(shapeFactory.getShape());
		opponentPowerup = new NullPowerup(); // powerup to avoid nullPointer
		localPowerup = powerupFactory.getPowerup();
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

	/**
	 * 
	 * @return
	 */
	public Powerup getPowerup() {
		return localPowerup;
	}

	public Powerup usePowerup() {
		Powerup ret = localPowerup;
		localPowerup = new NullPowerup();
		return ret;
	}

	/**
	 * getting a representation of the both board, game and shape.
	 * 
	 * @return
	 */
	public byte[][] getBoard() {
		int width = gameBoard.getWidth();
		int height = gameBoard.getHeight();
		byte[][] ret = new byte[height][width];
		if (opponentPowerup.getType() == Powerup.INVISIBLE
				&& opponentPowerup.isActive()) {
			for (int r = 0; r < height; r++) {
				for (int c = 0; c < width; c++) {
					ret[r][c] = shapeBoard.getType(r, c);
				}
			}
		} else {
			for (int r = 0; r < height; r++) {
				for (int c = 0; c < width; c++) {
					if (gameBoard.checkSlot(r, c)) {
						ret[r][c] = gameBoard.getType(r, c);
					} else if (shapeBoard.checkSlot(r, c)) {
						ret[r][c] = shapeBoard.getType(r, c);
					}
				}
			}
		}
		// TestMethods.printMatrix(ret);
		return ret;
	}

	/**
	 * @return true if there isn't any hit, otherwise false
	 */
	private boolean noHit() {
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

	/**
	 * Checks if the current shape are able to move down without hitting
	 * something (do a movedown and then rolling back)
	 * 
	 * @return
	 */
	private boolean canMoveDown() {
		if (shapeBoard.moveDown()) {
			if (noHit()) {
				shapeBoard.rollBack();
				return true;
			} else {
				shapeBoard.rollBack();
				return false;
			}
		}
		return false;
	}

	/**
	 * inform the Observer , most likely GUI
	 */
	private void updated() {
		setChanged();
		notifyObservers();
	}

	private void updated(Object o) {
		setChanged();
		notifyObservers(o);
	}

	/**
	 * Checking the move, is the move is illegal we are rolling back the move
	 */
	private void checkMove() {
		// if (timer != null)
		// timer.restart();
		if (noHit()) {
			updated();
		} else {
			shapeBoard.rollBack();
		}
	}

	// ----------------------------------- INTERATCTIONS

	/**
	 * 
	 */
	public void moveLeft() {
		if (lost)
			return;
		shapeBoard.moveLeft();
		checkMove();
	}

	public void moveRight() {
		if (lost)
			return;
		shapeBoard.moveRight();
		checkMove();
	}

	public void moveDown() {
		if (lost)
			return;
		if (canMoveDown()) {
			shapeBoard.moveDown();
		} else {
			Shape s = shapeBoard.getShape();
			Point p = new Point(shapeBoard.getX(), shapeBoard.getY());
			int removedRows = gameBoard.setShape(p, s);
			if (removedRows == 4) {
				localPowerup = powerupFactory.getPowerup();
			}

			if (opponentPowerup.getType() == Powerup.SINGLEBLOCK
					&& opponentPowerup.isActive()) {
				shapeBoard.setShape(new Z_Left());
			} else {
				shapeBoard.setShape(shapeFactory.getShape());
				if (!noHit()) {
					lost = true;
					updated(GAME_LOST);
					shapeBoard.removeShape();
				}
			}
			score += 1 * removedRows * removedRows;

		}
		updated();
	}

	public void moveBottom() {
		if (lost)
			return;
		while (canMoveDown()) {
			shapeBoard.moveDown();
		}
		checkMove();
	}

	public void rotateClockwise() {
		if (lost)
			return;
		shapeBoard.rotateClockwise();
		checkMove();
	}

	public void rotateCounterClockwise() {
		if (lost)
			return;
		shapeBoard.rotateCounterClockwise();
		checkMove();
	}

	public void activatePowerup(byte type) {
		if (lost)
			return;
		Powerup pwrUp;
		if (type == 0) {
			pwrUp = powerupFactory.getPowerup(); // randomized
		} else {
			pwrUp = powerupFactory.getPowerup(type);
		}
		opponentPowerup = pwrUp;
		System.out.println("Game: usePowerup(): using " + pwrUp.getType());
	}

}
