//Requirement: How many ways Rs:5 can be can be provided with 1 to 5 denominators.

package dynamic_greedy;

public class CoinChange_Dynamic {

	public static void main(String[] args) {
		int[][] matrix = new int[6][6];

		/*
		 * Below for loop is provided to populate 0th column.0th column
		 * represent how many ways _we can fill provided Rs: 0 with denominators
		 * 1 to 5.It's a hypothetical column to provide as base condition.
		 */
		for (int i = 0; i < matrix.length; i++)
			matrix[i][0] = 1;

		/*
		 * Below nested for loops to populate the memoization table to get the
		 * answer.The answer is the last cell.
		 */
		for (int coinsRange = 1; coinsRange < matrix.length; coinsRange++) {
			for (int value = 1; value < matrix[coinsRange].length; value++) {
				if (value < coinsRange) {
					matrix[coinsRange][value] = matrix[coinsRange - 1][value];
				} else {
					matrix[coinsRange][value] = matrix[coinsRange - 1][value]
							+ matrix[coinsRange][value - coinsRange];
				}
			}
		}
		// Displaying the memoization table
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println("We can provide Rs: 5 with denomination 1 to 5 in "
				+ matrix[5][5] + " ways.");
	}
}
