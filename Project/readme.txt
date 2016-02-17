==============  Readme  ==============

Authors : Isaiah Fuller & Johnathan Stiles

Files:
Vigenere.java
Affine.java
FileOps.java
Analysis.java

Compile with javac *.java

Run Affine Cipher with java Affine
Run Vigenere Cipher with java Vigenere
Run Analysis with java Analysis
FileOps is a utility, not intended to be run on it's own.

When Affine is run, it is expecting two integers for the key, and a file for the plaintext. It outputs the ciphertext, after being modified by the cipher.

When Vigenere is run, it is expecting a Encrypt or Decrypt mode. It then expects a file for the plaintext, and the key to be used to either encrypt or decrypt it. It outputs to a file specified by the user.

When Analysis is run, it is expecting a Letters or Diglyph mode. After this, it expects the plaintext and ciphertext files. It outputs a histogram of the relative frequencies of each letter/diglyph.