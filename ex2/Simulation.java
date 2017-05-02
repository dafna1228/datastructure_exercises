
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

        TankEstimator te = new TankEstimator();
        for (int i = 0; i < k; i++){
            te.captureTank(new Tank(intTo26(tankNums[i])));
        }
        System.out.println();
        System.out.println(te.estimateProduction());
		/*

		// create an estimation for these captured tanks

		        System.out.println();

		String[] tankNames = new String[k];
        for (int i = 0; i < k; i++){
            tankNames[i] = intTo26(tankNums[i]);
        }

        for (int i = 0; i < k; i++){
            System.out.print(tankNames[i] + " ");
        }

        System.out.println();

        Tank[] tanklist = new Tank[k];
        for (int i = 0; i < k; i++){
            tanklist[i] = new Tank(tankNames[i]);
            System.out.print(tanklist[i].serialNumber() + " ");
        }
		*/
	}

	// translate the ints to a base 26 string
	public static String intTo26 (int num) {
		String tankStrings = "";

		int length = String.valueOf(num).length();
        for (int i = 0; i < length; i++){
            tankStrings = (char) ((num % 26) + 97) + tankStrings;
            num = num / 26;
            //System.out.println(num / 26);
        }

		return tankStrings;
	}
}
