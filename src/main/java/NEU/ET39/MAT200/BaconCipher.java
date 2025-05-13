package NEU.ET39.MAT200;

import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BaconCipher {

    // use a String key instead of a char to hold multiple letters (I/J and U/V)
    private static final HashMap<String, String> baconMap = new HashMap<String, String>();

    public static void checkHash(){
        if (baconMap.isEmpty()) {
            applyHash();
        }
    }

    public static void applyHash(){
        // apply the bacon chart to the hash map
        baconMap.put("A ", "00000");
        baconMap.put("B ", "00001");
        baconMap.put("C ", "00010");
        baconMap.put("D ", "00011");
        baconMap.put("E ", "00100");
        baconMap.put("F ", "00101");
        baconMap.put("G ", "00110");
        baconMap.put("H ", "00111");
        baconMap.put("I/J ", "01000");
        baconMap.put("K ", "01001");
        baconMap.put("L ", "01010");
        baconMap.put("M ", "01011");
        baconMap.put("N ", "01100");
        baconMap.put("O ", "01101");
        baconMap.put("P ", "01110");
        baconMap.put("Q ", "01111");
        baconMap.put("R ", "10000");
        baconMap.put("S ", "10001");
        baconMap.put("T ", "10010");
        baconMap.put("U/V ", "10011");
        baconMap.put("W ", "10100");
        baconMap.put("X ", "10101");
        baconMap.put("Y ", "10110");
        baconMap.put("Z ", "10111");
    }


    /**
     * Encode a message using the Bacon cipher, turns capitals into 1s and non-capitals into 0s and returns the binary string.
     * Removes all non letter characters.
     * Ensures (to have a proper encoding) that the message is not empty and is divisible by 5.
     * @param message The message to encode into binary
     * @return Binary String of 1s and 0s (correlated to capitals and non-capitals)
     */
    public static String encrypt(String message){
        checkHash();
        // remove all non-letter characters and ensure that the message is not empty
        message = message.replaceAll("[^A-Za-z]", "");
        if (message.isEmpty()) {
            throw new IllegalArgumentException("Message cannot be empty");
        }
        if (message.length() % 5 != 0) {
            throw new IllegalArgumentException("Message length must be divisible by 5");
        }
        // loop through the message, replace capitals with 1s and non-capitals with 0s
        StringBuilder encodedMessage = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i); // get the character at the current index
            if (Character.isUpperCase(c)) {
                encodedMessage.append("1"); // append 1 for upper case
            } else if (Character.isLowerCase(c)) {
                encodedMessage.append("0"); // append 0 for lower case
            }
        }
        return encodedMessage.toString(); // return the encoded message
    }

    /**
     * Decode a message using the Bacon cipher, turns binary string back into letters using a hash map.
     * Ensures (to have a proper encoding) that the message is not empty and is divisible by 5.
     * @param encodedMessage The binary string to decode
     * @return The decoded message of letters.
     */
    public static String decrypt(String encodedMessage){
        checkHash();
        // ensure that the encoded message is not empty
        if (encodedMessage.isEmpty()) {
            throw new IllegalArgumentException("Encoded message cannot be empty");
        }
        // remove all non-letter characters
        encodedMessage = encodedMessage.replaceAll("[^01]", "");
        // ensure that the length of the encoded message is divisible by 5
        if (encodedMessage.length() % 5 != 0) {
            throw new IllegalArgumentException("Encoded message length must be divisible by 5");
        }
        // split up the message every 5 characters (binary)
        // stack overflow split regex
        String splitMessage = Pattern.compile("(?<=\\G.{5})")
                .splitAsStream(encodedMessage)
                .collect(Collectors.joining(" "));
        System.out.println(splitMessage);

        StringBuilder decodedMessage = new StringBuilder();
        // loop through the split message
        for (String code : splitMessage.split(" ")) {
            for (HashMap.Entry<String, String> entry : baconMap.entrySet()) {
                // check if the code is in the hash map
                if (entry.getValue().equals(code)) {
                    // append the key to the decoded message
                    decodedMessage.append(entry.getKey());
                    break;
                }
            }
        }
        return decodedMessage.toString();
    }
}



/**
 * ThiS claSS Is aN exCellEnt OpportUnIty to deVelOp aNd HonE yoUR cOdING skills. Try fINdinG wAys to QUicklY And EfFICiENtly fInd HiDden mesSageS And oPTimIze yOUR cOde uSing ArTIFICiaL IntellIgencE! thAt beIng Said, do Not hAve aI write YouR cOde For yOu -- thAT defeaTS the purPOse! you Can uSe it to RefINe YoUr COde And MaybE evEN LeARn A ThIng oR tWO, buT you sHOUlD UnDErStaNd EVerYThINg tHAT it cReATeS aS iF yOu cREatEd it yoURSelF. i wiLL GiVe yoU a laRgE hint... THis mEsSAGe COntAInS fiVe HunDrEd And sixtY chaRacteRS. I wILl STroNGLy disCourAgE you fROm brUTe FOrcing thIS as You Have oTheR clASsEs aNd limiteD time. hERe's My ADviCE: tO FinD OUt my seCREt, mAKe ThE compUTer WorK fOr yoU!
 * 10010001110010010001001000001010000001001001010010011010111000000100011000101000011000011001011101100001001010000001000110001100100011101000100010111110011000001000010010001001000001000100010000010010100100010001100000110000001100000100010000010011010101100100100010011101101101000101100100001110110110100101100110110011100010110101010100110010000011100100011101000100010100001100010111011001101001010010101000000100010000111011011001110000100010100001100011011000000011001001000010010011010010000000100000110010110011011001110000011100110101000011001001010001
 *
 * T H E
 * S E C R E T
 * T O
 * P A S S I/J N G
 * M Y
 * C L A S S
 * T H I/J S
 * Q U/V A R T E R
 * I/J S
 * A T T E N D A N C E
 * C O M E
 * S H O W
 * M E
 * H O W
 * Y O U/V
 * S O L U/V E D
 * T H I/J S
 * I/J N
 * M Y
 * O F F I/J C E
 * H O U/V R S
 * I/J N
 * O R D E R
 * T O
 * E A R N
 * Y O U/V R
 * P O I/J N T S
 *
 *
 * --------------------------------------------------------------------------------------------------
 *
 *
 * It is TiME To wITnEsS a batTlE of ePic PropoRtiOns bETWeeN codemAkeRS aNd coDEbrEakeRs ARe you goIng To bE roOting For tHOsE in hIding OR no?
 * 1000101110011010100001010001001000010010001110010000010011010001100100010110000001001001001000010001101000100001100
 *
 * S P O I/J L E R
 * T H E
 * C O D E
 * M A K E R S
 * W I/J N
 *
 */