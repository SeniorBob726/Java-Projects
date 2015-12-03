public class ArrayPractice {
	public static void main(String[] args) {
		String[] values = {"For", "Never", "To", "Return", "Take"};
		for(int i = 0; i < values.length; i++) {
			System.out.print(values[i]);
		}
		System.out.println();

		values[1] = null;
		values[3] = null;

		int j = 0;
		for(int i = 0; i < values.length; i++) {
			String v = values[i];
			if(v != null) {
				values[j++] = v;
			}
		}

		for(int i = 0; i < values.length; i++) {
			System.out.print(values[i]);
		}
		System.out.println();
	}
}