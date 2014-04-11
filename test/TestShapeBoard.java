package test;

import game.ShapeBoard;
import game.blocks.*;

public class TestShapeBoard {
	ShapeBoard b;
	Shape s;

	public static void main(String[] args) {
		new TestShapeBoard().testRollback();
	}
	
	public void testRollback(){
		b = new ShapeBoard(6, 10);
		s = new L_Left();
		b.setShape(s);
		b.printShape();
		b.print();
		b.moveDown();
		System.out.println("\nmoved down");
		b.print();
		b.rollBack();
		System.out.println("\nrolling back");
		b.print();
		
		b.rotateCounterClockwise();
		System.out.println();
		b.print();
		System.out.println("\n rolling back! \n");
		b.rollBack();
		b.print();
	}
	
	public void testCorners() {
		b = new ShapeBoard(6, 10);
		s = new L_Left();
		b.setShape(s);
		b.printShape();
		for (int i = 0; i < 7; i++) {
			b.moveLeft();
		}
		b.print();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < 15; i++)
			b.moveRight();
		System.out.println(" ");
		b.print();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < 15; i++)
			b.moveDown();

		System.out.println(" ");
		b.print();
	}

}
