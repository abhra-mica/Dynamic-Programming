package dynamic_greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Longest_Increasing_Subsequence_Dynamic {

	public static void main(String[] args) {
		int[] array = { 0, 4, 12, 2, 10, 6, 9, 13, 3, 11, 7, 15 };
		Integer[] subseqLength = new Integer[array.length];// taken Integer type
															// just for using in
															// Collections.max().
		int[] subsequence = new int[array.length];// holds index of elements of
													// the subsequnce,from here
													// we will figure out the
													// elements in the subsqunce

		// populate subseqLength every element with 1 because every element is a
		// subsequence of length 1

		for (int i = 0; i < subseqLength.length; i++) {
			subseqLength[i] = 1;
		}
		int i = 1, j = 0;// Track the elements

		while (i < array.length) {
			for (j = 0; j < i; j++) {
				if (array[j] <= array[i]
						&& subseqLength[j] + 1 >= subseqLength[i]) {
					subseqLength[i] = subseqLength[j] + 1;
					subsequence[i] = j;
				}
			}
			i++;
		}

		int largestIncreasingSubseq = Collections.max(Arrays
				.asList(subseqLength));
		System.out.println("Length of the longest increasing subsequence: "
				+ largestIncreasingSubseq);

		// find the index of largestIncreasingSubseq value in the subseqLength
		// array
		int index = 0;
		for (int x = 0; x < subseqLength.length; x++) {
			if (subseqLength[x] == largestIncreasingSubseq) {
				index = x;
				break;
			}
		}

		List<Integer> subSequenceIndexes = new ArrayList<>();
		subSequenceIndexes.add(index);

		while (index != 0) {
			index = subsequence[index];
			subSequenceIndexes.add(index);
		}

		Collections.reverse(subSequenceIndexes);

		System.out.print("Longest increasing subsequence: ");
		for (int x = 0; x < subSequenceIndexes.size(); x++) {
			System.out.print(array[subSequenceIndexes.get(x)] + " ");
		}
	}
}
