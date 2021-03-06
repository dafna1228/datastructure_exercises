
public class Heap {

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
	private void percolate(int i){
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
	private int getParent(int i){
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
        if (size >= 1) {
            // put the last tank in the place of the root
            data[0] = data[size - 1];
            data[size - 1] = null;
            size--;
            // maxheapify the root
            if (size > 1) {
                maxHeapify(0);
            }
        }
	}

    private void maxHeapify(int i){
        int maxChild = getMaxChild(i) ;
        while (data[i].compareTo(data[maxChild]) < 0) {
            // swap i and maxChild
            Tank temp = data[maxChild];
            data[maxChild] = data[i];
            data[i] = temp;
            // change index of i and it's child to the new location
            i = maxChild;
            maxChild = getMaxChild(i);
        }
    }

    // get the index the child with the largest value
    private int getMaxChild(int i){
        int child1 = 2*i + 1;
        if ( child1 > size - 1) {
            // if i is a leaf index, return i
            return i;
        } else if (child1 == size - 1) {
        	return child1;
		}
        int child2 = child1 + 1;
        return data[child1].compareTo(data[child2]) > 0 ? child1 : child2;
    }

	/**
	 * Returns the tank with the k highest serial number in the heap.
	 * Should run in time O(klog(n)).
	 * 
	 * @param k
	 * @return the tank with the k highest serial number in the heap.
	 */
	public Tank findKbiggest(int k){
		// copy the heap
		Heap copyHeap = new Heap();
		for(int i = 0; i < size - 1; i++){
			copyHeap.insert(data[i]);
		}
		// extract the maximal element k times
		for (int i = 0; i < k - 1; i++) {
			copyHeap.extractMax();
		}
		// the root is now the k'th maximal element
		return copyHeap.findMax();
	}
	
	/**
	 * Removes the tank with the k highest serial number from the heap.
	 * Should run in time O(klog(n)).
	 * 
	 * @param k
	 */
	public void removeKbiggest(int k){
        Heap tempHeap = new Heap();
        // extract the maximal element k - 1 times, put it in tempHeap
        for (int i = 0; i < k - 1; i++) {
            tempHeap.insert(this.findMax());
            this.extractMax();
        }
        // the root is now the k'th maximal element. remove it
        this.extractMax();
        // return all the tanks we put in tempHeap
        for (int i = 0; i < k - 1; i++) {
            this.insert(tempHeap.data[i]);
        }
	}
	
	/**
	 * Checks if a given tank is a part of the heap.
	 * 
	 * @param t - the tank to be checked
	 * @return true if and only if the tank is in the heap.
	 */
	public boolean contains(Tank t){
		for (int i = 0; i < size; i++){
		    if (data[i].compareTo(t) == 0) {
		        return true;
            }
        }
		return false;
	}


}


