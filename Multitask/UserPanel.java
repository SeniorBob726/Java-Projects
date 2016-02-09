import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// UserPanel inherits from JPanel and uses the KeyListener and ActionListener interfaces

public class UserPanel extends JPanel implements KeyListener, ActionListener {
	private javax.swing.Timer timer; // Controls how often stats are checked
	private boolean start = false;
	private int points;

	// public UserPanel(Color backColor, int width, int height){
	public UserPanel() {
		Color backColor = Color.BLACK;
		int width = 600, height = 450;

		points = 0;

		//Status check every 5 milliseconds
		timer = new javax.swing.Timer(5, this);

		addKeyListener(this); // Key controls

		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		setBackground(backColor);
		setPreferredSize(new Dimension(width, height));
	}

	public void actionPerformed(ActionEvent e) {
		checkStats();
		repaint();
	}

	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_ENTER:
				break;
			case KeyEvent.VK_SPACE:
				break;
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
				break;
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int fontSize = 14;
		g.setColor(Color.WHITE);
		g.setFont(new Font("Courier", Font.PLAIN, fontSize));
		g.drawString("Points: " + points, (getWidth() / 2) - 30, getHeight() / 2 + 20);
	}

	private void checkStats() { // Called every 5ms

	}
}