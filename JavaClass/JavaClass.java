import java.util.ArrayList;

public class JavaClass extends ArrayList<JavaStudent> {
	public JavaClass() {
		super();
	}

	public ArrayList<String> getFavoriteMovies() {
		ArrayList<String> favoriteMovies = new ArrayList<String>();

		for(JavaStudent st : this) {
			for(String mv : st.getFavoriteMovies()) {
				if(!favoriteMovies.contains(mv)) {
					favoriteMovies.add(mv);
				}
			}
		}

		return favoriteMovies;
	}

	public String findTheFavoriteMovie() {
		ArrayList<String> movies = new ArrayList<String>();

		for(JavaStudent st : this) {
			for(String mv : st.getFavoriteMovies()) {
				movies.add(mv);
			}
		}

		ArrayList<Integer> frequencies = new ArrayList<Integer>();
		for(String mv : movies) {
			int count = 0;
			for(String mv2 : movies) {
				if(mv.equals(mv2)) {
					count++;
				}
			}
			frequencies.add(count);
		}

		int index = -1;
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < frequencies.size(); i++) {
			if(max < frequencies.get(i)) {
				index = i;
			}
		}

		return movies.get(i);
	}

	public void removeDuplicateStudents() {
		ArrayList<String> names = new ArrayList<String>();

		for(int i = this.size() - 1; i >= 0; i++) {
			JavaStudent st = this.get(i);
			if(names.contains(st.getName()) {
				this.remove(i);
			}
		}
	}
}