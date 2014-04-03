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
	public void rotate(boolean clockwise) {
		cleanMatrix();
		if(orientation == WEST){
			orientation = NORTH;
		}
		else orientation++;
		setOrient();
		
	}

	@Override
	protected void setOrient(byte orientation) {
		// TODO Auto-generated method stubS
		swithc(ori)
		case: nort: setNort();
		
	}

	tru true föase 
}
