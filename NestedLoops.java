public class NestedLoops {
	public static void main(String[] args) {
		int n = 5;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j <= i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}

		System.out.println();
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < i; j++) {
				System.out.print(" ");
			}

			for(int j = 0; j < n - i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}

		System.out.println();

		n = 8;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(i % 2 == j % 2) {
					System.out.print("#");
				}
				else {
					System.out.print("o");
				}
			}
			System.out.println();
		}
	}
}