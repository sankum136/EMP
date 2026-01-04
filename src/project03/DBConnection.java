package project03;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	public static Connection getConnection() throws Exception {

        // Load MySQL Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Create connection
        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/project03",
            "root",
            "2002"
        );

        return con;
		
		

	}

}
