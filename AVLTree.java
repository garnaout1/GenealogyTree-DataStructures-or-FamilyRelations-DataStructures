public class AVLTree {

    Node root;

    private int height(Node n) {
        return (n == null) ? 0 : n.height;
    }

    private int getBalance(Node n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    public void insert(int key) {
        root = insertRecursive(root, key);
    }

    private Node insertRecursive(Node node, int key) {

        if (node == null)
            return new Node(key);

        if (key < node.key)
            node.left = insertRecursive(node.left, key);
        else if (key > node.key)
            node.right = insertRecursive(node.right, key);
        else {
            node.count++;
            return node;
        }

        node.height = 1 + max(height(node.left), height(node.right));
        int balance = getBalance(node);

        // LL
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // RR
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // LR
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public void inorder() {
        inorderRecursive(root);
        System.out.println();
    }

    private void inorderRecursive(Node node) {
        if (node == null) return;
        inorderRecursive(node.left);
        System.out.print(node.key + "(" + node.count + ") ");
        inorderRecursive(node.right);
    }

    public void changeKey(int oldKey,  int newKey) {
        int count = getCount(root, oldKey);
        if (count == 0) return;

        for (int i = 0; i < count; i++) {
            root = deleteRecursive(root, oldKey);
        }

        for (int i=0; i < count; i++) {
            insert(newKey);
        }
    }

    private int getCount(Node node, int key) {
        if (node == null) return 0;
        if (key < node.key) return getCount(node.left, key);
        if (key > node.key) return getCount(node.right, key);
        return node.count;
    }
    
    private Node deleteRecursive(Node node, int key) {

    if (node == null) return null;

    if (key < node.key)
        node.left = deleteRecursive(node.left, key);
    else if (key > node.key)
        node.right = deleteRecursive(node.right, key);
    else {

        if (node.count > 1) {
            node.count--;
            return node;
        }

        if (node.left == null || node.right == null) {
            Node temp = (node.left != null) ? node.left : node.right;

            if (temp == null) {
                node = null;
            } else {
                node = temp;
            }
        } else {
            Node successor = findMin(node.right);
            node.key = successor.key;
            node.count = successor.count;
            successor.count = 1;
            node.right = deleteRecursive(node.right, successor.key);
        }
    }

    if (node == null) return null;

    node.height = Math.max(height(node.left), height(node.right)) + 1;
    int balance = getBalance(node);

    if (balance > 1 && getBalance(node.left) >= 0)
        return rightRotate(node);

    if (balance > 1 && getBalance(node.left) < 0) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    if (balance < -1 && getBalance(node.right) <= 0)
        return leftRotate(node);

    if (balance < -1 && getBalance(node.right) > 0) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    return node;
}

private Node findMin(Node node) {
    while (node.left != null)
        node = node.left;
    return node;
}
}
