package climbing_the_leaderboard;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'climbingLeaderboard' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY ranked
     *  2. INTEGER_ARRAY player
     */

    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        // Write your code here
        int size = player.size();
        List<Integer> result = new ArrayList<>(Collections.nCopies(size, size + 1));
        ranked = new ArrayList<>(new HashSet<>(ranked));
        Collections.sort(ranked);
        Collections.reverse(ranked);

        int startIndex = 0;
        for (int i = size - 1; i >= 0; i--) {
            int score = player.get(i);
            result.set(i, ranked.size() + 1);
            for (int j = startIndex; j < ranked.size(); j++) {
                if (score >= ranked.get(j)) {
                    result.set(i, j + 1);
                    break;
                }
                startIndex++;
            }
        }

        return result;
    }

    static class Score {
        int score;
        int rank;

        public Score(int score, int rank) {
            this.score = score;
            this.rank = rank;
        }
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int rankedCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> ranked = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int playerCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> player = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = Result.climbingLeaderboard(ranked, player);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}

