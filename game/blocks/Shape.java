package game.blocks;


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
	protected static final byte NORTH = 1;
	protected static final byte EAST = 2;
	protected static final byte SOUTH = 3;
	protected static final byte WEST = 4;

	protected byte type;
	protected int orientation;
	protected boolean[][] matrix;

	public Shape(byte type) {
		this.type = type;
		orientation = NORTH;
	}

	/**
	 * return true if the place is empty or outside the matrix
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean checkSlot(int x, int y) {
		return insideMatrix(x, y) && matrix[x][y];

	}

	protected boolean insideMatrix(int x, int y) {
		return x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length;
	}

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
				if (matrix[i][j])
					System.out.print("*");
				else {
					System.out.print(" ");
				}
			}
			System.out.println("");
		}
	}

	protected void cleanMatrix() {
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				matrix[i][j] = false;
			}
		}
	}
	protected abstract void setOrient();
}
