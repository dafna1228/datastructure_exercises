
public class Simulation {

	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]); // total tanks produces
		int k = Integer.parseInt(args[1]); // tanks captured
		int T = Integer.parseInt(args[2]);

		// create an array of k random captured tanks
		int[] tankNums = new int[k];
		for (int i = 0; i < k; i++) {
			tankNums[i] = (int) (1 + Math.random() * (N));
		}
		for (int i = 0; i < k; i++){
			System.out.print(tankNums[i] + " ");
		}

		// create an estimation for these captured tanks
	}

	// translate the ints to a base 26 string
	public static String[] intTo26 (int[] arr) {
		String[] tankStrings = new String[arr.length];

		return tankStrings;
	}
}
