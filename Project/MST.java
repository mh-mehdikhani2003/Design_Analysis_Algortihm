

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MST {

        // Defines edge structure
        static class Edge {
            int src, dest, weight,num;

            public Edge(int src, int dest, int weight,int num)
            {
                this.src = src;
                this.dest = dest;
                this.weight = weight;
                this.num = num;
            }
        }

        // Defines subset element structure
        static class Subset {
            int parent, rank;

            public Subset(int parent, int rank)
            {
                this.parent = parent;
                this.rank = rank;
            }
        }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int ui, vi, wi;
        List<Edge> graphEdges = new ArrayList<Edge>();
        for (int i = 0; i < m; i++) {
            ui = scanner.nextInt();
            vi = scanner.nextInt();
            wi = scanner.nextInt();
            graphEdges.add(new Edge(ui-1, vi-1, wi, i + 1));
        }
        int q = scanner.nextInt();
        int f;
        ArrayList<Integer>[] students = new ArrayList[q];
        for (int i = 0; i < q; i++) {
            f = scanner.nextInt();
            students[i] = new ArrayList<Integer>();
            for (int j = 0; j < f; j++) {
                students[i].add(scanner.nextInt());
            }
        }

        graphEdges.sort(new Comparator<Edge>() {
            @Override public int compare(Edge o1, Edge o2)
            {
                return o1.weight - o2.weight;
            }
        });
         kruskals(n, graphEdges,students);

    }
    private static void kruskals(int V, List<Edge> edges,ArrayList<Integer>[] students)
    {
        int j = 0;
        int noOfEdges = 0;

        // Allocate memory for creating V subsets
        Subset subsets[] = new Subset[V];

        // Allocate memory for results
        Edge results[] = new Edge[V];

        // Create V subsets with single elements
        for (int i = 0; i < V; i++) {
            subsets[i] = new Subset(i, 0);
        }

        // Number of edges to be taken is equal to V-1
        while (noOfEdges < V - 1) {

            // Pick the smallest edge. And increment
            // the index for next iteration
            Edge nextEdge = edges.get(j);
            int x = findRoot(subsets, nextEdge.src);
            int y = findRoot(subsets, nextEdge.dest);

            // If including this edge doesn't cause cycle,
            // include it in result and increment the index
            // of result for next edge
            if (x != y) {
                results[noOfEdges] = nextEdge;
                union(subsets, x, y);
                noOfEdges++;
            }

            j++;
        }

        // Print the contents of result[] to display the
        // built MST
        boolean nigga1, nigga2;
        for (ArrayList<Integer> student : students) {
            nigga2 = false;
            for (Integer integer : student) {
                nigga1 = false;
                for (int i = 0; i < noOfEdges; i++) {
                    if (integer == results[i].num) {
                        nigga1 = true;
                        break;
                    }
                }
                if (!nigga1) {
                    System.out.println("NO");
                    nigga2 = true;
                    break;
                }
            }
            if (!nigga2) {
                System.out.println("YES");
            }
        }
    }

    // Function to unite two disjoint sets
    private static void union(Subset[] subsets, int x,
                              int y)
    {
        int rootX = findRoot(subsets, x);
        int rootY = findRoot(subsets, y);

        if (subsets[rootY].rank < subsets[rootX].rank) {
            subsets[rootY].parent = rootX;
        }
        else if (subsets[rootX].rank
                < subsets[rootY].rank) {
            subsets[rootX].parent = rootY;
        }
        else {
            subsets[rootY].parent = rootX;
            subsets[rootX].rank++;
        }
    }

    // Function to find parent of a set
    private static int findRoot(Subset[] subsets, int i)
    {
        if (subsets[i].parent == i)
            return subsets[i].parent;

        subsets[i].parent
                = findRoot(subsets, subsets[i].parent);
        return subsets[i].parent;
    }
}

