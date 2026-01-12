import java.util.Random;

public class BusSeatingSimulation {

    public static void main(String[] args) {
        
        Random r = new Random();
        
        // 1. INITIALIZE BUS (10 Rows, 4 Columns)
        // Layout: [0] [1] || [2] [3]
        // 0 & 3: Window, 1 & 2: Aisle
        String[][] bus = new String[10][4];

        // Fill the bus with empty seats ("*")
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                bus[i][j] = "*";
            }
        }

        // Data Sets (English)
        // F: Female, M: Male
        String[] genders = {"F", "M"};
        String[] preferences = {"Window", "Aisle"};
        
        // Simulation Counter (e.g., try for 50 passengers)
        int passengerCount = 0;
        
        while (passengerCount < 50) {
            
            // 2. GENERATE RANDOM PASSENGER
            String gender = genders[r.nextInt(2)];
            String preference = preferences[r.nextInt(2)];
            String passengerInfo = gender + "-" + preference; // e.g., "F-Window"
            
            boolean isSeated = false; // Flag to check if passenger found a seat

            // -----------------------------------------------------------
            // SCENARIO 1: FEMALE PASSENGER ("F")
            // -----------------------------------------------------------
            if (gender.equals("F")) {
                
                // A) PREFERS WINDOW
                if (preference.equals("Window")) {
                    for (int i = 0; i < 10; i++) {
                        // Check Left Window (0): Is it empty AND is Neighbor (1) safe?
                        // Safe means: Neighbor is Empty OR Neighbor is Female
                        if (bus[i][0].equals("*")) {
                            if (bus[i][1].equals("*") || bus[i][1].startsWith("F")) {
                                bus[i][0] = passengerInfo;
                                isSeated = true;
                                break;
                            }
                        }
                        // Check Right Window (3): Is it empty AND is Neighbor (2) safe?
                        if (bus[i][3].equals("*")) {
                            if (bus[i][2].equals("*") || bus[i][2].startsWith("F")) {
                                bus[i][3] = passengerInfo;
                                isSeated = true;
                                break;
                            }
                        }
                    }
                }
                
                // B) PREFERS AISLE OR COULD NOT FIND WINDOW (OFFER AISLE)
                if (!isSeated) {
                    
                    String response = "Yes"; 
                    
                    // If they originally wanted Window, ask if they accept Aisle
                    if (preference.equals("Window")) {
                        String[] answers = {"Yes", "No"};
                        response = answers[r.nextInt(2)]; // Random decision
                        if (response.equals("No")) {
                            System.out.println("Passenger (" + passengerInfo + ") refused Aisle seat.");
                        }
                    }

                    // If they accept Aisle (or wanted it initially)
                    if (response.equals("Yes")) {
                        for (int i = 0; i < 10; i++) {
                            // Left Aisle (1): Check Neighbor (0)
                            if (bus[i][1].equals("*")) {
                                if (bus[i][0].equals("*") || bus[i][0].startsWith("F")) {
                                    bus[i][1] = passengerInfo;
                                    isSeated = true;
                                    break;
                                }
                            }
                            // Right Aisle (2): Check Neighbor (3)
                            if (bus[i][2].equals("*")) {
                                if (bus[i][3].equals("*") || bus[i][3].startsWith("F")) {
                                    bus[i][2] = passengerInfo;
                                    isSeated = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            
            // -----------------------------------------------------------
            // SCENARIO 2: MALE PASSENGER ("M")
            // -----------------------------------------------------------
            else if (gender.equals("M")) {
                
                // A) PREFERS WINDOW
                if (preference.equals("Window")) {
                    for (int i = 0; i < 10; i++) {
                        // Left Window (0)
                        if (bus[i][0].equals("*")) {
                            if (bus[i][1].equals("*") || bus[i][1].startsWith("M")) {
                                bus[i][0] = passengerInfo;
                                isSeated = true;
                                break;
                            }
                        }
                        // Right Window (3)
                        if (bus[i][3].equals("*")) {
                            if (bus[i][2].equals("*") || bus[i][2].startsWith("M")) {
                                bus[i][3] = passengerInfo;
                                isSeated = true;
                                break;
                            }
                        }
                    }
                }
                
                // B) PREFERS AISLE OR WINDOW WAS FULL
                if (!isSeated) {
                    String response = "Yes";
                    if (preference.equals("Window")) {
                        String[] answers = {"Yes", "No"};
                        response = answers[r.nextInt(2)];
                        if (response.equals("No")) {
                            System.out.println("Passenger (" + passengerInfo + ") refused Aisle seat.");
                        }
                    }

                    if (response.equals("Yes")) {
                        for (int i = 0; i < 10; i++) {
                            // Left Aisle (1)
                            if (bus[i][1].equals("*")) {
                                if (bus[i][0].equals("*") || bus[i][0].startsWith("M")) {
                                    bus[i][1] = passengerInfo;
                                    isSeated = true;
                                    break;
                                }
                            }
                            // Right Aisle (2)
                            if (bus[i][2].equals("*")) {
                                if (bus[i][3].equals("*") || bus[i][3].startsWith("M")) {
                                    bus[i][2] = passengerInfo;
                                    isSeated = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            // Log if passenger left without ticket
            if (!isSeated) {
                // System.out.println("COULD NOT SEAT: " + passengerInfo); // Optional: Un-comment to see failures
            }
            
            passengerCount++;
        }

        // 3. PRINT FINAL BUS STATE
        System.out.println("\n--- FINAL BUS LAYOUT ---");
        System.out.println("     Left       ||      Right");
        for (int i = 0; i < 10; i++) {
            System.out.print("Row " + i + ": ");
            for (int j = 0; j < 4; j++) {
                // Formatting for alignment
                System.out.printf("[%-8s] ", bus[i][j]);
                if (j == 1) System.out.print(" || "); // Corridor
            }
            System.out.println();
        }
    }
}
