//package HW5;

public class AVLTree {

    public static void main(String[] args){
        AVLTree Test = new AVLTree();
        Tank Tank1 = new Tank("a");
        Tank Tank2 = new Tank("b");
        Tank Tank3 = new Tank("c");
        Tank Tank4 = new Tank("d");
        Tank Tank5 = new Tank("e");
        Tank Tank6 = new Tank("f");
        Tank Tank7 = new Tank("g");
        Tank Tank8 = new Tank("h");
        Tank[] ordered = new Tank[7];

        Test.insert(Tank4);
        Test.insert(Tank5);
        Test.insert(Tank6);
        Test.insert(Tank2);
        Test.insert(Tank3);
        Test.insert(Tank1);
        Test.insert(Tank8);
        Test.insert(Tank7);

//        Test.insert(Tank7);
//        Test.insert(Tank8);
//        Test.insert(Tank1);
//        Test.insert(Tank3);
//        Test.insert(Tank2);
//        Test.insert(Tank6);
//        Test.insert(Tank5);
//        Test.insert(Tank4);

        System.out.println();
        System.out.println("PRINTING");
        ordered = Test.inorder();
        for (int i =0; i< ordered.length; i++){
            System.out.println(ordered[i]);
        }
    }

    AVLNode root; 	// The tree root.
    int size;		// The size of the tree.

   
    /**
     * Construct an empty tree.
     */
    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Insert into the tree; You may assume that every tank is unique.
     * That is, no tank will appear twice.
     * 
     * @param t the tank to insert.
     */
    public void insert(Tank t) {
        AVLNode node = insert_bst(t, this);
        // balance all the new node's parents
        AVLNode parent = node.parent;
        while (parent != null){
            if (Math.abs(BalanceFactor(parent)) > 1) {
                //Left Left Case
                if (BalanceFactor(parent) > 1 && t.compareTo(parent.left.t) < 0) {
                    rightRotate(parent);
                }
                //Right Right Case
                if (BalanceFactor(parent) < -1 && t.compareTo(parent.right.t) > 0) {
                    leftRotate(parent);
                }
                // Left Right Case
                if (BalanceFactor(parent) > 1 && t.compareTo(parent.left.t) > 0) {
                    parent.left = leftRotate(parent.left);
                    rightRotate(parent);
                }
                // Right Left Case
                if (BalanceFactor(parent) < -1 && t.compareTo(parent.right.t) < 0 ) {
                    parent.right = rightRotate(parent.right);
                    leftRotate(parent);
                }
            }
            parent = parent.parent;
        }
    }

    // insert using the BST insert algorithm, might create an unbalanced tree
    public AVLNode insert_bst(Tank n, AVLTree T) {
        AVLNode node = new AVLNode(n);
        AVLNode parent = null;
        AVLNode x = T.root;
        // find the parent for the new tank
        while (x != null){
            parent = x;
            if (node.t.compareTo(x.t) < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        node.parent = parent;

        if (parent == null){
            // tree is empty
            T.root = node;
            node.height = 0;
            this.size++;
            return node;
            // else, update the parents of new node
        } else if (node.t.compareTo(parent.t)< 0){
            parent.left = node;
        } else {
            parent.right = node;
        }

        // update height for all the new node's parents
        parent = node.parent;
        node.height = 0;
        while (parent != null){
            parent.height = Math.max(getHeight(parent.left), getHeight(parent.right)) + 1;
            parent = parent.parent;
        }
        this.size++;
        return node;
    }

    // Left rotate
    public AVLNode leftRotate(AVLNode x){
        AVLNode z = x.right;
        AVLNode t23 = z.left;
        x.right = t23;
        if (t23 != null ) {
            t23.parent = x;
        }
        // switch locations and update parent/ child
        z.parent = x.parent;
        if (x.parent != null) {
            if (x.parent.left == x) {
                z.parent.left = z;
            } else {
                z.parent.right = z;
            }
        }
        z.left = x;
        x.parent = z;

        if (z.parent == null){
            this.root = z;
        }

        // update heights
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        z.height = Math.max(getHeight(z.left), getHeight(z.right)) + 1;
        return z;
    }

    // Right Right rotate
    public AVLNode rightRotate(AVLNode x){
        AVLNode z = x.left;
        AVLNode t23 = z.right;
        x.left = t23;
        if (t23 != null ) {
            t23.parent = x;
        }
        // switch locations and update parent/ child
        z.parent = x.parent;
        if (x.parent != null) {
            if (x.parent.left == x) {
                z.parent.left = z;
            } else {
                z.parent.right = z;
            }
        }
        z.right = x;
        x.parent = z;
        if (z.parent == null){
            this.root = z;
        }
        // update height
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        z.height = Math.max(getHeight(z.left), getHeight(z.right)) + 1;
        return z;
    }

    // return the height of the node. if it does not exist, return -1
    public int getHeight(AVLNode n) {
        if (n != null) {
            return n.height;
        }
        return -1;
    }

    // return the difference of heights between left and right subtrees of the node
    public int BalanceFactor( AVLNode node){
        return getHeight(node.left) - getHeight(node.right);
    }
    /**
     * Search for a tank in the tree.
     * 
     * @param t the tank to search for.
     * @return the matching tank or null if not found.
     */
    public Tank search(Tank t) {
        AVLNode index = this.root;

        while ( index != null) {
            // compare the node and walk down the tree
            if(index.t.compareTo(t) < 0){
                index = index.right;
            } else if(index.t.compareTo(t) > 0) {
                index = index.left;
            } else if (index.t.compareTo(t) == 0){
                return index.t;
            }
        }
    	return null;
    }
    
    /**
     * Traverse the contents of this tree in an 'inorder' manner and return and array
     * containing the traversal of the tree.
     * 
     * @return a sorted array of the tree's content.
     */

    Tank[] inorder() {
        int i = 0;
        Tank[] tankArr = new Tank[size];
        AVLNode node = this.root;
        // if the tree is not empty, do a recursive inorder and write results to the array
        if (this.root != null) {
            fill_array(node, tankArr, i);
        }
        return tankArr;
    }

    int fill_array(AVLNode root, Tank [] array, int pos) {
        if (root.left != null) {
            pos = fill_array(root.left, array, pos);
        }
        array[pos++] = root.t;
        if (root.right != null) {
            pos = fill_array(root.right, array, pos);
        }
        // return the last position filled in by this invocation
        return pos;
    }

}