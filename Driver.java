import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Driver {

    public static void main(String[] args) {
        System.out.println(huffmanEncode("Hello World"));
    }

    static String huffmanEncode(String input) {
        HashMap<Character, Integer> frequency = new HashMap<>();
        Node root;

        for(char c : input.toCharArray()) {
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Node> q = new PriorityQueue<>();

        //build tree

        for(HashMap.Entry<Character, Integer> entry : frequency.entrySet()) {
            q.add(new Node(entry.getValue(), entry.getKey()));
        }

        while(q.size() > 1) {
            Node left = q.poll();
            Node right = q.poll();
            root = new Node(left.getFrequency()+ right.getFrequency(), left, right);
            q.add(root);
        }

        root = q.poll();

        //generate codes

        HashMap<Character, String> codes = new HashMap<>();

        preorder(root, codes, "");

        StringBuilder encoded = new StringBuilder();
        for(char c : input.toCharArray()) {
            encoded.append(codes.get(c));
        }

        //print table
        for(char c : codes.keySet()) {
            System.out.println("Symbol: " + c + "\tFrequency: " + frequency.get(c) + "\tCode: " + codes.get(c));
        }

        return "\n" + encoded.toString();
    }

    static void preorder(Node root, HashMap<Character,String> map, String s) {
        if(root == null) return;

        if(root.getLeft() == null && root.getRight() == null) {
            map.put(root.getCharacter(), s.isEmpty() ? "0" : s);
            return;
        }
        preorder(root.getLeft(), map, s + '0');
        preorder(root.getRight(), map, s + '1');
    }

//    static String huffmanDecode(String input, Node root) {
//
//    }

}