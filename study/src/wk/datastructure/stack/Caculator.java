package wk.datastructure.stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static wk.util.StaticImport.toMap;

import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

/**
 * 利用栈实现计算器
 * 
 * @author wk
 *
 */
public class Caculator {

    enum Operator {
        PLUS('+', 1) {
            @Override
            public int caculate(int x, int y) {
                return x + y;
            }
        },
        MINUS('-', 1) {
            @Override
            public int caculate(int x, int y) {
                return x - y;
            }
        },
        MUTIPLE('*', 2) {
            @Override
            public int caculate(int x, int y) {
                return x * y;
            }
        },
        DIVIDE('/', 2) {
            @Override
            public int caculate(int x, int y) {
                return x / y;
            }
        };

        final int oper;
        final int priority;

        private static final Map<Integer, Operator> operMap = Stream.of(values()).collect(toMap(t -> t.oper, t -> t));

        private Operator(Character oper, int priority) {
            this.oper = oper;
            this.priority = priority;
        }

        public static boolean isOper(int oper) {
            return operMap.containsKey(oper);
        }

        public static Operator valueOf(int oper) {
            return operMap.get(oper);
        }

        public abstract int caculate(int x, int y);

        @Override
        public String toString() {
            return String.valueOf((char)oper);
        }
    }

    public static int caculate(String expression) {
        // 数栈
        Stack<Integer> numberStack = new Stack<Integer>();
        // 符号栈
        Stack<Integer> operStak = new Stack<>();
        ExpressionParser parser = new ExpressionParser(expression);
        while (parser.hasNext()) {
            int element = parser.nextElement();
            if (isOper(element)) {
                // 如果待压栈符号优先级小于栈顶符号优先级，则先弹出该符号与数栈中栈顶两个数做运算，之后再压栈
                while (operStak.get() != null && priority(element) <= priority(operStak.get())) {
                    int oper = operStak.pop();
                    int secondNum = numberStack.pop();
                    int firstNum = numberStack.pop();
                    numberStack.push(doCaculate(firstNum, secondNum, oper));
                }
                operStak.push(element);
            } else {
                numberStack.push(element);
            }
        }
        // 此时符号栈从栈顶至栈底优先级顺序减小，依次弹出计算即可
        while (operStak.get() != null) {
            int oper = operStak.pop();
            int secondNum = numberStack.pop();
            int firstNum = numberStack.pop();
            numberStack.push(doCaculate(firstNum, secondNum, oper));
        }
        return numberStack.pop();
    }

    private static Integer doCaculate(int firstNum, int secondNum, int oper) {
        return Operator.valueOf(oper).caculate(firstNum, secondNum);
    }

    public static int priority(int operator) {
        return Operator.valueOf(operator).priority;
    }

    public static boolean isOper(int operator) {
        return Operator.isOper(operator);
    }

    @Test
    public void test() {
        assertEquals(1 + 5 * 3 - 6 / 2 + 8, caculate("1+5*3-6/2+8"));
    }
}
