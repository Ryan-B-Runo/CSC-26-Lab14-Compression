import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Driver {

    public static void main(String[] args) throws IOException {
        System.out.println(huffmanEncode("Hello World"));

        System.out.println(huffmanDecode(huffmanEncode("Hello World")));
    }

    static String huffmanEncode(String input) throws IOException {
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

        FileOutputStream f = new FileOutputStream("root.node");
        ObjectOutputStream out = new ObjectOutputStream(f);
        out.writeObject(root);
        out.close();
        f.close();

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

    static String huffmanDecode(String input) {

        StringBuilder decoded = new StringBuilder();

        try{
            FileInputStream f = new FileInputStream("root.node");
            ObjectInputStream in = new ObjectInputStream(f);
            Node root = (Node) in.readObject();
            Node current = root;
            in.close();
            f.close();

            for(char c : input.toCharArray()) {
                if(c == '0') {
                    current = current.getLeft();
                }else{
                    current = current.getRight();
                }
                if(current.getLeft() == null && current.getRight() == null) {
                    decoded.append(current.getCharacter());
                    current = root;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return decoded.toString();
    }

}