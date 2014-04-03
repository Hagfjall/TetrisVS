package game.blocks;

public class L_Left extends Shape {

	public L_Left() {
		super(Shape.L_LEFT);
		matrix = new boolean[3][3];
		setMatrix();
	}

	private void setMatrix(){
		for(int i = 0; i < 3; i++)
			matrix[i][2] = true;
		matrix[2][1] = true;
	}

	@Override
	public boolean checkEmpty(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void rotate(boolean clockwise) {
		// TODO Auto-generated method stub

	}

}
