package client.game.shapes;

public class T extends Shape {

	public T() {
		super(Shape.T);
		matrix = new boolean[3][3];
		init();
	}

	private void init() {
		for (int i = 0; i < 3; i++)
			matrix[0][i] = true;
		matrix[1][1] = true;
	}

}
