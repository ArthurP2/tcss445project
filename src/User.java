
public class User {

    private int userID;
	private String name;
	private String username;
	private String password;
	private String email;
	private String phoneNum;
	private boolean isBanned;
	private boolean isSeller;

    public User(int userID, String name, String username, String password, String email,
                String phoneNum, boolean isBanned, boolean isSeller) {
        this.userID = userID;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNum = phoneNum;
        this.isBanned = isBanned;
        this.isSeller = isSeller;
    }

// GETTERS AND SETTERS
    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public boolean isSeller() {
        return isSeller;
    }

    public void setSeller(boolean seller) {
        isSeller = seller;
    }
}
