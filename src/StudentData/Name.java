package StudentData;

public class Name 
{
	private String fname, lname;
	public Name(String fname, String lname)
	{
		this.fname = fname;
		this.lname = lname;
	}
	public String getFirstName()
	{
		return fname;
	}
	public String getLastName()
	{
		return lname;
	}
}