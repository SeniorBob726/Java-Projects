public class LipogramAnalyser {
	private static String analyserText;

	public LipogramAnalyser(String text) {
		analyserText = text;
	}

	public String mark(char letter) {
		return analyserText.replace(letter, '#');
	}

	public static String allWordsWith(char letter) {
		int j = 0, k = 0;
		for(int i = 0; i < analyserText.length(); i++) {
			j = i;
			k = i;
			if(analyserText.charAt(i) == letter) {
				while(analyserText.charAt(j) != ' ' && j > 0) {
					j--;
				}

				while(analyserText.charAt(k) != ' ' && k > analyserText.length()) {
					k++;
				}
				System.out.println(analyserText);
				System.out.println(j);
				System.out.println(i);
				System.out.println(k);
			}
		}
	}

	public static void main(String[] args) {
		analyserText = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
		allWordsWith('e');
	}
}