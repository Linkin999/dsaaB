import java.util.*;

public class StackProblem{
    public static long score(String s){

        Stack<String> ops = new Stack<>();
 
        // Stores index of
        // character of String
        int i = 0;
     
        // Stores total scores
        // obtained from the String
        long ans = 0;
     
        // Iterate over characters
        // of the String
        while (i < s.length())
        {
     
          // If s[i] is '('
          if (s.charAt(i) == '(')
            ops.push("(");
          else
          {
     
            // If top element of
            // stack is '('
            if (ops.peek() == "(")
            {
              ops.pop();
              ops.push("1");
            }
            else
            {
     
              // Stores score of
              // inner parentheses
              long count = 0;
     
              // Calculate score of
              // inner parentheses
              while (ops.peek() != "(")
              {
     
                // Update count
                count += Integer.parseInt(ops.peek());
                ops.pop();
              }
     
              // Pop from stack
              ops.pop();
     
              // Insert score of
              // inner parentheses
              ops.push(String.valueOf(2 * count));
            }
          }
     
          // Update i
          i++;
        }
     
        // Calculate score
        // of the String
        while (!ops.isEmpty())
        {
     
          // Update ans
          ans += Integer.parseInt(ops.peek());
          ops.pop();
        }
        return ans;
    }

    public static void main(String[] args)
  {
    String S1 = "()()((()()))";
    System.out.print(score(S1) +"\n");
  }
}