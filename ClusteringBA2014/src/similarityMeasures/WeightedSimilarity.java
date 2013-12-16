package similarityMeasures;

import commonClasses.Wine;

/*
 * 
 * Die Klasse WeightedSimilarity berechnet für zwei gegebene Weine eine Aehnlichkeit
 * 
 */

public class WeightedSimilarity {

	// Similarity der einzelnen Attribute mit jeweiliger Gewichtung
	// multiplizieren
	public static double getSimilarity(Wine wine1, Wine wine2) {
		double faktor = 1; // Korrekturfaktor, falls ein Attribut nicht gesetzt
							// wurde
		double simYear = 0;
		double simWinery = 0;
		double simVdp = 0;
		double simRegion = 0;
		double simGrape = 0;
		double simPrice = 0;
		double simAlcohol = 0;
		double simSweetness = 0;
		double simAcid = 0;
		double simWineStyle = 0;
		double simQuality = 0;
		double simAroma = 0;

		if (wine1.getYear() == -1 || wine2.getYear() == -1) {
			faktor -= Weighting.getYearWeight();
		} else {
			simYear = Similarity.binary(wine1.getYear(), wine2.getYear());
		}
		if (wine1.getWinery() == -1 || wine2.getWinery() == -1) {
			faktor -= Weighting.getWineryWeight();
		} else {
			simWinery = Similarity.binary(wine1.getWinery(), wine2.getWinery());
		}
		if (wine1.getVdp() == -1 || wine2.getVdp() == -1) {
			faktor -= Weighting.getVdpWeight();
		} else {
			simVdp = Similarity.binary(wine1.getVdp(), wine2.getVdp());
		}
		if (wine1.getRegion() == -1 || wine2.getRegion() == -1) {
			faktor -= Weighting.getRegionWeight();
		} else {
			simRegion = Similarity.binary(wine1.getRegion(), wine2.getRegion());
		}
		if (wine1.getGrape() == null || wine2.getGrape() == null) {
			faktor -= Weighting.getGrapeWeight();
		} else {
			simGrape = Similarity.grape(wine1.getGrape(), wine2.getGrape());
		}
		if (wine1.getPrice() == -1 || wine2.getPrice() == -1) {
			faktor -= Weighting.getPriceWeight();
		} else {
			simPrice = Similarity.price(wine1.getPrice(), wine2.getPrice());
		}
		if (wine1.getAlcohol() == -1 || wine2.getAlcohol() == -1) {
			faktor -= Weighting.getAlcoholWeight();
		} else {
			simAlcohol = Similarity.alcohol(wine1.getAlcohol(),
					wine2.getAlcohol());
		}
		if (wine1.getSweetness() == -1 || wine2.getSweetness() == -1) {
			faktor -= Weighting.getSweetnessWeight();
		} else {
			simSweetness = Similarity.sweetness(wine1.getSweetness(),
					wine2.getSweetness());
		}
		if (wine1.getAcid() == -1 || wine2.getAcid() == -1) {
			faktor -= Weighting.getAcidWeight();
		} else {
			simAcid = Similarity.acid(wine1.getAcid(), wine2.getAcid());
		}
		if (wine1.getWineStyle() == -1 || wine2.getWineStyle() == -1) {
			faktor -= Weighting.getWineStyleWeight();
		} else {
			simWineStyle = Similarity.wineStyle(wine1.getWineStyle(),
					wine2.getWineStyle());
		}
		if (wine1.getQuality() == -1 || wine2.getQuality() == -1) {
			faktor -= Weighting.getQualityWeight();
		} else {
			simQuality = Similarity.quality(wine1.getQuality(),
					wine2.getQuality());
		}
		if (wine1.getAroma() == null || wine2.getAroma() == null) {
			faktor -= Weighting.getAromaWeight();
		} else {
			simAroma = Similarity.aroma(wine1.getAroma(), wine2.getAroma());
		}

		double result = simYear * Weighting.getYearWeight() + simWinery
				* Weighting.getWineryWeight() + simVdp
				* Weighting.getVdpWeight() + simRegion
				* Weighting.getRegionWeight() + simGrape
				* Weighting.getGrapeWeight() + simPrice
				* Weighting.getPriceWeight() + simAlcohol
				* Weighting.getAlcoholWeight() + simSweetness
				* Weighting.getSweetnessWeight() + simAcid
				* Weighting.getAcidWeight() + simWineStyle
				* Weighting.getWineStyleWeight() + simQuality
				* Weighting.getQualityWeight() + simAroma
				* Weighting.getAromaWeight();

		// Double Rundungsfehler eliminieren
		faktor = ((double) ((int) (faktor * 100))) / 100;

		// Ergebnis an Anzahl Attribute anpassen
		result = result / faktor;
		if (result > 1) {
			result = 1;
		}

		return result;
	}
}
