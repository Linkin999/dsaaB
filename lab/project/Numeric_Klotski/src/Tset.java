import java.util.Scanner;

public class Tset {
    
    public static int transform(String s){
        if(s.equals("1*2")){
            return 1;
        }
        else if(s.equals("2*1")){
            return 2;
        }
        else if(s.equals("2*2")){
            return 3;
        }
        else{
            return 0;//0代表不支持以外的block
        }

    }
    public static void main(String[] args) {
        /*Scanner input = new Scanner(System.in);
        int NumberOfBlock = input.nextInt();
        int NumberOfBlock1 = input.nextInt();
        int[][] array=new int[NumberOfBlock][NumberOfBlock1];
        int[] arrayofdecide= new int[NumberOfBlock*NumberOfBlock1];
        for(int i=0;i<NumberOfBlock;i++){
            for(int j=0;j<NumberOfBlock1;j++){
                array[i][j]=input.nextInt();
            }
        }

        for(int i=0;i<NumberOfBlock;i++){
            for(int j=0;i<NumberOfBlock1;j++){
                System.out.println(array[i][j]);
                //arrayofdecide[NumberOfBlock1*i+j]=array[i][j];
            }
        }

        //sizeofblock为numofblock*2的二维数组，第一列代表block的种类，第二列代表block中左上角的数字，
        /*int[][] SizeOfBlock = new int[NumberOfBlock][2];
        System.out.println("输入数值和类型:");
        for(int i=0;i<NumberOfBlock;i++){
            SizeOfBlock[i][1]=input.nextInt();
            SizeOfBlock[i][0]=transform(input.next());                   
        }
        for(int i=0;i<NumberOfBlock;i++){
            for(int j=0;j<2;j++){
                System.out.println(SizeOfBlock[i][j]);
            }                  
        }*/

        int[][] array = new int[2][2];
        array[0][0]=1;
        array[0][1]=2;
        array[1][0]=3;
        array[1][1]=4;

        int n= array[1][1];
        array[1][1]=5;
        
        System.out.println(n);
        System.out.println(array[1][1]);
    }
}
