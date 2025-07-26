import java.util.HashMap;

public class Driver {

    public static void main(String[] args) {
        System.out.println(huffmanEncode("blablafff  "));
    }

    static String huffmanEncode(String input) {
        HashMap<Character, Integer> frequency = new HashMap<>();
        for(char c : input.toCharArray()) {
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }

        


        return frequency.toString();
    }

}
