package maximum_palindromes;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Result {
    private static String s;

    /*
     * Complete the 'initialize' function below.
     *
     * The function accepts STRING s as parameter.
     */

    public static void initialize(String s) {
        // This function is called once before all queries.
        Result.s = s;

    }

    /*
     * Complete the 'answerQuery' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER l
     *  2. INTEGER r
     */

    public static int answerQuery(int l, int r) {
        // Return the answer for this query modulo 1000000007.
        String sub = s.substring(l - 1, r);
        Map<Character, Integer> map = new HashMap<>();
        for (char c : sub.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);
        int sortOfAlphabets = map.size();
        if (sortOfAlphabets == 1) return 1;
        // 종류가 두 개 일 때
        // ab aab aabb aaab aabbb aaabbb
        // 1,1: ab
        // 2,1: aba
        // 2,2: abba, baab
        // 3,1: x
        // 3,2: abbba, babab
        // 3,3: a


        if (sub.length() % 2 == 0) {
            int numerator = getFactorial(sortOfAlphabets);
            int denominator = map.values().stream().reduce(1, (left, right) -> getFactorial(left) * getFactorial(right) % 1000000007);
            return numerator / denominator;
        } else {

        }
    }

    private static int getFactorial(int sortOfAlphabets) {
        return IntStream.rangeClosed(2, sortOfAlphabets).reduce(1, (left, right) -> left * right % 1000000007);
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = bufferedReader.readLine();

        Result.initialize(s);

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int l = Integer.parseInt(firstMultipleInput[0]);

                int r = Integer.parseInt(firstMultipleInput[1]);

                int result = Result.answerQuery(l, r);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
