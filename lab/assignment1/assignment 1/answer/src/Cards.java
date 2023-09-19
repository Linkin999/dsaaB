import edu.princeton.cs.algs4.In;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Cards {
    /**
     * Using the order the cards are destroyed, calculate the initial order of the cards.
     * @param destroyOrder the order the cards are destroyed.
     * @return the initial order
     */
    public static int[] calculateInitCards( int[] destroyOrder ) {
        // write your code here.
        QueueOfInitial <Integer> initialorder = new QueueOfInitial<Integer>();
        for(int i=destroyOrder.length-1;i>-1;i--){
            if (initialorder.size()>1){
                int trans= initialorder.dequeue();
                initialorder.enqueue(trans);
            }
            initialorder.enqueue(destroyOrder[i]);
        }
        int[] initial= new int[destroyOrder.length];

        for (int i=destroyOrder.length-1;i>-1;i--){
            initial[i] = initialorder.dequeue();
        }
        return initial;
    }

    public static int[] calculateDestroyCards( int[] initOrder ) {
        // write your code here.

        QueueOfInitial <Integer> initialorder = new QueueOfInitial<Integer>();
        for (int i =0;i<initOrder.length;i++){
            initialorder.enqueue(initOrder[i]);
        }
        int[] destroyorder=new int[initOrder.length];
        for (int i =0;i<destroyorder.length;i++){
            if (initialorder.size()>2){
                int de = initialorder.dequeue();
                destroyorder[i]=de;
                int de1 = initialorder.dequeue();
                initialorder.enqueue(de1);
            }
            else {
                destroyorder[i]=initialorder.dequeue();
            }
        }
        return destroyorder;
    }


    public static void main( String[] args ) {

        for(int i =1;i<=2;++i){
            try {
                In fin1 =new In("D:\\Study in SUSTech\\First semester of junior year\\dsaaB\\lab\\assignment1\\assignment 1\\assignment 1\\data\\"+i+".in");
                int[] arr1 = fin1.readAllInts();
                fin1.close();

                int[] destroyOrder = new int[arr1.length-1];
                for( int j = 0; j < destroyOrder.length; j ++ ){
                    destroyOrder[j] = arr1[j+1];
                }
                int[] initOrder = calculateInitCards(destroyOrder);

                In fin2 = new In("D:\\Study in SUSTech\\First semester of junior year\\dsaaB\\lab\\assignment1\\assignment 1\\assignment 1\\data\\"+i+".out");
                int[] arr2= fin2.readAllInts();

                /*System.out.println("The initorder is:");
                for( int j : initOrder ){
                    System.out.println(j);
                }*/

                /*System.out.println("The out is:");
                for( int j : arr2 ){
                    System.out.println(j);
                }*/

                if (Arrays.equals(initOrder, arr2)){
                    System.out.println("The method is correct\n");
                }
                else {
                    System.out.println("The method isn't correct\n");
                }

            }catch (IllegalArgumentException e){
                e.printStackTrace();
            }
        }


        /**
         * 以下代码为proof代码
         */

        for(int i =1;i<=8;++i){
            try {
                In fin3= new In("D:\\Study in SUSTech\\First semester of junior year\\dsaaB\\lab\\assignment1\\assignment 1\\data\\"+i+".in");
                int[] destroyOrder= fin3.readAllInts();
                fin3.close();

                int[] initOrder = calculateInitCards(destroyOrder);
                /*for (int j : initOrder) {
                    System.out.print(j+" ");
                }
                System.out.print("\n");*/

                int[] destroy = calculateDestroyCards(initOrder);
                new File("D:\\Study in SUSTech\\First semester of junior year\\dsaaB\\lab\\assignment1\\assignment 1\\data\\").mkdirs();
                try ( PrintWriter fout = new PrintWriter("D:\\Study in SUSTech\\First semester of junior year\\dsaaB\\lab\\assignment1\\assignment 1\\data\\"+i+".out") ) {
                    for( int j = 0; j < i*1000 && j < destroy.length; ++ j )
                        fout.println(" " + destroy[j]);
                } catch ( IOException e ) {
                    e.printStackTrace();
                }

                if (Arrays.equals(destroyOrder,destroy)){
                    System.out.println("The method is correct");
                }
                else {
                    System.out.println("The method isn't correct");
                }


            }catch (IllegalArgumentException e){
                e.printStackTrace();
            }
        }
    }
}