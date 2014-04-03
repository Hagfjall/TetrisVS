package game.blocks;

public class I extends Shape {

	public I() {
		super(Shape.I);
		matrix = new boolean[4][4];
		setMatrix();
	}

	private void setMatrix(){
		for(int i = 0; i < 4; i++)
			matrix[i][0] = true;
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
