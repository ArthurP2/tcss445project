///**
// * This is the staff class used to define people that
// * are staff members of Action Central. The unique feature
// * that staff members can do is view all of the Storefronts from
// * the database.
// *
// * @author Andrew Dinh
// * @version 11/7/2016
// */
//public class Staff extends User implements java.io.Serializable
//{
//
//	/**
//	 * Generated serial ID
//	 */
//
//	private static final long serialVersionUID = -5499586537579673988L;
//
//    /**
//     * Constructor for objects of class Staff.
//     * @param theName Name of the staff member.
//     * @param theUsername The username that the staff member uses to log in.
//     * @param thePassword The password that the staff member uses to log in.
//     * @param theEmail the email of the staff member.
//     * @param thePhoneNumber The phone number to contact the staff person with.
//     */
//
//    public Staff(String theName, String theUsername, String thePassword, String theEmail, String thePhoneNumber)
//    {
//        myName = theName;
//        myUsername = theUsername;
//        myPassword = thePassword;
//        myEmail = theEmail;
//        myPhoneNumber = thePhoneNumber;
//    }
//
//    /**
//     * Views all upcoming Storefronts in the next month.
//     * @Return Returns a calendar of all of the Storefronts to be displayed.
//     */
//
//    public StorefrontCalendar viewStorefronts() {
//	StorefrontCalendar calender = new StorefrontCalendar(new StorefrontDate(), "Storefronts.ser");
//    	return calender;
//    }
//}
