import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.GeneralPath;

public class Dodge extends MiniGame {
	public static Color fgColor = new Color(0, 51, 153);
	public static Color bgColor = new Color(179, 194, 225);

	private GeneralPath barFrame;
	private GeneralPath barCross;

	private Rectangle2D bar;
	private int barPosition = 0; // -2 (top) to 2 (bottom)
	private static final int barHeight = 30;
	private static final int barWidth = 8;

	private long spikeOneTime, spikeTwoTime; // Spike creation timers
	private Spike[] spikes; // Contains active spikes

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

		reset();

		setFocusable(false);
		setBackground(bgColor);
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

	public Spike createSpike() {
		double direction = 180 * (int) (Math.random() * 2);
		double x = getWidth() * 0.5 - 30;
		if(direction == 0) {
			x *= -1;
		}
		int lane = (int) (Math.random() * 6 - 3.0);
		double speed = Math.random() * 0.2 + 0.8;
		return new Spike(x, lane * barHeight, direction, speed);
	}

	public void reset() {
		System.out.println("Reset - Dodge");
		setBackground(bgColor);
		barPosition = 0;

		spikes = new Spike[2];
		spikeOneTime = (long) (15.5 * 1000.0);
		spikeTwoTime = (long) (18 * 1000.0);
	}

	public void update(long elapsedms) {
		if(elapsedms >= spikeOneTime) {
			spikes[0] = createSpike();
			spikeOneTime += 16 * getWidth();
		}
		if(elapsedms >= spikeTwoTime) {
			spikes[1] = createSpike();
			spikeTwoTime += 16 * getWidth();
		}
		for(Spike spike : spikes) {
			if(spike != null) {
				spike.update();
			}
		}
	}

	public void pause(boolean paused) {
		if(paused) {
			System.out.println("Pause - Dodge");
		}
		else {
			System.out.println("Resume - Dodge");
		}
	}

	public boolean gameOver() {
		for(Spike spike : spikes) {
			if(spike != null && spike.intersects(bar)) {
				System.out.println("Game Over - Dodge");
				return true;
			}
		}
		return false;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int centerX = getWidth() / 2;
		int centerY = getHeight() / 2;

		Graphics2D g2d = (Graphics2D) g.create();

		g2d.translate(centerX, centerY); // Set Graphics2D transform origin to center of panel

		g2d.setColor(fgColor);
		bar.setFrame(-barWidth / 2, -barHeight / 2 + barHeight * barPosition, barWidth, barHeight);
		g2d.fill(bar);
		g2d.setColor(Color.BLACK);
		for(Spike spike : spikes) {
			if(spike != null) {
				g2d.fill(spike);
			}
		}
		g2d.draw(barCross);
		g2d.setStroke(new BasicStroke(2));
		g2d.draw(barFrame);
		g2d.dispose();
	}
}