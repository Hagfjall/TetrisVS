package game;

import game.blocks.Shape;
import game.blocks.ShapeFactory;
import game.blocks.Z_Left;
import game.powerups.Invisible;
import game.powerups.NullPowerup;
import game.powerups.Powerup;
import game.powerups.PowerupFactory;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

public class Game extends Observable implements Observer {
	private GameBoard gameBoard;
	private ShapeBoard shapeBoard;

	private ShapeFactory shapeFactory;
	private PowerupFactory powerupFactory;
	private Powerup opponentPowerup, localPowerup;
	private int score, level = 1;

	/**
	 * Used for the local game when it should use a local timer and send the
	 * events to the opponent
	 * 
	 * @param row
	 *            size
	 * @param col
	 *            size
	 * @param shapeRandomSeed
	 *            used for generating the bricks
	 * @param nout
	 *            , used for sending the timer-events (aka moving down)
	 */
	public Game(int row, int col, long shapeRandomSeed, long powerupRandomSeed) {
		shapeFactory = new ShapeFactory(shapeRandomSeed);
		powerupFactory = new PowerupFactory(powerupRandomSeed);
		score = 0;
		gameBoard = new GameBoard(row, col);
		shapeBoard = new ShapeBoard(row, col);
		shapeBoard.addObserver(this);
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

	/**
	 * @return the current score for the game
	 */
	public int getScore() {
		return score;
	}

	public Powerup usePowerup() {
		Powerup ret = localPowerup;
		localPowerup = new NullPowerup();
		return ret;
	}

	public Powerup getPowerup() {
		return localPowerup;
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
	// TODO private
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
	 * starting the timer and the game, only used for local games
	 */
	// public void start() {
	// if (timer != null)
	// timer.start();
	// }

	/**
	 * updates from the GameBoard (when removing rows and so)
	 */
	public void update(Observable o, Object arg) {
		updated();
	}

	/**
	 * inform the Observer , most likely GUI
	 */
	private void updated() {
		setChanged();
		notifyObservers();
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
			}
			score += 1 * removedRows * removedRows;

		}
		updated();
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

	public void activatePowerup(byte type) {
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
