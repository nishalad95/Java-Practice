// Nisha Lad,
// This class creates a new Anagrams object that takes a set of words in a dictionary, finds all
// anagram phrases that match a given word/phrase.
// The client can obtain a set of all words that can be made from the letters of a 
// given phrase, all permutations & combinations of multi-word anagrams that can be made from 
// a given phrase using all letters of the given phrase, & all permutations and combinations
// of multi-word anagrams that can be made from a given phrase up to a given max number of words.

import java.util.*;

public class Anagrams {
	private Map<String, LetterInventory> dictMap;

	// Method initializes a new Anagram solver object 
	// over the given Set<String> parameter dictionary of words 
	// Post: original Set<String> dictionary is unchanged
	// Throws an IllegalArgumentException if the parameter dictionary is null
	public Anagrams(Set<String> dictionary) {
		if (dictionary == null) {
			throw new IllegalArgumentException();
		}
		dictMap = new HashMap<String, LetterInventory>();
		for (String word : dictionary) {
			dictMap.put(word, new LetterInventory(word));
		}
	}

	// Method returns a set of all words from the dictionary that can be made using some or 
	// all of the letters in the given String parameter phrase in alphabetical order
	// If no words can be made, an empty set is returned
	// Throws an IllegalArgumentException if the parameter phrase is null
	public SortedSet<String> getWords(String phrase) {
		if (phrase == null) {
			throw new IllegalArgumentException();
		}
		SortedSet<String> validWords = new TreeSet<String>();
		LetterInventory phraseCount = new LetterInventory(phrase);
		for (String word : dictMap.keySet()) {
			if (phraseCount.subtract(dictMap.get(word)) != null) {
				validWords.add(word);
			}
		}
		return validWords;
	}
	
	// Method finds and prints all anagrams (including multi-word anagrams), combinations and
	// permutations, that can be formed using all of the letters in the given String parameter
	// 'phrase'. If 'phrase' is an empty String, then no output is generated.
	// Throws an IllegalArgumentException if the parameter phrase is null
	public void print(String phrase) {
		print(phrase, phrase.length());
	}
	
	// Method finds and prints all anagrams (including multi-word anagrams), combinations and
	// permutations, that can be formed using all of the letters in the given String parameter
	// 'phrase', and that include at most the int parameter 'max' number of words total.
	// If 'max' equals 0, then all combinations and permutations of anagrams possible are printed
	// If 'phrase' is an empty String, then no output is generated
	// Throws IllegalArgumentException if the parameter 'phrase' is null or is 'max' is negative
	public void print(String phrase, int max) {
		if (phrase == null || max < 0) {
			throw new IllegalArgumentException();
		}
		if (phrase.length() > 0) {
			if (max == 0) {
				print(phrase);
			} else {
				SortedSet<String> words = getWords(phrase);
				print(words, new LetterInventory(phrase), new Stack<String>(), max);
			}
		}
	}
	
	// Method finds and prints all possible combinations and permutations of anagrams,
	// including multi-word anagrams, and that include at most the int parameter 'max'
	// number of words total
	// Method accepts a SortedSet<String> 'words' of possible words to choose from,
	// a LetterInventory 'total' keeping track of the total number of each letter, 
	// a Stack<String> 'soFar' containing a possible anagram solution and int 'max'
	// If 'max' equals 0, then all combinations and permutations of anagrams possible are printed
	private void print(SortedSet<String> words, LetterInventory total, 
			Stack<String> soFar, int max) {
		if (total.size() == 0) {
			System.out.println(soFar);
		} else {
			for (String chosen : words) {
				if (total.subtract(dictMap.get(chosen)) != null && max > 0) {
					soFar.push(chosen);
					print(words, total.subtract(dictMap.get(chosen)), soFar, max-1);
					soFar.pop();
					total.add(dictMap.get(chosen));
				}
			}
		}
	}
	
}
