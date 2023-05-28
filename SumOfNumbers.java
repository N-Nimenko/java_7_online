import java.util.Scanner;

public class SumOfNumbers {
    public static void main(String args[]) {
        int n, sum = 0;
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter a value");
        String number = myObj.nextLine();
        String numberOnly = number.replaceAll("[^0-9]", "");
        int numStr = Integer.parseInt(numberOnly);
        while(numStr > 0) {
            n = numStr % 10;
            sum = sum + n;
            numStr = numStr / 10;
        }
        System.out.println("Sum of Digits:"+sum);
    }
}

