package StudentData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class StudentData 
{
	private static Roster roster;
	private static boolean exitPrompt;
	private static String fileName;
	public static void main(String[] args)
	{
		Scanner scan;
		do
		{
			scan = new Scanner(System.in);
			System.out.print("What is the input of the file? ");
			fileName = scan.nextLine();
		} while (!inputFile(fileName));
		menu(scan);
	}
	public static void menu(Scanner scan)
	{ 
		System.out.print("\n1) Display Roster\n2) Add Student\n3) Remove Student\n4) Add Grade\n5) Sort Roster\n6) Write File\n7) Exit\nWhat would you like to do? ");
		int choice = 0;
		try
		{
			choice = Integer.parseInt(scan.nextLine());
		}
		catch (NumberFormatException e) {}
		switch(choice)
		{
		case 1: 
			System.out.println();
			roster.print();
			break;
		case 2: 
			String aName;
			do
			{
				System.out.print("\nWhat is the students name? ");
				aName = scan.nextLine();
				if (!addStudent(aName))
					System.out.println("Invalid, must have first and last name.");	
				else
					break;
			}
			while (!addStudent(aName));
			exitPrompt = true;
			break;
		case 3: 
			boolean loop;
			do
			{
				loop = true;
				System.out.println();
				for (int i = 0; i < roster.getSize(); i++)
					System.out.printf("%d) %s\n", i + 1, roster.getName(i));
				System.out.print("\nPlease choose a student to remove: ");
				try
				{
					int index = Integer.parseInt(scan.nextLine()) - 1;
					if (index < 0 || index > roster.getSize() - 1)
					{
						System.out.println("Invalid, must be a number between 1 and " + roster.getSize() + ".");
						loop = false;
						continue;
					}
					roster.removeStudent(index);
					exitPrompt = true;
				}
				catch (NumberFormatException e)
				{
					System.out.println("Invalid, must be a number between 1 and " + roster.getSize() + ".");
					loop = false;
				}
			} while (!loop);
			break;
		case 4: 
			boolean loopg;
			do
			{
				loopg = true;
				System.out.println();
				for (int i = 0; i < roster.getSize(); i++)
					System.out.printf("%d) %s\n", i + 1, roster.getName(i));
				System.out.print("\nPlease choose a student to grade: ");
				try
				{
					int index = Integer.parseInt(scan.nextLine()) - 1;
					if (index < 0 || index > roster.getSize() - 1)
					{
						System.out.println("Invalid, must be a number between 1 and " + roster.getSize() + ".");
						loopg = false;
						continue;
					}
					System.out.print("Please enter a new score: ");
					double grade = Double.parseDouble(scan.nextLine());
					roster.addGrade(index, grade);
					System.out.println();
					roster.getStudent(index).print();
					exitPrompt = true;
				}
				catch (NumberFormatException e)
				{
					System.out.println("Invalid, must be a number between 1 and " + roster.getSize() + ".");
					loopg = false;
				}
			} while (!loopg);
			break;
		case 5: 
			boolean loops;
			do
			{
				loops = true;
				System.out.print("\n1) Alphabetically\n2) GPA\nHow would you like to sort the roster? ");
				try
				{
					int sort = Integer.parseInt(scan.nextLine());
					if (sort != 1 && sort != 2)
					{
						System.out.println("Invalid, must be 1 or 2.");
						loops = false;
						continue;
					}
					roster.sortRoster(sort);
					exitPrompt = true;
				}
				catch (NumberFormatException e) 
				{
					System.out.println("Invalid, must be 1 or 2.");
					loops = false;
				}
			} while (!loops);
			break;
		case 6: 
			System.out.println();
			outputFile();
			exitPrompt = false;
			break;
		case 7: 
			System.out.println();
			exit(scan);
			break;
		default: 
			System.out.println("That is not a valid option.");
			break;
		}
		menu(scan);
	}
	public static boolean addStudent(String name)
	{
		if(name.split(" ").length != 2)
			return false;
		roster.addStudent(new Student(name.split(" ")[0], name.split(" ")[1]));
		return true;
	}
	public static boolean inputFile(String filename)
	{
		BufferedReader br;
		try 
		{
			BufferedReader test = new BufferedReader(new FileReader(filename));
			if (test.readLine() == null)
			{
				System.out.println("That file is empty.\n");
				test.close();
				return false;
			}
			test.close();
			br = new BufferedReader(new FileReader(filename));
			Gson gson = new Gson();
			roster = gson.fromJson(br, Roster.class);
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("That file could not be found.\n");
			return false;
		}
		catch (Exception e)
		{
			System.out.println("That file is not a well-formed JSON file.\n");
			return false;
		}
		return true;
	}
	public static void outputFile()
	{
		try (Writer writer = new FileWriter(fileName))
		{
			Gson gson = new GsonBuilder().create();
			gson.toJson(roster, writer);
		}
		catch (IOException e) {}
		System.out.println("File has been saved.");
	}
	public static void exit(Scanner scan)
	{
		if (exitPrompt)
		{
			System.out.print("Changes have been made since the file was last saved.\n1) Yes\n2) No\nWould you like to save the file before exiting? ");
			int response = Integer.parseInt(scan.nextLine());
			switch (response)
			{
			case 1:
				outputFile();
				break;
			case 2:
				System.out.println("File was not saved.");
				break;
			default: 
				System.out.println("That is not a valid option\n");
				exit(scan);
				break;
			}
		}
		System.out.print("Thank you for using my program!");
		System.exit(0);
	}
}
