import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите математическое выражение:");
        String expression = scanner.nextLine();

        try {
            double result = Expression.evaluateExpression(expression);
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            System.err.println("Ошибка при обработке выражения: " + e.getMessage());
        }

        scanner.close();
    }
}