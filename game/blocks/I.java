package game.blocks;

public class I extends Shape {

	public I() {
		super(Shape.I);
		matrix = new boolean[4][4];
		reDraw();
	}


	@Override
	public void rotate(boolean clockwise) {
		cleanMatrix();
		if(clockwise){
		if (orientation == WEST) {
			orientation = NORTH;
		} else
			orientation++;
		}
		else{
			if(orientation == NORTH){
				orientation = WEST;
			}
			else{
				orientation--;
			}
		}
		reDraw();

	}

	@Override
	protected void reDraw() {
		switch (orientation) {
		case NORTH:
			for (int i = 0; i < 4; i++) {
				matrix[1][i] = true;
			}
			break;
		case EAST:
			for (int i = 0; i < 4; i++) {
				matrix[i][1] = true;
			}
			break;
		case SOUTH:
			for (int i = 0; i < 4; i++) {
				matrix[2][i] = true;
			}
			break;
		case WEST:
			for (int i = 0; i < 4; i++) {
				matrix[i][2] = true;
			}
		}
	}
}
