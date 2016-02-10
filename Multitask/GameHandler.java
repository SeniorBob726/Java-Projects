import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GameHandler extends JPanel implements KeyListener {
	private javax.swing.Timer timer; // Controls how often stats are checked
	private boolean start = false;
	private ArrayList<MiniGame> games;
	private int points;

	public GameHandler() {
		points = 0;

		games = new ArrayList<MiniGame>(4);
		games.add(new Balance());
		// games.add(new Dodge());
		// games.add(new Squares());
		// games.add(new Helicopter());

		int width = 600, height = 450;
		//timer = new javax.swing.Timer(5, this);

		addKeyListener(this); // Key controls

		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(width, height));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int fontSize = 14;
		g.setColor(Color.WHITE);
		g.setFont(new Font("Courier", Font.PLAIN, fontSize));
		g.drawString("Points: " + points, (getWidth() / 2) - 30, getHeight() / 2 + 20);
	}

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
}