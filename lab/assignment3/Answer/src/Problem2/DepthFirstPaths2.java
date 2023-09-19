package Problem2;

import edu.princeton.cs.algs4.Stack;

public class DepthFirstPaths2{
    private boolean[] marked;//从这个顶点上调用过dfs()了嘛？
    private int[][] edgeto; //第一行从起点到一个顶点的已知路径上的最后一个顶点，第二行表示那条边的代价
    private final int s;//起点
    public boolean[] Leafnode;//是叶节点为true,不是叶节点则为false


    public DepthFirstPaths2(Graph2 G, int s){
        marked = new boolean[G.V()+1];//数组索引从0开始，但为了更加方便，我们将此处扩大1
        edgeto = new int[2][G.V()+1];//数组索引从0开始，但为了更加方便，我们将此处扩大1
        Leafnode = new boolean[G.V()+1];//数组索引从0开始，但为了更加方便，我们将此处扩大1
        this.s =s;
        dfs(G,s);
    }

    private void dfs(Graph2 G, int v) {
        marked[v]=true;

        //判断该节点是不是叶节点
        int count=0;
        for(int w:G.adj1(v)){
            if(!marked[w]){
                count=count+1;
            }
        }
        if(count==0){
            Leafnode[v]=true;
        }
        else{
            Leafnode[v]=false;
        }

        for(int w:G.adj1(v)){
            if(!marked[w]){
                edgeto[0][w]=v;
                edgeto[1][w]=findedgecost(G, v, w);
                dfs(G, w);
            }            
        } 
    }

    private int findedgecost(Graph2 G,int v,int w){
        int index=0;
        for(int i=0;i<G.costmap()[0].length;i++){
            if((G.costmap()[0][i]==v&&G.costmap()[1][i]==w)||(G.costmap()[0][i]==w&&G.costmap()[1][i]==v)){
                index=i;
                break;
            }

        }
        return G.costmap()[2][index];

    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v)){
            return null;
        }

        Stack<Integer> path = new Stack<Integer>();
        for(int x =v;x!=s;x=edgeto[0][x]){
            path.push(x);
        }
        path.push(s);
        return path;
    }
    //计算每条路径的代价
    public int costOfPathTo(int v){
        if(!hasPathTo(v)){
            return 0;
        }
        Stack<Integer> stack = (Stack) pathTo(v);
        int cost=0; 
        int start = (int) stack.pop();//显而易见，栈顶第一个元素为1
        while(!stack.isEmpty()){
            int temp=(int) stack.pop();
            cost=cost+edgeto[1][temp];
        }
        return cost;
    }

    

}