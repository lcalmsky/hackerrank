package coin_change;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'getWays' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. LONG_INTEGER_ARRAY c
     */

    public static long getWays(int n, List<Long> c) {
        long[][] dp = new long[n + 1][c.size()];
        for (long[] row : dp) Arrays.fill(row, -1);
        return makeChange(n, c, 0, dp);
    }

    private static long makeChange(long n, List<Long> c, int index, long[][] dp) {
        if (index > c.size() - 1 && n > 0) return 0;
        if (n == 0) {
            dp[0][index] = 1;
            return 1;
        }
        if (n < 0) return 0;
        if (dp[(int) n][index] >= 0) return dp[(int) n][index];

        long denominator = c.get(index);
        long ways = 0;
        long remaining = n - denominator;
        ways += makeChange(remaining, c, index, dp) + makeChange(n, c, index + 1, dp);
        dp[(int) n][index] = ways;
        return ways;
    }


}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<Long> c = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Long::parseLong)
                .collect(toList());

        // Print the number of ways of making change for 'n' units using coins having the values given by 'c'

        long ways = Result.getWays(n, c);

        bufferedWriter.write(String.valueOf(ways));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
