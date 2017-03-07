import java.sql.SQLException;
import java.util.List;

public class Main {
	
	public static void main(String[] args) {

        List<User> BuyerList;


		StoreDB test = new StoreDB();
		try {
			test.createConnection();
			BuyerList = test.getBuyers();
            for (int i = 0; i < BuyerList.size(); i++) {
                System.out.println(BuyerList.get(i).toString());
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		
//		StorefrontDate myDate = new StorefrontDate();
//		StorefrontDate aucDate = new StorefrontDate(2016, 12, 6, 12);
//
//		StorefrontCalendar myCalendar = new StorefrontCalendar(myDate, "Storefronts.ser");
////		Storefront testStorefront = new Storefront(aucDate, "Test Storefront 1", "We're FOR Profit", "Jim Bob",
////										  "We take your money and make a profit", "The less you know, the better");
//		System.out.println(myCalendar.createAndAddStorefront(aucDate, "Storefront in 1 day1", "Testing 1 days", "Jon yoo",
//										  "We take your money and make a profit", "The less you know, the better"));
//
//		Item item1 = new Item("Test Item 1", "Vettel", "This is a test", 1, 10, "Used", "Massive", "Ping-Pong, or racing?");
//		Item item2 = new Item("Test Item 2", "Raikkonen", "Bwoah", 7, 15, "Like New", "Little", "I don't care");
//		Item item3 = new Item("Test Item 3", "Button", "It's great, really", 23, 1, "New", "Huge", "Massive Understeer");
//
//		myCalendar.getStorefront("Testing 1 days").addItem(item1);
//		myCalendar.getStorefront("Testing 1 days").addItem(item2);
//		myCalendar.getStorefront("Testing 1 days").addItem(item3);
//
//
//
//		System.out.println("Storefront Count: " + myCalendar.getStorefronts().size());
//		Login myLogin = new Login("Users.ser");
////
////		Storefront hpStorefront = new Storefront(new StorefrontDate().getStorefrontDateXDaysAway(-4), "hp", "hp",
////				"hp", "hp", "hp");
////		myCalendar.addPastStorefront(hpStorefront);
//		myCalendar.Update("Storefronts.ser");
//		myLogin.loadUserInfo("Users.ser");
//
//
//
//
//		GUI gui = new GUI(myLogin, myCalendar);
//		gui.start();
//		//database.Update("Storefronts.ser", "Users.ser");
//		System.out.println("GoodBye");
//
	}

}
