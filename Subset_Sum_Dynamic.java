package dynamic_greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Subset_Sum_Dynamic {

	public static void main(String[] args) {
		int sum = 8;
		int[] numSet = { 0, 1, 2, 5, 7 };
		boolean[][] memoizationTable = new boolean[numSet.length][sum + 1];

		/*
		 * With 0 we can make a sum of zero.With {0,1} also we can make sum of zero, with {0,1,2} also we can make.So {0,1,2,3,....} also we can make
		 * a sum of zero. That's why first column we fill up as true. With 0 we can't make a sum of 1,2,3,... anything That's why the first row will
		 * be false.But we don't have to declare as false.It will be by default false as it's a boolean array.
		 */

		for (int i = 0; i < numSet.length; i++) {
			memoizationTable[i][0] = true;
		}

		for (int row = 1; row < memoizationTable.length; row++) {
			for (int col = 1; col < memoizationTable[row].length; col++) {
				/*
				 * First condition : col represent the sum.So if our new number is greater than the sum then we can' include into our solution,So we
				 * will copy the value from the above cell which is representing the value excluding new number. Second condition: If with out
				 * including new number we can achive the sum means the above cell is giving TRUE, then after including the new number, if we can't
				 * use the new number to achive the sum, for new number we will get false.But the total result for that cell will be (TRUE or FALSE)=
				 * TRUE only.So if the above cell is true no need to check for the result from new number.Simpley copy the above cell value.
				 */
				if (numSet[row] > col || memoizationTable[row - 1][col]) {
					memoizationTable[row][col] = memoizationTable[row - 1][col];
				} else {
					/*
					 * If the above cell is false then after including the new number we need to see how much sum remains.Ex: new number = 5 and col
					 * is 8,so 8-5=3 is remaining.Already we have included the new number(5) so remaining 3 we have to achieve through the above cell
					 * 3 col value.which represent the value of acheiving sum 3 without having new number.
					 */
					int aboveCell = col - numSet[row];
					memoizationTable[row][col] = memoizationTable[row - 1][aboveCell];
				}
			}
		}

		// Display Memoization Table
		for (int i = 0; i < memoizationTable.length; i++) {
			for (int j = 0; j < memoizationTable[i].length; j++) {
				System.out.print(memoizationTable[i][j] + " ");
			}
			System.out.println();
		}

		int row = memoizationTable.length - 1;
		int col = memoizationTable[row].length - 1;
		List<Integer> nosSet = new ArrayList<>();
		while (row != 0 || col != 0) {
			if (memoizationTable[row - 1][col]) {
				row--;
				continue;
			}
			nosSet.add(numSet[row]);
			col = col - numSet[row];
		}
		Collections.sort(nosSet);
		System.out.println("Numbers use to form " + sum + " are " + nosSet);

	}
}
