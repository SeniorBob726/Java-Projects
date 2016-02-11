import javax.swing.*;
import java.awt.*;

public class Balance extends MiniGame {
	public Balance() {
		int width = 600;
		int height = 450;

		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(width, height));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(new Color(50, 50, 50));
		g.fillRect(0, 0, 50, 70);
	}
}