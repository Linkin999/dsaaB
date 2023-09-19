
import edu.princeton.cs.algs4.Queue;

import java.util.Scanner;

public class Adopt {
    public static String[] adoptOrder(String[] names, String[] type,String[] adoptType){
        Queue<String> queue1 = new Queue<>();
        Queue<String> queue2 = new Queue<>();
        for (int i = 0; i < names.length; i++) {
            if(type[i].equals("Cat")){
                queue1.enqueue(names[i]);
            }else{
                queue2.enqueue(names[i]);
            }
        }
        for (int i = 0; i < adoptType.length; i++) {
            if (adoptType[i].equals("Cat")&& !queue1.isEmpty()){
                adoptType[i] = queue1.dequeue();
            }else if (adoptType[i].equals("Dog")&&!queue2.isEmpty()){
                adoptType[i] = queue2.dequeue();
            }else if (adoptType[i].equals("Cat")&& queue1.isEmpty()){
                adoptType[i] = queue2.dequeue();
            }else{
                adoptType[i] = queue1.dequeue();
            }
        }
        return adoptType;

    }

    public static void main(String[] args) {
        try( Scanner input = new Scanner(System.in) ) {
            System.out.println("输入个数：");
            int N = input.nextInt();
            String[] names = new String[N];
            System.out.println("输入名字：");
            for (int i = 0; i < names.length; i++) {
                names[i] = input.next();
            }
            String[] types = new String[N];
            System.out.println("输入type:");
            for (int i = 0; i < types.length; i++) {
                types[i] = input.next();
            }
            System.out.println("输入你想要领养的个数：");
            int N1 = input.nextInt();
            String[] adoptType = new String[N1];
            System.out.println("输入你想领哪些：");
            for (int i = 0; i < adoptType.length; i++) {
                adoptType[i] = input.next();
            }
            adoptOrder(names,types,adoptType);
            for( String i : adoptType )
                System.out.println(i);
        }
    }
}
