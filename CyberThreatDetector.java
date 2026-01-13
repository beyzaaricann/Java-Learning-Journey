import java.util.Random;

public class CyberThreatDetector {

    public static void main(String[] args) {
        
        Random r = new Random();
        
        // 1. INITIALIZE GRID (10x10 Data Stream)
        char[][] grid = new char[10][10];
        
        // Fill the grid with random characters
        System.out.println("--- INCOMING DATA STREAM ---");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // Generate random char between A-Z
                grid[i][j] = (char) ('A' + r.nextInt(26));
                
                // FORCE TEST: Let's inject some 'X's manually to test the logic
                // (Optional: You can remove this if you want pure luck)
                if (r.nextInt(10) < 2) { 
                    grid[i][j] = 'X'; 
                }
                
                System.out.print(grid[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println("----------------------------\n");

        int threatCount = 0;

        // 2. SCANNING ALGORITHMS (Sliding Window)
        
        // A) HORIZONTAL SCAN (Row by Row)
        // Limit col < 8 because we look at [j], [j+1], [j+2]
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                if (grid[i][j] == 'X' && grid[i][j+1] == 'X' && grid[i][j+2] == 'X') {
                    System.out.println("⚠️ HORIZONTAL THREAT detected at Row: " + i + ", Col: " + j);
                    threatCount++;
                }
            }
        }

        // B) VERTICAL SCAN (Column by Column)
        // Limit row < 8 because we look at [i], [i+1], [i+2]
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                if (grid[i][j] == 'X' && grid[i+1][j] == 'X' && grid[i+2][j] == 'X') {
                    System.out.println("⚠️ VERTICAL THREAT detected at Row: " + i + ", Col: " + j);
                    threatCount++;
                }
            }
        }

        // C) DIAGONAL SCAN (Top-Left to Bottom-Right)
        // Limit both row < 8 and col < 8
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (grid[i][j] == 'X' && grid[i+1][j+1] == 'X' && grid[i+2][j+2] == 'X') {
                    System.out.println("⚠️ DIAGONAL THREAT (\\) detected at Row: " + i + ", Col: " + j);
                    threatCount++;
                }
            }
        }

        // D) ANTI-DIAGONAL SCAN (Top-Right to Bottom-Left)
        // Limit row < 8, but start col from 2 because we look BACKWARDS (j, j-1, j-2)
        for (int i = 0; i < 8; i++) {
            for (int j = 2; j < 10; j++) {
                if (grid[i][j] == 'X' && grid[i+1][j-1] == 'X' && grid[i+2][j-2] == 'X') {
                    System.out.println("⚠️ ANTI-DIAGONAL THREAT (/) detected at Row: " + i + ", Col: " + j);
                    threatCount++;
                }
            }
        }

        // 3. FINAL REPORT
        System.out.println("\n----------------------------");
        System.out.println("SCAN COMPLETE.");
        System.out.println("TOTAL THREATS FOUND: " + threatCount);
        if (threatCount > 0) {
            System.out.println("STATUS: SYSTEM COMPROMISED! 🔴");
        } else {
            System.out.println("STATUS: SYSTEM SECURE 🟢");
        }
    }
}
