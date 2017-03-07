//import java.time.LocalDate;
//import java.time.Month;
//import java.time.temporal.ChronoUnit;
//import java.util.List;
//
///**
// * This is the Seller class that defines what a
// * Seller organization may do. The unique features are
// * the ability to submit an Storefront request and add items to it.
// *
// * @author Andrew Dinh
// * @version 11/11/2016
// */
//public class Seller extends User implements java.io.Serializable
//{
//
//	/**
//	 *
//	 */
//	private static final long serialVersionUID = -5388228290817876502L;
//
//	private String myContactPerson;
//
//	private boolean myActiveStorefront;
//
//	private Storefront currentStorefront;
//
//        //private StorefrontCalendar calendar;
//
//	/**
//     * Constructor for objects of class Seller.
//     * @param theName Name of the Seller organization (NPO).
//     * @param theUsername The username that the NPO uses to log in.
//     * @param thePassword The password that the NPO uses to log in.
//     * @param theEmail the email of the NPO.
//     * @param thePhoneNumber The phone number to contact the NPO with.
//     */
//
//	public Seller(String theName, String theUsername, String thePassword, String theEmail, String thePhoneNumber /*StorefrontCalendar theCalendar*/)
//	{
//	//calendar = theCalendar;//new StorefrontCalendar(new StorefrontDate());
//            myName = theName;
//        myUsername = theUsername;
//        myPassword = thePassword;
//        myEmail = theEmail;
//        myPhoneNumber = thePhoneNumber;
//		myContactPerson = "";
//		myActiveStorefront = false;
//		currentStorefront = null;
//	}
//
//	/**
//	 * Submits a new Storefront for the NPO.
//	 * @return 0 if successfully added.
//   	 * @return 1 if the Storefront was not added due to the max number of Storefronts already exist
//   	* @return 2 if the Storefront already exists
//   	* @return 3 if the Seller Already has an Storefront
//   	* @return 4 if the Seller has had an Storefront Within the last year
//   	* @return 5 if the Date of the Storefront is less than one week away from the day of submition
//   	* @return 6 if the date of the Storefront is farther than one month out from the day of submition
//   	* @return 7 if there is already 2 Storefronts happening on that date
//	 */
//	public int submitStorefrontRequest(StorefrontDate theDate, String theStorefrontName, String theOrgName,
//    		String theContactPerson, String theDescription, String theComment, StorefrontCalendar calendar)
//	{
//		StorefrontDate today = new StorefrontDate();
//		Storefront activeStorefront;
//                activeStorefront = calendar.getStorefront(myUsername);
//
//		if(activeStorefront != null ) return 3;
//
//		Storefront temp = calendar.getPastStorefront(myUsername);
//		if( temp != null){
//			if(today.isWithinYear(temp.getDate())) return 4;
//		}
//
////		LocalDate oneWeek = today.toLocalDate().plusDays(7);
////		long daysBetween = ChronoUnit.DAYS.between(oneWeek, theDate.toLocalDate());
//		long daysBetween = ChronoUnit.DAYS.between(today.toLocalDate(), theDate.toLocalDate());
//		if(daysBetween < 7) return 5;
//
////		LocalDate oneMonth = today.toLocalDate().plusDays(31);
////		daysBetween = ChronoUnit.DAYS.between(oneWeek, theDate.toLocalDate());
//		daysBetween = ChronoUnit.DAYS.between(today.toLocalDate(), theDate.toLocalDate());
//		if(daysBetween > 31) return 6;
//
//		List<Storefront> allStorefronts = calendar.getStorefronts();
//		LocalDate buffer;
//		boolean oneStorefront = false;
//		for(int i = 0; i < allStorefronts.size(); i++){
//			buffer = allStorefronts.get(i).getDate().toLocalDate();
//			if(buffer.equals(theDate.toLocalDate())){
//				if(oneStorefront == true) return 7;
//				else oneStorefront = true;
//			}
//		}
////		if(calendar.getStorefrontsOnDate(theDate) == 2) return 7;
//		Storefront StorefrontTemp = new Storefront(theDate, theStorefrontName, myUsername, theContactPerson, theDescription, theComment);
//                //System.out.println(myUsername);
//		currentStorefront = StorefrontTemp;
//		this.myActiveStorefront = true;
//		return calendar.addStorefront(StorefrontTemp);
//// 		return calendar.createAndAddStorefront(theDate, theStorefrontName, this.myUsername, theContactPerson, theDescription, theComment);
//
//
//	}
//
//	/**
//	 * Adds an item into the Storefront that the NPO is hosting.
//	 *
//	 */
//
//	public boolean addItem(String theName, String theDonorName, String theDescription,
//			int theQuantity, float theStartingBid, String theCondition,
//			String theSize, String theComments, StorefrontCalendar calendar)
//	{
//		boolean result = false;
//		Item item = new Item(theName, theDonorName, theDescription, theQuantity, theStartingBid,
//				theCondition, theSize, theComments);
//		if (calendar.getStorefront(myUsername)!=null && calendar.getStorefront(myUsername).addItem(item)) {
//			result = true;
//		}
//		return result;
//	}
//
//
//	/**
//	 * Sets the name of the contact person.
//	 *
//	 * @param theName The name being passed in.
//	 */
//
//	public void setContactPerson(String theName) {
//		myContactPerson = theName;
//	}
//
//	/**
//	 * Gets the name of the contact person for the NPO
//	 * @return Returns the name of the contact person.
//	 */
//
//	public String getContactPerson() {
//		return myContactPerson;
//	}
//
//	/**
//	 * Checks to see if the NPO has an upcoming Storefront.
//	 * @return Returns if the NPO has an upcoming Storefront.
//	 */
//
//	public boolean hasCurrentStorefront() {
//		return myActiveStorefront;
//	}
//
//	public Storefront getStorefront(){
//		return currentStorefront;
//	}
//
//	/**
//	 * FOR TESTING PURPOSES ONLY
//	 * PLEASE DO NOT USE
//	 */
//	public StorefrontCalendar getCalendar(){
//		//return calendar;
//            return null;
//	}
//
//	/**
//	 * FOR TESTING PURPOSES ONLY
//	 * PLEASE DO NOT USE
//	 */
//	public void setCalendar(StorefrontCalendar theCalendar){
//		//calendar = theCalendar;
//	}
//
//}
