package test;

public class AntonTest {

	public static void main(String[] args) {
		int x = 5;
		int y = 10;
		int[][] matrix = new int[x][y];

		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				System.out.print(j);
			}
			System.out.print("\n");
		}
		System.out.println(matrix.length);
		

	}

}
