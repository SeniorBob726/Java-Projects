import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;

public class Balance extends MiniGame {
	private Ellipse2D ball;
	private double translationalVelocity = 0.0;
	private double ballPosition = 0.0;
	private double angle = 0.0;
	private double angularVelocity = 0.0;

	private Rectangle2D bar;
	private int barHeight = 12;
	private int barWidth = 200;

	public Balance() {
		bar = new Rectangle2D.Double(-barWidth / 2, -barHeight / 2, barWidth, barHeight);
		ball = new Ellipse2D.Double(-barHeight, -barHeight * 2.5, barHeight * 2, barHeight * 2);
		setFocusable(false);
		setBackground(new Color(231, 179, 179));
	}

	public void updateAngularVelocity(double av) {
		angularVelocity += av;
	}

	public void update() {
		angle += angularVelocity;
		ballPosition += translationalVelocity;

		angularVelocity += Math.pow(ballPosition, 3);
		angularVelocity *= 0.98; // Angular velocity decay
		angularVelocity = Math.max(-2, Math.min(2, angularVelocity)); // Clamp velocity between -2 and 2

		translationalVelocity += 0.0001 * angle;
		translationalVelocity *= 0.98; // Translational velocity decay
		translationalVelocity = Math.max(-0.015, Math.min(0.015, translationalVelocity)); // Clamp velocity between -0.015 and 0.015

		ball.setFrame(-barHeight + ballPosition * barWidth / 2, -barHeight * 2.5, barHeight * 2, barHeight * 2);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int centerX = getWidth() / 2;
		int centerY = getHeight() / 2;

		Graphics2D g2d = (Graphics2D) g.create();

		g2d.translate(centerX, centerY);
		g2d.rotate(Math.toRadians(angle), 0, 0);

		g2d.setColor(Color.BLACK);
		g2d.fill(bar);
		g2d.setColor(new Color(176, 0, 0));
		g2d.fill(ball);
		g2d.dispose();
		g.drawString("angularVelocity: " + angularVelocity, 0, 20);
		g.drawString("translationalVelocity: " + translationalVelocity, 0, 40);
	}
}