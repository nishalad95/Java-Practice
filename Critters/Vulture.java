//CSE 142, Section BE -Michelle Cho
//Author: Nisha Lad
//Programming Homework Assignment #8, 06/03/16
//Vulture objects are displayed as black V and moves in a clockwise pattern

import java.awt.Color;

public class Vulture extends Bird {
	private boolean hunger;
	private int numOfFights;
	
	//constructor for Vulture
	public Vulture() {
		hunger = true;
		numOfFights = 0;
	}
	
	//Color of vulture is black
	public Color getColor() {
		return Color.BLACK;
	}
	
	//Either roars or pounces in a fight
	public Attack fight(String opponent) {
		numOfFights++;
		return super.fight(opponent);
	}
	
	//Remains hungry until Vulture eats once
	public boolean eat() {
		if (hunger || numOfFights > 0) {
			hunger = false;
			numOfFights = 0;
			return true;
		} else {
			return false;
		}
	}
}
