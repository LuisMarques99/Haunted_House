package MyCollection.Stack;

import MyCollection.Exceptions.EmptyCollectionException;

/**
 * <h3>
 * <strong>Class that represents the structure of an {@link ArrayStack ArrayStack} the implements the contract of the
 * {@link StackADT StackADT}</strong>
 * </h3>
 *
 * @param <T> Abstract Data Type
 * @author Luis Marques
 */
public class ArrayStack<T> implements StackADT<T> {

    /**
     * constant to represent the default capacity of the array
     */
    private final int DEFAULT_CAPACITY = 100;

    /**
     * int that represents both the number of elements and the next available position in the array
     */
    private int top;

    /**
     * array of generic elements to represent the stack
     */
    private T[] stack;

    /**
     * Creates an empty stack using the default capacity
     */
    public ArrayStack() {
        top = 0;
        stack = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Creates a stack with a initial capacity defined by the user
     *
     * @param initialCapacity int initial capacity
     */
    public ArrayStack(int initialCapacity) {
        top = 0;
        stack = (T[]) (new Object[initialCapacity]);
    }

    /**
     * Adds the specified element to the top of this stack,
     * expanding the capacity of the stack array if necessary.
     *
     * @param element element to be pushed onto stack
     */
    @Override
    public void push(T element) {
        if (size() == stack.length)
            extendCapacity();

        stack[top] = element;
        top++;
    }

    /**
     * Removes the element at the top of this stack and returns a reference to it.
     * Throws an EmptyCollectionException if the stack is empty.
     *
     * @return T element removed from the top of the stack
     * @throws EmptyCollectionException if a pop is attempted on empty stack
     */
    @Override
    public T pop() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Stack");

        top--;
        T result = stack[top];
        stack[top] = null;

        return result;
    }

    /**
     * Returns a reference to the element at the top of this stack.
     * The element is not removed from the stack.
     * Throws an EmptyCollectionException if the stack is empty.
     *
     * @return T element on top of the stack
     * @throws EmptyCollectionException if peek is attempted on empty stack
     */
    @Override
    public T peek() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Stack");

        return stack[top - 1];
    }

    /**
     * Returns true if this stack contains no elements.
     *
     * @return boolean whether or not this stack is empty
     */
    @Override
    public boolean isEmpty() {
        if (size() == 0)
            return true;

        return false;
    }

    /**
     * Returns the number of elements in this stack.
     *
     * @return int number of elements in this stack
     */
    @Override
    public int size() {
        return top;
    }

    /**
     * Returns a string representation of this stack.
     *
     * @return String representation of this stack
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("-> Array: <-\n");
        for (int i = 0; i < top; i++) {
            string.append("[").append(i).append("]  ");
            string.append(stack[i]).append("\n");
        }
        return string.toString();
    }

    /**
     * Extends the capacity of the stack with more 100(DEFAULT_CAPACITY) positions
     */
    private void extendCapacity() {
        T[] newStack = (T[]) (new Object[stack.length + DEFAULT_CAPACITY]);
        for (int i = 0; i < stack.length; i++)
            newStack[i] = stack[i];
        stack = newStack;
    }
}