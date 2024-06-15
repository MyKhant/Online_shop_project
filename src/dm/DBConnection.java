package dm;
import java.sql.*;

public class DBConnection {
	
	public static Connection getconnection() throws SQLException {
		String url = "jdbc:mysql://localhost/online_shop";
		String username = "root";
		String password = "";
		return DriverManager.getConnection(url, username, password);
	}

}
