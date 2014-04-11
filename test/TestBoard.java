package test;

import game.GameBoard;
import game.ShapeBoard;
import game.blocks.I;
import game.blocks.Shape;
import game.blocks.Square;

public class TestBoard {

	private GameBoard gameBoard;
	private ShapeBoard shapeBoard;
	private Shape s;
	int x, y;

	public static void main(String[] args) {
		new TestBoard().testCheckMove();

	}

	public void testCheckMove() {
		int r = 6, c = 10;
		gameBoard = new GameBoard(r, c);
		shapeBoard = new ShapeBoard(r, c);

		gameBoard.setSlot(5, 0, (byte) 1);
		gameBoard.setSlot(4, 0, (byte) 1);
		gameBoard.setSlot(4, 0, (byte) 1);
		gameBoard.setSlot(5, 1, (byte) 1);
		gameBoard.setSlot(5, 2, (byte) 1);
		gameBoard.setSlot(3, 0, (byte) 1);
		gameBoard.setSlot(3, 1, (byte) 1);
		gameBoard.setSlot(3, 2, (byte) 1);
		gameBoard.setSlot(3, 3, (byte) 1);
		shapeBoard.setShape(new I());
		System.out.println("shapeboard");
		for (int i = 0; i < 3; i++){
			shapeBoard.moveDown();
		}
		shapeBoard.moveLeft();
		shapeBoard.moveLeft();

		System.out.println(checkMove());
		shapeBoard.print();
		System.out.println("gameBoard");
		gameBoard.print();
		System.out.println("rotating");
		shapeBoard.rotateClockwise();
		System.out.println(checkMove());
		shapeBoard.print();
		

	}

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

	public void test() {
		x = 10;
		y = 22;
		shapeBoard = new ShapeBoard(x, y);
		s = new Square();
		addShape(2, 4);
		addShape(4, 4);
		addShape(6, 4);
		addShape(0, 4);
		printBoard();

	}

	private void printBoard() {
		for (int iy = 0; iy < y; iy++) {
			for (int ix = 0; ix < x; ix++) {
				if (shapeBoard.checkSlot(ix, iy))
					System.out.print(shapeBoard.getType(ix, iy));
				else
					System.out.print(".");
			}
			System.out.println(" ");
		}
	}

	private void addShape(int ix, int iy) {
		for (int y = 0; y < s.getHeight(); y++) {
			for (int x = 0; x < s.getWidth(); x++) {
				shapeBoard.setSlot(ix + x, iy + y, s.getType());
			}
		}
	}
}
