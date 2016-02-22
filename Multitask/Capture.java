import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;

public class Capture extends MiniGame {
	private FontMetrics fontMetrics;
	private AffineTransform at;
	private final Color bgColor = new Color(192, 230, 192);

	private Rectangle2D box;
	private Point2D boxPosition;
	private final int boxSide = 32;

	private Square[] squares; // Contains active squares

	public Capture(FontMetrics fm) {
		fontMetrics = fm;

		// Store base graphics
		box = new Rectangle2D.Double(-boxSide / 2, -boxSide / 2, boxSide, boxSide);

		squares = new Square[4];
		squares[0] = new Square(110, 70, boxSide);

		reset();

		setFocusable(false);
		setBackground(bgColor);
	}

	public Square createSquare() {
		return null;
	}

	public void reset() {
		setBackground(bgColor);
		boxPosition = new Point2D.Double(0, 0);
	}

	public void moveBox(double x, double y) {
		boxPosition.setLocation(boxPosition.getX() + x, boxPosition.getY() + y);
	}

	public void update(long elapsedms) {

	}

	public boolean gameOver() {
		return false;
	}

	public void pause(boolean paused) {
		if(paused) {
			for(Square square : squares) {
				if(square != null) {
					square.stopCountdown();
				}
			}
		}
		else {
			for(Square square : squares) {
				if(square != null) {
					square.startCountdown();
				}
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int centerX = getWidth() / 2;
		int centerY = getHeight() / 2;

		Graphics2D g2d = (Graphics2D) g.create();

		g2d.translate(centerX, centerY); // Set Graphics2D transform origin to center of panel

		g2d.setColor(new Color(0, 153, 0));
		box.setFrame(-boxSide / 2 + boxPosition.getX(), -boxSide / 2 + boxPosition.getY(), boxSide, boxSide);
		g2d.fill(box);
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(3));
		for(Square square : squares) {
			if(square != null) {
				g2d.draw(square.getBox());
				String num = Integer.toString(square.getCountdown());
				Rectangle2D bounds = fontMetrics.getStringBounds(num, g2d);
				g2d.draw(bounds);
				double w = square.getBox().getWidth() / 2;
				int x = (int) (square.getBox().getX() + w + bounds.getX());
				int y = (int) (square.getBox().getY() + w - bounds.getY());
				g2d.drawString(num, x, y);
			}
		}
		g2d.dispose();
	}
}