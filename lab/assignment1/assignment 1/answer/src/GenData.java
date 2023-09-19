import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.File;
import java.util.Scanner;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 细节：这里生成的数据并没有表示数组长度的元素,且生成的是destroyOrder
 */

public class GenData {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int N= 100000;
        int[] arr = new int[N];
        for( int i = 0, num =1; i < arr.length; ++i, num++ )
            arr[i] = num;
        StdRandom.shuffle(arr);

        new File("D:\\Study in SUSTech\\First semester of junior year\\dsaaB\\lab\\assignment1\\assignment 1\\data\\").mkdirs();

        for( int i = 1; i <= 8; ++ i ) {
            try ( PrintWriter fout = new PrintWriter("D:\\Study in SUSTech\\First semester of junior year\\dsaaB\\lab\\assignment1\\assignment 1\\data\\"+i+".in") ) {
                for( int j = 0; j < i*1000 && j < arr.length; ++ j )
                    fout.println(" " + arr[j]);
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        }
    }
}
