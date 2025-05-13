package NEU.ET39.MAT200;

public class CaesarCipher {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // the alphabet used for encryption
    private static final int ALPHABET_SIZE = ALPHABET.length(); // will always be 26

    public static String encrypt(String message, int shift) {
        // Display the original and shifted alphabets
        String shiftedAlphabet = ALPHABET.substring(shift) + ALPHABET.substring(0, shift); // shift the alphabet by the given amount
        System.out.println("Plain Text:  " + ALPHABET); // original alphabet
        System.out.println("Cipher Text: " + shiftedAlphabet); // shifted alphabet

        StringBuilder encryptedMessage = new StringBuilder();
        for (char character : message.toCharArray()) { // iterate through the message
            if (Character.isLetter(character)) { // check if the character is a letter
                char base = Character.isUpperCase(character) ? 'A' : 'a'; // base ASCII comparison for upper and lower case
                int shiftedIndex = (character - base + shift) % ALPHABET_SIZE; // calculate the shifted index
                encryptedMessage.append((char) (base + shiftedIndex)); // convert the index back to a character
            } else {
                encryptedMessage.append(character); // if not a letter, append the character as is
            }
        }
        return encryptedMessage.toString();
    }

    /**
     * Decrypts a message using the Caesar Cipher algorithm.
     * The shift is the same as the one used for encryption.
     * @param message the message to decrypt
     * @param shift the shift used for encryption
     * @return the decrypted message
     */
    public static String decrypt(String message, int shift) {
        return encrypt(message, ALPHABET_SIZE - shift);
        // the decrypt method is just the encrypt method with the shift reversed
    }
}
