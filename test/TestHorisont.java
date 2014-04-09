package test;

import game.Board;
import game.blocks.Shape;
import game.blocks.T;

public class TestHorisont extends Thread {

	Board b;
	Shape s;
	int x, y;

	public static void main(String[] args) {
		new TestHorisont().run();

	}

	@Override
	public void run() {
		x = 10;
		y = 22;
		b = new Board(x, y);
		s = new T();
		addValues();
		s.rotate(true);
		// putShapeInBoard(3, 15);
		b.setSlot(0, 3, (byte) 1);
		printBoard();
		int[] curve = curveDown(0, 9);
		System.out.println("\nresult:");
		for (int i = 0; i <= 9 - 0; i++) {
			System.out.print(curve[i] + " ");
		}
		// pause på 1 sek
		// printBoard();

	}

	private int[] curveDown(int start, int end) {
		int[] result = new int[end - start + 1];
		for (int c = start; c <= end; c++) {
			for (int r = b.getHeight(); r >= 0; r--) {
				if (b.checkSlot(c, r))
					result[c-start] = r;
			}
		}
		return result;
	}
	
	private void testShapeSouth(){
		
	}
	
//	private int[] curveLeft(int start, int end, int x){
//		
//	}

	private void printBoard() {
		for (int iy = 0; iy < y; iy++) {
			for (int ix = 0; ix < x; ix++) {
				if (b.checkSlot(ix, iy)) {
					System.out.print(b.getType(ix, iy));
				} else
					System.out.print(".");
			}
			System.out.println(" ");
		}
	}

	private void putShapeInBoard(int ix, int iy) {
		for (int i = 0; i < s.getHeight(); i++) {
			for (int j = 0; j < s.getWidth(); j++) {
				if (s.checkSlot(i, j))
					b.setSlot(ix + i, iy + j, s.getType());
			}
		}
	}

	private void addValues() {
		b.setSlot(0, 21, (byte) 1);
		b.setSlot(1, 21, (byte) 1);
		b.setSlot(2, 19, (byte) 1);
		b.setSlot(3, 19, (byte) 1);
		b.setSlot(4, 19, (byte) 1);
		b.setSlot(5, 18, (byte) 1);
		b.setSlot(6, 19, (byte) 1);
		b.setSlot(7, 20, (byte) 1);
		b.setSlot(8, 20, (byte) 1);
		b.setSlot(9, 21, (byte) 1);
	}

}
