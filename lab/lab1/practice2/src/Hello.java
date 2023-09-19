import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4. StdArrayIO;

public class Hello {
    public static void main(String[] args) {
        int[][] array= StdArrayIO.readInt2D();
        for (int[] arr : array){
            for (int i : arr){
                StdOut.print(""+i);
            }
            StdOut.println();
        }
    }
}