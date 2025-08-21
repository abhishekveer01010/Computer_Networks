import java.util.Scanner;

public class hamm {
    // Function to calculate the number of parity bits needed
    static int calculateParityBits(int dataBits) {
        int m = dataBits;
        int r = 0;
        while (Math.pow(2, r) < m + r + 1) {
            r++;
        }
        return r;
    }

    // Function to encode data using Hamming code
    static int[] encodeData(int[] data) {
        int m = data.length;
        int r = calculateParityBits(m);
        int n = m + r;
        int[] encoded = new int[n];

        // Place data bits at non-power-of-2 positions
        int j = 0;
        for (int i = 0; i < n; i++) {
            if ((i + 1 & (i)) != 0) { // Check if position is not power of 2
                encoded[i] = data[j++];
            }
        }

        // Calculate parity bits
        for (int i = 0; i < r; i++) {
            int parityPos = (int) Math.pow(2, i) - 1;
            int parity = 0;
            for (int j2 = 0; j2 < n; j2++) {
                if (((j2 + 1) & (parityPos + 1)) != 0) {
                    parity ^= encoded[j2];
                }
            }
            encoded[parityPos] = parity;
        }

        return encoded;
    }

    // Function to detect and correct errors
    static void detectAndCorrect(int[] received) {
        int n = received.length;
        int r = 0;
        while (Math.pow(2, r) < n + 1) {
            r++;
        }

        int errorPos = 0;
        for (int i = 0; i < r; i++) {
            int parityPos = (int) Math.pow(2, i) - 1;
            int parity = 0;
            for (int j = 0; j < n; j++) {
                if (((j + 1) & (parityPos + 1)) != 0) {
                    parity ^= received[j];
                }
            }
            if (parity != 0) {
                errorPos += (parityPos + 1);
            }
        }

        if (errorPos == 0) {
            System.out.println("No error detected!");
        } else {
            System.out.println("Error detected at position: " + errorPos);
            // Correct the error
            received[errorPos - 1] ^= 1;
            System.out.println("Error corrected!");
        }
    }

    // Function to extract original data from encoded data
    static int[] extractData(int[] encoded) {
        int n = encoded.length;
        int r = 0;
        while (Math.pow(2, r) < n + 1) {
            r++;
        }
        int m = n - r;
        int[] data = new int[m];

        int j = 0;
        for (int i = 0; i < n; i++) {
            if ((i + 1 & (i)) != 0) {
                data[j++] = encoded[i];
            }
        }
        return data;
    }

    // Function to print array
    static void printArray(int[] arr, String label) {
        System.out.print(label + ": ");
        for (int bit : arr) {
            System.out.print(bit);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Hamming Code Error Detection and Correction ===");
        System.out.println("1. Encode data");
        System.out.println("2. Decode and correct errors");
        System.out.print("Enter your choice (1 or 2): ");

        int choice = scanner.nextInt();

        if (choice == 1) {
            // Encoding mode
            System.out.print("Enter number of data bits: ");
            int dataBits = scanner.nextInt();

            int[] data = new int[dataBits];
            System.out.print("Enter data bits (space separated): ");
            for (int i = 0; i < dataBits; i++) {
                data[i] = scanner.nextInt();
            }

            int[] encoded = encodeData(data);
            printArray(data, "Original data");
            printArray(encoded, "Encoded data");

        } else if (choice == 2) {
            // Decoding mode
            System.out.print("Enter length of received data: ");
            int length = scanner.nextInt();

            int[] received = new int[length];
            System.out.print("Enter received bits (space separated): ");
            for (int i = 0; i < length; i++) {
                received[i] = scanner.nextInt();
            }

            System.out.println("\nProcessing received data...");
            printArray(received, "Received data");

            detectAndCorrect(received);
            printArray(received, "Corrected data");

            int[] originalData = extractData(received);
            printArray(originalData, "Extracted original data");

        } else {
            System.out.println("Invalid choice!");
        }

        scanner.close();
    }
}
