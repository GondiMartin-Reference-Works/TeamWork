package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class GenCodeView extends View{

	/**
	 * Draws a triangle
	 * @param p - the left corner of the triangle
	 */
	public void Draw( Graphics g, Point p) {
		// TODO Auto-generated method stub
		g.setColor(Color.green);
		int[] xs=new int[3];
		int[] ys=new int[3];
		
		xs[0]=p.x; 		ys[0]=p.y;
		xs[1]=p.x+20; 	ys[1]=p.y;
		xs[2]=p.x+10; 	ys[2]=p.y+17;
		
		g.fillPolygon(xs, ys, 3);
	}

}
