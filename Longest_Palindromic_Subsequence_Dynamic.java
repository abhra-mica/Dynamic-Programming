package dynamic_greedy;

public class Longest_Palindromic_Subsequence_Dynamic {

	public static void main(String[] args) {
		String palindromSeq = "adbbca";
		int[][] memoizationTable = new int[6][6];

		/*
		 * Diagonals will be fill with 1 because every element in the diagonal
		 * represent a single letter which itself a palindrom of length 1
		 */
		for (int i = 0, j = 0; i < 6 && j < 6; i++, j++) {
			memoizationTable[i][j] = 1;
		}

		/*
		 * We are filling up the matrix diagonally up.In first iteration we
		 * started from m[0][1] then m[1][2] and so on.In next iteration we
		 * start from m[0][2] then m[1][3] and so on.In this way when we reaches
		 * to m[0][5], that will be the last cell to fill up.After that inner
		 * for loop will again modify row value as zero and column as 6,then we
		 * need to quit from the while loop.To start with properly increamented
		 * col value in the next iteration we are using
		 * nextIterationColumnStartIndex.
		 */
		int row = 0, col = 1, nextIterationColumnStartIndex = 1;
		while (!(row == 0 && col == 6)) {
			for (; row < 6 && col < 6; row++, col++) {
				if (palindromSeq.charAt(row) == palindromSeq.charAt(col)) {
					memoizationTable[row][col] = 2 + memoizationTable[row + 1][col - 1];
				} else {
					memoizationTable[row][col] = Math.max(
							memoizationTable[row][col - 1],
							memoizationTable[row + 1][col]);
				}
			}
			row = 0;
			nextIterationColumnStartIndex++;
			col = nextIterationColumnStartIndex;
		}

		// Print the memoization table.
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				System.out.print(memoizationTable[i][j] + " ");
			}
			System.out.println();
		}

		int longestPalindromLength = memoizationTable[0][5];
		System.out
				.println("So the lenngth of the longest palindromic subsequence: "
						+ longestPalindromLength);

		int start = 0;
		int end = longestPalindromLength - 1;
		char[] lps = new char[longestPalindromLength];

		int i = 0;
		int j = 5;
		while (i <= j) {// The moment i>j that means we came down under the
						// diagonal, so iteration must be stopped
			if (memoizationTable[i][j] == memoizationTable[i][j - 1]) {
				j = j - 1;
			} else if (memoizationTable[i][j] == memoizationTable[i + 1][j]) {
				i = i + 1;
			} else {// The value came from below diagonal cell
				lps[start] = palindromSeq.charAt(j);
				lps[end] = palindromSeq.charAt(j);
				i = i + 1;
				j = j - 1;
				start++;
				end--;
			}
		}

		System.out.print("Longest Palindromic Subsequence: ");
		for (int x = 0; x < lps.length; x++) {
			System.out.print(lps[x]);
		}
	}
}