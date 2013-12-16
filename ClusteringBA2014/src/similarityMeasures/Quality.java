package similarityMeasures;

public class Quality {

	/*
	 * Die Klasse Aroma stellt ein Qualitäts-Objekt für das Attribut Qualitaet
	 * dar
	 * 
	 * Quality-Objekt mit Verhältnissen zu allen 7 Qualitäten und
	 * Vergleichsmethode
	 * 
	 * qw = Qualitätswein qw1 = Qualitätswein b.A. qw2 = Qualitätswein m.A. k =
	 * Kabinett sl = Spätlese al = Auslese bal = Beerenauslese tbal =
	 * Trockenbeerenauslese ew = Eiswein
	 */

	final private int quality;
	final private double relationToQW;
	final private double relationToK;
	final private double relationToSL;
	final private double relationToAL;
	final private double relationToBAL;
	final private double relationToTBAL;
	final private double relationToEW;

	public Quality(int quality, double qw, double k, double sl, double al,
			double bal, double tbal, double ew) {
		this.quality = quality;
		this.relationToQW = qw;
		this.relationToK = k;
		this.relationToSL = sl;
		this.relationToAL = al;
		this.relationToBAL = bal;
		this.relationToTBAL = tbal;
		this.relationToEW = ew;

	}

	// Vergleichs-Methode fuer Qualitaets-Objekte
	public double compareTo(int compareQuality) {
		if (compareQuality == 6 || compareQuality == 118)
			return relationToQW;
		else if (compareQuality == 117)
			return relationToK;
		else if (compareQuality == 116)
			return relationToSL;
		else if (compareQuality == 14)
			return relationToAL;
		else if (compareQuality == 115)
			return relationToBAL;
		else if (compareQuality == 449)
			return relationToTBAL;
		else
			return relationToEW;
	}

	public int getQuality() {
		return quality;
	}

}
