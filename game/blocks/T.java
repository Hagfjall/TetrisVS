package game.blocks;

public class T extends Shape {

	public T() {
		super(Shape.T);
		matrix = new boolean[3][3];
		setMatrix();
	}

	private void setMatrix(){
		for(int i = 0; i < 3; i++)
			matrix[0][i] = true;
		matrix[1][1] = true;
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
