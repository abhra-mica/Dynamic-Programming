package dynamic_greedy;

public class Robot_Movement {

	public static void main(String[] args) {
		int[][] roboMatrix = new int[4][4];

		// Fill up first row and column by 1 because all the cell of first row
		// and column have only one way to traverse
		for (int i = 0; i < 4; i++) {
			roboMatrix[0][i] = 1;
			roboMatrix[i][0] = 1;
		}

		// No. of ways to reach all the other cells can be found by the value of
		// adding top and left cell value

		for (int i = 1; i < 4; i++) {
			for (int j = 1; j < 4; j++) {
				roboMatrix[i][j] = roboMatrix[i - 1][j] + roboMatrix[i][j - 1];
			}
		}
		System.out.println("Robot can reach the bottom right corner in "
				+ roboMatrix[3][3] +" ways.");
	}

}