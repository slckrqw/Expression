import java.util.Stack;

public class Expression {

    static double evaluateExpression(String expression) throws Exception {
        // Удаляем пробелы из строки
        expression = expression.replaceAll("\\s+", "");

        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (Character.isDigit(ch)) {
                StringBuilder sb = new StringBuilder();

                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    sb.append(expression.charAt(i++));
                }

                i--;
                values.push(Double.parseDouble(sb.toString()));
            } else if (ch == '(') {
                operators.push('(');
            } else if (ch == ')') {
                while (!operators.empty() && operators.peek() != '(') {
                    performOperation(values, operators.pop());
                }

                if (!operators.empty()) {
                    operators.pop(); // удаляем открывающуюся скобку
                } else {
                    throw new Exception("Неверно расставлены скобки");
                }
            } else if (isOperator(ch)) {
                while (!operators.empty() && hasPrecedence(ch, operators.peek())) {
                    performOperation(values, operators.pop());
                }

                operators.push(ch);
            }
        }

        while (!operators.empty()) {
            performOperation(values, operators.pop());
        }

        return values.pop();
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private static boolean hasPrecedence(char op1, char op2) {
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        } else return op2 != '(';
    }

    private static void performOperation(Stack<Double> values, char operator) throws Exception {
        if (values.size() < 2) {
            throw new Exception("Слишком короткое выражение");
        }

        double value2 = values.pop();
        double value1 = values.pop();

        switch (operator) {
            case '+':
                values.push(value1 + value2);
                break;
            case '-':
                values.push(value1 - value2);
                break;
            case '*':
                values.push(value1 * value2);
                break;
            case '/':
                if (value2 == 0) {
                    throw new Exception("Деление на ноль");
                }
                values.push(value1 / value2);
                break;
            default:
                throw new Exception("Неизвестный оператор: " + operator);
        }
    }
}