package StudentData;

import java.util.ArrayList;

public class Roster 
{
	private ArrayList<Student> students;
	public Roster()
	{
		students = new ArrayList<Student>();
	}
	public Student getStudent(int index)
	{
		return students.get(index);
	}
	public String getName(int i)
	{
		return students.get(i).getName().getLastName() + ", " + students.get(i).getName().getFirstName();
	}
	public int getSize()
	{
		return students.size();
	}
	public void addStudent(Student student)
	{
		students.add(student);
	}
	public void removeStudent(int index)
	{
		students.remove(index);
	}
	public void addGrade(int index, double grade)
	{
		students.get(index).addGrade(grade);
	}
	public void sortRoster(int choice)
	{
		switch (choice)
		{
		case 1:
			for (int i = 0; i < students.size() - 1; i++)
			{
				for (int j = i + 1; j < students.size(); j++)
				{
					if (students.get(i).compareTo(students.get(j)) > 0)
					{
						Student s = students.get(j);
						students.set(j, students.get(i));
						students.set(i, s);
					}
				}
			}
			break;
		case 2:
			for (int i = 0; i < students.size() - 1; i++)
			{
				for (int j = i + 1; j < students.size(); j++)
				{
					if (students.get(i).getAverage() < students.get(j).getAverage())
					{
						Student s = students.get(j);
						students.set(j, students.get(i));
						students.set(i, s);
					}
				}
			}
			break;
		default:
			System.out.println("Invalid, must be 1 or 2.");
			return;
		}
		System.out.println();
		print();
	}
	public void print()
	{
		for (int i = 0; i < students.size(); i++)
			students.get(i).print();
	}
}