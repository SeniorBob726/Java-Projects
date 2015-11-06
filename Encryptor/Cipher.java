public class Cipher {
	private String plaintext;

	public Cipher(String text) {
		plaintext = text;
	}

	public String getAnalyserText() {
		return plaintext;
	}

	public void setAnalyserText(String text) {
		plaintext = text;
	}

	public String caesarShift(char letter) {
		String ciphertext = plaintext;
		for(int i = 0; i < plaintext.length(); i++) {
			System.out.println((int) plaintext.charAt(i));
			System.out.println(((int) plaintext.charAt(i) - 65 + 1) % 26);
		}

		return "";
	}

	public static void main(String[] args) {
		System.out.println("T");
	}
}