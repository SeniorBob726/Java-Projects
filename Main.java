import java.util.ArrayList;

public class Main {
	public static void add(ArrayList<Integer> a) {
		a.add(2);
		ArrayList<Integer> b = a;
		b.add(3);
	}

	public static void swap(String a, String b) {
		String c = a;
		a = b;
		b = c;
	}

	public static void main(String[] args) {
		String x = "Hello";
		String y = "Goodbye";
		System.out.println(x + " " + y);
		swap(x, y);
		System.out.println(x + " " + y);

		ArrayList<Integer> l = new ArrayList<Integer>();
		l.add(1);
		System.out.println(l);
		add(l);
		System.out.println(l);
	}
}