package homeworkAssignments;
//Nisha Lad, CSE 143, Spring 2016, Section AA - TA: Garrett Deardorff
//Programming Homework Assignment #1, 31/03/16
//This class keeps track of an inventory of letters of the alphabet

public class LetterInventory {
	private int[] count;
	private int size;
	public static final int NUM_CHARS = 26;
	
	//Pre: Takes in a String as a parameter
	//Post: Initialises a new empty inventory of capacity NUM_CHARS
	public LetterInventory(String data) {
		count = new int[NUM_CHARS];
		data = data.toLowerCase();
		for (int i = 0; i < data.length(); i++) {
			int letter = data.charAt(i) - 'a';
			if (letter < NUM_CHARS && letter >= 0) {
				count[letter]++;
				size++;
			}
		}
	}
	
	//Initialises a new Letter Inventory with an default string.
	public LetterInventory() {
		this("");
	}

	//Returns: count of how many of the parameter letter are in the inventory
	//Pre: letter is part of the alphabet
	//throws IllegalArgumentException otherwise
	public int get(char letter) {
		letter = Character.toLowerCase(letter);
		checkIndex(letter, 1);
		return count[letter - 'a'];
	}

	//Sets the count for given letter to the given value
	//Pre: parameter letter is part of the alphabet and 0 <= value  
	//throws IllegalArgumentException otherwise
	public void set(char letter, int value) {
		letter = Character.toLowerCase(letter);
		checkIndex(letter, value);
		size += (value - count[letter - 'a']);
		count[letter - 'a'] = value;
	}
	
	//Return: the sum of all the counts in the inventory
	public int size() {
		return size;
	}
	
	//Return: true if the inventory is empty, false otherwise
	public boolean isEmpty() {
		return size == 0;
	}

	//Returns a String representation of the inventory consisting
	//of its letter elements and enclosed by square brackets
	//letters are in sorted order and in lower case
	public String toString() {
		String result = "[";
	      for (int i = 0; i < NUM_CHARS; i++) {
	    	  for (int j = 1; j <= count[i]; j++) {
	    		  result += (char) (i + 'a');
	    	  }
	      }
	      result += "]";
	      return result;
	}

	//Returns a new LetterInventory array consisting of the sum of 
	//this letter inventory and the other LetterInventory passed as a parameter
	public LetterInventory add(LetterInventory other) {
		LetterInventory added = new LetterInventory();
		for (int i = 0; i < NUM_CHARS; i++) {
			added.set((char)(i + 'a'), other.get((char) (i + 'a')) + count[i]);
		}
		return added;
	}

	//Returns a new LetterInventory array consisting of the difference
	//by subtracting the counts of the other LetterInventory from this letter inventory
	//Pre: difference in counts is positive, returns null otherwise
	public LetterInventory subtract(LetterInventory other) {
		LetterInventory subtracted = new LetterInventory();
		for (int i = 0; i < NUM_CHARS; i++) {
			int num = count[i] - other.get((char) (i + 'a'));
			if (num < 0) {
				return null;
			} else {
				subtracted.set((char)(i + 'a'), num);
			}
		}
		return subtracted;
	}
	
	//Internal test: If the given letter is not part of the alphabet
	//or if value < 0 throws IllegalArgumentException
	private void checkIndex(char letter, int value) {
		if (letter < 'a' || letter > 'z' || value < 0) {
			throw new IllegalArgumentException();
		}
	}

}
