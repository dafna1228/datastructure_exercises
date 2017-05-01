
public class Heap {

	public static void main(String[] args) {
		Tank t1 = new Tank("ba");
		Tank t2 = new Tank("dbb");
		Heap h = new Heap();
		h.insert(t1);
		System.out.println(h);
		h.insert(t2);
		System.out.println(h);
		System.out.println(h.findMax());

	}

	private Tank[] data;
	private int size; 
	
	/**
	 * Creates an empty list.
	 */
	public Heap(){
		this.data = new Tank[10000];
		// init each tank in the array
		for (int i = 0; i < size; i++) {
			this.data[i] = new Tank("");
		}
		this.size = size();
	}
	
	/**
	 * Returns the size of the heap.
	 * 
	 * @return the size of the heap
	 */
	public int size(){
		return this.size;
	}
	
	/**
	 * Inserts a given tank into the heap.
	 * Should run in time O(log(n)).
	 * 
	 * @param t - the tank to be inserted.
	 */
	public void insert(Tank t){
		// insert the new tank at the end of the list, as a "leaf"
		data[size] = t;
		size++;
		if (size > 1) {
			// percolate the tank up until reaching a correct position
			percolate(size - 1);
		}
	}

	/**
	 * Move a node up the tree, as long needed, until it reaches correct level.
	 * runs in O(log(n)).
	 *
	 * @param i - the tank index in the list to be percolated.
	 */
	public void percolate(int i){
		int parentIndex = getParent(i) ;
		while (data[i].compareTo(data[parentIndex]) > 0) {
			// swap parent and child
			Tank temp = data[parentIndex];
			data[parentIndex] = data[i];
			data[i] = temp;
			// change index of i and it's parent to the new location
			i = parentIndex;
			parentIndex = getParent(i);
		}
	}

	// get the index of the parent node
	public int getParent(int i){
		if (i == 0) {
			//if the given index is root, return root
			return 0;
		}
		return i%2 == 1 ? (i-1)/2 : (i-2)/2;
	}

	/**
	 * Returns the tank with the highest serial number in the heap.
	 * Should run in time O(1).
	 * 
	 * @return the tank with the highest serial number in the heap.
	 */
	public Tank findMax(){
		return data[0];
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

	public String toString(){
		String ans = "[ ";
		for (int i = 0; i < size; i ++) {
			ans += data[i] + ", ";
		}
		return ans + "]";
	}
}
