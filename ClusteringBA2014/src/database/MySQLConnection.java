package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import commonClasses.Wine;

/*
 * 
 * Die Klasse MySQLConnection ist die Schnittstelle zur Datenbank und
 * speichert die Daten in Datenstrukturen
 * 
 */

public class MySQLConnection {

	private static Connection conn = null;

	// Hostname
	private static String dbHost = DBUser.dbHost;

	// Port -- Standard: 3306
	private static String dbPort = DBUser.dbPort;

	// Datenbankname
	private static String database = DBUser.database;

	// Datenbankuser
	private static String dbUser = DBUser.dbUser;

	// Datenbankpasswort
	private static String dbPassword = DBUser.dbPassword;

	private MySQLConnection() {
		try {

			// Datenbanktreiber fuer ODBC Schnittstellen laden.
			// Fuer verschiedene ODBC-Datenbanken muss dieser Treiber
			// nur einmal geladen werden.
			Class.forName("com.mysql.jdbc.Driver");

			// Verbindung zur ODBC-Datenbank herstellen.
			// Es wird die JDBC-ODBC-Brueke verwendet.
			conn = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":"
					+ dbPort + "/" + database + "?" + "user=" + dbUser + "&"
					+ "password=" + dbPassword);
		} catch (ClassNotFoundException e) {
			System.out.println("Treiber nicht gefunden");
		} catch (SQLException e) {
			System.out.println("Connect nicht moeglich");
			JOptionPane.showMessageDialog(null, "Datenbank nicht verfügbar!");
		}
	}

	private static Connection getInstance() {
		if (conn == null)
			new MySQLConnection();
		return conn;
	}

	public static Vector<Wine> getWineContent() {
		Vector<Wine> wineVector = new Vector<Wine>();
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			Statement query;
			try {
				query = conn.createStatement();

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT pd.entity_id, name, price, manufacturer, manufacturer_value, vdp, taste, taste_value, grape, at.attribute_id, at.value "
						+ "FROM catalog_product_flat_1 AS pd "
						+ "INNER JOIN (SELECT entity_id, attribute_id, value "
						+ "FROM catalog_product_entity_varchar "
						+ "WHERE attribute_id = 140 OR attribute_id = 141 OR attribute_id = 142 OR attribute_id = 160 "
						+ "UNION SELECT entity_id, attribute_id, value "
						+ "FROM catalog_product_entity_int "
						+ "WHERE attribute_id = 135 OR attribute_id = 138 OR attribute_id = 166 OR attribute_id = 203) AS at ON pd.entity_id = at.entity_id "
						+ "ORDER BY pd.entity_id, at.attribute_id";
				ResultSet result = query.executeQuery(sql);

				// Ergebnissaetze durchfahren.
				while (result.next()) {
					// Datenstruktur
					Wine tmp = new Wine();

					// Defaultwerte fuer switch Attribute
					int quality = -1;
					int region = -1;
					double alcohol = -1;
					double acid = -1;
					double sweetness = -1;
					int wineStyle = -1;
					int year = -1;
					int aroma[] = null;

					// Weinobjekt fuellen + zum wineVektor hinzufuegen
					int wineID = result.getInt("pd.entity_id");

					// ID
					Wine tmpWine = searchWine(wineVector, wineID);
					// Wein hinzufuegen, wenn noch nicht vorhanden
					if (tmpWine == null) {

						// Name
						String name = result.getString("name");
						// Preis
						double price = result.getDouble("price");
						// Weingut
						int winery = result.getInt("manufacturer");
						// VDP
						int vdp = result.getInt("vdp");
						// Geschmack
						int taste = result.getInt("taste");
						// Rebsorte/n
						String grapeString = result.getString("grape");
						String grapeStringArray[] = grapeString.split(",");
						int grape[] = new int[grapeStringArray.length];
						for (int i = 0; i < grapeStringArray.length; i++) {
							grape[i] = Integer.parseInt(grapeStringArray[i]);
						}
						// Jahr
						try {
							year = Integer.parseInt(name.split(" |/")[0]);
						} catch (NumberFormatException e) {
						}

						tmp.setId(wineID);
						tmp.setName(name);
						tmp.setPrice(price);
						tmp.setTaste(taste);
						tmp.setVdp(vdp);
						tmp.setWinery(winery);
						tmp.setGrape(grape);
						tmp.setYear(year);
						wineVector.add(tmp);
					}
					// zusaetzliche EAV-Attribute hinzufuegen
					switch (result.getInt("at.attribute_id")) {
					// Qualitaet
					case 135:
						quality = result.getInt("value");
						break;
					// Region
					case 138:
						region = result.getInt("value");
						break;
					// Alkohol
					case 140:
						alcohol = getDouble(result);
						break;
					// Saeure
					case 141:
						acid = getDouble(result);
						break;
					// Restzucker
					case 142:
						sweetness = getDouble(result);
						break;
					// Aroma
					case 160:
						String aromaString = result.getString("value");
						if (aromaString != null) {
							String aromaStringArray[] = aromaString.split(",");
							aroma = new int[aromaStringArray.length];
							for (int i = 0; i < aromaStringArray.length; i++) {
								aroma[i] = Integer
										.parseInt(aromaStringArray[i]);
							}
						}
						break;
					// Weinstil
					case 166:
						wineStyle = result.getInt("value");
						break;
					// Jahr
					case 203:
						// Erst sinnvoll, wenn Jahr wirklich als Attribut
						// eingetragen wird! Bisher Jahr fast immer nur im Namen
						// drin
						// year = result.getInt("value");
					default:
					}
					if (quality != -1 && quality != 0) {
						tmp.setQuality(quality);
					}
					if (region != -1) {
						tmpWine.setRegion(region);
					}
					if (alcohol != -1 && alcohol != 0) {
						tmpWine.setAlcohol(alcohol);
					}
					if (acid != -1 && acid != 0) {
						tmpWine.setAcid(acid);
					}
					if (sweetness != -1) {
						// falls nicht vorhanden mittig in Geschmack einordnen
						if (sweetness == 0) {
							switch (tmpWine.getTaste()) {
							// trocken
							case (15):
								sweetness = 6;
								break;
							// halbtrocken
							case (23):
								sweetness = 12;
								break;
							// feinherb
							case (119):
								sweetness = 20;
								break;
							// lieblich
							case (21):
								sweetness = 60;
								break;
							// edelsuess
							case (20):
								sweetness = 80;
								break;
							}
						}
						tmpWine.setSweetness(sweetness);
					}
					if (wineStyle != -1 && wineStyle != 0) {
						tmpWine.setWineStyle(wineStyle);
					}
					if (year != -1) {
						tmp.setYear(year);
					}
					if (aroma != null) {
						tmpWine.setAroma(aroma);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return wineVector;
	}

	// Notwendig wegen unterschiedlicher Darstellung (z.B.: "4.3", "4,3",
	// "4.3 %", etc)
	private static double getDouble(ResultSet result) throws SQLException {
		double returnDouble;
		String alcoholString = result.getString("value");
		try {
			returnDouble = Double.parseDouble(alcoholString);
		} catch (NumberFormatException e1) {
			try {
				String returnStringArray[] = result.getString("value").split(
						Pattern.quote(" "));
				returnDouble = Double.parseDouble(returnStringArray[0]);
			} catch (NumberFormatException e2) {
				String returnStringArray[] = result.getString("value").split(
						Pattern.quote(","));
				returnDouble = Double.parseDouble(returnStringArray[0] + "."
						+ returnStringArray[1]);
			}
		}
		return returnDouble;
	}

	// Suche nach Wein mit Hilfe der ID
	private static Wine searchWine(Vector<Wine> wineVector, int id) {
		Wine result = null;

		for (int i = 0; i < wineVector.size(); i++) {
			Wine tmp = wineVector.elementAt(i);
			if (tmp.getId() == id)
				result = tmp;
		}

		return result;
	}
}
