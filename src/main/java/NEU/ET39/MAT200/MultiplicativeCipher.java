package NEU.ET39.MAT200;

/**
 * The Multiplicative Cipher is a type of monoalphabetic substitution cipher that uses a linear function (a * x) mod 26 to encrypt letters.
 * It is a simple encryption algorithm that replaces each letter in the plaintext with a letter at a fixed position in the alphabet.
 * ---
 * The encryption function is defined as:
 * E(x) = (a * x) mod 26
 ---
 * And the decryption function is:
 * D(y) = a⁻¹ * y mod 26
 * where:
 * - E(x) is the encrypted letter
 * - D(y) is the decrypted letter
 * - a is the key (must be coprime to 26)
 * - x is the index of the plaintext letter in the alphabet
 * - y is the index of the ciphertext letter in the alphabet
 * - a⁻¹ is the modular inverse of a with respect to 26
 * ---
 */
public class MultiplicativeCipher {

    // MOD(INDEX OF ALPHABET * KEY/SHIFT)
    // multiples of the factor of 26 would be invalid
    // any even numbers, 1 (works but is not a valid key), 13 (works but is not a valid key)
    // A will always be A, N will always be N (0 and 13)

    // 1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int ALPHABET_SIZE = ALPHABET.length(); // will always be 26
    private static final int[] VALID_KEYS = {1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25}; // valid keys that are coprime to 26
    private static final int MOD = ALPHABET_SIZE; // modulus for the alphabet size


    /**
     * Encrypts a message using the Multiplicative Cipher algorithm.
     * The key must be an odd number and coprime to 26.
     * @param message the message to encrypt
     * @param key the key to use for encryption
     * @return the encrypted message
     */
    public static String encrypt(String message, int key) {
        if (!isValidKey(key)) { // check if the key is valid
            throw new IllegalArgumentException("Invalid key. Must be an odd number and coprime to 26.");
        }

        // Generate the shifted alphabet
        StringBuilder shiftedAlphabet = new StringBuilder();
        for (int i = 0; i < ALPHABET_SIZE; i++) { // iterate through the alphabet
            int shiftedIndex = (i * key) % MOD; // calculate the shifted index
            shiftedAlphabet.append(ALPHABET.charAt(shiftedIndex)); // append the character at the shifted index to the shifted alphabet
        }

        // Print the original and shifted alphabets
        System.out.println("Plain Text: " + ALPHABET);
        System.out.println("Cipher Text:  " + shiftedAlphabet);

        // Encrypt the message
        StringBuilder encryptedMessage = new StringBuilder();
        for (char character : message.toCharArray()) { // iterate through the message
            if (Character.isLetter(character)) { // check if the character is a letter
                char base = Character.isUpperCase(character) ? 'A' : 'a'; // base ASCII comparison for upper and lower case
                int index = ALPHABET.indexOf(Character.toUpperCase(character)); // find the index of the character in the alphabet
                int shiftedIndex = (index * key) % MOD; // calculate the shifted index
                encryptedMessage.append((char) (base + shiftedIndex)); // convert the index back to a character
            } else {
                encryptedMessage.append(character); // if not a letter, append the character as is
            }
        }
        return encryptedMessage.toString();
    }

    /**
     * Decrypts a message using the Multiplicative Cipher algorithm.
     * The key must be an odd number and coprime to 26.
     * @param message the message to decrypt
     * @param key the key to use for decryption
     * @return the decrypted message
     */
    public static String decrypt(String message, int key) {
        if (!isValidKey(key)) { // check if the key is valid
            throw new IllegalArgumentException("Invalid key. Must be an odd number and coprime to 26.");
        }
        // Find the modular inverse of the key
        int inverseKey = findModularInverse(key, MOD); // find the modular inverse of the key
        return encrypt(message, inverseKey); // decrypt the message using the modular inverse
    }

    /**
     * Checks if the given key is valid for the Multiplicative Cipher.
     * The key must be an odd number and coprime to 26.
     * @param key the key to check
     * @return true if the key is valid, false otherwise
     */
    private static boolean isValidKey(int key) {
        for (int validKey : VALID_KEYS) { // check if the key is in the list of valid keys
            if (key == validKey) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds the modular inverse of a given number with respect to a modulus.
     * This is used to decrypt the message.
     * The modular inverse is the number x such that (a * x) % m = 1.
     * @param a the number to find the modular inverse of
     * @param m the modulus
     * @return the modular inverse of a with respect to m
     */
    private static int findModularInverse(int a, int m) {
        a = a % m; // reduce a modulo m
        for (int x = 1; x < m; x++) { // iterate through all possible values of x
            if ((a * x) % m == 1) { // check if (a * x) % m == 1
                return x; // return the modular inverse
            }
        }
        throw new IllegalArgumentException("No modular inverse exists for the given key.");
    }
}


