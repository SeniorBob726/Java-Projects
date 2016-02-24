import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;

public class Helicopter extends MiniGame {
	private AffineTransform at;
	private static final Color bgColor = new Color(225, 194, 240);

	private static final double gravity = 0.05;

	private Path2D helicopter;
	private double helicopterPosition;
	private double helicopterVelocity;

	private Bar[] bars; // Contains active bars
	private long nextBar;

	public Helicopter() {
		at = new AffineTransform();

		// Store base graphics
		helicopter = new Path2D.Double();
		helicopter.moveTo(10, 0);
		helicopter.lineTo(-10, 10);
		helicopter.lineTo(-10, -10);
		helicopter.closePath();

		bars = new Bar[5];
		nextBar = (long) (80.5 * 1000.0);

		reset();

		setFocusable(false);
		setBackground(bgColor);
	}

	public Bar createBar() {
		double x = getWidth() / 2;
		double y = (Math.random() - 0.5) * getHeight();
		return new Bar(x, y);
	}

	public void reset() {
		System.out.println("Reset - Helicopter");
		setBackground(bgColor);

		helicopterPosition = 0;
		helicopterVelocity = 0;
	}

	public void increaseLift() {
		helicopterVelocity -= 0.12;
	}

	public void update(long elapsedms) {
		helicopterPosition += helicopterVelocity;
		helicopterVelocity += gravity;
		helicopterVelocity = clamp(helicopterVelocity, -2, 2);

		double topHeight = -getHeight() / 2 + 10;
		double bottomHeight = getHeight() / 2 - 10;
		if(helicopterPosition < topHeight || helicopterPosition > bottomHeight) {
			helicopterVelocity = 0;
		}
		helicopterPosition = clamp(helicopterPosition, topHeight, bottomHeight);

		for(int i = 0; i < bars.length; i++) {
			if(bars[i] == null && elapsedms >= nextBar) {
				bars[i] = createBar();
				nextBar += 5000;
			}
			else if(bars[i] != null) {
				if(bars[i].getY() < -getWidth() / 2 - 5) {
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
		System.out.println("Game Over - Helicopter");
		Rectangle2D hB = helicopter.getBounds();
		for(int i = 0; i < bars.length; i++) {
			if(bars[i].intersects(hB.getX(), hB.getY(), hB.getWidth(), hB.getHeight())) {
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

		g2d.setColor(new Color(153, 51, 204));
		if(helicopterPosition > -getHeight() / 2 + 10 && helicopterPosition < getHeight() / 2 - 10) {
			at.setToTranslation(0, helicopterVelocity);
			helicopter.transform(at);
		}
		g2d.fill(helicopter);
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(3));
		for(Bar bar : bars) {
			if(bar != null) {
				g2d.fill(bar);
			}
		}
		g2d.dispose();
	}
}