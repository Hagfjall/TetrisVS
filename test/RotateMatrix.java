package test;

public class RotateMatrix {

	public static void main(String[] args) {

		String[][] first = new String[5][5];

		first[0][1] = "H";
		first[0][2] = "E";
		first[0][3] = "J";

		for (int i = 0; i < first.length; i++) {
			for (int j = 0; j < first[0].length; j++) {
				if (first[i][j] == null) {
					System.out.print(".");
				} else {
					System.out.print(first[i][j]);
				}
			}
			System.out.println();
		}

		// The magic code that turns everything 90´ clockvise
		for (int k = 1; k <= 6; k++) {
			String[][] sec = new String[first.length][first[0].length];
			for (int i = 0; i < first[0].length; i++) {
				for (int j = first.length - 1; j >= 0; j--) {
					sec[i][sec.length - 1 - j] = first[j][i];
				}
			}
			first = sec;
		}
		System.out.println("After rotation");

		for (int i = 0; i < first.length; i++) {
			for (int j = 0; j < first[0].length; j++) {
				if (first[i][j] == null) {
					System.out.print(".");
				} else {
					System.out.print(first[i][j]);
				}
			}
			System.out.println();
		}

	}

}
