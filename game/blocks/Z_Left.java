package game.blocks;

public class Z_Left extends Shape {

	public Z_Left() {
		super(Shape.Z_LEFT);
		matrix = new boolean[3][3];
		setMatrix();
	}

	private void setMatrix() {
		for (int i = 2; i > 0; i--) {
			matrix[0][i] = true;
			matrix[1][i - 1] = true;
		}
	}

	@Override
	public void rotate(boolean clockwise) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setOrient() {
		// TODO Auto-generated method stub
		
	}

}
