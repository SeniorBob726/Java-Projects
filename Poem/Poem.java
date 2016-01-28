public abstract class Poem {
	public abstract int[] getSyllables();
	public abstract int getSyllables(int k);

	public int getNumLines() {
		return getSyllables().length;
	}

	public void printRhythm() {
		for(int i : getSyllables()) {
			String line = "";
			for(int j = 0; j < i; j++) {
				line += "ta-";
			}
			System.out.println(line.substring(0, line.length() - 1));
		}
	}

	public static void main(String[] args) {
		Haiku h = new Haiku();
		System.out.println("Haiku");
		System.out.println("Lines: " + h.getNumLines());
		System.out.println("Syllables at line 1: " + h.getSyllables(1));
		System.out.println("Rhythm");
		h.printRhythm();

		System.out.println();

		Limerick l = new Limerick();
		System.out.println("Limerick");
		System.out.println("Lines: " + l.getNumLines());
		System.out.println("Syllables at line 3: " + l.getSyllables(3));
		System.out.println("Rhythm");
		l.printRhythm();
	}
}