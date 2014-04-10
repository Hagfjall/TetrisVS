package game;

import game.blocks.Shape;

//Hello
public class ShapeBoard extends Board {
	int currentX, oldX;
	int currentY, oldY;
	private Shape s;
	private boolean rotated = true, turnedClockwise = true;

	public ShapeBoard(int row, int col) {
		super(row, col);
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

	public void printShape() {
		for (int r = 0; r < s.getHeight(); r++) {
			for (int c = 0; c < s.getWidth(); c++) {
				if (s.checkSlot(r, c)) {
					setSlot(r + currentY, c + currentX, s.getType());
				}
			}
		}
	}

	private void clearAndUpdateOld() {
		for (int r = 0; r < s.getHeight(); r++) {
			for (int c = 0; c < s.getWidth(); c++) {
				if (s.checkSlot(r, c)) {
					setSlot(r + currentY, c + currentX, (byte) 0);
				}
			}
		}
		oldX = currentX;
		oldY = currentY;
	}

	public void rotateClockwise() {
		// TODO Auto-generated method stub

	}

	public void rotateCounterClockwise() {
		// TODO Auto-generated method stub

	}

	public void moveLeft() {
		int i = s.getMostWest();
		if (currentX + s.getMostWest() > 0) {
			clearAndUpdateOld();
			currentX--;
			printShape();
		}
	}

	public void moveRight() {
		int i = s.getMostEast();
		if (currentX + s.getMostEast() < width - 1) {
			clearAndUpdateOld();
			currentX++;
			printShape();
		}
	}

	public void moveBottom() {
		// TODO will not be needed?

	}

	public void moveDown() {
		int i = s.getMostSouth();

		if (currentY + s.getMostSouth() < height - 1) {
			clearAndUpdateOld();
			currentY++;
			printShape();
			// ta bort föregående slots
		}
	}

	public void fireAttack() {
		// TODO Auto-generated method stub

	}

}
