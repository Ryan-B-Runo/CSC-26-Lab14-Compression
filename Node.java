public class Node implements Comparable<Node>{
    private char character;
    private int frequency;
    private Node left, right;

    public Node(int frequency, char character) {
        this.character = character;
        this.frequency = frequency;
        left = right = null;
    }

    public Node(int frequency, Node left, Node right) {
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    //So priority queue will be sorted by frequency
    @Override
    public int compareTo(Node o) {
        return frequency - o.frequency;
    }

    public char getCharacter() {
        return character;
    }

    public int getFrequency() {
        return frequency;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

}
