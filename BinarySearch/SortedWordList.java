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
			if(s.compareTo(this.get(middle)) == 0) {
				return middle;
			}
			else if(s.compareTo(this.get(middle)) > 0) {
				low = middle + 1;
			}
			else {
				high = middle - 1;
			}
		}
		return -1;
	}

	public boolean contains(String s) {
		return indexOf(s) != -1;
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
		Collections.sort(l);
		System.out.println(l);
		System.out.println(l.indexOf("x"));
	}
}