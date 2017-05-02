
public class TankEstimator {

	public static void main(String[] args) {
		Tank t1 = new Tank("ba");
		Tank t2 = new Tank("dbb");
		Tank t3 = new Tank("dFb");
		Tank t4 = new Tank("F");
		Tank t5 = new Tank("dr");
		Tank t6 = new Tank("F");
		TankEstimator te = new TankEstimator();
		te.captureTank(t1);
		te.captureTank(t2);
		te.captureTank(t3);
		te.captureTank(t4);
		te.captureTank(t5);
		te.captureTank(t6);
		System.out.print(te.estimateProduction());
	}
		private Heap h;
	
	/**
	 * Creates a new estimator with an empty heap.
	 */
	public TankEstimator(){
		this.h = new Heap();
	}
	
	/**
	 * Adds the data of a new captured tank
	 * 
	 * @param t - the captured tank.
	 */
	public void captureTank(Tank t){
		this.h.insert(t);
	}
	
	/**
	 * Estimates the total number of produced tanks, based on the information of captured tanks.
	 * Estimation is done according to the formula presented in the assignment's document.
	 * 
	 * @return an estimation to the total number of produced tanks
	 */
	public int estimateProduction(){
		return h.findMax().serialNumber() + (h.findMax().serialNumber() / h.size()) - 1;
	}
}
