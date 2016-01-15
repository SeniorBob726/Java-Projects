import java.util.ArrayList;

public class DocumentIndex extends ArrayList<IndexEntry> {
	public DocumentIndex() {
		super();
	}

	public DocumentIndex(int capacity) {
		super(capacity);
	}

	private int foundOrInserted(String word) {
		int index = 0;
		for(int i = 0; i < this.size(); i++) {
			String entryWord = this.get(i).getWord().toUpperCase();
			if(word.toUpperCase().equals(entryWord)) {
				return i;
			}
		}

		return index;
	}

	public void addWord(String word, int num) {
		this.get(foundOrInserted(word)).add(num);
	}
}