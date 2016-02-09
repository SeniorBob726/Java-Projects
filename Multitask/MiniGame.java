import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class MiniGame extends JPanel implements KeyListener {
	private boolean firstGame = false;

	public boolean getFirstGame() {
		return firstGame;
	}

	public void setFirstGame(boolean b) {
		firstGame = b;
	}
}