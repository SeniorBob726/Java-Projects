import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Chapter7 {
	public static void MexicoPopulation(int targetPop) {
		int START_YEAR = 2014;
		double START_POP = 123.8;
		double GROWTH_RATE = 1.005;
		int targetYear = (int)Math.ceil(Math.log(targetPop / START_POP) / Math.log(GROWTH_RATE));
		System.out.println("Mexico will reach " + targetPop + " million in " + (targetYear + START_YEAR));
	}

	public static int addOdds(int n) {
		int sum = 0;
		for(int i = 1; i < n; i++) {
			sum += i;
		}
		return sum;
	}

	public static void sumTo(int n) {
		int sum = 0;
		String addend = "";
		for(int i = 1; i <= n; i++) {
			addend += i + " + ";
			sum += i;
		}
		addend = addend.substring(0, addend.length() - 3);
		System.out.println(addend + " = " + sum);
	}

	public static boolean isPrime(int n) {
		if(n <= 1) {
			return false;
		}
		else if(n == 2) {
			return true;
		}

		int m = 3;
		while(m * m <= n) {
			if(n % m == 0) {
				return false;
			}

			m += 2;
		}

		return true;
	}

	public static boolean isPrimeEfficient(int n) {
		return false;
	}

	public static int sumDigits(int n) {
		int sum = 0;
		while(n > 0) {
			sum += n % 10;
			n /= 10;
		}
		return sum;
	}

	public static double averageScore() {
		Scanner input = null;

		try {
			input = new Scanner(new File("scores.dat"));
		}
		catch(FileNotFoundException e) {
			System.out.println("Can't open scores.dat");
			System.exit(1);
		}

		double average = 0;
		int i = 0;

		while(input.hasNextInt()) {
			average += input.nextInt();
			i++;
		}

		return average / i;
	}

	public static String twinPrimes() {
		String output = "";
		int i = 0;

		int n = 3;
		int lastPrime = 3;

		while(i < 20) {
			if(isPrime(n)) {
				if(n - lastPrime == 2) {
					output += lastPrime + " and " + n + ", ";
					i++;
				}
				lastPrime = n;
				n += 2;
			}
			else {
				n += 2;
			}
		}

		return output.substring(0, output.length() - 2);
	}

	public static int flowchartOutput(int n, int b) {
		int p = 1;

		while(p <= n) {
			n -= p;
			p *= b;
		}

		return n;
	}

	public static double goldenRatio(int n) { // n = 10 has a difference of 5.646e-5 from the golden ratio
		double xprev;
		double x = 1;

		while(n > 0) {
			xprev = x;
			x = 1 + 1.0 / xprev;
			n--;
		}

		return x;
	}
}