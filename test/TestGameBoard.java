package test;

import game.GameBoard;

public class TestGameBoard {
	GameBoard b;

	public static void main(String[] args) {
		new TestGameBoard().testRemoveRows();
	}

	private void testRemoveRows() {
		b = new GameBoard(10, 6);
		for (int row = 10; row > 5; row--) {
			for (int col = 0; col < 6; col++) {
				if (col % 2 == 1) {
					b.setSlot(row, col, (byte) 1);
				}
			}
		}
		b.print();
		b.removeRow(9);
		System.out.println("One row removed");
		b.print();
		b.removeRow(9);
		System.out.println("One more row removed");
		b.print();
	}

}
