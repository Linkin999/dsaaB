import java.util.Arrays;
import java.util.Random;



import edu.princeton.cs.algs4.Stopwatch;

public class QuickSort {

    private static void exchange( int[] array, int i, int j ) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static void insertion( int[] array, int left, int right ) {
        for( int i = left+1; i <= right; ++ i ) {
            int value = array[i];
            int j;
            for( j = i; j > left && array[j-1] > value; -- j )
                array[j] = array[j-1];
            array[j] = value;
        }
    }

    public static void sort( int[] array, int left, int right, Random rand ) {
        //防止stackoverflow出现
        if( right - left < 8 ) {
            //insertion(array, left, right);这样做的话insertion的时间复杂度为O（N）
            return;
        }

        //select pivot
        //int pivot = array[right];
        int pivot=array[rand.nextInt(right-left+1)+left];//防止出现极端情况

        //do partition
        /*int i1 = left-1;
        int i2 = right+1;
        while( i2-i1 > 1 ) {
            int value = array[i1+1];
            if( value < pivot )
                i1++;
            else {
                exchange(array, i1+1, i2-1);
                i2--;
            }
        }*/

        //improvment
        int i1 = left-1;
        int i2 = left-1;
        int i3 = right+1;
        while(i3-i2>1){
            int value = array[i2+1];
            if(value<pivot){
                exchange(array, i1+1, i2+1);
                i1++;
                i2++;
            }else if(value>pivot){
                exchange(array, i2+1, i3-1);
                i3--;
            }else
            i2++;
        }


        sort( array, left, i1, rand );
        sort( array, i2, right, rand );
    }

    public static void sort( int[] array ) {
        sort(array, 0, array.length-1,new Random());
        insertion(array, 0,array.length-1);//这样做的话insertion的时间复杂度为O（N）， quicksort后每个子数组的每位元素最多向前移动8步，所以时间复杂度为8*N
    }
/*
    public static void main( String[] args ) {
        int[] array = new int[] { 6, 5, 4, 3, 2, 1, 10, 9, 8, 7 };
        sort(array);
        System.out.println(Arrays.toString(array));
    }
*/
    public static double test( int[] array, Random rand ) {
        int[] array2 = array.clone();
        Arrays.sort(array);
        Stopwatch watch = new Stopwatch();
        sort(array2);
        if( !Arrays.equals(array, array2) ) {
            System.err.println("Error");
            return 0;
        };
        return watch.elapsedTime();
    }

    public static void main( String[] args ) {
        Random rand = new Random();

        int[] array = new int[250];
        for( int i = 0; i < array.length; i ++ )
            array[i] = rand.nextInt();

        double prev = test(array, rand);
        for (int n = 250; n > 0; n *= 2) {
            array = new int[n];
            for( int i = 0; i < array.length; i ++ )
                array[i] = rand.nextInt();

            double time = test(array, rand);
            System.out.printf("%7d %7.1f %5.1f\n", n, time, time/prev);
            prev = time;
        }
    }
}