package methodFromZolitschka;

import java.util.Vector;

import commonClasses.Cluster;
import commonClasses.Wine;
import database.Lists;

/*
 * 
 * Hier kommt eine Methodenbeschreibung rein
 * 
 */

public class Method {
	private Vector<Wine> wineList = Lists.getWineList();
	private Vector<Cluster> clusterList = new Vector<Cluster>();
	private Cluster test = new Cluster("1");

	public Method() {
		for (Wine tmpWine : wineList) {
			test.addWineToCluster(tmpWine);
		}
		clusterList.add(test);
	}

	public Vector<Cluster> getClusterList() {
		return clusterList;
	}
}
