/**
* This program takes a text file, creates an index (by line numbers)
*  for all the words in the file and writes the index
*  into the output file.  The program takes input and output file names
*  from the command-line args or prompts the user for the file names.
*/

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class AddressReader {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		String fileName;

		// Open input file:

		if(args.length > 0) {
			fileName = args[0];
		}
		else {
			System.out.print("\nEnter input file name: ");
			fileName = scanner.nextLine().trim();
		}

		BufferedReader inputFile = new BufferedReader(new FileReader(fileName), 1024);

		PersonalDatabase database = new PersonalDatabase();

		String line = "";
		String input = "";
		int lineNum = 0;
		while((line = inputFile.readLine()) != null) {
			input += line;
		}
		int index = -1;
		System.out.println(input.length());
		System.out.println(input.indexOf("\"", 0));
		int count = 0;
		while (input.indexOf("\"", index + 1) != -1) {
			count++;
			index = input.indexOf("\"", index + 1);
			int endIndex = input.indexOf("\"", index + 1);

			String fName = input.substring(index + 1, endIndex);
			index = input.indexOf("\"", endIndex + 1);
			endIndex = input.indexOf("\"", index + 1);

			String lName = input.substring(index + 1, endIndex);
			index = input.indexOf("\"", endIndex + 1);
			endIndex = input.indexOf("\"", index + 1);

			String company = input.substring(index + 1, endIndex);
			index = input.indexOf("\"", endIndex + 1);
			endIndex = input.indexOf("\"", index + 1);

			String address = input.substring(index + 1, endIndex);
			index = input.indexOf("\"", endIndex + 1);
			endIndex = input.indexOf("\"", index + 1);

			String city = input.substring(index + 1, endIndex);
			index = input.indexOf("\"", endIndex + 1);
			endIndex = input.indexOf("\"", index + 1);

			String county = input.substring(index + 1, endIndex);
			index = input.indexOf("\"", endIndex + 1);
			endIndex = input.indexOf("\"", index + 1);

			String state = input.substring(index + 1, endIndex);
			index = endIndex + 2;
			endIndex = input.indexOf(",", index); // Look for comma here because zip is not in quotes.

			String zip = input.substring(index, endIndex);
			index = input.indexOf("\"", endIndex + 1);
			endIndex = input.indexOf("\"", index + 1);

			String phone1 = input.substring(index + 1, endIndex);
			index = input.indexOf("\"", endIndex + 1);
			endIndex = input.indexOf("\"", index + 1);

			String phone2 = input.substring(index + 1, endIndex);
			index = input.indexOf("\"", endIndex + 1);
			endIndex = input.indexOf("\"", index + 1);

			String email = input.substring(index + 1, endIndex);
			index = input.indexOf("\"", endIndex + 1);
			endIndex = input.indexOf("\"", index + 1);
			String web = input.substring(index + 1, endIndex);

			PersonalInfo info = new PersonalInfo(fName, lName, address, city, county, state, zip);
			info.addPhoneNumber(phone1);
			info.addPhoneNumber(phone2);
			info.addEmailAddress(email);
			info.addWebsite(web);
			database.add(info);
			index = endIndex;
		}

		inputFile.close();

		System.out.println("Done loading addresses " + database.size() + " entries");

		// Randomly set ages and voting affiliations
		for(int i = 0; i < database.size(); i++) {
			int age = (int)(Math.random() * 100) + 18;
			database.get(i).setAge(age);
			database.get(i).setVotingAffiliation((int)(Math.random() * 3));
		}

		while(true) {
			System.out.println("Search Database:");
			System.out.println("1. Find People from State");
			System.out.println("2. Find People between ages");
			System.out.println("3. Find Voters of an affiliation in a particular state");
			System.out.println("4. Quit");

			int choice = scanner.nextInt();
			scanner.nextLine();

			ArrayList<PersonalInfo> findResults = new ArrayList<PersonalInfo>();
			switch(choice) {
				case 1:
					System.out.print("Enter state: ");
					String state = scanner.nextLine();
					for(PersonalInfo p : database.findPeopleFromState(state)) {
						System.out.println(p);
					}
					findResults = database.findPeopleFromState(state);
					Collections.sort(findResults); // Default alphabetical sorting, last name first
					for(int i = 0; i < findResults.size() && i < 10; i++) {
						System.out.println(findResults.get(i));
					}
					break;
				case 2:
					System.out.print("Age Range\n - Enter lower age: ");
					int lower = scanner.nextInt();
					System.out.print(" - Enter upper age: ");
					int upper = scanner.nextInt();
					findResults = database.findPeopleBetweenAges(lower, upper);
					Collections.sort(findResults, new AgeComparator()); // Age sorting
					for(int i = 0; i < findResults.size() && i < 10; i++) {
						System.out.println(findResults.get(i));
					}
					break;
				case 3:
					System.out.print("Voting Affiliation by State\n - Enter state: ");
					String st = scanner.nextLine();
					System.out.print(" - Enter voting affiliation: ");
					int vA = scanner.nextInt();
					findResults = database.findVotingAffiliationsByState(st, vA);
					Collections.sort(findResults); // Default alphabetical sorting, last name first
					for(int i = 0; i < findResults.size() && i < 10; i++) {
						System.out.println(findResults.get(i));
					}
					break;
				case 4:
					System.out.print("Goodbye!");
					System.exit(0);
					break;
			}
			if(findResults.size() > 10) {
				System.out.print("Showing first 10 matches. List all matches? [Y/N]: ");
				String yn = scanner.next().toLowerCase();
				scanner.nextLine();
				if("y".equals(yn)) {
					for(int i = 10; i < findResults.size(); i++) {
						System.out.println(findResults.get(i));
					}
				}
			}
		}
	}
}