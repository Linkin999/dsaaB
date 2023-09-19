import edu.princeton.cs.algs4.ThreeSum;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class Simple3Sum {

    public static void main( String[] args ) {
        int[] array = new int[]{30, -40, -20, -10, 40, 0, 10, 5};

        Stopwatch watch = new Stopwatch();
        int count = ThreeSum.count(array);
        double elapsedTime= watch.elapsedTime();

        if( count <= 1 )
            StdOut.printf("There is %d triple in the array.\n Time is %f\n", count,elapsedTime);
        else
            StdOut.printf("There are %d triples in the array.\n Time is %f\n", count,elapsedTime);
    }
}
