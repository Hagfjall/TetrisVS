package test;

import game.Board;
import game.blocks.*;

public class TestBoard {

	public static void main(String[] args) {
		int x = 10, y = 10;
		Board b = new Board(x, y);
		for (int i = 0; i < x; i++)
			b.setSlot(0, i, (byte) 1);

		Shape s = new Square();
		for (int i = 0; i < s.getWidth(); i++)
			for (int j = 0; j < s.getHeight(); i++) {
				if (s.checkSlot(x, y))
					b.setSlot(i, j, s.getType());
			}

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (j == 0)
					System.out.print("|");
				if (b.checkSlot(i, j)) {
					System.out.print(b.getType(i, j));
				}

				else {
					System.out.print(" ");
				}
				if (j == y - 1) {
					System.out.print("|");
				}
			}
			System.out.println(" ");
		}
		System.out.print(" ");
		for (int i = 0; i < x - 1; i++)
			System.out.print("¨");
	}
}
