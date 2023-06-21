package src;

import java.awt.Graphics;
import java.awt.Point;

/**
 * this abstract class is the base class for all of the drawable classes.
 * @author csizm
 *
 */
public abstract class View {
	//method, that takes a java graphics issue, and a point,
	//which is going to be the top left corner of the image
	public abstract void Draw(Graphics g, Point p);

}
