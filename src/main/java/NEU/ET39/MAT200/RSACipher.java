package NEU.ET39.MAT200;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

public class RSACipher {
    // RSA Cipher is a public key encryption algorithm that uses two keys: a public key for encryption and a private key for decryption.
    // It is based on the mathematical properties of prime numbers and modular arithmetic.
    // The security of RSA relies on the difficulty of factoring large composite numbers.

    //code needs to generate a public and private key, encrypt messages with a public key, and decrypt messages with our private key

    // The public key consists of two numbers: n (the product of two large prime numbers) and e (the public exponent).
    // The private key consists of two numbers: n (the same product of two large prime numbers) and d (the private exponent).
    // The encryption process is done using the formula: ciphertext = (plaintext^e) mod n
    // The decryption process is done using the formula: plaintext = (ciphertext^d) mod n
    // The keys are generated using the following steps:
    // 1. Choose two distinct large prime numbers p and q.
    // 2. Compute n = p * q.
    // 3. Compute the totient: φ(n) = (p - 1)(q - 1).
    // 4. Choose an integer e such that 1 < e < φ(n) and gcd(e, φ(n)) = 1.
    // 5. Compute d such that d * e ≡ 1 (mod φ(n)).
    // 6. The public key is (n, e) and the private key is (n, d).


    private BigInteger p, q, n, phi, e, d;
    private final int BIT_LENGTH = 512;

    public RSACipher() {
        generateKeys();
    }

    private void generateKeys() {
        SecureRandom rand = new SecureRandom();
        p = BigInteger.probablePrime(BIT_LENGTH, rand);
        q = BigInteger.probablePrime(BIT_LENGTH, rand);
        n = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        e = new BigInteger("65537"); // Common choice
        while (!phi.gcd(e).equals(BigInteger.ONE)) {
            e = e.add(BigInteger.TWO);
        }

        d = e.modInverse(phi);
    }

    // Manually initialize keys (for loaded private key use)
    public void loadPrivateKey(BigInteger d, BigInteger n) {
        this.d = d;
        this.n = n;
    }

    public BigInteger encryptBlock(String message, BigInteger pubE, BigInteger pubN) {
        BigInteger messageInt = new BigInteger(message.getBytes());
        if (messageInt.compareTo(pubN) >= 0) {
            throw new IllegalArgumentException("Message is too large to encrypt with this public key");
        }
        return messageInt.modPow(pubE, pubN);
    }

    public String decryptBlock(BigInteger ciphertext) {
        if (d == null || n == null) {
            throw new IllegalStateException("Private key not initialized.");
        }
        byte[] decryptedBytes = ciphertext.modPow(d, n).toByteArray();
        return new String(decryptedBytes);
    }

    public void savePublicKeyToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(e.toString() + "\n");
            writer.write(n.toString() + "\n");
            System.out.println("Public key saved to " + filename);
        } catch (IOException ex) {
            System.err.println("Error writing public key: " + ex.getMessage());
        }
    }

    public void savePrivateKeyToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(d.toString() + "\n");
            writer.write(n.toString() + "\n");
            System.out.println("Private key saved to " + filename + " (do NOT share this file!)");
        } catch (IOException ex) {
            System.err.println("Error writing private key: " + ex.getMessage());
        }
    }

    public void saveEncryptedMessage(BigInteger encrypted, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(encrypted.toString());
            System.out.println("Encrypted message saved to " + filename);
        } catch (IOException ex) {
            System.err.println("Error writing encrypted message: " + ex.getMessage());
        }
    }

    public static BigInteger[] loadPublicKeyFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            BigInteger pubE = new BigInteger(reader.readLine());
            BigInteger pubN = new BigInteger(reader.readLine());
            return new BigInteger[]{pubE, pubN};
        } catch (IOException ex) {
            System.err.println("Error reading public key: " + ex.getMessage());
            return null;
        }
    }

    public static BigInteger[] loadPrivateKeyFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            BigInteger privD = new BigInteger(reader.readLine());
            BigInteger privN = new BigInteger(reader.readLine());
            return new BigInteger[]{privD, privN};
        } catch (IOException ex) {
            System.err.println("Error reading private key: " + ex.getMessage());
            return null;
        }
    }

    public BigInteger getPublicKeyExponent() {
        return e;
    }

    public BigInteger getPublicKeyModulus() {
        return n;
    }

    public BigInteger getPrivateKeyExponent() {
        return d;
    }

}
