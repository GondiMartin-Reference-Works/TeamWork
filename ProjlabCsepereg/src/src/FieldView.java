package src;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;


/**
 * FieldView
 * This class responsible for drawing the Field onto the canvas.
 * @author csizm
 */
public class FieldView extends View{
	
	private int vertices;
	
	private Image img;
	
	public FieldView(int vert) {
		vertices = vert;
		switch(vertices) {
		
		case 3:
			img = new ImageIcon(this.getClass().getResource("Field_3.png")).getImage();
			break;
			
		case 4:
			img = new ImageIcon(this.getClass().getResource("Field_4.png")).getImage();
			break;
			
		case 5:
			img = new ImageIcon(this.getClass().getResource("Field_5.png")).getImage();
			break;
			
		case 6:
			img = new ImageIcon(this.getClass().getResource("Field_6.png")).getImage();
			break;
			
		case 7:
			img = new ImageIcon(this.getClass().getResource("Field_7.png")).getImage();
			break;
			
		case 8:
			img = new ImageIcon(this.getClass().getResource("Field_8.png")).getImage();
			break;
			
		default:
			break;
		}
	}

	public void Draw(Graphics g, Point p) {
		g.drawImage(img, p.x, p.y, null);
		
	}

}
