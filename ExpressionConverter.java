import java.util.Scanner;
import java.util.Stack;

public class ExpressionConverter {
  // return the precedence of the operator
  // higher number represent higher precedence
  private static int getPrecedence(Character ch) {
    if (ch == '*' || ch == '/') {
      return 2;
    }
    
    if (ch == '+' || ch == '-') {
      return 1;
    }

    return -1;
  }

  // return true if ch is equal to either +, -, *, /
  private static boolean isOperator(Character ch) {
    return ch == '+' || ch == '-' || ch == '*' || ch == '/';
  }

  public static String infixToPostfix(String expression) {
    Stack<Character> stack = new Stack<Character>();
    String postfixExpression = "";

    // expression.length() -> get the length of the string
    for (int i = 0; i < expression.length(); i++) {
      Character scannedCharacter = expression.charAt(i);
      
      // Character.isAlphabetic(character) -> check if character is an alphabet
      // Character.isDigit(character) -> check if character is a digit
      if (Character.isAlphabetic(scannedCharacter) || Character.isDigit(scannedCharacter)) {
        // if scanned character is digit or alphabet, add to the postfix expression.
        postfixExpression += scannedCharacter;
      }

      if (scannedCharacter == '(') {
        // if scanned character is an opening bracket, push to stack
        stack.push(scannedCharacter);
      }

      if (scannedCharacter == ')') {
        // if scanned character is a closing bracket, pop the stack
        while (true) {
          // pop the stack
          Character popCharacter = stack.pop();

          // if the pop character from the stack is an opening bracket, stop the
          // loop.
          if (popCharacter == '(') {
            break;
          }

          postfixExpression += popCharacter;
        }
      }
      
      if (isOperator(scannedCharacter)) {
        while (stack.empty() == false && getPrecedence(stack.peek()) >= getPrecedence(scannedCharacter)) {
          // stack is not empty too, if empty just push it to the stack
          // pop the stack while stack top has higher or equal precedence compare to scanned character
          // the pop character will be appended to the postfix expression
          postfixExpression += stack.pop();
        }

        // push scanned character to stack
        stack.push(scannedCharacter);
      }
    }

    // check if there are any characters left in the stack.
    // if there are, remember to pop it all and append it to the postfix expression
    while (stack.empty() == false) {
      // while stack is not empty
      // pop the character then append to the postfix expression
      postfixExpression += stack.pop();
    }

    return postfixExpression;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Infix: ");
    String expression = scanner.nextLine();

    // remove all white spaces in the expression string
    expression = expression.replaceAll("\\s+","");

    System.out.println("Postfix: " + infixToPostfix(expression));

    scanner.close();
  }
}
