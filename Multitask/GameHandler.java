import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GameHandler extends JPanel implements ActionListener, KeyListener/*, JavaArcade */ {
	private int width, height;
	private JLabel pointLabel;
	private javax.swing.Timer timer; // Game clock
	private Font font;
	private FontMetrics fontMetrics;

	private boolean rightKeyDown = false, leftKeyDown = false;
	private boolean upLocked = false, downLocked = false;

	private long startTime = 0;
	private long pauseTime = 0;
	private boolean gameActive = false;
	private boolean paused = false;
	private ArrayList<MiniGame> games; // Contains active MiniGames
	private int points;
	private int highScore = 0;

	public GameHandler() {
		font = new Font("Courier", Font.PLAIN, 14);
		fontMetrics = this.getFontMetrics(font);

		points = 0;
		games = new ArrayList<MiniGame>(4);
		games.add(new Balance());
		games.add(new Dodge());
		games.add(new Capture(fontMetrics));

		width = 600;
		height = 450;
		pointLabel = new JLabel(Integer.toString(points));
		pointLabel.setForeground(Color.WHITE);
		pointLabel.setBackground(new Color(50, 50, 50));
		pointLabel.setOpaque(true);
		timer = new javax.swing.Timer(1000 / 60, this); // 60 ticks per second

		addKeyListener(this); // Key controls

		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		setPreferredSize(new Dimension(width, height));

		constructLayout();
	}

	public void constructLayout() { // Layout MiniGame panels
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		this.add(pointLabel, c); // Add pointLabel with points display
		c.gridwidth = 1;

		// Single cell
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1;

		for(int i = 0; i < games.size(); i++) {
			if(games.size() == 2) { // Two cells - side by side, equal sizing
				c.gridx = i;
				c.gridy = 1;
			}
			else if(games.size() == 3) { // Three cells - 2x2 grid, first cell takes two rows
				if(i == 0) {
					c.gridheight = 2;
					c.gridx = 0;
					c.gridy = 1;
				}
				else {
					c.gridheight = 1;
					c.gridx = 1;
					c.gridy = i;
				}
			}
			else if(games.size() == 4) { // Four cells - 2x2 grid, equal sizing
				if(i == 0 || i == 3) {
					c.gridx = 0;
				}
				else {
					c.gridx = 1;
				}
				c.gridy = (i <= 1 ? 1 : 2);
			}
			this.add(games.get(i), c);
		}
		this.validate();
		this.repaint();
	}

	public void startGame() {
		for(MiniGame game : games) {
			game.reset();
		}
		gameActive = true;
		paused = false;
		rightKeyDown = false;
		leftKeyDown = false;
		upLocked = false;
		downLocked = false;
		startTime = System.nanoTime(); // Set startTime with nanosecond precision
		timer.start();
	}

	public void endGame() {
		highScore = Math.max(highScore, points);
		timer.stop();
		gameActive = false;
		System.out.println("Game Over");
		System.out.println("Points: " + points);
		System.out.println("High Score: " + highScore);
	}

	public void pauseGame() {
		if(paused) {
			startTime += System.nanoTime() - pauseTime; // Shift startTime to reduce calculations
			pauseTime = 0;
			timer.start();
			paused = false;
		}
		else {
			pauseTime = System.nanoTime();
			timer.stop();
			paused = true;
		}

		for(MiniGame game : games) {
			game.pause(paused);
		}
	}

	public String getGameName() {
		return "Multitask";
	}

	public int getHighScore() {
		return highScore;
	}

	public String getInstructions() {
		return "";
	}

	public String getCredits() {
		return "";
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		pointLabel.setText(Integer.toString(points));
	}

	public void actionPerformed(ActionEvent e) {
		if(gameActive) {
			long elapsedns = System.nanoTime() - startTime;
			points = (int) (elapsedns * Math.pow(10, -9)); // Points = seconds from start
			if(points == 15 && games.size() == 1) {
				games.add(new Dodge());
				constructLayout();
			}
			else if(points == 70 && games.size() == 2) {
				// games.add(new Capture());
				constructLayout();
			}
			else if(points == 100 && games.size() == 3) {
				// games.add(new Helicopter());
				constructLayout();
			}

			long elapsedms = (long) (elapsedns * Math.pow(10, -6));
			for(MiniGame game : games) {
				game.update(elapsedms);
				if(game.gameOver()) {
					game.setBackground(Color.WHITE);
					repaint();
					endGame();
					return;
				}
			}

			if(rightKeyDown) {
				((Balance) games.get(0)).updateAngularVelocity(0.09);
			}
			if(leftKeyDown) {
				((Balance) games.get(0)).updateAngularVelocity(-0.09);
			}

			repaint();
		}
	}

	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {
		if(gameActive) {
			switch(e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					rightKeyDown = false;
					break;
				case KeyEvent.VK_LEFT:
					leftKeyDown = false;
					break;
				case KeyEvent.VK_UP:
					upLocked = false;
					break;
				case KeyEvent.VK_DOWN:
					downLocked = false;
					break;
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_ENTER:
				System.out.println("Enter");
				startGame();
				break;
			case KeyEvent.VK_SPACE:
				System.out.println("Space");
				endGame();
				break;
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
				break;
		}
		if(gameActive) {
			switch(e.getKeyCode()) {
				case KeyEvent.VK_P:
					pauseGame();
					break;
				case KeyEvent.VK_RIGHT:
					rightKeyDown = true;
					break;
				case KeyEvent.VK_LEFT:
					leftKeyDown = true;
					break;
				case KeyEvent.VK_UP:
					if(!upLocked) { // Lock key to only trigger once on keyDown
						((Dodge) games.get(1)).moveUp();
						upLocked = true;
					}
					break;
				case KeyEvent.VK_DOWN:
					if(!downLocked) { // Lock key to only trigger once on keyDown
						((Dodge) games.get(1)).moveDown();
						downLocked = true;
					}
					break;
			}
		}
	}
}