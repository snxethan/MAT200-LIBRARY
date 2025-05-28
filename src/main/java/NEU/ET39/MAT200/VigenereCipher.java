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
                char keyChar = key.charAt(keyIndex % key.length());
                keyChar = Character.isUpperCase(c) ? Character.toUpperCase(keyChar) : Character.toLowerCase(keyChar);
                char encryptedChar = (char) ((c - offset + (keyChar - offset)) % 26 + offset); // encrypt the character
                // the formula is (plaintextChar - offset + keyChar - offset) % 26 + offset
                // plaintextChar is the character in the plaintext
                // offset is the base ASCII value for the character (A or a)
                // keyChar is the character in the key
                // encryptedChar is the character in the ciphertext
                // this ensures that the result is within the bounds of the alphabet
                // the % 26 is to wrap around the alphabet
                // the + offset is to convert the result back to a character
                ciphertext.append(encryptedChar); // append the encrypted character to the ciphertext
                keyIndex++; // increment the key index
            } else {
                ciphertext.append(c); // if not a letter, append the character as is
            }
        }

        StringBuilder keyLine = new StringBuilder();
        int keyI = 0;
        for (int i = 0; i < plaintext.length(); i++) {
            if (Character.isLetter(plaintext.charAt(i))) {
                keyLine.append(key.charAt(keyI % key.length()));
                keyI++;
            } else {
                keyLine.append(plaintext.charAt(i));
            }
        }
        System.out.println("Key:        " + keyLine);
        System.out.println("Plaintext:  " + plaintext);
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
                char keyChar = key.charAt(keyIndex % key.length());
                keyChar = Character.isUpperCase(c) ? Character.toUpperCase(keyChar) : Character.toLowerCase(keyChar);
                char decryptedChar = (char) ((c - offset - (keyChar - offset) + 26) % 26 + offset); // decrypt the character
                // the formula is (ciphertextChar - offset - keyChar - offset + 26) % 26 + offset
                // ciphertextChar is the character in the ciphertext
                // offset is the base ASCII value for the character (A or a)
                // keyChar is the character in the key
                // decryptedChar is the character in the plaintext
                // this ensures that the result is within the bounds of the alphabet
                // the + 26 is to ensure that the result is positive
                // the % 26 is to wrap around the alphabet
                // the + offset is to convert the result back to a character
                plaintext.append(decryptedChar); // append the decrypted character to the plaintext
                keyIndex++; // increment the key index
            } else {
                plaintext.append(c); // if not a letter, append the character as is
            }
        }

        StringBuilder keyLine = new StringBuilder();
        int keyI = 0;
        for (int i = 0; i < ciphertext.length(); i++) {
            if (Character.isLetter(ciphertext.charAt(i))) {
                keyLine.append(key.charAt(keyI % key.length()));
                keyI++;
            } else {
                keyLine.append(ciphertext.charAt(i));
            }
        }
        System.out.println("Key:        " + keyLine);
        System.out.println("Ciphertext: " + ciphertext);
        return plaintext.toString(); // return the decrypted message
    }


}

