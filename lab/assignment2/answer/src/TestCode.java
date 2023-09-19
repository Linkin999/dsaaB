import java.util.Arrays;
import java.util.Random;
import edu.princeton.cs.algs4.Quick;

public class TestCode {

    //生成[i,j]之间的随机整数
    public static int getRandomNumberInRange(int i, int j) {
        if (i >= j) {
			throw new IllegalArgumentException("max must be greater than min");
		}
 
		Random r = new Random();
		return r.nextInt((j - i) + 1) + i;
    }

    //随机生成输入的数组，N表示数组的长度
    public static int[] inputArray(int N){

        int[] inputarray = new int[N];
        //随机生成输入的数组
        for(int i =0;i<N;i++){
            inputarray[i]=getRandomNumberInRange(1, (int) Math.pow(10, 9));
        }
        return inputarray;
    }
    //插入排序
    public static int[] insertion( int[] array, int left, int right ) {
        for( int i = left+1; i <= right; ++ i ) {
            int value = array[i];
            int j;
            for( j = i; j > left && array[j-1] > value; -- j )
                array[j] = array[j-1];
            array[j] = value;
        }
        return array;
    }

    //使用插入排序将数组排序后找median
    public static int[] medianofInsertion(int[] array){
        int[] medianarray=new int[array.length];
        for(int i=0;i<array.length;i++){
            int[] array2 = insertion(array, 0, i);
            medianarray[i]=array2[i/2];
        }
        return medianarray;
    }

    public static void main(String[] args) {

        int[] array = new int[]{2,5,1,4,7};
        int[] array3=array.clone();
        int[] medianarray=new int[array.length];
        for(int i=0;i<array.length;i++){
            int[] array2 = insertion(array, 0, i);
            
            medianarray[i]=array2[i/2];
        }
        for(int i=0;i<medianarray.length;i++){
            
            System.out.println(medianarray[i]);
        }
        
        int[] testmedian= medianofInsertion(array3);
        for(int i=0;i<testmedian.length;i++){
            
            System.out.println(testmedian[i]);
        }
        
    }   
}
