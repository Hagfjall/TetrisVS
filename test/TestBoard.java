package test;

import game.Board;
import game.blocks.*;

public class TestBoard {

	public static void main(String[] args) {
		int x = 10, y = 10;
		Board b = new Board(x, y);

		Shape s = new Square();
		System.out.println(s.getWidth() + s.getHeight());
		
		for (int i = 0; i < s.getWidth(); i++)
			for (int j = 0; j < s.getHeight(); j++) {
				if (s.checkSlot(i, j))
					b.setSlot(i, j, s.getType());
			}
		System.out.print(" ");
		for(int i = 0; i < x ; i++)
			System.out.print("_");
		System.out.println("");
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
		for (int i = 0; i < x; i++)
			System.out.print("-");
	}
}
