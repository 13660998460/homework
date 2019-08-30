package wk.datastructure.linkedlist;

/**
 * 双向链表
 * 
 * @author wk
 *
 */
public class DoubleLinkedList<T>
{
    private Node<T> first = new Node<>(null, null, null);   // 占位符

    private static class Node<T>
    {
        T element;
        Node<T> pre;
        Node<T> next;

        public Node(T element, Node<T> pre, Node<T> next)
        {
            this.element = element;
            this.pre = pre;
            this.next = next;
        }
    }

    public void add(T t)
    {
        Node<T> temp = first;
        while (temp.next != null)
        {
            temp = temp.next;
        }
        Node<T> node = new Node<>(t, temp, null);
        temp.next = node;
    }

    public void remove(T t)
    {
        Node<T> temp = first.next;
        while (temp != null)
        {
            if (temp.element.equals(t))
            {
                temp.pre.next = temp.next;
                if (temp.next != null)
                {
                    temp.next.pre = temp.pre;
                }
                return;
            }
            temp = temp.next;
        }
    }
}
