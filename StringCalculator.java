package src_kata;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение:");
        String input = scanner.nextLine();

        try {
            System.out.println("\"" + calculate(input) + "\"");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static String calculate(String input) throws Exception {
        Pattern pattern = Pattern.compile("\"([^\"]{1,10})\"\\s*([+\\-*/])\\s*(\"([^\"]{1,10})\"|[1-9]|10)");
        Matcher matcher = pattern.matcher(input);

        if (!matcher.matches()) { //matches()Проверяет, соответствует ли вся строка паттерну.
            throw new Exception("Неверный формат выражения.");
        }

        String str1 = matcher.group(1);
        String operator = matcher.group(2); // что такое оператор?
        String operand = matcher.group(3);// что такое операнд?

        switch (operator) {
            case "+":
                if (!operand.startsWith("\"")) { //если операнд не начинается с ковычек то
                    throw new Exception("Операция сложения возможна только для строк.");
                }
                String str2 = matcher.group(4);
                return formatResult(str1 + str2); // formatResult - wtf?

            case "-":
                if (!operand.startsWith("\"")) {
                    throw new Exception("Операция вычитания возможна только для строк.");
                }
                str2 = matcher.group(4); //group(4) - wtf?
                return formatResult(str1.replaceFirst(str2, ""));

            case "*":
                int multiplier = Integer.parseInt(operand); //parseInt - wtf?
                if (multiplier < 1 || multiplier > 10) {
                    throw new Exception("Множитель должен быть от 1 до 10.");
                }
                return formatResult(str1.repeat(multiplier));

            case "/":
                int divisor = Integer.parseInt(operand);
                if (divisor < 1 || divisor > 10) {
                    throw new Exception("Делитель должен быть от 1 до 10.");
                }
                int resultLength = str1.length() / divisor;
                return formatResult(str1.substring(0, resultLength));

            default:
                throw new Exception("Неподдерживаемая операция.");
        }
    }

    private static String formatResult(String result) {
        return result.length() > 40 ? result.substring(0, 40) + "..." : result;
    }
}