/*
 * Vigenere.java
 * Author: Johnathan Stiles
 * 
 * This class encrypts and decrypts a given string using the Vigenere cipher.
 */
public class Vigenere {
	public static String encrypt(String message, String key) {
		//pad key length to be at least as long as the message
		key = padKey(key, message.length());

		int[] shiftArray = new int[key.length()];
		for (int i = 0; i < key.length(); i++) {
			shiftArray[i] = key.charAt(i) - (int)'A';
		}

		return shift(message, shiftArray);
	}

	public static String decrypt(String message, String key) {
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
			character -= (int)'A';
			character += shift[i];
			character %= 26;
			character += (int)'A';
			out.append((char)character);
		}
		
		return out.toString();
	}
}
