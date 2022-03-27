public class AbstractStack <E> {

    private int top; /* will index the "top" of values in the array */
    private int max;
    private E[] stackArray;

    AbstractStack (int size) {
        if (size <= 0) {
            throw new IllegalArgumentException();
        }
        top = -1;  /* due to Java's indexing, an empty stack should be a negative value */
        stackArray = (E[])new Object[size];  /* The idea is to optimally size the stack on declaration */
        max = size;
    }

    public void push(E x) {
        if (top < max - 1) {
            top++;
            stackArray[top] = x;
        }
    }

    public E pop() {
        if (top != -1) {
            E y = stackArray[top];
            --top;
            return y;
        }
        else {
            return null;  /* will have to be used in error handling in a different class */
        }
    }

    public E peek() {
        if (top != -1) {
            return stackArray[top];
        }
        else {
            return null;  /* will have to be used in error handling in a different class */
        }

    }

    public boolean isEmpty() {
        if (top < 0) {
            return true;
        } else {
            return false;
        }
    }
}
