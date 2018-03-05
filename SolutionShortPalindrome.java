import com.sun.deploy.util.StringUtils;

import java.util.HashMap;
import java.util.Scanner;

public class SolutionShortPalindrome {

    private static final long BIG_NUMBER = 1000000007L;

    private static HashMap<String, Integer> memo = new HashMap<>();

    private static int shortPalindrome(String s) {

        if(s == null || s.length() == 0) return 0;

        if(s.length() < 4) return 0;

        int countPalindrome = 0;

        char[] chars = s.toCharArray();
        int lookupIndex;

        for(int i = 0; i < chars.length - 3; i++) {
            char c1 = chars[i];

            lookupIndex = i + 3;

            while((lookupIndex = s.indexOf(c1, lookupIndex)) >= 0) {

                String innerString = s.substring(i+1, lookupIndex);

                if(memo.containsKey(innerString)) {
                    countPalindrome += memo.get(innerString) % BIG_NUMBER;
                } else {

                    int pairCount = 0;
                    for (int k = i + 1; k < lookupIndex - 1; k++) {
                        char c2 = chars[k];

                        for (int j = k + 1; j < lookupIndex; j++) {
                            if (chars[j] == c2) {
                                pairCount++;
                            }
                        }
                    }

                    memo.put(innerString, pairCount);
                    countPalindrome += pairCount % BIG_NUMBER;
                }

                lookupIndex++;
            }
        }

        return countPalindrome;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int result = shortPalindrome(s);
        System.out.println(result);
        in.close();
    }
}
