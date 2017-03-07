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
	private List<User> BuyerList;
	private List<User> SellerList;


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
	 * Returns a list of user objects from the database.
	 * @return list of movies
	 * @throws SQLException
	 */
	public List<User> getBuyers() throws SQLException {
		if (conn == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select userID, name, username, password, email, phoneNumber," +
				" isBanned, isSeller from apanlili.user where isBanned = 'false' " +
				"and isSeller = 'false' ";

		BuyerList = new ArrayList<User>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("userID");
				String name = rs.getString("name");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String phone = rs.getString("phoneNumber");
				boolean banned = rs.getBoolean("isBanned");
				boolean seller = rs.getBoolean("isSeller");

				User movie = new User(id, name, username, password, email, phone,
										banned, seller);
				BuyerList.add(movie);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return BuyerList;
	}

	/**
	 * Returns a list of user objects from the database.
	 * @return list of movies
	 * @throws SQLException
	 */
	public List<User> getSellers() throws SQLException {
		if (conn == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select userID, name, username, password, email, phoneNumber," +
				" isBanned, isSeller from apanlili.user where isBanned = 'false' " +
				"and isSeller = 'false' ";

		SellerList = new ArrayList<User>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("userID");
				String name = rs.getString("name");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String phone = rs.getString("phoneNumber");
				boolean banned = rs.getBoolean("isBanned");
				boolean seller = rs.getBoolean("isSeller");

				User movie = new User(id, name, username, password, email, phone,
						banned, seller);
				SellerList.add(movie);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return SellerList;
	}
}
