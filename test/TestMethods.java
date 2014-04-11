package test;

public class TestMethods {

	public static void printMatrix(byte[][] m) {
		for (int r = 0; r < m.length; r++) {
			for (int c = 0; c < m[0].length; c++) {
				if (m[r][c] == (byte) 0)
					System.out.print(".");
				else
					System.out.print(m[r][c]);
			}
			System.out.println(" ");
		}
		System.out.println(" ");
	}
}
