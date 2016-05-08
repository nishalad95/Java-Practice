package TextAnalyser;
// Author: Nisha Lad, 6th May 2016.
// This library allows the client to analyse a text file and to obtain statistics about it
// The client can retrieve the total number of words in the file, the total number of lines,
// the average number of letters per word and the most common letter in the file.

import java.util.*;
import java.io.*;

public class textInventory {
	private Scanner fileScan;
	private Map<String, Integer> wordTally; // Maps the word onto frequency it appears
	private SortedMap<Character, Integer> letterTally; // Maps the letter onto frequency it appears
	private int numLines;
	private int numWords;
	
	// Initialises a new textAnalyser object to analyse the text file passed as parameter: filename
	// Throws FileNotFoundException if the file is not located in current directory
	// Throws IllegalStateException if the file is empty or if filename is null
	public textInventory(String filename) throws FileNotFoundException {
		fileScan = new Scanner(new File(filename));
		if (!fileScan.hasNext() || filename == null) {
			throw new IllegalStateException("File is empty.");
		}
		wordTally = new HashMap<String, Integer>();
		letterTally = new TreeMap<Character, Integer>();
		numLines = 0;
		numWords = 0;
		while(fileScan.hasNextLine()) {
			numLines++;
			String line = fileScan.nextLine();
			Scanner lineScan = new Scanner(line);
			constructMaps(lineScan);
			lineScan.close();
		}
		fileScan.close();
	}
	
	// Method checks if the passed word has every character in it part of the 
	// English alphabet and returns true. Returns false otherwise.
	private boolean checkWord(String word) {
		if(word.equals("")){ return false;}
		for (char c : word.toCharArray()) {
			if (c <= 'a' && c>= 'z') {
				return false;
			}
		}
		return true;
	}
	
	// Method constructs a map containing the word onto the frequency it appears in the text file
	// and a map that contains the letter onto the frequency it appears in the text file
	private void constructMaps(Scanner lineScan) {
		while (lineScan.hasNext()) {
			String word = lineScan.next();
			// Only consider words, disregard numbers or special characters
			word = word.replaceAll("[^a-zA-Z ]", "").toLowerCase();
			if (checkWord(word)) {
				numWords++;
				// Add the word and number of occurrences to the Map
				if (!wordTally.containsKey(word)) {
					wordTally.put(word, 0);
				}
				wordTally.put(word, wordTally.get(word) + 1);
				// Add the character and number of occurrences to the Map
				for (char c : word.toCharArray()) {
					c = Character.toLowerCase(c);
					if (!letterTally.containsKey(c)) {
						letterTally.put(c, 0);
					}
					letterTally.put(c, letterTally.get(c) + 1);
				}
			}
		}
	}
	
	// Returns the most common letter used within the text file
	// If more than one letter is the most common, the method will return
	// the letter that first occurs in the alphabet
	public char mostCommonLetter() {
		int maxValue = -1;
		char maxLetter = letterTally.firstKey();
		for (Map.Entry<Character, Integer> entry : letterTally.entrySet()) {
			if (maxValue < entry.getValue()){
				maxValue= entry.getValue();
				maxLetter = entry.getKey();
			}
		}
		return maxLetter;
	}
	
	// Returns the total number of words in text file
	public int getWordCount() {
		return numWords;
	}
	
	// Returns the total number of lines in the text file
	// Inclusive of empty lines interspersed between lines of text
	public int getLineCount() {
		return numLines;
	}
	
	// Returns the average number of letters per word to 1 decimal place
	public double averageNumLetter() {
		double average = 0.0;
		for (char c : letterTally.keySet()) {
			average += letterTally.get(c);
		}
		return Math.round(average * 10.0 / numWords) / 10.0;
	}

}
