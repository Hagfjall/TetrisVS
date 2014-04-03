package test;

import game.Board;
import game.blocks.*;

public class TestBoard {

	static Board b;
	static Shape s;
	static int x, y;
	static byte p;

	public static void main(String[] args) {
		x = 10;
		y = 18;
		b = new Board(x, y);

		s = new Square();
		addShape(2,4);
		printBoard(b);
	}

	private static void printBoard(Board b) {
		for (int iy = 0; iy < y; iy++) {
			for (int ix = 0; ix < x; ix++) {
				System.out.print(b.getType(ix, iy));
			}
			System.out.println(" ");
		}
	}

	private static void addShape(int ix, int iy) {
		for (int y = 0; y < s.getHeight(); y++) {
			for (int x = 0; x < s.getWidth(); x++) {
				b.setSlot(ix+x, iy+y, s.getType());
			}
		}
	}
}
