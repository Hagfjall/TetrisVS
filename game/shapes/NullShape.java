package game.shapes;

public class NullShape extends Shape {

	public NullShape() {
		super(Shape.NULL);
		matrix = new boolean[4][4];
	}
}
