package ua.dp.zaychenko.betmanager.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ApiBetManager {

	public static float calculate(float koef, float chance, float bank){
		return ((koef * chance/100 - 1)/(koef - 1)) * bank;
	}
	
	
	public static void saveMatch(String s){
	Connection connection = null;
	Statement statement = null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		  String url = "jdbc:mysql://localhost/nhlbet" +
					                    "?autoReconnect=true&useUnicode=true&characterEncoding=utf8";
		connection = DriverManager.getConnection(url, "root", "kotenok777");
		statement = connection.createStatement();
		System.out.println("!!!");
		statement.executeUpdate(s);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	}
	
	public static String getCurrentDate(){
		
		Date dtn = new Date();
	    SimpleDateFormat formatter1 = new SimpleDateFormat(
	        "dd.MM.yyyy ");
	    String dt1=formatter1.format(dtn);
	    return dt1;
	}
}
