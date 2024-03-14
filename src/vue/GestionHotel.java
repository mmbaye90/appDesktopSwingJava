package vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Components.Button;
import Components.TextField;
import dao.HotelDao;
import dao.SocieteDao;
import entites.Db;
import entites.Hotel;
import javax.swing.JTextArea;


public class GestionHotel extends JInternalFrame {

	//============================ VARIABLES ====================================
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TextField chmpIdSociete;
	private TextField idSociete;
	private TextField nomHotel;
	private TextField adHotel;
	private TextField villeHotel;
	private TextField descHotel;
	private TextField parkingHotel;
	private TextField wifiHotel;
	private TextField horArrive;
	private TextField horDepart;
	private TextField piscineHotel;
	private TextField navHotel;
	private TextField animalHotel;
	private TextField cat;
	private TextField idHotel;
	private String wordCherched;
	private Button btnAjouter;
	private Button btnModifier;
	private Button btnSuppriemr;
	
	TextField champRech;
	DefaultTableModel model;
	JTable table;
	private JLabel lblNewLabel;
	private JLabel lblNom;
	private JLabel lblAdresse;
	private JLabel lblVille;
	private JLabel lblDescription;
	private JLabel lblCat;
	private JLabel lblHa;
	private JLabel lblHd;
	private JLabel lblPk;
	private JLabel lblWifi;
	private JLabel lblPisc;
	private JLabel lblNav;
	private JLabel lblAnim;
	private JLabel lblIh;
	private JLabel lblIs;
	private JLabel lblIs_1;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionHotel frame = new GestionHotel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestionHotel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 762, 464);
		//Enlever la décoration de la fenetre JinternalFrame
		putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(0,101,252));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Titre
		JLabel titre = new JLabel("GESTION DES HOTELS");
		titre.setForeground(SystemColor.textHighlightText);
		titre.setFont(new Font("Tahoma", Font.BOLD, 16));
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setBounds(10, 25, 746, 28);
		
		//===================  LOGO  =====================================
		//Chargement Logo et configuration Style
        ImageIcon iconlogo = new ImageIcon(this.getClass().getResource("/logo.png"));
        Image logo = iconlogo.getImage().getScaledInstance(55,70, Image.SCALE_DEFAULT);

		JLabel contLogo = new JLabel(new ImageIcon(logo));
		contLogo.setHorizontalAlignment(SwingConstants.CENTER);
		contLogo.setBounds(10, 11, 27,28);
		
		
		//Ajout ChampRecherche
		champRech = new TextField();
		champRech.setForeground(SystemColor.textHighlightText);
		champRech.setFont(new Font("Tahoma", Font.BOLD, 16));
		champRech.setHorizontalAlignment(SwingConstants.CENTER);
		champRech.setBounds(20, 64, 149, 40);
		
		//Ajout Fonctionnalité de la recherche
		//========================= Fonctionnalités Recherche ===================
		champRech.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateFields();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateFields();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateFields();
            }

        });
		
		
		//Ajout Table
		Db.connect();
		ArrayList<Hotel> hotel = new HotelDao().getAll();
		String columns[] = { "ID", "Societe", "NomHotel","Adhotel","VilleH","DescHo","ParkH","wifiH","HarrHot","HdepHot","Piscine","NavHot","Animal","Cat"};
		String[][] data = new String[hotel.size()][columns.length];
		


		int i = 0;
		for (Hotel s : hotel) {
			data[i][0] = s.getId_hotel() + "";
			data[i][1] = new SocieteDao().getById(s.getId_soc()).getNom();
			data[i][2] = s.getNomH();
			data[i][3] = s.getAdresseH();
			data[i][4] = s.getVilleH();
			data[i][5] = s.getDescriptionH();
			data[i][6] = s.getParkingH();
			data[i][7] = s.getWifiH();
			data[i][8] = s.getHeureAaH();
			data[i][9] = s.getHeureDepH();
			data[i][10] = s.getPiscineH();
			data[i][11] = s.getNavetteH();
			data[i][12] = s.getPresenceAniH();
			if (s.getCatH() ==1) data[i][13] = "*";
			else if(s.getCatH() ==2)  data[i][13] = "**"; 
			else if(s.getCatH() ==3)  data[i][13] = "***"; 
			else if(s.getCatH() ==4)  data[i][13] = "****"; 
			else if(s.getCatH() ==5)  data[i][13] = "*****"; 
			i++;
		}

		model = new DefaultTableModel(data,columns);
		table = new JTable(model);
		//table.setRowHeight(50);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 115, 726, 113);
		getContentPane().add(scrollPane);
		
		//======================================== Ajout des Champs Text ============
		//-----id_societe fremplace par le nom de la societé
		idSociete = new TextField();
		idSociete.setForeground(SystemColor.textHighlightText);
		idSociete.setFont(new Font("Tahoma", Font.BOLD, 16));
		idSociete.setHorizontalAlignment(SwingConstants.CENTER);
		idSociete.setBounds(188, 389, 46, 34);
		contentPane.add(idSociete);
		idSociete.setVisible(false);
		idSociete.setVisible(false);
		
		
		//-----nomHotel
		nomHotel = new TextField();
		nomHotel.setForeground(SystemColor.textHighlightText);
		nomHotel.setFont(new Font("Tahoma", Font.BOLD, 16));
		nomHotel.setHorizontalAlignment(SwingConstants.CENTER);
		nomHotel.setBounds(48, 263, 108, 34);
		contentPane.add(nomHotel);

		//-----adHotel
		adHotel = new TextField();
		adHotel.setForeground(SystemColor.textHighlightText);
		adHotel.setFont(new Font("Tahoma", Font.BOLD, 16));
		adHotel.setHorizontalAlignment(SwingConstants.CENTER);
		adHotel.setBounds(181, 263, 173, 34);
		contentPane.add(adHotel);
		
		
		//villeHotel
		villeHotel = new TextField();
		villeHotel.setForeground(SystemColor.textHighlightText);
		villeHotel.setFont(new Font("Tahoma", Font.BOLD, 16));
		villeHotel.setHorizontalAlignment(SwingConstants.CENTER);
		villeHotel.setBounds(377, 263, 95, 34);
		contentPane.add(villeHotel);
	
		//descHotel
		descHotel =new TextField();
		descHotel.setForeground(SystemColor.textHighlightText);
		descHotel.setFont(new Font("Tahoma", Font.BOLD, 16));
		descHotel.setHorizontalAlignment(SwingConstants.CENTER);
		descHotel.setBounds(488, 263, 231, 94);
		contentPane.add(descHotel);
		
		//parkingHotel
		parkingHotel =new TextField();
		parkingHotel.setForeground(SystemColor.textHighlightText);
		parkingHotel.setFont(new Font("Tahoma", Font.BOLD, 16));
		parkingHotel.setHorizontalAlignment(SwingConstants.CENTER);
		parkingHotel.setBounds(244, 323, 46, 34);
		contentPane.add(parkingHotel);
		
		//wifiHotel
		wifiHotel =new TextField();
		wifiHotel.setForeground(SystemColor.textHighlightText);
		wifiHotel.setFont(new Font("Tahoma", Font.BOLD, 16));
		wifiHotel.setHorizontalAlignment(SwingConstants.CENTER);
		wifiHotel.setBounds(300, 323, 46, 34);
		contentPane.add(wifiHotel);
		
		//horArrive
		horArrive =new TextField();
		horArrive.setForeground(SystemColor.textHighlightText);
		horArrive.setFont(new Font("Tahoma", Font.BOLD, 16));
		horArrive.setHorizontalAlignment(SwingConstants.CENTER);
		horArrive.setBounds(110, 323, 46, 34);
		contentPane.add(horArrive);
		
		//horDep
		horDepart =new TextField();
		horDepart.setForeground(SystemColor.textHighlightText);
		horDepart.setFont(new Font("Tahoma", Font.BOLD, 16));
		horDepart.setHorizontalAlignment(SwingConstants.CENTER);
		horDepart.setBounds(188, 323, 46, 34);
		contentPane.add(horDepart);
		
		//piscineHotel
		piscineHotel =new TextField();
		piscineHotel.setForeground(SystemColor.textHighlightText);
		piscineHotel.setFont(new Font("Tahoma", Font.BOLD, 16));
		piscineHotel.setHorizontalAlignment(SwingConstants.CENTER);
		piscineHotel.setBounds(377, 323, 46, 34);
		contentPane.add(piscineHotel);
		
		//navHotel		
		navHotel =new TextField();
		navHotel.setForeground(SystemColor.textHighlightText);
		navHotel.setFont(new Font("Tahoma", Font.BOLD, 16));
		navHotel.setHorizontalAlignment(SwingConstants.CENTER);
		navHotel.setBounds(433, 323, 46, 34);
		contentPane.add(navHotel);
		
		//animalHotel		
		animalHotel =new TextField();
		animalHotel.setForeground(SystemColor.textHighlightText);
		animalHotel.setFont(new Font("Tahoma", Font.BOLD, 16));
		animalHotel.setHorizontalAlignment(SwingConstants.CENTER);
		animalHotel.setBounds(48, 389, 46, 34);
		contentPane.add(animalHotel);
		
		//idHotel pour récuperer l'objet correspondant à cet id	
		idHotel =new TextField();
		idHotel.setForeground(SystemColor.textHighlightText);
		idHotel.setFont(new Font("Tahoma", Font.BOLD, 16));
		idHotel.setHorizontalAlignment(SwingConstants.CENTER);
		idHotel.setBounds(110, 389, 46, 34);
		contentPane.add(idHotel);
		idHotel.setVisible(false);
		
		
		//chmpIdSociete pour recuperer l'id de la societé correspondant
		chmpIdSociete =new TextField();
		chmpIdSociete.setForeground(SystemColor.textHighlightText);
		chmpIdSociete.setFont(new Font("Tahoma", Font.BOLD, 16));
		chmpIdSociete.setHorizontalAlignment(SwingConstants.CENTER);
		chmpIdSociete.setBounds(244, 389, 46, 34);
		contentPane.add(chmpIdSociete);
		chmpIdSociete.setVisible(false);
		
		//cat
		
		cat =new TextField();
		cat.setForeground(SystemColor.textHighlightText);
		cat.setFont(new Font("Tahoma", Font.BOLD, 16));
		cat.setHorizontalAlignment(SwingConstants.CENTER);
		cat.setBounds(48, 323, 52, 34);
		contentPane.add(cat);
		
		//ajout Des elements
		contentPane.add(contLogo);
		contentPane.add(titre);
		contentPane.add(champRech);
		
		//======================== EVENT SELECTION TABLE ==============
		table.getSelectionModel().addListSelectionListener(e -> {
			int rowIndex = table.getSelectedRow();
			if (rowIndex != -1) {
				String idTableSelected = (String) table.getValueAt(rowIndex, 0);
				String nmSoc = (String) table.getValueAt(rowIndex, 1);
				String nomHote = (String) table.getValueAt(rowIndex, 2);
				String adHote = (String) table.getValueAt(rowIndex, 3);
				String villeHote = (String) table.getValueAt(rowIndex, 4);
				String descHote = (String) table.getValueAt(rowIndex, 5);
				String parkingHote = (String) table.getValueAt(rowIndex, 6);
				String wifiHote = (String) table.getValueAt(rowIndex, 7);
				String horArriv = (String) table.getValueAt(rowIndex, 8);
				String horDepar = (String) table.getValueAt(rowIndex, 9);
				String piscineHote = (String) table.getValueAt(rowIndex, 10);
				String navHote = (String) table.getValueAt(rowIndex, 11);
				String animalHote = (String) table.getValueAt(rowIndex, 12);
				String catH = (String) table.getValueAt(rowIndex, 13);
				
				//Les chmps
				idSociete.setText(nmSoc);
				nomHotel.setText(nomHote);
				adHotel.setText(adHote);
				villeHotel.setText(villeHote);
				descHotel.setText(descHote);
				parkingHotel.setText(parkingHote);
				wifiHotel.setText(wifiHote);
				horArrive.setText(horArriv);
				horDepart.setText(horDepar);
				piscineHotel.setText(piscineHote);
				navHotel.setText(navHote);
				animalHotel.setText(animalHote);
				idHotel.setText(idTableSelected);
				
				//Afficher le chiffre au lieu les etoiles sur le champ text
				int id_hot = Integer.parseInt(idHotel.getText());
				cat.setText(new HotelDao().getIntIdCatHotel(id_hot)+"");

				chmpIdSociete.setText(new HotelDao().getIdSocieteByHotel(id_hot)+"");
				
				btnAjouter.setEnabled(true);
				btnModifier.setEnabled(true);
				btnSuppriemr.setEnabled(true);
			} 
			else {
//				btnModifier.setEnabled(false);
//				btnSuppriemr.setEnabled(false);
			}
		});
		
		//=========================  BTNS =========================
		//------ Ajouter
		btnAjouter = new Button();
		btnAjouter.setText("Ajouter");
		btnAjouter.setForeground(SystemColor.textHighlightText);
		btnAjouter.setBackground(Color.BLACK);
		btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAjouter.setHorizontalAlignment(SwingConstants.CENTER);
		btnAjouter.setBounds(412, 76, 86, 28);
		contentPane.add(btnAjouter);
		//------ Modifier
		btnModifier = new Button();
		btnModifier.setText("Modifier");
		btnModifier.setForeground(SystemColor.textHighlightText);
		btnModifier.setBackground(Color.BLACK);
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnModifier.setHorizontalAlignment(SwingConstants.CENTER);
		btnModifier.setBounds(508, 76, 95, 28);
		contentPane.add(btnModifier);
		//Supp
		btnSuppriemr = new Button();
		btnSuppriemr.setText("Supprimer");
		btnSuppriemr.setForeground(SystemColor.textHighlightText);
		btnSuppriemr.setBackground(Color.BLACK);
		btnSuppriemr.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSuppriemr.setHorizontalAlignment(SwingConstants.CENTER);
		btnSuppriemr.setBounds(613, 76, 113, 28);
		contentPane.add(btnSuppriemr);
		
		btnAjouter.setEnabled(false);
		btnModifier.setEnabled(false);
		btnSuppriemr.setEnabled(false);
		
		lblNewLabel = new JLabel("Recherche");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(179, 78, 76, 14);
		contentPane.add(lblNewLabel);
		
		lblNom = new JLabel("Nom");
		lblNom.setForeground(Color.WHITE);
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNom.setBounds(59, 248, 76, 14);
		contentPane.add(lblNom);
		
		lblAdresse = new JLabel("Adresse");
		lblAdresse.setForeground(Color.WHITE);
		lblAdresse.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAdresse.setBounds(191, 249, 113, 14);
		contentPane.add(lblAdresse);
		
		lblVille = new JLabel("Ville");
		lblVille.setForeground(Color.WHITE);
		lblVille.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblVille.setBounds(385, 249, 46, 14);
		contentPane.add(lblVille);
		
		lblDescription = new JLabel("Description");
		lblDescription.setForeground(Color.WHITE);
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDescription.setBounds(501, 249, 113, 14);
		contentPane.add(lblDescription);
		
		lblCat = new JLabel("Cat");
		lblCat.setForeground(Color.WHITE);
		lblCat.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCat.setBounds(58, 308, 27, 14);
		contentPane.add(lblCat);
		
		lblHa = new JLabel("HA");
		lblHa.setForeground(Color.WHITE);
		lblHa.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblHa.setBounds(122, 308, 27, 14);
		contentPane.add(lblHa);
		
		lblHd = new JLabel("HD");
		lblHd.setForeground(Color.WHITE);
		lblHd.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblHd.setBounds(191, 308, 27, 14);
		contentPane.add(lblHd);
		
		lblPk = new JLabel("P");
		lblPk.setForeground(Color.WHITE);
		lblPk.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPk.setBounds(263, 308, 8, 14);
		contentPane.add(lblPk);
		
		lblWifi = new JLabel("Wifi");
		lblWifi.setForeground(Color.WHITE);
		lblWifi.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblWifi.setBounds(310, 309, 27, 14);
		contentPane.add(lblWifi);
		
		lblPisc = new JLabel("Pisc");
		lblPisc.setForeground(Color.WHITE);
		lblPisc.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPisc.setBounds(387, 308, 27, 14);
		contentPane.add(lblPisc);
		
		lblNav = new JLabel("Nav");
		lblNav.setForeground(Color.WHITE);
		lblNav.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNav.setBounds(445, 308, 27, 14);
		contentPane.add(lblNav);
		
		lblAnim = new JLabel("Ani");
		lblAnim.setForeground(Color.WHITE);
		lblAnim.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAnim.setBounds(58, 375, 27, 14);
		contentPane.add(lblAnim);
		
		lblIh = new JLabel("IH");
		lblIh.setForeground(Color.WHITE);
		lblIh.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIh.setBounds(120, 375, 27, 14);
		contentPane.add(lblIh);
		lblIh.setVisible(false);
		
		lblIs = new JLabel("IS");
		lblIs.setForeground(Color.WHITE);
		lblIs.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIs.setBounds(198, 376, 27, 14);
		contentPane.add(lblIs);
		lblIs.setVisible(false);
		
	
		
		//========================= EVENTS BTN ===========================
		btnAjouter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (validateCat() && validateIdSociete() && validateHoraires() && validateParking() && validateWifi() && validatePiscine() && validateNav() && validateAnimal()) {
					Hotel h = new Hotel();
					h.setId_soc(Integer.parseInt (chmpIdSociete.getText()));
					h.setAdresseH(adHotel.getText());
					h.setNomH(nomHotel.getText());
					h.setVilleH(villeHotel.getText());
					h.setParkingH(parkingHotel.getText());
					h.setWifiH(wifiHotel.getText());
					h.setHeureAaH(horArrive.getText());
					h.setHeureDepH(horDepart.getText());
					h.setPiscineH(piscineHotel.getText());
					h.setNavetteH(navHotel.getText());
					h.setPresenceAniH(animalHotel.getText());
					h.setDescriptionH(descHotel.getText());
					h.setCatH(Integer.parseInt (cat.getText()));
					
					new HotelDao().save(h);
					ArrayList<Hotel> hotel = new HotelDao().getAll();
					String columns[] = { "ID", "Societe", "NomHotel","Adhotel","VilleH","DescHo","ParkH","wifiH","HarrHot","HdepHot","Piscine","NavHot","Animal","Cat"};
					String[][] data = new String[hotel.size()][columns.length];
					


					int i = 0;
					for (Hotel s : hotel) {
						data[i][0] = s.getId_hotel() + "";
						data[i][1] = new SocieteDao().getById(s.getId_soc()).getNom();
						data[i][2] = s.getNomH();
						data[i][3] = s.getAdresseH();
						data[i][4] = s.getVilleH();
						data[i][5] = s.getDescriptionH();
						data[i][6] = s.getParkingH();
						data[i][7] = s.getWifiH();
						data[i][8] = s.getHeureAaH();
						data[i][9] = s.getHeureDepH();
						data[i][10] = s.getPiscineH();
						data[i][11] = s.getNavetteH();
						data[i][12] = s.getPresenceAniH();
						if (s.getCatH() ==1) data[i][13] = "*";
						else if(s.getCatH() ==2)  data[i][13] = "**"; 
						else if(s.getCatH() ==3)  data[i][13] = "***"; 
						else if(s.getCatH() ==4)  data[i][13] = "****"; 
						else if(s.getCatH() ==5)  data[i][13] = "*****"; 
						i++;
					}

					model = new DefaultTableModel(data,columns);
					table.setModel(model);
					
		        }
			}

		});
		
		
		
		btnModifier.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (validateCat() && validateIdSociete() && validateHoraires() && validateParking() && validateWifi() && validatePiscine() && validateNav() && validateAnimal()) {
					int id = Integer.parseInt (idHotel.getText());
					Hotel h = new HotelDao().getById(id);
					h.setId_soc(Integer.parseInt (chmpIdSociete.getText()));
					h.setAdresseH(adHotel.getText());
					h.setNomH(nomHotel.getText());
					h.setVilleH(villeHotel.getText());
					h.setParkingH(parkingHotel.getText());
					h.setWifiH(wifiHotel.getText());
					h.setHeureAaH(horArrive.getText());
					h.setHeureDepH(horDepart.getText());
					h.setPiscineH(piscineHotel.getText());
					h.setNavetteH(navHotel.getText());
					h.setPresenceAniH(animalHotel.getText());
					h.setDescriptionH(descHotel.getText());
					h.setCatH(Integer.parseInt (cat.getText()));
					
					new HotelDao().save(h);
					
					ArrayList<Hotel> hotel = new HotelDao().getAll();
					String columns[] = { "ID", "Societe", "NomHotel","Adhotel","VilleH","DescHo","ParkH","wifiH","HarrHot","HdepHot","Piscine","NavHot","Animal","Cat"};
					String[][] data = new String[hotel.size()][columns.length];
					


					int i = 0;
					for (Hotel s : hotel) {
						data[i][0] = s.getId_hotel() + "";
						data[i][1] = new SocieteDao().getById(s.getId_soc()).getNom();
						data[i][2] = s.getNomH();
						data[i][3] = s.getAdresseH();
						data[i][4] = s.getVilleH();
						data[i][5] = s.getDescriptionH();
						data[i][6] = s.getParkingH();
						data[i][7] = s.getWifiH();
						data[i][8] = s.getHeureAaH();
						data[i][9] = s.getHeureDepH();
						data[i][10] = s.getPiscineH();
						data[i][11] = s.getNavetteH();
						data[i][12] = s.getPresenceAniH();
						if (s.getCatH() ==1) data[i][13] = "*";
						else if(s.getCatH() ==2)  data[i][13] = "**"; 
						else if(s.getCatH() ==3)  data[i][13] = "***"; 
						else if(s.getCatH() ==4)  data[i][13] = "****"; 
						else if(s.getCatH() ==5)  data[i][13] = "*****"; 
						i++;
					}

					model = new DefaultTableModel(data,columns);
					table.setModel(model);
					
		        }
			}

		});
		
		
		btnSuppriemr.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (validateCat() && validateIdSociete() && validateHoraires() && validateParking() && validateWifi() && validatePiscine() && validateNav() && validateAnimal()) {
					int id = Integer.parseInt (idHotel.getText());
					Hotel h = new HotelDao().getById(id);
					
					//Message de confirmation
					int input = JOptionPane.showConfirmDialog(null, "êtes vous sûr ? Vous ne verrez plus cette ligne");
					if (input == 0) {
						//suppression de l'objet avec son id
						new HotelDao().delete(h.getId_hotel());
					}
					
					ArrayList<Hotel> hotel = new HotelDao().getAll();
					String columns[] = { "ID", "Societe", "NomHotel","Adhotel","VilleH","DescHo","ParkH","wifiH","HarrHot","HdepHot","Piscine","NavHot","Animal","Cat"};
					String[][] data = new String[hotel.size()][columns.length];

					int i = 0;
					for (Hotel s : hotel) {
						data[i][0] = s.getId_hotel() + "";
						data[i][1] = new SocieteDao().getById(s.getId_soc()).getNom();
						data[i][2] = s.getNomH();
						data[i][3] = s.getAdresseH();
						data[i][4] = s.getVilleH();
						data[i][5] = s.getDescriptionH();
						data[i][6] = s.getParkingH();
						data[i][7] = s.getWifiH();
						data[i][8] = s.getHeureAaH();
						data[i][9] = s.getHeureDepH();
						data[i][10] = s.getPiscineH();
						data[i][11] = s.getNavetteH();
						data[i][12] = s.getPresenceAniH();
						if (s.getCatH() ==1) data[i][13] = "*";
						else if(s.getCatH() ==2)  data[i][13] = "**"; 
						else if(s.getCatH() ==3)  data[i][13] = "***"; 
						else if(s.getCatH() ==4)  data[i][13] = "****"; 
						else if(s.getCatH() ==5)  data[i][13] = "*****"; 
						i++;
					}

					model = new DefaultTableModel(data,columns);
					table.setModel(model);
					
		        }
			}

		});
		
	}
	
	//========================== UTILS ================================
    // Méthode de validation pour le champ Catégorie
    private boolean validateCat() {
        try {
            int valCat = Integer.parseInt(cat.getText());
            if (valCat < 1 || valCat > 5) {
                JOptionPane.showMessageDialog(null, "La catégorie doit être un nombre entre 1 et 5");
                cat.requestFocus();
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Veuillez saisir un nombre valide pour la catégorie");
            cat.requestFocus();
            return false;
        }
    }

    // Méthode de validation pour le champ ID Société
    private boolean validateIdSociete() {
    	int lenghttabSociete = new SocieteDao().getAll().size();
        try {
            int valId_soc = Integer.parseInt(chmpIdSociete.getText());
            if (valId_soc < 1 || valId_soc > lenghttabSociete) {
                JOptionPane.showMessageDialog(null, "Veuillez saisir un ID de société valide entre 1 et " + lenghttabSociete);
                chmpIdSociete.requestFocus();
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Veuillez saisir un nombre valide pour l'ID de société");
            chmpIdSociete.requestFocus();
            return false;
        }
    }

    // Méthode de validation pour les horaires
    private boolean validateHoraires() {
        try {
            int hAr = Integer.parseInt(horArrive.getText());
            int hArDe = Integer.parseInt(horDepart.getText());
            if (hAr > hArDe) {
                JOptionPane.showMessageDialog(null, "L'heure d'arrivée doit être inférieure à l'heure de départ");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Veuillez saisir des heures valides pour les horaires");
            return false;
        }
    }

    // Méthode de validation pour le champ Parking
    private boolean validateParking() {
        String valParking = parkingHotel.getText();
        if (!valParking.equalsIgnoreCase("non") && !valParking.equalsIgnoreCase("oui")) {
            JOptionPane.showMessageDialog(null, "Veuillez saisir 'oui' ou 'non' pour le parking");
            return false;
        }
        return true;
    }

    // Méthode de validation pour le champ Wifi
    private boolean validateWifi() {
        String wfi = wifiHotel.getText();
        if (!wfi.equalsIgnoreCase("non") && !wfi.equalsIgnoreCase("oui")) {
            JOptionPane.showMessageDialog(null, "Veuillez saisir 'oui' ou 'non' pour le wifi");
            return false;
        }
        return true;
    }
    
 // Méthode de validation pour le champ Piscine
    private boolean validatePiscine() {
        String valPiscine = piscineHotel.getText();
        if (!valPiscine.equalsIgnoreCase("non") && !valPiscine.equalsIgnoreCase("oui")) {
            JOptionPane.showMessageDialog(null, "Veuillez saisir 'oui' ou 'non' pour la piscine");
            return false;
        }
        return true;
    }

    // Méthode de validation pour le champ Navette
    private boolean validateNav() {
        String valNav = navHotel.getText();
        if (!valNav.equalsIgnoreCase("non") && !valNav.equalsIgnoreCase("oui")) {
            JOptionPane.showMessageDialog(null, "Veuillez saisir 'oui' ou 'non' pour la navette");
            return false;
        }
        return true;
    }

    // Méthode de validation pour le champ Animal
    private boolean validateAnimal() {
        String valAnimal = animalHotel.getText();
        if (!valAnimal.equalsIgnoreCase("non") && !valAnimal.equalsIgnoreCase("admis")) {
            JOptionPane.showMessageDialog(null, "Veuillez saisir 'oui' ou 'admis' pour les animaux");
            return false;
        }
        return true;
    }
	
	//============================================
    private void updateFields() {
        wordCherched = champRech.getText();
        

        if (!wordCherched.isEmpty()) {
       		ArrayList<Hotel> hotel = new HotelDao().recherche(wordCherched);
    		String columns[] = { "ID", "Societe", "NomHotel","Adhotel","VilleH","DescHo","ParkH","wifiH","HarrHot","HdepHot","Piscine","NavHot","Animal","Cat"};
    		String[][] data = new String[hotel.size()][columns.length];
    		
    		int i = 0;
    		for (Hotel s : hotel) {
    			data[i][0] = s.getId_hotel() + "";
    			data[i][1] = new SocieteDao().getById(s.getId_soc()).getNom();
    			data[i][2] = s.getNomH();
    			data[i][3] = s.getAdresseH();
    			data[i][4] = s.getVilleH();
    			data[i][5] = s.getDescriptionH();
    			data[i][6] = s.getParkingH();
    			data[i][7] = s.getWifiH();
    			data[i][8] = s.getHeureAaH();
    			data[i][9] = s.getHeureDepH();
    			data[i][10] = s.getPiscineH();
    			data[i][11] = s.getNavetteH();
    			data[i][12] = s.getPresenceAniH();
    			if (s.getCatH() ==1) data[i][13] = "*";
    			else if(s.getCatH() ==2)  data[i][13] = "**"; 
    			else if(s.getCatH() ==3)  data[i][13] = "***"; 
    			else if(s.getCatH() ==4)  data[i][13] = "****"; 
    			else if(s.getCatH() ==5)  data[i][13] = "*****"; 
    			i++;
    		}

    		model = new DefaultTableModel(data,columns);
			table.setModel(model);
        } 
        else {
    		ArrayList<Hotel> hotel = new HotelDao().getAll();
    		String columns[] = { "ID", "Societe", "NomHotel","Adhotel","VilleH","DescHo","ParkH","wifiH","HarrHot","HdepHot","Piscine","NavHot","Animal","Cat"};
    		String[][] data = new String[hotel.size()][columns.length];
    		
    		int i = 0;
    		for (Hotel s : hotel) {
    			data[i][0] = s.getId_hotel() + "";
    			data[i][1] = new SocieteDao().getById(s.getId_soc()).getNom();
    			data[i][2] = s.getNomH();
    			data[i][3] = s.getAdresseH();
    			data[i][4] = s.getVilleH();
    			data[i][5] = s.getDescriptionH();
    			data[i][6] = s.getParkingH();
    			data[i][7] = s.getWifiH();
    			data[i][8] = s.getHeureAaH();
    			data[i][9] = s.getHeureDepH();
    			data[i][10] = s.getPiscineH();
    			data[i][11] = s.getNavetteH();
    			data[i][12] = s.getPresenceAniH();
    			if (s.getCatH() ==1) data[i][13] = "*";
    			else if(s.getCatH() ==2)  data[i][13] = "**"; 
    			else if(s.getCatH() ==3)  data[i][13] = "***"; 
    			else if(s.getCatH() ==4)  data[i][13] = "****"; 
    			else if(s.getCatH() ==5)  data[i][13] = "*****"; 
    			i++;
    		}

    		model = new DefaultTableModel(data,columns);
			table.setModel(model);
			
        		
        }
    }
}
