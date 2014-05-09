package game.shapes;

public class Z_Right extends Shape {

	public Z_Right() {
		super(Shape.Z_RIGHT);
		matrix = new boolean[3][3];
		init();
	}

	private void init() {
		for (int i = 0; i < 2; i++) {
			matrix[0][i] = true;
			matrix[1][i + 1] = true;
		}
	}
}
