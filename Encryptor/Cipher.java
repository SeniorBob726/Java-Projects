public class Cipher {
	private static String plaintext;

	public Cipher(String text) {
		plaintext = text;
	}

	public String getAnalyserText() {
		return plaintext;
	}

	public void setAnalyserText(String text) {
		plaintext = text;
	}

	public static String caesarShift(char letter) {
		String ciphertext = plaintext;
		for(int i = 0; i < plaintext.length(); i++) {
			System.out.println((int) plaintext.charAt(i));
			System.out.println(((int) plaintext.charAt(i) - 65 + 1) % 26);
		}

		return "";
	}

	public static void main(String[] args) {
		plaintext = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
		caesarShift('e');
	}
}