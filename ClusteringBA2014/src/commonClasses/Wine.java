package commonClasses;

import java.text.DecimalFormat;
import java.util.Vector;

/*
 * 
 * Wein-Objekt für Datenstruktur und Basis für den Weinvektor
 * 
 */

public class Wine implements Comparable<Wine> {
	private double acid = -1;
	private double alcohol = -1;
	private int aroma[];
	private int grape[];
	private int wineId = -1;
	private String wineName;
	private double wineScore = 0.0;
	private int similarity = -1;
	private float rating = -1;

	private boolean svd = false;
	private boolean kaufhistorie = false;
	private boolean content = false;

	private Vector<Wine> similarityList = new Vector<Wine>();

	private double price = -1;
	private int quality = -1;
	private int region = -1;
	private double sweetness = -1;
	private int taste = -1;
	private int vdp = -1;
	private int winery = -1;
	private int wineStyle = -1;
	private int year = -1;
	DecimalFormat f = new DecimalFormat("#0.00");

	public Wine() {

	}

	public boolean isSvd() {
		return svd;
	}

	public void setSvd(boolean svd) {
		this.svd = svd;
	}

	public boolean isKaufhistorie() {
		return kaufhistorie;
	}

	public void setKaufhistorie(boolean kaufhistorie) {
		this.kaufhistorie = kaufhistorie;
	}

	public boolean isContent() {
		return content;
	}

	public void setContent(boolean content) {
		this.content = content;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getId() {
		return wineId;
	}

	public void setId(int wineId) {
		this.wineId = wineId;
	}

	public String getName() {
		return wineName;
	}

	public void setName(String wineName) {
		this.wineName = wineName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getWinery() {
		return winery;
	}

	public void setWinery(int winery) {
		this.winery = winery;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public int getRegion() {
		return region;
	}

	public void setRegion(int region) {
		this.region = region;
	}

	public int getTaste() {
		return taste;
	}

	public void setTaste(int taste) {
		this.taste = taste;
	}

	public double getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(double alcohol) {
		this.alcohol = alcohol;
	}

	public double getAcid() {
		return acid;
	}

	public void setAcid(double acid) {
		this.acid = acid;
	}

	public double getSweetness() {
		return sweetness;
	}

	public void setSweetness(double sweetness) {
		this.sweetness = sweetness;
	}

	public int getVdp() {
		return vdp;
	}

	public void setVdp(int vdp) {
		this.vdp = vdp;
	}

	public int[] getGrape() {
		return grape;
	}

	public void setGrape(int grape[]) {
		this.grape = grape;
	}

	public int[] getAroma() {
		return aroma;
	}

	public void setAroma(int aroma[]) {
		this.aroma = aroma;
	}

	public int getWineStyle() {
		return wineStyle;
	}

	public void setWineStyle(int wineStyle) {
		this.wineStyle = wineStyle;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return getId() + ": " + getName();
	}

	public double getWineScore() {
		return wineScore;
	}

	public void setWineScore(double wineScore) {
		this.wineScore = wineScore;
	}

	public Vector<Wine> getSimilarityList() {
		return similarityList;
	}

	public void setSimilarityList(Vector<Wine> similarityList) {
		this.similarityList = similarityList;
	}

	public int getSimilarity() {
		return similarity;
	}

	public void setSimilarity(double similarity) {
		this.similarity = (int) (similarity * 100);
	}

	@Override
	public int compareTo(Wine wine2) {
		if (this.similarity < wine2.getSimilarity()) {
			return 1;
		} else if (this.similarity > wine2.getSimilarity()) {
			return -1;
		}
		return 0;
	}

	public void copyWine(Wine wine) {
		this.acid = wine.getAcid();
		this.alcohol = wine.getAlcohol();
		this.aroma = wine.getAroma();
		this.grape = wine.getGrape();
		this.price = wine.getPrice();
		this.quality = wine.getQuality();
		this.region = wine.getRegion();
		this.sweetness = wine.getSweetness();
		this.taste = wine.getTaste();
		this.vdp = wine.getVdp();
		this.wineId = wine.getId();
		this.wineName = wine.getName();
		this.winery = wine.getWinery();
		this.wineScore = wine.getWineScore();
		this.wineStyle = wine.getWineStyle();
		this.year = wine.getYear();
	}
}
