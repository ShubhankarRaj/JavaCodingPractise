package myPackage;

import javax.swing.JApplet;
import java.awt.Graphics;

public class Olympicsapplet extends JApplet {

	/**
	 * @param args
	 */
	public void paint(Graphics rings) {
		// TODO Auto-generated method stub
		//Drawing the upper circles
		rings.drawOval(100, 50, 25, 25);
		rings.drawOval(130, 50, 25, 25);
		rings.drawOval(160, 50, 25, 25);
				
		//Drawing the lower circles
		rings.drawOval(115, 65, 25, 25);
		rings.drawOval(145, 65, 25, 25);
		
		
	}

}
