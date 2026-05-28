

public class BST {

    private Node root;

    public BST() {
        root = null;
    }

    public void insert(int key) {
        root = insertRecursive(root, key);
    }

    private Node insertRecursive(Node current, int key) {

        if (current == null) {
            return new Node(key);
        }

        if (key < current.key) {
            current.left = insertRecursive(current.left, key);
        }

        else if (key > current.key) {
            current.right = insertRecursive(current.right, key);
        }

        else {
            current.count++;
        }

        return current;

    }
    //Ξεκινάνε τα Traversals
    //Εμφάνιση κλειδιών σε αύξουσα σειρά 
    public void inorder() {
        inorderRecursive(root);
        System.out.println();
    }

    private void inorderRecursive(Node node) {
        if (node != null) {
            inorderRecursive(node.left);
            System.out.print(node.key + "(" + node.count + ") ");
            inorderRecursive(node.right);
        }
    }
    //Preorder
    public void preorder() {
        preorderRecursive(root);
        System.out.println();
}

    private void preorderRecursive(Node node) {
        if (node != null) {
            System.out.print(node.key + "(" + node.count + ") ");
            preorderRecursive(node.left);
            preorderRecursive(node.right);
    }
}
    //Postorder
    public void postorder() {
        postorderRecursive(root);
        System.out.println();
}

    private void postorderRecursive(Node node) {
    if (node != null) {
        postorderRecursive(node.left);
        postorderRecursive(node.right);
        System.out.print(node.key + "(" + node.count + ") ");
    }
}

    //Διαγραφή
    public void delete(int key) {
        root = deleteRecursive(root, key);
    }
    private Node deleteRecursive(Node node, int key) {
        if(node == null) return null;

        if (key < node.key) {
            node.left = deleteRecursive(node.left, key);
        }

        else if (key > node.key) {
            node.right = deleteRecursive(node.right, key);
        }

        else {
            //Βρέθηκε το κλειδί 
            if(node.count > 1) {
                node.count--;
                return node;
            }

            //Περίπτωση 1 και 2 
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            //Περίπτωση 3, για 2 παιδιά

            Node successor = findMin(node.right);
            node.key = successor.key;
            node.count = successor.count;
            successor.count=1;
            node.right = deleteRecursive(node.right, successor.key);
        }

        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null)
            node = node.left;
        return node;
    }


}
