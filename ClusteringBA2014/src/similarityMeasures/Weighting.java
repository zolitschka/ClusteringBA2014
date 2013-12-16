package similarityMeasures;

/*
 * 
 * Die Klasse Weighting stellt die Gewichtung für jedes Attribut dar
 * 
 */

public class Weighting {
	final private static double acidWeight = 0.02;
	final private static double alcoholWeight = 0.02;
	final private static double aromaWeight = 0.14;
	final private static double grapeWeight = 0.18;
	final private static double priceWeight = 0.05;
	final private static double qualityWeight = 0.07;
	final private static double regionWeight = 0.07;
	final private static double sweetnessWeight = 0.15;
	final private static double vdpWeight = 0.02;
	final private static double wineryWeight = 0.08;
	final private static double wineStyleWeight = 0.1;
	final private static double yearWeight = 0.1;

	public static double getAcidWeight() {
		return acidWeight;
	}

	public static double getAlcoholWeight() {
		return alcoholWeight;
	}

	public static double getAromaWeight() {
		return aromaWeight;
	}

	public static double getGrapeWeight() {
		return grapeWeight;
	}

	public static double getPriceWeight() {
		return priceWeight;
	}

	public static double getQualityWeight() {
		return qualityWeight;
	}

	public static double getRegionWeight() {
		return regionWeight;
	}

	public static double getSweetnessWeight() {
		return sweetnessWeight;
	}

	public static double getVdpWeight() {
		return vdpWeight;
	}

	public static double getWineryWeight() {
		return wineryWeight;
	}

	public static double getWineStyleWeight() {
		return wineStyleWeight;
	}

	public static double getYearWeight() {
		return yearWeight;
	}
}
