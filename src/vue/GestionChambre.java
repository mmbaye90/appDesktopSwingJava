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
import dao.ChambreDao;
import dao.HotelDao;
import dao.SocieteDao;
import entites.Chambre;
import entites.Db;
import entites.Hotel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Icon;

public class GestionChambre extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TextField idChambre;
	private TextField idHotel;
	private TextField numChambre;
	private TextField nbrLitSimp;
	private TextField nbrLitDou;
	private TextField superficie;
	private TextField sbdPrvative;
	private TextField tv;
	private TextField balcon;
	private TextField refrigerateur;
	private TextField baignoire;
	private TextField insono;
	private TextField prixNuit;
	private TextField idHotelClefEtr;
	private JLabel container;
	
	private String wordCherched;
	private Button btnAjouter;
	private Button btnModifier;
	private Button btnSuppriemr;
	
	TextField champRech;
	DefaultTableModel model;
	JTable table;
	private JLabel lblNewLabel;
	private JLabel lblIc;
	private JLabel lblIh;
	private JLabel lblLs;
	private JLabel lblLd;
	private JLabel lblTv;
	private JLabel lblNc;
	private JLabel lblBal;
	private JLabel lblSdb;
	private JLabel lblRef;
	private JLabel lblSup;
	private JLabel lblBai;
	private JLabel lblSon;
	private JLabel lblSon_1;
	private JLabel lblPrix;
	
	
	private Image photoChambre;
	private JLabel contPhoto;
	JPanel panelPhoto;
	JLabel lblNomChambre;
	JLabel nomChambre;
	Image imgP;
	private JLabel lblAdresse;
	private JLabel valHotDansInfo;
	private JLabel iParking;
	private JLabel iParking_1;
	private JLabel iParking_2;
	private JLabel iParking_3;
	private JLabel iParking_4;
	private JLabel iParking_5;
	private JLabel valParking;
	private JLabel valWifi;
	private JLabel valPiscine;
	private JLabel valNav;
	private JLabel valAnim;
	private JLabel valCat;
	private JPanel panel;
	private JLabel lblServicesDeLa;
	private JLabel iParking_6;
	private JLabel iParking_7;
	private JLabel iParking_8;
	private JLabel iParking_9;
	private JLabel iParking_10;
	private JLabel iParking_11;
	private JLabel iParking_12;
	private JLabel valsdp;
	private JLabel valBalcon;
	private JLabel valEcran;
	private JLabel valRef;
	private JLabel valBai;
	private JLabel valSono;
	private JLabel valPrix;
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionChambre frame = new GestionChambre();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GestionChambre() {
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
		JLabel titre = new JLabel("GESTION DES CHAMBRES");
		titre.setForeground(SystemColor.textHighlightText);
		titre.setFont(new Font("Tahoma", Font.BOLD, 16));
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setBounds(194, 0, 235, 28);
		

		
		
		//Ajout ChampRecherche
		champRech = new TextField();
		champRech.setForeground(SystemColor.textHighlightText);
		champRech.setFont(new Font("Tahoma", Font.BOLD, 16));
		champRech.setHorizontalAlignment(SwingConstants.CENTER);
		champRech.setBounds(597, 0, 149, 40);
		contentPane.add(titre);
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
		
		//Db.connect();
        ArrayList<Chambre> chambres = new ChambreDao().getAll();
        String columns[] = { "ID", "IDH", "N°Ch", "LitS", "LitD", "Sup", "SdbP", "tv","Bal", "ref", "Baig", "Inso", "Prix" };
        String[][] data = new String[chambres.size()][columns.length];

        int i = 0;
        for (Chambre c : chambres) {
            data[i][0] = c.getId_ch()+"";
            data[i][1] = new HotelDao().getById(c.getId_hotel()).getNomH(); 
            data[i][2] = c.getNumCh()+"";
            data[i][3] = c.getNbLitChSimp()+"";
            data[i][4] = c.getNbLitChDoub()+"";
            data[i][5] = c.getSupCh()+"";
            data[i][6] = c.getSalleBainP();
            data[i][7] = c.getTvCh();
            data[i][8] = c.getBalconCh();
            data[i][9] = c.getRefrigerCh();
            data[i][10] =  c.getBaignCh();
            data[i][11] =  c.getInsonoCh();
            data[i][12] = c.getPrixNtCh()+"";
            i++;
        }

        model = new DefaultTableModel(data, columns);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 125, 726, 113);
        contentPane.add(scrollPane);
		
		//======================================== Ajout des Champs Text ============
		//-----id_societe fremplace par le nom de la societé
        idChambre = new TextField();
        idChambre.setForeground(SystemColor.textHighlightText);
        idChambre.setFont(new Font("Tahoma", Font.BOLD, 16));
        idChambre.setHorizontalAlignment(SwingConstants.CENTER);
        idChambre.setBounds(10, 39, 39, 34);
		contentPane.add(idChambre);
		idChambre.setVisible(false);
		numChambre = new TextField();
		numChambre.setHorizontalAlignment(SwingConstants.CENTER);
		numChambre.setForeground(SystemColor.textHighlightText);
		numChambre.setFont(new Font("Tahoma", Font.BOLD, 16));
		numChambre.setBounds(251, 39, 39, 34);
		contentPane.add(numChambre);
		
		nbrLitSimp= new TextField();
		nbrLitSimp.setHorizontalAlignment(SwingConstants.CENTER);
		nbrLitSimp.setForeground(SystemColor.textHighlightText);
		nbrLitSimp.setFont(new Font("Tahoma", Font.BOLD, 16));
		nbrLitSimp.setBounds(108, 39, 39, 34);
		contentPane.add(nbrLitSimp);
		
		nbrLitDou = new TextField();
		nbrLitDou.setHorizontalAlignment(SwingConstants.CENTER);
		nbrLitDou.setForeground(SystemColor.textHighlightText);
		nbrLitDou.setFont(new Font("Tahoma", Font.BOLD, 16));
		nbrLitDou.setBounds(157, 39, 39, 34);
		contentPane.add(nbrLitDou);
		
		superficie = new TextField();
		superficie.setHorizontalAlignment(SwingConstants.CENTER);
		superficie.setForeground(SystemColor.textHighlightText);
		superficie.setFont(new Font("Tahoma", Font.BOLD, 16));
		superficie.setBounds(10, 84, 39, 34);
		contentPane.add(superficie);
		
		sbdPrvative = new TextField();
		sbdPrvative.setHorizontalAlignment(SwingConstants.CENTER);
		sbdPrvative.setForeground(SystemColor.textHighlightText);
		sbdPrvative.setFont(new Font("Tahoma", Font.BOLD, 16));
		sbdPrvative.setBounds(350, 39, 39, 34);
		contentPane.add(sbdPrvative);
		
		tv = new TextField();
		tv.setHorizontalAlignment(SwingConstants.CENTER);
		tv.setForeground(SystemColor.textHighlightText);
		tv.setFont(new Font("Tahoma", Font.BOLD, 16));
		tv.setBounds(204, 39, 39, 34);
		contentPane.add(tv);
		
		balcon = new TextField();
		balcon.setHorizontalAlignment(SwingConstants.CENTER);
		balcon.setForeground(SystemColor.textHighlightText);
		balcon.setFont(new Font("Tahoma", Font.BOLD, 16));
		balcon.setBounds(300, 39, 39, 34);
		contentPane.add(balcon);
		
		refrigerateur = new TextField();
		refrigerateur.setHorizontalAlignment(SwingConstants.CENTER);
		refrigerateur.setForeground(SystemColor.textHighlightText);
		refrigerateur.setFont(new Font("Tahoma", Font.BOLD, 16));
		refrigerateur.setBounds(407, 39, 39, 34);
		contentPane.add(refrigerateur);
		
		baignoire = new TextField();
		baignoire.setHorizontalAlignment(SwingConstants.CENTER);
		baignoire.setForeground(SystemColor.textHighlightText);
		baignoire.setFont(new Font("Tahoma", Font.BOLD, 16));
		baignoire.setBounds(59, 84, 39, 34);
		contentPane.add(baignoire);
		
		insono = new TextField();
		insono.setHorizontalAlignment(SwingConstants.CENTER);
		insono.setForeground(SystemColor.textHighlightText);
		insono.setFont(new Font("Tahoma", Font.BOLD, 16));
		insono.setBounds(108, 84, 39, 34);
		contentPane.add(insono);
		
		prixNuit = new TextField();
		prixNuit.setHorizontalAlignment(SwingConstants.CENTER);
		prixNuit.setForeground(SystemColor.textHighlightText);
		prixNuit.setFont(new Font("Tahoma", Font.BOLD, 16));
		prixNuit.setBounds(157, 84, 67, 34);
		contentPane.add(prixNuit);
		
		idHotelClefEtr = new TextField();
		idHotelClefEtr.setHorizontalAlignment(SwingConstants.CENTER);
		idHotelClefEtr.setForeground(SystemColor.textHighlightText);
		idHotelClefEtr.setFont(new Font("Tahoma", Font.BOLD, 16));
		idHotelClefEtr.setBounds(59, 39, 39, 34);
		contentPane.add(idHotelClefEtr);
		
		nomChambre = new JLabel();
		nomChambre.setForeground(Color.WHITE);
		nomChambre.setFont(new Font("Tahoma", Font.BOLD, 13));
		nomChambre.setBounds(108, 408, 135, 15);
		contentPane.add(nomChambre);
		nomChambre.setVisible(false);
		
		
		
		//======================== EVENT SELECTION TABLE ==============
		table.getSelectionModel().addListSelectionListener(e -> {

			int rowIndex = table.getSelectedRow();
			if (rowIndex != -1) {
				String idTableSelected = (String) table.getValueAt(rowIndex, 0);
				String id_hotel = (String) table.getValueAt(rowIndex, 1);
				String num_cha = (String) table.getValueAt(rowIndex, 2);
				String nbrLitD = (String) table.getValueAt(rowIndex, 3);
				String nbrLS = (String) table.getValueAt(rowIndex, 4);
				String superficieC = (String) table.getValueAt(rowIndex, 5);
				String sbd_priv = (String) table.getValueAt(rowIndex, 6);
				String tvC = (String) table.getValueAt(rowIndex, 7);
				String balconC = (String) table.getValueAt(rowIndex, 8);
				String refrig = (String) table.getValueAt(rowIndex, 9);
				String baign = (String) table.getValueAt(rowIndex, 10);
				String insonor = (String) table.getValueAt(rowIndex, 11);
				String prix = (String) table.getValueAt(rowIndex, 12);
				
				//Les chmps
				idHotel.setText(id_hotel);
				numChambre.setText(num_cha);
				nbrLitSimp.setText(nbrLS);
				nbrLitDou.setText(nbrLitD);
				superficie.setText(superficieC);
				sbdPrvative.setText(sbd_priv);
				tv.setText(tvC);
				balcon.setText(balconC);
				refrigerateur.setText(refrig);
				baignoire.setText(baign);
				insono.setText(insonor);
				prixNuit.setText(prix);
				idChambre.setText(idTableSelected);
				
				//ID CHMABRE en int
				int id_ch = Integer.parseInt(idChambre.getText());

				idHotelClefEtr.setText(new ChambreDao().getIdHotelByChambre(id_ch)+"");

				//=============================== Affichage dynamique  des informations
				//num de la Chambre à afficher à la selection d'une ligne
				String nH = numChambre.getText();
				nomChambre.setText(nH);
				
				//Si aucune image trouvée, afficher une icone d'image
				try {
					 ImageIcon photoCh = new ImageIcon(this.getClass().getResource("/chambre"+id_ch+".jpg"));
					 photoChambre = photoCh.getImage().getScaledInstance(230, 190, Image.SCALE_DEFAULT);  
					 contPhoto = new JLabel();
					 contPhoto .setIcon(new ImageIcon(photoChambre));
					 contPhoto.setHorizontalAlignment(SwingConstants.CENTER);
					 contPhoto.setBounds(20, 249, 206, 148);
					 
					 panelPhoto = new JPanel();
					 panelPhoto.setBounds(15, 240, 210, 152);
					 panelPhoto.add(contPhoto);
					 
					 contentPane.add(panelPhoto);
					 
					 //Reload Page => Changement de photo				
					 panelPhoto.revalidate();
					 panelPhoto.repaint();
				} catch (NullPointerException n ) {
					 ImageIcon photoCh = new ImageIcon(this.getClass().getResource("/iChambre.png"));
					 photoChambre = photoCh.getImage().getScaledInstance(230, 190, Image.SCALE_DEFAULT);  
					 contPhoto = new JLabel();
					 contPhoto .setIcon(new ImageIcon(photoChambre));
					 contPhoto.setHorizontalAlignment(SwingConstants.CENTER);
					 contPhoto.setBounds(20, 249, 206, 148);
					 
					 panelPhoto = new JPanel();
					 panelPhoto.setBounds(15, 240, 210, 152);
					 panelPhoto.add(contPhoto);
					 contentPane.add(panelPhoto);
					 panelPhoto.revalidate();
					 panelPhoto.repaint();
				}

				lblNomChambre.setVisible(true);
				nomChambre.setVisible(true);
				
				valHotDansInfo.setText(idHotel.getText());
				
				//Utilisation de la clef Etrangere de l'hotel pour acceder aux datas de Htl
				int id_hot = Integer.parseInt(idHotelClefEtr.getText()); 
				valParking.setText(new HotelDao().getById(id_hot).getParkingH());
				valWifi.setText(new HotelDao().getById(id_hot).getWifiH());
				valPiscine.setText(new HotelDao().getById(id_hot).getPiscineH());
				valNav.setText(new HotelDao().getById(id_hot).getNavetteH());
				valAnim.setText(new HotelDao().getById(id_hot).getPresenceAniH());
				valCat.setText(new HotelDao().getById(id_hot).getCatH()+"");
				btnAjouter.setEnabled(true);
				btnModifier.setEnabled(true);
				btnSuppriemr.setEnabled(true);
				
				//Remplissage icons de la chambre
				valsdp.setText(sbdPrvative.getText());
				valBalcon.setText(balcon.getText());
				valEcran.setText(tv.getText());
				valRef.setText(refrigerateur.getText());
				valBai.setText(baignoire.getText());
				valSono.setText(insono.getText());
				valPrix.setText(prixNuit.getText());
			} 
			else {
				btnModifier.setEnabled(false);
				btnSuppriemr.setEnabled(false);
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
		btnAjouter.setBounds(434, 90, 86, 28);
		contentPane.add(btnAjouter);
		//------ Modifier
		btnModifier = new Button();
		btnModifier.setText("Modifier");
		btnModifier.setForeground(SystemColor.textHighlightText);
		btnModifier.setBackground(Color.BLACK);
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnModifier.setHorizontalAlignment(SwingConstants.CENTER);
		btnModifier.setBounds(530, 90, 95, 28);
		contentPane.add(btnModifier);
		//Supp
		btnSuppriemr = new Button();
		btnSuppriemr.setText("Supprimer");
		btnSuppriemr.setForeground(SystemColor.textHighlightText);
		btnSuppriemr.setBackground(Color.BLACK);
		btnSuppriemr.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSuppriemr.setHorizontalAlignment(SwingConstants.CENTER);
		btnSuppriemr.setBounds(635, 90, 101, 28);
		contentPane.add(btnSuppriemr);
		
		btnAjouter.setEnabled(false);
		btnModifier.setEnabled(false);
		btnSuppriemr.setEnabled(false);
		
		lblIc = new JLabel("IC");
		lblIc.setForeground(Color.WHITE);
		lblIc.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIc.setBounds(20, 26, 18, 14);
		contentPane.add(lblIc);
		lblIc.setVisible(false);
		
		lblIh = new JLabel("IH");
		lblIh.setForeground(Color.WHITE);
		lblIh.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIh.setBounds(70, 26, 18, 14);
		contentPane.add(lblIh);
		
		lblLs = new JLabel("LS");
		lblLs.setForeground(Color.WHITE);
		lblLs.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLs.setBounds(118, 26, 18, 14);
		contentPane.add(lblLs);
		
		lblLd = new JLabel("LD");
		lblLd.setForeground(Color.WHITE);
		lblLd.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLd.setBounds(166, 26, 18, 14);
		contentPane.add(lblLd);
		
		lblTv = new JLabel("TV");
		lblTv.setForeground(Color.WHITE);
		lblTv.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTv.setBounds(214, 26, 18, 14);
		contentPane.add(lblTv);
		
		lblNc = new JLabel("N°C");
		lblNc.setForeground(Color.WHITE);
		lblNc.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNc.setBounds(260, 26, 30, 14);
		contentPane.add(lblNc);
		
		lblBal = new JLabel("Bal");
		lblBal.setForeground(Color.WHITE);
		lblBal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBal.setBounds(309, 26, 30, 14);
		contentPane.add(lblBal);
		
		lblSdb = new JLabel("Sdb");
		lblSdb.setForeground(Color.WHITE);
		lblSdb.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSdb.setBounds(359, 26, 30, 14);
		contentPane.add(lblSdb);
		
		lblRef = new JLabel("Ref");
		lblRef.setForeground(Color.WHITE);
		lblRef.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRef.setBounds(416, 26, 30, 14);
		contentPane.add(lblRef);
		
		lblSup = new JLabel("Sup");
		lblSup.setForeground(Color.WHITE);
		lblSup.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSup.setBounds(20, 70, 30, 14);
		contentPane.add(lblSup);
		
		lblBai = new JLabel("Bai");
		lblBai.setForeground(Color.WHITE);
		lblBai.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBai.setBounds(68, 70, 30, 14);
		contentPane.add(lblBai);
		
		lblSon = new JLabel("Son");
		lblSon.setForeground(Color.WHITE);
		lblSon.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSon.setBounds(118, 71, 30, 14);
		contentPane.add(lblSon);
		
		lblSon_1 = new JLabel("Son");
		lblSon_1.setForeground(Color.WHITE);
		lblSon_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSon_1.setBounds(167, 82, 30, 14);
		contentPane.add(lblSon_1);
		
		lblPrix = new JLabel("Prix");
		lblPrix.setForeground(Color.WHITE);
		lblPrix.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrix.setBounds(178, 71, 30, 14);
		contentPane.add(lblPrix);
		
		lblNomChambre = new JLabel("N° Chambre: ");
		lblNomChambre.setForeground(Color.WHITE);
		lblNomChambre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNomChambre.setBounds(11, 408, 111, 14);
		contentPane.add(lblNomChambre);
		lblNomChambre.setVisible(false);
		
		JPanel containerInformations = new JPanel();
		containerInformations.setBackground(new Color(255, 255, 255));
		containerInformations.setBounds(260, 243, 476, 191);
		contentPane.add(containerInformations);
		containerInformations.setLayout(null);
		
		lblNewLabel = new JLabel("Service de l'Hotel : ");
		lblNewLabel.setBounds(65, 2, 133, 14);
		containerInformations.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(64, 0, 64));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		valHotDansInfo = new JLabel("");
		valHotDansInfo.setBackground(new Color(255, 255, 255));
		valHotDansInfo.setForeground(new Color(0, 0, 0));
		valHotDansInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		valHotDansInfo.setBounds(191, 1, 86, 14);
		containerInformations.add(valHotDansInfo);
		
		//---------------------------------- ICONS --------------
		//Chargement Logo et configuration Style
		//--------- icon Parking
        ImageIcon img = new ImageIcon(this.getClass().getResource("/iParking.png"));
        imgP = img.getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT);
		iParking = new JLabel(new ImageIcon(imgP));
		iParking.setHorizontalAlignment(SwingConstants.CENTER);
		iParking.setBounds(10, 23, 25, 31);
		containerInformations.add(iParking);
		//------- icon Wifi
        ImageIcon imgWifi = new ImageIcon(this.getClass().getResource("/iWifi.png"));
        imgP = imgWifi.getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT);
		iParking_1 = new JLabel(new ImageIcon(imgP));
		iParking_1.setHorizontalAlignment(SwingConstants.CENTER);
		iParking_1.setBounds(57, 23, 41, 31);
		containerInformations.add(iParking_1);
		
		//------------- IPiscine
        ImageIcon imgPisc = new ImageIcon(this.getClass().getResource("/ipiscine.png"));
        imgP = imgPisc.getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT);
		iParking_2 = new JLabel(new ImageIcon(imgP));
		iParking_2.setHorizontalAlignment(SwingConstants.CENTER);
		iParking_2.setBounds(126, 27, 33, 31);
		containerInformations.add(iParking_2);
		
		//---------------- INavette
        ImageIcon imgNav= new ImageIcon(this.getClass().getResource("/iBus.png"));
        imgP = imgNav.getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT);
		iParking_3 = new JLabel(new ImageIcon(imgP));
		iParking_3.setHorizontalAlignment(SwingConstants.CENTER);
		iParking_3.setBounds(191, 27, 33, 31);
		containerInformations.add(iParking_3);
		
		//-------------------- Icon Animal
        ImageIcon imgAn= new ImageIcon(this.getClass().getResource("/iAnimal.png"));
        imgP = imgAn.getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT);
		iParking_4 = new JLabel(new ImageIcon(imgP));
		iParking_4.setHorizontalAlignment(SwingConstants.CENTER);
		iParking_4.setBounds(255, 27, 33, 31);
		containerInformations.add(iParking_4);
		
		//---------------------- IEtoile
        ImageIcon imgEt= new ImageIcon(this.getClass().getResource("/iEtoile.png"));
        imgP = imgEt.getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT);
		iParking_5 = new JLabel(new ImageIcon(imgP));
		iParking_5.setHorizontalAlignment(SwingConstants.CENTER);
		iParking_5.setBounds(315, 27, 33, 31);
		containerInformations.add(iParking_5);
		
		valParking = new JLabel();
		valParking.setBounds(10, 57, 25, 21);
		containerInformations.add(valParking);
		
		valWifi = new JLabel();
		valWifi.setBounds(65, 57, 25, 21);
		containerInformations.add(valWifi);
		
		valPiscine = new JLabel();
		valPiscine.setBounds(136, 60, 25, 21);
		containerInformations.add(valPiscine);
		
		valNav = new JLabel();
		valNav.setBounds(201, 60, 25, 21);
		containerInformations.add(valNav);
		
		valAnim = new JLabel();
		valAnim.setBounds(265, 60, 25, 21);
		containerInformations.add(valAnim);
		
		valCat = new JLabel();
		valCat.setHorizontalAlignment(SwingConstants.CENTER);
		valCat.setBounds(315, 57, 25, 21);
		containerInformations.add(valCat);
		
		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(211, 117, 2, 74);
		containerInformations.add(panel);
		
		lblServicesDeLa = new JLabel("Services de la Chambre");
		lblServicesDeLa.setForeground(new Color(64, 0, 64));
		lblServicesDeLa.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblServicesDeLa.setBounds(65, 91, 178, 14);
		containerInformations.add(lblServicesDeLa);
		
		//------------icon Chambre SDB
        ImageIcon imgSdb= new ImageIcon(this.getClass().getResource("/iBainPriv.png"));
        imgP = imgSdb.getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT);
		iParking_6 = new JLabel(new ImageIcon(imgP));
		iParking_6.setHorizontalAlignment(SwingConstants.CENTER);
		iParking_6.setBounds(10, 117, 41, 31);
		containerInformations.add(iParking_6);
		
		//----------------icon Balcon
        ImageIcon imgBal= new ImageIcon(this.getClass().getResource("/iBalc.png"));
        imgP = imgBal.getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT);
		iParking_7 = new JLabel(new ImageIcon(imgP));
		iParking_7.setHorizontalAlignment(SwingConstants.CENTER);
		iParking_7.setBounds(75, 117, 41, 31);
		containerInformations.add(iParking_7);
		
		//----------------iTV
        ImageIcon imgTv= new ImageIcon(this.getClass().getResource("/iEcran.png"));
        imgP = imgTv.getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT);
		iParking_8 = new JLabel(new ImageIcon(imgP));
		iParking_8.setHorizontalAlignment(SwingConstants.CENTER);
		iParking_8.setBounds(141, 117, 41, 31);
		containerInformations.add(iParking_8);
		
		//-----------------iRefrigerateur
        ImageIcon imgRef= new ImageIcon(this.getClass().getResource("/iRef.png"));
        imgP = imgRef.getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT);
		iParking_9 = new JLabel(new ImageIcon(imgP));
		iParking_9.setHorizontalAlignment(SwingConstants.CENTER);
		iParking_9.setBounds(236, 117, 41, 31);
		containerInformations.add(iParking_9);
		
		
		//------------------- iBaignoire
        ImageIcon imgBai= new ImageIcon(this.getClass().getResource("/IBai.png"));
        imgP = imgBai.getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT);
		iParking_10 = new JLabel(new ImageIcon(imgP));
		iParking_10.setHorizontalAlignment(SwingConstants.CENTER);
		iParking_10.setBounds(287, 117, 41, 31);
		containerInformations.add(iParking_10);
		
		//-------------ISono
        ImageIcon imgSono= new ImageIcon(this.getClass().getResource("/ISono.png"));
        imgP = imgSono.getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT);
		iParking_11 = new JLabel(new ImageIcon(imgP));
		iParking_11.setHorizontalAlignment(SwingConstants.CENTER);
		iParking_11.setBounds(350, 117, 41, 31);
		containerInformations.add(iParking_11);
		
		//----------------Iprix
        ImageIcon imgPrix= new ImageIcon(this.getClass().getResource("/Iprix.png"));
        imgP = imgPrix.getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT);
		iParking_12 = new JLabel(new ImageIcon(imgP));
		iParking_12.setHorizontalAlignment(SwingConstants.CENTER);
		iParking_12.setBounds(413, 117, 41, 31);
		containerInformations.add(iParking_12);
		
		valsdp = new JLabel();
		valsdp.setBounds(20, 148, 25, 21);
		containerInformations.add(valsdp);
		
		valBalcon = new JLabel();
		valBalcon.setBounds(85, 151, 25, 21);
		containerInformations.add(valBalcon);
		
		valEcran = new JLabel();
		valEcran.setBounds(151, 151, 25, 21);
		containerInformations.add(valEcran);
		
		valRef = new JLabel();
		valRef.setBounds(246, 151, 25, 21);
		containerInformations.add(valRef);
		
		valBai = new JLabel();
		valBai.setBounds(297, 151, 25, 21);
		containerInformations.add(valBai);
		
		valSono = new JLabel();
		valSono.setBounds(360, 151, 25, 21);
		containerInformations.add(valSono);
		
		valPrix = new JLabel();
		valPrix.setBounds(423, 151, 25, 21);
		containerInformations.add(valPrix);
		

		
		idHotel = new TextField();
		idHotel.setBounds(234, 81, 111, 33);
		contentPane.add(idHotel);
		idHotel.setHorizontalAlignment(SwingConstants.CENTER);
		idHotel.setForeground(SystemColor.textHighlightText);
		idHotel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		
		//---------------------
		
		lblAdresse = new JLabel("Recherche");
		lblAdresse.setForeground(Color.WHITE);
		lblAdresse.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAdresse.setBounds(507, 14, 86, 14);
		contentPane.add(lblAdresse);
		idHotel.setVisible(false); 
		

		
		//========================= EVENTS BTN ===========================
		btnAjouter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (validateBai() && validateBalcon() && validateLitDouble() && validateLitSimple() && validateSup() && validateIdH()  && validateNumCh() && validateSdb() && validateRef() && validatePrix()) {
					Chambre c = new Chambre();
					c.setId_hotel(Integer.parseInt (idHotelClefEtr.getText()));
					c.setNumCh(Integer.parseInt(numChambre.getText()));
					c.setNbLitChSimp(Integer.parseInt(nbrLitSimp.getText()));
					c.setNbLitChDoub(Integer.parseInt(nbrLitDou.getText()));
					c.setSupCh(Integer.parseInt(superficie.getText()));
					c.setSalleBainP(sbdPrvative.getText());
					c.setTvCh(tv.getText());
					c.setBalconCh(balcon.getText());
					c.setBaignCh(baignoire.getText());
					c.setInsonoCh(insono.getText());
					c.setRefrigerCh(refrigerateur.getText());
					c.setPrixNtCh(Float.parseFloat(prixNuit.getText()));
					
					new ChambreDao().save(c);
					
			        ArrayList<Chambre> chambres = new ChambreDao().getAll();
			        String columns[] = { "ID", "NumCh", "NbLitChSimp", "NbLitChDoub", "SupCh", "SalleBainP", "TvCh", "BalconCh","RefrigerCh", "BaignCh", "InsonoCh", "PrixNtCh", "id_Hotel" };
			        String[][] data = new String[chambres.size()][columns.length];

			        int i = 0;
			        for (Chambre ch : chambres) {
			            data[i][0] = ch.getId_ch()+"";
			            data[i][1] = new HotelDao().getById(ch.getId_hotel()).getNomH(); 
			            data[i][2] = ch.getNumCh()+"";
			            data[i][3] = ch.getNbLitChSimp()+"";
			            data[i][4] = ch.getNbLitChDoub()+"";
			            data[i][5] = ch.getSupCh()+"";
			            data[i][6] = ch.getSalleBainP();
			            data[i][7] = ch.getTvCh();
			            data[i][8] = ch.getBalconCh();
			            data[i][9] = ch.getRefrigerCh();
			            data[i][10] =  ch.getBaignCh();
			            data[i][11] =  ch.getInsonoCh();
			            data[i][12] = ch.getPrixNtCh()+"";
			            i++;
			        }

			        model = new DefaultTableModel(data, columns);
					table.setModel(model);
					
			}
		}

		});

		btnModifier.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (validateBai() && validateBalcon() && validateLitDouble() && validateLitSimple() && validateSup() && validateIdH()  && validateNumCh() && validateSdb() && validateRef() && validatePrix()) {
					int id = Integer.parseInt (idChambre.getText());
					Chambre c= new ChambreDao().getById(id);
					c.setId_hotel(Integer.parseInt (idHotelClefEtr.getText()));
					c.setNumCh(Integer.parseInt(numChambre.getText()));
					c.setNbLitChSimp(Integer.parseInt(nbrLitSimp.getText()));
					c.setNbLitChDoub(Integer.parseInt(nbrLitDou.getText()));
					c.setSupCh(Integer.parseInt(superficie.getText()));
					c.setSalleBainP(sbdPrvative.getText());
					c.setTvCh(tv.getText());
					c.setBalconCh(balcon.getText());
					c.setBaignCh(baignoire.getText());
					c.setInsonoCh(insono.getText());
					c.setRefrigerCh(refrigerateur.getText());
					c.setPrixNtCh(Float.parseFloat(prixNuit.getText()));
					
					new ChambreDao().save(c);
					
					ArrayList<Chambre> chambres = new ChambreDao().getAll();
			        String columns[] = { "ID", "NumCh", "NbLitChSimp", "NbLitChDoub", "SupCh", "SalleBainP", "TvCh", "BalconCh","RefrigerCh", "BaignCh", "InsonoCh", "PrixNtCh", "id_Hotel" };
			        String[][] data = new String[chambres.size()][columns.length];

			        int i = 0;
			        for (Chambre ch : chambres) {
			            data[i][0] = ch.getId_ch()+"";
			            data[i][1] = new HotelDao().getById(ch.getId_hotel()).getNomH(); 
			            data[i][2] = ch.getNumCh()+"";
			            data[i][3] = ch.getNbLitChSimp()+"";
			            data[i][4] = ch.getNbLitChDoub()+"";
			            data[i][5] = ch.getSupCh()+"";
			            data[i][6] = ch.getSalleBainP();
			            data[i][7] = ch.getTvCh();
			            data[i][8] = ch.getBalconCh();
			            data[i][9] = ch.getRefrigerCh();
			            data[i][10] =  ch.getBaignCh();
			            data[i][11] =  ch.getInsonoCh();
			            data[i][12] = ch.getPrixNtCh()+"";
			            i++;
			        }

			        model = new DefaultTableModel(data, columns);
					table.setModel(model);
					
		        }
			}
			});

		btnSuppriemr.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (validateBai() && validateBalcon() && validateLitDouble() && validateLitSimple() && validateSup() && validateIdH()  && validateNumCh() && validateSdb() && validateRef() && validatePrix()) {
					int id = Integer.parseInt (idChambre.getText());
					Chambre c = new ChambreDao().getById(id);
					
					//Message de confirmation
					int input = JOptionPane.showConfirmDialog(null, "êtes vous sûr ? Vous ne verrez plus cette ligne");
					if (input == 0) {
						//suppression de l'objet avec son id
						new ChambreDao().delete(c.getId_ch());
					}
					
					ArrayList<Chambre> chambres = new ChambreDao().getAll();
			        String columns[] = { "ID", "NumCh", "NbLitChSimp", "NbLitChDoub", "SupCh", "SalleBainP", "TvCh", "BalconCh","RefrigerCh", "BaignCh", "InsonoCh", "PrixNtCh", "id_Hotel" };
			        String[][] data = new String[chambres.size()][columns.length];

			        int i = 0;
			        for (Chambre ch : chambres) {
			            data[i][0] = ch.getId_ch()+"";
			            data[i][1] = new HotelDao().getById(ch.getId_hotel()).getNomH(); 
			            data[i][2] = ch.getNumCh()+"";
			            data[i][3] = ch.getNbLitChSimp()+"";
			            data[i][4] = ch.getNbLitChDoub()+"";
			            data[i][5] = ch.getSupCh()+"";
			            data[i][6] = ch.getSalleBainP();
			            data[i][7] = ch.getTvCh();
			            data[i][8] = ch.getBalconCh();
			            data[i][9] = ch.getRefrigerCh();
			            data[i][10] =  ch.getBaignCh();
			            data[i][11] =  ch.getInsonoCh();
			            data[i][12] = ch.getPrixNtCh()+"";
			            i++;
			        }

			        model = new DefaultTableModel(data, columns);
					table.setModel(model);
					
		        }
			
			}
		});
		
				
	
			
			
			
			
			
			
			
	}
	
	
	//======================================== UTILS ==============================
    private void updateFields() {
        wordCherched = champRech.getText();
        

        if (!wordCherched.isEmpty()) {
            ArrayList<Chambre> chambres = new ChambreDao().recherche(wordCherched);
            String columns[] = { "ID", "NumCh", "NbLitChSimp", "NbLitChDoub", "SupCh", "SalleBainP", "TvCh", "BalconCh","RefrigerCh", "BaignCh", "InsonoCh", "PrixNtCh", "id_Hotel" };
            String[][] data = new String[chambres.size()][columns.length];
    		
            int i = 0;
            for (Chambre c : chambres) {
                data[i][0] = c.getId_ch()+"";
                data[i][1] = c.getNumCh()+"";
                data[i][2] = c.getNbLitChSimp()+"";
                data[i][3] = c.getNbLitChDoub()+"";
                data[i][4] = c.getSupCh()+"";
                data[i][5] = c.getSalleBainP();
                data[i][6] = c.getTvCh();
                data[i][7] = c.getBalconCh();
                data[i][8] = c.getRefrigerCh();
                data[i][9] = c.getBaignCh();
                data[i][10] = c.getInsonoCh();
                data[i][11] = c.getPrixNtCh()+"";
                data[i][12] = c.getId_hotel()+"";
                i++;
            }

    		model = new DefaultTableModel(data,columns);
			table.setModel(model);
        } 
        else {
            ArrayList<Chambre> chambres = new ChambreDao().getAll();
            String columns[] = { "ID", "NumCh", "NbLitChSimp", "NbLitChDoub", "SupCh", "SalleBainP", "TvCh", "BalconCh","RefrigerCh", "BaignCh", "InsonoCh", "PrixNtCh", "id_Hotel" };
            String[][] data = new String[chambres.size()][columns.length];
    		
            int i = 0;
            for (Chambre c : chambres) {
                data[i][0] = c.getId_ch()+"";
                data[i][1] = c.getNumCh()+"";
                data[i][2] = c.getNbLitChSimp()+"";
                data[i][3] = c.getNbLitChDoub()+"";
                data[i][4] = c.getSupCh()+"";
                data[i][5] = c.getSalleBainP();
                data[i][6] = c.getTvCh();
                data[i][7] = c.getBalconCh();
                data[i][8] = c.getRefrigerCh();
                data[i][9] = c.getBaignCh();
                data[i][10] = c.getInsonoCh();
                data[i][11] = c.getPrixNtCh()+"";
                data[i][12] = c.getId_hotel()+"";
                i++;
            }
    		model = new DefaultTableModel(data,columns);
			table.setModel(model);
			
        		
        }
    }

    // Méthode de validation pour le champ ID Hotel
    private boolean validateIdH() {
    	int lenghttabSociete = new HotelDao().getAll().size();
        try {
            int valId_soc = Integer.parseInt(idHotelClefEtr.getText());
            if (valId_soc < 1 || valId_soc > lenghttabSociete) {
                JOptionPane.showMessageDialog(null, "Veuillez saisir un ID de Hotel valide: entre 1 et " + lenghttabSociete);
                return false;
            }
            return true;
        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(null, "Veuillez saisir un nombre valide pour l'ID de Hotel");
            idHotel.requestFocus();
            return false;
        }
    }
    
    // Méthode de validation pour le champ N°Chambre
    private boolean validateNumCh() {
        try {
            int valNumCh = Integer.parseInt(numChambre.getText());
            if (valNumCh <=0) {
                JOptionPane.showMessageDialog(null, "le numéro de la chambre doit etre un entier Positif");
                numChambre.requestFocus();
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Veuillez saisir un nombre valide ");
            numChambre.requestFocus();
            return false;
        }
        
    }
        
        // Méthode de validation pour le champ Nbr LitS
        private boolean validateLitSimple() {
            try {
                int valNumCh = Integer.parseInt(nbrLitSimp.getText());
                if (valNumCh <=0) {
                    JOptionPane.showMessageDialog(null, "le numéro de lit Simpleetre un entier Positif");
                    numChambre.requestFocus();
                    return false;
                }
                return true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Veuillez saisir un nombre valide ");
                numChambre.requestFocus();
                return false;
            }
    }
        
        
        private boolean validateLitDouble() {
            try {
                int valNumCh = Integer.parseInt(nbrLitDou.getText());
                if (valNumCh <=0) {
                    JOptionPane.showMessageDialog(null, "le numéro de lit Double etre un entier Positif");
                    numChambre.requestFocus();
                    return false;
                }
                return true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Veuillez saisir un nombre valide ");
                numChambre.requestFocus();
                return false;
            }
    }
        
        private boolean validateSup() {
            try {
                int valNumCh = Integer.parseInt(superficie.getText());
                if (valNumCh <=0) {
                    JOptionPane.showMessageDialog(null, "la superficie doit etre un entier Positif");
                    numChambre.requestFocus();
                    return false;
                }
                return true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Veuillez saisir un nombre valide");
                numChambre.requestFocus();
                return false;
            }
    }
        
        private boolean validateSdb() {
            String valParking = sbdPrvative.getText();
            if (!valParking.equalsIgnoreCase("non") && !valParking.equalsIgnoreCase("oui")) {
                JOptionPane.showMessageDialog(null, "Veuillez saisir 'oui' ou 'non' ");
                return false;
            }
            return true;
        }
        
        private boolean validateBalcon() {
            String valParking = balcon.getText();
            if (!valParking.equalsIgnoreCase("non") && !valParking.equalsIgnoreCase("oui")) {
                JOptionPane.showMessageDialog(null, "Veuillez saisir 'oui' ou 'non' ");
                return false;
            }
            return true;
        }
        
        private boolean validateTv() {
            String valParking = tv.getText();
            if (!valParking.equalsIgnoreCase("non") && !valParking.equalsIgnoreCase("oui")) {
                JOptionPane.showMessageDialog(null, "Veuillez saisir 'oui' ou 'non' ");
                return false;
            }
            return true;
        }
    
        private boolean validateRef() {
            String valParking = refrigerateur.getText();
            if (!valParking.equalsIgnoreCase("non") && !valParking.equalsIgnoreCase("oui")) {
                JOptionPane.showMessageDialog(null, "Veuillez saisir 'oui' ou 'non' ");
                return false;
            }
            return true;
        }
        
        private boolean validateBai() {
            String valParking = baignoire.getText();
            if (!valParking.equalsIgnoreCase("non") && !valParking.equalsIgnoreCase("oui")) {
                JOptionPane.showMessageDialog(null, "Veuillez saisir 'oui' ou 'non' ");
                return false;
            }
            return true;
        }
        
        private boolean validateInsono() {
            String valParking = insono.getText();
            if (!valParking.equalsIgnoreCase("non") && !valParking.equalsIgnoreCase("oui")) {
                JOptionPane.showMessageDialog(null, "Veuillez saisir 'oui' ou 'non' ");
                return false;
            }
            return true;
        }
        
        private boolean validatePrix() {
            try {
                float valNumCh = Float.parseFloat(prixNuit.getText());
                if (valNumCh <=0) {
                    JOptionPane.showMessageDialog(null, "la superficie doit etre un entier Positif");
                    numChambre.requestFocus();
                    return false;
                }
                return true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Veuillez saisir un nombre valide");
                numChambre.requestFocus();
                return false;
            }
    }
}
