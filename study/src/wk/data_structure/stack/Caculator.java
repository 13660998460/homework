package wk.data_structure.stack;

/**
 * 利用栈实现计算器
 * 
 * @author wk
 *
 */
public class Caculator {
    public static void main(String[] args) {
        int result = caculate("1+2+3*3");
        System.out.println(result);
    }

    public static int caculate(String expression) {
        Stack<Integer> numberStack = new Stack<Integer>();
        Stack<Integer> operStak = new Stack<>();
        ExpressionParser parser = new ExpressionParser(expression);
        while (parser.hasNext()) {
            int element = parser.nextElement();
            if (isOper(element)) {
                if (operStak.get() != null && priority(element) < priority(operStak.get())) {
                    int oper = operStak.pop();
                    int firstNum = numberStack.pop();
                    int secondNum = numberStack.pop();
                    numberStack.push(doCaculate(firstNum, secondNum, oper));
                }
                operStak.push(element);
            } else {
                numberStack.push(element);
            }
        }

        while (operStak.get() != null) {
            int oper = operStak.pop();
            int firstNum = numberStack.pop();
            int secondNum = numberStack.pop();
            numberStack.push(doCaculate(firstNum, secondNum, oper));
        }
        return numberStack.pop();
    }

    private static Integer doCaculate(int firstNum, int secondNum, int oper) {
        switch (oper) {
            case '+':
                return firstNum + secondNum;
            case '-':
                return firstNum - secondNum;
            case '*':
                return firstNum * secondNum;
            case '/':
                return firstNum / secondNum;
            default:
                return -1;
        }
    }

    public static int priority(int operator) {
        switch (operator) {
            case '+':
                return 0;
            case '-':
                return 0;
            case '*':
                return 1;
            case '/':
                return 1;
            default:
                return -1;
        }
    }

    public static boolean isOper(int operator) {
        return priority(operator) != -1;
    }

    public static String transform(String expression) {
        // 1+5*((3+4)-5)-3+5 34+5-5*3-5+
        Stack<Integer> numberStack = new Stack<Integer>();
        Stack<Integer> operStak = new Stack<>();
        ExpressionParser parser = new ExpressionParser(expression);

        while (parser.hasNext()) {
            int element = parser.nextElement();
            if (isOper(element)) {
                if (element == ')') {
                    while (operStak.get() != '(') {
                        numberStack.push(operStak.pop());
                    }
                    operStak.pop();
                }
                if (operStak.get() != null && priority(element) < priority(operStak.get())) {
                    int oper = operStak.pop();
                    int firstNum = numberStack.pop();
                    int secondNum = numberStack.pop();
                }
                operStak.push(element);
            } else {
                numberStack.push(element);
            }
        }

        return "";
    }
}
