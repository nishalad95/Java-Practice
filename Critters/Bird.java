//CSE 142, Section BE -Michelle Cho
//Author: Nisha Lad
//Programming Homework Assignment #8, 06/03/16
//Bird objects are displayed as blue > and moves in a clockwise pattern

import java.awt.Color;

public class Bird extends Critter{
	private int steps;
	
	//constructor for Bird
	public Bird() {
		steps = 0;
	}
	
	//Color of Bird is blue
	public Color getColor() {
		return Color.BLUE;
	}
	
	//Bird moves in a clockwise movement taking 3 steps N, E, S, W
	public Direction getMove() {
		steps %= 12;
		steps++;
		if (steps <= 3) {
			return Direction.NORTH;
		} else if (steps <= 6) {
			return Direction.EAST;
		} else if (steps <= 9) {
			return Direction.SOUTH;
		} else {
			return Direction.WEST;
		}
	}

	//Either roars or pounces in a fight
	public Attack fight(String opponent) {
		if (opponent.equals("%")) {
			return Attack.ROAR;
		} else {
			return Attack.POUNCE;
		}
	}

	//Bird represented by changing ^ symbol depending on direction of movement
	public String toString() {
		if (steps <= 3) {
			return "^";
		} else if (steps <= 6) {
			return ">";
		} else if (steps <= 9) {
			return "V";
		} else {
			return "<";
		}
	}
}
