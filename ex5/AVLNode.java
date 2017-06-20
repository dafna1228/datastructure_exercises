package HW5;
// Basic node stored in AVL trees

class AVLNode {
	public static void main(String[] args) {
		Tank t1 = new Tank("bA");
		Tank t2 = new Tank("DBB");
		Tank t22 = new Tank("dbB");

		System.out.println(t1);
		System.out.println(t2);

		System.out.println(t1.compareTo(t2));
		System.out.println(t2.compareTo(t1));

		AVLNode n1 = new AVLNode(t1);
		AVLNode n2 = new AVLNode(t1, null, n1, n1 );

		System.out.println(n1);
		System.out.println(n2);
	}
	
	Tank t;				// The data in the node
	AVLNode parent;		// The parent
	AVLNode left;       // Left child
	AVLNode right;      // Right child
	int height;       	// Height

	/**
	 * A standard constructor, sets all pointers to null.
	 * 
	 * @param t - the data of the node.
	 */
    AVLNode(Tank t) {
          this.t = t;
          this.height = 0;
          this.parent = null;
          this.left = null;
          this.right = null;
    }
    
    /**
     * A standard constructor
     * 
     * @param t - the data of the node.
     * @param lt - the left child.
     * @param rt - the right child.
     * @param parent - the parent.
     */
    AVLNode(Tank t, AVLNode lt, AVLNode rt, AVLNode parent){
		this.t = t;
		this.height = parent.height - 1;
		this.parent = parent;
		this.left = lt;
		this.right = rt;
    }

	public String toString(){
		return this.t;
	}
}