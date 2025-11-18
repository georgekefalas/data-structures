// Georgios Kefalas, A.M: 5252
// Dimitrios Papadopoulos, A.M: 5321
// Group 3, ID: 3

import java.io.* ;

public class Calculator
{   
    private static void Calculate(String expr) 
    {
        int N = expr.length();
        char[] a = expr.toCharArray();
        Stack<Character> s1 = new Stack<Character>();
        Stack<Integer> s2 = new Stack<Integer>();
        if (a[0] != '(' || a[N-1] != ')') {
            System.out.println("The expression must be enclosed in a parenthesis!");
            System.exit(0);
        }
        else {
            for (int i=1; i<N; i++)
            {
                Character c = a[i];
                if ( (a[i-1] == '(')  &&  (a[i+1] == ')') ) {
                    System.out.println("Wrong expression. Character " + c  + " is between an unecessary parenthesis.");
                    System.exit(0);
                }
            }
            for (int j = 1; j < N; j++) {
                Character c = a[j];
                if (c == '+' || c == '*' || c == '-') {
                    s1.push(c);
                }
                else if (c == ')') {
                    char operation = s1.pop();
                    if (operation == '+') {
                        s2.push(s2.pop() + s2.pop());
                    }
                    else if (operation == '*') {
                        s2.push(s2.pop() * s2.pop());
                    }
                    else {
                        Integer x = s2.pop();
                        Integer y = s2.pop();
                        s2.push(y-x);
                    }
                }
                else if (c != ' ' && c != '(') {
                    s2.push(Character.getNumericValue(c));
                }
            }
            System.out.println("Result = " + s2.pop());
        }
    }
    
    public static void main(String[] args)
    {
        String expr = args[0];
        System.out.println("Input expression = " + expr + " , length = " + expr.length());
                
        Calculate(expr);
    }
}
