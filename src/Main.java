import com.sun.source.tree.Tree;

import java.util.*;


public class Main {
    static String input ="";

    public static String calc(String input) throws Exception {
        String[] regexOperators = {"\\+", "-", "/", "\\*"};
        String[] operators = {"+", "-", "/", "*"};
        //Определение знака выражения
        int operIndex= -1;
        for (int i=0; i<operators.length; i++) {
            if (input.contains(operators[i])) {
                operIndex = i;
                break;
            }
        }
        int operSum = 0;
        //Считаем колиество арифметических знаков для дальнейшего вывода ошибки
        for (int i=0; i<input.length(); i++) {
            switch (input.charAt(i)) {
                case '+':
                    operSum++;
                    break;
                case '-':
                    operSum++;
                    break;
                case '*':
                    operSum++;
                    break;
                case '/':
                    operSum++;
                    break;
            }
        }
        if (operSum > 1) throw new Exception("Wrong input");
        if (operIndex == -1) throw new Exception("Wrong input");
        // Отделяем числа в int
        String[] strNumbers = input.split(regexOperators[operIndex]);
        int a, b;
        int result = 0;
        boolean isRoman = false;
        if ( strNumbers[0].matches("[IVX]+") == strNumbers[1].matches("[IVX]+")) {
            if (strNumbers[0].matches("[IVX]+")) {
                isRoman = true;
            }
            if (isRoman) {
                a = romanToInt(strNumbers[0]);
                b = romanToInt(strNumbers[1]);
            } else {
                a = Integer.valueOf(strNumbers[0]);
                b = Integer.valueOf(strNumbers[1]);
            }
            if (a<1 || a>10 || b<1 || b>10) throw new Exception("Input numbers must be from 1 to 10");
            //Получаем ответ в виде int
            switch (operIndex) {
                case 0:
                    result = a + b;
                    break;
                case 1:
                    result = a - b;
                    break;
                case 2:
                    result = a / b;
                    break;
                case 3:
                    result = a * b;
                    break;
            }


        } else throw new Exception("Numbers must be the same type");
        if (isRoman) {
            return intToRoman(result);
        } else return String.valueOf(result);
    }

    //Конвертация римского входного числа в арабское
    public static int romanToInt(String roman) {
        Map<Character, Integer> romanInt = new HashMap<>();
            romanInt.put('I', 1);
            romanInt.put('V', 5);
            romanInt.put('X', 10);
        int result = 0;
        int prev = 0;
            for( int i=roman.length()-1; i>=0; i--) {
                int current =  romanInt.get(roman.charAt(i));
                if (current < prev) result -= current;
                else result += current;
                prev = current;
        }


        return result;

    }

    //Конвертация финального арабского в римское
    public static String intToRoman(int number) {
        TreeMap<Integer, String> intRoman = new TreeMap<>();
        intRoman.put(1, "I");
        intRoman.put(4, "IV");
        intRoman.put(5, "V");
        intRoman.put(9, "IX");
        intRoman.put(10, "X");
        intRoman.put(40, "XL");
        intRoman.put(50, "L");
        intRoman.put(90, "XC");
        intRoman.put(100, "C");
        String roman= "";
        int current;
        while (number != 0) {
            current = intRoman.floorKey(number);
            roman += intRoman.get(intRoman.floorKey(number));
            number -= intRoman.floorKey(number);
        }
    return roman;
    }


    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter your expression: ");
        input = s.nextLine();

        System.out.println("Answer is: " + calc(input));


    }
}

