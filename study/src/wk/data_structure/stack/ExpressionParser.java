package wk.data_structure.stack;

public class ExpressionParser {
    private String expression;

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
        while (true) {
            if (!hasNext())
                break;
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
