package ua.dp.zaychenko.betmanager.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

	private final static String createTableQuery = "CREATE TABLE `matches` ("
			+ "  `id` int(11) NOT NULL auto_increment,"
			+ "  `firstTeam` varchar(20) default NULL,"
			+ "  `secondTeam` varchar(20) default NULL,"
			+ "  `forecast` varchar(5) default NULL,"
			+ "  `koef` float default NULL,"
			+ "  `chance` float default NULL,"
			+ "  `sumBet` float default NULL,"
			+ "  `firstTeamGoals` int(1) default NULL,"
			+ "  `secondTeamGoals` int(1) default NULL,"
			+ "  `result` double default NULL,"
			+ "  `date` varchar(15) default NULL,"
			+ "  `bullits` BOOL," + "  PRIMARY KEY  (`id`)"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

	public static void main(String args[]) {
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			  String url = "jdbc:mysql://localhost/nhlbet" +
						                    "?autoReconnect=true&useUnicode=true&characterEncoding=utf8";
			connection = DriverManager.getConnection(url, "root", "kotenok777");
			statement = connection.createStatement();
			statement.executeUpdate(createTableQuery);
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

}
