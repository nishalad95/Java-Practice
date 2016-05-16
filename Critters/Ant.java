//CSE 142, Section BE -Michelle Cho
//Author: Nisha Lad
//Programming Homework Assignment #8, 06/03/16
//Ant objects are displayed as % and moves in a zigzag pattern
//They always pick SCRATCH in a fight

import java.awt.Color;

public class Ant extends Critter{
	private int steps;
	private boolean walkSouth;
	
	//constructor for Ant
	public Ant(boolean walkSouth) {
		this.walkSouth = walkSouth;
		steps = 0;
	}
	
	//Color of Ant is red
	public Color getColor() {
		return Color.RED;
	}
	
	//Ant always eats
	public boolean eat() {
		return true;
	}
	
	//Ant will move S, E, S, E... if walkSouth = true, otherwise will walk N, E, N, E...
	public Direction getMove() {
		steps++;
		if (steps % 2 == 1) {
			if (walkSouth) {
				return Direction.SOUTH;
			}else {
				return Direction.NORTH;
			}
		} else {
			return Direction.EAST;
		}
	}

	//Always returns scratch in a fight
	public Attack fight(String opponent) {
		return Attack.SCRATCH;
	}

	//Ant represented by a % symbol
	public String toString() {
		return "%";
	}
}
