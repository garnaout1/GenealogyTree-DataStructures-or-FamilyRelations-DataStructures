public class Node {
    int key;
    int count;
    int height;
    Node left;
    Node right;

    public Node(int key) {
        this.key = key;
        this.count = 1;
        this.height = 1;
        this.left = null;
        this.right = null;
    }
}

