package EvilHangman;
// Nisha Lad, CSE 143, Spring 2016, Section AA - TA: Garrett Deardorff
// Programming Homework Assignment #5, 28/04/16
// This class creates a new HangmanManager2 object, extends all methods from the 
// HangmanManager class and manages the current state of the game as each guess is made by the user
// Overridden methods: record() which now causes the user to lose the game on specific conditions when
// they have 1 guess left

import java.util.*;

public class HangmanManager2 extends HangmanManager{
	private Set<String> originalWordSet;
	private SortedSet<Character> originalCharSet;
	
	// Method constructs a new HangmanManager object and initialises the state of the game
	// to store the original set of words in parameter currentWordSet and the original set of 
	// character guesses in parameter currentCharSet (which is originally empty)
	public HangmanManager2(List<String> dictionary, int length, int max){
		super(dictionary, length, max);
		originalWordSet = new TreeSet<String>(dictionary);
		originalCharSet = new TreeSet<Character>();
	}
	
	// This method returns the number of occurrences of the guessed letter in the new 
	// pattern after a guess has been made.
	// if the number of guesses left is equal to 1 then it will force the user to lose
	// the game on the next guess if a specific word is found within the current set of words
	// as options, that does not contain the character 'guess'
	// if no such word can be found then the method returns the same behaviour as HangmanManager
	public int record(char guess) {
		if (guessesLeft() == 1) {
			for (String current : words()) {
				if (!current.contains(guess + "")) {
					super.words().clear();
					super.words().add(current);
					return super.record(guess);
				}
			}
		}
		return super.record(guess);
	}
	
	// Method creates an extra layer of protection for the current set of words being
	// considered as options by the game, if the current and previous set of words are
	// different after a guess has been made, so that this set of words cannot be modified.
	// If the current and previous set of words are the same after a guess has been made,
	// the layer of protection remains
	public Set<String> words() {
		if (originalWordSet.containsAll(super.words())) {
			return super.words();
		} else {
			Set<String> newSet = Collections.unmodifiableSet(originalWordSet);
			originalWordSet = newSet;
			return newSet;
		}
		
	}
	
	// Method creates an extra layer of protection for the current set of characters that
	// have been guessed by the user, if the current and previous set of guesses are
	// different after a guess has been made, so that this set of guesses cannot be modified.
	// If the current and previous set of guesses are the same after a guess has been made,
	// the layer of protection remains
	public SortedSet<Character> guesses() {
		if (originalCharSet.containsAll(super.guesses())) {
			return super.guesses();
		} else {
			SortedSet<Character> newCharSet = Collections.unmodifiableSortedSet(super.guesses());
			originalCharSet = newCharSet;
			return newCharSet;
		}
		
	}

}
