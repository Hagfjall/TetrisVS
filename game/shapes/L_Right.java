package game.shapes;

public class L_Right extends Shape {

	public L_Right() {
		super(Shape.L_RIGHT);
		matrix = new boolean[3][3];
		init();
	}

	private void init() {
		for (int i = 0; i < 3; i++)
			matrix[i][0] = true;
		matrix[2][1] = true;
	}
}
