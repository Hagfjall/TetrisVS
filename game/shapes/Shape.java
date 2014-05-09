package game.shapes;

public abstract class Shape {

	/* SHAPES ID */
	public static final byte SQUARE = 1;
	public static final byte T = SQUARE + 1;
	public static final byte L_RIGHT = T + 1;
	public static final byte L_LEFT = L_RIGHT + 1;
	public static final byte Z_RIGHT = L_LEFT + 1;
	public static final byte Z_LEFT = Z_RIGHT + 1;
	public static final byte I = Z_LEFT + 1;
	public static final byte NULL = I + 1;

	protected byte type;
	protected int orientation;
	protected boolean[][] matrix;

	public Shape(byte type) {
		this.type = type;
	}

	/**
	 * return true if the place is filled, otherwise false
	 * 
	 * @param row
	 * @param col
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
		int turns;
		if (clockwise)
			turns = 1;
		else
			turns = 3;
		for (int k = 1; k <= turns; k++) {
			boolean sec[][] = new boolean[getHeight()][getWidth()];

			for (int i = 0; i < getHeight(); i++) {
				for (int j = getWidth() - 1; j >= 0; j--) {
					sec[i][getWidth() - 1 - j] = matrix[j][i];
				}
			}
			matrix = sec;

		}
	}

	public byte getType() {
		return type;
	}

	public int getWidth() {
		return matrix[0].length;
	}

	public int getHeight() {
		return matrix.length;
	}

	/**
	 * Return the mostSouth value in the matrix that contains something. 
	 * 
	 * @return -1 if the matrix is empty
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

	/**
	 * returns the most west value in the matrix that contains something
	 * @return -1 if the matrix is empty
	 */
	public int getMostWest() {
		for (int c = 0; c < matrix.length; c++) {
			for (int r = 0; r < matrix.length; r++) {
				if (checkSlot(r, c))
					return c;
			}
		}
		return -1;
	}

	/**
	 * returns the most east value in the matrix that contains something
	 * @return -1 if the matrix is empty
	 */
	public int getMostEast() {
		for (int c = matrix.length - 1; c >= 0; c--) {
			for (int r = 0; r < matrix.length; r++) {
				if (checkSlot(r, c))
					return c;
			}
		}
		return -1;
	}
}
