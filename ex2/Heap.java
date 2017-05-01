
public class Heap {

	private Tank[] data;
	private int size; 
	
	/**
	 * Creates an empty list.
	 */
	public Heap(){
		//Your code comes here
	}
	
	/**
	 * Returns the size of the heap.
	 * 
	 * @return the size of the heap
	 */
	public int size(){
		//Your code comes here
		return 0;
	}
	
	/**
	 * Inserts a given tank into the heap.
	 * Should run in time O(log(n)).
	 * 
	 * @param t - the tank to be inserted.
	 */
	public void insert(Tank t){
		//Your code comes here
	}
	
	/**
	 * Returns the tank with the highest serial number in the heap.
	 * Should run in time O(1).
	 * 
	 * @return the tank with the highest serial number in the heap.
	 */
	public Tank findMax(){
		//Your code comes here
		return null;
	}
	
	/**
	 * Removes the tank with the highest serial number from the heap.
	 * Should run in time O(log(n)).
	 * 
	 */
	public void extractMax(){
		//Your code comes here
	}
	
	
	/**
	 * Returns the tank with the k highest serial number in the heap.
	 * Should run in time O(klog(n)).
	 * 
	 * @param k
	 * @return the tank with the k highest serial number in the heap.
	 */
	public Tank findKbiggest(int k){
		//Your code comes here
		return null;
	}
	
	/**
	 * Removes the tank with the k highest serial number from the heap.
	 * Should run in time O(klog(n)).
	 * 
	 * @param k
	 */
	public void removeKbiggest(int k){
		//Your code comes here
	}
	
	/**
	 * Checks if a given tank is a part of the heap.
	 * 
	 * @param t - the tank to be checked
	 * @return true if and only if the tank is in the heap.
	 */
	public boolean contains(Tank t){
		//Your code comes here
		return false;
	}
}
