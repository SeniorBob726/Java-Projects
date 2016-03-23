import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UserPanel extends JPanel implements ActionListener, KeyListener, ArcadeFriendly {
	private JLabel pointLabel;
	private javax.swing.Timer timer; // Game clock
	private Font font;
	private FontMetrics fontMetrics;

	private boolean rightKeyDown = false, leftKeyDown = false;
	private boolean upLocked = false, downLocked = false;
	private boolean wKeyDown = false, aKeyDown = false, sKeyDown = false, dKeyDown = false;
	private boolean spacebarDown = false;

	private long instructionDelay = 0;
	private boolean gameInstructions = false;
	private int instructionY = 0;
	private String[] instructions = {"Use the left and right arrow keys to balance the ball on the bar.", "Use the up and down arrow keys to avoid the spikes.", "Use the WASD keys to get all the squares before they disappear.", "Use the spacebar to avoid hitting the bars.", "The difficulty of all games has now increased."};
	private final String anyKeyToContinue = "ANY KEY TO CONTINUE";
	private Font aktcFont;
	private final int aktcWidth;

	private long startTime = 0;
	private long pauseTime = 0;
	private boolean gameActive = false;
	private boolean paused = false;
	private ArrayList<MiniGame> games; // Contains active MiniGames, Polymorphism is used here
	private int points;
	private int highScore = 0;

	public UserPanel(int width, int height) {
		font = new Font("Verdana", Font.PLAIN, 18);
		aktcFont = new Font("Verdana", Font.PLAIN, 12);
		fontMetrics = this.getFontMetrics(font);
		aktcWidth = this.getFontMetrics(aktcFont).stringWidth(anyKeyToContinue);

		points = 0;
		games = new ArrayList<MiniGame>(4);

		pointLabel = new JLabel(Integer.toString(points));
		pointLabel.setFont(font);
		pointLabel.setBorder(BorderFactory.createEmptyBorder(3, 5, 0, 5));
		pointLabel.setForeground(new Color(102, 102, 102));
		pointLabel.setBackground(new Color(216, 216, 216));
		pointLabel.setOpaque(true);

		timer = new javax.swing.Timer(1000 / 60, this); // 60 ticks per second

		addKeyListener(this); // Key controls
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		setPreferredSize(new Dimension(width, height));
		setBackground(new Color(153, 153, 153));

		resetGame();
	}

	public UserPanel() {
		this(600, 450);
	}

	public void constructLayout() { // Layout MiniGame panels
		this.removeAll();
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.insets = new Insets(0, 0, 2, 0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		this.add(pointLabel, c); // Add pointLabel with points display
		c.gridwidth = 1;

		// Single cell
		c.insets = new Insets(0, 0, 0, 0);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1;

		for(int i = 0; i < games.size(); i++) {
			if(games.size() == 2) { // Two cells - side by side, equal sizing
				c.insets = new Insets(0, i, 0, 1 - i);
				c.gridx = i;
				c.gridy = 1;
			}
			else if(games.size() == 3) { // Three cells - 2x2 grid, first cell takes two rows
				if(i == 0) {
					c.insets = new Insets(0, 0, 0, 1);
					c.gridheight = 2;
					c.gridx = 0;
					c.gridy = 1;
				}
				else {
					c.insets = new Insets(i - 1, 1, 2 - i, 0);
					c.gridheight = 1;
					c.gridx = 1;
					c.gridy = i;
				}
			}
			else if(games.size() == 4) { // Four cells - 2x2 grid, equal sizing
				if(i == 0 || i == 3) {
					c.insets = new Insets(i / 3, 0, 1 - i / 3, 1);
					c.gridx = 0;
				}
				else {
					c.insets = new Insets(i - 1, 1, 2 - i, 0);
					c.gridx = 1;
				}
				c.gridy = (i <= 1 ? 1 : 2);
			}
			this.add(games.get(i), c);
		}
		this.validate();
		this.repaint();
	}

	public void addMiniGame(MiniGame minigame) { // Polymorphism
		games.add(minigame);
		constructLayout();
		if(kp==-1){games.get(games.size()-1).k();};
		gameInstructions = true;
		instructionDelay = System.nanoTime();
		instructionY = 100;
		pauseGame();
	}

	public boolean running() {
		return gameActive && !paused;
	}

	public void resetGame() {
		points = 0;
		kp = 0;
		MiniGame.setGameSpeed(1.0);
		MiniGame.setK(1.0);
		gameActive = false;
		paused = false;
		gameInstructions = false;
		rightKeyDown = false;
		leftKeyDown = false;
		upLocked = false;
		downLocked = false;
		wKeyDown = false;
		aKeyDown = false;
		sKeyDown = false;
		dKeyDown = false;
		spacebarDown = false;
		timer.stop();
		games.clear();
		games.add(new Balance());
		constructLayout();
	}

	public void startGame() {
		if(paused) {
			System.out.println("Resume - Game");
			startTime += System.nanoTime() - pauseTime; // Shift startTime to reduce calculations
			pauseTime = 0;
			paused = false;
			gameInstructions = false;

			for(MiniGame game : games) { // Polymorphism
				game.pause(paused);
			}
		}
		else if(!running()) {
			System.out.println("Start - Game");
			resetGame();
			startTime = System.nanoTime(); // Set startTime with nanosecond precision
			timer.start();
			gameActive = true;

			gameInstructions = true;
			instructionDelay = System.nanoTime();
			instructionY = 100;
			pauseGame();
		}
	}

	public void stopGame() {
		if(gameActive) {
			System.out.println("End - Game");
			highScore = Math.max(highScore, points);
			timer.stop();
			gameActive = false;
			paused = false;
		}
	}

	public void pauseGame() {
		if(running()) {
			System.out.println("Pause - Game");
			pauseTime = System.nanoTime();
			rightKeyDown = false;
			leftKeyDown = false;
			upLocked = false;
			downLocked = false;
			wKeyDown = false;
			aKeyDown = false;
			sKeyDown = false;
			dKeyDown = false;
			spacebarDown = false;
			paused = true;

			for(MiniGame game : games) { // Polymorphism
				game.pause(paused);
			}
		}
	}

	public String getGameName() {
		return "Multitask";
	}

	public int getPoints() {
		return points;
	}

	public String getHighScore() {
		return Integer.toString(highScore);
	}

	public String getInstructions() {
		return instructions[0] + "\n" + instructions[1]+ "\n" + instructions[2] + "\n" + instructions[3];
	}

	public String getCredits() {
		return "Original game written in ActionScript by IcyLime.\nJava port by Taha.";
	}

	public void drawWrappingText(Graphics g, String text, int x, int y, int width) {
		int lineHeight = fontMetrics.getHeight();
		int spaceWidth = fontMetrics.stringWidth(" ");
		int xOffset = 0;
		int yOffset = 0;
		String[] words = text.split(" ");

		for(String word : words) {
			int wordWidth = fontMetrics.stringWidth(word);
			if(xOffset + wordWidth >= width) {
				yOffset += lineHeight;
				xOffset = 0;
			}

			g.drawString(word, x + xOffset, y + yOffset);
			xOffset += wordWidth + spaceWidth;
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		pointLabel.setText(Integer.toString(points));
	}

	public void paintChildren(Graphics g) {
		super.paintChildren(g);

		if(gameInstructions) {
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setFont(font);
			g2d.setColor(new Color(255, 255, 255, (int) (0.7 * 285 - instructionY)));
			int boxWidth = Math.max(getWidth() / 2, aktcWidth + 30);
			g2d.fillRect((getWidth() - boxWidth) / 2, instructionY, boxWidth, getHeight() / 3);

			if(instructionY == 30) {
				g2d.setColor(Color.BLACK);
				int index = MiniGame.getGameSpeed() == 1 ? games.size() - 1 : 4;
				String instruction = instructions[index];
				drawWrappingText(g2d, instruction, (getWidth() - boxWidth) / 2 + 15, 45 + fontMetrics.getAscent(), boxWidth - 30);
				g2d.setFont(aktcFont);
				g2d.drawString(anyKeyToContinue, (getWidth() - aktcWidth) / 2, 15 + getHeight() / 3);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(running()) {
			long elapsedns = System.nanoTime() - startTime;
			long elapsedms = (long) (elapsedns * Math.pow(10, -6));
			points = (int) (elapsedms / 1000); // Points = seconds from start
			if(points == 15 && games.size() == 1) {
				addMiniGame(new Dodge());
			}
			else if(points == 40 && games.size() == 2) {
				addMiniGame(new Capture());
			}
			else if(points == 80 && games.size() == 3) {
				addMiniGame(new Helicopter());
			}
			else if(points == 120 && MiniGame.getGameSpeed() == 1.0) {
				MiniGame.setGameSpeed(1.25);
				gameInstructions = true;
				instructionDelay = System.nanoTime();
				instructionY = 100;
				pauseGame();
			}

			for(MiniGame game : games) { // Polymorphism
				game.update(elapsedms);
				if(game.gameOver()) {
					game.setBackground(Color.WHITE);
					repaint();
					stopGame();
					return;
				}
			}

			if(rightKeyDown) {
				((Balance) games.get(0)).updateAngularVelocity(0.08);
			}
			if(leftKeyDown) {
				((Balance) games.get(0)).updateAngularVelocity(-0.08);
			}
			if(games.size() >= 3) {
				if(wKeyDown) {
					((Capture) games.get(2)).moveBox(0, -2);
				}
				if(aKeyDown) {
					((Capture) games.get(2)).moveBox(-2, 0);
				}
				if(sKeyDown) {
					((Capture) games.get(2)).moveBox(0, 2);
				}
				if(dKeyDown) {
					((Capture) games.get(2)).moveBox(2, 0);
				}
			}
			if(games.size() >= 4 && spacebarDown) {
				((Helicopter) games.get(3)).increaseLift();
			}
		}
		if(instructionY > 30) {
			instructionY -= 2;
		}
		repaint();
	}

	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {
		if(running()) {
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
				case KeyEvent.VK_W:
					wKeyDown = false;
					break;
				case KeyEvent.VK_A:
					aKeyDown = false;
					break;
				case KeyEvent.VK_S:
					sKeyDown = false;
					break;
				case KeyEvent.VK_D:
					dKeyDown = false;
					break;
				case KeyEvent.VK_SPACE:
					spacebarDown = false;
					break;
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_ESCAPE:
				stopGame();
				System.exit(0);
				break;
		}
		if(gameInstructions && instructionY <= 30) { // Wait until animation has completed to allow dismissal
			gameInstructions = false;
			startGame();
		}
		if(running()) {
			if(kp!=-1&&games.size()>=2){if(e.getKeyCode()==kc[kp]){kp++;if(kp==kc.length){k();kp=-1;}}else{kp=0;}}
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
					if(games.size() >= 2 && !upLocked) { // Lock key to only trigger once on keyDown
						((Dodge) games.get(1)).moveUp();
						upLocked = true;
					}
					break;
				case KeyEvent.VK_DOWN:
					if(games.size() >= 2 && !downLocked) { // Lock key to only trigger once on keyDown
						((Dodge) games.get(1)).moveDown();
						downLocked = true;
					}
					break;
				case KeyEvent.VK_W:
					wKeyDown = true;
					break;
				case KeyEvent.VK_A:
					aKeyDown = true;
					break;
				case KeyEvent.VK_S:
					sKeyDown = true;
					break;
				case KeyEvent.VK_D:
					dKeyDown = true;
					break;
				case KeyEvent.VK_SPACE:
					spacebarDown = true;
					break;
			}
		}
	}

	private int kp=0;
	private final int[] kc={KeyEvent.VK_UP,KeyEvent.VK_UP,KeyEvent.VK_DOWN,KeyEvent.VK_DOWN,KeyEvent.VK_LEFT,KeyEvent.VK_RIGHT,KeyEvent.VK_LEFT,KeyEvent.VK_RIGHT,KeyEvent.VK_B,KeyEvent.VK_A};
	public void k(){MiniGame.setK(0.4);for(MiniGame g:games){g.k();}}
}