import java.util.Scanner;

public class StringCalculator {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("!!!Строки должны быть выделены двойными кавычками!!!");
        System.out.println("Введите выражение: ");
        String input = sc.nextLine();
        System.out.println(calculate(input));

    }

    public static String calculate(String input) throws Exception {
        exceptions(input);
        input = deleteQuotes(input);
        if (input.contains(" + ")) {
            input = addition(input);
        }
        else if (input.contains(" - ")) {
            input = subtraction(input);
        }
        else if (input.contains(" * ")) {
            input = multiplication(input);
        }
        else if (input.contains(" / ")) {
            input = division(input);
        }
        if (input.length() > 40) {
            input = input.substring(0, 40);
            input = input + "...";
        }
        input = addQuotes(input);

        return input;
    }

    public static void exceptions(String input) throws Exception {
        if (input.contains(" + ")) {
            if (!input.contains("\" + \"")) {
                throw new Exception("Складывать можно только строки!");
            } else return;
        }
        if (input.contains(" - ")) {
            if (!input.contains("\" - \"")) {
                throw new Exception("Вычитать можно только строку из строки!");
            } else return;
        }
        if (input.contains(" * ")) {
            if (!input.contains("\" * ")) {
                throw new Exception("Умножать можно только строку на число!");
            } else return;

        }
        if (input.contains(" / ")) {
            if (!input.contains("\" / ")) {
                throw new Exception("Делить можно только строку на число!");
            } else return;
        }
        else throw new Exception("Неверный ввод!");
    }

    public static String deleteQuotes(String input) {
        input = input.replace("\"", "");
        return input;
    }

    public static String addQuotes(String input) {
        input = "\"" + input + "\"";
        return input;
    }


    public static String addition(String input) throws Exception {
        String result;
        String[] parts = input.split(" \\+ ");
        if (parts[0].length() > 10 || parts[1].length() > 10){
            throw new Exception("Подаваемая строка длиннее 10ти символов!");
        }
        result = (parts[0] + parts[1]);
        return result;
    }

    public static String subtraction(String input) throws Exception {
        StringBuilder sb = new StringBuilder();
        String[] parts = input.split(" - ");
        if (parts[0].length() > 10 || parts[1].length() > 10){
            throw new Exception("Подаваемая строка длиннее 10ти символов!");
        }
        int index = parts[0].indexOf(parts[1]);
        if (parts[0].contains(parts[1])){
            for (int i = 0; i < index; i++) {
                sb.append(input.charAt(i));
            }
        }
        else sb.append(parts[0]);
        return sb.toString();
    }

    public static String multiplication(String input) throws Exception {
        StringBuilder sb = new StringBuilder();
        String[] parts = input.split(" \\* ");
        if (parts[0].length() > 10){
            throw new Exception("Подаваемая строка длиннее 10ти символов!");
        }
        if (Integer.parseInt(parts[1]) > 10){
            throw new Exception("Множитель больше 10ти!");
        }
        int x = Integer.parseInt(parts[1]) * parts[0].length();
        while (sb.length() < x){
            for (int i = 0; i < parts[0].length(); i++) {
                sb.append(parts[0].charAt(i));
            }
        }
        return sb.toString();
    }

    public static String division(String input) throws Exception {
        StringBuilder sb = new StringBuilder();
        String[] parts = input.split(" / ");
        if (parts[0].length() > 10){
            throw new Exception("Подаваемая строка длиннее 10ти символов!");
        }
        if (Integer.parseInt(parts[1]) > 10){
            throw new Exception("Делитель больше 10ти!");
        }
        int x = parts[0].length() / Integer.parseInt(parts[1]);
        for (int i = 0; i < x; i++) {
            sb.append(parts[0].charAt(i));
        }
        return sb.toString();
    }

}
