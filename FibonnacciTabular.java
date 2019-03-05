package practice;



public class FibonnacciTabular {
	public static void main(String[] args) {
		System.out.println("Fibonnacci Result-- "+ fib(7));
	}

	static int fib(int n) {
		int[] table = new int[10];
		if (n <= 1)
			return n;

		table[0] = 0;
		table[1] = 1;

		for (int i = 2; i <= n; i++) {
			table[i] = table[i - 2] + table[i - 1];
		}
		return table[n];
	}
}
