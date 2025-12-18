package day1_loop;

import java.util.Scanner;

public class Day1_loop {

    public static void main(String[] args) {
        /*
         * QUESTION: Sum of Positive Numbers
         * ----------------------------------
         * Rule: The loop should continue as long as the user enters a positive number.
         * It should stop if the user enters 0 or a negative number.
         * Action: Calculate the sum of all the positive numbers entered.
         * Output: Print the total sum to the console when the loop finishes.
         */

        Scanner scan = new Scanner(System.in);

        int number;
        int totalSum = 0;

        System.out.println("--- Positive Number Calculator ---");

        do {
            
            System.out.println("Please enter a number to add to the sum:");
            System.out.println("(Enter 0 or a negative number to stop)");
            
            number = scan.nextInt();
         
            if (number > 0) {
                totalSum += number;
            }

        } while (number > 0);

        System.out.println("Total Sum: " + totalSum);
        scan.close();
    }
}
