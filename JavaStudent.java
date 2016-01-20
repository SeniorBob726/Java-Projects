import java.util.ArrayList;

public class JavaStudent {
	private String name;
	private ArrayList<String> favoriteMovies;

	public JavaStudent(String n, String mv1, String mv2, String mv3) {
		name = n;
		favoriteMovies.add(mv1);
		favoriteMovies.add(mv2);
		favoriteMovies.add(mv3);
	}

	public String getName() {
		return name;
	}

	public void setName(String n) {
		name = n;
	}

	public void addMovie(String m) {
		if(!favoriteMovies.contains(m)) {
			favoriteMovies.add(m);
		}
	}

	public ArrayList<String> getMovies() {
		return favoriteMovies;
	}

	public void removeLongTitles() {
		for(int i = favoriteMovies.size() - 1; i >= 0; i--) {
			if(favoriteMovies.get(i).length() > 6) {
				favoriteMovies.remove(i);
			}
		}
	}

	public String toString()  {
		String output = "Name: " + name + "\nMovies: ";

		for(String mv : favoriteMovies) {
			output += mv + ", ";
		}

		return output.substring(0, output.length() - 2);
	}
}