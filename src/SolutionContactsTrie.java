import java.io.*;
import java.util.*;

public class SolutionContactsTrie {

    public class Trie {
        char c;
        int size;
        boolean isComplete;
        HashMap<Character, Trie> children = new HashMap<>();

        void addWord(String word) {
            if(word.length() == 1) {
                size++;
                isComplete = true;
            } else {

                char firstChar = word.charAt(1);
                size++;

                if(children.containsKey(firstChar)) {
                    Trie t = children.get(firstChar);
                    t.addWord(word.substring(1));
                } else {
                    Trie t = new Trie();
                    t.c = firstChar;

                    children.put(firstChar, t);

                    t.addWord(word.substring(1));
                }
            }
        }

        int findWord(String word) {

            if(word.length() == 1) {
                return size;
            } else {
                char firstChar = word.charAt(1);
                if(children.containsKey(firstChar)) {
                    return children.get(firstChar).findWord(word.substring(1));
                }
                return 0;
            }
        }
    }

    public class TrieRoot {
        HashMap<Character, Trie> children = new HashMap<>();

        void addWord(String word) {
            if(word == null || word.length() == 0) return;

            char firstChar = word.charAt(0);

            if(children.containsKey(firstChar)) {
                Trie t = children.get(firstChar);
                t.addWord(word);
            } else {
                Trie t = new Trie();
                t.c = firstChar;

                children.put(firstChar, t);

                t.addWord(word);
            }
        }

        int findWord(String word) {
            if(word == null || word.length() == 0)  return 0;

            char firstChar = word.charAt(0);

            if(children.containsKey(firstChar)) {
                return children.get(firstChar).findWord(word);
            }
            return 0;
        }
    }

    TrieRoot trie = new TrieRoot();

    /*
     * Complete the contacts function below.
     */
    static int[] contacts(String[][] queries) {
        /*
         * Write your code here.
         */

        ArrayList<Integer> partial = new ArrayList<>();

        TrieRoot trie = new SolutionContactsTrie().trie;

        for(int i = 0; i < queries.length; i++) {
            if(queries[i][0].equals("add")) {
                trie.addWord(queries[i][1]);
            }

            if(queries[i][0].equals("find")) {
                partial.add(trie.findWord(queries[i][1]));
            }
        }

        int[] result = new int[partial.size()];
        for(int i = 0; i < partial.size(); i++) {
            result[i] = partial.get(i);
        }

        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int queriesRows = Integer.parseInt(scanner.nextLine().trim());

        String[][] queries = new String[queriesRows][2];

        for (int queriesRowItr = 0; queriesRowItr < queriesRows; queriesRowItr++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");

            for (int queriesColumnItr = 0; queriesColumnItr < 2; queriesColumnItr++) {
                String queriesItem = queriesRowItems[queriesColumnItr];
                queries[queriesRowItr][queriesColumnItr] = queriesItem;
            }
        }

        int[] result = contacts(queries);

        for (int resultItr = 0; resultItr < result.length; resultItr++) {
            bufferedWriter.write(String.valueOf(result[resultItr]));
            //System.out.print(String.valueOf(result[resultItr]));

            if (resultItr != result.length - 1) {
                bufferedWriter.write("\n");
                //System.out.println();
            }
        }

        bufferedWriter.newLine();
        //System.out.println();

        bufferedWriter.close();
    }
}
