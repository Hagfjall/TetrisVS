/**
 * Author: Fredrik Hagfjäll
 */
package test.game;

import test.TestMethods;
import game.Game;
import game.GameBoard;
import game.ShapeBoard;
import game.shapes.I;

public class TestRollback {

	public static void main(String[] args) {
		new TestRollback().run();
	}

	public void run() {
		int r = 6, c = 10;
		// ShapeBoard board = new ShapeBoard(r, c);
		// GameBoard gboard = new GameBoard(r, c);
		// gboard.setSlot(4, 4, (byte) 1);
		Game game = new Game(r, c);
		game.testRollback();
		for (int i = 0; i < 3; i++)
			game.shapeBoard.moveDown();
		TestMethods.printMatrix(game.getBoard());
		game.moveRight();
		TestMethods.printMatrix(game.getBoard());
		//
		// board.setShape(new I());
		// for (int i = 0; i < 3; i++) {
		// board.moveDown();
		// }
		// board.print();
		// board.rollBack();
		// System.out.println();
		// board.print();

	}

}
