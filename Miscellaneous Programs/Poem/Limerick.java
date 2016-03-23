public class Limerick extends Poem {
	private int[] syllables = {9, 9, 6, 6, 9};

	public int[] getSyllables() {
		return syllables;
	}

	public int getSyllables(int k) {
		return syllables[k];
	}
}