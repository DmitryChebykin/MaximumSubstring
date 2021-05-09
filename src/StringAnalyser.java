import java.util.Scanner;

public class StringAnalyser {
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {
        System.out.println("Программа находит подстроки c максимальным количеством одинаковых символов.");
        System.out.println("Определяет длину таких подстрок, их количество, если их несколько.");
        System.out.println("И выводит вашу строку с подсветкой таких подстрок.");
        System.out.println("Ниже пример строки и вывода этой же строки с подсветкой:");
        System.out.println("aaasdfdvctttttttttyyyppppppppp89er999999999---+6543");
        System.out.println("aaasdfdvc" + ANSI_PURPLE + "ttttttttt" + ANSI_RESET + "yyy" + ANSI_PURPLE + "ppppppppp" + ANSI_RESET + "89er" + ANSI_PURPLE + "999999999" + ANSI_RESET + "---+6543");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите произвольную строку для анализа:");
        String string = scanner.nextLine();

        char currentChar = string.charAt(0);

        int maxSubStringLength = 1;
        int currentSubStringLength = 1;

        StringBuilder maxSubStringsEndSymbolsIndexesLog = new StringBuilder(); // If there are several substrings with the maximum length, then we will remember their position in the string by the last character

        for (int i = 1; i < string.length(); i++) {
            char nextChar = string.charAt(i);

            if (Character.toLowerCase(nextChar) == Character.toLowerCase(currentChar)) {
                currentSubStringLength++;
                currentChar = nextChar;
            } else {
                currentChar = string.charAt(i);

                if (currentSubStringLength >= maxSubStringLength) {

                    if (currentSubStringLength > maxSubStringLength) {
                        maxSubStringsEndSymbolsIndexesLog = new StringBuilder(); // Resetting the log if the new substring is larger than the previous substrings
                    }

                    maxSubStringLength = currentSubStringLength;

                    if (maxSubStringLength != 1) { // We do not save single characters substring to the log
                        maxSubStringsEndSymbolsIndexesLog.append(i - 1).append(System.lineSeparator());
                    }
                }

                currentSubStringLength = 1;
            }
        }

        scanner = new Scanner(maxSubStringsEndSymbolsIndexesLog.toString());

        StringBuilder coloredStringBuilder = new StringBuilder();

        int beginIndex = 0;
        int subStringsCount = 0;

        while (scanner.hasNext()) {
            subStringsCount++;
            int endIndex = scanner.nextInt();
            String nonColoredSubString = string.substring(beginIndex, endIndex - maxSubStringLength + 1);
            String coloredSubString = ANSI_PURPLE + string.substring(endIndex - maxSubStringLength + 1, endIndex + 1) + ANSI_RESET;
            beginIndex = endIndex + 1;
            coloredStringBuilder.append(nonColoredSubString).append(coloredSubString);
        }

        if (beginIndex < string.length()) {
            coloredStringBuilder.append(string.substring(beginIndex));
        }

        if (subStringsCount == 0) {
            System.out.println("В вашей строке нет подстрок c повторяющимися символами.");
        } else {
            System.out.println("Максимальный размер подстроки c одинаковыми символами в веденной строке равен - " + maxSubStringLength);
            System.out.println("Количество подстрок равно " + subStringsCount);
            System.out.println("Вывод начальной и обработанной строки с подсветкой максимальных подстрок:");
            System.out.println(string);
            System.out.println(coloredStringBuilder);
        }
    }
}