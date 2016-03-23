public class ArrayPractice {
	public static int[] turnSingle(int[][] matrix) {
		int[] single = new int[matrix.length * matrix[0].length];
		for(int row = 0; row < matrix.length; row++) {
			for(int col = 0; col < matrix[row].length; col++) {
				single[row * matrix.length + col] = matrix[row][col];
			}
		}

		for(int i = 1; i < single.length; i++) {
			for(int j = i; j > 0; j--) {
				if(single[j] < single[j - 1]) {
					int temp = single[j];
					single[j] = single[j - 1];
					single[j - 1] = temp;
				}
			}
		}

		return single;
	}

	public static void main(String[] args) {
		int[][] matrix = {{2, 4, 8}, {3, 1, 7}, {9, 1, 4}};
		int[] single = turnSingle(matrix);
		for(int i : single) {
			System.out.print(i + " ");
		}
		System.out.println("\n");
		for(int[] row : matrix) {
			for(int i : row) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
}