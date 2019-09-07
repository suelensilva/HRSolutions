import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SolutionBalancedBrackets {

    // Complete the isBalanced function below.
    static String isBalanced(String s) {
        if(s == null || s.length() == 0) {
            return "YES";
        }

        char[] chars = s.toCharArray();

        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < chars.length; i++) {
            char c = chars[i];

            switch(c) {
                case '{':
                case '[':
                case '(':
                    stack.push(c);
                    break;
                case '}': {
                    if(!stack.isEmpty()) {
                        char popped = stack.pop();
                        if (popped != '{') return "NO";
                    } else {
                        return "NO";
                    }
                    break;
                }
                case ']': {
                    if(!stack.isEmpty()) {
                        char popped = stack.pop();
                        if (popped != '[') return "NO";
                    } else {
                        return "NO";
                    }
                    break;

                }
                case ')':   {
                    if(!stack.isEmpty()) {
                        char popped = stack.pop();
                        if (popped != '(') return "NO";
                    } else {
                        return "NO";
                    }
                    break;
                }
            }
        }

        if(stack.isEmpty()) {
            return "YES";
        }
        return "NO";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String s = scanner.nextLine();

            String result = isBalanced(s);

            //bufferedWriter.write(result);
            System.out.println(result);
            //bufferedWriter.newLine();
        }

        //bufferedWriter.close();

        scanner.close();
    }
}
