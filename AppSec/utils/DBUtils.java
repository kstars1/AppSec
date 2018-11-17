package AppSec.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.honeybadger.freeguard.beans.Claim;
import edu.honeybadger.freeguard.beans.User;

public class DBUtils
{

	public static void addUser(Connection conn, String username, String password, String SSN, 
			String firstName, String lastName, String middleInitial, int admin)throws SQLException
	{
		String sql = "insert into accounts(`SSN`, `firstname`, `lastname`, `middleinitial`, `username`, `password`, admin) "
				+ "values('" + SSN + "', '" + firstName + "', '" + lastName + "', '" + middleInitial + "', '" + username + "', '" + password + "', '" + admin + "'" + " )";
		System.out.println(sql);
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.execute();
	}

	public static User getUser(Connection conn, String ssn) throws SQLException
	{

		String sql = "select ssn, firstname, lastname, middleinitial, username, password, admin"
				+ " from accounts where ssn = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, ssn);

		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			String firstName = rs.getString("firstname");
			String lastName = rs.getString("lastname");
			String middleInitial = rs.getString("middleinitial");
			String username = rs.getString("username");
			String password = rs.getString("password");
			boolean admin = rs.getBoolean("admin");
			User user = new User(username, password, firstName, middleInitial, lastName, ssn, admin);
			return user;
		}
		return null;
	}

	public static User findUser(Connection conn,
			String username, String password) throws SQLException
	{

		String sql = "select ssn, firstname, lastname, middleinitial, username, password, admin"
				+ " from accounts where username = ? and password = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, username);
		pstm.setString(2, password);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			String ssn = rs.getString("ssn");
			String firstName = rs.getString("firstname");
			String lastName = rs.getString("lastname");
			String middleInitial = rs.getString("middleinitial");
			boolean admin = rs.getBoolean("admin");
			User user = new User(username, password, firstName, middleInitial, lastName, ssn, admin);
			return user;
		}
		return null;
	}

	public static User findUser(Connection conn, String username) throws SQLException
	{

		String sql = "select username, psswd"
				+ " from USER where username = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, username);

		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			String ssn = rs.getString("ssn");
			String password = rs.getString("password");
			User user = new User(username, psswd);
			return user;
		}
		return null;
	}

	public static List<Claim> queryMyClaims(Connection conn, String ssn) throws SQLException
	{
		String sql = "select id, ssn, amount from claims where ssn = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, ssn);

		ResultSet rs = pstm.executeQuery();
		List<Claim> list = new ArrayList<Claim>();
		while (rs.next())
		{
			String id = rs.getString("id");
			float amount = rs.getFloat("amount");
			Claim product = new Claim(id, ssn, amount);
			list.add(product);
		}
		return list;
	}

	public static List<Claim> queryAllClaims(Connection conn) throws SQLException
	{
		String sql = "select id, ssn, amount from claims";

		PreparedStatement pstm = conn.prepareStatement(sql);

		ResultSet rs = pstm.executeQuery();
		List<Claim> list = new ArrayList<Claim>();
		while (rs.next())
		{
			String id = rs.getString("id");
			String ssn = rs.getString("ssn");
			float amount = rs.getFloat("amount");
			Claim product = new Claim(id, ssn, amount);
			list.add(product);
		}
		return list;
	}

	public static List<String> ssnOfClaims(Connection conn) throws SQLException
	{
		String sql = "select ssn from accounts";

		PreparedStatement pstm = conn.prepareStatement(sql);

		ResultSet rs = pstm.executeQuery();
		List<String> list = new ArrayList<String>();
		while (rs.next())
		{
			String ssn = rs.getString("ssn");
			list.add(ssn);
		}
		return list;
	}

	public static Claim findClaim(Connection conn, String cid) throws SQLException
	{
		String sql = "select id, ssn, amount from claims a where id = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, cid);

		ResultSet rs = pstm.executeQuery();

		while (rs.next())
		{
			String id = rs.getString("id");
			String ssn = rs.getString("ssn");
			float amount = rs.getFloat("amount");
			Claim product = new Claim(id, ssn, amount);
			return product;
		}
		return null;
	}

	public static void updateClaim(Connection conn, Claim claim) throws SQLException
	{
		String sql = "update claims set amount = ? where id = ?"; 

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setDouble(1, claim.getAmount());
		pstm.setString(2, claim.getId());
		System.out.println(pstm.toString());
		pstm.executeUpdate();
	}

	//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	public static void fileClaim(Connection conn, Claim claim) throws SQLException
	{
		String sql = "insert into claims(ssn, amount) values (?, ?)";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, claim.getSSN());
		pstm.setDouble(2, claim.getAmount());

		pstm.executeUpdate();
	}

	public static void removeClaim(Connection conn, String id) throws SQLException
	{
		String sql = "delete from claims where id = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, id);
		pstm.executeUpdate();
	}

	public static void updateUsername(Connection conn, String ssn, String newUsername) throws SQLException
	{
		String sql = "update accounts set username = ? where ssn = ?"; 

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, newUsername);
		pstm.setString(2, ssn);
		pstm.executeUpdate();
	}

	public static void updatePassword(Connection conn, String ssn, String newPassword) throws SQLException
	{
		String sql = "update accounts set password = ? where ssn = ?"; 

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, newPassword);
		pstm.setString(2, ssn);
		pstm.executeUpdate();
	}

	public static void updateFirstName(Connection conn, String ssn, String newFirstName) throws SQLException
	{
		String sql = "update accounts set firstname = ? where ssn = ?"; 

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, newFirstName);
		pstm.setString(2, ssn);
		pstm.executeUpdate();
	}

	public static void updateMiddleInitial(Connection conn, String ssn, String newMI) throws SQLException
	{
		String sql = "update accounts set middleinitial = ? where ssn = ?"; 

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, newMI);
		pstm.setString(2, ssn);
		pstm.executeUpdate();
	}

	public static void updateLastname(Connection conn, String ssn, String newLastName) throws SQLException
	{
		String sql = "update accounts set lastname = ? where ssn = ?"; 

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, newLastName);
		pstm.setString(2, ssn);
		pstm.executeUpdate();
	}

}