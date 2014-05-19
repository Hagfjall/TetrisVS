package client.game.shapes;

public class I extends Shape {

	public I() {
		super(Shape.I);
		matrix = new boolean[4][4];
		init();
	}

	private void init() {
		for (int i = 0; i < 4; i++)
			matrix[1][i] = true;
	}
}
