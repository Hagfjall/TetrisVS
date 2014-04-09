package game;

import game.blocks.Shape;
//Hello
public class ShapeBoard extends Board {
	int currentX;
	int currentY;
	private Shape s;

	public ShapeBoard(int width, int height, Shape s) {
		super(width, height);
		this.s = s;
		currentX = currentY = 0;
		currentX = (int) Math.round(((double) getWidth() / 2)
				- ((double) s.getWidth() / 2));
		currentY = 0;
		
		
	}

	public boolean addShape(Shape s) {
		putShapeOnBoard();
		return true;
	}
	public void setShape(int x, int y){
		for (int r = 0; r < s.getHeight(); r++) {
			for (int c = 0; c < s.getWidth(); c++) {
				if (checkSlot(currentX + r, currentY + c)
						&& s.checkSlot(r, c)) {
				}
			}
		}
	}
	
	public void moveDown(){
		
	}

}
