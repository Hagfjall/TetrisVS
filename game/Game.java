package game;

import game.blocks.Shape;


public class Game extends Thread {
	private Board board;
	private Shape currentShape;
	private int currentX;
	private int currentY;

	private ShapeFactory factory;

	private Game(Board board, ShapeFactory factory) {
		this.board = board;
		this.factory = factory;
	}

	private boolean checkLeft() {

	}

	private boolean checkRight() {

	}

	private boolean checkOneDown() {
		boolean free = true;
		
	}

	private void getShape() {
		currentShape = factory.getShape();
	}

	//Tries to set a new shape. If false, game is lost.
	private boolean newShape() {
		getShape();
		currentX = (int) Math.round(((double)board.getWidth() / 2) - ((double)currentShape.getWidth() / 2));
		currentY = 0;
		putShapeOnBoard();
		return true;
	}
	
	private void putShapeOnBoard() {
		for (int r = 0; r < currentShape.getHeight(); r++) {
			for(int c = 0; c < currentShape.getWidth(); c++ ){
				if(currentShape.checkSlot(r, c)) {
					board.setSlot(currentX + r, currentY +c, currentShape.getType());
				}
			}
		}
	}
	
	private void failGame() {

	}
}
