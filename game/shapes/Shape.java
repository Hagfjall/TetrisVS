package game.shapes;

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
	public static final byte NULL = I + 1;
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
	 * return true if the place is filled, otherwise false
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean checkSlot(int row, int col) {
		return insideMatrix(row, col) && matrix[row][col];

	}

	protected boolean insideMatrix(int row, int col) {
		return row >= 0 && row < matrix.length && col >= 0
				&& col < matrix[0].length;
	}

	/**
	 * rotate the block inside the matrix
	 * 
	 * @param clockwise
	 *            or not
	 */
	public void rotate(boolean clockwise) {
		int r;
		if (clockwise)
			r = 1;
		else
			r = 3;
		for (int k = 1; k <= r; k++) {
			boolean sec[][] = new boolean[matrix.length][matrix[0].length];

			for (int i = 0; i < matrix[0].length; i++) {
				for (int j = matrix.length - 1; j >= 0; j--) {
					sec[i][matrix.length - 1 - j] = matrix[j][i];
				}
			}
			matrix = sec;

		}
	}

	public byte getType() {
		return type;
	}

	public int getWidth() {
		return matrix.length;
	}

	public int getHeight() {
		return matrix[0].length;
	}

	/**
	 * Return the mostSouth value in the matrix that contains something. Returns
	 * -1 if (should never happen) it doesn't contains anything
	 * 
	 * @return
	 */
	public int getMostSouth() {
		for (int r = matrix.length - 1; r >= 0; r--) {
			for (int c = matrix.length - 1; c >= 0; c--) {
				if (checkSlot(r, c)) {
					return r;
				}
			}
		}
		return -1;
	}

	public int getMostWest() {
		for (int c = 0; c < matrix.length; c++) {
			for (int r = 0; r < matrix.length; r++) {
				if (checkSlot(r, c))
					return c;
			}
		}
		return -1;
	}

	public int getMostEast() {
		for (int c = matrix.length - 1; c >= 0; c--) {
			for (int r = 0; r < matrix.length; r++) {
				if (checkSlot(r, c))
					return c;
			}
		}
		return -1;
	}

	public void printShape() {
		for (int r = 0; r < getHeight(); r++) {
			for (int c = 0; c < getWidth(); c++) {
				if (matrix[r][c])
					System.out.print("#");
				else {
					System.out.print("-");
				}
			}
			System.out.println("");
		}
	}

}
