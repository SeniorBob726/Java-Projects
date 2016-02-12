public class Main {
	public static void swap(String a, String b) {
		String c = a;
		a = b;
		b = c;
	}

	public static void main(String[] args) {
		String x = "Hello";
		String y = "Goodbye";
		System.out.println(x + y);
		swap(x, y);
		System.out.println(x + y);
	}
}