

import java.util.ArrayList;
import java.util.Scanner;
class Node {
    long DPA;
    long DPB;
    boolean kaj;
    int number;
    ArrayList<Node> Children;
    Node Parent;

    Node(int d)
    {

        DPA = -1;DPB = -1;
        number = d;Parent = null;
    }
}
class Graph{
    Node Root;

   Graph(int[] parent,int[] kaj){
       boolean[] lif = new boolean[kaj.length];
       ArrayList<Node> Vertices = new ArrayList<>();
       for (int i = 0; i < kaj.length; i++){
           Vertices.add(new Node(i));
           Vertices.get(i).Children = new ArrayList<>();
           if(kaj[i]==1){
               Vertices.get(i).kaj = true;
           }else {
               Vertices.get(i).kaj = false;
           }
       }
       Root = Vertices.get(0);
       for (int i = 0; i < parent.length; i++) {
           Vertices.get(i+1).Parent = Vertices.get(parent[i]);
           Vertices.get(parent[i]).Children.add(Vertices.get(i+1));
           lif[parent[i]] = true;
       }
       for (int i = 0; i < lif.length; i++){
           if(!lif[i]){
               if(Vertices.get(i).kaj){
                   Vertices.get(i).DPA = 1;
                   Vertices.get(i).DPB = 0;
               }else{
                   Vertices.get(i).DPA = 0;
                   Vertices.get(i).DPB = 1;
               }

           }
       }
   }

   public void DFS(Node s){
       if(s.DPB == -1){
           for (Node child : s.Children) {
               DFS(child);
           }
           if(s.kaj){
               s.DPA = 1;
               for (Node child : s.Children) {
                   s.DPA *= (child.DPA+child.DPB);
                   s.DPA %= (1000000000+7);
               }
               s.DPB = 0;
           }else {
               s.DPB = 1;
               for (Node child : s.Children) {
                   s.DPB *= (child.DPA+child.DPB);
                   s.DPB %= (1000000000+7);
               }
               s.DPA = 0;
               long zarb ;
               for (Node child : s.Children) {
                   zarb = 1;
                   for (Node childs : s.Children) {
                       if(childs != child){
                           zarb *= (childs.DPA+childs.DPB);
                           zarb %= (1000000000+7);
                       }
                   }
                   s.DPA +=  (child.DPA*zarb);
                   s.DPA %= (1000000000+7);
               }
           }
       }
   }
}
public class kaj {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] parent = new int[n-1];
        int[] kaj = new int[n];
        for (int i = 0; i < n-1; i++) {
            parent[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            kaj[i] = scanner.nextInt();
        }
        Graph graph = new Graph(parent,kaj);
        graph.DFS(graph.Root);
        System.out.println(graph.Root.DPA );
    }
}
