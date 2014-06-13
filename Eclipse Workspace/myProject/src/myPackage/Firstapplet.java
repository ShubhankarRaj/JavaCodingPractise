package myPackage;

import javax.swing.JApplet;
import java.awt.Graphics;

public class Firstapplet extends JApplet {

	/**
	 * @param args
	 */
	public void paint(Graphics canvas) {
		// TODO Auto-generated method stub
		canvas.drawOval(100, 50, 200, 200);
		canvas.fillOval(155, 100, 10, 20);
		canvas.fillOval(230, 100, 10, 20);
		canvas.drawArc(150, 160, 100, 80, 0, 180);
	}

}
