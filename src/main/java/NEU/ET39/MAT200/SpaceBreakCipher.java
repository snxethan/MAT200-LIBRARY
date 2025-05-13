package NEU.ET39.MAT200;

public class SpaceBreakCipher {

    /**
     * removes all spaces from the message
     * @param message The message to remove spaces from
     * @return The message without spaces
     */
    public static String removeSpaces(String message) {
        return message.replaceAll("\\s+", "");
    }
}
