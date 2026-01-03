import java.util.Random;

public class GameOfLife {

    public static void main(String[] args) {
        int n = 10; // Grid size
        int[][] grid = new int[n][n];
        Random r = new Random();

        // 1. INITIALIZATION: Fill the grid randomly
        System.out.println("--- INITIAL STATE ---");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = r.nextInt(2); // Generates 0 (Dead) or 1 (Alive)
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------");

        // 2. GAME LOOP (INFINITE)
        while (true) {
            int[][] nextGrid = new int[n][n]; // Create a clean state for the next generation

            // Iterate through every cell
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    
                    // --- COUNT LIVE NEIGHBORS ---
                    int liveNeighbors = 0;
                    
                    // Check the 3x3 area around the cell
                    for (int k = i - 1; k <= i + 1; k++) {
                        for (int m = j - 1; m <= j + 1; m++) {
                            
                            // Boundary Check: Ensure we don't go out of grid limits
                            if (k >= 0 && k < n && m >= 0 && m < n) {
                                // Do not count the cell itself, only neighbors
                                if (!(k == i && m == j)) {
                                    if (grid[k][m] == 1) {
                                        liveNeighbors++;
                                    }
                                }
                            }
                        }
                    }

                    // --- APPLY CONWAY'S RULES ---
                    
                    // Rule 1 & 2: Underpopulation (<2) or Overpopulation (>3) -> Cell Dies
                    if (grid[i][j] == 1) {
                        if (liveNeighbors < 2 || liveNeighbors > 3) {
                            nextGrid[i][j] = 0;
                        } else {
                            nextGrid[i][j] = 1; // Lives on to the next generation
                        }
                    } 
                    // Rule 3: Reproduction (Exactly 3 neighbors) -> Cell is Born
                    else {
                        if (liveNeighbors == 3) {
                            nextGrid[i][j] = 1;
                        } else {
                            nextGrid[i][j] = 0;
                        }
                    }
                }
            }

            // --- PRINT THE NEXT GENERATION ---
            for (int i = 0; i < nextGrid.length; i++) {
                for (int j = 0; j < nextGrid[i].length; j++) {
                    System.out.print(nextGrid[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("---------------------");

            // --- UPDATE STATE ---
            grid = nextGrid; // The new generation becomes the current one

            // --- PAUSE (1 Second) ---
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
