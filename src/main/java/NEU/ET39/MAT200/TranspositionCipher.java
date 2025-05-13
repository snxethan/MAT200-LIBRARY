package NEU.ET39.MAT200;

import java.util.ArrayList;
import java.util.List;

public class TranspositionCipher {

    public static String encryptWord(String message, String key) {
        message = message.replaceAll("[^a-zA-Z]", "").toLowerCase(); // Clean and lowercase
        key = key.replaceAll("\\s+", "").toLowerCase(); // Clean and lowercase key

        int[] columnOrder = getColumnOrder(key);

        int numRows = (int) Math.ceil((double) message.length() / key.length());
        int paddedLength = numRows * key.length();
        StringBuilder paddedMessage = new StringBuilder(message);
        while (paddedMessage.length() < paddedLength) {
            paddedMessage.append("x");
        }

        char[][] grid = new char[numRows][key.length()];
        int index = 0;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < key.length(); col++) {
                grid[row][col] = paddedMessage.charAt(index++);
            }
        }

        StringBuilder cipherText = new StringBuilder();
        for (int col : columnOrder) {
            for (int row = 0; row < numRows; row++) {
                cipherText.append(grid[row][col]);
            }
        }

        return formatOutput(cipherText.toString());
    }



    // Helper to format output into uppercase and groups of 5 characters
    private static String formatOutput(String text) {
        text = text.toUpperCase(); // All uppercase
        StringBuilder formatted = new StringBuilder();
        int count = 0;

        for (int i = 0; i < text.length(); i++) {
            formatted.append(text.charAt(i));
            count++;
            if (count == 5 && i != text.length() - 1) { // Add space every 5 letters
                formatted.append(' ');
                count = 0;
            }
        }

        return formatted.toString();
    }


    private static int[] getColumnOrder(String key) {
        int len = key.length();
        int[] order = new int[len];

        // Create list of (char, originalIndex) pairs
        List<int[]> keyList = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            keyList.add(new int[]{key.charAt(i), i});
        }

        // Sort by character (then by original index for duplicates)
        keyList.sort((a, b) -> {
            if (a[0] == b[0]) return Integer.compare(a[1], b[1]);
            return Character.compare((char) a[0], (char) b[0]);
        });

        // Return array of original indices in sorted order
        for (int i = 0; i < len; i++) {
            order[i] = keyList.get(i)[1];
        }

        return order;
    }



    public static String encryptNum(String message, int key) {
        message = message.replaceAll("[^a-zA-Z]", "").toLowerCase(); // Clean and lowercase
        int numRows = (int) Math.ceil((double) message.length() / key);

        int paddedLength = numRows * key;
        StringBuilder paddedMessage = new StringBuilder(message);
        while (paddedMessage.length() < paddedLength) {
            paddedMessage.append("x");
        }

        char[][] grid = new char[numRows][key];
        int index = 0;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < key; col++) {
                grid[row][col] = paddedMessage.charAt(index++);
            }
        }

        StringBuilder cipherText = new StringBuilder();
        for (int col = 0; col < key; col++) {
            for (int row = 0; row < numRows; row++) {
                cipherText.append(grid[row][col]);
            }
        }

        return formatOutput(cipherText.toString());
    }



    public static String decryptWord(String message, String key) {
        message = message.replaceAll("\\s+", ""); // Clean input
        key = key.replaceAll("\\s+", "").toLowerCase();
        int numCols = key.length();
        int numRows = (int) Math.ceil((double) message.length() / numCols);

        // Determine column order
        int[] colOrder = getColumnOrder(key);

        // Figure out how many columns have fewer rows
        int fullCells = message.length();
        int fullCols = fullCells % numCols;
        if (fullCols == 0) fullCols = numCols;

        // Set up grid
        char[][] grid = new char[numRows][numCols];
        int idx = 0;

        // Fill grid **column by column** in the sorted key order
        for (int sortedColIndex = 0; sortedColIndex < numCols; sortedColIndex++) {
            int realCol = colOrder[sortedColIndex];
            int colHeight = (realCol < fullCols) ? numRows : numRows - 1;
            for (int row = 0; row < colHeight; row++) {
                if (idx < message.length()) {
                    grid[row][realCol] = message.charAt(idx++);
                }
            }
        }

        // Read grid **row by row**
        StringBuilder decrypted = new StringBuilder();
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (grid[row][col] != 0) {
                    decrypted.append(grid[row][col]);
                }
            }
        }

        return decrypted.toString();
    }


//    public static String decryptWord(String message, String key) {
//        message = message.replaceAll("\\s+", ""); // Remove spaces from encrypted message
//
//        int numCols = key.length();
//        int numRows = (int) Math.ceil((double) message.length() / numCols);
//
//        // Get the order of columns by sorted key
//        int[] sortedKeyOrder = getColumnOrder(key);
//
//        // Rebuild the grid column by column using sorted order
//        char[][] grid = new char[numRows][numCols];
//        int index = 0;
//
//        for (int col : sortedKeyOrder) {
//            for (int row = 0; row < numRows; row++) {
//                if (index < message.length()) {
//                    grid[row][col] = message.charAt(index++);
//                }
//            }
//        }
//
//        // Read the grid row by row to reconstruct the original message
//        StringBuilder decrypted = new StringBuilder();
//        for (int row = 0; row < numRows; row++) {
//            for (int col = 0; col < numCols; col++) {
//                if (grid[row][col] != 0) { // Avoid uninitialized cells
//                    decrypted.append(grid[row][col]);
//                }
//            }
//        }
//
//        // Trim off padding (like 'X') at the end
//        return decrypted.toString().replaceAll("X+$", "");
//    }


    // i like to eat spaghetti
    // 9
    // iliketoeatspaghetti
    //
    public static String decryptNum(String message, int key) {
        // Clean message: remove spaces and punctuation
        message = message.replaceAll("[^A-Za-z0-9]", "");
        int numRows = (int) Math.ceil((double) message.length() / key); //  How many rows will be needed in the grid (ceil because the last row might be partial).
        int numFullCols = message.length() % key; // How many columns are completely full (i.e., have all rows).
        if (numFullCols == 0) numFullCols = key; // If all columns are evenly filled (% key == 0), then all columns are full (numFullCols = key).


        char[][] grid = new char[numRows][key]; // Create grid
        int index = 0; // Index for message parameter

        // Fill the grid column by column
        /**
         * For each column, calculate how many rows it should fill:
         * The first numFullCols columns are taller (i.e., have numRows).
         * The rest of the columns are shorter (i.e., have numRows - 1).
         */
        for (int col = 0; col < key; col++) { // Loop through each column
            int currentColLength = (col < numFullCols) ? numRows : numRows - 1; // Determine current column length

            for (int row = 0; row < currentColLength; row++) { // Loop through each row
                if (index < message.length()) { // Check if index is within message length
                    grid[row][col] = message.charAt(index++); // Fill grid with message characters
                }
            }
        }

        // Read the grid row by row
        StringBuilder decrypted = new StringBuilder(); // Create StringBuilder for decrypted message
        for (int row = 0; row < numRows; row++) { // Loop through each row
            for (int col = 0; col < key; col++) { // Loop through each column
                if (grid[row][col] != 0) { // Check if grid cell is not empty
                    decrypted.append(grid[row][col]); // Append character to decrypted message
                }
            }
        }

        return decrypted.toString(); // Return decrypted message
    }



}

