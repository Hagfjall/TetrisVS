package game.blocks;

public class Square extends Shape {

	public Square() {
		super(Shape.SQUARE);
		matrix = new boolean[2][2];
		setMatrix();
	}

	private void setMatrix(){
		for(int i = 0; i < 2; i++)
			for(int j = 0; j < 2; j++)
				matrix[i][j] = true;
	}

	@Override
	public void rotate(boolean clockwise) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setOrient(byte orientation) {
		// TODO Auto-generated method stub
	}

}
