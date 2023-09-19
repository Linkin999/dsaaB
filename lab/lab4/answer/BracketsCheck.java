import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BracketsCheck {
    private static boolean match( char c1, char c2 ) {
        return (c1=='('&&c2==')') || (c1=='['&&c2==']') || (c1=='{'&&c2=='}');
    }

    public static boolean isValidSequence( String sequence ) {
        Stack<Character> stack = new Stack<>();

        for( char current : sequence.toCharArray() ) {
            switch( current ) {
                case '(':
                case '[':
                case '{':
                    stack.push(current);
                    break;
                case ')':
                case ']':
                case '}':
                    if( stack.isEmpty() || !match(stack.pop(), current) )
                        return false;
                    else
                        break;
                default:
                    StdOut.println("Wrong");
            }
        }

        return stack.isEmpty();
    }

    public static void main( String[] args ) {
        String seq = StdIn.readString();
        if(isValidSequence(seq))
            StdOut.print(1);
        else
            StdOut.print(0);
    }
}
