

import java.util.*;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Hagvartz {
    
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader()
        {
            br = new BufferedReader(
                    new InputStreamReader(System.in));
        }

        String next()
        {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() { return Integer.parseInt(next()); }

       
    }
    List<List<Nodes> > adj;
    private int V;
    private PriorityQueue<Nodes> Min;
    private Set<Integer> sptSet;
    private long distance[];

    public Hagvartz(int V) {
        sptSet = new HashSet<Integer>();
        distance = new long[V];
        Min = new PriorityQueue<Nodes>(V, new Nodes());
        this.V = V;
    }

    public void dijkstra(List<List<Nodes> > adj, int src)
    {
        Min.add(new Nodes(src, 0));
        this.adj = adj;
        for (int i = 0; i < V; i++)
            distance[i] = Long.MAX_VALUE;
        distance[src] = 0;
        while (sptSet.size() != V) {
            if (Min.isEmpty())
                return;
            int u = Min.remove().node;
            if (sptSet.contains(u))
                continue;
            sptSet.add(u);
            e_Neighbours(u);
        }
    }

    private void e_Neighbours(int u) {
        long edge = -1;
        long newdis = -1;
        for (int i = 0; i < adj.get(u).size(); i++) {
            Nodes v = adj.get(u).get(i);
            if (!sptSet.contains(v.node)) {
                edge = v.cost;
                newdis = distance[u] + edge;
                if (newdis < distance[v.node])
                    distance[v.node] = newdis;
                Min.add(new Nodes(v.node, distance[v.node]));
            }
        }
    }

    public static void main(String[] args) {
        FastReader scanner = new FastReader();
        int V = scanner.nextInt();
        int E = scanner.nextInt();
        int Start = scanner.nextInt();
        int End = scanner.nextInt();
        int GardNumber = scanner.nextInt();
        List<List<Nodes> > adj
                = new ArrayList<List<Nodes> >();
        // O(n)
        for (int i = 0; i < V; i++){
            List<Nodes> item = new ArrayList<Nodes>();
            adj.add(item);
        }

        int Ui, Vi, Wi, pla[] = new int[GardNumber];
        long[] ans;
        //every index should be -1;
        for (int i = 0; i < E; i++) {
            Ui = scanner.nextInt();
            Vi = scanner.nextInt();
            Wi = scanner.nextInt();
            //creating adjacency list
            adj.get(Ui - 1).add(new Nodes( Vi - 1,Wi));
            adj.get(Vi - 1).add(new Nodes( Ui - 1,Wi));
        }

        //input guards
        for (int i = 0; i < GardNumber; i++) {
            pla[i] = scanner.nextInt() - 1;
        }

       Hagvartz dMin = new Hagvartz(V);
        dMin.dijkstra(adj, End -1);
        boolean know = false;
        for (int i = 0; i < pla.length; i++) {
            if (dMin.distance[pla[i]] <= dMin.distance[Start - 1]) {
                know = true;break;
            }
        }
        if (know) {
            System.out.println("impossible");
        } else {
            System.out.println(dMin.distance[Start - 1]);
        }
    }
}
class Nodes implements Comparator<Nodes> {
    public int node;
    public long cost;
    @Override public int compare(Nodes node1, Nodes node2) {
        if (node1.cost < node2.cost)
            return -1;

        if (node1.cost > node2.cost)
            return 1;

        return 0;
    }
    public Nodes(int node, long cost) {

        this.node = node;
        this.cost = cost;
    }
    public Nodes() {}

}


