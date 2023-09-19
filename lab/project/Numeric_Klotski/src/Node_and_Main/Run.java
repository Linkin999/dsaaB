package Node_and_Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

import static Node_and_Main.Node.arrayToString;
import static Node_and_Main.Node.printState;
import static Node_and_Main.Node.transform;



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

            ArrayList<String> close = new ArrayList<String>();//判断是否在close中
            ArrayList<String> openJudge = new ArrayList<String>();//判断是否在open中
            PriorityQueue<Node> open = new PriorityQueue<Node>();//要搜索的状态

            //初始状态
            Node initState = new Node(initArray, null,numberOfblock,allOfBlock);
            //将初始状态放入
            open.offer(initState);
            openJudge.add(arrayToString(initState));

            boolean flag = false;
            Node ans = null;

            while (!open.isEmpty()){
                Node currentState = open.poll();
                if(currentState.isComplete()){
                    flag = true;
                    ans = currentState;
                    break;
                }
                currentState.findNext(open, openJudge, close);
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
                //System.out.print(boards.pop());
    
                while (!move.isEmpty()){
                    System.out.println(move.pop());
                    //System.out.print(boards.pop());
                }
            }
            else{
                System.out.print("No\n");
            }
            System.out.print((System.currentTimeMillis() - start) + " ms");
    

            




        }
        
        /*File file = new File("D:\\Study in SUSTech\\First semester of junior year\\dsaaB\\lab\\project\\1.in");
        Scanner scanner = new Scanner(file);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int[][] initArray = new int[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                initArray[i][j]=scanner.nextInt();
            }
        }
        //获取block的数量
        int numberOfblock = scanner.nextInt();

        //allofblock为numofblock*2的二维数组，第二列代表block的种类，第一列代表block中左上角的数字，
        int[][] allOfBlock=new int[numberOfblock][2];
        if(numberOfblock!=0){
            for(int i=0;i<numberOfblock;i++){
                allOfBlock[i][0]=scanner.nextInt();
                //因为表示block种类的输入是字符类型，将各种种类转换为整数来分类，
                allOfBlock[i][1]=transform(scanner.next());
            }
        }else{
            allOfBlock=null;
        }

        scanner.close();


        ArrayList<String> close = new ArrayList<String>();//判断是否在close中
        ArrayList<String> openJudge = new ArrayList<String>();//判断是否在open中
        PriorityQueue<Node> open = new PriorityQueue<Node>();//要搜索的状态

        //初始状态
        Node initState = new Node(initArray, null,numberOfblock,allOfBlock);

        

        //将初始状态放入
        open.offer(initState);
        openJudge.add(arrayToString(initState));

        boolean flag = false;
        Node ans = null;

        while (!open.isEmpty()){
            Node currentState = open.poll();
            if(currentState.isComplete()){
                flag = true;
                ans = currentState;
                break;
            }
            currentState.findNext(open, openJudge, close);
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
            System.out.print(boards.pop());

            while (!move.isEmpty()){
                System.out.println(move.pop());
                System.out.print(boards.pop());
            }
        }
        else{
            System.out.print("No\n");
        }
        System.out.print((System.currentTimeMillis() - start) + " ms");*/
    }
}
