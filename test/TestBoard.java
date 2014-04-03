package test;

import game.Board;

public class TestBoard {

	public static void main(String[] args) {
		int x = 10, y = 10;
		Board b = new Board(x, y);
		b.setSlot(0, 0, (byte) 10);
		
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (j == 0 ) {
					System.out.print("|");
				}
				if (b.checkSlot(i, j))
					System.out.print("#");
				else
					System.out.print(" ");
				if( j == y - 1){
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
