public class StudentReserved extends Reserved {
	public StudentReserved(int d) {
		super(d);
	}

	public double getPrice() {
		return super.getPrice() / 2;
	}

	public String toString() {
		return super.toString() + " (Student ID Required)";
	}
}