public class Kitty {
	private double money = 0.0;

	public bet(double m) {
		money += m;
	}

	public roundEnd() {
		money = 0.0;
	}
}