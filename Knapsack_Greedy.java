package dynamic_greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Knapsack_Greedy {
	public static void main(String[] args) {

		int[] weights = { 2, 3, 5, 7, 1, 4, 1 };
		int[] profits = { 10, 5, 15, 7, 6, 18, 3 };
		knapsack(15, weights, profits);

	}

	static void knapsack(int capacity, int[] weights, int[] profits) {
		float[] profit_weight_ratio = new float[weights.length];// holds
																// profit/weights
																// ratio
		float profit = 0;

		for (int i = 0; i < weights.length; i++) {
			profit_weight_ratio[i] = (float) profits[i] / (float) weights[i];
		}

		List<Integer> objectSetected = new ArrayList<>();
		while (true) {
			int index = findMaxIndex(profit_weight_ratio);
			objectSetected.add(index + 1);
			if (capacity > weights[index]) {
				profit = profit + profit_weight_ratio[index] * weights[index];
				capacity -= weights[index];
				profit_weight_ratio[index] = 0.0f;
			} else {
				profit += profit_weight_ratio[index] * capacity;
				break;
			}

		}

		Collections.sort(objectSetected);
		System.out.print("Object included in Knapsack are : ");
		for (int i : objectSetected) {
			System.out.print(i + " ");
		}

		System.out.println("\nTotal Profit= " + profit);
	}

	static int findMaxIndex(float[] arr) {
		float max = arr[0];
		int index = 0;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
				index = i;
			}
		}
		return index;
	}
}
