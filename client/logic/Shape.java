package client.logic;

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
	
	
	public abstract boolean checkEmpty(int x, int y);
	public byte getType(){
		return type;
	}
	/**
	 * rotate the block inside the matrix
	 * @param clockwise or not
	 */
	public abstract void rotate(boolean clockwise);
	

}
