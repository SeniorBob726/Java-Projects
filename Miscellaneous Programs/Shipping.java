public class Shipping {
	public double computeShippingCost(int nJars) {
		int nCartons = (int) Math.ceil(nJars / 12.0);
		int totalOunces = nCartons * 25 + nJars * 21;
		int lbs = (int) Math.ceil(totalOunces / 16.0);
		return 1.44 * nCartons + 0.96 * lbs + 3;
	}
}