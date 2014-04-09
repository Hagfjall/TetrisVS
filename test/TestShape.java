package test;

import game.blocks.I;
import game.blocks.L_Left;
import game.blocks.L_Right;
import game.blocks.Shape;
import game.blocks.Square;
import game.blocks.T;
import game.blocks.Z_Left;
import game.blocks.Z_Right;

public class TestShape {

	public static void main(String[] args) {

		Shape s = new Square();
		s.printShape();
		
		s = new T();
		System.out.println(" ");
		s.printShape();
		
		s = new L_Right();
		System.out.println(" ");
		s.printShape();
		
		s = new L_Left();
		System.out.println(" ");
		s.printShape();
		
		s = new Z_Right();
		System.out.println(" ");
		s.printShape();
		
		s = new Z_Left();
		System.out.println(" ");
		s.printShape();
		
		s = new I();
		System.out.println(" ");
		s.printShape();
	}
}
