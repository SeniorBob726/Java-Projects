public class Quadratic {
	private double a, b, c;

	public void setCoefficients(int ca, int cb, int cc) {
		a = ca;
		b = cb;
		c = cc;
	}

	public double[] quadratic() {
		double[] roots = new double[2];
		if(a == 0) {
			throw new IllegalArgumentException("a = 0");
		}

		double disc = b * b - 4 * a * c;
		if(disc < 0) {
			return null;
		}
		roots[0] = (-b + Math.sqrt(disc)) / 2 / a;
		roots[1] = (-b - Math.sqrt(disc)) / 2 / a;

		return roots;
	}
}