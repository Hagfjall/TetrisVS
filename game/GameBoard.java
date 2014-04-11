package game;

import java.awt.Point;

import game.blocks.Shape;

public class GameBoard extends Board {

	
	public GameBoard(int width, int height) {
		super(width, height);
	}
	
	public void setShape(Point p, Shape s){
		for(int row = 0; row < s.getWidth(); row++){
			for (int col = 0; col < s.getHeight(); col++){
				if(s.checkSlot(row, col)){
					setSlot(row, col, s.getType());
				}
			}
		}
	}
	

	public void removeRows(int nbrrows) {
		byte[][] temp = new byte[width][height];
		for (int r = 0; r < height-nbrrows; r++) {
			for (int c = 0; c < width; c++) {
				temp[c][r+nbrrows] = board[c][r];
			}
		}
		board = temp;
	}
}
