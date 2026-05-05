// Author: Michael Kenny
// Class: CSC-130 Data Structures and Algorithm Analysis
// Prof: Devin Cook
// Assignment: Create a very basic Binary Search Tree(BST) and read data from files and load the values with keys into the tree.
//             The tree must be able to be printed into an ASCII art tree and a sorted list, use a public node class.

class BinarySearchTree {
    private Node root;
    
    public String About() {
        return "Author: Michael Kenny";
    }

    public String toTree() {
        String result = "";

        if(root == null) {
            return result;
        }else {
            result += root.toTree("-", 0);
        }
        return result;
    }

    public String toSortedList() {
        String result = "";

        if (root == null) {
            return result;
        } else {
            result += root.toSortedList();
        }
        return result;
    }

    public void add(int key, String value) {
        if(root == null) {
            Node newRoot = new Node(key, value);
            root = newRoot;
        }else {
            root.add(key, value);
        }
    }

    public String find(int key) {
        if(root != null) {
            if(root.key == key) {
                return root.value;
            }else {
                return root.find(key);
            }
        }
        return null;
    }
}