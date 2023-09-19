import java.util.Arrays;
import java.util.Random;

public class NaturalMergeSort {

    private static int[] findAllInterval( int[] array, int[] aux ) {
        int count = 0;
        for( int i = 0; i < array.length-1; i ++ )
            if( array[i] > array[i+1] )
                aux[count++] = i;
        aux[count++] = array.length-1;
        int[] result = new int[count];
        System.arraycopy(aux, 0, result, 0, count);
        return result;
    }

    public static void sort( int[] array ) {
        int[] aux = new int[array.length];
        int[] intervals = findAllInterval(array, aux);
        int N = intervals.length;

        for( int len = 1; len < N; len *= 2 ) {
            int left = 0;
            for( int mid = len; mid < N; mid += 2*len ) {
                int right = Math.min(mid+len, N);
                merge(array, left, intervals[mid-1], intervals[right-1], aux);
                left = intervals[right-1]+1;
            }
        }
    }

    private static void merge( int[] array, int low, int mid, int high, int [] aux ) {
        System.arraycopy(array, low, aux, low, high-low+1);
        int i, j1, j2;
        for( i = low, j1=low, j2 = mid+1; j1<=mid && j2 <= high; ) {
            if( aux[j1] <= aux[j2] )
                array[i++] = aux[j1++];
            else
                array[i++] = aux[j2++];
        }
        while( j1 <= mid )
            array[i++] = aux[j1++];
        while( j2 <= high )
            array[i++] = aux[j2++];
    }

    private static boolean test( int[] array ) {
        int[] array2 = array.clone();
        sort(array);
        Arrays.sort(array2);
        return Arrays.equals(array, array2);
    }

    public static void main( String[] args ) {
        int[] array = new int[20000];
        Random rand = new Random();
        
        for( int ite = 0; ite < 50; ite ++ ) {
            for( int i = 0; i < array.length; i ++ )
                array[i] = rand.nextInt();
            System.out.println(test(array));
        }
    }
}