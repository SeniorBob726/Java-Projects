public class Haiku extends Poem {
	private int[] syllables = {5, 7, 5};

	public int[] getSyllables() {
		return syllables;
	}

	public int getSyllables(int k) {
		return syllables[k];
	}
}