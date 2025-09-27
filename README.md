# MAT200 Cryptography Library

A comprehensive Java library implementing various classical and modern cryptographic algorithms, developed for the MAT200 course at Northeastern University.

## Overview

This library provides implementations of multiple cipher algorithms ranging from simple classical ciphers to modern RSA encryption. Each cipher includes both encryption and decryption capabilities with an interactive console interface for testing and demonstration.

## Supported Ciphers

### Unit 1 - Basic Ciphers
- **Bacon Cipher** - Binary encoding using A/B patterns
- **Space Break Cipher** - Simple space removal encoding
- **Every Nth Letter Cipher** - Extract patterns by position
- **Reverse Cipher** - Character order reversal

### Unit 2 - Transposition Ciphers
- **Transposition Cipher** - Column-based rearrangement with numeric or word keys

### Unit 3 - Substitution Ciphers
- **Caesar Cipher** - Alphabet shifting with numeric or keyword-based shifts
- **Multiplicative Cipher** - Modular multiplication-based substitution
- **Affine Cipher** - Combination of multiplicative and additive ciphers

### Unit 4 - Polyalphabetic Ciphers
- **Vigenère Cipher** - Keyword-based polyalphabetic substitution

### Final Project - Modern Cryptography
- **RSA Cipher** - Public-key cryptography with key generation and file I/O

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Installation

1. Clone the repository:
```bash
git clone https://github.com/snxethan/MAT200-LIBRARY.git
cd MAT200-LIBRARY
```

2. Build the project:
```bash
mvn compile
```

3. Run the interactive console:
```bash
mvn exec:java -Dexec.mainClass="NEU.ET39.MAT200.Main"
```

## Usage

### Interactive Console
The main application provides an interactive menu system to test all cipher implementations:

```java
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    runConsole(scanner);
    scanner.close();
}
```

### Using Individual Ciphers

#### Caesar Cipher Example
```java
// Encrypt with shift
String encrypted = CaesarCipher.encryptByShift("HELLO WORLD", 3);
// Result: "KHOOR ZRUOG"

// Decrypt with shift
String decrypted = CaesarCipher.decryptByShift("KHOOR ZRUOG", 3);
// Result: "HELLO WORLD"

// Encrypt with keyword
String encrypted = CaesarCipher.encryptByKeyWord("HELLO", "SECRET");
// Uses keyword to generate substitution alphabet
```

#### Vigenère Cipher Example
```java
// Encrypt with keyword
String encrypted = VigenereCipher.encrypt("ATTACK AT DAWN", "LEMON");
// Result: "LXFOPV EF RNHR"

// Decrypt with keyword
String decrypted = VigenereCipher.decrypt("LXFOPV EF RNHR", "LEMON");
// Result: "ATTACK AT DAWN"
```

#### RSA Cipher Example
```java
RSACipher rsa = new RSACipher();

// Save keys to file
rsa.savePublicKeyOnly("public_key.txt");
rsa.savePrivateKeyOnly("private_key.txt");

// Load and encrypt
BigInteger[] publicKey = RSACipher.loadPublicKeyFromTxt("public_key.txt");
BigInteger encrypted = rsa.encryptBlock("Hello RSA!", publicKey[0], publicKey[1]);

// Load and decrypt
BigInteger[] privateKey = RSACipher.loadPrivateKeyFromTxt("private_key.txt");
rsa.loadPrivateKey(privateKey[0], privateKey[1]);
String decrypted = rsa.decryptBlock(encrypted);
```

## Testing

The project includes JUnit tests. Run them with:

```bash
mvn test
```

## Project Structure

```
src/
├── main/java/NEU/ET39/MAT200/
│   ├── Main.java                 # Interactive console interface
│   ├── AffineCipher.java         # Affine cipher implementation
│   ├── BaconCipher.java          # Bacon cipher implementation
│   ├── CaesarCipher.java         # Caesar cipher implementation
│   ├── EveryNthCipher.java       # Every nth letter cipher
│   ├── MultiplicativeCipher.java # Multiplicative cipher implementation
│   ├── RSACipher.java            # RSA cipher implementation
│   ├── ReverseCipher.java        # Reverse cipher implementation
│   ├── SpaceBreakCipher.java     # Space break cipher implementation
│   ├── TranspositionCipher.java  # Transposition cipher implementation
│   └── VigenereCipher.java       # Vigenère cipher implementation
└── test/java/
    └── BaconCipherTest.java      # Unit tests
```

## Features

- **Interactive Console**: Menu-driven interface for all ciphers
- **File I/O Support**: RSA cipher supports key file generation and loading
- **Multiple Input Methods**: Support for both programmatic and console input
- **Comprehensive Error Handling**: Input validation and error messages
- **Educational Output**: Displays cipher alphabets and transformation steps

## Technical Details

### RSA Implementation
- **Key Length**: 1024-bit keys for security
- **Key Generation**: Uses `SecureRandom` for cryptographically secure random numbers
- **File Format**: Keys saved in readable text format
- **Block Processing**: Handles message size limitations

### Classical Cipher Features
- **Case Preservation**: Maintains original character casing
- **Non-alphabetic Character Handling**: Preserves spaces and punctuation
- **Alphabet Display**: Shows plaintext/ciphertext alphabet mappings
- **Input Validation**: Checks for valid keys and parameters

## Course Alignment

This library is structured to align with the MAT200 curriculum:
- **Unit 1**: Basic encoding and simple transformations
- **Unit 2**: Transposition-based cryptography
- **Unit 3**: Substitution cipher theory and implementation
- **Unit 4**: Advanced polyalphabetic methods
- **Final Project**: Modern public-key cryptography

## Contributing

This is a course project repository. For educational purposes, please:
1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add appropriate tests
5. Submit a pull request

## License

This project is developed for educational purposes as part of the MAT200 course at Northeastern University.

## Author(s)

- [**Ethan Townsend (snxethan)**](https://www.ethantownsend.dev)
