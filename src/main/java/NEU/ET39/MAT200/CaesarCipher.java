package NEU.ET39.MAT200;

public class CaesarCipher {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int ALPHABET_SIZE = ALPHABET.length();

    /**
     * Generates a cipher alphabet based on the provided shift.
     * The shift is used to create a substitution cipher.
     * The letters in the shift are added first, followed by the remaining letters of the alphabet in order.
     * @param shift the shift to generate the cipher alphabet
     * @return the generated cipher alphabet
     */
    private static String generateCipherAlphabet(String shift) {
        StringBuilder cipherAlphabet = new StringBuilder(); // StringBuilder is more efficient for string concatenation
        boolean[] used = new boolean[26]; // Array to track used letters
        shift = shift.toUpperCase(); // Convert shift to uppercase for consistency

        // Add unique letters from the shift
        for (char c : shift.toCharArray()) { // iterate through each character in the shift
            if (Character.isLetter(c)) { // check if the character is a letter
                int idx = c - 'A'; // calculate the index of the letter in the alphabet
                if (!used[idx]) { // check if the letter has already been used
                    cipherAlphabet.append(c); // append the letter to the cipher alphabet
                    used[idx] = true; // mark the letter as used
                }
            }
        }
        // Add remaining unused letters
        for (char c = 'A'; c <= 'Z'; c++) { // iterate through the alphabet
            if (!used[c - 'A']) { // check if the letter has not been used
                cipherAlphabet.append(c); // append the letter to the cipher alphabet
            }
        }
        return cipherAlphabet.toString(); // return the generated cipher alphabet
    }

    /**
     * Encrypts a message using the Caesar Cipher algorithm.
     * The message is shifted by the specified number of positions in the alphabet.
     * @param message the message to encrypt
     * @param shift the number of positions to shift
     * @return the encrypted message
     */
    public static String encryptByShift(String message, int shift) {
        shift = (shift % ALPHABET_SIZE + ALPHABET_SIZE) % ALPHABET_SIZE; // Ensure shift is positive
        String shiftedAlphabet = ALPHABET.substring(shift) + ALPHABET.substring(0, shift); // create the shifted alphabet
        System.out.println("Plain Text:  " + ALPHABET); // Print the original alphabet
        System.out.println("Cipher Text: " + shiftedAlphabet); // Print the shifted alphabet

        StringBuilder encryptedMessage = new StringBuilder(); // StringBuilder is more efficient for string concatenation
        for (char character : message.toCharArray()) { // iterate through each character in the message
            if (Character.isLetter(character)) { // check if the character is a letter
                char base = Character.isUpperCase(character) ? 'A' : 'a'; // base ASCII comparison for upper and lower case
                int shiftedIndex = (character - base + shift) % ALPHABET_SIZE; // calculate the shifted index
                encryptedMessage.append((char) (base + shiftedIndex)); // convert the index back to a character
            } else {
                encryptedMessage.append(character); // if not a letter, append the character as is
            }
        }
        return encryptedMessage.toString(); // return the encrypted message
    }

    /**
     * Encrypts a message using the Caesar Cipher algorithm with a keyword.
     * The keyword is used to generate a substitution cipher.
     * The letters in the keyword are added first, followed by the remaining letters of the alphabet in order.
     * @param message the message to encrypt
     * @param keyword the keyword to use for encryption
     * @return the encrypted message
     */
    public static String encryptByKeyWord(String message, String keyword) {
        String cipherAlphabet = generateCipherAlphabet(keyword);
        System.out.println("Plain Text:  " + ALPHABET);
        System.out.println("Cipher Text: " + cipherAlphabet);

        StringBuilder encryptedMessage = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (Character.isUpperCase(c)) {
                int idx = c - 'A';
                encryptedMessage.append(cipherAlphabet.charAt(idx));
            } else if (Character.isLowerCase(c)) {
                int idx = c - 'a';
                encryptedMessage.append(Character.toLowerCase(cipherAlphabet.charAt(idx)));
            } else {
                encryptedMessage.append(c);
            }
        }
        return encryptedMessage.toString();
    }

    /**
     * Decrypts a message using the Caesar Cipher algorithm.
     * The message is shifted back by the specified number of positions in the alphabet.
     * @param message the message to decrypt
     * @param keyword the number of positions to shift back
     * @return the decrypted message
     */
    public static String decryptByKeyWord(String message, String keyword) {
        String cipherAlphabet = generateCipherAlphabet(keyword);
        System.out.println("Cipher Text: " + cipherAlphabet);
        System.out.println("Plain Text:  " + ALPHABET);

        StringBuilder decryptedMessage = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (Character.isUpperCase(c)) {
                int idx = cipherAlphabet.indexOf(c);
                decryptedMessage.append(ALPHABET.charAt(idx));
            } else if (Character.isLowerCase(c)) {
                int idx = cipherAlphabet.indexOf(Character.toUpperCase(c));
                decryptedMessage.append(Character.toLowerCase(ALPHABET.charAt(idx)));
            } else {
                decryptedMessage.append(c);
            }
        }
        return decryptedMessage.toString();
    }

    /**
     * Decrypts a message using the Caesar Cipher algorithm.
     * The message is shifted back by the specified number of positions in the alphabet.
     * @param message the message to decrypt
     * @param shift the number of positions to shift back
     * @return the decrypted message
     */
    public static String decryptByShift(String message, int shift) {
        shift = (shift % ALPHABET_SIZE + ALPHABET_SIZE) % ALPHABET_SIZE; // Ensure shift is positive
        return encryptByShift(message, ALPHABET_SIZE - shift); // call the encryptByShift method with the negative shift to decrypt
    }
}





