package JQuoridors;

import java.util.ArrayList;
import java.util.HashSet;

public class MyGraph {
    private HashSet<Integer> [] node;

    public MyGraph (int n){
        this.node = new HashSet[n];
        for (int i = 0; i < n; i++)
            this.node[i] = new HashSet<>();
    }

    public boolean connect(int a, int b){
        try {
            this.node[a].add(b);
            this.node[b].add(a);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean disconnect(int a, int b){
        try {
            this.node[a].remove(b);
            this.node[b].remove(a);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean isConnect (int a, int b){
        try {
            if (this.node[a].contains(b) || this.node[b].contains(a))
                return true;
            else
                return false;
        }catch (Exception e){
            return false;
        }
    }

    private int [] BFS(int a){
        int [] step = new int [this.node.length];
        ArrayList<Integer> queue = new ArrayList<>();
        queue.add(a);
        step [a] = 1;
        while (queue.size() != 0){
            for (int i = 0; i < this.node.length; i++){
                if (this.node[queue.get(0)].contains(i) && step[i] == 0){
                    step[i] = step[queue.get(0)] + 1;
                    queue.add(i);
                }
            }
            queue.remove(0);
        }
        return step;
    }

    public int nextNodeInShortestPath(int a, int b){
        int [] step = BFS(a);
        int currnet = b;
        while (step[currnet] > 2){
            for (int i = 0; i < this.node.length; i++){
                if (step[currnet] - 1 == step[i] && node[currnet].contains(i)){
                    currnet = i;
                    break;
                }
            }
        }
        return currnet;
    }

    public boolean hasPath (int a, int b){
        int [] step = BFS(a);
        if (step[b] == 0)
            return false;
        else
            return true;
    }

    public int stepsFromAtoB (int a, int b){
        return BFS(a)[b];
    }

    public MyGraph copy(){
        MyGraph graph = new MyGraph(this.node.length);
        for (int i = 0; i < this.node.length; i++){
            graph.node[i] = (HashSet<Integer>) this.node[i].clone();
        }
        return graph;
    }
}
