package game;

import game.blocks.Shape;

//Hello
public class ShapeBoard extends Board {
	int currentX, oldX;
	int currentY, oldY;
	private Shape s;
	private boolean rotated = true, turnedClockwise = true;

	public ShapeBoard(int width, int height) {
		super(width, height);
		currentX = currentY = 0;

	}

	public void setShape(Shape s) {
		this.s = s;
		currentX = (int) Math.round(((double) getWidth() / 2)
				- ((double) s.getWidth() / 2));
		currentY = 0;
	}

	public void rollBack() {

	}

	public void moveDown() {
		if (currentY + s.getMostSouth() < height) {
			updateOld();
			clearBoard();
			currentY++;
			printShape();
			// ta bort föregående slots
		}
	}

	public void printShape() {
		for (int r = 0; r < s.getHeight(); r++) {
			for (int c = 0; c < s.getWidth(); c++) {
				if(s.checkSlot(r, c)){
					setSlot(r,c,s.getType());
				}
			}
		}
	}

	private void updateOld() {
		oldX = currentX;
		oldY = currentY;
	}

	private void clearBoard() {
		for (int r = 0; r < s.getHeight(); r++) {
			for (int c = 0; c < s.getWidth(); c++) {
				if (s.checkSlot(r, c)) {
					board[r][c] = 0;
				}
			}
		}
	}

}
