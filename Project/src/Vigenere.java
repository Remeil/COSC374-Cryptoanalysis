import java.io.IOException;
import java.util.Scanner;

/*
 * Vigenere.java
 * Author: Johnathan Stiles
 * 
 * This class encrypts and decrypts a given string using the Vigenere cipher.
 */
public class Vigenere {
    public static void main(String[] args) {
	Scanner scan = new Scanner(System.in);
	char mode = ' ';

	while (mode != 'E' && mode != 'D')
	{
	    System.out.print("(E)ncrypt or (D)ecrypt? ");
	    mode = scan.next().toUpperCase().charAt(0);
	}

	scan.nextLine();
	String plaintext = "", ciphertext = "", key;
	boolean success = false;
	switch (mode){
	case 'E':
	    while (plaintext.equals(""))
	    {
		try {
		    System.out.print("Enter plaintext file: ");
		    plaintext = FileOps.readFile(scan.nextLine());
		} catch (IOException e) {}
	    }
	    
	    System.out.print("Enter key: ");
	    key = scan.nextLine();
	    while (!success)
	    {
		try {
		    System.out.print("Enter output file: ");
		    FileOps.writeFile(scan.nextLine(), encrypt(plaintext, key));
		    System.out.println("Done!");
		    success = true;
		} catch (IOException e) {}
	    }
	    break;

	case 'D':
	    while (ciphertext.equals(""))
	    {
		try {
		    System.out.print("Enter ciphertext file: ");
		    ciphertext = FileOps.readFile(scan.nextLine());
		} catch (IOException e) {}
	    }
	    
	    System.out.print("Enter key: ");
	    key = scan.nextLine();
	    while (!success)
	    {
		try {
		    System.out.print("Enter output file: ");
		    FileOps.writeFile(scan.nextLine(), decrypt(ciphertext, key));
		    System.out.println("Done!");
		    success = true;
		} catch (IOException e) {}
	    }
	    break;
	}
    }

    private static String encrypt(String message, String key) {
	//pad key length to be at least as long as the message
	key = padKey(key, message.length());
	message = message.toUpperCase();

	int[] shiftArray = new int[key.length()];
	for (int i = 0; i < key.length(); i++) {
	    shiftArray[i] = key.charAt(i) - (int)'A';
	}

	return shift(message, shiftArray);
    }

    private static String decrypt(String message, String key) {
	//pad key length to be at least as long as the message
	key = padKey(key, message.length());

	int[] shiftArray = new int[key.length()];
	for (int i = 0; i < key.length(); i++) {
	    shiftArray[i] = key.charAt(i) - (int)'A';
	    shiftArray[i] *= -1;
	}

	return shift(message, shiftArray);
    }


    private static String padKey(String key, int length) {
	while (key.length() < length) {
	    key += key;
	}

	return key.toUpperCase();
    }

    private static String shift(String message, int[] shift) {
	StringBuilder out = new StringBuilder();

	for (int i = 0; i < message.length(); i++) {
	    int character = message.charAt(i);

	    if (character >= 'A' && character <= 'Z') {
		character -= (int)'A';
		character += shift[i];

		while (character < 0) {
		    character += 26;
		}

		character %= 26;
		character += (int)'A';
	    }
	    out.append((char)character);
	}

	return out.toString();
    }
}
