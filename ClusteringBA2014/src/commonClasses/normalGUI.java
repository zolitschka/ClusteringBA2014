package commonClasses;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import commonClasses.Wine;
import similarityMeasures.SimilarityList;
import database.MySQLConnection;

/*
 * Graphische Oberflaeche
 * 
 * GUI-Methoden:
 * aktuellen User (als String): gui.getCurrentUser()
 * aktuellen Wein (als Wine): gui.getCurrentWine()
 *
 */
public class normalGUI {
	private static JComboBox<User> userDropDown;
	private static JComboBox<Wine> wineDropDown;
	private static final Vector<Wine> wineList = SimilarityList.getWineList();
	private static final Vector<User> userList = MySQLConnection.getUser();
	RecCreationKaufhistorieWarenkorb CRBuyHistory;
	SvdppRec SvdRecommender;
	Vector<Wine> svdList;
	Vector<Wine> buyHistoryList;
	Vector<Wine> collHybList;
	Vector<Wine> normalContentList;

	public normalGUI(int width) {

		JFrame frame = new JFrame("Intelligente Weinempfehlung");
		frame.setSize((int) (1.42 * width), (int) (width * 0.85));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.lightGray);

		// Allgemeine Beschriftung
		JLabel titelText = new JLabel("Einstellungen");
		titelText.setBounds((int) (width * 0.33 - 45), (int) (width * 0.02),
				200, 20);
		titelText.setFont(new Font("Arial", Font.BOLD, 14));
		panel.add(titelText);

		JLabel normalText = new JLabel("Content Empfehlung");
		normalText.setBounds((int) (width * 0.33 - 70), (int) (width * 0.31),
				200, 20);
		normalText.setFont(new Font("Arial", Font.BOLD, 15));
		panel.add(normalText);

		JLabel orderText = new JLabel("Collaborative Empfehlung");
		orderText
				.setBounds((int) (width * 0.96), (int) (width * 0.02), 200, 20);
		orderText.setFont(new Font("Arial", Font.BOLD, 15));
		panel.add(orderText);

		// DropDownBox von Usern
		JLabel userText = new JLabel("User auswählen");
		userText.setBounds((int) (width * 0.17 - 50), (int) (width * 0.07),
				200, 20);
		panel.add(userText);

		userDropDown = new JComboBox<User>(userList);
		userDropDown.setBounds((int) (width * 0.02), (int) (width * 0.1),
				(int) (width * 0.3), 20);
		panel.add(userDropDown);

		new SimilarityList(getCurrentUser());

		// DropDownBox von Weinen
		JLabel wineText = new JLabel("Wein auswählen");
		wineText.setBounds((int) (width * 0.49 - 50), (int) (width * 0.07),
				200, 20);
		panel.add(wineText);

		wineDropDown = new JComboBox<Wine>(wineList);
		wineDropDown.setBounds((int) (width * 0.34), (int) (width * 0.1),
				(int) (width * 0.3), 20);
		panel.add(wineDropDown);

		// ScrollBox mit normalen Content-Based Empfehlungen
		final JPanel normalContentPanel = new JPanel();

		JScrollPane normalContentScrollPane = new JScrollPane(
				normalContentPanel);

		normalContentScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		normalContentScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		normalContentPanel.setLayout(null);
		normalContentScrollPane.setBounds((int) (width * 0.18),
				(int) (width * 0.35), (int) (width * 0.3), (int) (width * 0.2));
		normalContentPanel.setBackground(Color.GRAY);
		normalContentPanel.setBorder(BorderFactory
				.createLineBorder(Color.BLACK));

		// normale content Empfehlungsliste
		Wine tmp = search(wineList, getCurrentWine().getId());
		normalContentList = tmp.getSimilarityList();
		paintPanel(normalContentList, normalContentPanel, "content");

		panel.add(normalContentScrollPane);

		// ScrollBox mit Kaufhistorie Collaborativen Empfehlungen
		JLabel normalCollaborativeText = new JLabel(
				"Kaufhistorien Empfehlungen");
		normalCollaborativeText.setBounds((int) (width * 0.91 - 85),
				(int) (width * 0.07), 200, 20);
		panel.add(normalCollaborativeText);

		final JPanel normalCollaborativePanel = new JPanel();
		JScrollPane normalCollaborativeScrollPane = new JScrollPane(
				normalCollaborativePanel);
		normalCollaborativeScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		normalCollaborativeScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		normalCollaborativePanel.setLayout(null);
		normalCollaborativeScrollPane.setBounds((int) (width * 0.76),
				(int) (width * 0.1), (int) (width * 0.3), (int) (width * 0.2));
		normalCollaborativePanel.setBackground(Color.GRAY);
		normalCollaborativePanel.setBorder(BorderFactory
				.createLineBorder(Color.BLACK));

		// normale collaborativen Kaufhistorie Empfehlungsliste
		CRBuyHistory = new RecCreationKaufhistorieWarenkorb(getCurrentUser()
				.getId());
		buyHistoryList = CRBuyHistory.createRecBuyHistory();
		paintPanel(buyHistoryList, normalCollaborativePanel, "collaborative");

		panel.add(normalCollaborativeScrollPane);

		// ScrollBox mit SVD Collaborativen Empfehlungen
		JLabel svdCollaborativeText = new JLabel("SVD++ Empfehlungen");
		svdCollaborativeText.setBounds((int) (width * 1.23 - 70),
				(int) (width * 0.07), 200, 20);
		panel.add(svdCollaborativeText);

		final JPanel svdCollaborativePanel = new JPanel();
		JScrollPane svdCollaborativeScrollPane = new JScrollPane(
				svdCollaborativePanel);
		svdCollaborativeScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		svdCollaborativeScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		svdCollaborativePanel.setLayout(null);
		svdCollaborativeScrollPane.setBounds((int) (width * 1.08),
				(int) (width * 0.1), (int) (width * 0.3), (int) (width * 0.2));
		svdCollaborativePanel.setBackground(Color.GRAY);
		svdCollaborativePanel.setBorder(BorderFactory
				.createLineBorder(Color.BLACK));

		// Empfehlungsliste
		SvdRecommender = new SvdppRec();
		svdList = SvdRecommender.recommend((long) getCurrentUser().getId(), 10);
		paintPanel(svdList, svdCollaborativePanel, "svdpp");

		panel.add(svdCollaborativeScrollPane);

		// ScrollBox mit Collaborativen Hybriden Empfehlungen
		JLabel colHybridText = new JLabel("Hybride Collaborative Empfehlungen");
		colHybridText.setBounds((int) (width * 1.08 - 105),
				(int) (width * 0.32), 250, 20);
		panel.add(colHybridText);

		final JPanel colHybridPanel = new JPanel();
		JScrollPane colHybridScrollPane = new JScrollPane(colHybridPanel);
		colHybridScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		colHybridScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		colHybridPanel.setLayout(null);
		colHybridScrollPane.setBounds((int) (width * 0.92),
				(int) (width * 0.35), (int) (width * 0.3), (int) (width * 0.2));
		colHybridPanel.setBackground(Color.GRAY);
		colHybridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// Empfehlungsliste
		collHybList = Hybrid
				.collSwitch(svdList, buyHistoryList, CRBuyHistory
						.getCurrentBuyHistorie(getCurrentUser().getId()).wine);
		paintPanel(collHybList, colHybridPanel, "gemischt");

		panel.add(colHybridScrollPane);

		// ScrollBox mit normalen Hybriden Empfehlungen
		JLabel normalHybridText = new JLabel("Hybride Empfehlungen");
		normalHybridText.setFont(new Font("Arial", Font.BOLD, 15));
		normalHybridText.setBounds((int) (width * 0.69 - 65),
				(int) (width * 0.55), 200, 20);
		panel.add(normalHybridText);

		final JPanel normalHybridPanel = new JPanel();
		JScrollPane normalHybridScrollPane = new JScrollPane(normalHybridPanel);
		normalHybridScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		normalHybridScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		normalHybridPanel.setLayout(null);
		normalHybridScrollPane.setBounds((int) (width * 0.56),
				(int) (width * 0.59), (int) (width * 0.3), (int) (width * 0.2));
		normalHybridPanel.setBackground(Color.GRAY);
		normalHybridPanel
				.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// wineList2 ersetzen mit der normalen hybriden Empfehlungsliste

		paintPanel(Hybrid.normalHybrid(normalContentList, collHybList),
				normalHybridPanel, "gemischt");

		panel.add(normalHybridScrollPane);

		// Preferenzprofil Button
		final Color ownRed = new Color(211, 171, 170);
		final Color ownGreen = new Color(208, 219, 178);
		final JButton preferenceProfilButton = new JButton(
				"Preferenceprofil off");
		preferenceProfilButton.setBackground(ownRed);
		preferenceProfilButton.setBounds((int) (width * 0.02),
				(int) (width * 0.2), (int) (width * 0.3), (int) (width * 0.04));
		// panel.add(preferenceProfilButton);

		/*
		 * 
		 * Aktionlistener
		 */

		// Aktionlistener fuer wineDropDown
		wineDropDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Wine tmp = search(wineList, getCurrentWine().getId());
				normalContentList = tmp.getSimilarityList();
				collHybList = Hybrid.collSwitch(svdList, buyHistoryList,
						CRBuyHistory.getCurrentBuyHistorie(getCurrentUser()
								.getId()).wine);
				paintPanel(Hybrid.normalHybrid(normalContentList, collHybList),
						normalHybridPanel, "gemischt");
				paintPanel(normalContentList, normalContentPanel, "content");
				normalContentPanel.repaint();
				normalHybridPanel.repaint();
			}
		});
		// Aktionlistener fuer userDropDown
		userDropDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// new SimilarityList(getCurrentUser());
				refreshCollLists();
				paintPanel(svdList, svdCollaborativePanel, "svdpp");
				paintPanel(buyHistoryList, normalCollaborativePanel,
						"collaborative");
				collHybList = Hybrid.collSwitch(svdList, buyHistoryList,
						CRBuyHistory.getCurrentBuyHistorie(getCurrentUser()
								.getId()).wine);
				paintPanel(collHybList, colHybridPanel, "gemischt");
				paintPanel(Hybrid.normalHybrid(normalContentList, collHybList),
						normalHybridPanel, "gemischt");
				normalHybridPanel.repaint();
				normalCollaborativePanel.repaint();
				svdCollaborativePanel.repaint();
				colHybridPanel.repaint();
			}
		});
		// Aktionlistener fuer PreferenceProfilButton
		preferenceProfilButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (preferenceProfilButton.getBackground() == ownRed) {
					preferenceProfilButton.setBackground(ownGreen);
					preferenceProfilButton.setText("Preferenceprofil on");
					Preference.setPreferenceBoolean(true);
				} else {
					preferenceProfilButton.setBackground(ownRed);
					preferenceProfilButton.setText("Preferenceprofil off");
					Preference.setPreferenceBoolean(false);
				}
				// new SimilarityList(getCurrentUser());
				// Wine tmp = search(wineList, getCurrentWine().getId());
				// normalContentList = tmp.getSimilarityList();
				// paintPanel(normalContentList, normalContentPanel, "content");
				// paintPanel(Hybrid.normalHybrid(normalContentList,
				// collHybList),
				// normalHybridPanel, "gemischt");
				// normalHybridPanel.repaint();
			}
		});

		// Mittelstrich
		JPanel drawPanel = new JPanel();
		drawPanel.setLayout(null);
		drawPanel.setBounds((int) (width * 0.7), (int) (width * 0.02), 1,
				(int) (width * 0.52));
		drawPanel.setBackground(Color.GRAY);
		drawPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		panel.add(drawPanel);

		// Seitenstrich
		JPanel drawPanel2 = new JPanel();
		drawPanel2.setLayout(null);
		drawPanel2.setBounds((int) (width * 0.02), (int) (width * 0.28),
				(int) (width * 0.62), 1);
		drawPanel2.setBackground(Color.GRAY);
		drawPanel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		panel.add(drawPanel2);

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

	// Zeichnet die Empfehlungs-Vektoren
	private void paintPanel(final Vector<Wine> vector, JPanel panel,
			String source) {
		panel.removeAll();
		DecimalFormat f = new DecimalFormat("#0.00");
		int y;
		y = 0;
		JLabel tmp = null;
		if (vector.size() == 0) {
			tmp = new JLabel("Keine Empfehlung möglich ...");
			tmp.setBounds(5, y, 400, 20);
			panel.add(tmp);
		} else {
			if (source.equals("collaborative")
					&& vector.elementAt(0).getWineScore() == 0.0) {
				tmp = new JLabel("Keine Empfehlung möglich ...");
				tmp.setBounds(5, y, 400, 20);
				panel.add(tmp);
			} else {
				for (int i = 0; i < vector.size(); i++) {
					Wine tmpWine = vector.elementAt(i);
					if (source.equals("normal")) {
						tmp = new JLabel(tmpWine.toString());
					}
					if (source.equals("content")) {
						tmp = new JLabel(tmpWine.getId() + ": ("
								+ tmpWine.getSimilarity() + " %) "
								+ tmpWine.getName());
					}
					if (source.equals("collaborative")) {
						tmp = new JLabel(tmpWine.getId() + ": (WS: "
								+ f.format(tmpWine.getWineScore()) + ") "
								+ tmpWine.getName());
					}
					if (source.equals("svdpp")) {
						tmp = new JLabel(tmpWine.getId() + ": (B: "
								+ f.format(tmpWine.getRating()) + ") "
								+ tmpWine.getName());
					}

					if (source.equals("gemischt")) {

						if (tmpWine.isContent()) {
							tmp = new JLabel(tmpWine.getId() + ": ("
									+ tmpWine.getSimilarity() + " %) "
									+ tmpWine.getName());
						}
						if (tmpWine.isKaufhistorie()) {
							tmp = new JLabel(tmpWine.getId() + ": (WS: "
									+ f.format(tmpWine.getWineScore()) + ") "
									+ tmpWine.getName());
						}
						if (tmpWine.isSvd()) {
							tmp = new JLabel(tmpWine.getId() + ": (B: "
									+ f.format(tmpWine.getRating()) + ") "
									+ tmpWine.getName());
						}

					}
					tmp.setBounds(5, y, 400, 20);
					y += 20;
					panel.add(tmp);
				}
			}
		}
		if (y > 170) {
			panel.setPreferredSize(new Dimension(400, y));
		} else {
			panel.setPreferredSize(new Dimension(400, 200));
		}

	}

	public static User getCurrentUser() {
		return (User) userDropDown.getSelectedItem();
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

	public static JComboBox<User> getUserDropDown() {
		return userDropDown;
	}

	public static JComboBox<Wine> getWineDropDown() {
		return wineDropDown;
	}

	public static Vector<Wine> getWineList() {
		return wineList;
	}

	public static Vector<User> getUserList() {
		return userList;
	}

	private void refreshCollLists() {
		svdList = SvdRecommender.recommend((long) getCurrentUser().getId(), 10);
		CRBuyHistory = new RecCreationKaufhistorieWarenkorb(getCurrentUser()
				.getId());
		buyHistoryList = CRBuyHistory.createRecBuyHistory();
	}
}
