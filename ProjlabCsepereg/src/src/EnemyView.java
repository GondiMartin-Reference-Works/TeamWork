package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * EnemyView
 * This class responsible for drawing the enemy virologist onto the canvas.
 * @author erdei
 */
public class EnemyView extends View{
	
	/**
	 * Draws circle to given coordinate
	 * @param p - top left corner of the rectangle
	 */
	public void Draw(Graphics g, Point p) {
		// TODO Auto-generated method stub
		g.setColor(Color.yellow);
		
		g.fillOval(p.x, p.y, 15, 15);
	}

}
