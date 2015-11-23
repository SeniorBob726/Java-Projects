public class Main {
	public static void main(String[] args) {
		int n = 4;
		int[] squares = new int[n + 1];
		for(int i = 0; i < squares.length; i++) {
			squares[i] = i*i;
		}
		for(int i = squares.length - 1; i > 0; i--) {
			if(i == 1) {
				System.out.print(squares[i]);
			}
			else {
				System.out.print(squares[i] + ", ");
			}
		}
	}
}