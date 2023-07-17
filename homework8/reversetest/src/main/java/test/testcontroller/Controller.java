package test.testcontroller;

import reverse.ReverseString;

public class Controller {
    public void start() {
        String ultimateReverse = ReverseString.ultimateReverse("hello world", 3, 7 );
        System.out.println(ultimateReverse);

        String reversedDest = ReverseString.reverseDest("hello world", "worl" );
        System.out.println(reversedDest);

        String reversed = ReverseString.reverse("hello world");
        System.out.println(reversed);
    }
}
