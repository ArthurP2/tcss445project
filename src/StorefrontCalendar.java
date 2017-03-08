//import java.util.*;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Calendar;
//import java.util.Random;
///**
// * This is a representation of a calendar for upcoming Storefronts.
// *
// * @author Cody Arnold, Katherine Harmon
// * @version 12/4/16
// */
//public class StorefrontCalendar implements java.io.Serializable {
//
//	private static final long serialVersionUID = 1L;
//	private String fileName;
//	private StorefrontDate theDate;
//	private int theStorefrontCount;
//	private Map<String, Storefront> myStorefronts;
//	private Map<String, Storefront> myPastStorefronts;
//	private int maxStorefronts = 24;
//
//	/**
//	 * Constructs and StorefrontCalendar with a starting date.
//	 * @param startingDate the starting date of the calendar.
//	 */
//	public StorefrontCalendar(StorefrontDate startingDate, String thefileName) {
//		fileName = thefileName;
//	    theDate = startingDate;
//	    myStorefronts = new HashMap<>();
//	    myPastStorefronts = new HashMap<>();
////         Load("Storefronts.ser");
//		Load(this.fileName);
//        movePastStorefronts();
//        theStorefrontCount = myStorefronts.size();
//	}
//
//	private void movePastStorefronts(){
//		   Set<String> temp = myStorefronts.keySet();
//		   Iterator<String> itr = temp.iterator();
//		   String tempKey;
//		   Storefront buffer;
//	      while(itr.hasNext()){
//	    	  tempKey = itr.next();
//	    	  buffer = myStorefronts.get(tempKey);
//                  System.out.println(buffer.getOrg());
//	    	  if(buffer.getDate().isSameOrAfterDate(theDate)){
//	    		  myPastStorefronts.put(buffer.getOrg(), buffer);
//	    		  myStorefronts.remove(buffer.getOrg());
//                          System.out.println("removed: " + buffer.getOrg());
//	    	  }
//	      }
//// 		Update("Storefronts.ser");
//	}
//
//	/**
//	    * creates and Adds an Storefront to the calendar.
//	    * @param theStorefrontName is the Storefront being added.
//	    * @return 0 if successfully added.
//	    * @return 1 if the Storefront was not added due to the max number of Storefronts already exist
//	   	* @return 2 if the Storefront already exists
//	   	*
//	    */
//	public int createAndAddStorefront(StorefrontDate theDate, String theStorefrontName, String theOrgName,
//    		String theContactPerson, String theDescription, String theComment){
//
//		Storefront temp = new Storefront(theDate, theStorefrontName, theOrgName,
//    		theContactPerson, theDescription, theComment);
//		return addStorefront(temp);
//
//	}
//
//
//   /**
//    * Adds an Storefront to the calendar.
//    * @param theStorefront is the Storefront being added.
//    * @return 0 if successfully added.
//    * @return 1 if the Storefront was not added due to the max number of Storefronts already exist
//   	* @return 2 if the Storefront already exists
//   	* @return 3 if the Max number of Storefronts is already met
//    */
//   public int addStorefront(Storefront theStorefront) {
//	   if (theStorefrontCount >= maxStorefronts) return 1;
//	   if (myStorefronts.containsKey(theStorefront.getOrg())) return 2;
//	   if (theStorefrontCount >= maxStorefronts) return 3;
//	   myStorefronts.put(theStorefront.getOrg(), theStorefront);
//	   theStorefrontCount++;
//// 	   Update("Storefronts.ser");
//	   return 0;
//   }
//   /**
//    * removes an Storefront from the calendar
//    * @return 0 if the Storefront is successfully removed
//    * @return 1 if the Storefront did not exist
//    * @retrun 2 if the date of deletion is within 2 days of the Storefront date
//    */
//   public int removeStorefront(String theStorefront){
//	   StorefrontDate today = new StorefrontDate();
//	   if(!myStorefronts.containsKey(theStorefront)) return 1;
//	   if(!today.isTwoOrMoreDaysBefore(myStorefronts.get(theStorefront).getDate())) return 2;
//	   myStorefronts.remove(theStorefront);
//	   theStorefrontCount++;
//// 	   Update("Storefronts.ser");
//	   return 0;
//   }
//
//   /**
//    * Returns a collection of all Storefronts in the calendar.
//    * @return a collection of Storefronts.
//    */
//   public List<Storefront> getStorefronts() {
//	   List<Storefront> StorefrontList = new ArrayList<Storefront>();
//	   Set<String> temp = myStorefronts.keySet();
//	   Iterator<String> itr = temp.iterator();
//	   String tempKey;
//	   Storefront buffer;
//      while(itr.hasNext()){
//    	  tempKey = itr.next();
//    	  buffer = myStorefronts.get(tempKey);
////    	  if(buffer.getDate().isSameOrAfterDate(theDate)){
//	  if(theDate.isSameOrAfterDate(buffer.getDate())){
//    		  StorefrontList.add(buffer);
//    	  }
//      }
//
//      return StorefrontList;
//    }
//
//   /**gets the current Storefront belonging to the param name
//    * @returns the Storefront belonging to OrgName
//    */
//   public Storefront getStorefront(String OrgName){
//       //System.out.println("Ding!");
//       if (myStorefronts.containsKey(OrgName))
//       {
//           //System.out.println(OrgName);
//           return myStorefronts.get(OrgName);
//       }
//       else
//           return null;
//   }
//
//   /**
//	 * @return true if the New Maximum was set
//	 * @return false if the newMax is less than 1
//	 */
//	public boolean setMaxStorefronts(int theNewMax){
//		if (theNewMax<1) return false;
//		maxStorefronts = theNewMax;
//// 		Update("Storefronts.ser");
//		return true;
//	}
//
//	public int getMaxStorefronts(){
//		return maxStorefronts;
//	}
//
//
//   /**
//    * This function will package up the user and Storefront hashes into .ser
//    * files. Call this whenever you want to save all changes. Will return
//    * a boolean based on if the update worked (true) or if there was a
//    * problem (false).
//    *
//    * @return A true/false value based on if the files saved correctly
//    */
//   public boolean Update(String StorefrontFileName)
//   {
//       boolean didItWork;
//       didItWork = (dumpStorefrontsToFile(StorefrontFileName));
//       return didItWork;
//   }
//
//   public boolean Load(String StorefrontFileName)
//   {
//       boolean didItWork;
//       didItWork = (loadStorefrontsFromFile(StorefrontFileName));
//       return didItWork;
//   }
//
//   private boolean dumpStorefrontsToFile(String StorefrontFileName)
//   {
//       boolean didItWork = false;
//       try
//     {
//        FileOutputStream fileOut = new FileOutputStream(StorefrontFileName);
//        ObjectOutputStream out = new ObjectOutputStream(fileOut);
//        out.writeObject(myStorefronts);
//        out.writeObject(myPastStorefronts);
//        out.writeInt(maxStorefronts);
//        out.close();
//        fileOut.close();
//        didItWork = true;
//        //System.out.printf("Serialized data is saved in /tmp/employee.ser");
//     }
//       catch(IOException i)
//     {
//         //return false;
//     }
//       return didItWork;
//   }
//
//   private boolean loadStorefrontsFromFile(String StorefrontFileName)
//   {
//       try
//     {
//        FileInputStream fileIn = new FileInputStream(StorefrontFileName);
//        ObjectInputStream in = new ObjectInputStream(fileIn);
//        myStorefronts = (Map<String, Storefront>) in.readObject();
//        myPastStorefronts = (Map<String, Storefront>) in.readObject();
//        maxStorefronts = in.readInt();
//        in.close();
//        fileIn.close();
//     }catch(IOException i)
//     {
//        //i.printStackTrace();
//        return false;
//         //Update(StorefrontFileName);
//     }catch(ClassNotFoundException c)
//     {
//        System.out.println("Storefront class not found");
//        c.printStackTrace();
//        return false;
//     }
//       return true;
//   }
//
//   public int getStorefrontsOnDate(StorefrontDate date) {
//	   int count = 0;
//	   Set<String> temp = myStorefronts.keySet();
//	   Iterator<String> itr = temp.iterator();
//	   String tempKey;
//	   Storefront buffer;
//      while(itr.hasNext()){
//    	  tempKey = itr.next();
//    	  buffer = myStorefronts.get(tempKey);
//    	  if(buffer.getDate().isSameDay(date)){
//    		  count++;
//    	  }
//      }
//
//      return count;
//   }
//
//    Storefront getPastStorefront(String myUsername) {
//        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        return myPastStorefronts.get(myUsername);
//    }
//}