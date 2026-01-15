import java.util.Scanner;
public class LuhnValidator {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("--- CREDIT CARD VALIDATOR (Luhn Algorithm) ---");
        System.out.print("Enter Card Number: ");
        
        // Kullanıcı boşluklu girerse (örn: 1234 5678) boşlukları siliyoruz.
        String cardNo = scan.nextLine().replace(" ", "");

        int totalSum = 0;
        
        // TOGGLE SWITCH (Bizim "Elektrik Düğmesi")
        // Sondan başladığımız için ilk rakam (Check Digit) çarpılmaz -> false
        boolean isSecondDigit = false;

        // Loop backwards (Sondan başa doğru)
        for (int i = cardNo.length() - 1; i >= 0; i--) {
            
            // 1. Get current character and convert to integer
            char c = cardNo.charAt(i);
            int digit = c - '0'; // ASCII conversion trick ('5' -> 5)

            // 2. Check if we need to double this digit
            if (isSecondDigit == true) {
                digit = digit * 2;

                // Logic: If result > 9, subtract 9 (Example: 18 -> 9, 10 -> 1)
                if (digit > 9) {
                    digit = digit - 9;
                }
            }

            // 3. Add to total sum
            totalSum = totalSum + digit;

            // 4. FLIP THE SWITCH (! mantığı)
            // True ise False, False ise True yap.
            isSecondDigit = !isSecondDigit;
        }

        // Final Validation Check
        if (totalSum % 10 == 0) {
            System.out.println("✅ VALID CARD NUMBER (Sum: " + totalSum + ")");
        } else {
            System.out.println("❌ INVALID CARD NUMBER (Sum: " + totalSum + ")");
        }
        
        scan.close();
    }
}
