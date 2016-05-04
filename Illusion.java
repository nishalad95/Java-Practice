/* Nisha Lad
* This program produces a graphic image of an optical illusion using Drawing Panel
* and other Java graphical features. 
*/

import java.awt.*; // imports java Graphics commands

public class Illusion {
	public static void main(String[] args) {
		   
		DrawingPanel panel = new DrawingPanel(500, 400);
		panel.setBackground(Color.GRAY);
		Graphics g = panel.getGraphics();
		subFigure(g, 0, 0, 90, 3);
		subFigure(g, 120, 10, 90, 3);
		subFigure(g, 250, 50, 80, 5);
		grid(g, 10, 120, 100, 10, 2);
		grid(g, 350, 20, 40, 5, 3);
		grid(g, 230, 160, 50, 5, 4);
	}
	
	// Method draws and fills a square grid by repeating the circular illusion pattern within it
	// Parameters: graphics object, x_position, y_position, diameter, # circles, # repetitions
	public static void grid(Graphics g, int x, int y, int diam, int num, int rep) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x, y, diam*rep, diam*rep);
		g.setColor(Color.RED);
		g.drawRect(x, y, diam*rep, diam*rep);
		for (int i = 0; i <= rep - 1; i++) {
			for (int j = 0; j <= rep - 1; j++) {
				subFigure(g, x + i*diam, y + j*diam, diam, num);
			}
		}	
	}
	
	// Method draws the red circular pattern
	// Parameters: graphics object, x_position, y_position, diameter, # circles
	public static void subFigure(Graphics g, int x, int y, int diam, int num) {
		g.setColor(Color.RED);
		g.fillOval(x, y, diam, diam);
		g.setColor(Color.BLACK);
		// Loop draws concentric circles within the circle unit
		for (int i = 0; i < num; i++) {
			g.drawOval(x + (diam*i)/(2*num), y + (diam*i)/(2*num), diam - (diam*i/num), diam - (diam*i/num));
		}
		// Loop draws a diamond in each circle unit
		for (int i = 0; i <= 1; i++) {
			for (int j = 0; j <= 1; j++) {
				g.drawLine(x + diam/2, y + i * diam, x + j * diam, y + diam/2);
			}
		}	
	}
}
