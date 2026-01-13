import java.util.Random;

public class BugHunter {

    public static void main(String[] args) {
        
        Random r = new Random();
        int size = 11; // 11x11 Grid Size
        String[][] surface = new String[size][size];

        // 1. INITIALIZE GRID (Clean the room)
        // Haritayı boşlukla dolduruyoruz
        System.out.println("--- ROOM MAP GENERATING ---");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                surface[i][j] = " "; // Empty floor
            }
        }

        // 2. PLACE DUST (*)
        // Rastgele 20 adet toz yerleştir
        int dustCount = 0;
        while (dustCount < 20) {
            int row = r.nextInt(size);
            int col = r.nextInt(size);
            
            // Eğer yer boşsa toz koy
            if (surface[row][col].equals(" ")) {
                surface[row][col] = "*";
                dustCount++;
            }
        }

        // 3. PLACE WALLS (D)
        // Rastgele 5 adet duvar/engel yerleştir
        int wallCount = 0;
        while (wallCount < 5) {
            int row = r.nextInt(size);
            int col = r.nextInt(size);
            
            // Eğer yer boşsa (toz veya başka duvar yoksa) duvar koy
            if (surface[row][col].equals(" ")) {
                surface[row][col] = "D";
                wallCount++;
            }
        }

        // 4. PLACE ROBOT (R)
        // Robotu tam merkeze koyuyoruz
        int rRow = 5; 
        int rCol = 5;
        surface[rRow][rCol] = "R";
        
        System.out.println("Simulation Started! Robot at: [" + rRow + "," + rCol + "]");
        System.out.println("--------------------------------------------------");

        // 5. MOVEMENT LOOP (Simulation)
        int step = 1;
        int score = 0;

        while (step <= 50) { // 50 Adımlık şarj
            
            // 0: Right, 1: Left, 2: Down, 3: Up
            int direction = r.nextInt(4); 
            
            // "Gölge Değişkenler" (Aday Konum)
            int nextRow = rRow;
            int nextCol = rCol;

            if (direction == 0) nextCol++;      // Right
            else if (direction == 1) nextCol--; // Left
            else if (direction == 2) nextRow++; // Down
            else if (direction == 3) nextRow--; // Up

            // --- COLLISION & BOUNDARY CHECKS ---

            // A) Check Boundaries (Harita dışı kontrolü)
            if (nextRow < 0 || nextRow >= size || nextCol < 0 || nextCol >= size) {
                System.out.println("Step " + step + ": Hit the boundary! (Stayed put)");
            }
            // B) Check Walls (Duvar kontrolü)
            else if (surface[nextRow][nextCol].equals("D")) {
                System.out.println("Step " + step + ": Hit a WALL! (Bonk!)");
            }
            // C) MOVE (Yol güvenli, hareket et!)
            else {
                // Puan Kontrolü
                if (surface[nextRow][nextCol].equals("*")) {
                    score++;
                    System.out.println("Step " + step + ": Dust cleaned! ✨ (+1 Score)");
                } else {
                    System.out.println("Step " + step + ": Moved to empty space.");
                }

                // --- UPDATE POSITION (Gerçek Hareket) ---
                surface[rRow][rCol] = " ";   // 1. Eski yeri sil
                rRow = nextRow;              // 2. Koordinatı güncelle
                rCol = nextCol;
                surface[rRow][rCol] = "R";   // 3. Yeni yere yerleş
            }
            
            step++;
        }

        System.out.println("--------------------------------------------------");
        System.out.println("BATTERY EMPTY.");
        System.out.println("TOTAL SCORE: " + score);
    }
}
