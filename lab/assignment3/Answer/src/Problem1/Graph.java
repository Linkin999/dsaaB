package Problem1;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class Graph {
    private final int V; //顶点数目
    private final int E; //边的数目
    private Bag<Integer>[] adj; //邻接表
    public Graph(int V){
        this.V=V;
        this.E=V-1;//在这个题目中边的数目比顶点的数目少一
        adj = (Bag<Integer>[]) new Bag[V+1];//创建邻接表,数组索引从0开始，但为了更加方便，我们将此处的邻接表扩大1
        for(int v=1;v<=V;v++){//在这个题目中顶点是从1到V的
            adj[v]=new Bag<Integer>();

        }
    }

    public Graph(In in){
        this(in.readInt());//读取V并将图初始化
        int E =this.V-1; //E=V-1
        for(int i = 0;i<E;i++){
            int v =in.readInt();
            int w =in.readInt();
            addEdge(v,w);
        }
    }

    public int V(){return V;}
    public int E(){return E;}

    private void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }
    
}
