package wk.datastructure.stack;

/**
 * 栈简易实现，固定大小
 * 
 * @author wk
 * @date 2019/07/25
 */
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

    public int size() {
        return index;
    }

    @SuppressWarnings("unchecked")
    private T element(int index) {
        return (T)elements[index];
    }
}
