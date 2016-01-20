import java.util.ArrayList;

public class PersonalDatabase extends ArrayList<PersonalInfo> {
	public PersonalDatabase() {
		super();
	}

	public ArrayList<PersonalInfo> findPeopleFromState(String state) {
		ArrayList<PersonalInfo> peopleInState = new ArrayList<PersonalInfo>();
		for(PersonalInfo p : this) {
			if(state.equals(p.getAddress().getState())) {
				peopleInState
			}
		}
	}
}