/**Nisha Lad
 * This class manipulates the grammar of a file in Backus-Naur Form. 
 * Clients can get all non-terminals of the grammar file, they can check if a certain symbol 
 * is a non-terminal in the defined grammar and generate random occurrences of the grammar. */

import java.util.*;

public class GrammarSolver {
	private Map<String, String[]> gramSolver; 
	
	/**Initialises a new GrammarSolver over the given List<String> @param rules
	 * throws IllegalArgumentException if rules is null, if rules is an empty list
	 * or if the list contains duplicate rules for one non-terminal
	 * Post: original List rules is unchanged */
	public GrammarSolver(List<String> rules) {
		if (rules.size() == 0 || rules == null) {
			throw new IllegalArgumentException();
		}
		gramSolver = new TreeMap<String, String[]>();
		for (int i = 0; i < rules.size(); i++) {
			String[] colonSplit = rules.get(i).split("::=");
			String[] separateRules = colonSplit[1].trim().split("[|]");
			if (!gramSolver.containsKey(colonSplit[0].trim())) {
				gramSolver.put(colonSplit[0].trim(), separateRules);
			} else {
				throw new IllegalArgumentException();
			}
		}
	}
	
	/**Takes in a String @param symbol
	 * Returns true if the given symbol is a non-terminal in the grammar
	 * Returns false otherwise 
	 * throws IllegalArgumentException if the symbol is null or an empty String */
	public boolean contains(String symbol) {
		checkString(symbol);
		return gramSolver.containsKey(symbol);
	}
	
	/**Returns all non-terminal symbols of the grammar in sorted order */
	public Set<String> getSymbols() {
		return gramSolver.keySet();
	}
	
	/**Takes in a String @param symbol
	 * Returns a random occurrence of the given grammar symbol as a String
	 * Pre: assumes that the symbol passed in is part of the grammar
	 * throws IllegalArgumentException if the symbol is null or an empty String */
	public String generate(String symbol) {
		checkString(symbol);
		Random rand = new Random();
		if (!gramSolver.containsKey(symbol)) {
			return symbol;
		} else {
			String[] rulesList = gramSolver.get(symbol);
			int num = rand.nextInt(rulesList.length); 
			String[] ruleToExecute = rulesList[num].trim().split("[ \t]+");
			String result = "";
			for (int i = 0; i < ruleToExecute.length; i++) {
				result += generate(ruleToExecute[i]) + " ";
			}
			return result.trim();
		}
	}
	
	/**Internal Method: Takes in a String @param symbol
	 * throws IllegalArgumentException if the symbol is null or an empty String */
	private void checkString(String symbol) {
		if (symbol.length() == 0 || symbol == null) {
			throw new IllegalArgumentException();
		}
	}

}
