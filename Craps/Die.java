public class Die {
	private int face;

	public void roll() {
		face = (int)(Math.random() * 6 + 1);
	}

	public int getNumDots() {
		return face;
	}
}