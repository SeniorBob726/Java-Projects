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
import java.util.Scanner;

public class AddressReader {
	public static void main(String[] args) throws IOException {
		Scanner keyboard = new Scanner(System.in);
		String fileName;

		// Open input file:

		if(args.length > 0) {
			fileName = args[0];
		}
		else {
			System.out.print("\nEnter input file name: ");
			fileName = keyboard.nextLine().trim();
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

			String lastName = input.substring(index + 1, endIndex);
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

			PersonalInfo info = new PersonalInfo(fName, lastName, address, city, county, state, zip, phone1, phone2, email, web);
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

			int choice = keyboard.nextInt();
			keyboard.nextLine();

			/*	switch(choice)
			{
				...
			}
			*/
		}
	}
}