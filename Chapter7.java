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

	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		sumTo(scanner.nextInt());
	}
}