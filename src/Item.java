import java.io.*;
import java.util.*;
import java.lang.Float;


public class Item {
	private int itemID;
	private int sellerID;
	private String iName;
	private String iDesc;
	private float iPrice;
	private int iQuantity;
	private String iCondition;
	private String iSize;
	private String iComment;
	
	/** Constructor for the Item class */
	public Item(int iID, int sID, String theName, String theDesc, float thePrice, 
				int theQuantity, String theCond, String theSize, String theComment) {
		itemID = iID;
		sellerID = sID;
		iName = theName;
		iDesc = theDesc;
		iPrice = thePrice;
		iQuantity = theQuantity;
		iCondition = theCond;
		iSize = theSize;
		iComment = theComment;		
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public int getSellerID() {
		return sellerID;
	}

	public void setSellerID(int sellerID) {
		this.sellerID = sellerID;
	}

	public String getiName() {
		return iName;
	}

	public void setiName(String iName) {
		this.iName = iName;
	}

	public String getiDesc() {
		return iDesc;
	}

	public void setiDesc(String iDesc) {
		this.iDesc = iDesc;
	}

	public float getiPrice() {
		return iPrice;
	}

	public void setiPrice(float iPrice) {
		this.iPrice = iPrice;
	}

	public int getiQuantity() {
		return iQuantity;
	}

	public void setiQuantity(int iQuantity) {
		this.iQuantity = iQuantity;
	}

	public String getiCondition() {
		return iCondition;
	}

	public void setiCondition(String iCondition) {
		this.iCondition = iCondition;
	}

	public String getiSize() {
		return iSize;
	}

	public void setiSize(String iSize) {
		this.iSize = iSize;
	}

	public String getiComment() {
		return iComment;
	}

	public void setiComment(String iComment) {
		this.iComment = iComment;
	}
}

