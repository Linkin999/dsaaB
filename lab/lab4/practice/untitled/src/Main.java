import edu.princeton.cs.algs4.*;

public class Main {
    public static boolean match(char c1,char c2){
        return ((c1=='('&&c2==')')||(c1=='['&&c2==']')||(c1=='{'&&c2=='}'));
    }
    public static int isValidSeq(String sequence){
        Stack<Character> stack=new Stack<>();
        for (char current:sequence.toCharArray()){
            switch (current){
                case '(':
                case '[':
                case '{':
                    stack.push(current);
                    break;
                case ')':
                case ']':
                case '}':
                    if (stack.isEmpty()||!match(stack.pop(),current))
                        return 0;
                    break;
                default:
                    break;
            }
        }
        return stack.isEmpty()?1 : 0;
    }

    public static void main(String[] args) {
        for(int data=1;data<=100;data++){
            In f1=new In();
            In f2=new In();

        }
    }
}