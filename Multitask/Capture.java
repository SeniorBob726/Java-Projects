import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;

public class Capture extends MiniGame {
	private AffineTransform at;
	private final Color bgColor = new Color(192, 230, 192);

	private Rectangle2D box;
	private Point2D boxPosition;
	private final int boxSide = 32;

	private Square[] squares; // Contains active squares

	public Capture() {
		// Store base graphics
		box = new Rectangle2D.Double(-boxSide / 2, -boxSide / 2, boxSide / 2, boxSide / 2);

		reset();

		setFocusable(false);
		setBackground(bgColor);
	}

	public void reset() {
		setBackground(bgColor);
		boxPosition = new Point2D.Double(0, 0);
	}

	public Square createSquare() {
		return null;
	}

	public void update(long elapsedms) {
		for(Square square : squares) {
			if(square != null) {
				square.update();
			}
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

		g2d.setColor(new Color(0, 153, 0));
		box.setFrame(-boxSide / 2 + boxPosition.getX(), -boxSide / 2 + boxPosition.getY(), boxSide / 2 + boxPosition.getX(), boxSide / 2 + boxPosition.getY());
		g2d.fill(box);
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(3));
		for(Square square : squares) {
			if(square != null) {
				g2d.draw(square);
			}
		}
		g2d.dispose();
	}
}