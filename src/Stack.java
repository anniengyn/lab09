
public interface Stack<T> {
	public void push(T Object);

	public T pop() throws StackUnderflow;

	public boolean isEmpty();

	public boolean isFull();

	public String toString();

	public String peek();

}

