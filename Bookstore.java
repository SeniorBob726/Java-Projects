import java.util.Scanner;

public class Bookstore{
	public static void main(String[] args){
		System.out.println("Bookstore");
		double inputPrice, markup, wholesale, totalPrice = 0, totalMarkup = 0;
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter sale price of book: $");
		inputPrice = reader.nextDouble();
		while(inputPrice >= 0){ // Loop until negative inputPrice
			wholesale = inputPrice / 1.1; // Simplified from inputPrice * 100 / 110
			markup = inputPrice - wholesale; // Subtract sale price from wholesale price
			totalPrice += inputPrice; // Add sale price to total
			totalMarkup += markup; // Add markup price to total
			System.out.printf("This book has a wholesale price of $%.2f and was marked up by $%.2f.\n", wholesale, markup);
			System.out.print("Enter sale price of book: $");
			inputPrice = reader.nextDouble();
		}
		System.out.print("\nTotal price: $" + String.format("%.2f", totalPrice) + ", Total markup: $" + String.format("%.2f", totalMarkup));
	}
}