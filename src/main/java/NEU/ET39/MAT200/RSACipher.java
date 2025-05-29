package NEU.ET39.MAT200;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;

public class RSACipher {
    private BigInteger p, q, n, phi, e, d;
    private final int BIT_LENGTH = 1024;

    public RSACipher() {
        generateKeys();
    }

    private void generateKeys() {
        SecureRandom rand = new SecureRandom();
        p = BigInteger.probablePrime(BIT_LENGTH / 2, rand);
        q = BigInteger.probablePrime(BIT_LENGTH / 2, rand);
        n = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        do {
            e = BigInteger.probablePrime(BIT_LENGTH - 1, rand);
        } while (!phi.gcd(e).equals(BigInteger.ONE) || e.compareTo(phi) >= 0);

        d = e.modInverse(phi);
    }

    public void loadPrivateKey(BigInteger d, BigInteger n) {
        this.d = d;
        this.n = n;
    }

    public BigInteger encryptBlock(String message, BigInteger pubE, BigInteger pubN) {
        if (message == null || message.isEmpty()) {
            throw new IllegalArgumentException("Message cannot be null or empty.");
        }
        BigInteger messageInt = new BigInteger(message.getBytes());        if (messageInt.compareTo(pubN) >= 0) {
            throw new IllegalArgumentException("Message is too large to encrypt with this public key");
        }
        return messageInt.modPow(pubE, pubN);
    }

    public String decryptBlock(BigInteger ciphertext) {
        if (d == null || n == null) {
            throw new IllegalStateException("Private key not initialized.");
        }
        if (ciphertext == null || ciphertext.compareTo(BigInteger.ZERO) <= 0) {
            throw new IllegalArgumentException("Ciphertext must be a positive BigInteger.");
        }
        byte[] decryptedBytes = ciphertext.modPow(d, n).toByteArray();
        return new String(decryptedBytes);
    }

    public void savePublicKeyOnly(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(BIT_LENGTH + "," + n.toString() + "," + e.toString());
            System.out.println("Public key saved to " + filename + " (format: bitLength,n,e)");
        } catch (IOException ex) {
            System.err.println("Error saving public key: " + ex.getMessage());
        }
    }

    public void savePrivateKeyOnly(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(BIT_LENGTH + "," + n.toString() + "," + d.toString());
            System.out.println("Private key saved to " + filename + " (format: bitLength,n,d)");
        } catch (IOException ex) {
            System.err.println("Error saving private key: " + ex.getMessage());
        }
    }

    public static BigInteger[] loadPublicKeyFromTxt(String filename) {
        try {
            String line = Files.readString(Path.of(filename)).trim();
            String[] parts = line.split(",");
            if (parts.length != 3) throw new IllegalArgumentException("Expected format: bitLength,n,e");            BigInteger n = new BigInteger(parts[1]);
            BigInteger e = new BigInteger(parts[2]);
            return new BigInteger[]{e, n};
        } catch (IOException | IllegalArgumentException ex) {
            System.err.println("Error loading public key: " + ex.getMessage());
            return null;
        }
    }

    public static BigInteger[] loadPrivateKeyFromTxt(String filename) {
        try {
            String line = Files.readString(Path.of(filename)).trim();
            String[] parts = line.split(",");
            if (parts.length != 3) throw new IllegalArgumentException("Expected format: bitLength,n,d");
            BigInteger n = new BigInteger(parts[1]);
            BigInteger d = new BigInteger(parts[2]);
            return new BigInteger[]{d, n};
        } catch (IOException | IllegalArgumentException ex) {
            System.err.println("Error loading private key: " + ex.getMessage());
            return null;
        }
    }

    public void saveEncryptedMessage(BigInteger encrypted, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            if (encrypted == null) {
                throw new IllegalArgumentException("Encrypted message cannot be null.");
            }
            writer.write(encrypted.toString());            System.out.println("Encrypted message saved to " + filename);
        } catch (IOException ex) {
            System.err.println("Error writing encrypted message: " + ex.getMessage());
        }
    }

    public BigInteger getPublicKeyExponent() { return e; }
    public BigInteger getPublicKeyModulus() { return n; }
    public BigInteger getPrivateKeyExponent() { return d; }
}
