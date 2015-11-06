public class LipogramAnalyser {
	private String analyserText;

	public LipogramAnalyser(String text) {
		analyserText = text;
	}

	public String getAnalyserText() {
		return analyserText;
	}

	public void setAnalyserText(String text) {
		analyserText = text;
	}

	public String mark(char letter) {
		return analyserText.replace(letter, '#');
	}

	public String allWordsWith(char letter) {
		String words = "";
		int j = 0, k = 0;
		for(int i = 0; i < analyserText.length(); i++) {
			j = i;
			k = i;
			if(analyserText.charAt(i) == letter) {
				while(j >= 0 && analyserText.charAt(j) != ' ') {
					j--;
				}

				while(k < analyserText.length() && analyserText.charAt(k) != ' ') {
					k++;
				}
				i = k;
				words += analyserText.substring(j + 1, k) + "\n";
			}
		}

		return words;
	}
}