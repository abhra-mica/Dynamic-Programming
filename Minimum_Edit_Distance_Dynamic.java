package dynamic_greedy;

public class Minimum_Edit_Distance_Dynamic {

	public static void main(String[] args) {
		String str1 = "abcfg";
		String str2 = "adceg";

		int[][] memoizationTable = new int[str1.length() + 1][str2.length() + 1];
		/*
		 * First row represents no of steps to convert null to a String.Like
		 * Null --> a : 1 step Null --> ab : 2 steps etc. First column
		 * represents no. of steps to convert a String to null.Like a--> Null: 1
		 * step ab--> Null:2 steps etc.Loop-1 and Loop-2 are filling up first
		 * row and column
		 */
		// Loop-1
		for (int i = 0; i < memoizationTable[0].length; i++) {
			memoizationTable[0][i] = i;
		}
		// Loop-2
		for (int j = 0; j < memoizationTable[0].length; j++) {
			memoizationTable[j][0] = j;
		}

		for (int row = 1; row <= str2.length(); row++) {
			for (int col = 1; col <= str1.length(); col++) {
				if (str2.charAt(row - 1) == str1.charAt(col - 1)) {
					memoizationTable[row][col] = memoizationTable[row - 1][col - 1];
				} else {
					memoizationTable[row][col] = 1 + Math.min(
							Math.min(memoizationTable[row][col - 1],// insert
									memoizationTable[row - 1][col - 1]),// replace
							memoizationTable[row - 1][col]);// remove
				}
			}
		}

		for (int i = 0; i < memoizationTable.length; i++) {
			for (int j = 0; j < memoizationTable[i].length; j++) {
				System.out.print(memoizationTable[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println("Min operation required: " + memoizationTable[5][5]);

		// Backtrack to find types and no of operations
		int row = memoizationTable.length - 1;
		int col = memoizationTable[row].length - 1;
		int insert = 0, replace = 0, remove = 0;
		
		while (row > 0 || col > 0) {
			if (str2.charAt(row-1) == str1.charAt(col-1)) {// Means no operation,
														// simply copied value
														// from top corner cell.
				row--;
				col--;
			} else {
				int min = Math.min(Math.min(memoizationTable[row][col - 1],// insert
						memoizationTable[row - 1][col - 1]),// replace
						memoizationTable[row - 1][col]);// remove
				
				if (min == memoizationTable[row][col - 1]) {
					insert++;
					col--;
				} else if (min == memoizationTable[row - 1][col - 1]) {
					replace++;
					row--;
					col--;
				} else {
					remove++;
					row--;
				}
			}
		}

		System.out.println("Total operations performed:");
		System.out.println("INSERT-- " + insert);
		System.out.println("REPLACE-- " + replace);
		System.out.println("REMOVE-- " + remove);
	}
}
