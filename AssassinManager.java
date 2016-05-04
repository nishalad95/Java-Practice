package assassinHomework;
/**Nisha Lad, CSE 143, Spring 2016, Section AA - TA: Garrett Deardorff
 * Programming Homework Assignment #3, 14/04/16
 * This class is used within the game Assassin, contained within AssassinMain, 
 * to keep a track of who each person is stalking and the history of who killed them. */

import java.util.*;

public class AssassinManager {
	private AssassinNode ringFront;
	private AssassinNode graveyardFront;
	
	/**Initialises a new AssassinManager over the given ArrayList<String> @param names
	 * Pre: names is non-empty, non-null Strings, no duplicates
	 * throws IllegalArgumentException if names is null or is an empty list
	 * Post: Original ArrayList is unchanged */
	public AssassinManager(ArrayList<String> names) {
		if (names == null || names.isEmpty()) {
			throw new IllegalArgumentException();
		}
		for (int i = names.size() - 1; i >=  0; i--) {
			ringFront = new AssassinNode(names.get(i), ringFront);
		}
	}
	
	/** Method prints the names of the people still 'alive' in the kill ring
	 *  at a given instance within the game, indicating who is stalking who.
	 *  Pre: assumes there is more than 1 name remaining within the kill ring
	 *  Post: current state of kill ring & graveyard unchanged */
	public void printKillRing() {
		AssassinNode current = ringFront;
		while (current.next != null) {
			System.out.println("  " + current.name + " is stalking " + current.next.name);
			current = current.next;
		} if (!isGameOver()) {
			System.out.println("  " + current.name + " is stalking " + ringFront.name);
		}
	}
	
	/** Method prints the names of the people who have been killed (most recent first),
	 *  at a given instance within the game, indicating who was killed by who
	 *  Post: current state of kill ring & graveyard unchanged */
	public void printGraveyard() {
		AssassinNode current = graveyardFront;
		while (current != null) {
			System.out.println("  " + current.name + " was killed by " + current.killer);
			current = current.next;
		}
	}
	
	/**Takes in a String @param name
	 * Returns true if the given name is in the current kill ring (alive)
	 * Returns false otherwise */
	public boolean killRingContains(String name) {
		return containsName(name, ringFront);
	}
	
	/**Takes in a String @param name
	 * Returns true if the given name is in the current graveyard (alive)
	 * Returns false otherwise */
	public boolean graveyardContains(String name) {
		return containsName(name, graveyardFront);
	}
	
	/**Internal Method: Takes in a String @param name, AssassinNode @param nodeList
	 * Returns true if the given name is in the given nodeList
	 * Returns false otherwise */
	private boolean containsName(String name, AssassinNode nodeList) {
		AssassinNode current = nodeList;
		while (current != null) {
			if (name.equalsIgnoreCase(current.name)) {
				return true;
			} else {
				current = current.next;
			}
		}
		return false;	
	}
	
	/**Method returns true if the kill ring has only 1 person
	 * Returns false otherwise */
	public boolean isGameOver() {
		return ringFront.next == null;
	}
	
	/**Method returns the name of the winner if the game is over
	 * Returns null otherwise */
	public String winner() {
		if (isGameOver()) {
			return ringFront.name;
		} else {
			return null;
		}
	}
	
	/**Method takes in a String @param name, records who killed them,
	 * removes the name from the kill ring & sends it to the graveyard
	 * if game is over & name is not part of kill ring, or
	 * if the game is over and name is valid throw new IllegalStateException 
	 * if the name is not part of kill ring throw new IllegalArgumentException */
	public void kill(String name) {
		if (!isGameOver() && !killRingContains(name)) {
			throw new IllegalArgumentException();
		} else if (isGameOver()) {
			throw new IllegalStateException();
		}
		AssassinNode current = ringFront;
		AssassinNode previousName = null;
		while (!current.name.equalsIgnoreCase(name)) {
			previousName = current;
			current = current.next;
		}
		if (ringFront.name.equals(current.name)) {
			//Person killed at the front
			AssassinNode lastName = ringFront;
			while (lastName.next != null) {
				lastName = lastName.next;
			}
			current.killer = lastName.name;
			ringFront = ringFront.next;
		} else if (current.next == null) {
			//Person killed at the back
			current.killer = previousName.name;
			previousName.next = null;
		} else {
			//Person killed in the middle
			current.killer = previousName.name;
			previousName.next = current.next;
		}
		current.next = graveyardFront;
		graveyardFront = current;
	}
	
}
