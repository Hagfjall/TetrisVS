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


	public void printShape() {
		for (int r = 0; r < s.getHeight(); r++) {
			for (int c = 0; c < s.getWidth(); c++) {
				if (s.checkSlot(r, c)) {
					setSlot(r + currentY, c + currentX, s.getType());
				}
			}
		}
	}

	private void clear() {
		for (int r = 0; r < s.getHeight(); r++) {
			for (int c = 0; c < s.getWidth(); c++) {
				if (s.checkSlot(r, c)) {
					setSlot(r + currentY, c + currentX, (byte) 0);
				}
			}
		}
	}
	private void updateOld(){
		oldX = currentX;
		oldY = currentY;
		
	}

	public void rotateClockwise() {
		turnedClockwise = true;
		clear();
		s.rotate(true);
		printShape();
		

	}

	public void rotateCounterClockwise() {
		turnedClockwise = false;
		clear();
		s.rotate(false);
		printShape();
	}
	public void rollBack() {
		
		//rotated if true
		if(oldX == currentX && oldY == currentY){
			clear();
			s.rotate(!turnedClockwise);
		}
		else{
			
			clear();
			currentX = oldX;
			currentY = oldY;
		}
		printShape();
		
	}

	public void moveLeft() {
		int i = s.getMostWest();
		if (currentX + s.getMostWest() > 0) {
			clear();
			updateOld();
			currentX--;
			printShape();
		}
	}

	public void moveRight() {
		int i = s.getMostEast();
		if (currentX + s.getMostEast() < width - 1) {
			clear();
			updateOld();
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
			clear();
			updateOld();
			currentY++;
			printShape();
		}
	}

	public void fireAttack() {
		// TODO SKA inte finnas?

	}

}
