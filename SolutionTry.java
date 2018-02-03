import java.util.HashMap;
import java.util.Scanner;

/**
* Solution for the challenge 'Tries: Contacts',
* which uses the data structure Try, that keeps
* track of a words and their preffix 
*/
public class SolutionTry {

    private static Node root;

    private static void addName(String name, Node root) {

        if(name.isEmpty()) {
            root.isCompleteWord = true;
        } else {

            char firstLetter = name.toCharArray()[0];
            Node child;
            if (root.children.containsKey(firstLetter)) {
                child = root.children.get(firstLetter);
                child.counterWords++;
            } else {
                child = new Node();
                child.counterWords = 1;
                root.children.put(firstLetter, child);
            }

            String remainingName = name.substring(1);
            addName(remainingName, child);
        }
    }

    private static int findName(String name, Node root) {
        char firstLetter = name.toCharArray()[0];
        int result = 0;

        if(root != null && root.children.containsKey(firstLetter)) {
            String remainingName = name.substring(1);

            if(!remainingName.isEmpty()) {
                result = findName(remainingName, root.children.get(firstLetter));
            } else {
                result = root.children.get(firstLetter).counterWords;
            }
        }

        return result;
    }

    private static void buildTry() {
        root = new Node();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        buildTry();
        for(int a0 = 0; a0 < n; a0++){
            String op = in.next();
            String contact = in.next();

            if(op.equals("add")) {
                addName(contact, root);
            } else {
                System.out.println(findName(contact, root));
            }
        }
    }

    static class Node {
        HashMap<Character, Node> children = new HashMap<>();
        int counterWords;
        boolean isCompleteWord;
    }
}