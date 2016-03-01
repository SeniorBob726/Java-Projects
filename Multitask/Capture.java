import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;

public class Capture extends MiniGame {
	private Font font;
	private FontMetrics fontMetrics;
	private AffineTransform at;
	private static Color fgColor = new Color(0, 153, 0);
	private static Color bgColor = new Color(192, 230, 192);
	private static Color textColor = new Color(148, 191, 164);

	private Rectangle2D box;
	private Point2D boxPosition;
	private static final int boxSide = 20;

	private Square[] squares; // Contains active squares
	private long nextSquare;

	public Capture() {
		font = new Font("Verdana", Font.BOLD, 15);
		fontMetrics = this.getFontMetrics(font);

		// Store base graphics
		box = new Rectangle2D.Double(-boxSide / 2, -boxSide / 2, boxSide, boxSide);

		squares = new Square[5];
		nextSquare = (long) (40.5 * 1000.0);

		reset();

		setFocusable(false);
		setBackground(bgColor);
	}

	public Square createSquare() {
		double x = (Math.random() - 0.5) * (getWidth() - boxSide * 2);
		double y = (Math.random() - 0.5) * (getHeight() - boxSide * 2);
		return new Square(x, y, boxSide * 2, (int) (1000 / MiniGame.getK()));
	}

	public void reset() {
		System.out.println("Reset - Capture");
		fgColor = new Color(0, 153, 0);
		bgColor = new Color(192, 230, 192);
		setBackground(bgColor);
		boxPosition = new Point2D.Double(0, 0);

		squares = new Square[5];
		nextSquare = (long) (40.5 * 1000.0);
	}

	public void moveBox(double x, double y) {
		boxPosition.setLocation(clamp(boxPosition.getX() + x, -getWidth() / 2 + boxSide / 2, getWidth() / 2 - boxSide / 2),
								clamp(boxPosition.getY() + y, -getHeight() / 2 + boxSide / 2, getHeight() / 2 - boxSide / 2));
	}

	public void update(long elapsedms) {
		for(int i = 0; i < squares.length; i++) {
			if(squares[i] == null && elapsedms >= nextSquare) {
				squares[i] = createSquare();
				nextSquare = elapsedms + (long) ((Math.random() * 1000 + 2000) / MiniGame.getK() / MiniGame.getGameSpeed());
			}
			else if(squares[i] != null && squares[i].intersects(box)) {
				squares[i].stopCountdown();
				squares[i] = null;
			}
		}
	}

	public void pause(boolean paused) {
		if(paused) {
			System.out.println("Pause - Capture");
			for(Square square : squares) {
				if(square != null) {
					square.stopCountdown();
				}
			}
		}
		else {
			System.out.println("Resume - Capture");
			for(Square square : squares) {
				if(square != null) {
					square.startCountdown();
				}
			}
		}
	}

	public boolean gameOver() {
		for(Square square : squares) {
			if(square != null && square.isDead()) {
				System.out.println("Game Over - Capture");
				return true;
			}
		}
		return false;
	}

	public void k(){fgColor=new Color(109,109,109);bgColor=new Color(219,219,219);textColor=new Color(180,180,180);setBackground(bgColor);for(Square s:squares){if(s!=null){s.k();}}}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int centerX = getWidth() / 2;
		int centerY = getHeight() / 2;

		Graphics2D g2d = (Graphics2D) g.create();

		g2d.translate(centerX, centerY); // Set Graphics2D transform origin to center of panel

		g2d.setColor(fgColor);
		box.setFrame(-boxSide / 2 + boxPosition.getX(), -boxSide / 2 + boxPosition.getY(), boxSide, boxSide);
		g2d.fill(box);
		g2d.setFont(font);
		g2d.setStroke(new BasicStroke(2));
		for(Square square : squares) {
			if(square != null) {
				g2d.setColor(Color.BLACK);
				g2d.draw(square);
				String num = Integer.toString(square.getCountdown());
				Rectangle2D bounds = fontMetrics.getStringBounds(num, g2d);
				double w = square.getWidth() / 2;
				int x = (int) (square.getX() + w + bounds.getX() - bounds.getWidth() / 2);
				int y = (int) (square.getY() + w - bounds.getY() - bounds.getHeight() / 2);
				g2d.setColor(textColor);
				g2d.drawString(num, x, y);
			}
		}
		g2d.dispose();
	}
}