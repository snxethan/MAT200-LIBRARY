package NEU.ET39.MAT200;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static String invalid = "Invalid choice. Please try again.";
    public static String invalidNum = "Please enter a valid number choice.";
    public static String returnToMain = "Returning...";
    public static String mainMenu = "0. Return to Main Menu";
    public static String enterMessage = "\nEnter your message: ";


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        runConsole(scanner);
        scanner.close();
    }

    /**
     * Console for all Ciphers
     * @param scanner Scanner object to read user input
     */
    public static void runConsole(Scanner scanner) {
        while (true) {
            System.out.println("-- CIPHERS --");
            System.out.println("0. Exit");
            System.out.println("1. [U1] Bacon Cipher");
            System.out.println("2. [U1] Space Break Cipher");
            System.out.println("3. [U1] Every nth Letter Cipher");
            System.out.println("4. [U1] Reverse Cipher");
            System.out.println("5. [U2] Transposition Cipher");
            System.out.println("6. [U3] Caesar Cipher");
            System.out.println("7. [U3] Multiplicative Cipher");
            System.out.println("8. [U3] Affine Cipher");
            System.out.println("9. [U4] Vigenere Cipher");
            System.out.println("10. [FINAL] RSA Cipher");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 0:
                    System.out.println("Exiting...");
                    return;
                case 1:
                    runConsoleBacon(scanner);
                    break;
                case 2:
                    runConsoleSpaceBreak(scanner);
                    break;
                case 3:
                    runConsoleEveryNth(scanner);
                    break;
                case 4:
                    runConsoleReverse(scanner);
                    break;
                case 5:
                    runConsoleTransposition(scanner);
                    break;
                case 6:
                    runConsoleCaesar(scanner);
                    break;
                case 7:
                    runConsoleMultiplicative(scanner);
                    break;
                case 8:
                    runConsoleAffine(scanner);
                    break;
                case 9:
                    runConsoleVigenere(scanner);
                case 10:
                    runConsoleRSA(scanner);
                default:
                    System.out.println(invalid);
                case 11:
                    // turn text to all upper
                    System.out.println("Enter your message: ");
                    String message = scanner.nextLine();
                    System.out.println(message.toUpperCase());
            }
        }
    }


    public static void runConsoleRSA(Scanner scanner) {
        RSACipher rsa = new RSACipher();

        while (true) {
            System.out.println("-- RSA CIPHER --");
            System.out.println(mainMenu);
            System.out.println("1. Generate/Save Public Key");
            System.out.println("2. Generate/Save  Private Key");
            System.out.println("3. Encrypt Message with Public Key");
            System.out.println("4. Decrypt Message with Private Key");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(invalidNum);
                continue;
            }

            switch (choice) {
                case 0:
                    System.out.println(returnToMain);
                    return;

                case 1: {
                    System.out.println("Enter filename to save public key (e.g. yourname_key.txt):");
                    String filename = scanner.nextLine().trim();
                    rsa.savePublicKeyOnly(filename);
                    break;
                }
                case 2: {
                    System.out.println("Enter filename to save private key (e.g. yourname_key.txt):");
                    String filename = scanner.nextLine().trim();
                    rsa.savePrivateKeyOnly(filename);
                    break;
                }
                case 3: {
                    System.out.println("Enter the path to key file (e.g. person_key.txt):");
                    String path = scanner.nextLine().trim();
                    BigInteger[] key = RSACipher.loadPublicKeyFromTxt(path);
                    if (key == null) break;

                    System.out.println("Enter your message:");
                    String message = scanner.nextLine();
                    try {
                        BigInteger encrypted = rsa.encryptBlock(message, key[0], key[1]);
                        System.out.println("Encrypted Message:\n" + encrypted);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }

                case 4: {
                    System.out.println("Enter your key file path (e.g. yourname_key.txt):");
                    String path = scanner.nextLine().trim();
                    BigInteger[] key = RSACipher.loadPrivateKeyFromTxt(path);
                    if (key == null) break;

                    rsa.loadPrivateKey(key[0], key[1]); // d and n

                    System.out.println("Enter encrypted message:");
                    String input = scanner.nextLine().trim();
                    try {
                        BigInteger encrypted = new BigInteger(input);
                        String decrypted = rsa.decryptBlock(encrypted);
                        System.out.println("Decrypted Message:\n" + decrypted);
                    } catch (Exception e) {
                        System.out.println("Invalid input or decryption error.");
                    }
                    break;
                }

                default:
                    System.out.println(invalid);
            }
        }
    }




    public static void runConsoleVigenere(Scanner scanner) {
        while (true) {
            System.out.println("-- VIGENERE CIPHER --");
            System.out.println(mainMenu);
            System.out.println("1. Encrypt");
            System.out.println("2. Decrypt");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(invalidNum);
                continue;
            }

            switch (choice) {
                case 0:
                    System.out.println(returnToMain);
                    return;
                case 1: {
                    System.out.println("Encrypt");
                    System.out.println(enterMessage);
                    String message = scanner.nextLine();
                    System.out.println("Enter your key: ");
                    String key = scanner.nextLine();
                    System.out.println(VigenereCipher.encrypt(message,key));
                    break;
                }
                case 2: {
                    System.out.println("Decrypt");
                    System.out.println(enterMessage);
                    String message = scanner.nextLine();
                    System.out.println("Enter your key: ");
                    String key = scanner.nextLine();
                    System.out.println("            " +VigenereCipher.decrypt(message,key));
                    break;
                }
                default:
                    System.out.println(invalid);
            }
        }
    }

    public static void displaySubstitionPairs() {
        System.out.println("VALID MODULO PAIRS: (1, 1), (3, 9), (5, 21), (7, 15), (9, 3), (11, 23), (15, 7), (17, 19), (25, 25)");
        System.out.println("VALID KEYS: 1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25");
    }

    public static void runConsoleAffine(Scanner scanner) {
        while (true) {
            System.out.println("-- AFFINE CIPHER --");
            System.out.println(mainMenu);
            System.out.println("1. Convert Multiplicative & Caesar to Affine Key");
            System.out.println("2. Convert Affine Key to Multiplicative & Caesar Key");
            System.out.println("3. Encrypt by Affine Key");
            System.out.println("4. Encrypt by Caesar & Multiplicative Key");
            System.out.println("5. Decrypt by Affine Key");
            System.out.println("6. Decrypt by Caesar & Multiplicative Key");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(invalidNum);
                continue;
            }

            switch (choice) {
                case 0:
                    System.out.println(returnToMain);
                    return;
                case 1: {
                    System.out.println("Convert Multiplicative & Caesar to Affine Key");
                    System.out.println("Enter your multiplicative key: ");
                    int multiplicativeKey = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.println("Enter your Caesar key: ");
                    int caesarKey = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.println("Affine Key: " + AffineCipher.findKey(multiplicativeKey, caesarKey));
                    break;
                }
                case 2: {
                    System.out.println("Convert Affine Key to Multiplicative & Caesar Key");
                    System.out.println("Enter your key: ");
                    int key = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    int[] keys = AffineCipher.findKeys(key);
                    System.out.println("Multiplicative Key: " + keys[0]);
                    System.out.println("Caesar Key: " + keys[1]);
                    break;
                }
                case 3: {
                    System.out.println("Encrypt by Affine Key");
                    System.out.println("Enter your message: ");
                    String message = scanner.nextLine();
                    System.out.println("Enter your key: ");
                    int key = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.println("Encrypting... by a key of " + key);
                    System.out.println(AffineCipher.encrypt(message, key) + "\n");
                    break;
                }
                case 4: {
                    System.out.println("Encrypt by Caesar & Multiplicative Key");
                    System.out.println("Enter your message: ");
                    String message = scanner.nextLine();
                    System.out.println("Enter your multiplicative key: ");
                    int multiplicativeKey = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.println("Enter your Caesar key: ");
                    int caesarKey = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.println("Encrypting... by a key of " + multiplicativeKey + " and " + caesarKey);
                    System.out.println(AffineCipher.encrypt(message, multiplicativeKey, caesarKey) + "\n");
                    break;
                }
                case 5: {
                    System.out.println("Decrypt by Affine Key");
                    System.out.println("Enter your message: ");
                    String message = scanner.nextLine();
                    System.out.println("Enter your key: ");
                    int key = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.println("Decrypting... by a key of " + key);
                    System.out.println(AffineCipher.decrypt(message, key) + "\n");
                    break;
                }
                case 6: {
                    System.out.println("Decrypt by Caesar & Multiplicative Key");
                    System.out.println("Enter your message: ");
                    String message = scanner.nextLine();
                    System.out.println("Enter your multiplicative key: ");
                    int multiplicativeKey = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.println("Enter your Caesar key: ");
                    int caesarKey = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.println("Decrypting... by a key of " + multiplicativeKey + " and " + caesarKey);
                    System.out.println(AffineCipher.decrypt(message, multiplicativeKey, caesarKey) + "\n");
                    break;
                }
                default:
                    System.out.println(invalid);
            }
        }
    }

    public static void runConsoleMultiplicative(Scanner scanner) {
        while (true) {
            System.out.println("-- MULTIPLICATIVE CIPHER --");
            System.out.println(mainMenu);
            System.out.println("1. Encrypt");
            System.out.println("2. Decrypt");


            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(invalidNum);
                continue;
            }

            switch (choice) {
                case 0:
                    System.out.println(returnToMain);
                    return;
                case 1: {
                    displaySubstitionPairs();
                    System.out.println("Encrypt");
                    System.out.println(enterMessage);
                    String message = scanner.nextLine();
                    System.out.println("\nEnter your key: ");
                    int key = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.println("Encrypting... by a key of " + key);
                    System.out.println(MultiplicativeCipher.encrypt(message, key) + "\n");
                    break;
                }
                case 2: {
                    displaySubstitionPairs();
                    System.out.println("Decrypt");
                    System.out.println(enterMessage);
                    String message = scanner.nextLine();
                    System.out.println("\nEnter your key: ");
                    int key = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.println("Decrypting... by a key of " + key);
                    System.out.println(MultiplicativeCipher.decrypt(message, key) + "\n");
                    break;
                }
                default:
                    System.out.println(invalid);
            }
        }
    }


    public static void runConsoleCaesar(Scanner scanner) {
        while(true){
            System.out.println("-- CAESAR CIPHER --");
            System.out.println(mainMenu);
            System.out.println("1. Encrypt by Shift");
            System.out.println("2. Encrypt by Key Word/Phrase");
            System.out.println("3. Decrypt by Shift");
            System.out.println("4. Decrypt by Key Word/Phrase");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(invalidNum);
                continue;
            }

            switch(choice){
                case 0:
                    System.out.println(returnToMain);
                    break;
                case 1: {
                    System.out.println("Encrypt");
                    System.out.println(enterMessage);
                    String message = scanner.nextLine();
                    System.out.println("\nEnter your shift: ");
                    int shift = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.println("Encrypting... by a shift of " + shift);
                    System.out.println(CaesarCipher.encryptByShift(message, shift) + "\n");
                    break;
                }
                case 2: {
                    System.out.println("Encrypt");
                    System.out.println(enterMessage);
                    String message = scanner.nextLine();
                    System.out.println("\nEnter your key word: ");
                    String key = scanner.nextLine();
                    System.out.println("Encrypting... by a key of " + key);
                    System.out.println(CaesarCipher.encryptByKeyWord(message, key) + "\n");
                    break;
                }
                case 3: {
                    System.out.println("Decrypt");
                    System.out.println(enterMessage);
                    String message = scanner.nextLine();
                    System.out.println("\nEnter your shift: ");
                    int shift = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.println("Decrypting... by a shift of " + shift);
                    System.out.println(CaesarCipher.decryptByShift(message, shift) + "\n");
                    break;
                }
                case 4: {
                    System.out.println("Decrypt");
                    System.out.println(enterMessage);
                    String message = scanner.nextLine();
                    System.out.println("\nEnter your key word: ");
                    String key = scanner.nextLine();
                    System.out.println("Decrypting... by a key of " + key);
                    System.out.println(CaesarCipher.decryptByKeyWord(message, key) + "\n");
                    break;
                }
                default:
                    System.out.println(invalid);
            }
        }
    }


    public static void runConsoleTransposition(Scanner scanner) {
        while (true) {
            System.out.println("-- TRANSPOSITION CIPHER --");
            System.out.println(mainMenu);
            System.out.println("1. Encrypt by Number Key");
            System.out.println("2. Encrypt by Word Key");
            System.out.println("3. Decrypt by Number Key");
            System.out.println("4. Decrypt by Word Key");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(invalidNum);
                continue;
            }

            switch (choice) {
                case 0:
                    System.out.println(returnToMain);
                    return;

                case 1: {
                    System.out.println("Encrypt by Number Key");
                    System.out.println(mainMenu);
                    String message = scanner.nextLine();
                    System.out.println("\nEnter your key: ");
                    int key = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.println("Encrypting... by a key of " + key);
                    System.out.println(TranspositionCipher.encryptNum(message, key));
                    break;
                }

                case 2: {
                    System.out.println("Encrypt by Word Key");
                    System.out.println("\nEnter your message: ");
                    String message = scanner.nextLine();
                    System.out.println("\nEnter your key: ");
                    String key = scanner.nextLine();
                    System.out.println("Encrypting... by a key of " + key);
                    System.out.println(TranspositionCipher.encryptWord(message, key));
                    break;
                }

                case 3: {
                    System.out.println("Decrypt by Number Key");
                    System.out.println("\nEnter your message: ");
                    String message = scanner.nextLine();
                    System.out.println("\nEnter your key: ");
                    int key = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.println("Decrypting... by a key of " + key);
                    System.out.println(TranspositionCipher.decryptNum(message, key));
                    break;
                }

                case 4: {
                    System.out.println("Decrypt by Word Key");
                    System.out.println("\nEnter your message: ");
                    String message = scanner.nextLine();
                    System.out.println("\nEnter your key: ");
                    String key = scanner.nextLine();
                    System.out.println("Decrypting... by a key of " + key);
                    System.out.println(TranspositionCipher.decryptWord(message, key));
                    break;
                }

                default:
                    System.out.println(invalid);
            }
        }
    }

    public static void runConsoleSpaceBreak(Scanner scanner) {
        System.out.println("-- SPACE BREAK CIPHER --");
        System.out.println("Enter the message to remove spaces: ");
        String message = scanner.nextLine();
        String noSpacesMessage = SpaceBreakCipher.removeSpaces(message);
        System.out.println("Message without spaces:\n" + noSpacesMessage + "\n");
    }

    public static void runConsoleEveryNth(Scanner scanner) {
        while(true){
            System.out.println("-- EVERY NTH CIPHER --");
            System.out.println("0. Return to Main Menu");
            System.out.println("1. Display by every nth");
            System.out.println("2. Display by staircase");
            System.out.println("3. Display by every # of letters");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch(choice){
                case 1:
                    System.out.println("Enter the message to encode: ");
                    String message = scanner.nextLine();
                    ArrayList<String> everyNthMessage = EveryNthCipher.everyNth(message);
                    for (int words = 0; words < everyNthMessage.size(); words++) {
                        switch(words+1){
                            case 1:
                                System.out.println("Every 1st: " + everyNthMessage.get(words));
                                break;
                            case 2:
                                System.out.println("Every 2nd: " + everyNthMessage.get(words));
                                break;
                            case 3:
                                System.out.println("Every 3rd:" + everyNthMessage.get(words));
                                break;
                            default:
                                System.out.println("Every " + (words+1) + "th: " + everyNthMessage.get(words));
                                break;
                        }
                    }
                    break;
                case 2:
                    System.out.println("Enter the message to encode: ");
                    String staircaseMessage = scanner.nextLine();
                    String staircaseEncodedMessage = EveryNthCipher.displayByStaircase(staircaseMessage);
                    System.out.println("Encoded message:\n" + staircaseEncodedMessage + "\n");
                    break;
                case 3:
                    System.out.println("Enter the message to encode: ");
                    String everyNthMessage2 = scanner.nextLine();
                    System.out.println("Enter the number of letters to skip per letter: ");
                    int letters = scanner.nextInt();
                    System.out.println("Enter the starting index: ");
                    int start = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    String everyNthEncodedMessage = EveryNthCipher.displayByEveryLetter(everyNthMessage2, letters, start);
                    System.out.println("Encoded message:\n" + everyNthEncodedMessage + "\n");
                    break;
                case 0:
                    System.out.println(returnToMain);
                    return;
                default:
                    System.out.println(invalid);
            }
        }
    }

    public static void runConsoleReverse(Scanner scanner) {
        System.out.println("-- REVERSE CIPHER --");
        System.out.println("Enter the message to reverse: ");
        String message = scanner.nextLine();
        String reversedMessage = ReverseCipher.reverse(message);
        System.out.println("\nReversed message:\n");
        System.out.println(reversedMessage + "\n");
    }

    public static void runConsoleBacon(Scanner scanner) {
        while (true) {
            System.out.println("-- BACON CIPHER --");
            System.out.println("0. Return to Main Menu");
            System.out.println("1. Encrypt (Letters to Bacon)");
            System.out.println("2. Decrypt (Bacon to Letters)");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter your message: ");
                    String message = scanner.nextLine();
                    try {
                        String encodedMessage = BaconCipher.encrypt(message);
                        System.out.println("Encrypting...");
                        System.out.println(encodedMessage + "\n");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Enter your message:: ");
                    String encodedMessage = scanner.nextLine();
                    try {
                        String decodedMessage = BaconCipher.decrypt(encodedMessage);
                        System.out.println("Decrypting...");
                        System.out.println(decodedMessage + "\n");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println(returnToMain);
                    return;
                default:
                    System.out.println(invalid);
            }
        }
    }
}

