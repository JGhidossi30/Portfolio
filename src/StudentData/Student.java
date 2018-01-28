package StudentData;

@SuppressWarnings("rawtypes")
public class Student implements Comparable
{
	private Name name;
	private int numGrades;
	private double average;
	public Student(String fname, String lname)
	{
		this.name = new Name(fname, lname);
	}
	public void addGrade(double grade)
	{
		this.average = ((this.average * this.numGrades) + grade) / ++this.numGrades;
	}
	public Name getName()
	{
		return name;
	}
	public double getAverage()
	{
		return average;
	}
	public int getNumGrades()
	{
		return numGrades;
	}
	public void print()
	{
		System.out.printf("%s, %s %.2f\n", name.getLastName(), name.getFirstName(), average);
	}
	@Override public int compareTo(Object o) 
	{
		Student s = (Student)o;
		return name.getLastName().toLowerCase().compareTo(s.getName().getLastName().toLowerCase());
	}
}