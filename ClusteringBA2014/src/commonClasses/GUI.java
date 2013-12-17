package commonClasses;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import commonClasses.Wine;
import similarityMeasures.SimilarityList;

/*
 * Startet die graphische Oberflaeche
 * 
 *
 */
public class GUI {
	private static JComboBox<Wine> wineDropDown;
	private static final Vector<Wine> wineList = SimilarityList.getWineList();

	public GUI(int width) {

		JFrame frame = new JFrame("Cluteranalyse-Verfahren");
		frame.setSize((int) (1.42 * width), (int) (width * 0.85));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.lightGray);

		// Allgemeine Beschriftung
		JLabel titelText = new JLabel("Eigenschaften");
		titelText.setBounds((int) (width * 0.06), (int) (width * 0.02),
				200, 20);
		titelText.setFont(new Font("Arial", Font.BOLD, 14));
		panel.add(titelText);
		
		JLabel wineText = new JLabel("2 Eigenschaften auswählen");
		wineText.setBounds((int) (width * 0.025), (int) (width * 0.07),
				200, 20);
		panel.add(wineText);
		
		// DropDownBox von Weinen
		wineDropDown = new JComboBox<Wine>(wineList);
		wineDropDown.setBounds((int) (width * 0.34), (int) (width * 0.02),
				(int) (width * 0.3), 20);
		panel.add(wineDropDown);

		// Buttons für alle Eigenschaften
		final Vector<JButton> propertiesButtonList = new Vector<JButton>();
		final Color ownRed = new Color(211, 171, 170);
		final Color ownGreen = new Color(208, 219, 178);

		final JButton alcoholButton = new JButton("Alcohol");
		final JButton aromaButton = new JButton("Aroma");
		final JButton yearButton = new JButton("Jahr");
		final JButton priceButton = new JButton("Preis");
		final JButton qualityButton = new JButton("Qualität");
		final JButton grapeButton = new JButton("Rebsorte");
		final JButton regionButton = new JButton("Region");
		final JButton sweetnessButton = new JButton("Restzucker");
		final JButton acidButton = new JButton("Säure");
		final JButton vdpButton = new JButton("VDP");
		final JButton wineryButton = new JButton("Weingut");
		final JButton winestyleButton = new JButton("Weinstil");

		propertiesButtonList.add(alcoholButton);
		propertiesButtonList.add(aromaButton);
		propertiesButtonList.add(yearButton);
		propertiesButtonList.add(priceButton);
		propertiesButtonList.add(qualityButton);
		propertiesButtonList.add(grapeButton);
		propertiesButtonList.add(regionButton);
		propertiesButtonList.add(sweetnessButton);
		propertiesButtonList.add(acidButton);
		propertiesButtonList.add(vdpButton);
		propertiesButtonList.add(wineryButton);
		propertiesButtonList.add(winestyleButton);

		for (int i = 0; i < propertiesButtonList.size(); i++) {
			JButton tmpPropertieButton = propertiesButtonList.elementAt(i);
			tmpPropertieButton.setBackground(ownRed);
			tmpPropertieButton.setBounds((int) (width * 0.02),
					(int) (width * 0.1 + width * 0.05 * i),
					(int) (width * 0.2), (int) (width * 0.04));
			panel.add(tmpPropertieButton);
		}
		/*
		 * 
		 * Aktionlistener
		 */

		// Aktionlistener fuer wineDropDown
		wineDropDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Wine tmp = search(wineList, getCurrentWine().getId());
			}
		});

		// Aktionlistener fuer alle Eigenschafts-Button (höchstens zwei können
		// an sein)
		final int maxClickedButtons = 2;
		final int clicked = 0;
		for (int i = 0; i < propertiesButtonList.size(); i++) {
			final JButton tmpPropertieButton = propertiesButtonList
					.elementAt(i);
			tmpPropertieButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (tmpPropertieButton.getBackground() == ownRed) {
						tmpPropertieButton.setBackground(ownGreen);
					} else {
						tmpPropertieButton.setBackground(ownRed);
					}
				}
			});
		}

		// Mittelstrich
		JPanel drawPanel = new JPanel();
		drawPanel.setLayout(null);
		drawPanel.setBounds((int) (width * 0.7), (int) (width * 0.02), 1,
				(int) (width * 0.75));
		drawPanel.setBackground(Color.GRAY);
		drawPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		panel.add(drawPanel);

		// Bilder zeichnen
		ImageIcon image = new ImageIcon("uniTrierKreisLogo5%.png");
		image.setImage(image.getImage().getScaledInstance(700, 700,
				Image.SCALE_DEFAULT));
		JLabel uniTrierKreisLogo = new JLabel(image);
		uniTrierKreisLogo.setBounds(-100, -100, 700, 700);
		panel.add(uniTrierKreisLogo);

		ImageIcon image1 = new ImageIcon("uni-logo.png");
		image1.setImage(image1.getImage().getScaledInstance(300, 50,
				Image.SCALE_DEFAULT));
		JLabel uniTrierLogo = new JLabel(image1);
		uniTrierLogo.setBounds((int) (width * 0.02), (int) (width * 0.73), 300,
				50);
		panel.add(uniTrierLogo);

		ImageIcon image2 = new ImageIcon("weine.de.png");
		image2.setImage(image2.getImage().getScaledInstance(300, 80,
				Image.SCALE_DEFAULT));
		JLabel weineLogo = new JLabel(image2);
		weineLogo
				.setBounds((int) (width * 1.05), (int) (width * 0.71), 300, 80);
		panel.add(weineLogo);

		frame.add(panel);
		frame.setVisible(true);
	}

	public static Wine getCurrentWine() {
		return (Wine) wineDropDown.getSelectedItem();
	}

	// Suche nach Wein mit Hilfe der ID
	private static Wine search(Vector<Wine> wineVector, int id) {
		Wine result = null;

		for (int i = 0; i < wineVector.size(); i++) {
			Wine tmp = wineVector.elementAt(i);
			if (tmp.getId() == id)
				result = tmp;
		}

		return result;
	}

	public static JComboBox<Wine> getWineDropDown() {
		return wineDropDown;
	}

	public static Vector<Wine> getWineList() {
		return wineList;
	}
}
