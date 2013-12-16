package similarityMeasures;

import java.util.Collections;
import java.util.Vector;

/*
 * 
 * Die Klasse Similarity stellt Aehnlichkeitsmasse für jedes Attribut zur Verfügung
 * 
 */

public class Similarity {
	public static Vector<WineStyle> wineStyleVector = new Vector<WineStyle>();
	public static Vector<Quality> qualityVector = new Vector<Quality>();

	// Aroma-Taxonomie initialisieren nach Morbach
	static Aroma aromaTaxonomy = new Aroma(null, 0, 0);
	static Aroma fruchtig = new Aroma(aromaTaxonomy, 0.1, 0);
	static Aroma wuerzig = new Aroma(aromaTaxonomy, 0.1, 0);
	static Aroma pflanzlich = new Aroma(aromaTaxonomy, 0.1, 0);

	static Aroma gekochteFruechte = new Aroma(fruchtig, 0.3, 0);
	static Aroma trockenFruechte = new Aroma(fruchtig, 0.3, 0);
	static Aroma tropischeFruechte = new Aroma(fruchtig, 0.3, 0);
	static Aroma schwarzeFruechte = new Aroma(fruchtig, 0.3, 0);
	static Aroma roteFruechte = new Aroma(fruchtig, 0.3, 0);
	static Aroma gelbeFruechte = new Aroma(fruchtig, 0.3, 0);
	static Aroma grueneFruechte = new Aroma(fruchtig, 0.3, 0);
	static Aroma zitrusFruechte = new Aroma(fruchtig, 0.3, 0);
	static Aroma hefeartig = new Aroma(wuerzig, 0.3, 0);
	static Aroma schmelzig = new Aroma(wuerzig, 0.3, 0);
	static Aroma mineralisch = new Aroma(wuerzig, 0.3, 0);
	static Aroma animalisch = new Aroma(wuerzig, 0.3, 0);
	static Aroma pikant = new Aroma(wuerzig, 0.3, 0);
	static Aroma suesslich = new Aroma(wuerzig, 0.3, 0);
	static Aroma blumig = new Aroma(pflanzlich, 0.3, 0);
	static Aroma nussig = new Aroma(pflanzlich, 0.3, 0);
	static Aroma holzig = new Aroma(pflanzlich, 0.3, 0);
	static Aroma getrocknetePflanzen = new Aroma(pflanzlich, 0.3, 0);
	static Aroma gemuese = new Aroma(pflanzlich, 0.3, 0);
	static Aroma pflanzen = new Aroma(pflanzlich, 0.3, 0);

	static Aroma acatia = new Aroma(blumig, 1, 186);
	static Aroma almonds = new Aroma(nussig, 1, 250);
	static Aroma ananas = new Aroma(tropischeFruechte, 1, 225);
	static Aroma anise = new Aroma(pikant, 1, 224);
	static Aroma apple = new Aroma(roteFruechte, 1, 204);
	static Aroma apple_green = new Aroma(grueneFruechte, 1, 465);
	static Aroma apricot = new Aroma(gelbeFruechte, 1, 221);
	static Aroma asparagus = new Aroma(gemuese, 1, 275);
	static Aroma bacon = new Aroma(animalisch, 1, 276);
	static Aroma banana = new Aroma(tropischeFruechte, 1, 220);
	static Aroma bitter_chocolate = new Aroma(nussig, 1, 269);
	static Aroma blackberry = new Aroma(schwarzeFruechte, 1, 218);
	static Aroma black_cherries = new Aroma(schwarzeFruechte, 1, 273);
	static Aroma black_currant = new Aroma(schwarzeFruechte, 1, 272);
	static Aroma blueberry = new Aroma(schwarzeFruechte, 1, 466);
	static Aroma bread = new Aroma(hefeartig, 1, 217);
	static Aroma butter = new Aroma(schmelzig, 1, 216);
	static Aroma cabbage = new Aroma(gemuese, 1, 188);
	static Aroma cacao = new Aroma(nussig, 1, 193);
	static Aroma caramel = new Aroma(suesslich, 1, 192);
	static Aroma cedar = new Aroma(holzig, 1, 295);
	static Aroma champignon = new Aroma(pflanzen, 1, 215);
	static Aroma cheese = new Aroma(schmelzig, 1, 191);
	static Aroma cherry = new Aroma(schwarzeFruechte, 1, 190);
	static Aroma chocolate = new Aroma(nussig, 1, 268);
	static Aroma cinnamon = new Aroma(suesslich, 1, 296);
	static Aroma citrus_fruits = new Aroma(zitrusFruechte, 1, 350);
	static Aroma cloves = new Aroma(suesslich, 1, 208);
	static Aroma coconut = new Aroma(nussig, 1, 187);
	static Aroma coffee = new Aroma(nussig, 1, 194);
	static Aroma cranberries = new Aroma(roteFruechte, 1, 226);
	static Aroma cream = new Aroma(schmelzig, 1, 266);
	static Aroma elder = new Aroma(holzig, 1, 198);
	static Aroma eucalyptus = new Aroma(pflanzen, 1, 211);
	static Aroma fennel = new Aroma(pflanzen, 1, 304);
	static Aroma figs = new Aroma(trockenFruechte, 1, 210);
	static Aroma foliage = new Aroma(getrocknetePflanzen, 1, 257);
	static Aroma geranium = new Aroma(pflanzen, 1, 209);
	static Aroma ginger = new Aroma(suesslich, 1, 195);
	static Aroma gooseberry = new Aroma(grueneFruechte, 1, 277);
	static Aroma grapefuit = new Aroma(zitrusFruechte, 1, 207);
	static Aroma gras = new Aroma(pflanzen, 1, 206);
	static Aroma green_paprika = new Aroma(gemuese, 1, 205);
	static Aroma hay = new Aroma(getrocknetePflanzen, 1, 200);
	static Aroma hazelnut = new Aroma(nussig, 1, 203);
	static Aroma herbs = new Aroma(getrocknetePflanzen, 1, 259);
	static Aroma honey = new Aroma(gekochteFruechte, 1, 197);
	static Aroma horseradish = new Aroma(pikant, 1, 305);
	static Aroma hot_pepper = new Aroma(pikant, 1, 307);
	static Aroma ivy = new Aroma(pflanzen, 1, 308);
	static Aroma juniper = new Aroma(schwarzeFruechte, 1, 288);
	static Aroma kiwi = new Aroma(tropischeFruechte, 1, 189);
	static Aroma leather = new Aroma(animalisch, 1, 256);
	static Aroma lemon = new Aroma(zitrusFruechte, 1, 297);
	static Aroma licorice = new Aroma(pikant, 1, 258);
	static Aroma lime = new Aroma(zitrusFruechte, 1, 255);
	static Aroma lime_tree = new Aroma(zitrusFruechte, 1, 254);
	static Aroma lovage = new Aroma(pflanzen, 1, 251);
	static Aroma lychee = new Aroma(tropischeFruechte, 1, 253);
	static Aroma mandarines = new Aroma(tropischeFruechte, 1, 252);
	static Aroma mango = new Aroma(tropischeFruechte, 1, 249);
	static Aroma maracuja = new Aroma(tropischeFruechte, 1, 248);
	static Aroma mellow_apple = new Aroma(grueneFruechte, 1, 223);
	static Aroma mellow_apples = new Aroma(roteFruechte, 1, 222);
	static Aroma melon = new Aroma(tropischeFruechte, 1, 247);
	static Aroma menthol = new Aroma(pflanzen, 1, 291);
	static Aroma metal = new Aroma(mineralisch, 1, 246);
	static Aroma mint = new Aroma(pflanzen, 1, 245);
	static Aroma mirabelle = new Aroma(gelbeFruechte, 1, 244);
	static Aroma mocha = new Aroma(nussig, 1, 351);
	static Aroma moss = new Aroma(pflanzen, 1, 243);
	static Aroma muscat = new Aroma(suesslich, 1, 241);
	static Aroma musk = new Aroma(animalisch, 1, 242);
	static Aroma muskmelon = new Aroma(suesslich, 1, 298);
	static Aroma nuts = new Aroma(nussig, 1, 239);
	static Aroma oak = new Aroma(holzig, 1, 303);
	static Aroma olives = new Aroma(pikant, 1, 235);
	static Aroma onion = new Aroma(pikant, 1, 306);
	static Aroma orange = new Aroma(tropischeFruechte, 1, 237);
	static Aroma orange_peel = new Aroma(suesslich, 1, 236);
	static Aroma papaya = new Aroma(tropischeFruechte, 1, 234);
	static Aroma paprika = new Aroma(gemuese, 1, 233);
	static Aroma peach = new Aroma(gelbeFruechte, 1, 228);
	static Aroma pear = new Aroma(gelbeFruechte, 1, 219);
	static Aroma pepper = new Aroma(pikant, 1, 230);
	static Aroma petrol = new Aroma(mineralisch, 1, 524);
	static Aroma plum = new Aroma(roteFruechte, 1, 227);
	static Aroma porcini = new Aroma(pflanzen, 1, 290);
	static Aroma prune = new Aroma(trockenFruechte, 1, 214);
	static Aroma punchbowl = new Aroma(gekochteFruechte, 1, 265);
	static Aroma quince = new Aroma(gelbeFruechte, 1, 271);
	static Aroma raised_pastry = new Aroma(hefeartig, 1, 201);
	static Aroma raisins = new Aroma(trockenFruechte, 1, 261);
	static Aroma raspberry = new Aroma(roteFruechte, 1, 199);
	static Aroma red_currant = new Aroma(roteFruechte, 1, 264);
	static Aroma roasted = new Aroma(holzig, 1, 263);
	static Aroma roasted_nuts = new Aroma(nussig, 1, 238);
	static Aroma rockmelon = new Aroma(tropischeFruechte, 1, 299);
	static Aroma rose = new Aroma(blumig, 1, 260);
	static Aroma rosemary = new Aroma(pflanzen, 1, 262);
	static Aroma saffron = new Aroma(getrocknetePflanzen, 1, 380);
	static Aroma smoke = new Aroma(holzig, 1, 270);
	static Aroma soil = new Aroma(mineralisch, 1, 212);
	static Aroma sour_cherries = new Aroma(schwarzeFruechte, 1, 355);
	static Aroma stone_fuits = new Aroma(pflanzen, 1, 374);
	static Aroma straw = new Aroma(getrocknetePflanzen, 1, 279);
	static Aroma strawberry = new Aroma(roteFruechte, 1, 213);
	static Aroma syrup = new Aroma(gekochteFruechte, 1, 274);
	static Aroma tar = new Aroma(mineralisch, 1, 282);
	static Aroma tea = new Aroma(getrocknetePflanzen, 1, 281);
	static Aroma toast = new Aroma(hefeartig, 1, 284);
	static Aroma tobacco = new Aroma(holzig, 1, 280);
	static Aroma toffee = new Aroma(schmelzig, 1, 267);
	static Aroma truffles = new Aroma(animalisch, 1, 285);
	static Aroma vanilla = new Aroma(suesslich, 1, 286);
	static Aroma violets = new Aroma(blumig, 1, 287);
	static Aroma walnut = new Aroma(nussig, 1, 289);
	static Aroma wet_whool = new Aroma(animalisch, 1, 240);
	static Aroma whitethorn = new Aroma(blumig, 1, 293);
	static Aroma wood = new Aroma(holzig, 1, 196);
	static Aroma yeast = new Aroma(hefeartig, 1, 202);

	// Weinstil-/Qualitaet-/Aroma-Vektor fuellen
	public Similarity() {
		initAroma();
		initQuality();
		initWineStyle();
	}

	// Fuegt Aromen die passenden (laut Morbach) Kinderknoten hinzu
	private void initAroma() {
		aromaTaxonomy.setChilds(fruchtig, wuerzig, pflanzlich);
		fruchtig.setChilds(gekochteFruechte, trockenFruechte,
				tropischeFruechte, schwarzeFruechte, roteFruechte,
				gelbeFruechte, grueneFruechte, zitrusFruechte);
		wuerzig.setChilds(hefeartig, schmelzig, mineralisch, animalisch,
				pikant, suesslich);
		pflanzlich.setChilds(blumig, nussig, holzig, getrocknetePflanzen,
				gemuese, pflanzen);
		gekochteFruechte.setChilds(honey, punchbowl, syrup);
		trockenFruechte.setChilds(figs, prune, raisins);
		tropischeFruechte.setChilds(ananas, banana, kiwi, lychee, mandarines,
				mango, maracuja, melon, orange, papaya, rockmelon);
		schwarzeFruechte.setChilds(blackberry, black_cherries, black_currant,
				blueberry, cherry, juniper, sour_cherries);
		roteFruechte.setChilds(apple, cranberries, mellow_apples, plum,
				raspberry, red_currant, strawberry);
		gelbeFruechte.setChilds(apricot, mirabelle, peach, pear, quince);
		grueneFruechte.setChilds(apple_green, gooseberry, mellow_apple);
		zitrusFruechte.setChilds(citrus_fruits, grapefuit, lemon, lime,
				lime_tree);
		hefeartig.setChilds(bread, raised_pastry, toast, yeast);
		schmelzig.setChilds(butter, cheese, cream, toffee);
		mineralisch.setChilds(metal, petrol, soil, tar);
		animalisch.setChilds(bacon, leather, musk, truffles, wet_whool);
		pikant.setChilds(anise, horseradish, hot_pepper, licorice, olives,
				onion, pepper);
		suesslich.setChilds(caramel, cinnamon, cloves, ginger, muscat,
				muskmelon, orange_peel, vanilla);
		blumig.setChilds(acatia, rose, violets, whitethorn);
		nussig.setChilds(almonds, bitter_chocolate, cacao, chocolate, coconut,
				walnut, coffee, hazelnut, mocha, nuts, roasted_nuts);
		holzig.setChilds(cedar, elder, oak, roasted, smoke, tobacco, wood);
		getrocknetePflanzen.setChilds(foliage, hay, herbs, saffron, straw, tea);
		gemuese.setChilds(asparagus, cabbage, green_paprika, paprika);
		pflanzen.setChilds(champignon, eucalyptus, fennel, geranium, gras, ivy,
				lovage, menthol, mint, moss, porcini, rosemary, stone_fuits);
	}

	// Erzeugt die Qualitäts-Vergleichs-Matrix
	private void initQuality() {
		// Qualitaet-Tabellen initialisieren nach Morbach & Gessinger
		Quality qw1 = new Quality(6, 1, 0.8, 0, 0, 0, 0, 0);
		Quality qw2 = new Quality(118, 1, 0.8, 0, 0, 0, 0, 0);
		Quality k = new Quality(117, 0.8, 1, 0, 0, 0, 0, 0);
		Quality sl = new Quality(116, 0, 0, 1, 0.8, 0, 0, 0);
		Quality al = new Quality(14, 0, 0, 0.8, 1, 0.5, 0, 0.3);
		Quality bal = new Quality(115, 0, 0, 0, 0.5, 1, 0.8, 0.8);
		Quality tbal = new Quality(449, 0, 0, 0, 0, 0.8, 1, 0.8);
		Quality ew = new Quality(450, 0, 0, 0, 0.3, 0.5, 0.5, 1);
		Collections.addAll(qualityVector, qw1, qw2, k, sl, al, bal, tbal, ew);
	}

	// Erzeugt die Weinstil-Vergleichs-Matrix
	private void initWineStyle() {
		// Weinstil-Tabellen initialisieren nach Morbach & Gessinger
		WineStyle ww1 = new WineStyle(357, 1, 0.5, 0.3, 0, 0, 0, 0, 0);
		WineStyle ww2 = new WineStyle(358, 0.5, 1, 0.3, 0.2, 0, 0, 0, 0);
		WineStyle ww3 = new WineStyle(359, 0.3, 0.3, 1, 0.3, 0, 0, 0, 0);
		WineStyle ww4 = new WineStyle(360, 0, 0.2, 0.3, 1, 0, 0, 0, 0);
		WineStyle rw1 = new WineStyle(361, 0, 0, 0, 0, 1, 0, 0, 0.2);
		WineStyle rw2 = new WineStyle(362, 0, 0, 0, 0, 0, 1, 0.3, 0.5);
		WineStyle rw3 = new WineStyle(363, 0, 0, 0, 0, 0, 0.3, 1, 0.3);
		WineStyle rw4 = new WineStyle(364, 0, 0, 0, 0, 0.2, 0.5, 0.3, 1);
		Collections.addAll(wineStyleVector, ww1, ww2, ww3, ww4, rw1, rw2, rw3,
				rw4);
	}

	// Mass fuer binaere Attribute (Year, Winery, Vdp, Region, Grape)
	public static double binary(int faktor1, int faktor2) {
		if (faktor1 == faktor2)
			return 1;
		else
			return 0;
	}

	// Mass fuer Attribut Grape
	public static double grape(int[] ks1, int[] ks2) {
		double result = 0;
		for (int i = 0; i < ks1.length; i++) {
			for (int j = 0; j < ks2.length; j++) {
				if (ks1[i] == (ks2[j])) {
					result += 1;
					break;
				}
				// Abfangen verschiedener Bezeichnungen für eine Rebsorte
				else if (ks1[i] == 171 && ks2[j] == 131 || ks1[i] == 131
						&& ks2[j] == 171) {
					result += 1;
					break;

				} else if (ks1[i] == 151 && ks2[j] == 152 || ks1[i] == 152
						&& ks2[j] == 151) {
					result += 1;
					break;
				} else if (ks1[i] == 139 && ks2[j] == 505 || ks1[i] == 505
						&& ks2[j] == 139) {
					result += 1;
					break;
				} else if (ks1[i] == 141 && ks2[j] == 169 || ks1[i] == 169
						&& ks2[j] == 141) {
					result += 1;
					break;
				} else if (ks1[i] == 150 && ks2[j] == 149 || ks1[i] == 149
						&& ks2[j] == 150) {
					result += 1;
					break;
				} else if (ks1[i] == 155 && ks2[j] == 157 || ks1[i] == 157
						&& ks2[j] == 155) {
					result += 1;
					break;
				} else if (ks1[i] == 159 && ks2[j] == 160 || ks1[i] == 160
						&& ks2[j] == 159) {
					result += 1;
					break;
				} else if (ks1[i] == 166 && ks2[j] == 167 || ks1[i] == 167
						&& ks2[j] == 166) {
					result += 1;
					break;
				} else if (ks1[i] == 512 && ks2[j] == 429 || ks1[i] == 429
						&& ks2[j] == 512) {
					result += 1;
					break;
				} else if (ks1[i] == 155 && ks2[j] == 156 || ks1[i] == 156
						&& ks2[j] == 155) {
					result += 1;
					break;
				} else if (ks1[i] == 156 && ks2[j] == 157 || ks1[i] == 157
						&& ks2[j] == 156) {
					result += 1;
					break;
				}
			}
		}
		return (result / ks1.length);
	}

	// Mass fuer numerische Attribute (Price, Alcohol, Sweetness, Acid)
	public static double price(double price1, double price2) {
		double min = 1.0;
		double max = 10.0;
		double asymmetricFactor = 0.9;

		if (price1 < price2)
			max *= asymmetricFactor;

		double difference = Math.abs(price1 - price2);

		if (difference < min)
			return 1;
		else if (difference > max)
			return 0;
		else
			return ((max - difference) / (max - min));
	}

	public static double alcohol(double alcohol1, double alcohol2) {
		double min = 0.5;
		double max = 2.0;
		double difference = Math.abs(alcohol1 - alcohol2);

		if (difference < min)
			return 1;
		else if (difference > max)
			return 0;
		else
			return ((max - difference) / (max - min));
	}

	public static double sweetness(double sweetness1, double sweetness2) {
		double min = 3.0;
		double max = 10.0;
		double difference = Math.abs(sweetness1 - sweetness2);

		if (difference < min)
			return 1;
		else if (difference > max)
			return 0;
		else
			return ((max - difference) / (max - min));
	}

	public static double acid(double acid1, double acid2) {
		double min = 0.1;
		double max = 1.0;
		double difference = Math.abs(acid1 - acid2);

		if (difference < min)
			return 1;
		else if (difference > max)
			return 0;
		else
			return ((max - difference) / (max - min));
	}

	// Mass fuer Eigenschaften mit Aehnlichkeitsmatrix (WineStyle, Quality)
	public static double wineStyle(int wineStyle1, int wineStyle2) {
		double result = 0;
		for (int i = 0; i < wineStyleVector.size(); i++) {
			WineStyle tmp = wineStyleVector.elementAt(i);
			if (tmp.getWineStyle() == wineStyle1)
				result = tmp.compareTo(wineStyle2);
		}
		return result;

	}

	public static double quality(int quality1, int quality2) {
		double result = 0;
		for (int i = 0; i < qualityVector.size(); i++) {
			Quality tmp = qualityVector.elementAt(i);
			if (tmp.getQuality() == quality1)
				result = tmp.compareTo(quality2);
		}
		return result;
	}

	// Mass fuer Attribut Aroma
	public static double aroma(int[] ks1, int[] ks2) {
		double result = 0;
		for (int i = 0; i < ks1.length; i++) {
			Aroma a1 = searchAroma(ks1[i]);
			double maxSim = 0;
			for (int j = 0; j < ks2.length; j++) {
				Aroma a2 = searchAroma(ks2[j]);
				double tmpSim = tax(a1, a2);
				if (tmpSim > maxSim) {
					maxSim = tmpSim;
				}
			}
			result += maxSim;
		}
		return (result / ks1.length);
	}

	// sucht in der Taxonomie den nächsten gemeinsamen Knoten
	private static double tax(Aroma a1, Aroma a2) {
		while (a1 != a2) {
			a1 = a1.getParent();
			a2 = a2.getParent();
		}

		return a1.getSimilarity();
	}

	// findet vorhandenes Aroma anhand einer Auspräung
	private static Aroma searchAroma(int value) {
		Aroma tmp = null;

		switch (value) {
		case 186:
			tmp = acatia;
			break;
		case 250:
			tmp = almonds;
			break;
		case 225:
			tmp = ananas;
			break;
		case 224:
			tmp = anise;
			break;
		case 204:
			tmp = apple;
			break;
		case 465:
			tmp = apple_green;
			break;
		case 221:
			tmp = apricot;
			break;
		case 275:
			tmp = asparagus;
			break;
		case 276:
			tmp = bacon;
			break;
		case 220:
			tmp = banana;
			break;
		case 269:
			tmp = bitter_chocolate;
			break;
		case 218:
			tmp = blackberry;
			break;
		case 273:
			tmp = black_cherries;
			break;
		case 272:
			tmp = black_currant;
			break;
		case 466:
			tmp = blueberry;
			break;
		case 217:
			tmp = bread;
			break;
		case 216:
			tmp = butter;
			break;
		case 188:
			tmp = cabbage;
			break;
		case 193:
			tmp = cacao;
			break;
		case 192:
			tmp = caramel;
			break;
		case 295:
			tmp = cedar;
			break;
		case 215:
			tmp = champignon;
			break;
		case 191:
			tmp = cheese;
			break;
		case 190:
			tmp = cherry;
			break;
		case 268:
			tmp = chocolate;
			break;
		case 296:
			tmp = cinnamon;
			break;
		case 350:
			tmp = citrus_fruits;
			break;
		case 208:
			tmp = cloves;
			break;
		case 187:
			tmp = coconut;
			break;
		case 194:
			tmp = coffee;
			break;
		case 226:
			tmp = cranberries;
			break;
		case 266:
			tmp = cream;
			break;
		case 198:
			tmp = elder;
			break;
		case 211:
			tmp = eucalyptus;
			break;
		case 304:
			tmp = fennel;
			break;
		case 210:
			tmp = figs;
			break;
		case 257:
			tmp = foliage;
			break;
		case 209:
			tmp = geranium;
			break;
		case 195:
			tmp = ginger;
			break;
		case 277:
			tmp = gooseberry;
			break;
		case 207:
			tmp = grapefuit;
			break;
		case 206:
			tmp = gras;
			break;
		case 205:
			tmp = green_paprika;
			break;
		case 200:
			tmp = hay;
			break;
		case 203:
			tmp = hazelnut;
			break;
		case 259:
			tmp = herbs;
			break;
		case 197:
			tmp = honey;
			break;
		case 305:
			tmp = horseradish;
			break;
		case 307:
			tmp = hot_pepper;
			break;
		case 308:
			tmp = ivy;
			break;
		case 288:
			tmp = juniper;
			break;
		case 189:
			tmp = kiwi;
			break;
		case 256:
			tmp = leather;
			break;
		case 297:
			tmp = lemon;
			break;
		case 258:
			tmp = licorice;
			break;
		case 255:
			tmp = lime;
			break;
		case 254:
			tmp = lime_tree;
			break;
		case 251:
			tmp = lovage;
			break;
		case 253:
			tmp = lychee;
			break;
		case 252:
			tmp = mandarines;
			break;
		case 249:
			tmp = mango;
			break;
		case 248:
			tmp = maracuja;
			break;
		case 223:
			tmp = mellow_apple;
			break;
		case 222:
			tmp = mellow_apples;
			break;
		case 247:
			tmp = melon;
			break;
		case 291:
			tmp = menthol;
			break;
		case 246:
			tmp = metal;
			break;
		case 245:
			tmp = mint;
			break;
		case 244:
			tmp = mirabelle;
			break;
		case 351:
			tmp = mocha;
			break;
		case 243:
			tmp = moss;
			break;
		case 241:
			tmp = muscat;
			break;
		case 242:
			tmp = musk;
			break;
		case 298:
			tmp = muskmelon;
			break;
		case 239:
			tmp = nuts;
			break;
		case 303:
			tmp = oak;
			break;
		case 235:
			tmp = olives;
			break;
		case 306:
			tmp = onion;
			break;
		case 237:
			tmp = orange;
			break;
		case 236:
			tmp = orange_peel;
			break;
		case 234:
			tmp = papaya;
			break;
		case 233:
			tmp = paprika;
			break;
		case 228:
			tmp = peach;
			break;
		case 219:
			tmp = pear;
			break;
		case 230:
			tmp = pepper;
			break;
		case 524:
			tmp = petrol;
			break;
		case 227:
			tmp = plum;
			break;
		case 290:
			tmp = porcini;
			break;
		case 214:
			tmp = prune;
			break;
		case 265:
			tmp = punchbowl;
			break;
		case 271:
			tmp = quince;
			break;
		case 201:
			tmp = raised_pastry;
			break;
		case 261:
			tmp = raisins;
			break;
		case 199:
			tmp = raspberry;
			break;
		case 264:
			tmp = red_currant;
			break;
		case 263:
			tmp = roasted;
			break;
		case 238:
			tmp = roasted_nuts;
			break;
		case 299:
			tmp = rockmelon;
			break;
		case 260:
			tmp = rose;
			break;
		case 262:
			tmp = rosemary;
			break;
		case 380:
			tmp = saffron;
			break;
		case 270:
			tmp = smoke;
			break;
		case 212:
			tmp = soil;
			break;
		case 355:
			tmp = sour_cherries;
			break;
		case 374:
			tmp = stone_fuits;
			break;
		case 279:
			tmp = straw;
			break;
		case 213:
			tmp = strawberry;
			break;
		case 274:
			tmp = syrup;
			break;
		case 282:
			tmp = tar;
			break;
		case 281:
			tmp = tea;
			break;
		case 284:
			tmp = toast;
			break;
		case 280:
			tmp = tobacco;
			break;
		case 267:
			tmp = toffee;
			break;
		case 285:
			tmp = truffles;
			break;
		case 286:
			tmp = vanilla;
			break;
		case 287:
			tmp = violets;
			break;
		case 289:
			tmp = walnut;
			break;
		case 240:
			tmp = wet_whool;
			break;
		case 293:
			tmp = whitethorn;
			break;
		case 196:
			tmp = wood;
			break;
		case 202:
			tmp = yeast;
			break;
		}
		return tmp;
	}
}
