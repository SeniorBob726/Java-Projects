import javax.swing.*;

public abstract class MiniGame extends JPanel {
	public abstract void reset();
	public abstract void update(long elapsedms);
	public abstract void pause(boolean paused);
	public abstract boolean gameOver();

	public double clamp(double value, double min, double max) {
		return Math.max(min, Math.min(max, value));
	}
}