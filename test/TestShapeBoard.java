package test;

import game.ShapeBoard;
import game.blocks.*;

public class TestShapeBoard {
	ShapeBoard b;
	Shape s;

	public static void main(String[] args) {

		new TestShapeBoard().run();

	}

	public void run() {
		b = new ShapeBoard(10, 22);
		s = new I();
		b.setShape(s);
		b.printShape();
		b.moveDown();

		b.moveDown();
		b.moveDown();
		b.moveDown();
		b.moveDown();
		b.moveDown();
		b.moveDown();
		b.moveDown();
		b.moveDown();
		b.moveDown();
		b.moveDown();
		b.moveDown();
		b.moveDown();
		b.moveDown();
		b.moveDown();
		b.moveDown();
		b.moveDown();
		b.moveDown();
		b.moveDown();
		b.moveDown();
		b.moveDown();
		b.moveDown();
		b.moveDown();
		b.moveDown();
		b.moveDown();
		b.moveDown();
		b.moveDown();
		b.moveDown();
		b.moveDown();
		b.moveDown();
		b.moveDown();
		b.moveDown();
		b.moveDown();
		b.moveDown();
		b.print();
		// b.moveDown();

	}

}
