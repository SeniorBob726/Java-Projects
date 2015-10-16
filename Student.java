public class Student {
	private String name;
	private int numCourses;

	public Student(String n, int c) {
		name = n;
		numCourses = c;
	}

	public String getName() {
		return name;
	}

	public int getCourses() {
		return numCourses;
	}

	public void setName(String n) {
		name = n;
	}

	public void setCourses(int c) {
		numCourses = c;
	}

	public String toString() {
		return "Name: " + getName() + ", Courses: " + getCourses();
	}
}