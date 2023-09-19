import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        //standard input
        try (Scanner input = new Scanner(System.in)) {
            int N = input.nextInt();
            int M = input.nextInt();
            int[][] initArray = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    initArray[i][j] = input.nextInt();
                }
            }
            int numberOfblock = input.nextInt();
            int[][] allOfBlock = new int[numberOfblock][2];
            if (numberOfblock != 0) {
                for (int i = 0; i < numberOfblock; i++) {
                    allOfBlock[i][0] = input.nextInt();
                    //因为表示block种类的输入是字符类型，将各种种类转换为整数来分类，
                    allOfBlock[i][1] = Node.transform(input.next());
                }
            } else {
                allOfBlock = null;
            }
            Node initState = new Node(initArray, null, numberOfblock, allOfBlock);
            HuaRongDao hrd = new HuaRongDao(Node.answer(initState));
        }
    }
}
