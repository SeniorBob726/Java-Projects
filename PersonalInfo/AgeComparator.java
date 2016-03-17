import java.util.Comparator;

public class AgeComparator implements Comparator<PersonalInfo> {
	public int compare(PersonalInfo pi1, PersonalInfo pi2) {
		return pi1.getAge() - pi2.getAge();
	}
}