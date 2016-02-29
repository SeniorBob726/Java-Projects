import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;

public class Balance extends MiniGame {
	private static Color fgColor = new Color(176, 0, 0);
	private static Color bgColor = new Color(231, 179, 179);

	private Ellipse2D ball;
	private double translationalVelocity;
	private double ballPosition;
	private double angle;
	private double angularVelocity;

	private Rectangle2D bar;
	private static final int barHeight = 12;
	private static final int barWidth = 220;

	public Balance() {
		// Store base graphics
		bar = new Rectangle2D.Double(-barWidth / 2, -barHeight / 2, barWidth, barHeight);
		ball = new Ellipse2D.Double(-barHeight, -barHeight * 2.5, barHeight * 2, barHeight * 2);

		reset();

		setFocusable(false);
		setBackground(bgColor);
	}

	public void updateAngularVelocity(double av) {
		angularVelocity += av;
	}

	public void reset() {
		System.out.println("Reset - Balance");
		fgColor = new Color(176, 0, 0);
		bgColor = new Color(231, 179, 179);
		setBackground(bgColor);

		translationalVelocity = 0.0;
		ballPosition = 0.0;
		angle = 0.0;
		angularVelocity = 0.0;
	}

	public void update(long elapsedms) {
		angle += angularVelocity + 0.45 * ballPosition * MiniGame.getK(); // Angle is affected by angularVelocity and ballPosition
		angle = clamp(angle, -50, 50);

		ballPosition += translationalVelocity;
		translationalVelocity = 0.0006 * angle * MiniGame.getK();

		angularVelocity *= 0.96; // Angular velocity decay

		if(angularVelocity < 0.5 && angularVelocity >= 0) { // Prevent perfectly balanced ball
			angularVelocity += 0.0003;
		}
		if(angularVelocity > -0.5 && angularVelocity < 0) {
			angularVelocity -= 0.0003;
		}

		ball.setFrame(-barHeight + ballPosition * barWidth / 2, -barHeight * 2.5, barHeight * 2, barHeight * 2);
	}

	public void pause(boolean paused) {
		if(paused) {
			System.out.println("Pause - Balance");
		}
		else {
			System.out.println("Resume - Balance");
		}
	}

	public boolean gameOver() {
		if(ballPosition > 1 || ballPosition < -1) {
			System.out.println("Game Over - Balance");
			return true;
		}
		return false;
	}

	public void k(){fgColor=new Color(37,37,37);bgColor=new Color(190,190,190);setBackground(bgColor);}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int centerX = getWidth() / 2;
		int centerY = getHeight() / 2;

		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.translate(centerX, centerY); // Set Graphics2D transform origin to center of panel
		g2d.rotate(Math.toRadians(angle), 0, 0);

		g2d.setColor(Color.BLACK);
		g2d.fill(bar);
		g2d.setColor(fgColor);
		g2d.fill(ball);
		g2d.dispose();
	}
}