import javax.swing.*;

public abstract class MiniGame extends JPanel {
	private static double k = 1.0;
	private static double gameSpeed = 1.0;

	public static void setGameSpeed(double gameSpeed) {
		this.gameSpeed = gameSpeed;
	}

	public static double getGameSpeed() {
		return gameSpeed;
	}

	public static double getK(){return k;}
	public static void setK(double k){this.k=k;}

	public abstract void reset();
	public abstract void update(long elapsedms);
	public abstract void pause(boolean paused);
	public abstract boolean gameOver();
	public abstract void k();

	public double clamp(double value, double min, double max) {
		return Math.max(min, Math.min(max, value));
	}
}