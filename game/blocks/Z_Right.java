package game.blocks;

public class Z_Right extends Shape {

	public Z_Right() {
		super(Shape.Z_RIGHT);
		matrix = new boolean[3][3];
		setMatrix();
	}

	private void setMatrix() {
		for (int i = 0; i < 2; i++) {
			matrix[0][i] = true;
			matrix[1][i + 1] = true;
		}
	}

	@Override
	public void rotate(boolean clockwise) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void reDraw() {
		// TODO Auto-generated method stub
		
	}

}
