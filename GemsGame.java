import java.util.Random;
import java.util.Scanner;

/**
 * Gems Hunter Game
 * A simple console-based 2D array game where the player tries to collect gems
 * while avoiding traps.
 * * Author: Beyza Arıcan
 */
public class GemsGame {

    public static void main(String[] args) {
        Random r = new Random();
        Scanner scan = new Scanner(System.in);

        int gemsFound = 0; // Previously 'counter' for gems
        int score = 0;
        int n = 5; // Grid size

        // visibleGrid: What the user sees (masked with '*')
        char[][] visibleGrid = new char[n][n];
        
        // realGrid: Backend grid (0: Empty, 1: Gem, 2: Trap)
        int[][] realGrid = new int[n][n];

        // Initialize the visible grid with '*'
        System.out.println("--- Welcome to Gems Hunter! ---");
        for (int i = 0; i < visibleGrid.length; i++) {
            for (int j = 0; j < visibleGrid[i].length; j++) {
                visibleGrid[i][j] = '*';
                System.out.print(visibleGrid[i][j] + " ");
            }
            System.out.println("");
        }

        // 1. Place Gems (Randomly place 3 gems)
        int placedGems = 0;
        while (placedGems < 3) {
            int row = r.nextInt(n);
            int col = r.nextInt(n);
            
            if (realGrid[row][col] == 0) {
                realGrid[row][col] = 1; // 1 represents a Gem
                placedGems++;
            }
        }

        // 2. Place Traps (Randomly place 1 trap)
        int placedTraps = 0;
        while (placedTraps < 1) {
            int row = r.nextInt(n);
            int col = r.nextInt(n);
            
            if (realGrid[row][col] == 0) {
                realGrid[row][col] = 2; // 2 represents a Trap
                placedTraps++;
            }
        }

        // 3. Main Game Loop
        do {
            System.out.println("\nEnter row (0-4): ");
            int row = scan.nextInt();
            
            System.out.println("Enter column (0-4): ");
            int col = scan.nextInt();

            // Check boundaries (Optional but good practice)
            if (row < 0 || row >= n || col < 0 || col >= n) {
                System.out.println("Invalid coordinates! Please enter values between 0 and 4.");
                continue;
            }

            // Check the content of the selected cell
            if (realGrid[row][col] == 1) {
                // User found a gem
                visibleGrid[row][col] = 'G';
                System.out.println("Congratz! You found a gem!");
                score += 10; // Example: 10 points per gem
                gemsFound++;
            } else if (realGrid[row][col] == 0) {
                // User found an empty area
                visibleGrid[row][col] = '_';
                System.out.println("This area is empty.");
            } else {
                // User stepped on a trap (2)
                System.out.println("GAME OVER! You stepped on a trap.");
                System.out.println("Your Final Score: " + score);
                break; // Exit the loop immediately
            }

            // Print the current state of the board
            for (int i = 0; i < visibleGrid.length; i++) {
                for (int j = 0; j < visibleGrid[i].length; j++) {
                    System.out.print(visibleGrid[i][j] + " ");
                }
                System.out.println("");
            }

            // Check if user collected all gems
            if (gemsFound == 3) {
                System.out.println("\nYOU WON! You collected all the gems!");
                System.out.println("Your Final Score: " + score);
            }

        } while (gemsFound < 3);
        
        scan.close();
    }
}
