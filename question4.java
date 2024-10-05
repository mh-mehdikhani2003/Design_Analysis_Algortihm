import java.util.Scanner;
public class Main{

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int edges = 0;
        long [][] graph = new long[n][n];
        boolean [][] matrix = new boolean[n][n];

        for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) matrix[i][j] = false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = scanner.nextLong();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] != graph[j][i]) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = 0; k < n; k++) {

                    if (k == i || k == j) continue;

                    if(graph[i][j] > graph[i][k] + graph[j][k]) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                matrix[i][j] = true;
                matrix[j][i] = true;

                for (int k = 0; k < n; k++) {

                    if (k == i || k == j) continue;

                    if (graph[i][j] == graph[j][k] + graph[k][i]) {
                        matrix[i][j] = false;
                        matrix[j][i] = false;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j]) {
                    edges++;
                }
            }
        }
        System.out.println(edges/2);
    }
}