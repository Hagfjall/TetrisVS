package game.blocks;

import client.logic.Orientation;

public abstract class Shape {

	/**
	 * SHAPES ID
	 */
	public static final byte SQUARE = 1;
	public static final byte T = SQUARE + 1;
	public static final byte L_RIGHT = T + 1;
	public static final byte L_LEFT = L_RIGHT + 1;
	public static final byte Z_RIGHT = L_LEFT + 1;
	public static final byte Z_LEFT = Z_RIGHT + 1;
	public static final byte I = Z_LEFT + 1;

	protected byte type;
	protected Orientation orientation;
	protected boolean[][] matrix;

	public Shape(byte type) {
		this.type = type;
	}

	/**
	 * return true if the place is empty or outside the matrix
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public abstract boolean checkEmpty(int x, int y);

	/**
	 * rotate the block inside the matrix
	 * 
	 * @param clockwise
	 *            or not
	 */
	public abstract void rotate(boolean clockwise);

	public byte getType() {
		return type;
	}

	public int getWidth() {
		return matrix.length;
	}

	public int getHeight() {
		return matrix[0].length;
	}

	public void printShape() {
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				if(matrix[i][j])
					System.out.print("*");
				else{
					System.out.print(" ");
				}
			}
			System.out.println("");
		}
	}

}
