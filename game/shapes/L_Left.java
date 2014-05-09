package game.shapes;

public class L_Left extends Shape {

	public L_Left() {
		super(Shape.L_LEFT);
		matrix = new boolean[3][3];
		init();
	}

	private void init(){
		for(int i = 0; i < 3; i++)
			matrix[i][2] = true;
		matrix[2][1] = true;
	}

	

}
