import java.util.Objects;
import java.util.Scanner;

public class SubstringChecker {
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String SMALL_SQUARE = "\u25aa";
    private String checkedString;

    public SubstringChecker() {
        checkedString = "";
    }

    public SubstringChecker(String string) {
        checkedString = Objects.requireNonNull(string, "string must not be null");
    }

    public String getCheckedString() {
        return checkedString;
    }

    public void setCheckedString(String checkedString) {
        this.checkedString = Objects.requireNonNull(checkedString, "string must not be null");
    }

    public String getColoredSubstring() {
        int[] maxSubstringsEndIndexes = getMaxSubstringsEndIndexes();
        int beginIndex = 0;
        int maxSubstringLength = getMaxSubstringLength();

        StringBuilder coloredStringBuilder = new StringBuilder();

        for (int endIndex : maxSubstringsEndIndexes) {
            String nonColoredSubString = checkedString.substring(beginIndex, endIndex - maxSubstringLength + 1);
            String coloredSubString = ANSI_PURPLE + getReplacedWhiteSpacesString(checkedString.substring(endIndex - maxSubstringLength + 1,
                    endIndex + 1)) + ANSI_RESET;

            beginIndex = endIndex + 1;

            coloredStringBuilder.append(nonColoredSubString).append(coloredSubString);
        }

        if (beginIndex < checkedString.length()) {
            coloredStringBuilder.append(checkedString.substring(beginIndex));
        }

        return coloredStringBuilder.toString();
    }

    public int getMaxSubstringLength() {
        if (checkedString.length() == 0) {

            return 0;
        }

        char currentChar = checkedString.charAt(0);
        int maxSubstringLength = 1;
        int currentSubstringLength = 1;

        for (int i = 1; i < checkedString.length(); i++) {
            char nextChar = checkedString.charAt(i);

            if (Character.toLowerCase(nextChar) == Character.toLowerCase(currentChar)) {
                currentSubstringLength++;

                currentChar = nextChar;
            } else {
                currentChar = checkedString.charAt(i);

                if (currentSubstringLength >= maxSubstringLength) {
                    maxSubstringLength = currentSubstringLength;
                }

                currentSubstringLength = 1;
            }
        }

        if (currentSubstringLength >= maxSubstringLength) {
            maxSubstringLength = currentSubstringLength;
        }

        return maxSubstringLength;
    }

    public String[] getMaxSubstrings() {
        if (checkedString.length() == 0) {
            return new String[0];
        }

        String[] maxSubstrings = new String[getMaxSubstringsCount()];
        int[] maxSubstringsEndIndexes = getMaxSubstringsEndIndexes();
        int maxSubstringLength = getMaxSubstringLength();

        for (int i = 0; i < getMaxSubstringsCount(); i++) {
            maxSubstrings[i] = getReplacedWhiteSpacesString(checkedString.substring((maxSubstringsEndIndexes[i] + 1 - maxSubstringLength), maxSubstringsEndIndexes[i] + 1));
        }
        return maxSubstrings;
    }

    public int getMaxSubstringsCount() {
        if (checkedString.length() == 0) {
            return 0;
        }

        char currentChar = checkedString.charAt(0);

        int maxSubstringLength = 1;
        int currentSubstringLength = 1;
        int maxSubstringsCount = 0;

        for (int i = 1; i < checkedString.length(); i++) {
            char nextChar = checkedString.charAt(i);

            if (Character.toLowerCase(nextChar) == Character.toLowerCase(currentChar)) {
                currentSubstringLength++;

                currentChar = nextChar;
            } else {
                currentChar = checkedString.charAt(i);

                if (currentSubstringLength >= maxSubstringLength) {
                    if (currentSubstringLength > maxSubstringLength) {
                        maxSubstringsCount = 0;    // Resetting count if the new substring is larger than the previous substrings
                    }

                    maxSubstringsCount++;

                    maxSubstringLength = currentSubstringLength;
                }

                currentSubstringLength = 1;
            }
        }

        if (currentSubstringLength >= maxSubstringLength) {
            if (currentSubstringLength > maxSubstringLength) {
                maxSubstringsCount = 1;
            } else {
                maxSubstringsCount++;
            }
        }

        return maxSubstringsCount;
    }

    public int[] getMaxSubstringsEndIndexes() {
        if (checkedString.length() == 0) {
            return new int[0];
        }

        char currentChar = checkedString.charAt(0);
        int maxSubstringLength = 1;
        int currentSubstringLength = 1;

        StringBuilder maxSubStringsEndSymbolsIndexesLog = new StringBuilder();

        for (int i = 1; i < checkedString.length(); i++) {
            char nextChar = checkedString.charAt(i);

            if (Character.toLowerCase(nextChar) == Character.toLowerCase(currentChar)) {
                currentSubstringLength++;

                currentChar = nextChar;
            } else {
                currentChar = checkedString.charAt(i);

                if (currentSubstringLength >= maxSubstringLength) {
                    if (currentSubstringLength > maxSubstringLength) {
                        maxSubStringsEndSymbolsIndexesLog = new StringBuilder();    // Resetting the log if the new substring is larger than the previous substrings
                    }

                    maxSubstringLength = currentSubstringLength;

                    maxSubStringsEndSymbolsIndexesLog.append(i - 1).append(System.lineSeparator());
                }

                currentSubstringLength = 1;
            }
        }

        if (currentSubstringLength >= maxSubstringLength) {
            if (currentSubstringLength > maxSubstringLength) {
                maxSubStringsEndSymbolsIndexesLog = new StringBuilder();    // Resetting the log if the new substring is larger than the previous substrings
            }

            maxSubStringsEndSymbolsIndexesLog.append(checkedString.length() - 1).append(System.lineSeparator());
        }

        int[] maxSubstringsEndIndexes = new int[getMaxSubstringsCount()];

        if (maxSubstringsEndIndexes.length != 0) {
            Scanner scanner = new Scanner(maxSubStringsEndSymbolsIndexesLog.toString());

            for (int i = 0; i < maxSubstringsEndIndexes.length; i++) {
                if (scanner.hasNext()) {
                    maxSubstringsEndIndexes[i] = Integer.parseInt(scanner.nextLine());
                }
            }
        }

        return maxSubstringsEndIndexes;
    }

    private String getReplacedWhiteSpacesString(String s) {
        if ((s == null) || (s.length() == 0)) {
            return s;
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (Character.isWhitespace(s.charAt(i))) {
                stringBuilder.append(SMALL_SQUARE);
            } else {
                stringBuilder.append(s.charAt(i));
            }
        }

        return stringBuilder.toString();
    }
}