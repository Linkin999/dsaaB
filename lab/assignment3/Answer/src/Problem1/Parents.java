package Problem1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import Problem1.Graph;


public class Parents {
    
 

    public static int[] findParents(int n,Graph G) {
        int[] parents = new int[n];
        parents[0]=-1;

        int s =1;//起点
        DepthFirstPaths search = new DepthFirstPaths(G, s);
        for(int v=2;v<=G.V();v++){
            if(search.hasPathTo(v)){
                Queue queue = (Queue) search.pathTo(v);
                int j =(int) queue.dequeue();
                parents[v-1]=(int) queue.dequeue();
            }
        }

        return parents;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // 文件中第一个数字是数组长度，接下来N个数字才是数组元素。
        // 请根据实际情况更改文件路径
        File input = new File("D:/Study in SUSTech/First semester of junior year/dsaaB/lab/assignment3/test_data/Q1/A2.in");
        if (!input.exists()) {
            System.out.println("File isn't exist");
            System.exit(0);
        }
        Scanner in = new Scanner(input);
        int n = in.nextInt();  //the number of tree nodes

        Graph G = new Graph(n);
        In in2 =new In(input);
        Graph G1 = new Graph(in2);
        
        int[] parents = findParents(n, G1);
        

        
        File output = new File("D:/Study in SUSTech/First semester of junior year/dsaaB/lab/assignment3/test_data/Q1/A2.out");
        in = new Scanner(output);
        boolean flag = true;

        for (int i = 0; i < n; i++) {
            if (in.nextInt() != parents[i]) {
                flag = false;
                break;
            }
        }
        System.out.println(flag);

        
    }
}
