package game;

import game.blocks.Shape;

public class BlockBoard extends Board {
	int currentX;
	int currentY;

	public BlockBoard(int width, int height) {
		super(width, height);
		currentX = currentY = 0;
	}

	public boolean addShape(Shape s) {
		currentX = (int) Math.round(((double) getWidth() / 2)
				- ((double) s.getWidth() / 2));
		currentY = 0;
		for (int r = 0; r < s.getHeight(); r++) {
			for (int c = 0; c < s.getWidth(); c++) {
				if (checkSlot(currentX + r, currentY + c)
						&& s.checkSlot(r, c)) {
					return false;
				}
			}
		}
		putShapeOnBoard();
		return true;
	}

}
