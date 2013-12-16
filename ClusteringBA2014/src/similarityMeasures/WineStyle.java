package similarityMeasures;

/*
 * Die Klasse Aroma stellt ein Qualitäts-Objekt für das Attribut Qualitaet dar
 * 
 * Weinstil-Objekt mit Verhältnissen zu allen 8 Weinstilen und Vergleichsmethode
 * 
 * ww1 = leicht & schlank 
 * ww2 = lebhaft & aromatisch
 * ww3 = würzig & stahlig
 * ww4 = kräftig & füllig
 * rw1 = leicht & fruchtig
 * rw2 = kraftvoll & würzig
 * rw3 = samtig & schmelzig
 * rw4 = üppig & saftig
 * 
 */
public class WineStyle {
	final private int wineStyle;
	final private double relationToWW1;
	final private double relationToWW2;
	final private double relationToWW3;
	final private double relationToWW4;
	final private double relationToRW1;
	final private double relationToRW2;
	final private double relationToRW3;
	final private double relationToRW4;

	public WineStyle(int wineStyle, double ww1, double ww2, double ww3,
			double ww4, double rw1, double rw2, double rw3, double rw4) {
		this.wineStyle = wineStyle;
		this.relationToWW1 = ww1;
		this.relationToWW2 = ww2;
		this.relationToWW3 = ww3;
		this.relationToWW4 = ww4;
		this.relationToRW1 = rw1;
		this.relationToRW2 = rw2;
		this.relationToRW3 = rw3;
		this.relationToRW4 = rw4;
	}

	// Vergleichs-Methode fuer Weinstil-Objekte
	public double compareTo(int compareWineStyle) {
		if (compareWineStyle == 357)
			return relationToWW1;
		else if (compareWineStyle == 358)
			return relationToWW2;
		else if (compareWineStyle == 359)
			return relationToWW3;
		else if (compareWineStyle == 360)
			return relationToWW4;
		else if (compareWineStyle == 361)
			return relationToRW1;
		else if (compareWineStyle == 362)
			return relationToRW2;
		else if (compareWineStyle == 363)
			return relationToRW3;
		else
			return relationToRW4;
	}

	public int getWineStyle() {
		return wineStyle;
	}
}
