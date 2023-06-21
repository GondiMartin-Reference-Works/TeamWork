package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * EquipmentView
 * This class responsible for drawing the equipment onto the canvas.
 * @author erdei
 */
public class EquipmentView extends View{
	

	/**
	 * Draws rectangle to given coordinate
	 * @param p -  top left corner of the rectangle
	 */
	public void Draw(Graphics g, Point p) {
		// TODO Auto-generated method stub
		
		
		g.setColor(Color.blue);
		g.fillRect(p.x, p.y, 15, 15);
	}
	


}
