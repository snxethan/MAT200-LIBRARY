package NEU.ET39.MAT200;

/**
 * The Vigenere Cipher is a method of encrypting alphabetic text by using a simple form of polyalphabetic substitution.
 * It uses a keyword to determine the shift for each letter in the plaintext.
 * The key is repeated to match the length of the plaintext.
 * ---
 * example:
 * plaintext:  A T T A C K A T D A W N
 * key:        L E M O N L E M O N L E
 * ciphertext: L X F O P V E F R N H R
 *
 * you shift the letter in the plaintext by the index of the letter in the key.
 */
public class VigenereCipher {


    /**
     * Encrypts a message using the Vigenere Cipher algorithm.
     * The key must be a string of letters.
     * @param plaintext the message to encrypt
     * @param key the key to use for encryption
     * @return the encrypted message
     */
    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder(); // StringBuilder is more efficient for string concatenation
        int keyIndex = 0; // index for the key
        for (char c : plaintext.toCharArray()) { // iterate through each character in the plaintext
            if (Character.isLetter(c)) { // check if the character is a letter
                char offset = Character.isUpperCase(c) ? 'A' : 'a'; // determine the offset based on case
                char keyChar = key.charAt(keyIndex % key.length()); // get the corresponding character from the key
                char encryptedChar = (char) ((c - offset + (keyChar - offset)) % 26 + offset); // encrypt the character
                ciphertext.append(encryptedChar); // append the encrypted character to the ciphertext
                keyIndex++; // increment the key index
            } else {
                ciphertext.append(c); // if not a letter, append the character as is
            }
        }
        return ciphertext.toString(); // return the encrypted message
    }

    /**
     * Decrypts a message using the Vigenere Cipher algorithm.
     * @param ciphertext the message to decrypt
     * @param key the key to use for decryption
     * @return the decrypted message
     */
    public static String decrypt(String ciphertext, String key) { // StringBuilder is more efficient for string concatenation
        StringBuilder plaintext = new StringBuilder(); // StringBuilder is more efficient for string concatenation
        int keyIndex = 0; // index for the key
        for (char c : ciphertext.toCharArray()) { // iterate through each character in the ciphertext
            if (Character.isLetter(c)) { // check if the character is a letter
                char offset = Character.isUpperCase(c) ? 'A' : 'a'; // determine the offset based on case
                char keyChar = key.charAt(keyIndex % key.length()); // get the corresponding character from the key
                char decryptedChar = (char) ((c - offset - (keyChar - offset) + 26) % 26 + offset); // decrypt the character
                plaintext.append(decryptedChar); // append the decrypted character to the plaintext
                keyIndex++; // increment the key index
            } else {
                plaintext.append(c); // if not a letter, append the character as is
            }
        }
        return plaintext.toString(); // return the decrypted message
    }


}

