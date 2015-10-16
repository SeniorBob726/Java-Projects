import java.util.Scanner;

public class MonetaryCoinTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the value of a coin: ");
		double v = scanner.nextDouble();
		double total = 0;

		while(v >= 0) {
			MonetaryCoin monetaryCoin = new MonetaryCoin(v);
			System.out.println("\tThe value of the coin is " + monetaryCoin.getValue());
			total += monetaryCoin.getValue();
			System.out.println("\tThe total value is " + total);
			System.out.print("Enter the value of another coin: ");
			v = scanner.nextDouble();
		}

		MonetaryCoin monetaryCoin = new MonetaryCoin(5);
		monetaryCoin.flip();
		System.out.println("\n" + monetaryCoin);
	}
}