package dynamic_greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Knapsack_0_1_Dynamic {

	public static void main(String[] args) {
		int[] weight = { 2, 3, 4, 5 };
		int[] profit = { 1, 2, 5, 6 };
		int capacity = 8;

		int[][] profitTable = knapsack(4, capacity, weight, profit);
		System.out.println("Maximum Profit= " + profitTable[4][capacity]);

		System.out.println("Items put inside bag:"
				+ itemSelected(profitTable, weight));
	}

	static int[][] knapsack(int noOfItems, int bagCapacity, int[] weight,
			int[] profit) {
		int mat[][] = new int[noOfItems + 1][bagCapacity + 1];
		int i = 0;
		int w = 0;

		// Create Memoization table in bottom up approach
		for (i = 0; i <= noOfItems; i++) {
			for (w = 0; w <= bagCapacity; w++) {
				if (i == 0 || w == 0) {
					mat[i][w] = 0;
				} else if (weight[i - 1] <= w) {
					mat[i][w] = max(mat[i - 1][w],
							mat[i - 1][w - weight[i - 1]] + profit[i - 1]);
				} else {
					mat[i][w] = mat[i - 1][w];
				}
			}
		}
        //Print profit table
		/*for (i = 0; i <= noOfItems; i++) {
			for (w = 0; w <= bagCapacity; w++) {
				System.out.print(mat[i][w] + " ");
			}
			System.out.println();
		}*/

		return mat;
	}

	static int max(int a, int b) {
		return a > b ? a : b;
	}

	static List<Integer> itemSelected(int[][] profitTable, int[] weight) {

		int row = profitTable.length - 1;
		int colum = profitTable[row].length - 1;
		List<Integer> selectedItems = new ArrayList<>();

		while (true) {
			if (profitTable[row][colum] != profitTable[row - 1][colum]) {
				selectedItems.add(row);
				colum = colum - weight[row - 1];
			}
			row--;

			if (colum < 1)
				break;
		}

		Collections.sort(selectedItems);
		return selectedItems;

	}

}
