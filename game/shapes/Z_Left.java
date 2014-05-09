package game.shapes;

public class Z_Left extends Shape {

	public Z_Left() {
		super(Shape.Z_LEFT);
		matrix = new boolean[3][3];
		init();
	}

	private void init() {
		for (int i = 2; i > 0; i--) {
			matrix[0][i] = true;
			matrix[1][i - 1] = true;
		}
	}
}
