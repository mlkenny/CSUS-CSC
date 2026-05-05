public class Node {
    int key;
    String value;
    Node right;
    Node left;

    Node(int key, String value) {
        this.key = key;
        this.value = value;
    }

    // Add Method: Add a new node to the BST based off a given key and value.
    //             Use recursion to find where it exists, then add.
    public void add(int key, String value) {
        if(key < this.key) { // If new node's key is too small, go left in tree.
            if(this.left == null) { // Check if there is an existing left child. If not, then add new node there.
                Node newLeftChild = new Node(key, value);
                this.left = newLeftChild;
            } else { // If there is an existing left child, recurse left.
                left.add(key, value);
            }
        }
        if(key > this.key) { // If new node's key is too big, go right in tree.
            if(this.right == null) { // Check if there is an existing right child. If not, then add new node there.
                Node newRightChild = new Node(key, value);
                this.right = newRightChild;
            } else { // If there is an existing right child, recurse right.
                right.add(key, value);
            }
        }
    }
    
    // ToTree Method: Print out the BST in such a way that easy identifies the root and each child's branch, L or R, and adds indentation.
    //                Use recursion to find all children of each node.
    public String toTree(String label, int indent) {
        String result = "";

        for(int i = 0; i < indent; i++) { // Generate indentations.
            result += "  ";
        }
        result += label + ": "; // Print whether node is on a left branch or right branch.
        result += "(" + this.key + ")" + " " + this.value + "\n"; // Print out the key in parenthesis and the value and spacing.

        if(this.left != null) { // If there still exists a left child, keep recursing to the left.
            result += left.toTree("L", indent + 1);
        }
        if (this.right != null) { // If there still exists a right child, keep recursing to the right.
            result += right.toTree("R", indent + 1);
        }
        return result;
    }
    
    // ToSortedList Method: Print the BST in a sorted list in ascending order based off the key.
    //                      Must print key and the value, use recursion once again.
    public String toSortedList() {
        String result = "";

        if(this.left != null) { // If there exists a left child, keep recursing to the left.
            result += left.toSortedList();
        }

        // This section is just for style indentation.
        if (this.left != null) {
            result += ", ";
        }
        result += this.key + " " + this.value;
        if(this.right != null) {
            result += ", ";
        }

        if (this.right != null) { // If there exists a right child, keep recursing to the right.
            result += right.toSortedList();
        }
        return result;
    }
    
    // Find Method: Implement a find method to search the BST from the node down to find the given value.
    //              If the value is not found, return the root's value. Use recursion.
    public String find(int key) {
        // Base Case, key is root.
        if(this.key == key) {
            return this.value;
        }else {
            // Search recursively, left if key is smaller than root and right if key is bigger than the root.
            if (this.key > key) {
                return left.find(key);
            } else {
                return right.find(key);
            }
        }
    }
}