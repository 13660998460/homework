package wk.data_structure.linked_list;

import wk.data_structure.stack.Stack;

/**
 * 单向链表
 * 
 * @author wk
 *
 */
public class SingleLinnkedList<T>
{
    private Node<T> first = new Node<>(null);   // 占位符

    public static class Node<T>
    {
        T element;

        Node<T> next;

        public Node(T element)
        {
            this.element = element;
        }

        @Override
        public String toString()
        {
            return element.toString();
        }
    }

    /**
     * 单链表反转
     */
    public void reserved()
    {
        Node<T> oldPointer = first.next;        // 旧链表指针
        Node<T> newPointer = null;              // 新链表指针
        Node<T> exchanged = oldPointer;         // 被交换的元素
        while (oldPointer != null)
        {
            oldPointer = oldPointer.next;
            exchanged.next = newPointer;
            newPointer = exchanged;
            exchanged = oldPointer;
        }
        first.next = newPointer;
    }

    /**
     * 逆序打印
     */
    public void reservePrint()
    {
        Stack<T> stack = new Stack<>();     // 借助栈
        Node<T> temp = first.next;
        while (temp != null)
        {
            stack.push(temp.element);
            temp = temp.next;
        }
        StringBuilder sb = new StringBuilder();
        T t = null;
        while ((t = stack.pop()) != null)
        {
            sb.append(t + ", ");
        }
        sb.setLength(sb.length() - 2);      // 不使用delete从而避免内存复制
        System.out.println(sb);
    }

    // 单链表的大部分操作都需要遍历整个链表，而删除操作更是必须要知道被删元素的前一个元素。

    public void add(T t)
    {
        Node<T> node = new Node<>(t);
        Node<T> temp = first;
        while (temp.next != null)
        {
            temp = temp.next;
        }
        temp.next = node;
    }

    public void remove(T t)
    {
        Node<T> temp = first;
        while (temp.next != null)
        {
            if (temp.next.element.equals(t))
            {
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
    }

    public T get(int index)
    {
        Node<T> node = first.next;
        while (index > 0 && node != null)
        {
            node = node.next;
            index--;
        }
        return node.element;
    }

    public static void main(String[] args)
    {
        // 测试
        SingleLinnkedList<Integer> list = new SingleLinnkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.reservePrint();
        list.reserved();
        list.reservePrint();
    }
}
