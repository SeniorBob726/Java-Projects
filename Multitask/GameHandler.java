import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GameHandler extends JPanel implements ActionListener, KeyListener/*, JavaArcade */ {
	private javax.swing.Timer timer; // Controls how often stats are checked
	private int width, height;
	private Font font;
	private FontMetrics fontMetrics;

	private boolean rightKeyDown = false, leftKeyDown = false;
	private boolean upLocked = false, downLocked = false;

	private long startTime = 0;
	private boolean gameActive = false;
	private boolean paused = false;
	private ArrayList<MiniGame> games;
	private int points;
	private int highScore = 0;

	public GameHandler() {
		font = new Font("Courier", Font.PLAIN, 14);
		fontMetrics = this.getFontMetrics(font);

		points = 0;
		games = new ArrayList<MiniGame>(4);
		games.add(new Balance());
		games.add(new Dodge());

		width = 600;
		height = 450;
		timer = new javax.swing.Timer((int) (1000 / 60), this); // 60 ticks per second clock

		addKeyListener(this); // Key controls

		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(width, height));

		constructLayout();
	}

	public void constructLayout() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		this.add(Box.createVerticalStrut(fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent()), c);

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1;

		for(int i = 0; i < games.size(); i++) {
			if(games.size() == 2) {
				c.gridx = i;
				c.gridy = 1;
			}
			else if(games.size() == 3) {
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
			else if(games.size() == 4) {
				c.gridx = i % 2;
				c.gridy = (i == 0 ? 1 : i);
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
		timer.start();
		startTime = System.currentTimeMillis();
		gameActive = true;
	}

	public void endGame() {
		System.out.println("Game Over");
		System.out.println("Points: " + points);
		timer.stop();
		gameActive = false;
	}

	public void pauseGame() {
		if(paused) {
			timer.start();
			paused = false;
		}
		else {
			timer.stop();
			paused = true;
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

		g.setColor(new Color(50, 50, 50));
		g.fillRect(0, 0, width, fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent());

		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString(Integer.toString(points), 5, fontMetrics.getMaxAscent());
	}

	public void actionPerformed(ActionEvent e) {
		if(gameActive) {
			points = (int) ((e.getWhen() - startTime) / 1000);
			if(points == 15 && games.size() == 1) {
				// games.add(new Dodge());
				constructLayout();
			}
			else if(points == 70 && games.size() == 2) {
				// games.add(new Squares());
				constructLayout();
			}
			else if(points == 100 && games.size() == 3) {
				// games.add(new Helicopter());
				constructLayout();
			}

			for(MiniGame game : games) {
				game.update();
				if(game.gameOver()) {
					game.setBackground(Color.WHITE);
					repaint();
					endGame();
					return;
				}
			}

			if(rightKeyDown) {
				((Balance) games.get(0)).updateAngularVelocity(0.07);
			}
			if(leftKeyDown) {
				((Balance) games.get(0)).updateAngularVelocity(-0.07);
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
					if(!upLocked) {
						((Dodge) games.get(1)).moveUp();
						upLocked = true;
					}
					break;
				case KeyEvent.VK_DOWN:
					if(!downLocked) {
						((Dodge) games.get(1)).moveDown();
						downLocked = true;
					}
					break;
			}
		}
	}
}