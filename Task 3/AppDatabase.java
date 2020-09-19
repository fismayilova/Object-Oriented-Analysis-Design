package OOAD_Project;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class AppDatabase {
	static String url = "jdbc:mysql://localhost:3306/test?useTimezone=true&serverTimezone=UTC";
	static String usern = "root";
	static String password = "";

	public static Connection connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, usern, password);
			return con;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}