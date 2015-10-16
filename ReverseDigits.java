public class ReverseDigits {
	public static int reverse(int number) {
		// Number / 100 * 100 removes tens and ones place
		// Number % 10 * 10 gets ones place and moves to tens place
		// Number / 10 % 10 gets tens place and moves to ones place
		return number / 100 * 100 + number % 10 * 10 + number / 10 % 10;
	}

	public static void main(String[] args) {
		System.out.println("264 => " + reverse(264));
		System.out.println("2143 => " + reverse(2143));
		System.out.println("35 => " + reverse(35));
		System.out.println("1 => " + reverse(1));
		System.out.println("325 => " + reverse(325));
	}
}