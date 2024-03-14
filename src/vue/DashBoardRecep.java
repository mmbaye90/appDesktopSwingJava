package vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale.IsoCountryCode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import Components.Button;
import Components.TextField;
import dao.ChambreDao;
import dao.ClientDao;
import dao.HotelDao;
import dao.PaiementDao;
import dao.ReservDao;
import entites.Chambre;
import entites.Client;
import entites.Db;
import entites.Paiement;
import entites.Reservation;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DashBoardRecep extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	//Varibles Clients
	private TextField idClient;
	private TextField nomClient;
	private TextField prenomCl;
	private TextField adCl;
	private TextField ageCl;
	private TextField villeCl;
	private TextField emailCl;
	private TextField sexeCl;
	private TextField telCl;
	private TextField paysCl;
	private JLabel lblNewLabel;
	private JLabel lblPrenom;
	private JLabel lblAge;
	private JLabel lblAdresse_1;
	private JLabel lblEmail;
	private JLabel lblVille;
	private JLabel lblPays;
	private JLabel lblSexe;
	private JLabel lblTel;
	private String wordCherched;
	private Button btnAjouter;
	DefaultTableModel model;
	JTable table;
	TextField champRech;
	private JLabel lblRechercher;
	private JLabel lblInformationsClients_1;
	
	
	//Variables Chambres
	private TextField idChambre;
	private TextField numChambre;
	private TextField superficie;
	private TextField prixNuit;	private TextField idHotelClefEtr;
	DefaultTableModel modelChambre;
	JTable tableChambre;

	
	//Variables Reservation
	private TextField idReservation;
	private TextField idChambreClefEtDansRes;
	private TextField idClientClefEtDansRes;
	private TextField dateDebutRes;
	private TextField dateFinRes;
	private TextField nbrPers;
	private TextField nbrdeJours;
	private TextField champRechR;
	private TextField id_ch;
	private TextField id_client;
	
	private String wordCherchedRes;
	private Button btnAjouterR;
	private Button btnModifierR;
	private Button btnSupprimerR;
	
	
	DefaultTableModel modelRes;
	JTable tableRes;
	
	//Variables Paiements
	JButton valideDate;
	private TextField idPaiement;
	//private TextField idResClefEtDsP;
	private TextField datePaiement;
	private JDateChooser dateChooser;
	private TextField montantPaiement;
	private TextField methodePaiement;
	private TextField champRechP;
	private String wordCherchedP;
	private Button btnAjouterP;
	private Button btnModifierP;
	private Button btnSupprimerP;
	TextField prixNuitCha;
	TextField total;
	TextField resteApayer;
	TextField status;
	DefaultTableModel modelP;
	JTable tableP;
	private JPanel panel_2;
	private JLabel lblRechercher_1;
	private JLabel lblD;
	private JLabel lblDatef;
	private JLabel lblNbp;
	private JLabel lblSup;
	private JLabel lblNch;
	private JLabel lblPrix;
	private JPanel panel_1;
	private JPanel panel_3;
	private JLabel lblGestionPaiements;
	private JLabel lblIdp;
	private JLabel lblNbrjr;
	private JLabel lblChxdatep;
	private JLabel lblDatep;
	private JPanel panel_4;
	private JLabel lblMethPai;
	private JPanel panel_5;
	private JPanel panel_6;
	private JLabel lblRechercher_2;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					DashBoardRecep frame = new DashBoardRecep();
					frame.setVisible(true);
					JOptionPane.showMessageDialog(null, " Trois etapes à respecter pour reserver :\n1-Choisir un client\n2-Choisir une chambre\n3-Choisir la rese a regler".toUpperCase());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public DashBoardRecep() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 918, 709);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(0,101,252));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Titre
		JLabel titre = new JLabel("GESTION DES RESERVATIONS");
		titre.setForeground(SystemColor.textHighlightText);
		titre.setFont(new Font("Tahoma", Font.BOLD, 16));
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setBounds(353, 0, 245, 28);
		contentPane.add(titre);
		
		//===================  LOGO  =====================================
		//Chargement Logo et configuration Style
        ImageIcon iconlogo = new ImageIcon(this.getClass().getResource("/logo.png"));
        Image logo = iconlogo.getImage().getScaledInstance(55,70, Image.SCALE_DEFAULT);

		JLabel contLogo = new JLabel(new ImageIcon(logo));
		contLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new Login().setVisible(true);
			}
		});
		contLogo.setHorizontalAlignment(SwingConstants.CENTER);
		contLogo.setBounds(10, 11, 27,28);
		contentPane.add(contLogo);
		
		//Partie Clients
		JLabel lblInformationsClients = new JLabel("INFORMATIONS CLIENTS");
		lblInformationsClients.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformationsClients.setForeground(SystemColor.textHighlightText);
		lblInformationsClients.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblInformationsClients.setBounds(47, 11, 176, 28);
		contentPane.add(lblInformationsClients);
		
		
		//Ajout ChampRecherche
		champRech = new TextField();
		champRech.setForeground(SystemColor.textHighlightText);
		champRech.setFont(new Font("Tahoma", Font.BOLD, 16));
		champRech.setHorizontalAlignment(SwingConstants.CENTER);
		champRech.setBounds(10, 68, 107, 28);
		contentPane.add(champRech);
		
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
		ArrayList<Client> Clients = new ClientDao().getAll();
		String columns[] = { "ID", "Nom", "Prenom","Adresse","Age","Ville","Email","Sexe","Tel","Pays"};
		String[][] data = new String[Clients.size()][columns.length];

		int i = 0;
		for (Client s : Clients) {
			data[i][0] = s.getId_client() + "";
			data[i][1] = s.getNomC();
			data[i][2] = s.getPrenomC();
			data[i][3] = s.getAdC();
			data[i][4] = s.getAgeC()+"";
			data[i][5] = s.getVilleC();
			data[i][6] = s.getMailC();
			data[i][7] = s.getSexeC();
			data[i][8] = s.getTelC();
			data[i][9] = s.getPaysC();
			i++;
		}

		model = new DefaultTableModel(data,columns);
		table = new JTable(model);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 101, 344, 113);
		getContentPane().add(scrollPane);
		
		//======================================== Ajout des Champs Text ============
		//-----id_societe fremplace par le nom de la societé
		idClient = new TextField();
		idClient.setForeground(SystemColor.textHighlightText);
		idClient.setFont(new Font("Tahoma", Font.BOLD, 16));
		idClient.setHorizontalAlignment(SwingConstants.CENTER);
		idClient.setBounds(399, 289, 40, 34);
		contentPane.add(idClient);
		idClient.setVisible(false);
		
		//-----nomClient
		nomClient = new TextField();
		nomClient.setForeground(SystemColor.textHighlightText);
		nomClient.setFont(new Font("Tahoma", Font.BOLD, 16));
		nomClient.setHorizontalAlignment(SwingConstants.CENTER);
		nomClient.setBounds(9, 238, 93, 34);
		contentPane.add(nomClient);

		//-----prenomCl
		prenomCl = new TextField();
		prenomCl.setForeground(SystemColor.textHighlightText);
		prenomCl.setFont(new Font("Tahoma", Font.BOLD, 16));
		prenomCl.setHorizontalAlignment(SwingConstants.CENTER);
		prenomCl.setBounds(113, 238, 93, 34);
		contentPane.add(prenomCl);
		
		//adCl
		adCl = new TextField();
		adCl.setForeground(SystemColor.textHighlightText);
		adCl.setFont(new Font("Tahoma", Font.BOLD, 16));
		adCl.setHorizontalAlignment(SwingConstants.CENTER);
		adCl.setBounds(235, 334, 194, 40);
		contentPane.add(adCl);
	
		//ageCl
		ageCl =new TextField();
		ageCl.setForeground(SystemColor.textHighlightText);
		ageCl.setFont(new Font("Tahoma", Font.BOLD, 16));
		ageCl.setHorizontalAlignment(SwingConstants.CENTER);
		ageCl.setBounds(321, 238, 47, 34);
		contentPane.add(ageCl);
		
		//villeCl
		villeCl =new TextField();
		villeCl.setForeground(SystemColor.textHighlightText);
		villeCl.setFont(new Font("Tahoma", Font.BOLD, 16));
		villeCl.setHorizontalAlignment(SwingConstants.CENTER);
		villeCl.setBounds(215, 238, 93, 34);
		contentPane.add(villeCl);
		
		//emailCl
		emailCl =new TextField();
		emailCl.setForeground(SystemColor.textHighlightText);
		emailCl.setFont(new Font("Tahoma", Font.BOLD, 16));
		emailCl.setHorizontalAlignment(SwingConstants.CENTER);
		emailCl.setBounds(10, 289, 163, 34);
		contentPane.add(emailCl);
		
		//sexeCl
		sexeCl =new TextField();
		sexeCl.setForeground(SystemColor.textHighlightText);
		sexeCl.setFont(new Font("Tahoma", Font.BOLD, 16));
		sexeCl.setHorizontalAlignment(SwingConstants.CENTER);
		sexeCl.setBounds(296, 289, 93, 34);
		contentPane.add(sexeCl);
		
		//TelClient
		telCl =new TextField();
		telCl.setForeground(SystemColor.textHighlightText);
		telCl.setFont(new Font("Tahoma", Font.BOLD, 16));
		telCl.setHorizontalAlignment(SwingConstants.CENTER);
		telCl.setBounds(10, 336, 214, 34);
		contentPane.add(telCl);
		
		//paysClient
		paysCl =new TextField();
		paysCl.setForeground(SystemColor.textHighlightText);
		paysCl.setFont(new Font("Tahoma", Font.BOLD, 16));
		paysCl.setHorizontalAlignment(SwingConstants.CENTER);
		paysCl.setBounds(183, 289, 103, 34);
		contentPane.add(paysCl);
	
		
		lblNewLabel = new JLabel("Nom");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(20, 225, 46, 14);
		contentPane.add(lblNewLabel);
		
		lblPrenom = new JLabel("Prenom");
		lblPrenom.setForeground(Color.WHITE);
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrenom.setBounds(132, 226, 46, 14);
		contentPane.add(lblPrenom);
		
		lblAge = new JLabel("Age");
		lblAge.setForeground(Color.WHITE);
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAge.setBounds(328, 225, 46, 14);
		contentPane.add(lblAge);
		
		lblAdresse_1 = new JLabel("Adresse");
		lblAdresse_1.setForeground(Color.WHITE);
		lblAdresse_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAdresse_1.setBounds(245, 323, 46, 14);
		contentPane.add(lblAdresse_1);
		
		lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBounds(19, 278, 46, 14);
		contentPane.add(lblEmail);
		
		lblVille = new JLabel("Ville");
		lblVille.setForeground(Color.WHITE);
		lblVille.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVille.setBounds(223, 226, 46, 14);
		contentPane.add(lblVille);
		
		lblPays = new JLabel("Pays");
		lblPays.setForeground(Color.WHITE);
		lblPays.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPays.setBounds(223, 278, 46, 14);
		contentPane.add(lblPays);
		
		lblSexe = new JLabel("Sexe");
		lblSexe.setForeground(Color.WHITE);
		lblSexe.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSexe.setBounds(322, 278, 46, 14);
		contentPane.add(lblSexe);
		
		lblTel = new JLabel("Tel");
		lblTel.setForeground(Color.WHITE);
		lblTel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTel.setBounds(20, 323, 46, 14);
		contentPane.add(lblTel);
		
		lblRechercher = new JLabel("rechercher");
		lblRechercher.setHorizontalAlignment(SwingConstants.CENTER);
		lblRechercher.setForeground(SystemColor.textHighlightText);
		lblRechercher.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRechercher.setBounds(10, 47, 93, 28);
		contentPane.add(lblRechercher);
		
		
		//======================== EVENT SELECTION TABLE CLIENT ==============
		table.getSelectionModel().addListSelectionListener(e -> {
			int rowIndex = table.getSelectedRow();
			if (rowIndex != -1) {
				String idTableSelected = (String) table.getValueAt(rowIndex, 0);
				String nom = (String) table.getValueAt(rowIndex, 1);
				String prenom = (String) table.getValueAt(rowIndex, 2);
				String adresse = (String) table.getValueAt(rowIndex, 3);
				String age = (String) table.getValueAt(rowIndex, 4);
				String ville = (String) table.getValueAt(rowIndex, 5);
				String mail = (String) table.getValueAt(rowIndex, 6);
				String sexe = (String) table.getValueAt(rowIndex, 7);
				String telephone = (String) table.getValueAt(rowIndex, 8);
				String pays = (String) table.getValueAt(rowIndex, 9);
				
				//Les chmps
				//ID table client
				idClient.setText(idTableSelected);				
				nomClient.setText(nom);
				prenomCl.setText(prenom);
				adCl.setText(adresse);
				ageCl.setText(age);
				villeCl.setText(ville);
				emailCl.setText(mail);
				sexeCl.setText(sexe);
				telCl.setText(telephone);
				paysCl.setText(pays);
				
				id_client.setText(idClient.getText()); 
				
				tableChambre.setEnabled(true);
			} 
//			else {
//				btnModifier.setEnabled(false);
//				btnSuppriemr.setEnabled(false);
//			}
		});
		
		//=========================  BTNS =========================
		//------ Ajouter
		btnAjouter = new Button();
		btnAjouter.setText("Ajouter");
		btnAjouter.setForeground(SystemColor.textHighlightText);
		btnAjouter.setBackground(Color.BLACK);
		btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAjouter.setHorizontalAlignment(SwingConstants.CENTER);
		btnAjouter.setBounds(268, 68, 86, 28);
		contentPane.add(btnAjouter);
		
		
		btnAjouterR = new Button();
		btnAjouterR.setText("Ajouter");
		btnAjouterR.setForeground(SystemColor.textHighlightText);
		btnAjouterR.setBackground(Color.BLACK);
		btnAjouterR.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAjouterR.setHorizontalAlignment(SwingConstants.CENTER);
		btnAjouterR.setBounds(162, 424, 86, 28);
		contentPane.add(btnAjouterR);
		
		btnModifierR = new Button();
		btnModifierR.setText("Modifier");
		btnModifierR.setForeground(SystemColor.textHighlightText);
		btnModifierR.setBackground(Color.BLACK);
		btnModifierR.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnModifierR.setHorizontalAlignment(SwingConstants.CENTER);
		btnModifierR.setBounds(258, 424, 86, 28);
		contentPane.add(btnModifierR);
		btnModifierR.setEnabled(false);
		
		btnSupprimerR = new Button();
		btnSupprimerR.setText("Supprimer");
		btnSupprimerR.setForeground(SystemColor.textHighlightText);
		btnSupprimerR.setBackground(Color.BLACK);
		btnSupprimerR.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSupprimerR.setHorizontalAlignment(SwingConstants.CENTER);
		btnSupprimerR.setBounds(353, 424, 86, 28);
		contentPane.add(btnSupprimerR);
		btnSupprimerR.setEnabled(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(456, 34, 10, 372);
		contentPane.add(panel);
		
		lblInformationsClients_1 = new JLabel("INFORMATIONS CHAMBRES");
		lblInformationsClients_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformationsClients_1.setForeground(SystemColor.textHighlightText);
		lblInformationsClients_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblInformationsClients_1.setBounds(608, 23, 176, 28);
		contentPane.add(lblInformationsClients_1);
		

		
		///////////////////////////////////// PARTIE CHAMBRE=============================		
		ArrayList<Chambre> chambres = new ChambreDao().getAll();
        String columnsChambre[] = { "ID", "N°Ch", "Sup", "Prix" ,"Hotel"};
        String[][] dataChambre = new String[chambres.size()][columnsChambre.length];
        
        int j = 0;
        for (Chambre c : chambres) {
        	dataChambre[j][0] = c.getId_ch()+"";
        	dataChambre[j][1] = c.getNumCh()+"";
        	dataChambre[j][2] = c.getSupCh()+"";
        	dataChambre[j][3] = c.getPrixNtCh()+"";
        	dataChambre[j][4] = new HotelDao().getById(c.getId_hotel()).getNomH();
            j++;
        }

        modelChambre = new DefaultTableModel(dataChambre, columnsChambre);
        tableChambre = new JTable(modelChambre);
        JScrollPane scrollPane1 = new JScrollPane(tableChambre);
        scrollPane1.setBounds(502, 85, 380, 98);
        contentPane.add(scrollPane1);
        tableChambre.setEnabled(false);
        
		///////////////////// EVENT CLICK TABLE PARTIE CHAMBRE  ===================
        tableChambre.getSelectionModel().addListSelectionListener(e -> {

				int rowIndex = tableChambre.getSelectedRow();			
				String idTableSelected = (String) tableChambre.getValueAt(rowIndex, 0);
				String num_cha = (String) tableChambre.getValueAt(rowIndex, 1);
				String superficieC = (String) tableChambre.getValueAt(rowIndex, 2);
				String prix = (String) tableChambre.getValueAt(rowIndex, 3);
				
				numChambre.setText(num_cha);
				superficie.setText(superficieC);
				prixNuit.setText(prix);
				idChambre.setText(idTableSelected);
				id_ch.setText(idTableSelected);
				prixNuitCha.setText(prixNuit.getText());
				tableRes.setEnabled(true);
		});
		
		prixNuit = new TextField();
		prixNuit.setHorizontalAlignment(SwingConstants.CENTER);
		prixNuit.setForeground(SystemColor.textHighlightText);
		prixNuit.setFont(new Font("Tahoma", Font.BOLD, 16));
		prixNuit.setBounds(668, 213, 86, 34);
		contentPane.add(prixNuit);
		
		superficie = new TextField();
		superficie.setHorizontalAlignment(SwingConstants.CENTER);
		superficie.setForeground(SystemColor.textHighlightText);
		superficie.setFont(new Font("Tahoma", Font.BOLD, 16));
		superficie.setBounds(499, 213, 80, 34);
		contentPane.add(superficie);
		
		idChambre = new TextField();
		idChambre.setHorizontalAlignment(SwingConstants.CENTER);
		idChambre.setForeground(SystemColor.textHighlightText);
		idChambre.setFont(new Font("Tahoma", Font.BOLD, 16));
		idChambre.setBounds(767, 213, 67, 34);
		contentPane.add(idChambre);
		
		numChambre = new TextField();
		numChambre.setHorizontalAlignment(SwingConstants.CENTER);
		numChambre.setForeground(SystemColor.textHighlightText);
		numChambre.setFont(new Font("Tahoma", Font.BOLD, 16));
		numChambre.setBounds(591, 213, 67, 34);
		contentPane.add(numChambre);
		///////////////////////////////////// PARTIE RESERVATION=============================
		champRechR = new TextField();
		champRechR.setForeground(SystemColor.textHighlightText);
		champRechR.setFont(new Font("Tahoma", Font.BOLD, 16));
		champRechR.setHorizontalAlignment(SwingConstants.CENTER);
		champRechR.setBounds(10, 420, 142, 35);
		contentPane.add(champRechR);
		
		champRechR.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateFieldsR();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateFieldsR();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateFieldsR();
            }

        });
		ArrayList<Reservation> reservations = new ReservDao().getAll();
		String columnsRes[] = { "ID", "N°Ch", "NomCl","DateDebR","DateFinR","NbrPer"};
		String[][] dataRes = new String[reservations.size()][columnsRes.length];

		int k = 0;
		for (Reservation r : reservations) {
			dataRes[k][0] = r.getId_reserv()+ "";
			dataRes[k][1] = new ChambreDao().getById(r.getId_ch()).getNumCh()+"" ; //id_ch remplacé par le numéro
			dataRes[k][2] = new ClientDao().getById(r.getId_client()).getNomC() ;//id_Cl remplacé par le nom du client
			dataRes[k][3] = r.getDateDebRes();
			dataRes[k][4] = r.getDateFinR();
			dataRes[k][5] = r.getNbPerso()+"";

			k++;
		}

		modelRes= new DefaultTableModel(dataRes,columnsRes);
		tableRes = new JTable(modelRes);
		
		JScrollPane scrollPaneR = new JScrollPane(tableRes);
		scrollPaneR.setBounds(10, 459, 420, 98);
		contentPane.add(scrollPaneR);
		tableRes.setEnabled(false);
		///////////////////// EVENT CLICK TABLE PARTIE RESERVATION  ===================
		tableRes.getSelectionModel().addListSelectionListener(e -> {

				int rowIndex = tableRes.getSelectedRow();	
				if (rowIndex != -1) {
					String idTableSelected = (String) tableRes.getValueAt(rowIndex, 0);
					String numCh = (String) tableRes.getValueAt(rowIndex, 1);
					String nomClient = (String) tableRes.getValueAt(rowIndex, 2);
					String dateD = (String) tableRes.getValueAt(rowIndex, 3);
					String dateF = (String) tableRes.getValueAt(rowIndex, 4);
					String nombrePerso = (String) tableRes.getValueAt(rowIndex, 5);
					

					//remplissage des champs
					idReservation.setText(idTableSelected) ;
					
					int idRes = Integer.parseInt(idReservation.getText());
					
					id_ch.setText(new ReservDao().getIdChambreByReser(idRes)+"");
					
					id_client.setText( new ReservDao().getIdClientreByReser(idRes)+"");
					
					Chambre c = new ChambreDao().getById(Integer.parseInt(id_ch.getText()));
					
					//Gestion de l'affichage dynamiqu du prix à la select de la table Reservation
					if(c!=null) {
						prixNuitCha.setText(c.getPrixNtCh()+"");
					}else {
						JOptionPane.showMessageDialog(null, "Cette Chambre n'existe pas");
					}
					
					
					idChambreClefEtDansRes.setText(numCh);
					
					idClientClefEtDansRes.setText(nomClient);
					
					dateDebutRes.setText(dateD);
					
					dateFinRes.setText(dateF);
					
					nbrPers.setText(nombrePerso);
					float nbj = nbJourRes(dateDebutRes.getText(),dateFinRes.getText());
					//System.out.println(nbj);
					nbrdeJours.setText(nbj+"");
					
					Float prix = Float.parseFloat(prixNuitCha.getText());
					Float t = prix*nbj;

					total.setText(t+"");
					
					tableP.setEnabled(true);
					
					btnModifierR.setEnabled(true);
					btnSupprimerR.setEnabled(true);
				}

		});
		
		//Affichage des champs
		idReservation = new TextField();
		idReservation.setHorizontalAlignment(SwingConstants.CENTER);
		idReservation.setForeground(SystemColor.textHighlightText);
		idReservation.setFont(new Font("Tahoma", Font.BOLD, 16));
		idReservation.setBounds(535, 442, 40, 34);
		contentPane.add(idReservation);
		
		
		nbrdeJours= new TextField();
		nbrdeJours.setHorizontalAlignment(SwingConstants.CENTER);
		nbrdeJours.setForeground(SystemColor.textHighlightText);
		nbrdeJours.setFont(new Font("Tahoma", Font.BOLD, 16));
		nbrdeJours.setBounds(535, 487, 62, 34);
		contentPane.add(nbrdeJours);
		nbrdeJours.setEnabled(false);
		
		idChambreClefEtDansRes = new TextField();
		idChambreClefEtDansRes.setHorizontalAlignment(SwingConstants.CENTER);
		idChambreClefEtDansRes.setForeground(SystemColor.textHighlightText);
		idChambreClefEtDansRes.setFont(new Font("Tahoma", Font.BOLD, 16));
		idChambreClefEtDansRes.setBounds(10, 630, 67, 34);
		contentPane.add(idChambreClefEtDansRes);
		idChambreClefEtDansRes.setVisible(false);
		
		id_ch = new TextField();
		id_ch.setHorizontalAlignment(SwingConstants.CENTER);
		id_ch.setForeground(SystemColor.textHighlightText);
		id_ch.setFont(new Font("Tahoma", Font.BOLD, 16));
		id_ch.setBounds(10, 630, 67, 34);
		contentPane.add(id_ch);
		
		idClientClefEtDansRes = new TextField();
		idClientClefEtDansRes.setHorizontalAlignment(SwingConstants.CENTER);
		idClientClefEtDansRes.setForeground(SystemColor.textHighlightText);
		idClientClefEtDansRes.setFont(new Font("Tahoma", Font.BOLD, 16));
		idClientClefEtDansRes.setBounds(198, 630, 107, 34);		
		contentPane.add(idClientClefEtDansRes);
		idClientClefEtDansRes.setVisible(false);
		
		id_client = new TextField();
		id_client.setHorizontalAlignment(SwingConstants.CENTER);
		id_client.setForeground(SystemColor.textHighlightText);
		id_client.setFont(new Font("Tahoma", Font.BOLD, 16));
		id_client.setBounds(198, 630, 107, 34);	
		contentPane.add(id_client);
		
		dateDebutRes = new TextField();
		dateDebutRes.setHorizontalAlignment(SwingConstants.CENTER);
		dateDebutRes.setForeground(SystemColor.textHighlightText);
		dateDebutRes.setFont(new Font("Tahoma", Font.BOLD, 16));
		dateDebutRes.setBounds(10, 580, 159, 34);
		contentPane.add(dateDebutRes);
		
		dateFinRes = new TextField();
		dateFinRes.setHorizontalAlignment(SwingConstants.CENTER);
		dateFinRes.setForeground(SystemColor.textHighlightText);
		dateFinRes.setFont(new Font("Tahoma", Font.BOLD, 16));
		dateFinRes.setBounds(198, 580, 156, 34);
		contentPane.add(dateFinRes);
		
		nbrPers = new TextField();
		nbrPers.setHorizontalAlignment(SwingConstants.CENTER);
		nbrPers.setForeground(SystemColor.textHighlightText);
		nbrPers.setFont(new Font("Tahoma", Font.BOLD, 16));
		nbrPers.setBounds(117, 630, 52, 34);
		contentPane.add(nbrPers);
		
		
		///////////////////////////////////// PARTIE PAIEMENT =============================
		ArrayList<Paiement> paiements = new PaiementDao().getAll();
		String columnsP[] = { "ID", "N°Res", "DatePaiement","MontantP","MethodeP"};
		String[][] dataP = new String[paiements.size()][columnsP.length];

		int l = 0;
		for (Paiement p : paiements) {
			dataP[l][0] = p.getId_p()+ "";
			dataP[l][1] = p.getId_reserv()+"";
			dataP[l][2] = p.getDateP();
			dataP[l][3] = p.getMontP()+"";
			dataP[l][4] = p.getMethP()+"";
			l++;
		}

		modelP= new DefaultTableModel(dataP,columnsP);
		tableP = new JTable(modelP);
		
		JScrollPane scrollPaneP = new JScrollPane(tableP);
		scrollPaneP.setBounds(476, 342, 416, 98);
		contentPane.add(scrollPaneP);
		tableP.setEnabled(false);
		

		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(456, 345, 10, 354);
		contentPane.add(panel_2);
		
		JLabel lblResrvationsEnCours = new JLabel("RESRVATIONS EN COURS");
		lblResrvationsEnCours.setHorizontalAlignment(SwingConstants.CENTER);
		lblResrvationsEnCours.setForeground(SystemColor.textHighlightText);
		lblResrvationsEnCours.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblResrvationsEnCours.setBounds(168, 385, 176, 28);
		contentPane.add(lblResrvationsEnCours);
		
		lblRechercher_1 = new JLabel("rechercher");
		lblRechercher_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblRechercher_1.setForeground(SystemColor.textHighlightText);
		lblRechercher_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRechercher_1.setBounds(10, 395, 93, 28);
		contentPane.add(lblRechercher_1);
		
		lblD = new JLabel("DateDeb");
		lblD.setForeground(Color.WHITE);
		lblD.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblD.setBounds(20, 568, 46, 14);
		contentPane.add(lblD);
		
		lblDatef = new JLabel("DateF");
		lblDatef.setForeground(Color.WHITE);
		lblDatef.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDatef.setBounds(202, 568, 46, 14);
		contentPane.add(lblDatef);
		
		lblNbp = new JLabel("NbP");
		lblNbp.setForeground(Color.WHITE);
		lblNbp.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNbp.setBounds(132, 615, 46, 14);
		contentPane.add(lblNbp);
		
		JLabel lblIdch = new JLabel("IDCh");
		lblIdch.setForeground(Color.WHITE);
		lblIdch.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIdch.setBounds(20, 615, 46, 14);
		contentPane.add(lblIdch);
		
		JLabel lblIdcl = new JLabel("IDCl");
		lblIdcl.setForeground(Color.WHITE);
		lblIdcl.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIdcl.setBounds(202, 615, 46, 14);
		contentPane.add(lblIdcl);
		
		lblSup = new JLabel("Sup");
		lblSup.setHorizontalAlignment(SwingConstants.CENTER);
		lblSup.setForeground(SystemColor.textHighlightText);
		lblSup.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSup.setBounds(497, 186, 47, 28);
		contentPane.add(lblSup);
		
		lblNch = new JLabel("N°ch");
		lblNch.setHorizontalAlignment(SwingConstants.CENTER);
		lblNch.setForeground(SystemColor.textHighlightText);
		lblNch.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNch.setBounds(593, 186, 47, 28);
		contentPane.add(lblNch);
		
		lblPrix = new JLabel("Prix");
		lblPrix.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrix.setForeground(SystemColor.textHighlightText);
		lblPrix.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrix.setBounds(668, 186, 58, 28);
		contentPane.add(lblPrix);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(456, 262, 446, 10);
		contentPane.add(panel_1);
		
		panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(0, 381, 460, 10);
		contentPane.add(panel_3);
		
		lblGestionPaiements = new JLabel("GESTION DES PAIEMENTS");
		lblGestionPaiements.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestionPaiements.setForeground(SystemColor.textHighlightText);
		lblGestionPaiements.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGestionPaiements.setBounds(606, 270, 176, 28);
		contentPane.add(lblGestionPaiements);
		
		idPaiement = new TextField();
		idPaiement.setHorizontalAlignment(SwingConstants.CENTER);
		idPaiement.setForeground(SystemColor.textHighlightText);
		idPaiement.setFont(new Font("Tahoma", Font.BOLD, 16));
		idPaiement.setBounds(635, 630, 40, 34);
		contentPane.add(idPaiement);
		idPaiement.setVisible(false);
		
		datePaiement = new TextField();
		datePaiement.setForeground(new Color(255, 255, 255));
		datePaiement.setHorizontalAlignment(SwingConstants.CENTER);
		datePaiement.setFont(new Font("Tahoma", Font.BOLD, 16));
		datePaiement.setBounds(538, 595, 124, 34);
		contentPane.add(datePaiement);
		
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(535, 529, 113, 28);
		contentPane.add(dateChooser);
		
		montantPaiement= new TextField();
		montantPaiement.setHorizontalAlignment(SwingConstants.CENTER);
		montantPaiement.setForeground(SystemColor.textHighlightText);
		montantPaiement.setFont(new Font("Tahoma", Font.BOLD, 16));
		montantPaiement.setBounds(548, 630, 77, 34);
		contentPane.add(montantPaiement);
		
		methodePaiement = new TextField();
		methodePaiement.setHorizontalAlignment(SwingConstants.CENTER);
		methodePaiement.setForeground(Color.WHITE);
		methodePaiement.setFont(new Font("Tahoma", Font.BOLD, 16));
		methodePaiement.setBounds(731, 444, 124, 34);
		contentPane.add(methodePaiement);
		
	    
		lblIdp = new JLabel("IDR");
		lblIdp.setForeground(Color.WHITE);
		lblIdp.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIdp.setBounds(476, 454, 27, 14);
		contentPane.add(lblIdp);
		
		lblNbrjr = new JLabel("NBRJR");
		lblNbrjr.setForeground(Color.WHITE);
		lblNbrjr.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNbrjr.setBounds(476, 499, 40, 14);
		contentPane.add(lblNbrjr);
		valideDate = new JButton("Valider");
		valideDate.setBounds(538, 564, 64, 23);
		contentPane.add(valideDate);
		
		
		btnAjouterP= new Button();
		btnAjouterP.setText("Valider");
		btnAjouterP.setHorizontalAlignment(SwingConstants.CENTER);
		btnAjouterP.setForeground(SystemColor.textHighlightText);
		btnAjouterP.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAjouterP.setBackground(Color.BLACK);
		btnAjouterP.setBounds(616, 309, 86, 28);
		contentPane.add(btnAjouterP);
		
		btnModifierP = new Button();
		btnModifierP.setText("Modifier");
		btnModifierP.setHorizontalAlignment(SwingConstants.CENTER);
		btnModifierP.setForeground(SystemColor.textHighlightText);
		btnModifierP.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnModifierP.setBackground(Color.BLACK);
		btnModifierP.setBounds(711, 309, 86, 28);
		contentPane.add(btnModifierP);
		btnModifierP.setEnabled(false);
		
		btnSupprimerP= new Button();
		btnSupprimerP.setText("Supprimer");
		btnSupprimerP.setHorizontalAlignment(SwingConstants.CENTER);
		btnSupprimerP.setForeground(SystemColor.textHighlightText);
		btnSupprimerP.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSupprimerP.setBackground(Color.BLACK);
		btnSupprimerP.setBounds(807, 309, 86, 28);
		contentPane.add(btnSupprimerP);
		btnSupprimerP.setEnabled(false);
		
		champRechP = new TextField();
		champRechP.setForeground(SystemColor.textHighlightText);
		champRechP.setFont(new Font("Tahoma", Font.BOLD, 16));
		champRechP.setHorizontalAlignment(SwingConstants.CENTER);
		champRechP.setBounds(476, 304, 122, 34);
		contentPane.add(champRechP);
		
		lblChxdatep = new JLabel("ChxDateP");
		lblChxdatep.setForeground(Color.WHITE);
		lblChxdatep.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblChxdatep.setBounds(476, 536, 62, 14);
		contentPane.add(lblChxdatep);
		
		lblDatep = new JLabel("DateP");
		lblDatep.setForeground(Color.WHITE);
		lblDatep.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDatep.setBounds(476, 607, 62, 14);
		contentPane.add(lblDatep);
		
		panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(685, 492, 2, 172);
		contentPane.add(panel_4);
		
		lblMethPai = new JLabel("Mont Pai");
		lblMethPai.setForeground(Color.WHITE);
		lblMethPai.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMethPai.setBounds(476, 642, 62, 14);
		contentPane.add(lblMethPai);
		
		//EVNT RECHPA
		champRechP.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateFieldsP();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	updateFieldsP();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            	updateFieldsP();
            }

        });
		
		///////////////////// EVENT CLICK TABLE PARTIE PAIEMENT  ===================
		tableP.getSelectionModel().addListSelectionListener(e -> {

				int rowIndex = tableP.getSelectedRow();	
				if (rowIndex != -1) {
					String idTableSelected = (String) tableP.getValueAt(rowIndex, 0);
					String id_reserv = (String) tableP.getValueAt(rowIndex, 1);
					String datePymt = (String) tableP.getValueAt(rowIndex, 2);
					String mount = (String) tableP.getValueAt(rowIndex, 3);
					String methPaymt = (String) tableP.getValueAt(rowIndex, 4);
					

					//remplissage des champs
					idPaiement.setText(idTableSelected) ;
					
					//int idP = Integer.parseInt(idPaiement.getText());
					//new PaiementDao().getIdResByPaiement(idP)+""
					idReservation.setText(id_reserv);
					
					datePaiement.setText( datePymt);
										
					montantPaiement.setText(mount);
					
					methodePaiement.setText(methPaymt);
					
					btnModifierP.setEnabled(true);
					btnSupprimerP.setEnabled(true);
				}else {
					btnModifierP.setEnabled(false);
					btnSupprimerP.setEnabled(false);
				}

		});
		
		

		
		JLabel lblMethP = new JLabel("Meth P");
		lblMethP.setForeground(Color.WHITE);
		lblMethP.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMethP.setBounds(685, 459, 52, 14);
		contentPane.add(lblMethP);
		
		panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(685, 492, 217, 3);
		contentPane.add(panel_5);
		
		prixNuitCha = new TextField();
		prixNuitCha.setHorizontalAlignment(SwingConstants.CENTER);
		prixNuitCha.setForeground(SystemColor.textHighlightText);
		prixNuitCha.setFont(new Font("Tahoma", Font.BOLD, 16));
		prixNuitCha.setEnabled(false);
		prixNuitCha.setBounds(791, 498, 62, 34);
		contentPane.add(prixNuitCha);
		prixNuitCha.setEnabled(false);
		
		JLabel lblPrixn = new JLabel("Prix/N");
		lblPrixn.setForeground(Color.WHITE);
		lblPrixn.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrixn.setBounds(711, 510, 40, 14);
		contentPane.add(lblPrixn);
		
		total = new TextField();
		total.setHorizontalAlignment(SwingConstants.CENTER);
		total.setForeground(SystemColor.textHighlightText);
		total.setFont(new Font("Tahoma", Font.BOLD, 16));
		total.setEnabled(false);
		total.setBounds(791, 548, 77, 34);
		contentPane.add(total);
		total.setEnabled(false);
		
		
		JLabel lblTotal = new JLabel("TOTAL");
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotal.setBounds(714, 560, 40, 14);
		contentPane.add(lblTotal);
		
		resteApayer = new TextField();
		resteApayer.setHorizontalAlignment(SwingConstants.CENTER);
		resteApayer.setForeground(SystemColor.textHighlightText);
		resteApayer.setFont(new Font("Tahoma", Font.BOLD, 16));
		resteApayer.setEnabled(false);
		resteApayer.setBounds(791, 595, 77, 34);
		contentPane.add(resteApayer);
		resteApayer.setEnabled(false);
		
		JLabel lblResteAPayer = new JLabel("RESTE A PAYER");
		lblResteAPayer.setForeground(Color.WHITE);
		lblResteAPayer.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblResteAPayer.setBounds(697, 607, 85, 14);
		contentPane.add(lblResteAPayer);
		
		status = new TextField();
		status.setHorizontalAlignment(SwingConstants.CENTER);
		status.setForeground(SystemColor.textHighlightText);
		status.setBackground(SystemColor.RED);
		status.setFont(new Font("Tahoma", Font.BOLD, 16));
		status.setEnabled(false);
		status.setBounds(823, 635, 21, 23);
		contentPane.add(status);
		
		JLabel lblStatus = new JLabel("STATUS");
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStatus.setBounds(714, 642, 52, 14);
		contentPane.add(lblStatus);
		
		lblRechercher_2 = new JLabel("rechercher");
		lblRechercher_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblRechercher_2.setForeground(SystemColor.textHighlightText);
		lblRechercher_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRechercher_2.setBounds(476, 283, 93, 28);
		contentPane.add(lblRechercher_2);
		


		
		//====================== EVENTS BTNS================================
		
		//Génération PDF => Table Reservation
		JButton btnNewButton = new JButton("New button");
        ImageIcon iconImp = new ImageIcon(this.getClass().getResource("/imprimante.png"));
        Image imp = iconImp.getImage().getScaledInstance(55,70, Image.SCALE_DEFAULT);
        btnNewButton.setIcon(new ImageIcon(imp));
        btnNewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnNewButton.setBounds(321, 633, 40, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblImpr = new JLabel("Impr");
		lblImpr.setForeground(Color.WHITE);
		lblImpr.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblImpr.setBounds(321, 615, 46, 14);
		contentPane.add(lblImpr);
		
		//---------------------- BTN IMPRIMANTE
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JFileChooser jf = new JFileChooser();
				MessageFormat header = new MessageFormat("Liste des Reservations");
				MessageFormat footer = new MessageFormat("Page");
				
				try {
					tableRes.print(JTable.PrintMode.NORMAL,header,footer);
				}catch (Exception ex) {
					// TODO: handle exception
				}
			}
		});
		//---------------------- BTN CLIENT
		btnAjouter.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				if(validateAgeCl() && isValidTel() && isValidMail() &&validateSexe()) {
					Client c = new Client();
					c.setNomC(nomClient.getText());
					c.setPrenomC(prenomCl.getText());
					c.setAdC(adCl.getText());
					c.setAgeC(Integer.parseInt(ageCl.getText()));
					c.setVilleC(villeCl.getText());
					c.setMailC(emailCl.getText());
					c.setSexeC(sexeCl.getText());
					c.setTelC(telCl.getText());				
					c.setPaysC(paysCl.getText());
			
					new ClientDao().save(c);
					ArrayList<Client> Clients = new ClientDao().getAll();
					String columns[] = { "ID", "Nom", "Prenom","Adresse","Age","Ville","Email","Sexe","Tel","Pays"};
					String[][] data = new String[Clients.size()][columns.length];

					int i = 0;
					for (Client s : Clients) {
						data[i][0] = s.getId_client() + "";
						data[i][1] = s.getNomC();
						data[i][2] = s.getPrenomC();
						data[i][3] = s.getAdC();
						data[i][4] = s.getAgeC()+"";
						data[i][5] = s.getVilleC();
						data[i][6] = s.getMailC();
						data[i][7] = s.getSexeC();
						data[i][8] = s.getTelC();
						data[i][9] = s.getPaysC();
						i++;
					}
			        model = new DefaultTableModel(data, columns);
					table.setModel(model);
				}
		
			}
			
		});
		
		//---------------------- BTN RESERVATION
		btnAjouterR.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				if(isValidDate(dateDebutRes.getText())&& isValidDate(dateFinRes.getText())
						&& isValidBeetWeenDate() &&validateIdCh()&&validateIdCl() && validateNbrP()) {
					Reservation res = new Reservation();
					res.setId_ch(Integer.parseInt(id_ch.getText()));
					res.setId_client(Integer.parseInt(id_client.getText()));					
					res.setDateDebRes(dateDebutRes.getText());
					res.setDateFinR(dateFinRes.getText());				
					res.setNbPerso(Integer.parseInt(nbrPers.getText()));
					
					new ReservDao().save(res);
					
					
					ArrayList<Reservation> reservations = new ReservDao().getAll();
					String columnsRes[] = { "ID", "N°Ch", "NomCl","DateDebR","DateFinR","NbrPer"};
					String[][] dataRes = new String[reservations.size()][columnsRes.length];

					int k = 0;
					for (Reservation r : reservations) {
						dataRes[k][0] = r.getId_reserv()+ "";
						dataRes[k][1] = new ChambreDao().getById(r.getId_ch()).getNumCh()+"" ; //id_ch remplacé par le numéro
						dataRes[k][2] = new ClientDao().getById(r.getId_client()).getNomC() ;//id_Cl remplacé par le nom du client
						dataRes[k][3] = r.getDateDebRes();
						dataRes[k][4] = r.getDateFinR();
						dataRes[k][5] = r.getNbPerso()+"";

						k++;
					}

					modelRes= new DefaultTableModel(dataRes,columnsRes);
					tableRes.setModel(modelRes);
					
				}
			}
			
		});
		
		btnModifierR.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				if(isValidDate(dateDebutRes.getText())&& isValidDate(dateFinRes.getText())
						&& isValidBeetWeenDate() &&validateIdCh()&&validateIdCl() && validateNbrP()) {
					
					int id = Integer.parseInt(idReservation.getText());
					
					Reservation res = new ReservDao().getById(id);
					
					res.setId_ch(Integer.parseInt(id_ch.getText()));
					res.setId_client(Integer.parseInt(id_client.getText()));					
					res.setDateDebRes(dateDebutRes.getText());
					res.setDateFinR(dateFinRes.getText());				
					res.setNbPerso(Integer.parseInt(nbrPers.getText()));
					
					new ReservDao().save(res);
					
					
					ArrayList<Reservation> reservations = new ReservDao().getAll();
					String columnsRes[] = { "ID", "N°Ch", "NomCl","DateDebR","DateFinR","NbrPer"};
					String[][] dataRes = new String[reservations.size()][columnsRes.length];

					int k = 0;
					for (Reservation r : reservations) {
						dataRes[k][0] = r.getId_reserv()+ "";
						dataRes[k][1] = new ChambreDao().getById(r.getId_ch()).getNumCh()+"" ; //id_ch remplacé par le numéro
						dataRes[k][2] = new ClientDao().getById(r.getId_client()).getNomC() ;//id_Cl remplacé par le nom du client
						dataRes[k][3] = r.getDateDebRes();
						dataRes[k][4] = r.getDateFinR();
						dataRes[k][5] = r.getNbPerso()+"";

						k++;
					}

					modelRes= new DefaultTableModel(dataRes,columnsRes);
					tableRes.setModel(modelRes);
					btnModifierR.setEnabled(false);
				}
			}
			
		});
		
		
		btnSupprimerR.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				if(isValidDate(dateDebutRes.getText())&& isValidDate(dateFinRes.getText())
						&& isValidBeetWeenDate() &&validateIdCh()&&validateIdCl() && validateNbrP()) {
					
					int id = Integer.parseInt(idReservation.getText());
					
					Reservation res = new ReservDao().getById(id);
					
					//Message de confirmation
					int input = JOptionPane.showConfirmDialog(null, "êtes vous sûr ? Vous ne verrez plus cette ligne");
					if (input == 0) {
						//suppression de l'objet avec son id						
						new ReservDao().delete(res.getId_reserv());
					}
					
					ArrayList<Reservation> reservations = new ReservDao().getAll();
					String columnsRes[] = { "ID", "N°Ch", "NomCl","DateDebR","DateFinR","NbrPer"};
					String[][] dataRes = new String[reservations.size()][columnsRes.length];

					int k = 0;
					for (Reservation r : reservations) {
						dataRes[k][0] = r.getId_reserv()+ "";
						dataRes[k][1] = new ChambreDao().getById(r.getId_ch()).getNumCh()+"" ; //id_ch remplacé par le numéro
						dataRes[k][2] = new ClientDao().getById(r.getId_client()).getNomC() ;//id_Cl remplacé par le nom du client
						dataRes[k][3] = r.getDateDebRes();
						dataRes[k][4] = r.getDateFinR();
						dataRes[k][5] = r.getNbPerso()+"";

						k++;
					}

					modelRes= new DefaultTableModel(dataRes,columnsRes);
					tableRes.setModel(modelRes);
					btnSupprimerR.setEnabled(false);
				}
			}
			
		});
		
		//---------------------- BTN PAIEMENT
		valideDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    SimpleDateFormat dcn = new SimpleDateFormat("yyyy-MM-dd");
				    String date = dcn.format(dateChooser.getDate() );

				    datePaiement.setText(date.toString());

				}catch (NullPointerException d) {

			    	JOptionPane.showMessageDialog(null, "Veuillez saisir une date");

				}

			}
		});

		btnAjouterP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Reservation objRToValidId = new  ReservDao().getById(Integer.parseInt(idReservation.getText()));
				if(isValidDate(datePaiement.getText()) &&objRToValidId != null 
						&&validateMountP() 
						&& validateMetP()) 
				{
					
					//Gestion Paiement
					
					Float t = Float.parseFloat(total.getText());
					Float mtP = Float.parseFloat(montantPaiement.getText());
					Float rest = t-mtP;	
					if(rest==0) {
						status.setBackground(SystemColor.GREEN);
					}else if(rest <=0){
						status.setBackground(SystemColor.GREEN);
						JOptionPane.showConfirmDialog(null, "Nous vous devons : "+ resteApayer.getText());
					}
					resteApayer.setText(rest+"");
					
					Paiement p = new Paiement();
					p.setId_reserv(Integer.parseInt(idReservation.getText()));
					p.setDateP(datePaiement.getText());					
					p.setMontP(Float.parseFloat(montantPaiement.getText()));
					p.setMethP(methodePaiement.getText());				
					
					new PaiementDao().save(p);
									
					
					ArrayList<Paiement> paiements = new PaiementDao().getAll();
					String columnsP[] = { "ID", "N°Res", "DatePaiement","MontantP","MethodeP"};
					String[][] dataP = new String[paiements.size()][columnsP.length];

					int l = 0;
					for (Paiement p1 : paiements) {
						dataP[l][0] = p1.getId_p()+ "";
						dataP[l][1] = p1.getId_reserv()+"";
						dataP[l][2] = p1.getDateP();
						dataP[l][3] = p1.getMontP()+"";
						dataP[l][4] = p1.getMethP()+"";
						l++;
					}

					modelP= new DefaultTableModel(dataP,columnsP);
					tableP.setModel(modelP);
					
				}
				
			}
		});
		
		
		btnModifierP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//verif si IDRESERV n'est pas null
				Reservation objRToValidId = new  ReservDao().getById(Integer.parseInt(idReservation.getText()));
				if(isValidDate(datePaiement.getText()) &&objRToValidId != null 
						&&validateMountP() 
						&& validateMetP()) 
				{
					int idpymt = Integer.parseInt(idPaiement.getText());
					Paiement p = new PaiementDao().getById(idpymt);
					
					p.setId_reserv(Integer.parseInt(idReservation.getText()));
					p.setDateP(datePaiement.getText());					
					p.setMontP(Float.parseFloat(montantPaiement.getText()));
					p.setMethP(methodePaiement.getText());				
					
					
					new PaiementDao().save(p);
									
					
					ArrayList<Paiement> paiements = new PaiementDao().getAll();
					String columnsP[] = { "ID", "N°Res", "DatePaiement","MontantP","MethodeP"};
					String[][] dataP = new String[paiements.size()][columnsP.length];

					int l = 0;
					for (Paiement p1 : paiements) {
						dataP[l][0] = p1.getId_p()+ "";
						dataP[l][1] = p1.getId_reserv()+"";
						dataP[l][2] = p1.getDateP();
						dataP[l][3] = p1.getMontP()+"";
						dataP[l][4] = p1.getMethP()+"";
						l++;
					}

					modelP= new DefaultTableModel(dataP,columnsP);
					tableP.setModel(modelP);
					
				}
				btnModifierP.setEnabled(false);
			}
		});
		
		
		btnSupprimerP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//verif si IDRESERV n'est pas null
				Reservation objRToValidId = new  ReservDao().getById(Integer.parseInt(idReservation.getText()));
				if(isValidDate(datePaiement.getText()) &&objRToValidId != null 
						&&validateMountP() 
						&& validateMetP()) 
				{
					int idpymt = Integer.parseInt(idPaiement.getText());
					Paiement p = new PaiementDao().getById(idpymt);
					
					int input = JOptionPane.showConfirmDialog(null, "êtes vous sûr ? Vous ne verrez plus cette ligne");
					if (input == 0) {
						//suppression de l'objet avec son id						
						new PaiementDao().delete(p.getId_p());
					}

														
					ArrayList<Paiement> paiements = new PaiementDao().getAll();
					String columnsP[] = { "ID", "N°Res", "DatePaiement","MontantP","MethodeP"};
					String[][] dataP = new String[paiements.size()][columnsP.length];

					int l = 0;
					for (Paiement p1 : paiements) {
						dataP[l][0] = p1.getId_p()+ "";
						dataP[l][1] = p1.getId_reserv()+"";
						dataP[l][2] = p1.getDateP();
						dataP[l][3] = p1.getMontP()+"";
						dataP[l][4] = p1.getMethP()+"";
						l++;
					}

					modelP= new DefaultTableModel(dataP,columnsP);
					tableP.setModel(modelP);
					
				}
				btnSupprimerP.setEnabled(false);
			}
		});
		
		
		
	}
	
	
	
	
	
	
	
	//==================================================================================
	//******************************** UTILS ******************************************
	//==================================================================================
	//------------------------------ Recherche Client
	private void updateFields() {
        wordCherched = champRech.getText();
        

        if (!wordCherched.isEmpty()) {
			ArrayList<Client> Clients = new ClientDao().recherche(wordCherched);
			String columns[] = { "ID", "Nom", "Prenom","Adresse","Age","Ville","Email","Sexe","Tel","Paays"};
			String[][] data = new String[Clients.size()][columns.length];
	
			int i = 0;
			for (Client s : Clients) {
				data[i][0] = s.getId_client() + "";
				data[i][1] = s.getNomC();
				data[i][2] = s.getPrenomC();
				data[i][3] = s.getAdC();
				data[i][4] = s.getAgeC()+"";
				data[i][5] = s.getVilleC();
				data[i][6] = s.getMailC();
				data[i][7] = s.getSexeC();
				data[i][8] = s.getTelC();
				data[i][9] = s.getPaysC();
				i++;
			}
	
				model = new DefaultTableModel(data,columns);
				table.setModel(model);
        } 
        else {
			ArrayList<Client> Clients = new ClientDao().getAll();
			String columns[] = { "ID", "Nom", "Prenom","Adresse","Age","Ville","Email","Sexe","Tel","Paays"};
			String[][] data = new String[Clients.size()][columns.length];
	
			int i = 0;
			for (Client s : Clients) {
				data[i][0] = s.getId_client() + "";
				data[i][1] = s.getNomC();
				data[i][2] = s.getPrenomC();
				data[i][3] = s.getAdC();
				data[i][4] = s.getAgeC()+"";
				data[i][5] = s.getVilleC();
				data[i][6] = s.getMailC();
				data[i][7] = s.getSexeC();
				data[i][8] = s.getTelC();
				data[i][9] = s.getPaysC();
				i++;
			}
	
			model = new DefaultTableModel(data,columns);
			table.setModel(model);
        }
        
        
    }
	
	
	//------------------------------ Recherche Reservation
	private void updateFieldsR() {
        wordCherchedRes = champRechR.getText();
        

        if (!wordCherchedRes.isEmpty()) {
    		ArrayList<Reservation> reservations = new ReservDao().recherche(wordCherchedRes);
    		String columnsRes[] = { "ID", "N°Ch", "NomCl","DateDebR","DateFinR","NbrPer"};
    		String[][] dataRes = new String[reservations.size()][columnsRes.length];

    		int k = 0;
    		for (Reservation r : reservations) {
    			dataRes[k][0] = r.getId_reserv()+ "";
    			dataRes[k][1] = new ChambreDao().getById(r.getId_reserv()).getNumCh()+"" ; 
    			dataRes[k][2] = new ClientDao().getById(r.getId_reserv()).getNomC() ;
    			dataRes[k][3] = r.getDateDebRes();
    			dataRes[k][4] = r.getDateFinR();
    			dataRes[k][5] = r.getNbPerso()+"";
    			k++;
    		}

    		modelRes= new DefaultTableModel(dataRes,columnsRes);
    		tableRes.setModel(modelRes);
        } 
        else {
    		ArrayList<Reservation> reservations = new ReservDao().getAll();
    		String columnsRes[] = { "ID", "N°Ch", "NomCl","DateDebR","DateFinR","NbrPer"};
    		String[][] dataRes = new String[reservations.size()][columnsRes.length];

    		int k = 0;
    		for (Reservation r : reservations) {
    			dataRes[k][0] = r.getId_reserv()+ "";
    			dataRes[k][1] = new ChambreDao().getById(r.getId_ch()).getNumCh()+"" ; 
    			dataRes[k][2] = new ClientDao().getById(r.getId_client()).getNomC() ;
    			dataRes[k][3] = r.getDateDebRes();
    			dataRes[k][4] = r.getDateFinR();
    			dataRes[k][5] = r.getNbPerso()+"";

    			k++;
    		}

    		modelRes= new DefaultTableModel(dataRes,columnsRes);
    		tableRes.setModel(modelRes);
        }
    }
	
	//------------------------------ Recherche PAIEMENTS
	private void updateFieldsP() {
        wordCherchedP = champRechP.getText();
        

        if (!wordCherchedP.isEmpty()) {
    		ArrayList<Paiement> paiements = new PaiementDao().recherche(wordCherchedP);
    		String columnsP[] = { "ID", "N°Res", "DatePaiement","MontantP","MethodeP"};
    		String[][] dataP = new String[paiements.size()][columnsP.length];

    		int l = 0;
    		for (Paiement p : paiements) {
    			dataP[l][0] = p.getId_p()+ "";
    			dataP[l][1] = p.getId_reserv()+"";
    			dataP[l][2] = p.getDateP();
    			dataP[l][3] = p.getMontP()+"";
    			dataP[l][4] = p.getMethP()+"";
    			l++;
    		}

    		modelP= new DefaultTableModel(dataP,columnsP);
    		tableP.setModel(modelP);
        } 
        else {
    		ArrayList<Paiement> paiements = new PaiementDao().getAll();
    		String columnsP[] = { "ID", "N°Res", "DatePaiement","MontantP","MethodeP"};
    		String[][] dataP = new String[paiements.size()][columnsP.length];

    		int l = 0;
    		for (Paiement p : paiements) {
    			dataP[l][0] = p.getId_p()+ "";
    			dataP[l][1] = p.getId_reserv()+"";
    			dataP[l][2] = p.getDateP();
    			dataP[l][3] = p.getMontP()+"";
    			dataP[l][4] = p.getMethP()+"";
    			l++;
    		}

    		modelP= new DefaultTableModel(dataP,columnsP);
    		tableP.setModel(modelP);
        }
    }
	//============================= Control de saisi du Mail
	public boolean isValidMail() {
		Pattern mailValid = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = mailValid.matcher(emailCl.getText());
		if(!matcher.matches()) {
			JOptionPane.showMessageDialog(null, " veuillez saisir un Mail valide");
			return false;
		}
		return true;
	}
	
	//============================ Control Telephone
	public boolean isValidTel() {
		Pattern mailValid = Pattern.compile(
				"^(?:(?:\\+|00)33[\\s.-]{0,3}(?:\\(0\\)[\\s.-]{0,3})?|0)[1-9](?:(?:[\\s.-]?\\d{2}){4}|\\d{2}(?:[\\s.-]?\\d{3}){2})$");
		Matcher matcher = mailValid.matcher(telCl.getText());
		if(!matcher.matches()) {
			JOptionPane.showMessageDialog(null, "Veuillez saisir un tel au format '09-76-56-34-12''");
			return false;
			
		}
		return true;

	}
	
	//============================ Control Sexe
    private boolean validateSexe() {
        String valParking = sexeCl.getText();
        if (!valParking.equalsIgnoreCase("F") && !valParking.equalsIgnoreCase("H")) {
            JOptionPane.showMessageDialog(null, "Veuillez saisir 'H' ou 'F' ");
            return false;
        }
        return true;
    }
    
    //====================== control age du client
    private boolean validateAgeCl() {
        try {
            int valNumCh = Integer.parseInt(ageCl.getText());
            if (valNumCh <=0) {
                JOptionPane.showMessageDialog(null, "l'age doit etre un entier Positif");
                ageCl.requestFocus();
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Veuillez saisir un nombre valide Pour Age Client");
            ageCl.requestFocus();
            return false;
        }
        
    }
    
    //==================== Control date
	public  boolean isValidDate(String d) {	
		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		df.setLenient(false);
		Date dte = null;
		try {
			dte = df.parse(d);			
			return true;
		} catch (ParseException pE) {
			JOptionPane.showMessageDialog(null, "Veuillez saisir une date au format yyyy-mm-dd");
			return false;
		}
	}
	
	public boolean isValidBeetWeenDate() {	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		sdf.setLenient(false);
		try {
			Date date1 = sdf.parse(dateDebutRes.getText());
			Date date2 = sdf.parse(dateFinRes.getText());
			if (date1.before(date2)) {
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Date dé&but de réservation doit etre inferieure \nà la date de fin de reservation");			
			}
		} catch (ParseException p) {
			JOptionPane.showMessageDialog(null, "Saisir une date valide");
		}
		return false;
	}
	

    private boolean validateIdCh() {
    	//int lenghttabSociete = new ChambreDao().getAll().size();
        try {
            int valId_soc = Integer.parseInt(id_ch.getText());
            Chambre ch = new ChambreDao().getById(valId_soc);
            if (ch ==null) {
                JOptionPane.showMessageDialog(null, "Veuillez saisir un ID de Chambre valide");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(null, "Veuillez Remplacer le num de la chambre par un ID");
            return false;
        }
    }
    
    private boolean validateIdCl() {
    	int lenghttabSociete = new ClientDao().getAll().size();
        try {
            int valId_soc = Integer.parseInt(id_client.getText());
            if (valId_soc < 1 || valId_soc > lenghttabSociete) {
                JOptionPane.showMessageDialog(null, "Veuillez saisir un ID de Client valide: entre 1 et " + lenghttabSociete);
                return false;
            }
            return true;
        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(null, "Veuillez Remplacer Nom Client par un ID");
            return false;
        }
           
        
    }
	
    private boolean validateNbrP() {
        try {
            int valNumCh = Integer.parseInt(nbrPers.getText());
            if (valNumCh <=0 || valNumCh >= 5) {
                JOptionPane.showMessageDialog(null, "le nomvre de personnes doit etre comprise 1 et 5");            
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Veuillez saisir un nombre valide ");
            return false;
        }
    }
    
	public float nbJourRes(String d1, String d2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		sdf.setLenient(false);
		try {
			Date dateAvant = sdf.parse(d1);
			Date dateApres = sdf.parse(d2);

			long diff = dateApres.getTime() - dateAvant.getTime();
			float res = (diff / (1000 * 60 * 60 * 24));
			//System.out.println("Nombre de jours entre les deux dates est: " + res);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
    //====================== control Paiement Montant
    private boolean validateMountP() {
        try {
            float valNumCh = Float.parseFloat(montantPaiement.getText());
            if (valNumCh <=0) {
                JOptionPane.showMessageDialog(null, "le prix doit etre un entier Positif");             
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Veuillez saisir un nombre valide Pour le montant à payer");
            return false;
        }
        
    }
    
    private boolean validateMetP() {
        String valParking = methodePaiement.getText();
        if (!valParking.equalsIgnoreCase("CB") 
        		&& !valParking.equalsIgnoreCase("CHEQUE")
        		&& !valParking.equalsIgnoreCase("ESPECE")
        		) {
            JOptionPane.showMessageDialog(null, "Veuillez saisir 'CB' ou 'CHEQUE' ou 'ESPECEs' ");
            return false;
        }
        return true;
    }
}
