import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.GeneralPath;

public class Dodge extends MiniGame {
	private static Color fgColor = new Color(0, 51, 153);
	private static Color bgColor = new Color(179, 194, 225);

	private GeneralPath barFrame;
	private GeneralPath barCross;

	private Rectangle2D bar;
	private int barPosition = 0; // -2 (top) to 2 (bottom)
	private static final int barHeight = 24;
	private static final int barWidth = 8;

	private Spike[] spikes; // Contains active spikes
	private long[] spikeTimes; // Spike creation times

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

	public Spike createSpike(double direction) {
		double x = getWidth() * 0.5 - 30;
		if(direction == 0) {
			x *= -1;
		}
		int lane = (int) (Math.random() * 6 - 3.0);
		return new Spike(x, lane * barHeight, direction, 1.0 * MiniGame.getK());
	}

	public void reset() {
		System.out.println("Reset - Dodge");
		fgColor = new Color(0, 51, 153);
		bgColor = new Color(179, 194, 225);
		setBackground(bgColor);
		barPosition = 0;

		spikes = new Spike[2];
		spikeTimes = new long[2];
		spikeTimes[0] = (long) (15.5 * 1000.0);
		spikeTimes[1] = (long) (18 * 1000.0);
	}

	public void update(long elapsedms) {
		for(int i = 0; i < spikes.length; i++) {
			if(spikes[i] != null) {
				boolean outOfBounds = false;
				if(spikes[i].getDirection() == 0) {
					outOfBounds = spikes[i].getPosition().getX() > getWidth() * 0.5 - 20;
				}
				else {
					outOfBounds = spikes[i].getPosition().getX() < -getWidth() * 0.5 + 20;
				}
				if(outOfBounds) {
					spikes[i] = null;
					spikeTimes[i] = elapsedms + (long) (Math.random() * 2000 / MiniGame.getK() / MiniGame.getGameSpeed());
				}
				else {
					spikes[i].update();
				}
			}
			else if(elapsedms >= spikeTimes[i]) {
				spikes[i] = createSpike(i * 180);
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

	public void k(){fgColor=new Color(48,48,48);bgColor=new Color(193,193,193);setBackground(bgColor);for(Spike s:spikes){if(s!=null){s.k();}}}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int centerX = getWidth() / 2;
		int centerY = getHeight() / 2;

		Graphics2D g2d = (Graphics2D) g.create();

		g2d.translate(centerX, centerY); // Set Graphics2D transform origin to center of panel

		g2d.setColor(fgColor);
		bar.setFrame(-barWidth / 2, -barHeight / 2 + barHeight * barPosition, barWidth, barHeight);
		g2d.fill(bar);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for(Spike spike : spikes) {
			if(spike != null) {
				double opacity = 255;
				if(spike.getDirection() == 0) {
					opacity = getWidth() * 0.5 - 70 - spike.getPosition().getX();
				}
				else {
					opacity = spike.getPosition().getX() + getWidth() * 0.5 - 70;
				}
				g2d.setColor(new Color(0, 0, 0, (int) clamp(opacity * 10, 0, 255)));
				g2d.fill(spike);
			}
		}
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		g2d.setColor(Color.BLACK);
		g2d.draw(barCross);
		g2d.setStroke(new BasicStroke(2));
		g2d.draw(barFrame);
		g2d.dispose();
	}
}