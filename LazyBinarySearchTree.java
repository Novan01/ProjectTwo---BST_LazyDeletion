public class LazyBinarySearchTree {

            private class TreeNode {
                private int key; //range [1-99]
                private TreeNode leftChild;//pointer to left node
                private TreeNode rightChild; //pointer to right node
                private boolean deleted; //turns on or off a node
                private int nodeHeight;

                //Constructor for TreeNode
                    public TreeNode(int k) {
                        this.key = k;
                        this.leftChild = null;
                        this.rightChild = null;
                        this.deleted = false;
                        this.nodeHeight = 1;
                    }

                    /*
                     * Getters
                     */

                    //get left child node
                    public TreeNode getLeftNode() {
                        return leftChild;
                    }
                    
                    //get right child node
                    public TreeNode getRightNode() {
                        return rightChild;
                    }

                    //get node key
                    public int getKey() {
                        return key;
                    }

                    //get node deletion status
                    public boolean getStatus() {
                        return deleted;
                    }

                    //get node height
                    public int getHeight() {
                        return nodeHeight;
                    }

                    /*
                     * Setters
                     */

                    //set node key
                    public void setKey(int k) {
                        this.key = k;
                    }

                    //set left child node ptr
                    public void setLeftNode(TreeNode left) {
                        this.leftChild = left;
                    }

                    //set right child node ptr
                    public void setRightNode(TreeNode right) {
                        this.rightChild = right;
                    }

                    //set node deletion status
                    public void setStatus(boolean status) {
                        this.deleted = status;
                    }

                    //set node height
                    public void setHeight(int height) {
                        this.nodeHeight = height;
                    }
            }

            public TreeNode root;

            public LazyBinarySearchTree() {
                this.root = null;
            }

            
            //insert TreeNode at leaf (leftNode & rightNode = null) no duplicates, if node is deleted undelete node, return logic of inserting a node

            public boolean insertNode(TreeNode ptr, int k) {
                if(k < ptr.getKey()) {
                    if(ptr.getLeftNode() != null) {
                        return insertNode(ptr.getLeftNode(), k);
                    }
                    else {
                        ptr.setLeftNode(new TreeNode(k));
                        return true;
                    }
                }
                else if(k > ptr.getKey()) {
                    if(ptr.getRightNode() != null) {
                        return insertNode(ptr.getRightNode(), k);
                    }
                    else {
                        ptr.setRightNode(new TreeNode(k));
                        return true;
                    }
                }
                else {
                    if(ptr.getKey() == k) {
                         //if the node is deleted then undelete it
                        if(ptr.getStatus() == true) {
                            ptr.setStatus(false);
                            return true;
                        }
                        else {
                            return false;
                        }
                    }
                   
                }
                return false;
            }

            public boolean insert(int k) throws IllegalArgumentException {
                boolean inserted = false;
                if(k <= 0 || k > 99) {
                    throw new IllegalArgumentException("Please enter a number between 1 and 99, [1,99]");
                }
                else {
                    if(this.root == null) {
                        this.root = new TreeNode(k);
                        return true;
                    }
                    else {
                        return insertNode(this.root, k);
                    }
                }
                
            }

            //does not physically delete, instead marks as logically deleted | do nothing if already deleted or not in tree | return whether value was deleted
            public static boolean delete(int k) throws IllegalArgumentException {
                if(k <= 0 && k > 99) {
                    throw new IllegalArgumentException("Please enter a number between 1 and 99, [1,99]");
                }
                else {
                    //delete the node
                }
                
                return false;
            }

            //retrun value of min non delted element or -1 if not present
            public static int findMin() {
                int min = 0;
                return min;
            }

            //retrun value of max non delted element or -1 if not present
            public static int findMax() {
                int max = 0;
                return max;
            }

            public static boolean contains(int k) throws IllegalArgumentException {
                if(k <= 0 && k > 99) {
                    throw new IllegalArgumentException("Please enter a number between 1 and 99, [1,99]");
                }
                else {
                    //search for the node
                }
                return false;
            }

            @Override
            //pre order traversal and prints out each element of the tree
            public String toString() {
                //return the value of the key as a string 
                return String.valueOf(this.key); 
            }

            public static int height(TreeNode leaf) {
                if(leaf == null) {
                    return 0;
                }
                else {
                    return leaf.getHeight();
                }
            }

            public int size() {
                int size = 0;
                return size;
            }


            
    
}