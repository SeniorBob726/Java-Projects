import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

public class Dodge extends MiniGame {
	private GeneralPath barFrame;
	private GeneralPath barCross;

	private Rectangle2D bar;
	private int barPosition = 0; // -2 (top) to 2 (bottom)
	private int barHeight = 32;
	private int barWidth = 8;

	private ArrayList<Spike> spikes; // Contains active spikes

	public Dodge() {
		// Store base graphics
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

		spikes = new ArrayList<Spike>(2);

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
		if(spikes.size() < 2) {
			double direction = 180 * (int) (Math.random() * 2);
			double x = direction == 0 ? -getWidth() / 2 : getWidth() / 2;
			spikes.add(new Spike(x, 0, direction, Math.random()));
		}
		for(Spike spike : spikes) {
			spike.update();
		}
	}

	public boolean gameOver() {
		return false;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int centerX = getWidth() / 2;
		int centerY = getHeight() / 2;

		Graphics2D g2d = (Graphics2D) g.create();

		g2d.translate(centerX, centerY); // Set Graphics2D transform origin to center of panel

		g2d.setColor(new Color(0, 51, 153));
		bar.setFrame(-barWidth / 2, -barHeight / 2 + barHeight * barPosition, barWidth, barHeight);
		g2d.fill(bar);
		g2d.setColor(Color.BLACK);
		for(Spike spike : spikes) {
			g2d.fill(spike);
		}
		g2d.draw(barCross);
		g2d.setStroke(new BasicStroke(2));
		g2d.draw(barFrame);
		g2d.dispose();
	}
}