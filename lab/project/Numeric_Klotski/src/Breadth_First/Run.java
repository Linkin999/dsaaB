package Breadth_First;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

import edu.princeton.cs.algs4.Queue;

import static Breadth_First.Node.arrayToString;
import static Breadth_First.Node.printState;
import static Breadth_First.Node.transform;



public class Run {
    public static void main(String[] args) throws FileNotFoundException {
        long start = System.currentTimeMillis();
        //standard input
        try (Scanner input = new Scanner(System.in)) {
            int N = input.nextInt();
            int M = input.nextInt();
            int[][] initArray = new int[N][M];
            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    initArray[i][j]=input.nextInt();
                }
            }
            int numberOfblock = input.nextInt();
            int[][] allOfBlock=new int[numberOfblock][2];
            if(numberOfblock!=0){
                for(int i=0;i<numberOfblock;i++){
                    allOfBlock[i][0]=input.nextInt();
                    //因为表示block种类的输入是字符类型，将各种种类转换为整数来分类，
                    allOfBlock[i][1]=transform(input.next());
                }
            }else{
                allOfBlock=null;
            }

            ArrayList<String> close = new ArrayList<String>();//判断是否在close中，表示已经搜索过的状态
            Queue<Node> queue = new Queue<Node>();//要搜索的状态


            

            //初始状态
            Node initState = new Node(initArray, null,numberOfblock,allOfBlock);
            //将初始状态放入
            queue.enqueue(initState);

            boolean flag = false;
            Node ans = null;

            while (!queue.isEmpty()){
                Node currentState = queue.dequeue();
                if(currentState.isComplete()){
                    flag = true;
                    ans = currentState;
                    break;
                }
                currentState.findNext(queue,close);
            }
    
            Stack<String> move = new Stack<String>();
            Stack<String> boards = new Stack<String>();
            int moveCount = 0;

            if(flag){
                Node t = ans;
    
    
    
                boards.push(printState(t));
                move.push(t.movement);
                t = t.father;
    
                while(t != null){
                    boards.push(printState(t));
                    if(t.movement != null){
                        move.push(t.movement);
                    }
                    moveCount++;
                    t = t.father;
                }
    
                System.out.println("Yes");
                System.out.println(moveCount);
                
                while (!move.isEmpty()){
                    System.out.println(move.pop());
                    
                }
            }
            else{
                System.out.print("No\n");
            }
            System.out.print((System.currentTimeMillis() - start) + " ms");
        }
        
     
    }
}

