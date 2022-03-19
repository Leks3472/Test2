package com.leks;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Ввведите выражение \"a\" + \"b\", \"a\" - \"b\", \"a\" * x, \"a\" / x,  где a и b - строки, а x - число  от 1 до 10 включительно");
        Scanner scan = new Scanner(System.in);
        String inPut = scan.nextLine();
        char action = inPut.charAt(index(inPut));
        if (action != '+' && action != '-' && action != '*' && action != '/')
        {
            System.out.println("Введено неверное выражение. Проверте кавычки и формат выражения");
            System.exit(0);
        }
        String firstOperand = ""; // первая часть выражения
        String secondOperand = ""; // вторая часть выражения
        String inPutWithoutSpace = deletSpase(inPut);
        String result = "";
        switch (action) {
            case '+':
            case '-':
                checkPlusAndMinus(deletSpase(inPutWithoutSpace));
                break;
            case '*':
            case '/':
                checkMultAndDivision(deletSpase(inPutWithoutSpace));
                break;
        }

        //нахождение 1-й части выражения
        int i = 0;
        while (i < index(inPutWithoutSpace)) {
            firstOperand = (firstOperand + inPutWithoutSpace.charAt(i)).replace("\"", "");
            i++;
        }
    //нахождение 2-й части выражения
        i = index(inPutWithoutSpace) + 1;
        while (i < inPutWithoutSpace.length()) {
            secondOperand = (secondOperand + inPutWithoutSpace.charAt(i)).replace("\"", "");
            i++;
        }
        if (firstOperand.length() > 10 || secondOperand.length() > 10) {
            System.out.println("Вы ввели слишком длинный элеметн выражения");
            System.exit(0);
        }

        if ((action == '/' || action == '*')) {
            try {
            Integer.parseInt(secondOperand);
        }
        catch (NumberFormatException e) {
            System.out.println("Во второй части выражения должны быть целые числа 1...10 включительно");
            System.exit(0);
        }
        }
        if ((action == '/' || action == '*') && (Integer.parseInt(secondOperand) < 1 || Integer.parseInt(secondOperand) > 10)) {
            System.out.println("Неверная вторая часть выражения");
            System.exit(0);
        }
        switch (action) {
            case '+':
                result = methodPlus(firstOperand, secondOperand);
                break;
            case '-':
                result = methodMinus(firstOperand, secondOperand);
                break;
            case '*':
                result = methodMulti(firstOperand, secondOperand);
                break;
            case '/':
                result = methodDevision(firstOperand, secondOperand);
                break;
        }
        if (result.length() > 40) {
            System.out.println("\"" + result.substring(0, 40) + "..." + "\"");
        }
        else {
            System.out.println("\"" + result + "\"");
        }
        }
    public static String methodPlus(String a, String b) {
        String result = a + b;
        return result;

    }

    public static String methodMinus(String a, String b) {
        String result = "";
        int lengthA = a.length();
        int lengthB = b.length();

        if (lengthA < lengthB) {
            return "вторая часть выраения должна быть короче первой";
        }
        int minus = lengthA - lengthB;
        if ((a.substring(minus).equals(b))) {
            result = a.substring(0, minus);
        } else {
            result = a;
        }
        return result;
    }
    public static String methodMulti(String a, String b) {
        int i = 0;
        String result = "";
        for (i = 0; i < Integer.parseInt(b); i++) {
            result = result + a;
        }
        return result;
    }
    public static String methodDevision(String a, String b) {
        String result = "";
        int num1 = a.length() / Integer.parseInt(b);
        result = a.substring(0, num1);
        if (result == "")
            result = a.substring(0, 1);
        else
            result = a.substring(0, num1);
        return result;
    }
    // находим индекс оператора
    public static Integer index(String inPut) {
        int i = 0;
        int index = 0;
        int quote = 0;
        while (i < inPut.length() && quote <= 2) {
            if (inPut.charAt(i) == '\"') {
                quote++;
            }
            if ((inPut.charAt(i) == '+' || inPut.charAt(i) == '-' || inPut.charAt(i) == '*' || inPut.charAt(i) == '/') && quote == 2) {
                index = i;
                break;
            }
            i++;
        }
                return index;
    }
    //Убираем пробелы около оператора
    public static String deletSpase(String inPut) {
        String result = "";
        if (inPut.charAt(index(inPut) - 1) == ' ') {
            result = result + inPut.substring(0, index(inPut) - 1) + inPut.charAt(index(inPut)) + inPut.substring(index(inPut) + 2);
        } else
        result = inPut;
                return result;
    }

    // проверка првильности введеного выражения (правильная расстановка кавычек) с + b -
           public static void checkPlusAndMinus(String inPut) {
            int i = 0;
            int quote = 0;
            int n = index(inPut);
            while (i < inPut.length()) {
                if (inPut.charAt(i) == '\"') {
                    quote++;
                }
                i++;
            }
            //Проверяет количество кавычек
            if (quote != 4) {
                System.out.println("Введено неверное выражение. Проверте кавычки и формат выражения");
                System.exit(0);
            }
            // проверяет верно ли расставлены кавычки
            if (inPut.charAt(0) != '\"' || inPut.charAt(n - 1) != '\"' || inPut.charAt(n + 1) != '\"' || inPut.charAt(inPut.length() - 1) != '\"') {
                System.out.println("Введено неверное выражение. Проверте кавычки и формат выражения");
                System.exit(0);
            }
    }
    // проверка првильности введеного выражения (правильная расстановка кавычек) с * и /
    public static void checkMultAndDivision(String inPut) {
        int i = 0;
        int quote = 0;
        int n = index(inPut);
            while (i < inPut.length()) {
            if (inPut.charAt(i) == '\"') {
                quote++;
        }
            i++;        }
        //Проверяет количество кавычек
        if (quote != 2) {
            System.out.println("Введено неверное выражение. Проверте кавычки и формат выражения");
            System.exit(0);      }
        // проверяет верно ли расставлены кавычки
        if (inPut.charAt(0) != '\"' || inPut.charAt(n - 1) != '\"') {
            System.out.println("Введено неверное выражение. Проверте кавычки и формат выражения");
            System.exit(0);
        }
    }
}
