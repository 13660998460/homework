package wk.datastructure.stack;

/**
 * 表达式解析器
 * @author wk
 */
public class ExpressionParser {
    private final String expression;

    private int index;

    public ExpressionParser(String expression) {
        this.expression = expression;
    }

    public int nextElement() {
        char element = expression.charAt(index++);
        if (!Character.isDigit(element)) {
            return element;
        }
        String result = element + "";
        while (hasNext()) {
            char nextElement = expression.charAt(index);
            if (!Character.isDigit(nextElement)) {
                break;
            } else {
                result += nextElement;
                index++;
            }
        }
        return Integer.parseInt(result);
    }

    public boolean hasNext() {
        return index < expression.length();
    }
}
