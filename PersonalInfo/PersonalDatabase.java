import java.util.ArrayList;

public class PersonalDatabase extends ArrayList<PersonalInfo> {
	public PersonalDatabase() {
		super();
	}

	public ArrayList<PersonalInfo> findPeopleFromState(String state) {
		ArrayList<PersonalInfo> peopleFromState = new ArrayList<PersonalInfo>();
		for(PersonalInfo p : this) {
			if(state.equals(p.getAddress().getState())) {
				peopleFromState.add(p);
			}
		}

		return peopleFromState;
	}

	public ArrayList<PersonalInfo> findPeopleBetweenAges(int lowerAge, int upperAge) {
		ArrayList<PersonalInfo> peopleBetweenAges = new ArrayList<PersonalInfo>();
		for(PersonalInfo p : this) {
			if(lowerAge <= p.getAge() && p.getAge() <= upperAge) {
				peopleBetweenAges.add(p);
			}
		}

		return peopleBetweenAges;
	}

	public ArrayList<PersonalInfo> findVotingAffiliationsByState(String state, int votingAffiliation) {
		ArrayList<PersonalInfo> votingAffiliationsByState = new ArrayList<PersonalInfo>();
		for(PersonalInfo p : this) {
			if(state.equals(p.getAddress().getState()) && votingAffiliation == p.getVotingAffiliation()) {
				votingAffiliationsByState.add(p);
			}
		}

		return votingAffiliationsByState;
	}
}