import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Main {
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
            }
        }

        return stack.isEmpty();
    }

    public static void main( String[] args ) {
        for( int data = 1; data <= 100; data ++ ) {
            String seq = (new In("./data/"+data+".in")).readAll();
            int ans = (new In("./data/"+data+".out")).readInt();

            if( (isValidSequence(seq)?1:0) != ans )
                StdOut.println("error");
        }
    }
}
