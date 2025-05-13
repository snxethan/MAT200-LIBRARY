package NEU.ET39.MAT200;

import java.util.ArrayList;

public class EveryNthCipher {


    /**
     * Display every nth letter of the message.
     * Removes all non-alphanumeric characters.
     * @param sentence The message to check for every nth letter
     * @return A list of strings containing the different possibilities, such as the first letter of every word, the second letter of every word, and so on.
     */
    public static ArrayList<String> everyNth(String sentence) {
        ArrayList<String> result = new ArrayList<>(); // the collection of possible results
        sentence = sentence.replaceAll("[^a-zA-Z0-9 ]", ""); // Remove non-alphanumeric characters
        String[] words = sentence.split(" "); // split the sentence into words

        // Find the maximum word length
        int maxWordLength = 0;
        for (String word : words) {
            maxWordLength = Math.max(maxWordLength, word.length());
        }

        // loop through and store the different possibilities
        StringBuilder possibleHiddenPhrase = new StringBuilder(); //the string to store the possible hidden phrase
        for (int i = 0; i < maxWordLength; i++) {
            for (String word : words) {
                if (i < word.length()) {
                    //if the index is less than the length of the word, add the letter to the possible hidden phrase
                    possibleHiddenPhrase.append(word.charAt(i));
                    // will just skip the letter if the index is greater than the length of the word
                }
            }
            result.add(possibleHiddenPhrase.toString()); // add the possible hidden phrase to the result
            possibleHiddenPhrase.setLength(0); //reset the possible hidden phrase for the next loop
        }
        return result;
    }


    /**
     * Display the message by staircase, resetting if the next word is smaller than the previous word.
     * @param sentence The message to display
     * @return The staircase of letters
     */
    public static String displayByStaircase(String sentence){
        StringBuilder result = new StringBuilder(); // the string to store the result
        String[] words = sentence.split(" "); // split the sentence by spaces into a string array
        int index = 0; //temp variable to store the index of the letter to display

        // loop through the words
        for (String word : words) {
            if (index < word.length()) {
                //if the index is less than the length of the word, add the letter to the result
                result.append(word.charAt(index));
                index++;
            } else {
                //if the index is greater than the length of the word, reset the index to 0 and add the letter to the result
                index = 0;
                result.append(word.charAt(index));
                index++;
            }
        }
        return result.toString();
    }


    /**
     * Display the message by every # of letters, skipping the amount of letters specified and starting at the index specified.
     * @param sentence The message to display
     * @param skipAmount the amount of letters to skip
     * @param startingLetter the index to start at, in human counting
     * @return the message by every # of letters
     */
    public static String displayByEveryLetter(String sentence, int skipAmount, int startingLetter){
        StringBuilder result = new StringBuilder(); // the string to store the result
        //remove all spaces and non alphanumeric characters
        sentence = sentence.replaceAll("[^a-zA-Z0-9]", "");
        //display every letter, skipping the amount of letters specified
        for (int i = startingLetter-1; i < sentence.length(); i += skipAmount) {
            result.append(sentence.charAt(i));
        }
        return result.toString();
    }
}
