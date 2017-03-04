import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * The GUI for users that are represented as Buyers in
 * the system.
 * 
 * @author Kyle Phan
 * @version 12/5/2016
 */
public class BuyerGUI {
	
	final static int IDWIDTH = 20;
	final static int NAMEWIDTH = 100;
	final static int CONDITIONWIDTH = 30;
	final static int MINBIDWIDTH = 30;
	final static int MYBIDWIDTH = 30;
	final static int COLUMNNUMBERS = 5;
	
	final static String THEFILENAME = "Storefronts.ser";
	final static String USERFILE = "Users.ser";
	
	final static String INPUTPANEL = "Login Page";
	final static String BuyerCARD = "Buyer Card";
	final static String BuyerPANEL = "Buyer Welcome Page";
	final static String BuyerVIEWAUCS = "Buyer Storefronts Page";
	final static String BuyerVIEWITEMS = "Buyer Items Page";	
	final static String BuyerVIEWITEMDETAILS = "Buyer Item Details Page";
	
	final static String[] COLUMNNAMES = {"ID #",
            							 "Item Name",
            							 "Condition",
            							 "Min. Bid",
            							 "My Bids"};
	private Object[][] myTableData;
	private JTable myItemTable;
	
	private boolean within2Days = false;
	
	private Login myLogin;
	private Buyer myBuyer;
	private StorefrontCalendar myCalendar;
	
	private JPanel myLocalContainer;
	private CardLayout myLocalCLayout;
	
	private JPanel myMainContainer;
	private CardLayout myMainCLayout;
	
	private JPanel myMainScreen;
	private JPanel myWelcomeScreen;
	private JPanel myViewStorefrontsScreen;
	private JPanel myViewItemsScreen;
	private JPanel myViewItemDetailsScreen;
	
	private JTextField placedBid;
	private JTextArea StorefrontInfo;
	
	private ButtonBuilder myOptionButtons;
	
	private List<Item> itemsFromStorefront;
	private Storefront chosenStorefront;
	private Item chosenItem;
	private JScrollPane scrollPane;
	
	private NumberFormat currencyFormatter;
	private String rawInput;
	private float theBid;
	private float currentBid;
	
	private static final String AUCCENTRALMOTTO = "Storefront Central: The Storefronteer for Non-Profit Organizations";
	private static final String LOGGEDINASBuyer = " logged in as a Buyer";
	private static final String ITEMCURRENTBID = "Your bid: ";
	private static final String ITEMMINBID = "Starting Bid: ";
	private static final String ITEMCONDITION = "\nCondition: ";
	private static final String ITEMQUANTITY = "\nQuantity: ";
	private static final String ITEMDESCRIPTION = "\nDescription: ";
	

	private JTextArea myWelcomeText = new JTextArea(AUCCENTRALMOTTO);
	/**
	 * Constructor for BuyerGUI
	 * @param theUser is the current User of class Buyer
	 * @param theContainer is the JPanel that all of the different GUIs will be added to
	 * @param theCLayout is the CardLayout that is used to switch between different JPanels
	 */
	public BuyerGUI(User theUser, Login theLogin, StorefrontCalendar theCalendar, JPanel theContainer, CardLayout theCLayout) {
		
		myLogin = theLogin;
		myBuyer = (Buyer) theUser;
		myCalendar = theCalendar;
		myMainContainer = theContainer;
        myMainCLayout = theCLayout;
        
        
        System.out.println("Buyer Items: " + myBuyer.viewBids().keySet());
        
        
        currencyFormatter = NumberFormat.getCurrencyInstance();
        
        myLocalContainer = new JPanel();
        myLocalCLayout = new CardLayout();
        myMainScreen = new JPanel();
        myWelcomeScreen = new JPanel();
        placedBid = new JTextField();
        
	}

	
	/**
	 * This method sets up the GUI to run on the master JFrame.
	 */
	public void start() {
		myOptionButtons = new ButtonBuilder(new String[] {"View Storefronts", "View Item List", "View Item Details", "Place a Bid", "Remove Bid", "Logout", });

		BuyerScreenController();
		
		myMainContainer.add(myMainScreen, BuyerCARD);
		myMainCLayout.show(myMainContainer, BuyerCARD);
		
	}

	/**
	 * This method initializes all the buttons as well as starts up and sets
	 * up the initial JPanels.
	 */
	private void BuyerScreenController() {
		myMainScreen.setLayout(new BorderLayout());
		myOptionButtons.buildButtons();

		myMainScreen.add(myOptionButtons, BorderLayout.SOUTH);
		myOptionButtons.getButton(0).setEnabled(false);
		myOptionButtons.getButton(0).addActionListener(new ViewStorefronts());
		myOptionButtons.getButton(1).setVisible(false);
		myOptionButtons.getButton(1).addActionListener(new ViewItemList());		
		myOptionButtons.getButton(2).setEnabled(false);
		myOptionButtons.getButton(2).addActionListener(new SelectedItem());
		myOptionButtons.getButton(3).setVisible(false);
		myOptionButtons.getButton(3).addActionListener(new PlaceBid());
		myOptionButtons.getButton(4).setVisible(false);
		myOptionButtons.getButton(4).addActionListener(new RemoveBid());
		myOptionButtons.getButton(4).setVisible(false);
		myOptionButtons.getButton(5).addActionListener(new LogOut());
		
		BuyerWelcomeScreen();
		
		myLocalContainer.setLayout(myLocalCLayout);
		
		myViewStorefrontsScreen = new JPanel();
		BuyerViewStorefrontsScreen();
		myLocalContainer.add(myViewStorefrontsScreen, BuyerVIEWAUCS);
		myLocalCLayout.show(myLocalContainer, BuyerVIEWAUCS);
		
		myMainScreen.add(myLocalContainer, BorderLayout.CENTER);
		myLocalContainer.setBorder( new EmptyBorder( 20, 20, 20, 20 ) );
	}
	
	/**
	 * This helper method creates the header text.
	 */
	private void BuyerWelcomeScreen() {
		myWelcomeScreen.setLayout(new BorderLayout());
		myWelcomeScreen.add(myWelcomeText, BorderLayout.NORTH);
		myWelcomeText.append("\n" + myBuyer.getName() + LOGGEDINASBuyer);
		myMainScreen.add(myWelcomeScreen, BorderLayout.NORTH);
	}
	
	/**
	 * This method initializes the Storefront List screen that displays
	 * all of the current Storefronts in the system.
	 */
	private void BuyerViewStorefrontsScreen() {
		for (Storefront auc : myCalendar.getStorefronts()) {
			if (auc.getItems().size() > 0) {
				JButton button = new JButton(auc.getStorefrontName());
				
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						myViewItemsScreen = new JPanel();
						setStorefront(auc);
						BuyerViewItemsScreen();
						setStorefrontInfo(auc);
						myOptionButtons.getButton(2).setEnabled(true);
						myOptionButtons.getButton(0).setEnabled(true);
						if (within2Days) {
							JOptionPane.showMessageDialog(myMainScreen,
					                chosenStorefront.getStorefrontName() + " is less than 2 days away!\n"
					                + "All bids become final within 2 days of the Storefront.",
					                "Storefront Ending Soon!",
					                JOptionPane.INFORMATION_MESSAGE);
							}
					}
				});
				
				myViewStorefrontsScreen.add(button);
			}
		}
	}
	
	/**
	 * This method creates a JTable that lists all of the items in the Storefront.
	 * Doesn't sort the items by name, but by order of which they were added.
	 * 
	 * JTables work as a 2D array, 
	 * 
	 *
	 */
	private void BuyerViewItemsScreen() {		
		myTableData = new Object[itemsFromStorefront.size()][COLUMNNUMBERS];
		int itemID = 1;

		for (Item i : itemsFromStorefront) {
			for (int j = 0; j < COLUMNNUMBERS; j++) {
				if (j == 0) myTableData[itemID-1][j] = itemID;
				if (j == 1) myTableData[itemID-1][j] = i.getName();
				if (j == 2) myTableData[itemID-1][j] = i.getCondition();
				if (j == 3) myTableData[itemID-1][j] = currencyFormatter.format(i.getStartingBid());
				if (j == 4) {
					boolean sameName = false;
					float bidFromBuyer = 0;
					for (Item k : myBuyer.viewBids().keySet()) {
//						System.out.println(i.getBid(myBuyer.getName()));
//						System.out.println(myBuyer.viewBids());
//						System.out.println(k.getName());
						if (k.getName().equals(i.getName())) {
//							System.out.println("Inserting into bids column");
							sameName = true;
							bidFromBuyer = myBuyer.viewBids().get(k);
							break;
//							myTableData[itemID-1][j] = currencyFormatter.format(myBuyer.viewBids().get(k));
						} 
//						else {
//							System.out.println("Clearing bids column");
//							myTableData[itemID-1][j] = "";
//						}
					}
					if (sameName && i.getBid(myBuyer.getName()) == bidFromBuyer) {
						myTableData[itemID-1][j] = currencyFormatter.format(bidFromBuyer);
					} else {
						myTableData[itemID-1][j] = "";
					}
					
//					for (Item k : myBuyer.viewBids().keySet()) {
//						if (k.getName().equals(i.getName())) {
//							myTableData[itemID-1][j] = currencyFormatter.format(myBuyer.viewBids().get(k));
//						} else {
//							myTableData[itemID-1][j] = "";
//						}
//					}
				}
			}
			itemID++;
		}
		
		/*
		 * If the Storefront date is within 2 days, set Remove Bid button to disabled -- Business Rule
		 */
		if (!chosenStorefront.getDate().isTwoOrMoreDaysBeforeBid(new StorefrontDate())) {
			myOptionButtons.getButton(4).setEnabled(false);
			within2Days = true;
		}
		
		myItemTable = new JTable(myTableData, COLUMNNAMES);		
		scrollPane = new JScrollPane(myItemTable);
		
		JLabel selectAnItem = new JLabel("Please select an item from the list below.");
		
		myViewItemsScreen.setLayout(new BorderLayout());
		myViewItemsScreen.add(selectAnItem, BorderLayout.NORTH);
		myViewItemsScreen.add(scrollPane, BorderLayout.CENTER);
		
		myLocalContainer.add(myViewItemsScreen, BuyerVIEWITEMS);
		myLocalCLayout.show(myLocalContainer, BuyerVIEWITEMS);
	}
	
	/**
	 * Theoretically, this method should create/overwrite the previous instance of myViewItemDetailsScreen,
	 * Should create a new JPanel with all the new information and replace the previous one, seems like it's not.
	 * 
	 * 
	 * @param theItem is the item being viewed
	 * @param theStorefront the Storefront that the item came from
	 */
	private void BuyerViewItemDetailsScreen(Item theItem, Storefront theStorefront) {
		myViewItemDetailsScreen = new JPanel(new BorderLayout());
		JTextArea itemName = new JTextArea(theItem.getName());
		Font titleFont = new Font(itemName.getFont().getFontName(), Font.BOLD, itemName.getFont().getSize()+6);
		itemName.setFont(titleFont);
		itemName.setBorder( new EmptyBorder(15, 15, 20, 15));
		JTextArea itemDetails = new JTextArea();
		itemDetails.setBorder( new EmptyBorder(0, 15, 0, 15));
		
		/*
		 * If the Buyer has a bid, print it in the description
		 */
		if (theItem.getBid(myBuyer.getUserName()) != -1) {
			currentBid = theItem.getBid(myBuyer.getUserName());
			itemDetails.append(ITEMCURRENTBID + currencyFormatter.format(theItem.getBid(myBuyer.getUserName())) + "\n");
			myOptionButtons.getButton(3).setEnabled(false);
			myOptionButtons.getButton(4).setEnabled(true);
		} else if (theItem.getBid(myBuyer.getUserName()) == -1) {
			myOptionButtons.getButton(3).setEnabled(true);
			myOptionButtons.getButton(4).setEnabled(false);
		}
		
		/*
		 * Print the item's information.
		 */
		itemDetails.append(ITEMMINBID + currencyFormatter.format(theItem.getStartingBid()));
		itemDetails.append(ITEMCONDITION + theItem.getCondition());
		itemDetails.append(ITEMQUANTITY + theItem.getQuantity());
		itemDetails.append(ITEMDESCRIPTION + theItem.getDescription());
		
		JPanel enterBidPane = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		JLabel placeBidText = new JLabel("Enter a bid: ");
		
		c.gridx = 0;
		c.gridy = 0;
		
		enterBidPane.add(placeBidText, c);
		
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 0;
		enterBidPane.add(placedBid, c);
		
		myOptionButtons.getButton(3).setVisible(true);
		myOptionButtons.getButton(4).setVisible(true);
		myViewItemDetailsScreen.add(enterBidPane, BorderLayout.SOUTH);
		myViewItemDetailsScreen.add(itemName, BorderLayout.NORTH);
		myViewItemDetailsScreen.add(itemDetails, BorderLayout.CENTER);
		
		setItem(theItem);
		
//		/*
//		 * If the Storefront date is within 2 days, set Remove Bid button to disabled -- Business Rule
//		 */
//		if (!theStorefront.getDate().isTwoOrMoreDaysBefore(new StorefrontDate())) {
//			myOptionButtons.getButton(4).setEnabled(false);
//			within2Days = true;
//		}
		
		myLocalContainer.add(myViewItemDetailsScreen, BuyerVIEWITEMDETAILS);
		myLocalCLayout.show(myLocalContainer, BuyerVIEWITEMDETAILS);
	}
	
	/**
	 * This method clears the JTextField that is used to enter bids.
	 */
	private void clearTextField() {
		placedBid.setText("");
	}
	
	/**
	 * This method sets the chosenStorefront variable to whatever Storefront
	 * is selected in the Storefront List JPanel.
	 * @param theStorefront is the Storefront selected via the Storefront List JPanel
	 */
	private void setStorefront(Storefront theStorefront) {
		chosenStorefront = theStorefront;
		itemsFromStorefront = theStorefront.getItems();
	}
	
	/**
	 * This method sets the chosenItem variable to whatever Item is selected
	 * in the View Items List after selecting an Storefront.
	 * @param theItem is the Item that the user wishes to bid on.
	 */
	private void setItem(Item theItem) {
		chosenItem = theItem;
	}
	
	/**
	 * This method helps set the header info with the name of the Storefront
	 * as well as the month, day, year and time that it will occur.
	 * @param theStorefront is the Storefront selected by the User in the Storefront List JPanel
	 */
	private void setStorefrontInfo(Storefront theStorefront) {
		StorefrontInfo = new JTextArea();
		StorefrontInfo.setBorder( new EmptyBorder(15, 0, 0, 0));
		StorefrontInfo.append(theStorefront.getStorefrontName() + ", ");
		if (theStorefront.getDate().getMonth() == 1) StorefrontInfo.append("January");
		else if (theStorefront.getDate().getMonth() == 2) StorefrontInfo.append("February");
		else if (theStorefront.getDate().getMonth() == 2) StorefrontInfo.append("March");
		else if (theStorefront.getDate().getMonth() == 2) StorefrontInfo.append("April");
		else if (theStorefront.getDate().getMonth() == 2) StorefrontInfo.append("May");
		else if (theStorefront.getDate().getMonth() == 2) StorefrontInfo.append("June");
		else if (theStorefront.getDate().getMonth() == 2) StorefrontInfo.append("July");
		else if (theStorefront.getDate().getMonth() == 2) StorefrontInfo.append("August");
		else if (theStorefront.getDate().getMonth() == 2) StorefrontInfo.append("September");
		else if (theStorefront.getDate().getMonth() == 2) StorefrontInfo.append("October");
		else if (theStorefront.getDate().getMonth() == 2) StorefrontInfo.append("November");
		else StorefrontInfo.append("December");
		StorefrontInfo.append(" " + theStorefront.getDate().getDay() + ", " + theStorefront.getDate().getYear() +
						   ", " + theStorefront.getDate().getHour());
		if (theStorefront.getDate().getHour() > 0 && theStorefront.getDate().getHour() < 12) {
			StorefrontInfo.append("AM");
		} else {
			StorefrontInfo.append("PM");
		}
		myWelcomeScreen.add(StorefrontInfo, BorderLayout.SOUTH);
	}
	
	/**
	 * This is a helper method that redraws the JTable after information has been updated.
	 */
	private void redrawTable() {
		myViewItemsScreen.remove(scrollPane);
		myTableData = new Object[itemsFromStorefront.size()][COLUMNNUMBERS];
		int itemID = 1;

		for (Item i : itemsFromStorefront) {
			for (int j = 0; j < COLUMNNUMBERS; j++) {
				if (j == 0) myTableData[itemID-1][j] = itemID;
				if (j == 1) myTableData[itemID-1][j] = i.getName();
				if (j == 2) myTableData[itemID-1][j] = i.getCondition();
				if (j == 3) myTableData[itemID-1][j] = currencyFormatter.format(i.getStartingBid());
				if (j == 4) {
					boolean sameName = false;
					float bidFromBuyer = 0;
					for (Item k : myBuyer.viewBids().keySet()) {
//						System.out.println(i.getBid(myBuyer.getName()));
//						System.out.println(myBuyer.viewBids());
//						System.out.println(k.getName());
						if (k.getName().equals(i.getName())) {
//							System.out.println("Inserting into bids column");
							sameName = true;
							bidFromBuyer = myBuyer.viewBids().get(k);
							break;
//							myTableData[itemID-1][j] = currencyFormatter.format(myBuyer.viewBids().get(k));
						} 
//						else {
//							System.out.println("Clearing bids column");
//							myTableData[itemID-1][j] = "";
//						}
					}
					if (sameName && i.getBid(myBuyer.getName()) == bidFromBuyer) {
						myTableData[itemID-1][j] = currencyFormatter.format(bidFromBuyer);
					} else {
						myTableData[itemID-1][j] = "";
					}
					
//					System.out.println("________________________");
				}
			}
			itemID++;
		}
		
		myItemTable = new JTable(myTableData, COLUMNNAMES);
		scrollPane = new JScrollPane(myItemTable);
		myViewItemsScreen.add(scrollPane, BorderLayout.CENTER);
		myViewItemsScreen.repaint();
		
	}
	
	/**
	 * This ActionListener class is used to return to the list of Storefronts
	 * for the User to choose from.
	 * @author Kyle Phan
	 *
	 */
	class ViewStorefronts implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for (Component c : myLocalContainer.getComponents()) {
				if (c.equals(BuyerVIEWAUCS)) {
					System.out.println("Removed View Auc Screen");
					myLocalContainer.remove(myViewStorefrontsScreen);
				}
			}
			System.out.println(myLocalContainer.getComponents());
			within2Days = false;
			myViewStorefrontsScreen = new JPanel();
			BuyerViewStorefrontsScreen();
			myLocalContainer.add(myViewStorefrontsScreen, BuyerVIEWAUCS);
			clearTextField();
			myWelcomeScreen.remove(StorefrontInfo);
			myOptionButtons.getButton(0).setEnabled(false);
			myOptionButtons.getButton(1).setVisible(false);
			myOptionButtons.getButton(2).setEnabled(false);
			myOptionButtons.getButton(2).setVisible(true);
			myOptionButtons.getButton(3).setVisible(false);
			myOptionButtons.getButton(4).setVisible(false);
			myLocalCLayout.show(myLocalContainer, BuyerVIEWAUCS);
		}
	}
	
	/**
	 * This ActionListener class is used to return to the list of Items
	 * available in the chosen Storefront.
	 * @author Kyle Phan
	 *
	 */
	class ViewItemList implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			redrawTable();
			myViewItemsScreen.repaint();
			myLocalCLayout.show(myLocalContainer, BuyerVIEWITEMS);
			myOptionButtons.getButton(1).setVisible(false);
			myOptionButtons.getButton(2).setEnabled(true);
			myOptionButtons.getButton(3).setVisible(false);
			myOptionButtons.getButton(4).setVisible(false);
		}
	}
	
	/**
	 * This ActionListener class is used to place a bid on a selected item.
	 * Gets the input entered and checks if it is valid, if not creates JOptionPane 
	 * the issue.
	 * If valid bid, places bid on item and creates JOptionPane confirming, then
	 * updates View Item List JPanel.
	 * @author Kyle Phan
	 *
	 */
	class PlaceBid implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			rawInput = placedBid.getText();
			if (rawInput != null && !rawInput.isEmpty()) {
				theBid = Float.parseFloat(rawInput);
			}
			
			if (theBid < 0) {
				System.out.println("Bid is below 0");
				JOptionPane.showMessageDialog(myMainScreen,
		                "Please enter a valid bid:\nYour bid was a negative number!",
		                "Bid Error!",
		                JOptionPane.ERROR_MESSAGE);
			} else if (theBid == 0) {
				System.out.println("Bid is 0");
				JOptionPane.showMessageDialog(myMainScreen,
		                "Please enter a valid bid:\nYour bid was zero!",
		                "Bid Error!",
		                JOptionPane.ERROR_MESSAGE);
			} else if (theBid < chosenItem.getStartingBid()) {
				System.out.println("Bid is less than starting bid");
				JOptionPane.showMessageDialog(myMainScreen,
		                "Please enter a valid bid:\nYour bid was less than the minimum bid!",
		                "Bid Error!",
		                JOptionPane.ERROR_MESSAGE);
			} else {
				if (myBuyer.placeBid(chosenItem, theBid)) {
					System.out.println("Bid accepted");
					myItemTable.validate();
					myLogin.writeUserInfo(USERFILE);
					myCalendar.Update(THEFILENAME);
					clearTextField();
//					myLocalContainer.remove(myViewStorefrontsScreen);
//					myLocalContainer.remove(myViewItemsScreen);
//					myLocalContainer.remove(myViewItemDetailsScreen);
					redrawTable();
					myViewItemsScreen.repaint();
					myOptionButtons.getButton(1).setVisible(false);
					myOptionButtons.getButton(2).setEnabled(true);
					myOptionButtons.getButton(3).setVisible(false);
					myOptionButtons.getButton(4).setVisible(false);
					myLocalCLayout.show(myLocalContainer, BuyerVIEWITEMS);
					JOptionPane.showMessageDialog(myMainScreen,
			                "Your bid of " + currencyFormatter.format(theBid) + " has been placed on " +
			                chosenItem.getName(),
			                "Congratulations!",
			                JOptionPane.INFORMATION_MESSAGE);
				}  else {
					System.out.println("Bid already exists");
					JOptionPane.showMessageDialog(myMainScreen,
			                "You have already placed a bid on this item!",
			                "Bid Error!",
			                JOptionPane.ERROR_MESSAGE);
				}
			}
			
		}
	}
	
	/**
	 * This ActionListener class is used to remove a bid on a selected item.
	 * Creates a JOptionPane confirming that the bid has been removed then
	 * updates View Item List JPanel.
	 * @author Kyle Phan
	 *
	 */
	class RemoveBid implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Bid removed...?");
			
			System.out.println("Was bid removed? " + myBuyer.removeBid(chosenItem));
//			theItem.deleteBid(myBuyer.getUserName());
			myItemTable.validate();
			myLogin.writeUserInfo(USERFILE);
			myCalendar.Update(THEFILENAME);
			clearTextField();
//			myLocalContainer.remove(myViewItemDetailsScreen);
			redrawTable();
			myViewItemsScreen.repaint();
			myOptionButtons.getButton(1).setVisible(false);
			myOptionButtons.getButton(2).setEnabled(true);
			myOptionButtons.getButton(3).setVisible(false);
			myOptionButtons.getButton(4).setVisible(false);
			myLocalCLayout.show(myLocalContainer, BuyerVIEWITEMS);
			JOptionPane.showMessageDialog(myMainScreen,
	                "Your bid of " + currencyFormatter.format(currentBid) + " has been removed from " +
	                chosenItem.getName(),
	                "Bid Removed!",
	                JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/**
	 * This ActionListener class is used to get the Item selected from the
	 * View Items List Jtable and create a JPanel that will display the Item's
	 * information.
	 * @author Kyle Phan
	 *
	 */
	class SelectedItem implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (myItemTable.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(myMainScreen,
		                "Please select an item to view",
		                "No Item Selected",
		                JOptionPane.ERROR_MESSAGE);
			} else {
				BuyerViewItemDetailsScreen(itemsFromStorefront.get(myItemTable.getSelectedRow()), chosenStorefront);
				myOptionButtons.getButton(1).setVisible(true);
				myOptionButtons.getButton(2).setEnabled(false);
				if (within2Days) {
					myOptionButtons.getButton(4).setEnabled(false);
					JOptionPane.showMessageDialog(myMainScreen,
							chosenStorefront.getStorefrontName() + " is less than 2 days away!\n"
									+ "You may review the item information but cannot remove your bid\n"
									+ "within 2 days of the Storefront.",
									"Storefront Ending Soon!",
									JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
	
	/**
	 * This ActionListener is used to log the current user out and return
	 * back to the Login page.
	 * 
	 * Removes this GUI from the main GUI's CardLayout.
	 * @author Kyle
	 *
	 */
	class LogOut implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			myMainCLayout.show(myMainContainer, INPUTPANEL);
			myMainContainer.remove(myMainScreen);
			myLogin.writeUserInfo(USERFILE);
			myCalendar.Update(THEFILENAME);
		}
	}
}
