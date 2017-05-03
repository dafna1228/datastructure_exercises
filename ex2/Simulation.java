
public class Simulation {

	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]); // total tanks produces
		int k = Integer.parseInt(args[1]); // tanks captured
		int T = Integer.parseInt(args[2]); // num of experiments

		if (k > N){
			throw new IllegalArgumentException();
		}

		int tankSum = 0;
		// run T experiments
		for (int j = 0; j < T; j++) {

			// create an array of k random and unique numbers in range 1 to N
			int[] tankNums = new int[k];
			int size = 0;
			while (size < k) {
				// add a number from 1 to k, if it isn't on the list already
				int num = (int) (1 + Math.random() * (N));
				if ( !contains(num, tankNums)) {
					tankNums[size] = num;
					size++;
				}
			}

			TankEstimator te = new TankEstimator();
			for (int i = 0; i < k; i++) {
				te.captureTank(new Tank(intTo26(tankNums[i])));
			}
			tankSum += te.estimateProduction();
		}
		System.out.println(tankSum / T);
	}

	// translate the ints to a base 26 string
	private static String intTo26 (int num) {
		String tankStrings = "";
		int length = String.valueOf(num).length();
        for (int i = 0; i < length; i++){
            tankStrings = (char) ((num % 26) + 97) + tankStrings;
            num = num / 26;
        }
		return tankStrings;
	}

	private static Boolean contains (int num, int[] arr) {
		for (int i = 0; i < arr.length; i++){
			if (arr[i] == num){
				return true;
			}
		}
		return false;
	}

}
