package game.blocks;

public class L_Right extends Shape {

	public L_Right() {
		super(Shape.L_RIGHT);
		matrix = new boolean[3][3];
		setMatrix();
	}

	private void setMatrix(){
		for(int i = 0; i < 3; i++)
			matrix[i][0] = true;
		matrix[2][1] = true;
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
