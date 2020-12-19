package queens_attack_2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);
    private static final int NORTH = 0;
    private static final int NORTH_EAST = 1;
    private static final int EAST = 2;
    private static final int SOUTH_EAST = 3;
    private static final int SOUTH = 4;
    private static final int SOUTH_WEST = 5;
    private static final int WEST = 6;
    private static final int NORTH_WEST = 7;

    // Complete the queensAttack function below.
    static int queensAttack(int n, int k, int r_q, int c_q, int[][] obstacles) {
        int[] allowedMoves = new int[8];
        allowedMoves[NORTH] = n - r_q;
        allowedMoves[NORTH_EAST] = Math.min(n - r_q, n - c_q);
        allowedMoves[EAST] = n - c_q;
        allowedMoves[SOUTH_EAST] = Math.min(r_q - 1, n - c_q);
        allowedMoves[SOUTH] = r_q - 1;
        allowedMoves[SOUTH_WEST] = Math.min(r_q - 1, c_q - 1);
        allowedMoves[WEST] = c_q - 1;
        allowedMoves[NORTH_WEST] = Math.min(n - r_q, c_q - 1);
        for (int i = 0; i < k; i++) {
            int[] obstacle = obstacles[i];
            int rowObstacle = obstacle[0];
            int colObstacle = obstacle[1];
            int obstacleDirection = getDirection(r_q, c_q, rowObstacle, colObstacle);
            switch (obstacleDirection) {
                case NORTH:
                    allowedMoves[NORTH] = Math.min(allowedMoves[NORTH], rowObstacle - r_q - 1);
                    break;
                case NORTH_EAST:
                    allowedMoves[NORTH_EAST] = Math.min(allowedMoves[NORTH_EAST], Math.min(rowObstacle - r_q - 1, colObstacle - c_q - 1));
                    break;
                case EAST:
                    allowedMoves[EAST] = Math.min(allowedMoves[EAST], colObstacle - c_q - 1);
                    break;
                case SOUTH_EAST:
                    allowedMoves[SOUTH_EAST] = Math.min(allowedMoves[SOUTH_EAST], Math.min(colObstacle - c_q - 1, r_q - rowObstacle - 1));
                    break;
                case SOUTH:
                    allowedMoves[SOUTH] = Math.min(allowedMoves[SOUTH], r_q - rowObstacle - 1);
                    break;
                case SOUTH_WEST:
                    allowedMoves[SOUTH_WEST] = Math.min(allowedMoves[SOUTH_WEST], Math.min(r_q - rowObstacle - 1, c_q - colObstacle - 1));
                    break;
                case WEST:
                    allowedMoves[WEST] = Math.min(allowedMoves[WEST], c_q - colObstacle - 1);
                    break;
                case NORTH_WEST:
                    allowedMoves[NORTH_WEST] = Math.min(allowedMoves[NORTH_WEST], Math.min(rowObstacle - r_q - 1, c_q - colObstacle - 1));
                    break;
            }
        }
        return Arrays.stream(allowedMoves).sum();
    }

    private static int getDirection(int r_q, int c_q, int rowObstacle, int colObstacle) {
        if (c_q == colObstacle) {
            if (r_q > rowObstacle) return SOUTH;
            if (r_q < rowObstacle) return NORTH;
        }
        if (r_q == rowObstacle) {
            if (c_q > colObstacle) return WEST;
            if (c_q < colObstacle) return EAST;
        }
        if (Math.abs(r_q - rowObstacle) == Math.abs(c_q - colObstacle)) {
            if (c_q < colObstacle) {
                if (r_q > rowObstacle) return SOUTH_EAST;
                return NORTH_EAST;
            }
            if (c_q > colObstacle) {
                if (r_q > rowObstacle) return SOUTH_WEST;
                return NORTH_WEST;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        String[] r_qC_q = scanner.nextLine().split(" ");

        int r_q = Integer.parseInt(r_qC_q[0]);

        int c_q = Integer.parseInt(r_qC_q[1]);

        int[][] obstacles = new int[k][2];

        for (int i = 0; i < k; i++) {
            String[] obstaclesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int obstaclesItem = Integer.parseInt(obstaclesRowItems[j]);
                obstacles[i][j] = obstaclesItem;
            }
        }

        int result = queensAttack(n, k, r_q, c_q, obstacles);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
