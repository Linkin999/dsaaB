package Problem2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Paths {
    public static int findPaths(Graph2 G,int num) {
        int pathNumber = 0;
        // TODO: implement this method and modify the pathNumber
        int s =1;
        DepthFirstPaths2 search = new DepthFirstPaths2(G, s);
        for(int v=2;v<=G.V();v++){
            if(search.hasPathTo(v)){
                if(search.Leafnode[v]==true){
                    if(search.costOfPathTo(v)==num){
                        pathNumber=pathNumber+1;
                    }
                }
            }
        }

        return pathNumber;
    }
    

    public static void main(String[] args) throws FileNotFoundException {
        // 文件中第一个数字是数组长度，接下来N个数字才是数组元素。
        // 请根据实际情况更改文件路径
        File input = new File("D:/Study in SUSTech/First semester of junior year/dsaaB/lab/assignment3/test_data/Q2/B2.in");
        if (!input.exists()) {
            System.out.println("File isn't exist");
            System.exit(0);
        }
        Scanner in = new Scanner(input);
        int n = in.nextInt(); //the number of tree nodes
        int num = in.nextInt();// the target number
        //TODO: Store the edges in the tree
        //Your code here

        Graph2 G = new Graph2(n);
        In in2 =new In(input);
        Graph2 G1 = new Graph2(in2);

        /*int s=1;
        DepthFirstPaths2 search = new DepthFirstPaths2(G1, s);
        for(int v=2;v<=G.V();v++){
            System.out.println(search.Leafnode[v]);
            StdOut.print(s+" to "+v+":");
            if(search.hasPathTo(v)){
                StdOut.println(search.costOfPathTo(v));
                for(int x:search.pathTo(v)){
                    if(x==s){
                        StdOut.print(x);
                    }
                    else{
                        StdOut.print("-"+x);
                    }
                }
            }
            StdOut.println();
        }*/

        //TODO: Pass your parameter
        int pathNmuber = findPaths(G1,num);
        //System.out.println(pathNmuber);

        File output = new File("D:/Study in SUSTech/First semester of junior year/dsaaB/lab/assignment3/test_data/Q2/B2.out");
        in = new Scanner(output);
        boolean flag = in.nextInt() == pathNmuber;
        System.out.println(flag);
    }
}

