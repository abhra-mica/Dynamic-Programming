package dynamic_greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Longest_Common_Subsequence_Dynamic {

	/*    empty b q d r c v e f g h
	 * empty  0 0 0 0 0 0 0 0 0 0 0 
	 *     a  0 0 0 0 0 0 0 0 0 0 0
	 *     b  0 1 1 1 1 1 1 1 1 1 1
	 *     c  0 1 1 1 1 2 2 2 2 2 2
	 *     v  0 1 1 1 1 2 3 3 3 3 3
	 *     d  0 1 1 2 2 2 3 3 3 3 3
	 *     e  0 1 1 2 2 2 3 4 4 4 4 
	 *     f  0 1 1 2 2 2 3 4 5 5 5
	 *     g  0 1 1 2 2 2 3 4 5 6 6
	 *     h  0 1 1 2 2 2 3 4 5 6 7 
	 */

	public static void main(String[] args) {
		String str1 = "abcvdefgh";//
		String str2 = "bqdrcvefgh";

		int[][] memoizationTable = new int[str1.length() + 1][str2.length() + 1];

		//Populate memoization table
		for (int row = 1; row <= str1.length(); row++) {
			for (int col = 1; col <= str2.length(); col++) {
				if (str1.charAt(row - 1) == str2.charAt(col - 1)) {//Take diagonal value + 1 
					memoizationTable[row][col] = memoizationTable[row - 1][col - 1] + 1;
				} else {
					memoizationTable[row][col] = Math.max(
							memoizationTable[row - 1][col],
							memoizationTable[row][col - 1]);
				}
			}
		}
		// Print memoization table
		for (int i = 0; i < memoizationTable.length; i++) {
			for (int j = 0; j < memoizationTable[i].length; j++) {
				System.out.print(memoizationTable[i][j] + " ");
			}
			System.out.println();
		}

		System.out
				.println("Length of Longest Common Subsequence: "
						+ memoizationTable[memoizationTable.length - 1][memoizationTable[0].length - 1]);

		// List of letters in sequence
		List<Character> letters = new ArrayList<>();
		int row = memoizationTable.length - 1;
		int column = memoizationTable[row].length - 1;
		while (row > 0 && column >= 0) {
			if (memoizationTable[row][column] == memoizationTable[row - 1][column]) {//Check the value came from top cell
				row--;
			} else if (memoizationTable[row][column] == memoizationTable[row][column - 1]) {//check the value came from list side cell
				column--;
			} else {//value derived from corner cell
				letters.add(str1.charAt(row - 1));
				row--;
				column--;
			}
		}
		Collections.sort(letters);
		System.out.println("Longest common subsequence: " + letters);
	}
}
