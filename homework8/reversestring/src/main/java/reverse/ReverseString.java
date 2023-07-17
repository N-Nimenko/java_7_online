package reverse;

public final class ReverseString {
    private ReverseString() {
    }

    public static String reverse(String src) {
        StringBuilder result = new StringBuilder();
        String[] words = src.split(" ");
        for (String word : words) {
            StringBuilder reversedWord = reverseWord(word);
            result.append(reversedWord).append(" ");
        }
        return result.toString().trim();
    }

    private static StringBuilder reverseWord(String word) {
        StringBuilder reversed = new StringBuilder();
        for (int i = word.length() - 1; i >= 0; i--) {
            reversed.append(word.charAt(i));
        }
        return reversed;
    }

    public static String reverseDest(String src, String dest) {
        int index = src.indexOf(dest);
        if (index > 0) {
            StringBuilder newSrc = new StringBuilder(reverse(dest));
            return src.replace(dest, newSrc);
        }
        return "Error occurred";
    }

    public static String ultimateReverse(String src, int firstIndex, int lastIndex) {
        if (firstIndex >= 0 && lastIndex > 0 && lastIndex < src.length() && firstIndex < lastIndex) {
            StringBuilder toReverse = new StringBuilder(src.substring(firstIndex, lastIndex + 1));
            StringBuilder reversedString = new StringBuilder(reverse(toReverse.toString()));
            return src.replace(toReverse, reversedString);
        }
        return "Error occurred";
    }
}