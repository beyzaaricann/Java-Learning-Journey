import java.util.Random;

public class MinesweeperLogic {

    public static void main(String[] args) {
        
        Random r = new Random();
        int size = 10; // 10x10 Grid Size
        String[][] map = new String[size][size];

        // 1. INITIALIZE MAP
        // Fill the grid with safe zones ("-")
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = "-";
            }
        }

        // 2. PLACE MINES
        // Randomly place 20 mines ("*") on the map
        int minesPlaced = 0;
        while (minesPlaced < 20) {
            int row = r.nextInt(size);
            int col = r.nextInt(size);

            // Check if the cell is already occupied
            if (!map[row][col].equals("*")) {
                map[row][col] = "*";
                minesPlaced++;
            }
        }

        // 3. NEIGHBOR COUNTING ALGORITHM
        // Iterate through every cell in the matrix
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                // If the current cell is safe ("-"), count neighboring mines
                if (map[i][j].equals("-")) {
                    
                    int mineCounter = 0;

                    // --- KERNEL SCAN (3x3 Area) ---
                    // Check all 8 neighbors: from [i-1][j-1] to [i+1][j+1]
                    for (int row = i - 1; row <= i + 1; row++) {
                        for (int col = j - 1; col <= j + 1; col++) {

                            // BOUNDARY CHECK
                            // Ensure the neighbor coordinates are within the map limits
                            if (row >= 0 && row < size && col >= 0 && col < size) {
                                
                                // Increment counter if a mine is found
                                if (map[row][col].equals("*")) {
                                    mineCounter++;
                                }
                            }
                        }
                    }
                    // Update the cell with the total count (convert int to String)
                    map[i][j] = String.valueOf(mineCounter);
                }
            }
        }

        // 4. PRINT THE FINAL MAP
        // Display the radar results in the console
        System.out.println("--- MINESWEEPER RADAR ---");
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // Print cell content with spacing
                System.out.print(map[i][j] + "  "); 
            }
            // Move to the next line after finishing a row
            System.out.println(); 
        }
    }
}
