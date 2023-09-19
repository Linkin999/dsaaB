package Problem2;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class Graph2 {
    private final int V; //顶点数目
    private final int E; //边的数目
    private int[][] costmap;//E*3 数组记录每条边的cost,前两行对应的位置记录一条边的两个顶点，第三行对应的位置记录该边的cost
    private Bag<Integer>[] adj; //邻接表,邻接表的第一行代表相邻的顶点
   
    public Graph2(int V){
        this.V=V;
        this.E=V-1;//在这个题目中边的数目比顶点的数目少一
        adj = (Bag<Integer>[]) new Bag[V+1];//创建邻接表,数组索引从0开始，但为了更加方便，我们将此处的邻接表扩大1
        for(int v=1;v<=V;v++){//在这个题目中顶点是从1到V的
            adj[v]=new Bag<Integer>();
        }
        this.costmap = new int[3][E];
    }

    public Graph2(In in){
        this(in.readInt());//读取V并将图初始化
        int targetCost = in.readInt();
        int E =this.V-1; //E=V-1
        for(int i = 0;i<E;i++){
            int v =in.readInt();
            int w =in.readInt();
            int cost = in.readInt();
            addEdge(v,w);
            costmap[0][i]=v;
            costmap[1][i]=w;
            costmap[2][i]=cost;
        }
    }

    private void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    //返回邻接表
    public Iterable<Integer> adj1(int v){
        return adj[v];
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }
    public int[][] costmap(){
        return costmap;
    }

    
    
}
