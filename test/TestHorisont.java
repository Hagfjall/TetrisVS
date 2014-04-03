package test;

import game.Board;
import game.blocks.Shape;
import game.blocks.Square;

public class TestHorisont {

	Board b;
	Shape s;
	int x, y;

	public static void main(String[] args) {
		new TestHorisont().test();

	}

	private void test() {
		x = 10;
		y = 22;
		b = new Board(x, y);
		s = new Square();
		addValues();
		printBoard();
	}
	private void printBoard() {
		for (int iy = 0; iy < y; iy++) {
			for (int ix = 0; ix < x; ix++) {
				if(b.checkSlot(ix, iy))
					System.out.print(b.getType(ix, iy));
				else
					System.out.print(".");
			}
			System.out.println(" ");
		}
	}

	private void addValues() {
				b.setSlot(0,21, (byte)1); 
				b.setSlot(1,21, (byte)1); 
				b.setSlot(2,19, (byte)1); 
				b.setSlot(3,19, (byte)1);
				b.setSlot(4,19, (byte)1);
				b.setSlot(5,18, (byte)1);
				b.setSlot(6,19, (byte)1);
				b.setSlot(7,20, (byte)1);
				b.setSlot(8,20, (byte)1);
				b.setSlot(9,21, (byte)1);
	}

}
