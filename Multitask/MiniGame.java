import javax.swing.*;

public abstract class MiniGame extends JPanel {
	public abstract void update();

	public double clamp(double value, double min, double max) {
		return Math.max(min, Math.min(max, value));
	}
}