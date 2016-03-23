import java.util.Scanner;

public class Thesaurus {
	String[][] words; // Contains all words in Thesaurus
	String[][] synonyms; // Contains the synonyms, each element has a corresponding element in words

	public Thesaurus(String[][] starterWords, String[][] starterSyns) {
		int maxC = 100;
		int maxR = 26; // Each row corresponds to letter

		words = new String[maxR][maxC];
		synonyms = new String[maxR][maxC];

		for(int r = 0; r < starterWords.length; r++) {
			for(int c = 0; c < starterWords[r].length; c++) {
				words[r][c] = starterWords[r][c];
				synonyms[r][c] = starterSyns[r][c];
			}
		}
	}

	/** This is a private method used by findSynonyms and addWord to quickly find the correct
	* row in the words 2D array based on the first letter of the w String passed in.
	* For example if w = "apple", getRow should return 0, because the 'a' indicates apple
	* would be stored in row 0.
	* @param w <code>String</code> value containg a word to search for
	* @return int containing the appropriate row number in words for w.
	*/
	private int getRow(String w) {
		w = w.toLowerCase();
		char c = w.charAt(0); // Get first character
		int row = c - 'a';

		return row;
	}

	/**
	* This method returns the synonyms for the parameter w.
	* Its first check is if w exists in the words 2D array.
	* If it exists, return the corresponding element in the synonyms 2D array.
	* getRow() should be used so that the entire 2D array does not need to be
	* searched.
	*
	* If w does not exist in the words 2D array, look for it in the synonyms 2D array,
	* if it exists return the other synonyms for this element and the corresponding element
	* in the words 2D array.
	*
	* Otherwise, return "not found" message.
	*
	* @param w <code>String</code> value containg a word to search for
	* @return String consisting of synonyms for w.
	*/
	public String findSynonyms(String w) {
		int row = getRow(w);
		for(int col = 0; col < words[row].length; col++) {
			if(w.equals(words[row][col])) {
				return "Synonyms: " + synonyms[row][col];
			}
		}

		for(row = 0; row < synonyms.length; row++) {
			for(int col = 0; col < synonyms[row].length; col++) {
				if(synonyms[row][col] != null && synonyms[row][col].indexOf(w) != -1) {
					String[] syns = synonyms[row][col].split(",");
					String output = "Synonyms: " + words[row][col] + ", ";
					for(int i = 0; i < syns.length; i++) {
						if(!w.equals(syns[i])) {
							output += syns[i].trim() + ", ";
						}
					}
					return output.substring(0, output.length() - 2);
				}
			}
		}

		return "- Word not found -";
	}

	/**
	* This method adds a word to the Thesaurus.
	* getRow() should be called to find the correct row in the 2D arrays.
	* Once the row is found, check to see there is room to insert a word.
	*
	* Use the insert algorithm, to ensure alphabetical order of array is preserved.
	* Use String's compareTo method to find the correct spot to insert the word.
	* Otherwise, return "not found" message.
	*
	* Insert the synonyms String
	*
	* @param w <code>String</code> value containg a word to search for
	* @param syns <code>String</code> value containg synonyms for the word to be inserted
	* @return boolean indicating whether the word could be inserted.
	*/
	public boolean addWord(String w, String syns) {
		int row = getRow(w);
		int size = 0;
		for(int i = 0; i < words[row].length; i++) {
			if(words[row][i] != null) {
				size++;
			}
		}

		if(size < words[row].length) {
			int k = size - 1;
			while(k >= 0 && w.compareTo(words[row][k]) <= 0) {
				if(w.compareTo(words[row][k]) == 0) {
					return false;
				}
				words[row][k + 1] = words[row][k];
				k--;
			}
			words[row][k + 1] = w;
			synonyms[row][k + 1] = syns;
			return true;
		}

		return false;
	}

	/**
	* This method returns all of the words in the Thesaurus. Each row should start a new line.
	*/
@Override
	public String toString() {
		String output = "";

		for(int row = 0; row < words.length; row++) {
			for(int col = 0; col < words[row].length; col++) {
				if(words[row][col] != null) {
					output += "- " + words[row][col] + "\n";
				}
			}
		}

		return output;
	}

	public static void main(String[] args) {
		String[][] starterWords = {
			{"able", "average"},
			{"bad", "bald", "big"},
			{"carefree", "cold", "cute"},
			{"dangerous", "daring", "dead", "desperate"},
			{"eager", "excited"},
			{"far", "friendly"}
		};

		String[][] starterSyns = {
			{"capable, skillful", "regular, mean"},
			{"terrible, awful, lousy", "hairless", "large, gigantic, enormous"},
			{"happy, worry-free", "frigid, icy", "adorable"},
			{"risky", "adventurous, risktaking", "gone", "dire"},
			{"excited, intent", "animated, enthusiastic"},
			{"distant, remote", "affable, welcoming"}
		};

		Thesaurus thesaurus = new Thesaurus(starterWords, starterSyns);

		Scanner reader = new Scanner(System.in);
		while(true) {
			System.out.println("Thesaurus");
			System.out.println("1) Enter a word to look up");
			System.out.println("2) Add a word to Thesaurus");
			System.out.println("3) View Thesaurus");
			System.out.print(">");
			String choice = reader.nextLine();
			System.out.println();
			int option = 0;
			switch(choice) {
				case "1":
					option = 1;
					break;
				case "2":
					option = 2;
					break;
				case "3":
					option = 3;
					break;
				default:
					option = 0;
					break;
			}

			if(option == 1) {
				System.out.print("Enter a word to look up: ");
				String word = reader.nextLine();
				word = word.toLowerCase();
				word = word.trim();
				if(word.indexOf(" ") > -1) {
					word = word.substring(0, word.indexOf(" "));
				}
				System.out.println(thesaurus.findSynonyms(word));
			}
			else if(option == 2) {
				System.out.print("Enter a word to add: ");
				String word = reader.nextLine();
				word = word.trim();
				if(word.indexOf(" ") > -1) {
					word = word.substring(0, word.indexOf(" "));
				}
				System.out.print("Enter synonyms for " + word + " separated by a comma: ");
				String synonyms = reader.nextLine();

				if(thesaurus.addWord(word, synonyms) == false) {
					System.out.println("- No room in Thesaurus -");
				}
			}
			else if(option == 3) {
				System.out.println(thesaurus);
			}
		}
	}
}