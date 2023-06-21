package src;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

/**
 * This class inherits from view.
 * It is responsible for drawing a shelter field with a certain amount of neighbours on the canvas.
 * @author csizm
 */
public class ShelterView extends View{
	/**the number of vertices the image should have*/
	private int vertices;
	
	/**the image of the shelter*/
	private Image img;
	
	/**
	 * Puts one of the shelter imageicons on the Canvas, according to how many neighbours the field has.
	 * @param vert the number of vertices (neighboring fields) given as parameter
	 */
	public ShelterView(int vert) {
		
		vertices = vert;
		
		switch(vertices) {
		
		case 3: 
			img = new ImageIcon(this.getClass().getResource("Shelter_3.png")).getImage();
			break;
		
		case 4:
			img = new ImageIcon(this.getClass().getResource("Shelter_4.png")).getImage();
			break;
			
		case 5:
			img = new ImageIcon(this.getClass().getResource("Shelter_5.png")).getImage();
			break;
			
		case 6:
			img = new ImageIcon(this.getClass().getResource("Shelter_6.png")).getImage();
			break;
			
		case 7:
			img = new ImageIcon(this.getClass().getResource("Shelter_7.png")).getImage();
			break;
			
		case 8:
			img = new ImageIcon(this.getClass().getResource("Shelter_8.png")).getImage();
			break;
			
		default:
			break;
		}
	
	}

	/**
	 * Draws the shelter on the given Graphics object, starting from point p.
	 * @param g Graphics
	 * @param p the Point(X and Y) coordinate of the top left corner of the shelter image.
	 */
	public void Draw(Graphics g, Point p) {
		g.drawImage(img,  p.x,p.y, null);
		
	}

}
