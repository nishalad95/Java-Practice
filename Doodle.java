package HomeworkAssignments;

/* Nisha Lad, CSE 142, Winter 2016, Section BE -Michelle Cho
* Programming Homework Assignment #3, 01/20/16
* This program produces a graphic image of a simple house at sunset 
*/
import java.awt.*; 

public class Doodle {
	public static void main(String[] args) {
		DrawingPanel panel = new DrawingPanel(500, 500);
		panel.setBackground(Color.ORANGE);
	    Graphics g = panel.getGraphics();
	    
	    g.setColor(Color.RED);
	    g.fillOval(125, 150, 250, 170);
	    g.setColor(Color.LIGHT_GRAY);
	    g.fillRect(125, 250, 250, 250);
	    g.setColor(Color.RED);
	    g.fillRect(225, 425, 50, 75);
	    g.setColor(Color.BLACK);
	    g.drawLine(225, 425, 275, 425);
	    g.drawLine(225, 425, 225, 500);
	    g.drawLine(275, 425, 275, 500);
	    g.drawOval(230, 470, 15, 15);
	    g.setColor(Color.CYAN);
	    g.fillRect(150, 275, 50, 50);
	    g.fillRect(300, 275, 50, 50);
	    g.fillRect(150, 400, 50, 50);
	    g.fillRect(300, 400, 50, 50);
	    g.setColor(Color.WHITE);
	    g.fillOval(70, 70, 65, 40);
	    g.fillOval(130, 90, 85, 50);
	    g.fillOval(110, 55, 60, 70);
	    g.fillOval(300, 55, 80, 60);
	    g.fillOval(350, 50, 80, 60);
	}
}
