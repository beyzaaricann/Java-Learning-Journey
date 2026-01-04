import java.util.Scanner;

public class HangmanGame {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        String word = "BEYZA"; // The secret word (Must be uppercase)
        char[] board = new char[word.length()]; // The visible board
        int lives = 3; // Total lives

        // 1. Initialize the board with underscores
        for (int i = 0; i < board.length; i++) {
            board[i] = '_';
        }

        System.out.println("--- WELCOME TO HANGMAN GAME ---");

        // 2. Main Game Loop
        while (lives > 0) {
            
            // Print the current status
            System.out.print("Lives: " + lives + " | Word: ");
            for (int i = 0; i < board.length; i++) {
                System.out.print(board[i] + " ");
            }
            System.out.println(); // New line

            // Get user input
            System.out.print("Enter a letter: ");
            // Take the first char and make it uppercase
            char guess = scan.next().toUpperCase().charAt(0); 

            boolean isCorrect = false; // Flag to check if the guess exists

            // Check the word for the guess
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess) {
                    board[i] = guess; // Reveal the letter
                    isCorrect = true;
                }
            }

            // Provide feedback
            if (isCorrect) {
                System.out.println("✅ Correct guess!");
            } else {
                lives--;
                System.out.println("❌ Wrong guess! You lost a life.");
            }

            // --- WIN CHECK ---
            // Check if there are any underscores left
            boolean isWon = true;
            for (int i = 0; i < board.length; i++) {
                if (board[i] == '_') {
                    isWon = false; // Game is not finished yet
                    break;
                }
            }

            if (isWon) {
                System.out.println("\n🎉 CONGRATULATIONS! YOU WON! 🎉");
                System.out.println("The word was: " + word);
                break; // Exit the loop
            }
        }

        // --- GAME OVER CHECK ---
        if (lives == 0) {
            System.out.println("\n💀 GAME OVER! You ran out of lives.");
            System.out.println("The word was: " + word);
        }
    }
}
