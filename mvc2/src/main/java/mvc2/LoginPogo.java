package mvc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPogo {

	String uname;
	String pass;

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public boolean blLogic() {

		if (uname.equals("kevil") && pass.equals("kevil")) {

			return true;

		}

		else {

			return false;
		}
	}	
	
	public Connection connection(){
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public boolean insertUser() {
		Connection connection = connection();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("insert into emp values(?,?)");
			preparedStatement.setString(1, uname);
			preparedStatement.setString(2, pass);
			preparedStatement.execute();
			System.out.println("inserted successfully");
			connection.close();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	
	
	}
	
	public boolean updateUser() {
		Connection connection = connection();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("update emp set password =? where username =?");
			preparedStatement.setString(1, pass);
			preparedStatement.setString(2, uname);
			preparedStatement.execute();
			System.out.println("updated successfully");
			connection.close();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	
	}
	
	public boolean deleteUser() {
		Connection connection = connection();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("delete from emp where username =?");
			preparedStatement.setString(1, uname);
			preparedStatement.execute();
			System.out.println("deleted successfully");
			connection.close();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	
	}
	
	public boolean showAllUsers() {
		
		Connection connection = connection();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("select * from emp");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				
				System.out.println("Username"+uname);
				System.out.println("Password"+pass);
				return true;
				
			}
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
		
		
		
	}

}
