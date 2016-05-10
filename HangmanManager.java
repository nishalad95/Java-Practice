// Nisha Lad, CSE 143, Spring 2016, Section AA - TA: Garrett Deardorff
// Programming Homework Assignment #5, 28/04/16
// This class creates a new HangmanManager object and manages the current state of the game
// as each guess is made by the user. The client can obtain the number of occurrences of a 
// guessed character in the new pattern, obtain the current pattern of the guessed word
// obtain the current set of words being considered as options, current set of guessed letters
// and the number of guesses the player has left at any state in the game

import java.util.*;

public class HangmanManager{
	private int numGuesses;
	private SortedSet<Character> charGuesses;
	private Set<String> wordsSet;
	private String pattern;
	
	// Method constructs a new HangmanManager object and initialises the state of the game
	// The set of words to be guessed from by the user is chosen from the List<String> dictionary
	// all words are of size 'length' and the maximum number of wrong guesses is set to 'max'
	// IllegalArgumentException is thrown if length < 1 or if max < 0 
	public HangmanManager(List<String> dictionary, int length, int max) {
		if (length < 1 || max < 0) {
			throw new IllegalArgumentException();
		}
		numGuesses = max;
		charGuesses = new TreeSet<Character>();
		wordsSet = new TreeSet<String>();
		pattern = new String(new char[length]).replaceAll("\0", "- ").trim();
		for (int i = 0; i < dictionary.size(); i++) {
			if (dictionary.get(i).length() == length) {
				wordsSet.add(dictionary.get(i));
			}
		}
	}
	
	// This method records the next guess made by the user
	// then decides what set of words to use as options for the user on the next guess
	// Returns the number of occurrences of the guessed letter in the new pattern 
	// IllegalStateException is thrown if the number of guesses left is < 1 or if the list of
	// words to pick from is empty, IllegalArgumentException is thrown if the list of words
	// to pick from is nonempty and if the char 'guess' has already been guessed
	public int record(char guess) {
		if (wordsSet.isEmpty() || numGuesses < 1) {
			throw new IllegalStateException();
		} else if (charGuesses.contains(guess)) {
			throw new IllegalArgumentException();
		}
		charGuesses.add(guess);
		Map<String, Set<String>> optionMap = new TreeMap<String, Set<String>>();
		for (String word : wordsSet) {
			String createdPat = makePattern(word);
			createChoices(optionMap, createdPat, word);
		}
		String maxSet = findMaximumSet(optionMap);
		wordsSet = optionMap.get(maxSet);
		if (pattern.equals(maxSet)) {
			numGuesses--;
		}
		pattern = maxSet;
		int numLetter = pattern.length() - pattern.replace(guess + "", "").length();
		return numLetter;
	}
	
	// Method creates different choices for the hangman game 
	// choices consist of a set of words that match their corresponding pattern
	// ready for the evil hangman to choose the next set of words to be considered as options
	private void createChoices(Map<String, Set<String>> map, String createdPat, String word) {
		if (!map.containsKey(createdPat)) {
			map.put(createdPat, new TreeSet<String>());
		}
		Set<String> currentSet = map.get(createdPat);
		currentSet.add(word);
		map.put(createdPat, currentSet);
	}
	
	// Method finds the maximum set of words for each corresponding pattern
	// and returns the pattern, containing the maximum number of words, as a String
	private String findMaximumSet(Map<String, Set<String>> optionMap)  {
		int current = 0;
		String maxKey = "";
		for (String pattern : optionMap.keySet()) {
			if (current < optionMap.get(pattern).size()) {
				maxKey = pattern;
				current = optionMap.get(pattern).size();
			}
		}
		return maxKey;
	}
	
	// Makes and returns the corresponding pattern for a word to be displayed by the hangman game
	// in form of a String, taking into account the previous guesses made
	// letters that have not been guessed displayed as '-'
	// If the set of words to be guessed from is empty, IllegalStateException is thrown
	private String makePattern(String word) {
		String pattern = "";
		for (int i = 0; i < word.length(); i++) {
			if (!charGuesses.contains(word.charAt(i))) {
				pattern = pattern + "- ";
			} else {
				pattern = pattern + word.charAt(i) + " ";
			}
		}
		return pattern.trim();
	}
	
	// Returns the current pattern to be displayed by the hangman game, taking into account
	// guesses already been made, letters that have not been guessed displayed as '-'
	// If the set of words to be guessed from is empty, IllegalStateException is thrown
	public String pattern() {
		if (wordsSet.isEmpty()) {
			throw new IllegalStateException();
		}
		return pattern;
	}
	
	// Returns the current set of words being considered as options by the HangmanManager
	public Set<String> words() {
		return wordsSet;
	}
	
	// Returns the number of guesses the player has left at any state in the game
	public int guessesLeft() {
		return numGuesses;
	}
	
	// Returns the current set of letters already guessed by the user
	public SortedSet<Character> guesses() {
		return charGuesses;
	}
}
