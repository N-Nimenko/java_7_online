import java.util.Scanner;

public class EndOfLesson{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the lesson you want to know about ");
        int a = scanner.nextInt();
        a = a * 45 + (a / 2) * 5 + ((a + 1) / 2 - 1) * 15;
        System.out.println("This lesson ends at " + (a / 60 + 9 + " " + a % 60));
    }
}
