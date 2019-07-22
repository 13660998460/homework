package wk.data_structure.linked_list;

import static wk.util.StaticImport.sysout;

/**
 * 约瑟夫问题
 * 
 * 问题概述： n个人排成一圈。从某个人开始，按顺时针方向依次编号。从编号为1的人开始顺时针“一二三”报数， 报到m的人退出圈子，试问最后剩下的人最开始的编号。
 * 
 * @author wk
 *
 */
public class JosephProblem {
    public static void main(String[] args) {
        CircleDoubleLinkedList list = new CircleDoubleLinkedList(50);
        int last = list.outCircle(2);
        sysout(last);
    }

    private static class CircleDoubleLinkedList {
        private Person first;

        public CircleDoubleLinkedList(int n) {
            if (n < 1)
                return;
            first = new Person(1, null, null);
            Person temp = first;
            for (int i = 2; i <= n; i++) {
                Person p = new Person(i, temp, null);
                temp.next = p;
                temp = p;
            }
            temp.next = first;
            first.pre = temp;
        }

        public int outCircle(int m) {
            StringBuilder sb = new StringBuilder();
            Person temp = first;
            int count = 1;
            while (temp.next != temp) {
                if (count % m == 0) {
                    temp.pre.next = temp.next;
                    temp.next.pre = temp.pre;
                    sb.append(temp.number + ", ");
                }
                count++;
                temp = temp.next;
            }
            sb.append(temp.number);
            sysout("出圈序列:" + sb.toString());
            return temp.number;
        }
    }

    private static class Person {
        int number;
        Person pre;
        Person next;

        public Person(int number, Person pre, Person next) {
            this.number = number;
            this.pre = pre;
            this.next = next;
        }

    }
}
