import java.util.Scanner;

public class SymbolsSort {
    public static void main(String args[]) {
        System.out.print("Enter a value");
        Scanner scan = new Scanner(System.in);
        String newScan = scan.nextLine();
        String letter = newScan.replaceAll("[^a-zA-Z]", "");
        System.out.println(letter.toUpperCase());
        String letterUpperCase = letter.toUpperCase();
        int frequency[] = new int[256];
        for (int i = 0; i < letterUpperCase.length(); i++) {
            frequency[(int) letterUpperCase.charAt(i)]++;
        }

        for (int i = 0; i < frequency.length; i++) {
            if (frequency[i] != 0) {
                System.out.println((char) i + " - " + frequency[i]);

            }

        }
    }
}

