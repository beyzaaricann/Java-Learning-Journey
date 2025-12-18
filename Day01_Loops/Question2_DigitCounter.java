package day1_loop;

import java.util.Scanner;

public class Question2_DigitCounter {

    public static void main(String[] args) {
        /*
         * QUESTION: Digit Counter (Math Logic)
         * -------------------------------------
         * Rule: Ask the user for an integer number.
         * Action: Use a do-while loop to count how many digits the number has.
         * Hint: Divide the number by 10 in each iteration and increment a counter.
         * Output: Print the total digit count to the console.
         * Example: Input: 4589 -> Output: 4 digits
         */
         
        Scanner scan = new Scanner(System.in);
        
        System.out.println("--- Digit Counter ---");
        System.out.println("Please enter an integer number:");
        
        int number = scan.nextInt();

        int counter = 0;
        
        // Sayının orijinal halini ekrana yazdırmak için bir kopyasını tutabilirsin (Opsiyonel)
        int originalNumber = number; 

        do {
            number = number / 10;
            counter++;
        } while (number != 0); 

        System.out.println(originalNumber + " has " + counter + " digits.");
        
        scan.close();
    }
}
