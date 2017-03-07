//import java.awt.GridLayout;
//import java.util.Calendar;
//
//import javax.swing.BorderFactory;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//
///**
// * The GUI for viewing the upcoming Storefronts in
// * the next month.
// *
// * @author Andrew Dinh
// * @version 11/29/2016
// */
//
//public class StorefrontCalenderGUI {
//
//	private JFrame myFrame;
//
//	private StorefrontCalendar aucCal;
//
//	/**
//	 * Constructor for the Storefront gui.
//	 */
//
//	public StorefrontCalenderGUI() {
//		myFrame = new JFrame("Calender");
//		myFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//		aucCal = new StorefrontCalendar(new StorefrontDate(), "Storefronts.ser");
//	}
//
//	/**
//	 * Starts setting up the layout of the calender.
//	 */
//
//	public void start() {
//		GridLayout gLayout = new GridLayout(6, 7);
//		myFrame.setSize(600, 400);
//		myFrame.setLayout(gLayout);
//
//		CalenderScreen();
//
//		myFrame.setVisible(true);
//	}
//
//	/**
//	 * Adds in the days and Storefronts within those days
//	 * for the upcoming month.
//	 */
//
//	private void CalenderScreen() {
//	    StorefrontDate date = new StorefrontDate();
//	    Calendar cal = Calendar.getInstance();
//	    int[] dates = date.getNextXDays(30);
//	    int d = 0;
//	    String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "July", "Aug", "Sep", "Oct", "Nov", "Dec"};
//	    String[] days = {"Sun", "Mon", "Tues", "Wed", "Thur", "Fri", "Sat"};
//	    String month = months[date.getMonth() - 1];
//	    myFrame.add(new JLabel(month));
//		for (int j = 2; j < 8; j++) {
//			myFrame.add(new JLabel(""));
//		}
//	    for (int i = 0; i < 7; i++)
//	    {
//	        myFrame.add(new JLabel(days[i]));
//	    }
//	    for (int i = 1; i < cal.get(Calendar.DAY_OF_WEEK); i++)
//	    {
//	        myFrame.add(new JPanel());
//	    }
//	    for (int x = 0; x < dates.length; x++)
//	    {
//            JPanel calDate = new JPanel();
//            calDate.setBorder(BorderFactory.createTitledBorder("" + dates[d]));
//            calDate.add(new JLabel(aucCal.getStorefrontsOnDate(new StorefrontDate(date.getYear(), date.getMonth(), dates[d], date.getHour())) + ""));
//            myFrame.add(calDate);
//            d++;
//	    }
//	}
//}
