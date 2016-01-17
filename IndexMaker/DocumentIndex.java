import java.util.ArrayList;

public class DocumentIndex extends ArrayList<IndexEntry> {
	public DocumentIndex() {
		super();
	}

	public DocumentIndex(int capacity) {
		super(capacity);
	}

	private int foundOrInserted(String word) {
		for(int i = 0; i < this.size(); i++) {
			String entryWord = this.get(i).getWord().toUpperCase();
			if(word.toUpperCase().equals(entryWord)) {
				return i;
			}
			else if(word.toUpperCase().compareTo(entryWord) < 0) {
				IndexEntry ie = new IndexEntry(word);
				this.add(i, ie);
				return i;
			}
		}

		IndexEntry ie = new IndexEntry(word);
		this.add(ie);
		return this.size() - 1;
	}

	public void addWord(String word, int num) {
		this.get(foundOrInserted(word)).add(num);
	}

	public void addAllWords(String str, int num) {
		String[] words = str.split("\\W+");
		for(String s : words) {
			if(!s.equals("")) {
				addWord(s, num);
			}
		}
	}
}
