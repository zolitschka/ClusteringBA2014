package database;

import java.util.Vector;

import commonClasses.Wine;

/*
 * 
 * Diese Klasse ist ein Singleton-Objekt und liefert eine Weinliste, wenn sie existiert, ansonsten wird sie erzeugt.
 * Vorteil: Es wird nur einmal auf die Datenbank zugegriffen (Performance-Vorteil)
 * 
 */

public class Lists {
	private static Vector<Wine> wineList = null;

	public static Vector<Wine> getWineList() {
		if (wineList == null) {
			wineList = MySQLConnection.getWineList();
		}
		return wineList;
	}
}
