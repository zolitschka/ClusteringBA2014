package similarityMeasures;

import java.util.Vector;

/*
 * 
 * Die Klasse Aroma stellt ein Aroma-Objekt für das Attribut Aroma dar
 * 
 */

public class Aroma {
	private final int aroma;
	private final double similarity;
	private final Aroma parent;

	private static Vector<Aroma> childsVector = new Vector<Aroma>();

	public Aroma(Aroma parent, double similarity, int number) {
		this.similarity = similarity;
		this.parent = parent;
		this.aroma = number;
	}

	public Vector<Aroma> getChilds() {
		return childsVector;
	}

	public void setChilds(Aroma... childs) {
		for (Aroma child : childs) {
			childsVector.add(child);
		}
	}

	public double getSimilarity() {
		return similarity;
	}

	public Aroma getParent() {
		return parent;
	}

	public int getNumber() {
		return aroma;
	}

}
