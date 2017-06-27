//package HW5;

public class AVLTree {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        //String[] ids = new String[]{"b", "a", "c", "d", "e", "f", "g", "h"};
        //for (int i = 0; i< 3; i++){
        //    tree.insert(new Tank(ids[i]));
        //}

        /*
        tree.root = new AVLNode(new Tank("l"));
        tree.root.left = new AVLNode(new Tank("c"));
        tree.root.left.parent = tree.root;
        tree.root.right = new AVLNode(new Tank("o"));
        tree.root.right.parent = tree.root;
        tree.root.right.right = new AVLNode(new Tank("r"));
        tree.root.right.right.parent = tree.root.right;
       // tree.root.right.right.right = new AVLNode(new Tank("z"));
       // tree.root.right.right.right.parent = tree.root.right.right;
        tree.root.right.left = new AVLNode(new Tank("n"));
        tree.root.right.left.parent = tree.root.right;
        tree.root.height = 2; tree.root.left.height = 1; tree.root.right.height = 1; tree.root.right.right.height = 0; tree.root.right.left.height = 0;
        tree.size = 5;
*/

        tree.insert(new Tank("l"));
        tree.insert(new Tank("c"));
        tree.insert(new Tank("o"));
        tree.insert(new Tank("r"));
        tree.insert(new Tank("z"));
        Tank[] sorted = tree.inorder();
        for (int i = 0; i< tree.size; i++) {
            System.out.println(sorted[i]);
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
        System.out.println("inserting tank: " + t.toString());
        AVLNode node = insert_bst(t, this);
        //System.out.println("tank height: " + node.height);
        AVLNode parent = node.parent;
        while (parent != null){
            System.out.println("balancing tank: " + parent.t.toString());
            System.out.println("BF: " +BalanceFactor(parent) );
            if (Math.abs(BalanceFactor(parent)) > 1) {
                //Left Left Case
                if (BalanceFactor(parent) > 1 && t.compareTo(parent.left.t) < 0) {
                    System.out.println("Left Left Case on: " + parent.t.toString());
                    rightRotate(parent);
                }
                if (BalanceFactor(parent) < -1 && t.compareTo(parent.right.t) > 0) {
                    System.out.println("Right Right Case on: " + parent.t.toString());
                    leftRotate(parent);
                }
                // Left Right Case

                if (BalanceFactor(parent) > 1 && t.compareTo(parent.left.t) > 0) {
                    System.out.println("Left Right Case on: " + parent.t.toString());
                    rightLeftRotate(parent);
                }
                // Right Left Case

                if (BalanceFactor(parent) < -1 && t.compareTo(parent.right.t) < 0) {
                    System.out.println("Right Left Case on: " + parent.t.toString());
                    leftRightRotate(parent);
                }
            }
            parent = parent.parent;
        }
    }

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

        } else if (node.t.compareTo(parent.t)< 0){
            parent.left = node;
        } else {
            parent.right = node;
        }

        // update height
        parent = node.parent;
        node.height = 0;
        while (parent != null){
            parent.height +=1;
            parent = parent.parent;
        }
        this.size++;
        return node;
    }

    public void leftRotate(AVLNode x){
        AVLNode z = x.right;
        AVLNode t23 = z.left;
        x.right = t23;
        t23.parent = x;
        z.parent = x.parent;
        if (x.parent.left == x ) {
            z.parent.left = z;
        } else {
            z.parent.right = z;
        }
        z.left = x;
        x.parent = z;

        x.height = Math.max(x.left.height, x.right.height) + 1;
        z.height = Math.max(z.left.height, z.right.height) + 1;
    }

    public void rightRotate(AVLNode x){
        AVLNode z = x.left;
        AVLNode t23 = z.right;
        x.left = t23;
        t23.parent = x;
        z.parent = x.parent;
        if (x.parent.left == x ) {
            z.parent.left = z;
        } else {
            z.parent.right = z;
        }
        z.right = x;
        x.parent = z;

        x.height = Math.max(x.left.height, x.right.height) + 1;
        z.height = Math.max(z.left.height, z.right.height) + 1;
    }

    public void rightLeftRotate(AVLNode x){
        AVLNode z = x.right;
        AVLNode y = z.left;
        AVLNode t3 = y.right;
        z.left = t3;
        t3.parent = z;
        y.right = z;
        z.parent = y;

        AVLNode t2 = y.left;
        x.right = t2;
        t2.parent = x;
        y.left = x;
        x.parent = y;

        x.height = Math.max(x.left.height, x.right.height) + 1;
        z.height = Math.max(z.left.height, z.right.height) + 1;
        y.height = Math.max(y.left.height, y.right.height) + 1;
    }


    public void leftRightRotate(AVLNode x){
        AVLNode z = x.left;
        AVLNode y = z.right;
        AVLNode t3 = y.left;
        z.right = t3;
        t3.parent = z;
        y.left = z;
        z.parent = y;

        AVLNode t2 = y.right;
        x.left = t2;
        t2.parent = x;
        y.right = x;
        x.parent = y;

        x.height = Math.max(x.left.height, x.right.height) + 1;
        z.height = Math.max(z.left.height, z.right.height) + 1;
        y.height = Math.max(y.left.height, y.right.height) + 1;
    }

    public int getHeight(AVLNode n) {
        if (n != null) {
            return n.height;
        }
        return 0;
    }

public int BalanceFactor( AVLNode node){
     //   System.out.println("left: "+getHeight(node.left)+ ", right: "+getHeight(node.right));
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
        fill_array(node, tankArr, i);
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
        return pos; // return the last position filled in by this invocation
    }

}