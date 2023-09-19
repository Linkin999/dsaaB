import edu.princeton.cs.algs4.Queue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Zeros {
    int n;
    int m;
    int[][] board = new int[n][m];
    final int neoCount;
    Zeros father;
    int[] x0;
    int[] y0;


    //初始化Node
    public Zeros(int n, int m, int neoCount, Zeros father){
        this.n = n;
        this.m = m;
        this.board = new int[n][m];
        this.neoCount = neoCount;
        this.father = father;
        this.x0 = new int[neoCount];
        this.y0 = new int[neoCount];
    }

    public Zeros(int[][] board, Zeros father){
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j] == 0){
                    count++;
                }
            }
        }
        this.neoCount = count;
        int neo = 0;
        this.n = board.length;
        this.m = board[0].length;
        int[][] array=new int[board.length][board[0].length];
        for(int i = 0; i < board.length; i++){
            System.arraycopy(board[i], 0, array[i], 0, board[0].length);
        }
        this.board = array.clone();
        this.father = father;
        this.x0 = new int[count];
        this.y0 = new int[count];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j] == 0){
                    x0[neo] = i;
                    y0[neo] = j;
                    neo++;
                }
            }
        }
    }

    //复制Node
    public Zeros(Zeros node){
        this.n = node.n;
        this.m = node.m;
        int[][] array=new int[node.board.length][node.board[0].length];
        this.x0 = new int[node.x0.length];
        this.y0 = new int[node.y0.length];
        for(int i = 0; i < node.board.length; i++){
            System.arraycopy(node.board[i], 0, array[i], 0, node.board[0].length);
        }
        this.board = array.clone();
        this.neoCount = node.neoCount;
        this.father = node.father;
        System.arraycopy(node.x0, 0, this.x0, 0, node.x0.length);
        System.arraycopy(node.y0, 0, this.y0, 0, node.y0.length);
    }

    public static void printState(Zeros state){
        for(int i=0;i<state.n;i++){
            for(int j=0;j<state.m;j++){
                System.out.print(state.board[i][j]+" ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public static String arrayToString(Zeros state){
        String s="";
        for(int i =0;i<state.n;i++){
            for(int j =0;j<state.m;j++){
                s=s.concat(String.valueOf(state.board[i][j]));//此处可能有问题
            }
        }
        return s;
    }

    public boolean isComplete(){
        int mistake = 0;
        int[] total = new int[n * m];
        for (int i = 0; i < n * m; i++) {
            total[i] = this.board[i / board[0].length][i % board[0].length];
        }
        for (int i = 0; i < total.length - neoCount; i++) {
            if(total[i] != i + 1){
                mistake++;
            }
        }
        for (int i = total.length - neoCount; i < total.length; i++) {
            if (total[i] != 0){
                mistake++;
            }
        }
        return mistake == 0;
    }

    public static void main(String[] args) throws FileNotFoundException {

        try (/*File file = new File("data/2.in");
                Scanner scanner = new Scanner(file);
                int N = scanner.nextInt();
                int M = scanner.nextInt();
                int[][] initArray = new int[N][M];
                for(int i=0;i<N;i++){
                    for(int j=0;j<M;j++){
                        initArray[i][j]=scanner.nextInt();
                    }
                }
                scanner.close();*/
        Scanner input = new Scanner(System.in)) {
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
            Queue<Zeros> queue = new Queue<Zeros>();//要搜索的状态

            //初始状态
            Zeros initState = new Zeros(initArray, null);

            //将初始状态放入
            ss.add(arrayToString(initState));
            queue.enqueue(initState);

            boolean flag = false;
            Zeros ans = null;

            while(!queue.isEmpty()){
                Zeros currentState = queue.dequeue();
                printState(currentState);

                if(currentState.isComplete()){
                    flag = true;
                    ans = currentState;
                    break;
                }
                int n = currentState.n;
                int m = currentState.m;

                //目前状态 0所在的位置
                for (int i = 0; i < currentState.x0.length; i++) {
                    int x = currentState.x0[i];
                    int y = currentState.y0[i];

                    //上面能不能换
                    if(x - 1 >= 0 && currentState.board[x - 1][y] != 0){
                        Zeros next = new Zeros(currentState);
                        //
                        
                        printState(next);

                        //更新
                        next.board[x][y]=next.board[x-1][y];
                        next.board[x-1][y]=0;
                        next.x0[i]=x-1;
                        next.y0[i]=y;

                        if(ss.contains(arrayToString(next))){

                        }
                        else{
                            ss.add(arrayToString(next));
                            queue.enqueue(next);
                        }
                        //printState(next);
                    }

                    //printState(currentState);

                    //下边能不能换
                    if(x+1<=n-1 && currentState.board[x + 1][y] != 0){
                        Zeros next = new Zeros(currentState);
                        //printState(next);

                        //更新
                        next.board[x][y]=next.board[x+1][y];
                        next.board[x+1][y]=0;
                        next.x0[i] = x+1;
                        next.y0[i] = y;

                        if(ss.contains(arrayToString(next))){

                        }
                        else{
                            ss.add(arrayToString(next));
                            queue.enqueue(next);
                        }
                        //printState(next);
                    }

                    //左边能不能换
                    if(y-1>=0 && currentState.board[x][y - 1] != 0){
                        Zeros next = new Zeros(currentState);

                        //更新
                        next.board[x][y]=next.board[x][y-1];
                        next.board[x][y-1]=0;
                        next.x0[i] = x;
                        next.y0[i] = y-1;

                        if(ss.contains(arrayToString(next))){

                        }
                        else{
                            ss.add(arrayToString(next));
                            queue.enqueue(next);
                        }
                    }

                    //右边能不能换
                    if(y+1<=m-1 && currentState.board[x][y + 1] != 0){
                        Zeros next = new Zeros(currentState);

                        //更新
                        next.board[x][y]=next.board[x][y+1];
                        next.board[x][y+1]=0;
                        next.x0[i] = x;
                        next.y0[i] = y+1;

                        if(ss.contains(arrayToString(next))){

                        }
                        else{
                            ss.add(arrayToString(next));
                            queue.enqueue(next);
                        }
                    }
                }
            }

            if(flag){
                Zeros t = ans;
                printState(t);
                t = t.father;

                while(t != null){
                    printState(t);
                    t = t.father;
                }
            }else{
                System.out.print("No solution");
            }
        }
    }
}




