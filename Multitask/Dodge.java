import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.GeneralPath;

public class Dodge extends MiniGame {
	private Rectangle2D bar;
	private int barPosition = 0; // -2 (top) to 2 (bottom)
	private int barHeight = 32;
	private int barWidth = 8;
	private GeneralPath barFrame;
	private GeneralPath barCross;

	public Dodge() {
		bar = new Rectangle2D.Double(-barWidth / 2, -barHeight / 2, barWidth, barHeight);

		barFrame = new GeneralPath();
		barFrame.moveTo(-barWidth / 2, -barHeight * 2.5);
		barFrame.lineTo(barWidth / 2, -barHeight * 2.5);
		barFrame.lineTo(barWidth / 2, barHeight * 2.5);
		barFrame.lineTo(-barWidth / 2, barHeight * 2.5);
		barFrame.lineTo(-barWidth / 2, -barHeight * 2.5);

		barCross = new GeneralPath();
		barCross.moveTo(-barWidth / 2, -barHeight * 1.5);
		barCross.lineTo(barWidth / 2, -barHeight * 1.5);
		barCross.moveTo(-barWidth / 2, -barHeight * 0.5);
		barCross.lineTo(barWidth / 2, -barHeight * 0.5);
		barCross.moveTo(-barWidth / 2, barHeight * 0.5);
		barCross.lineTo(barWidth / 2, barHeight * 0.5);
		barCross.moveTo(-barWidth / 2, barHeight * 1.5);
		barCross.lineTo(barWidth / 2, barHeight * 1.5);

		reset();

		setFocusable(false);
		setBackground(new Color(179, 194, 225));
	}

	public void moveUp() {
		if(barPosition > -2) {
			barPosition--;
		}
	}

	public void moveDown() {
		if(barPosition < 2) {
			barPosition++;
		}
	}

	public void reset() {
		setBackground(new Color(179, 194, 225));
		barPosition = 0;
	}

	public void update() {

	}

	public boolean gameOver() {
		return false;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int centerX = getWidth() / 2;
		int centerY = getHeight() / 2;

		Graphics2D g2d = (Graphics2D) g.create();

		g2d.translate(centerX, centerY);

		// g2d.setColor(new Color(0, 51, 153));
		// bar.setFrame(-barWidth / 2, -barHeight / 2 + barHeight * barPosition, barWidth, barHeight);
		// g2d.fill(bar);
		// g2d.setColor(Color.BLACK);
		// g2d.draw(barCross);
		// g2d.setStroke(new BasicStroke(2));
		// g2d.draw(barFrame);
		g2d.fill(new Spike(2 * Math.PI));
		g2d.dispose();
	}
}