

public class StackAsList<T> implements Stack<T> {

	private T[] stackArray = null;
	private int top = 0;
	private int maxSize;

	@SuppressWarnings("unchecked")

	public StackAsList(int cap) {
		maxSize = cap;
		stackArray = (T[]) new Object[cap];
	}


	// Returns a String that outputs the contents of the Stack

	public String toString() {
		if (isEmpty()) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= top; i++) {
			sb.append(stackArray[i] + ", ");
		}

		sb.setLength(sb.length() - 2);
		return sb.toString();
	}

	// Checks if the Stack is empty
	@Override
	public boolean isEmpty() {
		if (top < 1) {
			return true;
		}
		return false;
	}

	// Checks if the Stack is full
	@Override
	public boolean isFull() {
		if (top == maxSize - 1) {
			return true;
		}
		return false;
	}

	// adds or "pushes" a new element to the stack
	@Override
	public void push(T t)  {

		stackArray[++top] = t;

	}

	// returns and removes/"pops" the last element added from the stack
	@Override
	public T pop() throws StackUnderflow {

		if (isEmpty()) {
			throw new StackUnderflow("Stack underflow");
		}

		T result = stackArray[top];
		stackArray[top] = null;
		top--;

		return result;
	}

	public String peek() {

		if (top == -1) {
			return "^";
		}

		return (String) stackArray[top];

	}

}

