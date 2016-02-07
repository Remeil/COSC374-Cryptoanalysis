import java.io.*;
import java.util.*;
public class Affine {
	public static void main(String[] args){
		Scanner kb = new Scanner(System.in);
		System.out.print("Key 1: ");
		int k1 = kb.nextInt();
		System.out.print("Key 2: ");
		int k2 = kb.nextInt();
		//String pt = read(kb.nextLine()); //testing
		kb.close();
		
		System.out.println("Ciphertext: " + encrypt(k1, k2, "spring"));
	}
	public static String read(String fileName){
		Scanner file = null;
		String plaintext = "";
		try{
			file = new Scanner(new FileInputStream(fileName));
		}catch(FileNotFoundException e){
			System.out.println("File  was not found");
			System.out.println("or could not be opened.");
			System.exit(0);
		}
		while(file.hasNext()){
			if(file.next() != " ")
				plaintext.concat(file.next());
		}
		return plaintext;
	}
	public static String encrypt(int k1, int k2, String pt){
		String ciphertext = "";
		for(int i=0; i<pt.length(); i++){
			char temp = pt.charAt(i);
			if(temp != ' '){
				temp = (char) ((k1 * (temp - 'a') + k2) % 26 + 'a');
			}
			ciphertext += temp;
		}
		return ciphertext;
	}
}
