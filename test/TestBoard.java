package test;

import game.Board;
import game.blocks.Shape;
import game.blocks.Square;

public class TestBoard {

	Board b;
	Shape s;
	int x, y;

	public static void main(String[] args) {
		new TestBoard().test();
	}

	public void test() {
		x = 10;
		y = 18;
		b = new Board(x, y);
		s = new Square();
		addShape(2, 4);
		printBoard();

	}

	private void printBoard() {
		for (int iy = 0; iy < y; iy++) {
			for (int ix = 0; ix < x; ix++) {
				System.out.print(b.getType(ix, iy));
			}
			System.out.println(" ");
		}
	}

	private void addShape(int ix, int iy) {
		for (int y = 0; y < s.getHeight(); y++) {
			for (int x = 0; x < s.getWidth(); x++) {
				b.setSlot(ix + x, iy + y, s.getType());
			}
		}
	}
}
