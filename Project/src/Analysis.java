import java.io.IOException;
import java.util.Scanner;

/*
 * Analysis.java
 * Author: Johnathan Stiles
 * 
 * This class performs an relative frequency analysis on a text file.
 */
public class Analysis {
    public static void main(String[] args) {
	Scanner scan = new Scanner(System.in);
	char mode = ' ';

	while (mode != 'L' && mode != 'D')
	{
	    System.out.print("(L)etter or (D)igraph frequency? ");
	    mode = scan.next().toUpperCase().charAt(0);
	}

	String plaintextToAnalyze = "";
	String ciphertextToAnalyze = "";

	while (plaintextToAnalyze.equals("")) {
	    System.out.print("Plaintext file: ");
	    try {
		plaintextToAnalyze = FileOps.readFile(scan.nextLine());
	    }
	    catch (IOException e){}
	}
	
	while (ciphertextToAnalyze.equals("")) {
	    System.out.print("Ciphertext file: ");
	    try {
		ciphertextToAnalyze = FileOps.readFile(scan.nextLine());
	    }
	    catch (IOException e){}
	}
    }
}
