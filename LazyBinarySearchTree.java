import java.util.ArrayList;

public class LazyBinarySearchTree {

    private class TreeNode {
        private int key; // range [1-99]
        private TreeNode leftChild;// pointer to left node
        private TreeNode rightChild; // pointer to right node
        private boolean deleted; // turns on or off a node
        private int nodeHeight;

        // Constructor for TreeNode
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

        // get left child node
        public TreeNode getLeftNode() {
            return leftChild;
        }

        // get right child node
        public TreeNode getRightNode() {
            return rightChild;
        }

        // get node key
        public int getKey() {
            return key;
        }

        // get node deletion status
        public boolean getStatus() {
            return deleted;
        }

        // get node height
        public int getHeight() {
            return nodeHeight;
        }

        /*
         * Setters
         */
        // set left child node ptr
        public void setLeftNode(TreeNode left) {
            this.leftChild = left;
        }

        // set right child node ptr
        public void setRightNode(TreeNode right) {
            this.rightChild = right;
        }

        // set node deletion status true means deleted false means not deleted
        public void setStatus(boolean status) {
            this.deleted = status;
        }

        // set node height
        public void setHeight(int height) {
            this.nodeHeight = height;
        }

        public String toString() {
            return String.valueOf(this.key);
        }
    }

    public TreeNode root;

    public LazyBinarySearchTree() {
        this.root = null;
    }

    // insert TreeNode at leaf (leftNode & rightNode = null) no duplicates, if node
    // is deleted undelete node, return logic of inserting a node

    public boolean insert(TreeNode ptr, int k) {
        if (k < ptr.getKey()) {
            if (ptr.getLeftNode() != null) {
                return insert(ptr.getLeftNode(), k);
            } else {
                ptr.setLeftNode(new TreeNode(k));
                return true;
            }
        } else if (k > ptr.getKey()) {
            if (ptr.getRightNode() != null) {
                return insert(ptr.getRightNode(), k);
            } else {
                ptr.setRightNode(new TreeNode(k));
                return true;
            }
        } else {
            if (ptr.getKey() == k) {
                // if the node is deleted then undelete it
                if (ptr.getStatus() == true) {
                    ptr.setStatus(false);
                    return true;
                } else {
                    return false;
                }
            }

        }
        ptr.setHeight(1 + Math.max(height(ptr.getLeftNode()), height(ptr.getRightNode())));
        return false;
    }

    public boolean insert(int k) throws IllegalArgumentException {
        if (k <= 0 || k > 99) {
            throw new IllegalArgumentException("Please enter a number between 1 and 99, [1,99]");
        } else {
            if (this.root == null) {
                this.root = new TreeNode(k);
                return true;
            } else {
                return insert(this.root, k);
            }
        }

    }

    // does not physically delete, instead marks as logically deleted | do nothing
    // if already deleted or not in tree | return whether value was deleted
    public boolean delete(TreeNode ptr, int k) throws IllegalArgumentException {
        // traverse down the left side of the tree
        if (k < ptr.getKey()) {
            if (ptr.getLeftNode() != null && ptr.getLeftNode().getKey() != k) {
                return delete(ptr.getLeftNode(), k);
            } else {
                if (k == ptr.getLeftNode().getKey()) {
                    ptr.getLeftNode().setStatus(true);
                    return true;
                }

            }
        } else if (k > ptr.getKey()) {
            if (ptr.getRightNode() != null && ptr.getRightNode().getKey() != k) {
                return delete(ptr.getRightNode(), k);
            } else {
                if (k == ptr.getRightNode().getKey()) {
                    ptr.setStatus(true);
                    return true;
                }
            }
        } else {
            if (ptr.getKey() == k) {
                if (ptr.getStatus() == true) {
                    return false;
                }
            }
        }

        return false;
    }

    // does not physically delete, instead marks as logically deleted | do nothing
    // if already deleted or not in tree | return whether value was deleted
    public boolean delete(int k) throws IllegalArgumentException {
        if (k <= 0 || k > 99) {
            throw new IllegalArgumentException("Please enter a number between 1 and 99, [1,99]");
        } else {
            if (this.root.getKey() == k) {
                this.root.setStatus(true);
                return true;
            } else {
                return delete(this.root, k);
            }
        }
    }

    // retrun value of min non delted element or -1 if not present

    public int findMin(TreeNode ptr, int min) {
        if(ptr.getStatus() == false) {
            min = ptr.getKey();
        }
        if((ptr.getLeftNode() != null && ptr.getLeftNode().getStatus() == false) && (ptr.getLeftNode().getKey() < min)) {
            min = ptr.getLeftNode().getKey();
            return findMin(ptr.getLeftNode(), min);
        }
        else if((ptr.getRightNode() != null && ptr.getRightNode().getStatus() == false) && (ptr.getRightNode().getKey() < min)) {
            min = ptr.getRightNode().getKey();
            return findMin(ptr.getRightNode(), min);
        }
        return min;
    }

    public int findMin() {
        int min = -1;
        
        if(this.root.getStatus() == false && this.root != null) {
            min = this.root.getKey();
            if(this.root.getLeftNode() != null) {
                min = findMin(this.root.getLeftNode(), min);
                //now search right side of the tree, reseting to the root to see if a min can be found there
                    if(this.root.getRightNode() != null) {
                        min = findMin(this.root.getRightNode(), min);
                    }
            }
        }
        return min;
    }

    // retrun value of max non delted element or -1 if not present

    public int findMax(TreeNode ptr, int max) {
        if(ptr.getStatus() == false) {
            max = ptr.getKey();
        }
        if((ptr.getRightNode() != null && ptr.getRightNode().getStatus() == false) && (ptr.getRightNode().getKey() > max)) {
            max = ptr.getRightNode().getKey();
            return findMax(ptr.getRightNode(), max);
        }
        else if((ptr.getLeftNode() != null && ptr.getLeftNode().getStatus() == false) && (ptr.getLeftNode().getKey() > max)) {
            max = ptr.getLeftNode().getKey();
            return findMax(ptr.getLeftNode(), max);
        }
        return max;
    }

    public int findMax() {
        int max = -1;
        
        if(this.root.getStatus() == false && this.root != null) {
            max = this.root.getKey();
            if(this.root.getRightNode() != null) {
                max = findMax(this.root.getRightNode(), max);
                //now search right side of the tree, reseting to the root to see if a min can be found there
                    if(this.root.getLeftNode() != null) {
                        max = findMax(this.root.getLeftNode(), max);
                    }
            }
        }
        return max;
    }

    public boolean contains(TreeNode ptr, int k) throws IllegalArgumentException {
        if (k <= 0 || k > 99) {
            throw new IllegalArgumentException("Please enter a number between 1 and 99, [1,99]");
        } else {
            if (k < ptr.getKey()) {
                if (ptr.getLeftNode() != null && ptr.getLeftNode().getKey() == k
                        && ptr.getLeftNode().getStatus() == false) {
                    return true;
                } else {
                    return contains(ptr.getLeftNode(), k);
                }
            } else if (k > ptr.getKey()) {
                if (ptr.getRightNode() != null && ptr.getRightNode().getKey() == k
                        && ptr.getRightNode().getStatus() == false) {
                    return true;
                } else {
                    return contains(ptr.getRightNode(), k);
                }
            } else {
                if (ptr.getStatus() == true) {
                    return false;
                } else {
                    return true;
                }
            }

        }
    }

    public boolean contains(int k) throws IllegalArgumentException {
        if (k <= 0 || k > 99) {
            throw new IllegalArgumentException("Please enter a number between 1 and 99, [1,99]");
        } else {
            if (this.root != null) {
                return contains(this.root, k);
            } else {
                return false;
            }
        }
    }

    public StringBuffer preorder(TreeNode ptr, StringBuffer array) {
        if(ptr != null && ptr.getStatus() == false) {
            array.append(ptr.getKey() + " ");
        }
        else if(ptr != null & ptr.getStatus() == true) {
            array.append("*" + ptr.getKey() + " ");
        }
        if(ptr.getLeftNode() != null) {
            preorder(ptr.getLeftNode(), array);
        }
        if(ptr.getRightNode() != null) {
            preorder(ptr.getRightNode(), array);
        }
        return array;
    }


    @Override
    // pre order traversal and prints out each element of the tree
    public String toString() {
        // return the value of the key as a string
        StringBuffer preorder = new StringBuffer();
        preorder = preorder(this.root, preorder);
        return preorder.toString();
    }

    // returns the height of the farthest leaf
    public int height(TreeNode root) {
        ArrayList<TreeNode> h = new ArrayList<TreeNode>();
        h.add(root);
        int height = 0;
        while(!h.isEmpty()) {
            int size = h.size();
            for(int i = 0; i < size; i++) {
                TreeNode ptr = h.get(i);
                h.remove(i);
                if(ptr.getLeftNode() != null) {
                    h.add(ptr.getLeftNode());
                }
                if(ptr.getRightNode() != null) {
                    h.add(ptr.getRightNode());
                }
            }
            height++;
        }
        return height;
    }

    // counts all elements in the tree

    public int size(TreeNode ptr, int size) {
        if(ptr != null) {
            size++;
        }
        if(ptr.getLeftNode() != null) {
            size = size(ptr.getLeftNode(), size);
        }
        if(ptr.getRightNode() != null) {
            size = size(ptr.getRightNode(), size);
        }
        return size;
    }

    public int size() {
        int size = 0;
        if(this.root != null) {
            return size(this.root, 0);
        }
        return size;
    }

}