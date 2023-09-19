import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import javax.xml.transform.Templates;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stopwatch;

public class Median {
    public static int[] findMedians(int[] array){
        // TODO: implement this method
        int[] medianArray = new int[array.length];

        MaxPQ <Integer> max = new MaxPQ<Integer>();  
        MinPQ <Integer> min = new MinPQ<Integer>();

        for(int i=0 ; i<array.length;i++){
            if(max.isEmpty()){
                max.insert(array[i]);
            }
            else{

                if(max.size()==min.size()){
                    if(array[i]<max.max()){
                        max.insert(array[i]);
                    }
                    else{
                        min.insert(array[i]);
                    }
                }

                else if(max.size()<min.size()){
                    if(array[i]<=min.min()){
                        max.insert(array[i]);
                    }
                    else{
                        int tmp = min.delMin();
                        max.insert(tmp);
                        min.insert(array[i]);                       
                    }
                }
                else{
                    if(array[i]>=max.max()){
                        min.insert(array[i]);
                    }
                    else{
                        int tmp = max.delMax();
                        min.insert(tmp);
                        max.insert(array[i]);
                    }
                }

            }

            if(max.size()==min.size()){
                medianArray[i]=max.max();
            }
            else if(max.size()<min.size()){
                medianArray[i]=min.min();
            }
            else{
                medianArray[i]=max.max();
            }
        }


        return medianArray;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // 文件中第一个数字是数组长度，接下来N个数字才是数组元素。
        // 请根据实际情况更改line 14, line 27的文件路径


        //以下for循环是测试test data文件夹中数据运行的结果
        for(int i=1;i<=10;++i){
            try{
                In fin1=new In("D:\\Study in SUSTech\\First semester of junior year\\dsaaB\\lab\\assignment2\\testing data\\"+i+".in");
                int[] initinput = fin1.readAllInts();
                fin1.close();
                int[] input = new int[initinput[0]];
                for(int j=1;j<initinput.length;j++){
                    input[j-1]=initinput[j];
                }

                int[] output = findMedians(input);

                In fin2=new In("D:\\Study in SUSTech\\First semester of junior year\\dsaaB\\lab\\assignment2\\testing data\\"+i+".out");
                int[] sampleOutput = fin2.readAllInts();
                fin2.close();

                if (Arrays.equals(output,sampleOutput)){
                    System.out.println("The method is correct");
                }
                else {
                    System.out.println("The method isn't correct");
                }
            } catch(IllegalArgumentException e){
                e.printStackTrace();
            }
        }


        /*File input = new File("src/testing data/sample input.txt");
        if(!input.exists()) {
            System.out.println("File isn't exist");
            System.exit(0);
        }
        Scanner in = new Scanner(input);
        int N  = in.nextInt();
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = in.nextInt();
        }
        int[] result = findMedians(array);

        File output = new File("src/testing data/sample output.txt");
        in = new Scanner(output);
        boolean flag = true;
        for (int i = 0; i < N; i++) {
            if(in.nextInt()!=result[i]) {
                flag = false;
                break;
            }
        }
        System.out.println(flag);*/

        // 以下四行代码为测试文本中给的样例
        int[] array = new int[]{2,5,1,4,7};
        int[] array2=findMedians(array);
        for(int i=0;i<array2.length;i++){
            System.out.println(array2[i]);
        }

        
        //以下为验证自己方法正确性的代码
        int N = TestCode.getRandomNumberInRange(1, (int) (5*Math.pow(10, 5)));//随机生成数组的长度
        int[] inputarray= TestCode.inputArray(N);//随机生成输入的数组
        int[] inputarray2= inputarray.clone();//将随机生成输入的数组克隆一份

        int[] methodmedian = findMedians(inputarray);//用填写的方法测试出来的median数组

        int[] testmedian = TestCode.medianofInsertion(inputarray2);
    

        if (Arrays.equals(testmedian,methodmedian)){
            System.out.println("The result of test is correct");
        }
        else {
            System.out.println("The result of test isn't correct");
        }
        

       //以下为验证时间复杂度的代码
       int[] inputarray3= TestCode.inputArray(250);
       Stopwatch watch1 = new Stopwatch();
       int[] methodmedian2 = findMedians(inputarray3);
       double prev = watch1.elapsedTime();

       for(int n =250;n>0;n*=2){
            int[] inputarray4= TestCode.inputArray(n);

            Stopwatch watch2 = new Stopwatch();
            int[] methodmedian3 = findMedians(inputarray4);
            System.out.printf("%7d %7.1f %5.1f\n", n, watch2.elapsedTime(),watch2.elapsedTime()/prev);
            prev = watch2.elapsedTime();

       }

       //以下为测试极端情况下的时间复杂度

       //测试数组全为相同的数的情况下
       int value = TestCode.getRandomNumberInRange(1, (int) Math.pow(10, 9));
       int[] inputarray5 = new int[250];
       for(int i=0;i<inputarray5.length;i++){
            inputarray5[i]=value;
       }
       Stopwatch watch3 = new Stopwatch();
       int[] methodmedian4 = findMedians(inputarray5);
       double prev2 = watch3.elapsedTime();

       for(int n =250;n>0;n*=2){
        int[] inputarray6= new int[n];
        for(int i=0;i<inputarray6.length;i++){
            inputarray6[i]=value;
        }

        Stopwatch watch4 = new Stopwatch();
        int[] methodmedian5 = findMedians(inputarray6);
        System.out.printf("%7d %7.1f %5.1f\n", n, watch4.elapsedTime(),watch4.elapsedTime()/prev2);
        prev2 = watch4.elapsedTime();
       }

       //测试数组为顺序的情况下

       int[] inputarray7 = new int[250];
       for(int i=0;i<inputarray7.length;i++){
        inputarray7[i]=i+1;
       }

       Stopwatch watch5 = new Stopwatch();
       int[] methodmedian6 = findMedians(inputarray7);
       double prev3 = watch5.elapsedTime();

       for(int n =250;n>0;n*=2){
        int[] inputarray8= new int[n];
        for(int i=0;i<inputarray8.length;i++){
            inputarray8[i]=i+1;
        }

        Stopwatch watch6 = new Stopwatch();
        int[] methodmedian7 = findMedians(inputarray8);
        System.out.printf("%7d %7.1f %5.1f\n", n, watch6.elapsedTime(),watch6.elapsedTime()/prev3);
        prev3 = watch6.elapsedTime();
       }


       //测试数组为逆序的情况下
       int[] inputarray9 = new int[250];
       for(int i=0;i<inputarray9.length;i++){
        inputarray9[i]=inputarray9.length-i;
       }
       Stopwatch watch7 = new Stopwatch();
       int[] methodmedian8 = findMedians(inputarray9);
       double prev4 = watch7.elapsedTime();

       for(int n =250;n>0;n*=2){
        int[] inputarray10= new int[n];
        for(int i=0;i<inputarray10.length;i++){
            inputarray10[i]=inputarray10.length-i;
        }

        Stopwatch watch8 = new Stopwatch();
        int[] methodmedian9 = findMedians(inputarray10);
        System.out.printf("%7d %7.1f %5.1f\n", n, watch8.elapsedTime(),watch8.elapsedTime()/prev4);
        prev4 = watch8.elapsedTime();
       }

    }

}
