package NEU.ET39.MAT200;

/**
 * It's a type of monoalphabetic substitution cipher that uses a linear function (a * x + b) mod 26 to encrypt letters.
 * It combines both multiplication and addition, making it stronger than a simple Caesar cipher.
 */
public class AffineCipher {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int ALPHABET_SIZE = ALPHABET.length(); // will always be 26
    private static final int MOD = ALPHABET_SIZE; // modulus for the alphabet size

    /**
     * Encrypts a message using the Affine Cipher algorithm.
     * The key must be an odd number and coprime to 26.
     * (a * x + b) mod 26
     * @param message the message to encrypt
     * @param multiplicativeKey the multiplicative key
     * @param caesarKey the caesar key
     * @return the encrypted message
     */
    public static String encrypt(String message, int multiplicativeKey, int caesarKey) {
        System.out.println("Encryption Process:");
        System.out.println("Multiplicative Key (a): " + multiplicativeKey);
        System.out.println("Caesar Key (b): " + caesarKey);
        System.out.println("Formula: (a * x + b) mod " + MOD);

        StringBuilder encryptedMessage = new StringBuilder();
        for (char character : message.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                int index = ALPHABET.indexOf(Character.toUpperCase(character));
                int shiftedIndex = (multiplicativeKey * index + caesarKey) % MOD;
                System.out.println("Character: " + character + " -> Index: " + index + " -> Encrypted Index: " + shiftedIndex);
                encryptedMessage.append((char) (base + shiftedIndex));
            } else {
                encryptedMessage.append(character);
            }
        }
        return encryptedMessage.toString();
    }

    /**
     * Decrypts a message using the Affine Cipher algorithm.
     * @param message the message to decrypt
     * @param key the key to use for decryption
     * @return the decrypted message
     */
    public static String encrypt(String message, int key) {
        int a = key / MOD; // multiplicative key
        int b = key % MOD; // caesar key
        return encrypt(message, a, b); // call the encrypt method with the multiplicative and caesar keys
    }

    /**
     * Decrypts a message using the Affine Cipher algorithm.
     * @param message the message to decrypt
     * @param key the key to use for decryption
     * @return the decrypted message
     */
    public static String decrypt(String message, int key) {
        int a = key / MOD; // multiplicative key
        int b = key % MOD; // caesar key
        return decrypt(message, a, b); // call the decrypt method with the multiplicative and caesar keys
    }

    /**
     * Decrypts a message using the Affine Cipher algorithm.
     * The key must be an odd number and coprime to 26.
     * (a^-1 * (y - b)) mod 26
     * @param message the message to decrypt
     * @param multiplicativeKey the multiplicative key
     * @param caesarKey the caesar key
     * @return the decrypted message
     */
    public static String decrypt(String message, int multiplicativeKey, int caesarKey) {
        System.out.println("Decryption Process:");
        System.out.println("Multiplicative Key (a): " + multiplicativeKey);
        System.out.println("Caesar Key (b): " + caesarKey);
        System.out.println("Formula: (a^-1 * (y - b)) mod " + MOD);

        int aInverse = findModularInverse(multiplicativeKey, MOD);
        System.out.println("Modular Inverse of a (a^-1): " + aInverse);

        StringBuilder decryptedMessage = new StringBuilder();
        for (char ch : message.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                int y = Character.toUpperCase(ch) - 'A';
                int decodedIndex = (aInverse * (y - caesarKey + MOD)) % MOD;
                System.out.println("Character: " + ch + " -> Index: " + y + " -> Decrypted Index: " + decodedIndex);
                decryptedMessage.append((char) (base + decodedIndex));
            } else {
                decryptedMessage.append(ch);
            }
        }
        return decryptedMessage.toString();
    }


    /**
     * Finds the modular inverse of a given number with respect to a modulus.
     * @param a the number to find the modular inverse of
     * @param m the modulus
     * @return the modular inverse of a with respect to m
     */
    private static int findModularInverse(int a, int m) {
        a %= m; // reduce a modulo m
        for (int x = 1; x < m; x++) { // iterate through all possible values of x
            if ((a * x) % m == 1) { // check if (a * x) % m == 1
                return x; // return the modular inverse
            }
        }
        throw new IllegalArgumentException("No modular inverse exists for the given key.");
    }

    /**
     * Finds the Affine key for the Affine Cipher.
     * @param key1 the multiplicative key
     * @param key2 the caesar key
     * @return the combined key
     */
    public static int findKey(int key1, int key2) {
        return key1 * MOD + key2; // combine the multiplicative and caesar keys into a single key
    }

    /**
     * Finds the multiplicative and caesar keys from the combined Affine key.
     * @param key the combined key
     * @return an array containing the multiplicative and caesar keys
     */
    public static int[] findKeys(int key) {
        int multiplicativeKey = key / MOD; // multiplicative key
        int additiveKey = key % MOD; // caesar key
        return new int[]{multiplicativeKey, additiveKey}; // return the multiplicative and caesar keys as an array
    }
}


