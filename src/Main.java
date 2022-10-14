import java.util.Arrays;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Выполните ввод выражения.");
        Scanner sc = new Scanner(System.in); //ввод выражения
        String expression = sc.nextLine();  //сохраняем строку с выражением в переменную
        String[] signs = {"*", "/", "+", "-"}; //массив с переменными для мат. оператора
        String[] romanNumbers = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "XI"}; //массив с римскими цифрами
        String[] romanNumbersC10 = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "CX", "C"}; //массив с римскими цифрами

        if (expression.contains("I") || expression.contains("V") || expression.contains("X") || expression.contains("L") || expression.contains("C") ||
                expression.contains("D") || expression.contains("M")) { //проверяем строку на содержание римской цифры
            if (!(expression.contains("XX")) && !(expression.contains("L")) && !(expression.contains("C")) && !(expression.contains("D")) &&
                    !(expression.contains("M"))) { //проверяем строку на не содержание римской цифры
                for (int i = 0; i < 4; i++) {
                    if (expression.contains(signs[i])) {                              //проверяем строку на содержание знака
                        expression = expression.replaceAll("\\s+",""); //удаляем пробелы, которые вставил в строку коварный тестировщик
                        String[] splitExpression = expression.split("[*/+-]");  //если он есть, делим по нему на массив строк
                        String romanNumbersStr = Arrays.toString(romanNumbers);             //создаем строку из массива с римскими цифрами
                        if (romanNumbersStr.contains(splitExpression[0]) && romanNumbersStr.contains(splitExpression[1])) {  //проверяем оба операнда на принадлежность к римским цифрам
                            Roman a = Roman.valueOf(splitExpression[0]);  //достаем значения из ENUM тут...
                            Roman b = Roman.valueOf(splitExpression[1]);  //...и тут
                            Integer x = a.getArabian();                   //сохраняем в переменных тут...
                            Integer y = b.getArabian();                   //...и тут
                            if (x > 0 && x < 11 && y > 0 && y < 11) {     //проверяем на соответствия условиям задачи
                                int res = 0;
                                if (splitExpression.length > 2) {
                                    try {
                                        throw new IOException();
                                    } catch (IOException e) {
                                        System.out.println("throws Exception: Формат математической операции не удовлетворяет заданию - " +
                                                "два операнда от 1 до 10 и один оператор (+, -, /, *) !");
                                        break;
                                    }
                                }
                                if (signs[i].contains("*")) {
                                    res = x * y;
                                } else if (signs[i].contains("/")) {
                                    res = x / y;
                                } else if (signs[i].contains("+")) {   //тут я не смог разобраться как мат. оператор использовать в переменной...
                                    res = x + y;                   //....по этому такая громоздкая конструкция
                                } else {
                                    res = x - y;
                                }
                                if (res > 0) {
                                    int c = res / 10;
                                    int n = res % 10;
                                    System.out.println("Возвращаемое значение: " + romanNumbersC10[c] + romanNumbers[n]);  // ну и выводим
                                    break;
                                } else {
                                    try {
                                        throw new IOException();
                                    } catch (IOException e) {
                                        System.out.println("throws Exception: Т.к. в римской системе нет отрицательных чисел!");
                                        break;
                                    }
                                }
                            } else {
                                try {
                                    throw new IOException();
                                } catch (IOException e) {
                                    System.out.println("throws Exception: Вводимые числа не должны быть меньше 1 или больше 10!");
                                    break;
                                }
                            }
                        } else {
                            try {
                                throw new IOException();
                            } catch (IOException e) {
                                System.out.println("throws Exception: Т.к. используются одновременно разные системы счисления!");
                                break;
                            }
                        }

                    }
                }
            } else {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    System.out.println("throws Exception: Вводимые числа не должны быть меньше 1 или больше 10!");
                                    }
            }
        } else if (expression.matches(".*\\d.*")) {
            for (int i = 0; i < 4; i++) {
                if (expression.contains(signs[i])) {                              //проверяем строку на содержание знака
                    String[] splitExpression = expression.split("[*/+-]");  //если он есть, делим по нему на массив строк
                    int x = Integer.parseInt(splitExpression[0].trim());  //сохраняем строки в переменных с типом int тут...
                    int y = Integer.parseInt(splitExpression[1].trim());  //...и тут
                    if (x > 0 && x < 11 && y > 0 && y < 11) {             //проверяем на соответствия условиям задачи
                        int res = 0;
                        if (splitExpression.length > 2) {
                            try {
                                throw new IOException();
                            } catch (IOException e) {
                                System.out.println("throws Exception: Формат математической операции не удовлетворяет заданию - " +
                                        "два операнда и один оператор (+, -, /, *) !");
                                break;
                            }
                        }
                        if (signs[i].contains("*")) {
                            res = x * y;
                        } else if (signs[i].contains("/")) {
                            res = x / y;
                        } else if (signs[i].contains("+")) {   // тут я не смог разобраться как мат. оператор использовать в переменной...
                            res = x + y;                       //....по этому такая громоздкая конструкция
                        } else {
                            res = x - y;
                        }
                        System.out.println("Возвращаемое значение: " + res);  // ну и выводим
                        break;
                    } else {
                        try {
                            throw new IOException();
                        } catch (IOException e) {
                            System.out.println("throws Exception: Вводимые числа не должны быть меньше 1 или больше 10!");
                            break;
                        }
                    }
                }
            }
        } else {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("throws Exception: Строка не является математической операцией!");
            }
        }
    }
}