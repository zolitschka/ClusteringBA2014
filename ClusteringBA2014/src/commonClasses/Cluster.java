package commonClasses;

import java.util.Vector;

/*
 * 
 * ein Clusterobjekt beinhaltet
 * - einen Vektor aller Weine dieses Clusters
 * - Spannweiten einzelner Eigenschaften, um Cluster zu interpretieren
 * 
 */

public class Cluster {
	private String name;
	private Vector<Wine> clusterList = new Vector<Wine>();

	private Vector<Double> acidList;
	private Vector<Double> alcoholList;
	private Vector<int[]> aromaList;
	private Vector<int[]> grapeList;
	private Vector<Double> priceList;
	private Vector<Integer> qualityList;
	private Vector<Integer> regionList;
	private Vector<Double> sweetnessList;
	private Vector<Integer> tasteList;
	private Vector<Integer> vdpList;
	private Vector<Integer> wineryList;
	private Vector<Integer> wineStyleList;
	private Vector<Integer> yearList;

	public Cluster(String name) {
		this.name = name;
	}

	/*
	 * füllt die Eigenschafts-Listen allen Werten aus dem Cluster wird
	 * aufgerufen, wenn Cluster vollständig gefüllt ist
	 */
	public void createPropertieLists() {
		for (Wine wine : clusterList) {
			acidList.add(wine.getAcid());
			alcoholList.add(wine.getAlcohol());
			aromaList.add(wine.getAroma());
			grapeList.add(wine.getGrape());
			priceList.add(wine.getPrice());
			qualityList.add(wine.getQuality());
			regionList.add(wine.getRegion());
			sweetnessList.add(wine.getSweetness());
			tasteList.add(wine.getTaste());
			vdpList.add(wine.getVdp());
			wineryList.add(wine.getWinery());
			wineStyleList.add(wine.getWineStyle());
			yearList.add(wine.getYear());
		}
	}

	public void addWineToCluster(Wine wineToAdd) {
		clusterList.add(wineToAdd);
	}

	public void removeWineFromCluster(Wine wineToRemove) {
		clusterList.remove(wineToRemove);
	}

	public String getName() {
		return name;
	}
}
