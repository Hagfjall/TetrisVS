package game;

import game.attacks.Attack;
import game.attacks.InvisibleAttack;
import game.attacks.NullAttack;
import game.attacks.AttackFactory;
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
	private AttackFactory attackFactory;
	private Attack opponentAttack; // storing the power up sent by the opponent
	private Attack localAttack; // storing the earned power up which can be sent
								// to the opponent
	private int score;
	private boolean lost; // if true the game will not react on any
							// interactions.

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
		attackFactory = new AttackFactory(powerupRandomSeed);
		score = 0;
		gameBoard = new GameBoard(row, col);
		shapeBoard = new ShapeBoard(row, col);
		shapeBoard.setShape(shapeFactory.getShape());
		opponentAttack = new NullAttack(); // attack to avoid
											// nullPointerException
		localAttack = new NullAttack();
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
	public Attack getAttack() {
		return localAttack;
	}

	public Attack useAttack() {
		Attack ret = localAttack;
		localAttack = new NullAttack();
		return ret;
	}

	/**
	 * Merging the shapeBoard and gameBoard to one new matrix and returning
	 * 
	 * @return representation of both boards.
	 */
	public byte[][] getBoard() {
		int width = gameBoard.getWidth();
		int height = gameBoard.getHeight();
		byte[][] ret = new byte[height][width];
		if (opponentAttack.getType() == Attack.INVISIBLE_GAME
				&& opponentAttack.isActive()) {
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
		return ret;
	}

	/**
	 * @return true if there isn't any hit, otherwise false
	 */
	private boolean hit() {
		Shape s = shapeBoard.getShape();
		int x = shapeBoard.getX();
		int y = shapeBoard.getY();
		for (int r = 0; r < s.getHeight(); r++) {
			for (int c = 0; c < s.getWidth(); c++) {
				if (gameBoard.checkSlot(r + y, c + x) && s.checkSlot(r, c))
					return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the current shape are able to move down without hitting
	 * something (does a moveDown() and then roll back)
	 * 
	 * @return
	 */
	private boolean canMoveDown() {
		if (shapeBoard.moveDown()) {
			if (!hit()) {
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
	 * Informs the Observer(s)
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
	 * Checking the move, is the move is illegal we are rolling back the move,
	 * otherwise notifying the observers
	 */
	private void checkMove() {
		if (!hit()) {
			updated();
		} else {
			shapeBoard.rollBack();
		}
	}

	// ----------------------------------- INTERATCTIONS

	/**
	 * Moving the current shape one step to left if possible
	 */
	public void moveLeft() {
		if (lost)
			return;
		shapeBoard.moveLeft();
		checkMove();
	}

	/**
	 * Moving the current shape one step to right if possible
	 */
	public void moveRight() {
		if (lost)
			return;
		shapeBoard.moveRight();
		checkMove();
	}

	/**
	 * Moving the current shape one step down if possible
	 */
	public void moveDown() {
		if (lost)
			return;
		if (canMoveDown()) {
			shapeBoard.moveDown();
		} else { // sets the shape in gameBoard, getting a new shape to interact
					// with
			Shape s = shapeBoard.getShape();
			Point p = new Point(shapeBoard.getX(), shapeBoard.getY());
			int removedRows = gameBoard.setShape(p, s);
			// if (removedRows == 4) {
			if (removedRows >= 1) {
				localAttack = attackFactory.getAttack();
			}
			score += 1 * removedRows * removedRows;

			if (opponentAttack.getType() == Attack.SINGLE_SHAPE
					&& opponentAttack.isActive()) { // checking if the attack
													// Singleblock is active
				shapeBoard.setShape(new Z_Left());
			} else { // getting next shape from the factory
				shapeBoard.setShape(shapeFactory.getShape());

			}
			if (hit()) {
				lost = true;
				updated(GAME_LOST);
				shapeBoard.removeShape();
			}

		}
		updated();
	}

	/**
	 * Moving the current shape to the bottom
	 */
	public void moveBottom() {
		if (lost)
			return;
		while (canMoveDown()) {
			shapeBoard.moveDown();
		}
		checkMove();
	}

	/**
	 * rotating the current shape if possible
	 */
	public void rotateClockwise() {
		if (lost)
			return;
		shapeBoard.rotateClockwise();
		checkMove();
	}

	/**
	 * rotating the current shape if possible
	 */
	public void rotateCounterClockwise() {
		if (lost)
			return;
		shapeBoard.rotateCounterClockwise();
		checkMove();
	}

	/**
	 * setting the opponentAttack as type made by the factory
	 * 
	 * @param type
	 *            of the attack
	 */
	public void activateAttack(Attack attack) {
		if (lost)
			return;
		opponentAttack = attack;
		attack.activate();
		// Attack pwrUp;
		// if (type == 0) {
		// pwrUp = attackFactory.getPowerup(); // randomized
		// } else {
		// pwrUp = attackFactory.getPowerup(type);
		// }
		// opponentAttack = pwrUp;
	}

}
