import java.util.Scanner;

/**
 * Cinema Booking System
 * A simple console-based reservation system using 2D Arrays.
 * * @author Beyza Arıcan
 */
public class CinemaBooking {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        // 5 Rows (A-E), 7 Columns (0-6)
        char[][] seats = new char[5][7];
        
        // --- INITIALIZATION STEP ---
        
        // Print Column Headers (0 1 2...)
        System.out.print("  "); // Corner spacing
        for (int k = 0; k < 7; k++) {
            System.out.print(k + "\t");
        }
        System.out.println();

        // Initialize seats with '0' (Empty) and print the initial board
        for (int i = 0; i < 5; i++) {
            System.out.print((char)('A' + i) + " "); // Row Header (A B C...)
            for (int j = 0; j < 7; j++) {
                seats[i][j] = '0';
                System.out.print(seats[i][j] + "\t");   
            }
            System.out.println();
        }

        // --- GAME LOOP ---
        while (true) {
            System.out.println("\n--- Make a Reservation ---");
            System.out.println("Enter row (A-E): ");
            char rowChar = scan.next().toUpperCase().charAt(0); // Convert to uppercase automatically
            
            System.out.println("Enter column (0-6): ");
            int colNum = scan.nextInt();

            // BOUNDARY CHECK: Prevent crashing if user enters Z or 9
            if (rowChar < 'A' || rowChar > 'E' || colNum < 0 || colNum > 6) {
                System.out.println("Invalid Selection! Please enter a valid row (A-E) and column (0-6).");
                continue; // Skip the rest of the loop and ask again
            }

            // Convert Row Character to Index (e.g., 'A' -> 0, 'B' -> 1)
            int rowIndex = rowChar - 'A';

            // Check availability
            if (seats[rowIndex][colNum] == '0') {
                seats[rowIndex][colNum] = 'X'; // Mark as booked
                System.out.println("Success! Seat " + rowChar + colNum + " reserved.");
            } else {
                System.out.println("Warning: Seat " + rowChar + colNum + " is already taken!");
            }

            // --- RE-PRINT BOARD ---
            System.out.println("\nCurrent Cinema Status:");
            
            // Print Headers again
            System.out.print("  ");
            for (int k = 0; k < 7; k++) {
                System.out.print(k + "\t");
            }
            System.out.println();

            // Print Grid
            for (int i = 0; i < 5; i++) {
                System.out.print((char)('A' + i) + " ");
                for (int j = 0; j < 7; j++) {
                    System.out.print(seats[i][j] + "\t");   
                }
                System.out.println();
            }
        }
    }
}
