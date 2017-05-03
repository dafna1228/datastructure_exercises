
public class TankEstimator {

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
