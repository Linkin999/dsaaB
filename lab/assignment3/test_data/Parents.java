package ass3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Parents {
    public static int[] findParents(int n, Node[] nodes) {
        int[] parents = new int[n];
        // TODO: implement this method
        for (int i = 0; i < parents.length; i++) {
            if (nodes[i+1].father!=null) {
                parents[i] = nodes[i+1].father.id;
            }else{
                parents[i] = -1;
            }
        }

        return parents;
    }

    private boolean[][] adjacency;

    public Parents (int number){
        adjacency = new boolean[number][number];
    }

    public void addEdge (int v, int w){
        adjacency[w][v] = true;
    }


    public static void main(String[] args) throws FileNotFoundException {
        // 文件中第一个数字是数组长度，接下来N个数字才是数组元素。
        // 请根据实际情况更改文件路径
        File input = new File("resources/test_data/Q1/A2.in");
        if (!input.exists()) {
            System.out.println("File isn't exist");
            System.exit(0);
        }
        Scanner in = new Scanner(input);
        int n = in.nextInt();  //the number of tree nodes
        //TODO: Store the edges in the tree
        //Your code here
        //用了二维数组
//        Parents p = new Parents(n+1);
//        for (int i = 0; i < n-1; i++) {
//            p.addEdge(in.nextInt(),in.nextInt());
//        }
        //用nodes
        Node[] nodes = new Node[n+1];
        for (int i=0; i<=n;i++){
            nodes[i] = new Node(i);
        }

        for(int i=0;i< n - 1;i++){
            int start = in.nextInt();
            int end = in.nextInt();
            nodes[start].sons.add(nodes[end]);
            nodes[end].father = nodes[start];
        }


        //TODO: Pass your parameter
        int[] parents = findParents(n, nodes);

        File output = new File("resources/test_data/Q1/A2.out");
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

class Node{
    ArrayList<Node> sons;
    Node father;
    int id;
    public Node(int id){
        this.id = id;
        sons = new ArrayList<>();
    }
}
