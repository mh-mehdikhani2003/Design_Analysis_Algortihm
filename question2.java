import java.util.Scanner;
import java.util.*;
import java.lang.*;
import java.io.*;

class Graph_Shortest_Path {

    static int num_Vertices ;  //max number of vertices in graph

    // find a vertex with minimum distance
    int minDistance(int path_array[], Boolean sptSet[])   {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < num_Vertices; v++){
            if (sptSet[v] == false &&  path_array[v] <= min ) {
                min = path_array[v];
                min_index = v;
            }
        }

        if (min == Integer.MAX_VALUE){
            return -1;
        }else {
            return min_index;
        }
    }

    public static void setNum_Vertices(int num_Vertices) {
        Graph_Shortest_Path.num_Vertices = num_Vertices;
    }

    // print the array of distances (path_array)
    void printMinpath(int path_array[],int q)   {
        int [] answer = new int[2];
        answer[0] = path_array[q];
        answer[1] = 1;
        for (int i = 0; i < num_Vertices; i++){
            if(answer[0]<path_array[i] && path_array[i]!=Integer.MAX_VALUE){
                answer[0] = path_array[i];answer[1]=1;
            }else if(answer[0] == path_array[i]){
                answer[1] ++;
            }
        }
        System.out.println("("+q+", "+answer[0]+", "+ Integer.toString( answer[1])+")");
    }

    // Implementation of Dijkstra's algorithm for graph (adjacency matrix)
    void algo_dijkstra(int graph[][], int src_node)  {
        int path_array[] = new int[num_Vertices];
        // The output array. dist[i] will hold
        // the shortest distance from src to i

        // spt (shortest path set) contains vertices that have shortest path
        Boolean sptSet[] = new Boolean[num_Vertices];

        // Initially all the distances are INFINITE and stpSet[] is set to false
        for (int i = 0; i < num_Vertices; i++) {
            path_array[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Path between vertex and itself is always 0
        path_array[src_node] = 0;
        boolean no_attach = true;
        int u = src_node;
        // now find shortest path for all vertices
        for (int count = 0; count < num_Vertices - 1; count++) {
            // call minDistance method to find the vertex with min distance

            // the current vertex u is processed
            sptSet[u] = true;
            no_attach = true;
            // process adjacent nodes of the current vertex
            for (int v = 0; v < num_Vertices; v++){
            // if vertex v not in sptset then update it
                if (!sptSet[v] && graph[u][v] != 0 && path_array[u] !=
                        Integer.MAX_VALUE && path_array[u]
                        + graph[u][v] < path_array[v]){
                    path_array[v] = path_array[u] + graph[u][v];
                }
                if (graph[src_node][v]!=0){
                    no_attach = false;
                }
            }
            u = minDistance(path_array, sptSet);
            if (u == -1){
                break;
            }

        }
        if (no_attach){
            System.out.println("("+src_node+", 0, 1)");
        }else {
            printMinpath(path_array,src_node);
        }
    }
}
public class main {
    public static void main(String [] args){
        //n:vertics m:edges Q:number of cities
        int n,m,Q;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        Q= scanner.nextInt();
        int u,v,l;
        int graph[][] = new int[n][n];
        for (int i = 0; i < m; i++) {
            u = scanner.nextInt();
            v = scanner.nextInt();
            l = scanner.nextInt();
            if (graph[u][v] == 0 || (graph[u][v]!=0  && graph[u][v]>l)){
                graph[u][v] = l;
                graph[v][u] = l;
            }
        }
        Graph_Shortest_Path g = new Graph_Shortest_Path();
        g.setNum_Vertices(n);
        int q;
        for (int i = 0; i < Q; i++) {
            q = scanner.nextInt();

            g.algo_dijkstra(graph, q);
        }
    }
}
