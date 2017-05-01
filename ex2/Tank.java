
public class Tank implements Comparable<Tank>{

	public static void main(String[] args) {
		Tank t1 = new Tank("bA");
		Tank t2 = new Tank("DBB");
		Tank t22 = new Tank("dbB");

		System.out.println(t1.compareTo(t2));
		System.out.println(t2.compareTo(t1));
		System.out.println(t22.compareTo(t2));
	}
	
	private String serialNumber;
	
	/**
	 * A standard constructor for the tank class.
	 * 
	 * @param serialNumber - the serial number of the tank
	 */
	public Tank(String serialNumber){
		this.serialNumber = serialNumber;
	}
	
	/**
	 * Compares this tank with another tank for order. 
	 * Returns a negative integer, zero, or a positive integer as the serial number of this tank is
	 * is less than, equal to, or greater than the serial number of the other tank.
	 * 
	 * @param other - the tank to be compared
	 * @return a negative integer, zero, or a positive integer as the serial number of this tank is 
	 * 		   less than, equal to, or greater than the serial number of the other tank.
	 */
	public int compareTo(Tank other) {
		return this.serialNumber() - other.serialNumber();
	}
	
	/**
	 * Convert the string serial number into a positive integer as explained in the assignment's
	 * document.
	 * 
	 * @return the integer represented by the serial number.
	 */
	public int serialNumber(){
		int result = 0;
		for (int i = 0; i < serialNumber.length(); i++){
			int num = charToInt(serialNumber.charAt(i));
			// sum the value of a letter
			result += num * (int) Math.pow(26,(serialNumber.length() - 1 - i));
		}
		return result;
	}

	// convert a char to int
	public int charToInt(char ch){
		// if capital letter
		if ((int) ch >= 65 && (int) ch <= 90) {
			return (int) ch - 65;
		// if lowercase letter
		} else if ((int) ch >= 97 && (int) ch <= 122) {
			return  (int) ch - 97;
		}
		return 0;
	}

	public String toString(){
		return serialNumber + " " + this.serialNumber();
	}
	
}
