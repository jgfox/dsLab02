/*
 * Jonathan Fox
 * 64CB42
 * jfox49@jhu.edu
 */

public class BinaryTree <E> {

    public static class Node <E> {
        E data;
        Node leftPointer;
        Node rightPointer;

        public Node(E data) {
            this.data = data;
            this.leftPointer = null;
            this.rightPointer = null;
        }
        public Node(E data, Node left, Node right) {
            this.data = data;
            this.leftPointer = left;
            this.rightPointer = right;

        }
    }

    public Node root;

    /*
    public void insert(Node node, E data)

    public void traverseInOrder()
     */

}
