package wk.data_structure.stack;

public class Stack<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elements;

    private int index;

    public Stack() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public Stack(int size) {
        elements = new Object[size];
    }

    public Stack(int size, int factor) {
        elements = new Object[size];
    }

    public void push(T t) {
        elements[index++] = t;
    }

    public T pop() {
        if (index == 0)
            return null;
        return element(--index);
    }

    public T get() {
        if (index == 0)
            return null;
        return element(index - 1);
    }

    @SuppressWarnings("unchecked")
    private T element(int index) {
        return (T)elements[index];
    }
}
