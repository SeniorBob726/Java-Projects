import java.util.ArrayList;
import java.util.Collections;

public class SortedWordList extends ArrayList<String> {
	public SortedWordList() {
		super();
	}

	public SortedWordList(int c) {
		super(c);
	}

	public int indexOf(String s) {
		int low = 0;
		int high = this.size() - 1;
		while(low <= high) {
			int middle = (high + low) / 2;
			int diff = this.get(middle).compareToIgnoreCase(s);
			if(diff == 0) {
				return middle;
			}
			else if(diff > 0) {
				high = middle - 1;
			}
			else {
				low = middle + 1;
			}
		}
		return -1;
	}

	public boolean contains(String s) {
		return indexOf(s) != -1;
	}

	public String set(int i, String word) {
		if((i > 0 && this.get(i - 1).compareToIgnoreCase(word) >= 0) || (i < this.size() - 1 && this.get(i + 1).compareToIgnoreCase(word) <= 0)) {
			throw new IllegalArgumentException("index = " + i + " word = " + word);
		}
		return super.set(i, word);
	}

	public boolean add(String word) {
		int low = 0;
		int high = this.size();
		while(low < high) {
			int middle = (high + low) / 2;
			int diff = this.get(middle).compareToIgnoreCase(word);
			if(diff == 0) {
				return false;
			}
			else if(diff > 0) {
				high = middle;
			}
			else {
				low = middle + 1;
			}
		}
		super.add(high, word);
		return true;
	}

	public void add(int i, String word) {
		if((i > 0 && this.get(i - 1).compareToIgnoreCase(word) >= 0) || (i < this.size() && this.get(i).compareToIgnoreCase(word) <= 0)) {
			throw new IllegalArgumentException("index = " + i + " word = " + word);
		}
		super.add(i, word);
	}

	public static void main(String[] args) {
		SortedWordList l = new SortedWordList();
		l.add("ipsum");
		l.add("dolor");
		l.add("sit");
		l.add("amet");
		l.add("consectetur");
		l.add("adipisicing");
		l.add("elit");
		l.add("sed");
		l.add("do");
		l.add("eiusmod");
		l.add("tempor");
		l.add("incididunt");
		l.add("ut");
		l.add("labore");
		l.add("et");
		l.add("dolore");
		l.add("magna");
		l.add("aliqua");
		l.add("Ut");
		l.add("enim");
		l.add("ad");
		l.add("minim");
		l.add("veniam");
		System.out.println(l);
		System.out.println(l.add(20, "u"));
		System.out.println(l.indexOf("u"));
		System.out.println(l);
	}
}