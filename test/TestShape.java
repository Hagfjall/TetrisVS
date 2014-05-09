package test;

import game.shapes.I;
import game.shapes.L_Left;
import game.shapes.L_Right;
import game.shapes.Shape;
import game.shapes.Square;
import game.shapes.T;
import game.shapes.Z_Left;
import game.shapes.Z_Right;

public class TestShape {

	public static void main(String[] args) {
		

		Shape s = new Square();
//		s.printShape();
//		System.out.println("South: " + s.getMostSouth());
//		System.out.println("East: " + s.getMostEast());
//		
//		s = new T();
//		System.out.println(" ");
//		s.printShape();
//		System.out.println("South: " + s.getMostSouth());
//		System.out.println("East: " + s.getMostEast());
//		
//		s = new L_Right();
//		System.out.println(" ");
//		s.printShape();
//		System.out.println("South: " + s.getMostSouth());
//		System.out.println("East: " + s.getMostEast());
//		
//		s = new L_Left();
//		System.out.println(" ");
//		s.printShape();
//		System.out.println("South: " + s.getMostSouth());
//		System.out.println("East: " + s.getMostEast());
//		
//		s = new Z_Right();
//		System.out.println(" ");
//		s.printShape();
//		System.out.println("South: " + s.getMostSouth());
//		System.out.println("East: " + s.getMostEast());
//		
//		s = new Z_Left();
//		System.out.println(" ");
//		s.printShape();
//		System.out.println("South: " + s.getMostSouth());
//		System.out.println("East: " + s.getMostEast());
//		
		s = new Z_Left();
		System.out.println(" ");
		s.printShape();
		System.out.println("South: " + s.getMostSouth());
		System.out.println("East: " + s.getMostEast());
		s.rotate(true);
		s.printShape();
	}
}
