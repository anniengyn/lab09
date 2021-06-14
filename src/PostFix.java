//import org.jetbrains.annotations.Contract;


public class PostFix {

	public PostFix() {
	}

	public int evaluate(String pfx, boolean HexaMode) throws Exception {
		Stack<Integer> stack = new StackAsList<Integer>(20);
		String[] c = pfx.split(" ");
		int a, b;

		for (String input : c) {
			if (input.matches("\\w+")) {
				if (!HexaMode) {
					stack.push(Integer.parseInt(input));
				} else {
					stack.push(Integer.decode("#" + input));
				}
			} else {

				b = stack.pop();
				a = stack.pop();

				stack.push(calculation(a, b, input.toCharArray()[0]));
			}
		}

		return stack.pop();

	}

	private int calculation(int a, int b, char input) throws Exception {

		switch (input) {
		case '^':
			return (int) (Math.pow(a, b));
		case '*':
			return a * b;
		case '/':
			return a / b;
		case '+':
			return a + b;
		case '-':
			return a - b;

		default:
			throw new Exception("ERROR");
		}
	}

	// priority levels of operators, 3 = high priority 1 = lowest priority
	// https://www.geeksforgeeks.org/stack-set-2-infix-to-postfix/

	public static int precedence(char c) {
		switch (c) {
		case '^':
			return 3;
		case '*':
		case '/':
			return 2;
		case '+':
		case '-':
			return 1;
		case ')':
		case '(':
			return 0;
		default:
			return -1;
		}
	}
	
	 private static boolean precedencePrio(String a, String b) {
	        int op1 = precedence(a.charAt(0));
	        int op2 = precedence(b.charAt(0));

	        return op1 == op2 || op1 > op2;
	    }


	public static String infixToPostfix(String ifx) throws StackUnderflow {
		Stack<String> stack2 = new StackAsList<String>(20);
		String[] c = ifx.split(" ");
		String pfxResult = "";

		for (String input : c) {

			if (input.matches("\\w+"))
				pfxResult += input + " ";

			else if (input.equals("("))
				stack2.push(input);

			else if (input.equals(")")) {
				while (!stack2.isEmpty() && !stack2.peek().equals("("))
					pfxResult += stack2.pop() + " ";
				stack2.pop();
			}
		       else {
		           while(!stack2.isEmpty() && precedencePrio(stack2.peek(), input)) {
		               pfxResult += stack2.pop() + " ";
		           }
		           stack2.push(input);
		       }
		   }

		while (!stack2.isEmpty())
			pfxResult += stack2.pop() + " ";
		return pfxResult;
	}
	



}