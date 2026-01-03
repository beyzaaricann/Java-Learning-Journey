public class SpiralMatrix {

    public static void main(String[] args) {
        int n = 5; // Matrix size (5x5)
        int[][] matrix = new int[n][n];

        // Define boundaries
        int top = 0;          // Top wall (formerly 'tab')
        int bottom = n - 1;   // Bottom wall
        int left = 0;         // Left wall
        int right = n - 1;    // Right wall

        int counter = 1;      // The value to fill in the cells

        // Continue until the matrix is full
        while (counter <= n * n) {

            // 1. Traverse Right (Fill the Top Wall)
            for (int i = left; i <= right; i++) {
                matrix[top][i] = counter;
                counter++;
            }
            top++; // Move the top wall down

            // 2. Traverse Down (Fill the Right Wall)
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = counter;
                counter++;
            }
            right--; // Move the right wall left

            // 3. Traverse Left (Fill the Bottom Wall)
            for (int i = right; i >= left; i--) {
                matrix[bottom][i] = counter;
                counter++;
            }
            bottom--; // Move the bottom wall up

            // 4. Traverse Up (Fill the Left Wall)
            for (int i = bottom; i >= top; i--) {
                matrix[i][left] = counter;
                counter++;
            }
            left++; // Move the left wall right
        }

        // --- PRINT THE RESULT ---
        System.out.println("--- SPIRAL MATRIX OUTPUT ---");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                // Use tab (\t) for better alignment
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println(); // New line after each row
        }
    }
}
