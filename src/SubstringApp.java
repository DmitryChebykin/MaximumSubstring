import java.util.Arrays;
import java.util.Scanner;

public class SubstringApp {
    public static void main(String[] args) {
        System.out.println("Программа находит подстроки c максимальным количеством одинаковых символов.");
        System.out.println("Определяет длину таких подстрок, их количество, если их несколько.");
        System.out.println("И выводит вашу строку с подсветкой таких подстрок.");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Напечатать примеры с тестовыми строками? (Y|N):");
        String answer = scanner.nextLine();

        if (Character.toLowerCase(answer.charAt(0)) == 'y') {
            SubstringChecker substringChecker = new SubstringChecker();
            int i = 0;

            for (String s : getDemoStringArray()) {
                i++;
                substringChecker.setCheckedString(s);
                System.out.println("Строка " + i);

                printStringChecking(substringChecker);
            }
        }

        System.out.println("Введите произвольную строку для анализа:");
        String string = scanner.nextLine();

        SubstringChecker substringChecker = new SubstringChecker(string);

        printStringChecking(substringChecker);
    }

    private static void printStringChecking(SubstringChecker substringChecker) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Строка и строка с подсветкой максимальных подстрок.");
        System.out.println("(пробельный символ заменяется на квадрат):");
        System.out.println(substringChecker.getCheckedString());
        System.out.println(substringChecker.getColoredSubstring());
        System.out.printf("Размер подстрок - %d, количество подстрок - %d", substringChecker.getMaxSubstringLength(), substringChecker.getMaxSubstringsCount());
        System.out.println();
        System.out.println("Подстроки отдельно (пробельный символ заменяется на квадрат):");
        System.out.println(Arrays.toString(substringChecker.getMaxSubstrings()));
        System.out.println("----------------------------------------------------------------");
        System.out.println();
    }

    private static String[] getDemoStringArray() {
        return new String[]{
                "aaasdfdvctttttttttyyyppppppppp89er999999999---+6543         ",
                "111111 222222 222222 ffffff;lklkl#### ';l;';l",
                "     12  33333  22222  2  dsd  33333 ",
                "", "122222222222222222222222222",
                "123456789"
        };
    }
}