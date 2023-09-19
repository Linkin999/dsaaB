package Problem1;

import edu.princeton.cs.algs4.Queue;

public class DepthFirstPaths {
    private boolean[] marked;//从这个顶点上调用过dfs()了嘛？
    private int[] edgeto; //从起点到一个顶点的已知路径上的最后一个顶点
    private final int s;//起点

    public DepthFirstPaths(Graph G, int s){
        marked = new boolean[G.V()+1];//数组索引从0开始，但为了更加方便，我们将此处的邻接表扩大1
        edgeto = new int[G.V()+1];//数组索引从0开始，但为了更加方便，我们将此处扩大1
        this.s =s;
        dfs(G,s);
    }

    private void dfs(Graph G, int v) {
        marked[v]=true;
        for(int w:G.adj(v)){
            if(!marked[w]){
                edgeto[w]=v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v)){
            return null;
        }
        else{
            //为求父节点方便，此处可以用queue
            Queue<Integer> path = new Queue<Integer>();
            for(int x=v;x!=s;x=edgeto[x]){
                path.enqueue(x);
            }
            path.enqueue(s);
            return path;
        }
    }
}
