// Implements the game of Craps logic

public class RulesClass {
	private int point = 0;

	private static boolean pairWith(int a, int b, int c, int n) {
		return (a == b && c == n && a != c)
			|| (a == c && b == n && a != b)
			|| (b == c && a == n && b != a);
	}
	
	private static boolean threeInARow(int a, int b, int c, int x, int y, int z) {
		return (a == x && b == y && c == z)
			|| (a == x && b == z && c == y)
			|| (a == y && b == x && c == z)
			|| (a == y && b == z && c == x)
			|| (a == z && b == x && c == y)
			|| (a == z && b == y && c == x);
	}

	/**
	 *  a, b, c are the values of the dice
	 */
	public int processRoll(int a, int b, int c) {
		int result = 0;

		if((a == b && b == c) || threeInARow(a, b, c, 4, 5, 6) || pairWith(a, b, c, 6)) {
			result = 1;
		}
		else if(threeInARow(a, b, c, 1, 2, 3) || pairWith(a, b, c, 1)) {
			result = -1;
		}
		else {
			for(int i = 2; i <= 5; i++) {
				if(pairWith(a, b, c, i)) {
					if(point == 0) {
						point = i;
					}
					else if(i > point) {
						result = 1;
						point = 0;
					}
					else {
						result = -1;
						point = 0;
					}
					break;
				}
			}
		}

		return result;
	}

	/**
	 *  Returns the saved point
	 */
	public int getPoint() {
		return point;
	}
}