import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;

public class Helicopter extends MiniGame {
	private AffineTransform at;
	private static Color fgColor = new Color(153, 51, 204);
	private static Color bgColor = new Color(225, 194, 240);

	private static final double gravity = 0.06;

	private Path2D helicopter;
	private double helicopterPosition = 0;
	private double helicopterVelocity = 0;

	private Bar[] bars; // Contains active bars
	private long nextBar;

	public Helicopter() {
		at = new AffineTransform();

		// Store base graphics
		helicopter = new Path2D.Double();
		drawHelicopter();

		reset();

		setFocusable(false);
		setBackground(bgColor);
	}

	public Bar createBar() {
		double x = getWidth() / 2;
		double y = (Math.random() - 0.5) * (getHeight() - 40) - 20;
		return new Bar(x, y, 1.0 * MiniGame.getK() * MiniGame.getGameSpeed());
	}

	public void reset() {
		System.out.println("Reset - Helicopter");
		fgColor = new Color(153, 51, 204);
		bgColor = new Color(225, 194, 240);
		setBackground(bgColor);

		helicopterPosition = 0;
		helicopterVelocity = 0;
		nextBar = (long) (80.5 * 1000.0);

		bars = new Bar[5];
	}

	public void increaseLift() {
		helicopterVelocity -= 0.12;
	}

	public void drawHelicopter() {
		double c = Math.cos(-helicopterVelocity * 0.1);
		double s = Math.sin(-helicopterVelocity * 0.1);
		double xOffset = 30 - getWidth() / 2;
		helicopter.reset();
		helicopter.moveTo(10 * c + xOffset, -10 * s + helicopterPosition);
		helicopter.lineTo(-10 * c + 10 * s + xOffset, 10 * s + 10 * c + helicopterPosition);
		helicopter.lineTo(-10 * c - 10 * s + xOffset, 10 * s - 10 * c + helicopterPosition);
		helicopter.closePath();
	}

	public void update(long elapsedms) {
		helicopterPosition += helicopterVelocity;
		helicopterVelocity += gravity;
		helicopterVelocity = clamp(helicopterVelocity, -2, 2);

		double topHeight = -getHeight() / 2 + 10;
		double bottomHeight = getHeight() / 2 - 10;
		if(helicopterPosition < topHeight || helicopterPosition >= bottomHeight) {
			helicopterPosition = clamp(helicopterPosition, topHeight, bottomHeight);
			helicopterVelocity = 0;
		}

		for(int i = 0; i < bars.length; i++) {
			if(bars[i] == null && elapsedms >= nextBar) {
				bars[i] = createBar();
				nextBar += getWidth() * 16 / MiniGame.getK() / MiniGame.getGameSpeed();
			}
			else if(bars[i] != null) {
				if(bars[i].getX() < -getWidth() / 2) {
					bars[i] = null;
				}
				else {
					bars[i].update();
				}
			}
		}
	}

	public void pause(boolean paused) {
		if(paused) {
			System.out.println("Pause - Helicopter");
		}
		else {
			System.out.println("Resume - Helicopter");
		}
	}

	public boolean gameOver() {
		for(int i = 0; i < bars.length; i++) {
			if(bars[i] != null && helicopter.intersects(bars[i])) {
				System.out.println("Game Over - Helicopter");
				return true;
			}
		}
		return false;
	}

	public void k(){fgColor=new Color(84,84,84);bgColor=new Color(204,204,204);setBackground(bgColor);}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int centerX = getWidth() / 2;
		int centerY = getHeight() / 2;

		Graphics2D g2d = (Graphics2D) g.create();

		g2d.translate(centerX, centerY); // Set Graphics2D transform origin to center of panel

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(fgColor);
		drawHelicopter();
		g2d.fill(helicopter);

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		g2d.setColor(Color.BLACK);
		for(Bar bar : bars) {
			if(bar != null) {
				g2d.fill(bar);
			}
		}
		g2d.dispose();
	}
}