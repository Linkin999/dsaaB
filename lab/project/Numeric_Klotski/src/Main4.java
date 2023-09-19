import java.util.ArrayList;
import java.util.Scanner;

import edu.princeton.cs.algs4.Queue;

public class Main4 {
        //定义一个表示状态的私有类
        private static class Node{
            private int n;//行数
            private int m;//列数
    
            private int[][] array;
    
            //block
            private int numberOfblock;
            //行数代表该种block的种类
            private int[][] block1;//1*2的block,以二维数组的形式记录，行数代表该种block的个数，每行的元素代表该block中的元素
            private int[][] block2;//2*1的block，以二维数组的形式记录，行数代表该种block的个数，每行的元素代表该block中的元素
            private int[][] block3;//2*2的block，以二维数组的形式记录，行数代表该种block的个数，每行的元素代表该block中的元素
    
            private Node father;//指向父状态的链接
    
            //先把数组初始化，不初始化即为空
            public void initArray(int n,int m){
                for(int i=0;i<n;i++){
                    for(int j=0;j<m;j++){
                        array[i][j]=0;
                    }
                }
            }
            public int[][] initBlock1(int numberOfblock){
                int[][] array= new int[numberOfblock][2];
                for(int i=0;i<numberOfblock;i++){
                    for(int j=0;j<2;j++){
                        array[i][j]=0;
                    }
                }
                return array;
            }
    
            public int[][] initBlock2(int numberOfblock){
                int[][] array= new int[numberOfblock][2];
                for(int i=0;i<numberOfblock;i++){
                    for(int j=0;j<2;j++){
                        array[i][j]=0;
                    }
                }
                return array;
            }
    
            public int[][] initBlock3(int numberOfblock){
                int[][] array= new int[numberOfblock][4];
                for(int i=0;i<numberOfblock;i++){
                    for(int j=0;j<4;j++){
                        array[i][j]=0;
                    }
                }
                return array;
            }
    
    
        
            public Node(int N,int M,Node Father){
                this.n=N;
                this.m=M;
                this.father=Father;
                initArray(this.n,this.m);
            }
    
            //将输入的数字赋值给矩阵
            //sizeofblock为numofblock*2的二维数组，第一列代表block的种类，第二列代表block中左上角的数字，
            //1代表1*2的block，2代表2*1的block，3代表2*2的block，所以需要在输入之后根据输入生成对应的数组
            public Node(int[][] Array,int N,int M,int numberOfblock,int[][] sizeOfBlock,Node Father){
                this.n=N;
                this.m=M;
                this.father=Father;
                //二维数组拷贝问题，不能简简单单地用clone方法
                int[][] array1=new int[Array.length][Array[0].length];
                for(int i=0;i<Array.length;i++){
                    for(int j=0;j<Array[0].length;j++){
                        array1[i][j]=Array[i][j];
                    }
                }
                this.array=array1.clone();
                
                //block的一些绑定
                this.numberOfblock=numberOfblock;
    
                //获取各种block的数量(谨防输入无解时的各种极端情况)
                int numberOfblock1=0;
                int numberOfblock2=0;
                int numberOfblock3=0;
    
    
                for(int i=0;i<numberOfblock;i++){
                    if(sizeOfBlock[i][0]==1){
                        numberOfblock1=numberOfblock1+1;
                    }
                    if(sizeOfBlock[i][0]==2){
                        numberOfblock2=numberOfblock2+1;
                    }
                    if(sizeOfBlock[i][0]==3){
                        numberOfblock3=numberOfblock3+1;
                    }
                }
                
                this.block1=initBlock1(numberOfblock1);
                this.block2=initBlock2(numberOfblock2);
                this.block3=initBlock3(numberOfblock3);
    
                int a=0;
                int b=0; 
                int c=0;
    
                for(int i=0;i<numberOfblock;i++){
                    if(sizeOfBlock[i][0]==1){
                        this.block1[a][0]=sizeOfBlock[i][1];
                        this.block1[a][1]=Array[coordinateOfTopInBlock(Array, sizeOfBlock[i][1])[0]][coordinateOfTopInBlock(Array, sizeOfBlock[i][1])[1]+1];
                        a=a+1;
                    }
                    if(sizeOfBlock[i][0]==2){
                        this.block2[b][0]=sizeOfBlock[i][1];
                        this.block2[b][1]=Array[coordinateOfTopInBlock(Array, sizeOfBlock[i][1])[0]+1][coordinateOfTopInBlock(Array, sizeOfBlock[i][1])[1]];
                        b=b+1;
                    }
                    if(sizeOfBlock[i][0]==3){
                        this.block3[c][0]=sizeOfBlock[i][1];
                        this.block3[c][1]=Array[coordinateOfTopInBlock(Array, sizeOfBlock[i][1])[0]][coordinateOfTopInBlock(Array, sizeOfBlock[i][1])[1]+1];
                        this.block3[c][2]=Array[coordinateOfTopInBlock(Array, sizeOfBlock[i][1])[0]+1][coordinateOfTopInBlock(Array, sizeOfBlock[i][1])[1]];
                        this.block3[c][3]=Array[coordinateOfTopInBlock(Array, sizeOfBlock[i][1])[0]+1][coordinateOfTopInBlock(Array, sizeOfBlock[i][1])[1]+1];
                        c=c+1;
                    }
                } 
            }
    
            //获取各种block中左上角元素在数组中位置的方法
            public static int[] coordinateOfTopInBlock(int[][] array,int number){
                int[] coordinate= new int[2];
                for(int i=0;i<array.length;i++){
                    for(int j=0;j<array[0].length;j++){
                        if(array[i][j]==number){
                            coordinate[0]=i;
                            coordinate[1]=j;
                            break;
                        }
                    }
                }
                return coordinate;
            }
    
    
    
            //拷贝构造函数，方便状态赋值
            public Node(Node a,Node Father){
                this.n=a.n;
                this.m=a.m;
    
                this.father=Father;
                
    
                //二维数组拷贝问题，不能简简单单地用clone方法
                int[][] array1=new int[a.array.length][a.array[0].length];
                for(int i=0;i<a.array.length;i++){
                    for(int j=0;j<a.array[0].length;j++){
                        array1[i][j]=a.array[i][j];
                    }
                }
                this.array=array1.clone();
    
                //block方面的拷贝
                this.numberOfblock=a.numberOfblock;
                //三种block均为二维数组，均需注意拷贝问题
    
                //block1的拷贝
                int[][] array2= new int[a.block1.length][a.block1[0].length];
                for(int i=0;i<a.block1.length;i++){
                    for(int j=0;j<a.block1[0].length;j++){
                        array2[i][j]=a.block1[i][j];
                    }
                }
                this.block1=array2;
                //block2的拷贝
                int[][] array3= new int[a.block2.length][a.block2[0].length];
                for(int i=0;i<a.block2.length;i++){
                    for(int j=0;j<a.block2[0].length;j++){
                        array3[i][j]=a.block2[i][j];
                    }
                }
                this.block2=array3;
    
                //block3的拷贝
                int[][] array4= new int[a.block3.length][a.block3[0].length];
                for(int i=0;i<a.block3.length;i++){
                    for(int j=0;j<a.block3[0].length;j++){
                        array4[i][j]=a.block3[i][j];
                    }
                }
                this.block3=array4;
            }
    
    
        }
    
        //将三种类型的字符串转为对应的标识
        public static int transform(String s){
            if(s.equals("1*2")){
                return 1;
            }
            else if(s.equals("2*1")){
                return 2;
            }
            else if(s.equals("2*2")){
                return 3;
            }
            else{
                return 0;//0代表不支持以外的block
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
            //将其转换为一维数组后再判断
            int[] arrayofdecide= new int[state.n*state.m];
            for(int i=0;i<state.n;i++){
                for(int j=0;j<state.m;j++){
                    arrayofdecide[state.m*i+j]=state.array[i][j];
                }
            }
            int numberOfZero=0;
            for(int i=0;i<state.n;i++){
                for(int j=0;j<state.m;j++){
                    if(state.array[i][j]==0){
                        numberOfZero=numberOfZero+1;
                    }
                }
            }
            //先判断零的位置
            int worryofzeros=0;
            for(int i=arrayofdecide.length-numberOfZero;i<arrayofdecide.length;i++){
                if(arrayofdecide[i]==0){
                }
                else{
                    worryofzeros=1;
                }
            }
    
            int mistake=0;
            if(worryofzeros!=0){
                return false;
            }else{
                for(int i=0;i<state.n;i++){
                    for(int j=0;j<state.m;j++){
                        if(state.array[i][j]==0){
                            continue;
                        }
                        if(state.array[i][j]!=j+1+state.m*i){
                            mistake=1;
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
            System.out.print("\n");
        }
    
         //判断上面能不能换及结果
         public static Node up(Node state,int x,int y){
            //state表示当前状态，[x,y]表示当前选定0的坐标
            if(x-1>=0&&state.array[x-1][y]!=0){
                Node next = new Node(state, state);
    
                //判断属于哪种类型的block
                //需要判断的点的坐标在array中的坐标是[x-1,y]
                boolean flag1=find(state.block1, state.array[x-1][y]);
                boolean flag2=find(state.block2, state.array[x-1][y]);
                boolean flag3=find(state.block3, state.array[x-1][y]);
                if(flag1==false&&flag2==false&&flag3==false){//属于1*1
                    //更新数值
                    next.array[x][y]=next.array[x-1][y];
                    next.array[x-1][y]=0;
                    return next;
                }else if(flag1==true&&flag2==false&&flag3==false){//属于1*2
                    int j=rangeofblock1(state, x-1, y);
                    if(j==1){
                        //下处需不需要判定y+1是否超出边界
                        if(state.array[x][y+1]==0){
                            //更新数值
                            next.array[x][y]=next.array[x-1][y];
                            next.array[x][y+1]=next.array[x-1][y+1];
                            next.array[x-1][y]=0;
                            next.array[x-1][y+1]=0;
                            return next;
                        }
                        else{
                            return state;
                        }
                    }
                    else{
                        //下处需不需要判定y-1是否超出边界
                        if(state.array[x][y-1]==0){
                            //更新数值
                            next.array[x][y]=next.array[x-1][y];
                            next.array[x][y-1]=next.array[x-1][y-1];
                            next.array[x-1][y]=0;
                            next.array[x-1][y-1]=0;
                            return next;
                        }
                        else{
                            return state;
                        }
                    }             
                }
                else if(flag1==false&&flag2==true&&flag3==false){//属于2*1
                    //更新数值
                    next.array[x][y]=next.array[x-1][y];
                    next.array[x-1][y]=next.array[x-2][y];
                    next.array[x-2][y]=0;
                    return next;
                }else{//属于2*2
                    int j=rangeofblock3(state, x-1, y);
                    if(j==1||j==2){
                        return state;
                    }
                    else if(j==3){
                        //下处需不需要判定y+1是否超出边界
                        if(state.array[x][y+1]==0){
                            //更新数值
                            next.array[x][y]=next.array[x-1][y];
                            next.array[x][y+1]=next.array[x-1][y+1];
                            //是否需要判断x-2超出边界
                            next.array[x-1][y]=next.array[x-2][y];
                            next.array[x-1][y+1]=next.array[x-2][y+1];
                            next.array[x-2][y]=0;
                            next.array[x-2][y+1]=0;
                            return next;
                        }
                        else{
                            return state;
                        }
                    }
                    else{
                        //下处需不需要判定y-1是否超出边界
                        if(state.array[x][y-1]==0){
                            //更新数值
                            next.array[x][y]=next.array[x-1][y];
                            next.array[x][y-1]=next.array[x-1][y-1];
                            //是否需要判断x-2超出边界
                            next.array[x-1][y]=next.array[x-2][y];
                            next.array[x-1][y-1]=next.array[x-2][y-1];
                            next.array[x-2][y]=0;
                            next.array[x-2][y-1]=0;
                            return next;
                        }
                        else{
                            return state;
                        }
    
                    }
    
    
                }
            }else{
                return state;
            }
    
        }
    
        //判断下面能不能换及结果
        public static Node down(Node state,int x,int y){
            //state表示当前状态，[x,y]表示当前选定0的坐标
            if(x+1<=state.n-1&&state.array[x+1][y]!=0){
                Node next = new Node(state, state);
    
                //判断属于哪种类型的block
                //需要判断的点的坐标在array中的坐标是[x+1,y]
                boolean flag1=find(state.block1, state.array[x+1][y]);
                boolean flag2=find(state.block2, state.array[x+1][y]);
                boolean flag3=find(state.block3, state.array[x+1][y]);
    
                if(flag1==false&&flag2==false&&flag3==false){//属于1*1
                    //更新数值
                    next.array[x][y]=next.array[x+1][y];
                    next.array[x+1][y]=0;
                    return next;
                }else if(flag1==true&&flag2==false&&flag3==false){//属于1*2
                    int j=rangeofblock1(state, x+1, y);
                    if(j==1){
                        //下处需不需要判定y+1是否超出边界
                        if(state.array[x][y+1]==0){
                            //更新数值
                            next.array[x][y]=next.array[x+1][y];
                            next.array[x][y+1]=next.array[x+1][y+1];
                            next.array[x+1][y]=0;
                            next.array[x+1][y+1]=0;
                            return next;
                        }
                        else{
                            return state;
                        }
                    }
                    else{
                        //下处需不需要判定y-1是否超出边界
                        if(state.array[x][y-1]==0){
                            //更新数值
                            next.array[x][y]=next.array[x+1][y];
                            next.array[x][y-1]=next.array[x+1][y-1];
                            next.array[x+1][y]=0;
                            next.array[x+1][y-1]=0;
                            return next;
                        }
                        else{
                            return state;
                        }
                    }
    
                }else if(flag1==false&&flag2==true&&flag3==false){//属于2*1
                    //更新数值
                    //是否需要判断x+2超出边界
                    next.array[x][y]=next.array[x+1][y];
                    next.array[x+1][y]=next.array[x+2][y];
                    next.array[x+2][y]=0;
                    return next;
                }else{//属于2*2
                    int j=rangeofblock3(state, x+1, y);
                    if(j==3||j==4){
                        return state;
                    }else if(j==1){
                        //下处需不需要判定y+1是否超出边界
                        if(state.array[x][y+1]==0){
                            //更新数值
                            next.array[x][y]=next.array[x+1][y];
                            next.array[x][y+1]=next.array[x+1][y+1];
                            //是否需要判断x+2超出边界
                            next.array[x+1][y]=next.array[x+2][y];
                            next.array[x+1][y+1]=next.array[x+2][y+1];
                            next.array[x+2][y]=0;
                            next.array[x+2][y+1]=0;
                            return next;
                        }
                        else{
                            return state;
                        }
    
                    }else{
                        //下处需不需要判定y-1是否超出边界
                        if(state.array[x][y-1]==0){
                            //更新数值
                            next.array[x][y]=next.array[x+1][y];
                            next.array[x][y-1]=next.array[x+1][y-1];
                            //是否需要判断x+2超出边界
                            next.array[x+1][y]=next.array[x+2][y];
                            next.array[x+1][y-1]=next.array[x+2][y-1];
                            next.array[x+2][y]=0;
                            next.array[x+2][y-1]=0;
                            return next;
                        }
                        else{
                            return state;
                        }
                    }
                }
    
            }
            else{
                return state;
            }
    
        }
    
        //判断左面能不能换及结果
        public static Node left(Node state,int x,int y){
            //state表示当前状态，[x,y]表示当前选定0的坐标
            if(y-1>=0&&state.array[x][y-1]!=0){
                Node next = new Node(state, state);
    
                //判断属于哪种类型的block
                //需要判断的点的坐标在array中的坐标是[x,y-1]
                boolean flag1=find(state.block1, state.array[x][y-1]);
                boolean flag2=find(state.block2, state.array[x][y-1]);
                boolean flag3=find(state.block3, state.array[x][y-1]);
                if(flag1==false&&flag2==false&&flag3==false){//属于1*1
                     //更新数值
                    next.array[x][y]=next.array[x][y-1];
                    next.array[x][y-1]=0;
                    return next;
                }else if(flag1==true&&flag2==false&&flag3==false){//属于1*2
                    //更新数值
                    //是否需要判断y-2超出边界
                    next.array[x][y]=next.array[x][y-1];
                    next.array[x][y-1]=next.array[x][y-2];
                    next.array[x][y-2]=0;
                    return next;
                }else if(flag1==false&&flag2==true&&flag3==false){//属于2*1
                    int j=rangeofblock2(state, x, y-1);
                    if(j==1){
                        //下处需不需要判定x+1是否超出边界
                        if(state.array[x+1][y]==0){
                            //更新数值
                            next.array[x][y]=next.array[x][y-1];
                            next.array[x+1][y]=next.array[x+1][y-1];
                            next.array[x][y-1]=0;
                            next.array[x+1][y-1]=0;
                            return next;
                        }else{
                            return state;
                        }
    
                    }else{
                        //下处需不需要判定x-1是否超出边界
                        if(state.array[x-1][y]==0){
                            //更新数值
                            next.array[x][y]=next.array[x][y-1];
                            next.array[x-1][y]=next.array[x-1][y-1];
                            next.array[x][y-1]=0;
                            next.array[x-1][y-1]=0;
                            return next;
                        }
                        else{
                            return state;
                        }
    
                    }
                }
                else{//属于2*2
                    int j=rangeofblock3(state, x, y-1);
                    if(j==1||j==3){
                        return state;
                    }else if(j==2){
                        //下处需不需要判定x+1是否超出边界
                        if(state.array[x+1][y]==0){
                            //更新数值
                            next.array[x][y]=next.array[x][y-1];
                            next.array[x+1][y]=next.array[x+1][y-1];
                            //是否需要判断y-2超出边界
                            next.array[x][y-1]=next.array[x][y-2];
                            next.array[x+1][y-1]=next.array[x+1][y-2];
                            next.array[x][y-2]=0;
                            next.array[x+1][y-2]=0;
                            return next;
                        }
                        else{
                            return state;
                        }
                    }else{
                        //下处需不需要判定x-1是否超出边界
                        if(state.array[x-1][y]==0){
                            //更新数值
                            next.array[x][y]=next.array[x][y-1];
                            next.array[x-1][y]=next.array[x-1][y-1];
                            //是否需要判断x-2超出边界
                            next.array[x][y-1]=next.array[x][y-2];
                            next.array[x-1][y-1]=next.array[x-1][y-2];
                            next.array[x-1][y-2]=0;
                            next.array[x][y-2]=0;
                            return next;
                        }
                        else{
                            return state;
                        }
                    }
    
                }
    
    
            }else{
                return state;
            }
        }
        
        //判断右面能不能换及结果
        public static Node right(Node state,int x,int y){
            //state表示当前状态，[x,y]表示当前选定0的坐标
            if(y+1<=state.m-1){
                Node next = new Node(state, state);
    
            //判断属于哪种类型的block
            //需要判断的点的坐标在array中的坐标是[x,y+1]
            boolean flag1=find(state.block1, state.array[x][y+1]);
            boolean flag2=find(state.block2, state.array[x][y+1]);
            boolean flag3=find(state.block3, state.array[x][y+1]);
    
            if(flag1==false&&flag2==false&&flag3==false){//属于1*1
                 //更新数值
                 next.array[x][y]=next.array[x][y+1];
                 next.array[x][y+1]=0;
                 return next;
            }else if(flag1==true&&flag2==false&&flag3==false){//属于1*2
                //更新数值
                //是否需要判断y+2超出边界
                next.array[x][y]=next.array[x][y+1];
                next.array[x][y+1]=next.array[x][y+2];
                next.array[x][y+2]=0;
                return next;
    
            }else if(flag1==false&&flag2==true&&flag3==false){//属于2*1
                int j=rangeofblock2(state, x, y+1);
                if(j==1){
                    //下处需不需要判定x+1是否超出边界
                    if(state.array[x+1][y]==0){
                        //更新数值
                        next.array[x][y]=next.array[x][y+1];
                        next.array[x+1][y]=next.array[x+1][y+1];
                        next.array[x][y+1]=0;
                        next.array[x+1][y+1]=0;
                        return next;
                    }else{
                        return state;
                    }
    
                }else{
                    //下处需不需要判定x-1是否超出边界
                    if(state.array[x-1][y]==0){
                        //更新数值
                        next.array[x][y]=next.array[x][y+1];
                        next.array[x-1][y]=next.array[x-1][y+1];
                        next.array[x][y+1]=0;
                        next.array[x-1][y+1]=0;
                        return next;
                    }
                    else{
                        return state;
                    }
    
                }
    
            }else{//属于2*2
                int j=rangeofblock3(state, x, y+1);
                if(j==2||j==4){
                    return state;
                }else if(j==1){
                    //下处需不需要判定x+1是否超出边界
                    if(state.array[x+1][y]==0){
                        //更新数值
                        next.array[x][y]=next.array[x][y+1];
                        next.array[x+1][y]=next.array[x+1][y+1];
                        //是否需要判断y-2超出边界
                        next.array[x][y+1]=next.array[x][y+2];
                        next.array[x+1][y+1]=next.array[x+1][y+2];
                        next.array[x][y+2]=0;
                        next.array[x+1][y+2]=0;
                        return next;
                    }
                    else{
                        return state;
                    }
    
                }else{
                    //下处需不需要判定x-1是否超出边界
                    if(state.array[x-1][y]==0){
                        //更新数值
                        next.array[x][y]=next.array[x][y+1];
                        next.array[x-1][y]=next.array[x-1][y+1];
                        //是否需要判断y+2超出边界
                        next.array[x][y+1]=next.array[x][y+2];
                        next.array[x-1][y+1]=next.array[x-1][y+2];
                        next.array[x][y+2]=0;
                        next.array[x-1][y+2]=0;
                        return next;
                    }
                    else{
                        return state;
                    }
    
    
                }
    
            }
    
    
            }
            else{
                return state;
            }
            
        }
    
        //判断一个数是否属于二维数组中
        public static boolean find(int[][]array,int target){
            boolean flag = false;
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j <array[i].length ; j++) {
                    if (target == array[i][j]) { //判断是否存在该整数
                        flag = true;  //存在
                        return flag;  
                    }
                }
            }
            return flag;
        }
    
        //判断属于1*2block中的第一个元素还是第二个元素
        //如果是第一个，就返回1，如果是第二个，就返回2
        public static int rangeofblock1(Node state,int x,int y){
            //state为目前需要判断的状态，[x,y]为需要判断的点在state.array中的坐标
            int column=0;
            for(int i=0;i<state.block1.length;i++){
                for(int j=0;j<state.block1[0].length;j++){
                    if(state.block1[i][j]==state.array[x][y]){
                        column=j;
                    }
                }
            }
            if(column==0){
                return 1;
            }else{
                return 2;
            }
        }
    
        //判断属于2*1 block中的第一个元素还是第二个元素
        //如果是第一个，就返回1，如果是第二个，就返回2
        public static int rangeofblock2(Node state,int x,int y){
            //state为目前需要判断的状态，[x,y]为需要判断的点在state.array中的坐标
            int column=0;
            for(int i=0;i<state.block2.length;i++){
                for(int j=0;j<state.block2[0].length;j++){
                    if(state.block2[i][j]==state.array[x][y]){
                        column=j;
                    }
                }
            }
            if(column==0){
                return 1;
            }else{
                return 2;
            }
        }
    
        //判断属于2*2 block中的第几个元素
        //如果是第一个，就返回1，如果是第二个，就返回2，以此类推
        public static int rangeofblock3(Node state,int x,int y){
            int column=0;
            for(int i=0;i<state.block3.length;i++){
                for(int j=0;j<state.block3[0].length;j++){
                    if(state.block3[i][j]==state.array[x][y]){
                        column=j;
                    }
                }
            }
            if(column==0){
                return 1;
            }else if(column==1){
                return 2;
            }else if(column==2){
                return 3;
            }else{
                return 4;
            }
        }
    
    
        public static void main(String[] args) {
            try (Scanner input = new Scanner(System.in)) {
                System.out.println("输入行数：");
                int N = input.nextInt();
    
                System.out.println("输入列数：");
                int M = input.nextInt();
    
                System.out.println("输入数组：");
                int[][] initArray = new int[N][M];
                for(int i=0;i<N;i++){
                    for(int j=0;j<M;j++){
                        initArray[i][j]=input.nextInt();
                    }
                }
                System.out.println("输入block的数量:");
                int NumberOfBlock = input.nextInt();
    
                //sizeofblock为numofblock*2的二维数组，第一列代表block的种类，第二列代表block中左上角的数字，
                int[][] SizeOfBlock = new int[NumberOfBlock][2];
                System.out.println("输入数值和类型:");
                for(int i=0;i<NumberOfBlock;i++){
                    SizeOfBlock[i][1]=input.nextInt();
                    SizeOfBlock[i][0]=transform(input.next());                   
                }
    
                //初始状态
                Node initState= new Node(initArray, N, M, NumberOfBlock, SizeOfBlock, null);
    
                //可以考虑用哈希表来实现
                ArrayList<String> ss=new ArrayList<String>();//判断是否重复
                Queue<Node> queue = new Queue<Node>();//要搜索的状态
    
                //将初始状态放入
                ss.add(arraytoString(initState));
                queue.enqueue(initState);
    
                boolean flag =false;//标识有无解
                Node ans=null;
    
                while(!queue.isEmpty()){
                    Node currentState = queue.dequeue();
    
                    //判断此状态是否是解
                    if(isComplete(currentState)){
                        flag=true;
                        ans=currentState;
                        break;
                    }
    
                    int n = currentState.n;
                    int m = currentState.m;
    
                    for(int i=0;i<n;i++){
                        for(int j=0;j<m;j++){
                            if(currentState.array[i][j]==0){
                                //此时零点的坐标为[i,j]
    
                                //与上面能否交换后的结果
                                Node next1= up(currentState, i, j);
                                if(ss.contains(arraytoString(next1))){
    
                                }
                                else{
                                    ss.add(arraytoString(next1));
                                    queue.enqueue(next1);
                                }
                                
                                //与下面能否交换后的结果
                                Node next2= down(currentState, i, j);
                                if(ss.contains(arraytoString(next2))){
    
                                }
                                else{
                                    ss.add(arraytoString(next2));
                                    queue.enqueue(next2);
                                }
    
                                //与左面能否交换后的结果
                                Node next3= down(currentState, i, j);
                                if(ss.contains(arraytoString(next3))){
    
                                }
                                else{
                                    ss.add(arraytoString(next3));
                                    queue.enqueue(next3);
                                }
    
    
                                //与右面能否交换后的结果
                                Node next4= down(currentState, i, j);
                                if(ss.contains(arraytoString(next4))){
    
                                }
                                else{
                                    ss.add(arraytoString(next4));
                                    queue.enqueue(next4);
                                }
                            }
                        }
                    }
    
    
    
                }
                if(flag){
                    Node t = ans;
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
