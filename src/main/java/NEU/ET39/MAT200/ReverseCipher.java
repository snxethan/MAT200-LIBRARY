package NEU.ET39.MAT200;

public class ReverseCipher {

    /**
     * Reverses the message
     * @param message The message to reverse
     * @return The reversed message
     */
    public static String reverse(String message) {
        return new StringBuilder(message).reverse().toString();
    }
}
