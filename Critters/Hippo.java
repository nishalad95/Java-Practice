//CSE 142, Section BE -Michelle Cho
//Author: Nisha Lad
//Programming Homework Assignment #8, 06/03/16
//Hippo objects are displayed as 5 and moves in a random direction

import java.awt.Color;
import java.util.*;

public class Hippo extends Critter{
	private int steps;
	private int hunger;
	private int direction; 
	
	//constructor for Hippo
	public Hippo(int hunger) {
		steps = 0;
		this.hunger = hunger;
		direction = -1;	
	}
	
	//Color of Hippo is gray if still hungry, otherwise white
	public Color getColor() {
		if (hunger > 0) {
			return Color.GRAY;
		} else {
			return Color.WHITE;
		}	
	}
	
	//Hippo always eats if hungry, doesn't eat when hunger = 0
		public boolean eat() {
			if (hunger > 0) {
				hunger--;
				return true;
			} else {
				return false;
			}
		}
	
	//Hippo moves 5 steps in a random direction N, S, E or W, chooses new direction and repeats
	public Direction getMove() {
		steps %= 5;
		if (steps == 0) {
			Random rand = new Random();
			direction = rand.nextInt(4);
		}
		steps++;
		if (direction == 0) {
			return Direction.NORTH;
		} else if (direction == 1) {
			return Direction.EAST;
		} else if (direction == 2) {
			return Direction.SOUTH;
		} else {
			return Direction.WEST;
		}
	}

	//Hippo either roars or pounces in a fight
	public Attack fight(String opponent) {
		if (hunger > 0) {
			return Attack.SCRATCH;
		} else {
			return Attack.POUNCE;
		}
	}

	//Hippo represented by integer given by number of piece of food the Hippo will eat
	public String toString() {
		return "" + hunger;
	}
}
