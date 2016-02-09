import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Balance extends MiniGame {
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_ENTER:
				System.out.println("Enter");
				break;
			case KeyEvent.VK_SPACE:
				System.out.println("Space");
				break;
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
				break;
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if(getFirstGame()) {
			int fontSize = 14;
			g.setColor(Color.WHITE);
			g.setFont(new Font("Courier", Font.PLAIN, fontSize));
			g.drawString("Points: " + points, (getWidth() / 2) - 30, getHeight() / 2 + 20);
		}
	}
}