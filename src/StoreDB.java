import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class StoreDB {
	private static String userName = "apanlili"; //Change to yours
	private static String password = "kollunn~";
	private static String serverName = "cssgate.insttech.washington.edu";
	private static Connection conn;
	
	/**
	 * Creates a sql connection to MySQL using the properties for
	 * userid, password and server information.
	 * @throws SQLException
	 */
	public static void createConnection() throws SQLException {
		Properties connectionProps = new Properties();
		connectionProps.put("user", userName);
		connectionProps.put("password", password);

		conn = DriverManager.getConnection("jdbc:" + "mysql" + "://"
				+ serverName + "/", connectionProps);

		System.out.println("Connected to database");
	}
	
	/**
	 * Returns a list of movie objects from the database.
	 * @return list of movies
	 * @throws SQLException
	 */
	public List<Item> getMovies() throws SQLException {
		if (conn == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select title, year, length, genre, studioName "
				+ "from youruwnetid.Movies ";

		list = new ArrayList<Movie>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String title = rs.getString("title");
				int year = rs.getInt("year");
				int length = rs.getInt("length");
				String genre = rs.getString("genre");
				String studioName = rs.getString("studioName");
				Movie movie = new Movie(title, year, length, genre, studioName);
				list.add(movie);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return list;
	}
}
