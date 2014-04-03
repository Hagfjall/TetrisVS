package game.blocks;

public class I extends Shape {

	public I() {
		super(Shape.I);
		matrix = new boolean[4][4];
		init();
	}

	private void init() {
		for (int i = 0; i < 4; i++)
			matrix[i][1] = true;
	}
}
