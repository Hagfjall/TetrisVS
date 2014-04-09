package test;

import game.ShapeBoard;
import game.blocks.Shape;
import game.blocks.Square;

public class TestShapeBoard {
	ShapeBoard b;
	Shape s;
	
	public static void main(String[] args) {
		
		new TestShapeBoard().run();
		
	}
	
	public void run(){
		b = new ShapeBoard(10,22);
		s = new Square();
		b.setShape(s);
		b.printShape();
		b.print();
		//b.moveDown();
		
	}

}
