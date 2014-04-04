package game.blocks;

import java.util.Random;

public class ShapeFactory {

	private Random rand;

	public ShapeFactory(long randomseed) {
		rand = new Random(randomseed);
	}

	/**
	 * return a shape randomly
	 * 
	 * @return Shape
	 */
	public Shape getShape() {
		return getShape((byte) (rand.nextInt(Shape.I) + 1));
	}

	/**
	 * returns the specified shape representing the type
	 * 
	 * @param type
	 *            one of the Shape-constant
	 * @return
	 */
	public Shape getShape(byte type) {
		switch (type) {
		case Shape.I:
			return new I();
		case Shape.L_LEFT:
			return new L_Left();
		case Shape.L_RIGHT:
			return new L_Right();
		case Shape.SQUARE:
			return new Square();
		case Shape.T:
			return new T();
		case Shape.Z_LEFT:
			return new Z_Left();
		case Shape.Z_RIGHT:
			return new Z_Right();
		default:
			return null;
		}
	}

}
