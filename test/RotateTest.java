package test;

import game.Board;
import game.ShapeBoard;
import game.blocks.L_Right;
import game.blocks.Shape;

public class RotateTest {
	public Shape s;
	public ShapeBoard b;
	
	public static void main(String[] args) {
		new RotateTest().test();
	}
	public void test(){
		s = new L_Right();
		b = new ShapeBoard(5,10);
		b.setShape(s);
		s.rotate(false);
		b.print();
		System.out.println("\n Rotated");
		s.rotate(false);
		b.print();
		
}

	public void old(){
		String[][] first = new String[5][5];
		
		first[0][1] = "H";
		first[0][2] = "E";
		first[0][3] = "J";
		
		for (int i = 0; i < first.length; i++) {
			for (int j = 0; j < first[0].length; j++) {
				if (first[i][j] == null) {
					System.out.print(".");
				} else {
					System.out.print(first[i][j]);
				}
			}
			System.out.println();
		}
		
		// The magic code that turns everything 90� clockvise
		for (int k = 1; k <= 1; k++) {
			String[][] sec = new String[first.length][first[0].length];
			for (int i = 0; i < first[0].length; i++) {
				for (int j = first.length - 1; j >= 0; j--) {
					sec[i][sec.length - 1 - j] = first[j][i];
				}
			}
			first = sec;
		}
		System.out.println("After rotation");
		
		for (int i = 0; i < first.length; i++) {
			for (int j = 0; j < first[0].length; j++) {
				if (first[i][j] == null) {
					System.out.print(".");
				} else {
					System.out.print(first[i][j]);
				}
			}
			System.out.println();
		}
		
	}
}