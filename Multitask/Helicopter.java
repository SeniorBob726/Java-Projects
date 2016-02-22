import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;

public class Helicopter extends MiniGame {
	private AffineTransform at;
	private static final Color bgColor = new Color(192, 230, 192);

	private long nextBar;

	private Path2D helicopter;
	private Rectangle2D hB;
	private double helicopterPosition;

	private Bar[] bars; // Contains active bars

	public Helicopter() {
		at = new AffineTransform();

		// Store base graphics
		helicopter = new Path2D.Double();
		helicopter.moveTo(8, 0);
		helicopter.lineTo(-8, 8);
		helicopter.lineTo(-8, -8);
		helicopter.closePath();
		hB = helicopter.getBounds();

		nextBar = (long) (1.5 * 1000.0);

		bars = new Bar[5];

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
		setBackground(bgColor);
		helicopterPosition = 0;
	}

	public void moveUp() {

	}

	public void moveDown() {

	}

	public void update(long elapsedms) {
		for(int i = 0; i < bars.length; i++) {
			if(bars[i] == null && elapsedms >= nextBar) {
				bars[i] = createBar();
				nextBar += Math.random() * 4000 + 1000;
			}
			else if(bars[i] != null && bars[i].intersects(hB.getX(), hB.getY(), hB.getWidth(), hB.getHeight())) {
				bars[i] = null;
			}
		}
	}

	public boolean gameOver() {
		return false;
	}

	public void pause(boolean paused) {

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int centerX = getWidth() / 2;
		int centerY = getHeight() / 2;

		Graphics2D g2d = (Graphics2D) g.create();

		g2d.translate(centerX, centerY); // Set Graphics2D transform origin to center of panel

		g2d.setColor(new Color(0, 153, 0));
		System.out.println(-getWidth() / 2 + 10);
		at.setToTranslation(-getWidth() / 2 + 10, helicopterPosition);
		helicopter.transform(at);
		g2d.fill(helicopter);
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(3));
		for(Bar bar : bars) {
			if(bar != null) {
				g2d.draw(bar);
			}
		}
		g2d.dispose();
	}
}