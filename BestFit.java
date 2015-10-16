public class BestFit {
	public static int findBestFit(int size1, int size2, int space) {
		int bestFit = 0;
		if(space >= size1 + size2) {
			bestFit = 3;
		}
		else if(space >= size1 && space >= size2) {
			if(size1 >= size2) {
				return 1;
			}
			else {
				return 2;
			}
		}
		else if(space >= size1) {
			return 1;
		}
		else if(space >= size2) {
			return 2;
		}

		return bestFit;
	}

	public static void main(String[] args) {
		System.out.println(findBestFit(25, 50, 80));
		System.out.println(findBestFit(50, 70, 80));
		System.out.println(findBestFit(80, 90, 80));
		System.out.println(findBestFit(80, 80, 80));

		System.out.println(findBestFit(100, 200, 80));
	}
}