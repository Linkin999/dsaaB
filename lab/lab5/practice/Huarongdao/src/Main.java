import java.util.ArrayList;
import edu.princeton.cs.algs4.Queue;
import java.util.Scanner;


public class Main {

    //定义一个表示状态的私有类
    private class Node{
        private int n;//行数
        private int m;//列数

        private int[][] array;

        //0的位置
        private int x0;
        private int y0;

        private Node father;//指向父状态的链接

        //先把数组初始化，不初始化即为空
        /*public void init(int n,int m){
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    array[i][j]=0;
                }
            }
        }*/

        //有些小疑问
         /*public Node(int N,int M, Node Father){
            this.n=N;
            this.m=M;
            this.father=Father;
            init(this.n,this.m);
            this.x0=this.n-1;
            this.y0=this.m-1;
        }*/


        //将输入的数字赋值给矩阵并记录0的位置
        public Node(int[][] Array,int N,int M, Node Father){
            this.n=N;
            this.m=M;
            this.father=Father;
            this.array=Array;

            //找0的位置
            for(int i=0;i<this.n;i++){
                for(int j=0;j<this.m;j++){
                    if(this.array[i][j]==0){
                        this.x0=i;
                        this.y0=j;
                        break;
                    }
                }
            }
        }

        //拷贝构造函数，方便状态赋值
        public Node(Node a,Node Father){
            this.n=a.n;
            this.m=a.m;
            this.x0=a.x0;
            this.y0=a.y0;
            this.array=a.array;
            this.father=Father;
        }
    }


    //将状态的矩阵转化为字符串，方便比较判重
    public static String arraytoString(Node state){
        String s="";
        for(int i =0;i<state.n;i++){
            for(int j =0;j<state.m;j++){
                s=s.concat(String.valueOf(state.array[i][j]));//此处可能有问题
            }
        }
        return s;
    }

    //判断最后是否完成
    public static boolean isComplete(Node state){
        int mistake=0;
        if(state.array[state.n-1][state.m-1]!=0){
            return false;
        }else{
            for(int i=0;i<state.n;i++){
                for(int j=0;j<state.m;j++){
                    if(i==state.n-1&&j==state.m-1){
                        continue;
                    }
                    if(state.array[i][j]!=(state.n*i+j+1)){
                        mistake=mistake+1;
                        break;
                    }
                }
            }
            if(mistake!=0){
                return false;
            }else{
                return true;
            }
        }
    }

    //将每个状态的矩阵打印出来
    public static void printState(Node state){
        for(int i=0;i<state.n;i++){
            for(int j=0;j<state.m;j++){
                System.out.print(state.array[i][j]+" ");
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) throws Exception {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("输入行数：");
            int N = input.nextInt();

            System.out.println("输入列数：");
            int M = input.nextInt();

            int[][] initArray = new int[N][M];

            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    initArray[i][j]=input.nextInt();
                }
            }

            ArrayList<String> ss=new ArrayList<String>();//判断是否重复
            Queue<Node> queue = new Queue<Node>();//要搜索的状态

            //初始状态
            Main app = new Main();
            Main.Node initState = app.new Node(initArray, N, M, null);

            //将初始状态放入
            ss.add(arraytoString(initState));
            queue.enqueue(initState);

            boolean flag = false;
            Node ans=null;

            while(!queue.isEmpty()){
                Node currentState = queue.dequeue();
                if(isComplete(currentState)){
                    flag=true;
                    ans=currentState;
                    break;
                }

                //目前状态 0所在的位置
                int x=currentState.x0;
                int y=currentState.y0;
                int n=currentState.n;
                int m=currentState.m;

                //上面能不能换
                if(x-1>=0){
                    Main app1 = new Main();
                    Main.Node next = app1.new Node(currentState,currentState);

                    //更新
                    next.array[x][y]=currentState.array[x-1][y];
                    next.array[x-1][y]=0;
                    next.x0=x-1;
                    next.y0=y;

                    if(ss.contains(arraytoString(next))){
                    }else{
                        ss.add(arraytoString(next));
                        queue.enqueue(next);
                    }

                }
                //下边能不能换
                if(x+1<=n-1){
                    Main app1 = new Main();
                    Main.Node next = app1.new Node(currentState,currentState);

                    //更新
                    next.array[x][y]=currentState.array[x+1][y];
                    next.array[x+1][y]=0;
                    next.x0=x+1;
                    next.y0=y;

                    if(ss.contains(arraytoString(next))){
                    }else{
                        ss.add(arraytoString(next));
                        queue.enqueue(next);
                    }

                }

                //左边能不能换
                if(y-1>=0){
                    Main app1 = new Main();
                    Main.Node next = app1.new Node(currentState,currentState);

                    //更新
                    next.array[x][y]=currentState.array[x][y-1];
                    next.array[x][y-1]=0;
                    next.x0=x;
                    next.y0=y-1;

                    if(ss.contains(arraytoString(next))){
                    }else{
                        ss.add(arraytoString(next));
                        queue.enqueue(next);
                    }

                }

                //右边能不能换
                if(y+1<=m-1){
                    Main app1 = new Main();
                    Main.Node next = app1.new Node(currentState,currentState);

                    //更新
                    next.array[x][y]=currentState.array[x][y+1];
                    next.array[x][y+1]=0;
                    next.x0=x;
                    next.y0=y+1;

                    if(ss.contains(arraytoString(next))){
                    }else{
                        ss.add(arraytoString(next));
                        queue.enqueue(next);
                    }

                }
            }

            if(flag){
                Node t =ans;
                while(true){
                    printState(t);
                    t=t.father;
                    if(t==null){
                        break;
                    }
                }
            }else{
                System.out.print("No solution");
            }

        }



















        /*int[][] array =new int[2][3];
        int temp=1;
        for(int i=0;i<2;i++){
            for (int j=0;j<3;j++){
                array[i][j]=temp;
                temp=temp+1;
            }
        }

        int h = array[1][2];
        array[1][2]=array[0][1];
        array[0][1]=h;
        for(int i=0;i<2;i++){
            for (int j=0;j<3;j++){
                System.out.print(array[i][j]+" ");
            }
        }


        ArrayList<String> ss=new ArrayList<String>();

        for(int i =0;i<2;i++){
            for(int j =0;j<3;j++){
                ss.add(String.valueOf(array[i][j]));
            }
        }
        if(ss.contains("6")){

        System.out.print(ss);

        }*/



    }
}
