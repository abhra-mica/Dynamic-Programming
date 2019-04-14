package dynamic_greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cutting_A_Rod_Dynamic {

	public static void main(String[] args) {
		int[][] memoizationTable = new int[5][6];// Table to hold profits for total weight:rod length 

		int[] profits = { 2, 5, 9, 8 };
		
		 /* 0th row and column is filled with zeros only by declaration of the memoization array,this row and column are hypothetical for program
		    base condition*/		 
		for (int rodLength = 1; rodLength < memoizationTable.length; rodLength++) {
			for (int totalLength = 1; totalLength < memoizationTable[rodLength].length; totalLength++) {

				if (rodLength > totalLength) {
					memoizationTable[rodLength][totalLength] = memoizationTable[rodLength - 1][totalLength];
				} else {
					int remainingLength = totalLength - rodLength;
					memoizationTable[rodLength][totalLength] = Math
							.max(memoizationTable[rodLength - 1][totalLength],
									profits[rodLength - 1]// (rodLength-1) because in profits array rod length of 1 present in 0th index.															 
											+ memoizationTable[rodLength][remainingLength]);
				}
			}
		}

		System.out.println("Total value vs rod length profits-- ");
		for (int i = 0; i < memoizationTable.length; i++) {
			for (int j = 0; j < memoizationTable[i].length; j++) {
				System.out.print(memoizationTable[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("Maximum profit-- " + memoizationTable[4][5]);

		/* Below section decides lengths of rods to include in the set */
		List<Integer> rodLengths = new ArrayList<>();
		int row = memoizationTable.length - 1;
		int column = memoizationTable[row].length - 1;
		while (row > 0 && column >= 0) {
			if (memoizationTable[row][column] == memoizationTable[row - 1][column]) {// profit same from top cell,so this rod length not be included.
				row--;
			} else {
				rodLengths.add(row);
				column = column - row;// After including one rod length, decide the remaining length 
			}
		}
		Collections.sort(rodLengths);
		System.out.println("Rod lengths considered : "+ rodLengths);
	}
}