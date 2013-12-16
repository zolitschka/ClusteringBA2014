package commonClasses;

import java.util.Vector;

/*
 * 
 * User-Objekt für Datenstruktur und Basis für den Uservektor
 * 
 */

public class User {
	private int userID;
	private Vector<Wine> products = new Vector<Wine>();
	private Vector<Wine> similarityList = new Vector<Wine>();

	public User() {

	}

	public int getId() {
		return userID;
	}

	public void setId(int id) {
		this.userID = id;
	}

	public Vector<Wine> getProducts() {
		return products;
	}

	public void addProduct(Wine product) {
		this.products.add(product);
	}

	public String toString() {
		return "User " + userID;
	}

	public Vector<Wine> getSimilarityList() {
		return similarityList;
	}

	public void setSimilarityList(Vector<Wine> similarityList) {
		this.similarityList = similarityList;
	}

}
