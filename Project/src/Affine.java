import java.io.*;
import java.util.*;
public class Affine {
	public static void main(String[] args){
		Scanner kb = new Scanner(System.in);
		System.out.print("Key 1: ");
		int k1 = kb.nextInt();
		System.out.print("Key 2: ");
		int k2 = kb.nextInt();
		kb.nextLine();
		System.out.print("File to encrypt: ");
		String fileName = read(kb.nextLine()); //testing
		kb.close();
		String ct = encrypt(k1, k2, fileName);
		System.out.println("Ciphertext: " + ct);
		System.out.println("Plaintext:  " + decrypt(k1, k2, ct));
	}
	public static String read(String fileName){
		Scanner file = null;
		String plaintext = "";
		try{
			file = new Scanner(new FileInputStream(fileName));
		}catch(FileNotFoundException e){
			System.out.println("File was not found or could not be opened.");
			System.exit(0);
		}
		while(file.hasNextLine()){
			String temp = file.nextLine();
			temp += "\n";
			plaintext += temp;
		}
		file.close();
		return plaintext;
	}
	public static String encrypt(int k1, int k2, String pt){
		pt = pt.toLowerCase();
		String ciphertext = "";
		for(int i=0; i<pt.length(); i++){
			char temp = pt.charAt(i);
			if(Character.isLetter(temp)){
				temp = (char) ((k1 * (temp - 'a') + k2) % 26 + 'a');
			}
			ciphertext += temp;
		}
		return ciphertext;
	}
	public static String decrypt(int k1, int k2, String ct){
		ct = ct.toLowerCase();
		String plaintext = "";
		int k1inv = 0;
		int tempi = 0;
		for(int i=0; i<26; i++){
			tempi = (k1 * i) % 26;
			if(tempi == 1)
				k1inv = i;
		}
		for(int i=0; i<ct.length(); i++){
			char temp = ct.charAt(i);
			if(Character.isLetter(temp))
				temp = (char) ((k1inv * (temp - 'a' - k2 + 26)) % 26 + 'a');
			plaintext += temp;
		}
		return plaintext;
	}
}
