import java.util.Scanner;

public class CaesarCipher {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // 1. GET USER INPUT
        System.out.println("--- CAESAR CIPHER TOOL ---");
        System.out.print("Enter message to encrypt: ");
        // Read the full line and convert to UPPERCASE for simpler logic
        String plainText = scan.nextLine().toUpperCase();

        System.out.print("Enter shift key (e.g., 3): ");
        int key = scan.nextInt();

        System.out.print("Encrypted Message: ");

        // 2. ENCRYPTION LOOP
        // Convert string to char array to manipulate individual characters
        char[] chars = plainText.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            
            char current = chars[i];

            // A) SPACE CHECK
            // If it's a space, don't encrypt it, just print as is.
            if (current == ' ') {
                System.out.print(" ");
                continue; 
            }

            // B) SHIFT LOGIC (The Algorithm)
            // Basic shift: Add key to the ASCII value
            char encrypted = (char) (current + key);

            // C) WRAP AROUND CHECK (Overflow)
            // If the character goes beyond 'Z' (ASCII 90), wrap back to 'A'
            // Logic: Subtract 26 (the length of the alphabet)
            if (encrypted > 'Z') {
                encrypted = (char) (encrypted - 26);
            }

            // Print the result immediately
            System.out.print(encrypted);
        }
        
        System.out.println(); // New line at the end
        System.out.println("--------------------------");
    }
}
