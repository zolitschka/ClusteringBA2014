package commonClasses;

import java.util.Vector;

/*
 * 
 * ein Clusterobjekt beinhaltet
 * - einen Vektor aller Weine dieses Clusters
 * - Spannweiten einzelner Eigenschaften, um Cluster zu interpretieren
 * 
 * 
 */

public class Cluster {
	private String name;
	private Vector<Wine> clusterVecor = new Vector<Wine>();

	public Cluster(String name) {
		this.name = name;
	}

	public void addWineToCluster(Wine wineToAdd) {
		clusterVecor.add(wineToAdd);
	}

	public void removeWineFromCluster(Wine wineToRemove) {
		clusterVecor.remove(wineToRemove);
	}

	public String getName() {
		return name;
	}
}
