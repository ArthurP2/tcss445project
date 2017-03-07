//import java.awt.BorderLayout;
//import java.awt.CardLayout;
//import java.awt.FlowLayout;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JButton;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//
///**
// * The GUI for users that are represented as staff in
// * the system.
// *
// * @author Andrew Dinh
// * @version 12/5/2016
// */
//
//public class StaffGUI {
//
//	final static String STAFFPANEL = "Staff Page";
//
//	private StorefrontCalendar aucCal;
//
//	private Staff myStaff;
//
//	private JPanel myContainer;
//
//	private CardLayout myLayout;
//
//	private JPanel myStaffPanel;
//
//	private JPanel myAdminPanel;
//
//	private JPanel myMainPanel;
//
//	private JPanel myInfoPanel;
//
//	private JTextField numberOfStorefront;
//
//	/**
//	 * Constructor class for the staff gui
//	 * @param theUser The staff user object
//	 * @param cLayout The layout manager for the frame.
//	 * @param containerPanel The container for the frame.
//	 */
//
//	public StaffGUI(User theUser, JPanel containerPanel, CardLayout cLayout) {
//		myStaff = (Staff) theUser;
//		myContainer = containerPanel;
//		myLayout = cLayout;
//		aucCal = myStaff.viewStorefronts();
//	}
//
//	/**
//	 * Starts setting up the layout of the staff gui.
//	 */
//
//	public void start() {
//		staffScreen();
//		myContainer.add(myMainPanel, STAFFPANEL);
//		myLayout.show(myContainer, STAFFPANEL);
//	}
//
//	/**
//	 * The main frame layout for the staff gui.
//	 */
//
//	private void staffScreen() {
//		myMainPanel = new JPanel();
//		myStaffPanel = new JPanel();
//		myInfoPanel = new JPanel();
//		myMainPanel.setLayout(new BorderLayout());
//		myStaffPanel.setLayout(new FlowLayout());
//		JButton viewCalender = new JButton("View Calender");
//		JButton adminTools = new JButton("View Admin Tools");
//		JButton logOut = new JButton("Log Out");
//		viewCalender.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				StorefrontCalenderGUI StorefrontCalender = new StorefrontCalenderGUI();
//				StorefrontCalender.start();
//			}
//
//		});
//		adminTools.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				myMainPanel.setVisible(false);
//				adminGUI();
//			}
//
//		});
//		logOut.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				myLayout.show(myContainer, STAFFPANEL);
//				myContainer.remove(myMainPanel);
//			}
//
//		});
//		myStaffPanel.add(viewCalender);
//		myStaffPanel.add(adminTools);
//		myStaffPanel.add(logOut);
//		myInfoPanel.add(new JLabel("Max Storefronts: " + aucCal.getMaxStorefronts()));
//		myMainPanel.add(myInfoPanel, BorderLayout.CENTER);
//		myMainPanel.add(myStaffPanel, BorderLayout.SOUTH);
//		myContainer.add(myMainPanel);
//		myMainPanel.setVisible(true);
//	}
//
//	/**
//	 * The admin gui when the staff member wants to changes max number of Storefronts.
//	 */
//
//	private void adminGUI() {
//		myAdminPanel = new JPanel();
//		JPanel editPanel = new JPanel();
//		JPanel buttonPanel = new JPanel();
//		JButton changeMaxStorefronts = new JButton("Update Max Storefronts");
//		JButton cancel = new JButton("Cancel Changes");
//		editPanel.setLayout(new GridBagLayout());
//		GridBagConstraints c = new GridBagConstraints();
//		numberOfStorefront = new JTextField("");
//		JLabel maxStorefrontsLabel = new JLabel("Change Max Number of Storefronts: ");
//
//        c.gridwidth = 1;
//        c.gridx = 0;
//        c.gridy = 0;
//        editPanel.add(maxStorefrontsLabel, c);
//
//		c.ipadx = 50;
//        c.gridwidth = 2;
//        c.gridx = 1;
//        c.gridy = 0;
//		editPanel.add(numberOfStorefront, c);
//
//		changeMaxStorefronts.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				String string = "";
//				if (numberOfStorefront.getText() != null) {
//					string = numberOfStorefront.getText();
//				}
//				 try {
//				      int i = Integer.parseInt(string.trim());
//				      boolean result = aucCal.setMaxStorefronts(i);
//				      if (result == true) {
//				    	  aucCal.Update("Storefronts.ser");
//				    	  myAdminPanel.setVisible(false);
//				    	  myInfoPanel.removeAll();
//				    	  myInfoPanel.add(new JLabel("Max Storefronts: " + aucCal.getMaxStorefronts()));
//				    	  myMainPanel.setVisible(true);
//				    	  myContainer.remove(myAdminPanel);
//				      } else {
//				    	  JOptionPane.showMessageDialog(myAdminPanel, "Error! Entered value can not be used to set the maximum number of Storefronts. (negative or zero)",
//				    			  "Error", JOptionPane.INFORMATION_MESSAGE);
//				      }
//				    }
//				    catch (NumberFormatException nfe)
//				    {
//				    	JOptionPane.showMessageDialog(myAdminPanel, "Error! Entered value can not be used to set the maximum number of Storefronts. (Not an integer)",
//				    			  "Error", JOptionPane.INFORMATION_MESSAGE);
//				    }
//			}
//
//		});
//		cancel.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				myAdminPanel.setVisible(false);
//				myContainer.remove(myAdminPanel);
//				myMainPanel.setVisible(true);
//			}
//
//		});
//		myAdminPanel.setLayout(new BorderLayout());
//		buttonPanel.setLayout(new FlowLayout());
//		myAdminPanel.add(editPanel, BorderLayout.CENTER);
//		myAdminPanel.add(buttonPanel, BorderLayout.SOUTH);
//		buttonPanel.add(changeMaxStorefronts);
//		buttonPanel.add(cancel);
//		myContainer.add(myAdminPanel);
//		myAdminPanel.setVisible(true);
//	}
//}