import java.util.Random;

public class Die {
	private static Random random = new Random();
	private int numDots;

	public Die() {
		roll();
	}

	public void roll() {
		numDots = random.nextInt(6) + 1;
	}
	
	public int getNumDots() {
		return numDots;
	}
}