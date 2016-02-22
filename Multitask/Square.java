import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

public class Square implements ActionListener {
	private Rectangle2D box;
	private javax.swing.Timer lifespan; // Lifespan clock
	private int countdown;

	public Square(double x, double y, double s) {
		box = new Rectangle2D.Double(x - s / 2, y - s / 2, s, s);
		lifespan = new javax.swing.Timer(1000, this); // Tick once every second
		lifespan.start();
		countdown = 10;
	}

	public Rectangle2D getBox() {
		return box;
	}

	public int getCountdown() {
		return countdown;
	}

	public void actionPerformed(ActionEvent e) {
		countdown--;
	}
}