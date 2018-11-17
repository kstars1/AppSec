package AppSec.utils;

public class User
{

	private String username;
	private String password;
	private String firstName;
	private String middleInitial;
	private String lastName;
	private String ssn;
	
	private boolean admin;

	public User()
	{
		setUsername("---");
		setPassword("---");
		setFirstName("---");
		setMiddleInitial("-");
		setLastName("---");
		setSSN("---------");
		setAdmin(false);
	}
	
	public User(String username, String password, String firstName, String middleInitial, String lastName, String ssn)
	{
		setUsername(username);
		setPassword(password);
		setFirstName(firstName);
		setMiddleInitial(middleInitial);
		setLastName(lastName);
		setSSN(ssn);
		setAdmin(false);
	}
	
	public User(String username, String password, String firstName, String middleInitial, String lastName, String ssn, boolean admin)
	{
		setUsername(username);
		setPassword(password);
		setFirstName(firstName);
		setMiddleInitial(middleInitial);
		setLastName(lastName);
		setSSN(ssn);
		setAdmin(admin);
	}
	
	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getMiddleInitial()
	{
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial)
	{
		this.middleInitial = middleInitial;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getSSN()
	{
		return ssn;
	}

	public void setSSN(String ssn)
	{
		this.ssn = ssn;
	}
	
	public boolean isAdmin()
	{
		return admin;
	}
	
	public void setAdmin(boolean admin)
	{
		this.admin = admin;
	}

}