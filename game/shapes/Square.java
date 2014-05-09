package game.shapes;

public class Square extends Shape {

	public Square() {
		super(Shape.SQUARE);
		matrix = new boolean[2][2];
		init();
	}

	private void init() {
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				matrix[i][j] = true;
	}

	public void rotate(boolean clockwise) {
		// Do nothing, no point of rotating the square.
	}
}
