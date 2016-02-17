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
	    mode = scan.nextLine().toUpperCase().charAt(0);
	}

	String plaintextToAnalyze = "";
	String ciphertextToAnalyze = "";

	//Ensure we successfully open and read files.
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


	HashMap<String, Double> plainFreqMap = null;
	HashMap<String, Double> cipherFreqMap = null;

	switch (mode) {
	case 'L':
	    plainFreqMap = ComputeRelativeFrequencyLetters(plaintextToAnalyze);
	    cipherFreqMap = ComputeRelativeFrequencyLetters(ciphertextToAnalyze);
	    break;
	case 'D':
	    plainFreqMap = ComputeRelativeFrequencyDigraphs(plaintextToAnalyze);
	    cipherFreqMap = ComputeRelativeFrequencyDigraphs(ciphertextToAnalyze);
	    break;
	}

	System.out.println("Plaintext Histogram: ");
	drawHistogram(plainFreqMap);

	System.out.println("\nCiphertext Histogram: ");
	drawHistogram(cipherFreqMap);
    }

    //Given a string, compute the relative frequency of each letter in that string.
    private static HashMap<String, Double> ComputeRelativeFrequencyLetters(String text) {
	long length = text.length();
	HashMap<String, Integer> freqMap = new HashMap<String, Integer>();

	//Search through the string and count each character.
	for (int i = 0; i < length; i++) {
	    char character = text.charAt(i);

	    if ((character >= 'A' && character <= 'Z'))
	    {
		int freq;
		//If we haven't seen this character yet, frequency is 1, otherwise, increment it.
		if (freqMap.get(Character.toString(character)) == null) {
		    freq = 1;
		}
		else {
		    freq = freqMap.get(Character.toString(character)) + 1;
		}
		freqMap.put(Character.toString(character), freq);
	    }
	}

	HashMap<String, Double> relativeFreqMap = new HashMap<String, Double>();
	long totalFreq = 0;

	//Find total frequency by adding all frequencies together.
	for (Entry<String, Integer> entry : freqMap.entrySet()) {
	    totalFreq += entry.getValue();
	}

	//Find relative frequency of all letters.
	for (Entry<String, Integer> entry : freqMap.entrySet()) {
	    double relativeFreq = (double)entry.getValue() / (double)totalFreq;
	    relativeFreqMap.put(entry.getKey(), relativeFreq);
	}

	return relativeFreqMap;
    }

    //Given a text string, calculate the frequency of all Digraphs in that string.
    private static HashMap<String, Double> ComputeRelativeFrequencyDigraphs(String text) {
	long length = text.length();

	//We need at least 2 characters.
	if (length <= 1) {
	    return new HashMap<String, Double>();
	}

	HashMap<String, Integer> freqMap = new HashMap<String, Integer>();

	//Look at this character, and the next one to determine digraphs.
	for (int i = 0; i < length - 1; i++) {
	    char character1 = text.charAt(i);
	    char character2 = text.charAt(i+1);

	    if (((character1 >= 'A' && character1 <= 'Z')) &&
		((character2 >= 'A' && character2 <= 'Z')))
	    {
		int freq;
		//If we haven't seen this combination yet, add it with a frequency of 1.
		if (freqMap.get("" + character1 + character2) == null) {
		    freq = 1;
		}
		else {
		    freq = freqMap.get("" + character1 + character2) + 1;
		}
		
		freqMap.put(("" + character1 + character2), freq);
	    }
	}

	HashMap<String, Double> relativeFreqMap = new HashMap<String, Double>();
	long totalFreq = 0;

	//Find total frequency by adding all frequencies together.
	for (Entry<String, Integer> entry : freqMap.entrySet()) {
	    totalFreq += entry.getValue();
	}

	//Find relative frequency of all letters.
	for (Entry<String, Integer> entry : freqMap.entrySet()) {
	    double relativeFreq = (double)entry.getValue() / (double)totalFreq;
	    relativeFreqMap.put(entry.getKey(), relativeFreq);
	}

	return relativeFreqMap;
    }

    //Given a hashmap of relative frequencies, draw a histogram of those frequencies.
    private static void drawHistogram(HashMap<String, Double> map) {
	double maximumRelativeFrequency = 0;

	//Find maximum relative frequency.
	for (Entry<String, Double> entry : map.entrySet()) {
	    if (maximumRelativeFrequency < entry.getValue()) {
		maximumRelativeFrequency = entry.getValue();
	    }
	}

	for (Entry<String, Double> entry : map.entrySet()) {
	    System.out.print(entry.getKey() + ": ");
	    
	    System.out.printf("%5.2f%% : ", entry.getValue()*100);
	    
	    //Scale histogram to largest relative frequency.
	    int stars = (int)((entry.getValue() / maximumRelativeFrequency) * 70.0);
	    for (int i = 0; i < stars; i++)
	    {
		System.out.print("*");
	    }
	    System.out.println();
	}
    }
}
