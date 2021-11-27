package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {

    private static Connection con = null;
    
    public static Connection getConnection()
    {
    	if(con == null) {
    		String url = "jdbc:mysql://localhost:3306/catalogues";
            String user = "root";
            String pass = "";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(url, user, pass);
            }
            catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
    	}
        return con;
    }


}
