import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Balance extends MiniGame {
	public Balance() {
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		setBackground(Color.GRAY);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int originX = getWidth() / 2;
		int originY = getHeight() / 2;

		int xOffset = -(getWidth() / 4);
		int yOffset = -(10 / 2);

		g.setColor(Color.BLACK);
		Graphics2D g2d = (Graphics2D) g.create();

		g2d.translate(originX, originY);
		g2d.rotate(Math.toRadians(60), 0, 0);

		g2d.fill(new Rectangle2D.Double(xOffset, yOffset, getWidth() / 2, 10));
		g2d.dispose();
	}
}