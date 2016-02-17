import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

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
		plaintextToAnalyze = FileOps.readFile(scan.nextLine()).toUpperCase();
	    }
	    catch (IOException e){}
	}

	while (ciphertextToAnalyze.equals("")) {
	    System.out.print("Ciphertext file: ");
	    try {
		ciphertextToAnalyze = FileOps.readFile(scan.nextLine()).toUpperCase();
	    }
	    catch (IOException e){}
	}
    }

    private static HashMap<String, Double> ComputeRelativeFrequencyLetters(String text) {
	long length = text.length();
	HashMap<String, Integer> freqMap = new HashMap<String, Integer>();

	for (int i = 0; i < length; i++) {
	    char character = text.charAt(i);
	    
	    if ((character >= 'A' && character <= 'Z') || character == ' ')
	    {
		int freq = freqMap.get(character) + 1;
		freqMap.put(Character.toString(character), freq);
	    }
	}

	HashMap<String, Double> relativeFreqMap = new HashMap<String, Double>();
	long totalFreq = 0;
	
	for (Entry<String, Integer> entry : freqMap.entrySet()) {
	    totalFreq += entry.getValue();
	}
	
	for (Entry<String, Integer> entry : freqMap.entrySet()) {
	    double relativeFreq = (double)entry.getValue() / (double)totalFreq;
	    relativeFreqMap.put(entry.getKey(), relativeFreq);
	}
	
	return relativeFreqMap;
    }
    
    private static HashMap<String, Double> ComputeRelativeFrequencyDigraphs(String text) {
	long length = text.length();
	
	if (length <= 1) {
	    return new HashMap<String, Double>();
	}
	
	HashMap<String, Integer> freqMap = new HashMap<String, Integer>();

	for (int i = 0; i < length - 1; i++) {
	    char character1 = text.charAt(i);
	    char character2 = text.charAt(i+1);
	    
	    if (((character1 >= 'A' && character1 <= 'Z') || character1 == ' ') &&
	       ((character2 >= 'A' && character2 <= 'Z') || character2 == ' '))
	    {
		int freq = freqMap.get(character1 + character2) + 1;
		freqMap.put(("" + character1 + character2), freq);
	    }
	}

	HashMap<String, Double> relativeFreqMap = new HashMap<String, Double>();
	long totalFreq = 0;
	
	for (Entry<String, Integer> entry : freqMap.entrySet()) {
	    totalFreq += entry.getValue();
	}
	
	for (Entry<String, Integer> entry : freqMap.entrySet()) {
	    double relativeFreq = (double)entry.getValue() / (double)totalFreq;
	    relativeFreqMap.put(entry.getKey(), relativeFreq);
	}
	
	return relativeFreqMap;
    }
}
