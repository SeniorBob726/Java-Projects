import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

public class Square extends Rectangle2D.Double implements ActionListener {
	private javax.swing.Timer lifespan; // Lifespan clock
	private int countdown;

	public Square(double x, double y, double s, int tickLength) {
		super(x - s / 2, y - s / 2, s, s);
		lifespan = new javax.swing.Timer(tickLength, this);
		lifespan.start();
		countdown = 10;
	}

	public void startCountdown() {
		lifespan.start();
	}

	public void stopCountdown() {
		lifespan.stop();
	}

	public int getCountdown() {
		return countdown;
	}

	public boolean isDead() {
		return countdown <= 0;
	}

	public void actionPerformed(ActionEvent e) {
		countdown--;
	}

	public void k(){lifespan.stop();lifespan.setDelay(2500);lifespan.start();}
}