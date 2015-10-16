import java.util.Scanner;

public class CylinderTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter radius of base: ");
		int radius = scanner.nextInt();
		System.out.print("Enter height: ");
		double height = scanner.nextDouble();

		Cylinder cylinder = new Cylinder(radius, height);
		System.out.println(cylinder);
	}
}