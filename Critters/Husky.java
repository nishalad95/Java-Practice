//CSE 142, Section BE -Michelle Cho
//Author: Nisha Lad
//Programming Homework Assignment #8, 06/03/16
//Husky objects are displayed as W 

import java.awt.Color;

public class Husky extends Critter {
	private int steps;
	private boolean hungry;
	
	//constructor for Husky
	public Husky() {
		steps = 0;
		hungry = true;
	}
	
	//Color of Husky alternates between yellow an magenta
	public Color getColor() {
		if (steps < 3) {
			return Color.YELLOW;
		} else {
			return Color.MAGENTA;
		}
	}
	
	//Husky either roars or scratch in a fight
	public Attack fight(String opponent) {
		if (opponent.equals("%")) {
			return Attack.SCRATCH;
		} else {
			return Attack.ROAR;
		}
	}
	
	//Husky moves in an upward ladder way
	public Direction getMove() {
		steps %= 6;
		steps++;
		if (steps <= 2) {
			return Direction.NORTH;
		} else if (steps <=4) {
			return Direction.WEST;
		} else {
			return Direction.EAST;
		}
	}
	
	//Husky alternates between eating and not eating
	public boolean eat() {
		if (hungry) {
			hungry = false;
			return true;
		} else {
			hungry = true;
			return false;
		}
	}
	
	//Husk represented by alternating U and W characters
	public String toString() {
		if (steps < 3) {
			return "U";
		} else {
			return "W";
		}	
	}
}
