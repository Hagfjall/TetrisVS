package client.game.shapes;

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

	@Override
	public void rotate(boolean clockwise) { // A square has no meaning of
											// rotating
	}
}
