public class FibonnacciSeries {
	static int[] dictionary = new int[50];

	// Fibonacci solution using Memoization
	public static int fibRec(int n) {
		if (n < 2)
			return 1;
		if (dictionary[n] != 0)
			return dictionary[n];
		else {
			dictionary[n] = fibRec(n - 1) + fibRec(n - 2);
			return dictionary[n];
		}
	}

	public static void main(String[] args) {
		System.out.println(fibRec(20));
	}
}
