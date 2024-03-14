package vue;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import javax.swing.table.DefaultTableModel;

import Components.Button;
import Components.TextField;
import dao.ChambreDao;
import dao.ClientDao;
import dao.HotelDao;
import dao.SocieteDao;
import entites.Db;
import entites.Hotel;
import entites.Chambre;
import entites.Client;

public class GestionClient extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
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
	
	private String wordCherched;
	private Button btnAjouter;
	private Button btnModifier;
	private Button btnSuppriemr;
	
	TextField champRech;
	DefaultTableModel model;
	JTable table;
	private JLabel lblAdresse;
	private JLabel lblNewLabel;
	private JLabel lblPrenom;
	private JLabel lblAge;
	private JLabel lblAdresse_1;
	private JLabel lblEmail;
	private JLabel lblVille;
	private JLabel lblPays;
	private JLabel lblSexe;
	private JLabel lblTel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionClient frame = new GestionClient();
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
	public GestionClient() {
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
		JLabel titre = new JLabel("GESTION DES CLIENTS");
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
		champRech.setBounds(88, 77, 149, 40);
		
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
		table = new JTable(model);
		//table.setRowHeight(50);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 115, 726, 113);
		getContentPane().add(scrollPane);
		
		//======================================== Ajout des Champs Text ============
		//-----id_societe fremplace par le nom de la societé
		idClient = new TextField();
		idClient.setForeground(SystemColor.textHighlightText);
		idClient.setFont(new Font("Tahoma", Font.BOLD, 16));
		idClient.setHorizontalAlignment(SwingConstants.CENTER);
		idClient.setBounds(256, 370, 40, 34);
		contentPane.add(idClient);
		idClient.setVisible(false);
		
		//-----nomClient
		nomClient = new TextField();
		nomClient.setForeground(SystemColor.textHighlightText);
		nomClient.setFont(new Font("Tahoma", Font.BOLD, 16));
		nomClient.setHorizontalAlignment(SwingConstants.CENTER);
		nomClient.setBounds(10, 251, 138, 34);
		contentPane.add(nomClient);

		//-----prenomCl
		prenomCl = new TextField();
		prenomCl.setForeground(SystemColor.textHighlightText);
		prenomCl.setFont(new Font("Tahoma", Font.BOLD, 16));
		prenomCl.setHorizontalAlignment(SwingConstants.CENTER);
		prenomCl.setBounds(158, 251, 138, 34);
		contentPane.add(prenomCl);
		
		//adCl
		adCl = new TextField();
		adCl.setForeground(SystemColor.textHighlightText);
		adCl.setFont(new Font("Tahoma", Font.BOLD, 16));
		adCl.setHorizontalAlignment(SwingConstants.CENTER);
		adCl.setBounds(502, 251, 214, 40);
		contentPane.add(adCl);
	
		//ageCl
		ageCl =new TextField();
		ageCl.setForeground(SystemColor.textHighlightText);
		ageCl.setFont(new Font("Tahoma", Font.BOLD, 16));
		ageCl.setHorizontalAlignment(SwingConstants.CENTER);
		ageCl.setBounds(303, 251, 168, 34);
		contentPane.add(ageCl);
		
		//villeCl
		villeCl =new TextField();
		villeCl.setForeground(SystemColor.textHighlightText);
		villeCl.setFont(new Font("Tahoma", Font.BOLD, 16));
		villeCl.setHorizontalAlignment(SwingConstants.CENTER);
		villeCl.setBounds(152, 315, 144, 34);
		contentPane.add(villeCl);
		
		//emailCl
		emailCl =new TextField();
		emailCl.setForeground(SystemColor.textHighlightText);
		emailCl.setFont(new Font("Tahoma", Font.BOLD, 16));
		emailCl.setHorizontalAlignment(SwingConstants.CENTER);
		emailCl.setBounds(10, 315, 138, 34);
		contentPane.add(emailCl);
		
		//sexeCl
		sexeCl =new TextField();
		sexeCl.setForeground(SystemColor.textHighlightText);
		sexeCl.setFont(new Font("Tahoma", Font.BOLD, 16));
		sexeCl.setHorizontalAlignment(SwingConstants.CENTER);
		sexeCl.setBounds(476, 315, 138, 34);
		contentPane.add(sexeCl);
		
		//TelClient
		telCl =new TextField();
		telCl.setForeground(SystemColor.textHighlightText);
		telCl.setFont(new Font("Tahoma", Font.BOLD, 16));
		telCl.setHorizontalAlignment(SwingConstants.CENTER);
		telCl.setBounds(10, 370, 214, 34);
		contentPane.add(telCl);
		
		//paysClient
		paysCl =new TextField();
		paysCl.setForeground(SystemColor.textHighlightText);
		paysCl.setFont(new Font("Tahoma", Font.BOLD, 16));
		paysCl.setHorizontalAlignment(SwingConstants.CENTER);
		paysCl.setBounds(306, 315, 138, 34);
		contentPane.add(paysCl);
		
	//ajout Des elements logo titre et chp rech
		contentPane.add(contLogo);
		contentPane.add(titre);
		contentPane.add(champRech);
		
		//======================== EVENT SELECTION TABLE ==============
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
				
				btnAjouter.setEnabled(true);
				btnModifier.setEnabled(true);
				btnSuppriemr.setEnabled(true);
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
		btnAjouter.setBounds(423, 83, 86, 28);
		contentPane.add(btnAjouter);
		//------ Modifier
		btnModifier = new Button();
		btnModifier.setText("Modifier");
		btnModifier.setForeground(SystemColor.textHighlightText);
		btnModifier.setBackground(Color.BLACK);
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnModifier.setHorizontalAlignment(SwingConstants.CENTER);
		btnModifier.setBounds(519, 83, 95, 28);
		contentPane.add(btnModifier);
		//Supp
		btnSuppriemr = new Button();
		btnSuppriemr.setText("Supprimer");
		btnSuppriemr.setForeground(SystemColor.textHighlightText);
		btnSuppriemr.setBackground(Color.BLACK);
		btnSuppriemr.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSuppriemr.setHorizontalAlignment(SwingConstants.CENTER);
		btnSuppriemr.setBounds(623, 83, 113, 28);
		contentPane.add(btnSuppriemr);
		
		//btnAjouter.setEnabled(false);
		btnModifier.setEnabled(false);
		btnSuppriemr.setEnabled(false);
		
		lblAdresse = new JLabel("Recherche");
		lblAdresse.setForeground(Color.WHITE);
		lblAdresse.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAdresse.setBounds(10, 90, 86, 14);
		contentPane.add(lblAdresse);
		
		lblNewLabel = new JLabel("Nom");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(64, 239, 46, 14);
		contentPane.add(lblNewLabel);
		
		lblPrenom = new JLabel("Prenom");
		lblPrenom.setForeground(Color.WHITE);
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrenom.setBounds(207, 239, 46, 14);
		contentPane.add(lblPrenom);
		
		lblAge = new JLabel("Age");
		lblAge.setForeground(Color.WHITE);
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAge.setBounds(360, 239, 46, 14);
		contentPane.add(lblAge);
		
		lblAdresse_1 = new JLabel("Adresse");
		lblAdresse_1.setForeground(Color.WHITE);
		lblAdresse_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAdresse_1.setBounds(583, 239, 46, 14);
		contentPane.add(lblAdresse_1);
		
		lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBounds(64, 304, 46, 14);
		contentPane.add(lblEmail);
		
		lblVille = new JLabel("Ville");
		lblVille.setForeground(Color.WHITE);
		lblVille.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVille.setBounds(207, 304, 46, 14);
		contentPane.add(lblVille);
		
		lblPays = new JLabel("Pays");
		lblPays.setForeground(Color.WHITE);
		lblPays.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPays.setBounds(360, 304, 46, 14);
		contentPane.add(lblPays);
		
		lblSexe = new JLabel("Sexe");
		lblSexe.setForeground(Color.WHITE);
		lblSexe.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSexe.setBounds(519, 302, 46, 14);
		contentPane.add(lblSexe);
		
		lblTel = new JLabel("Tel");
		lblTel.setForeground(Color.WHITE);
		lblTel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTel.setBounds(88, 359, 46, 14);
		contentPane.add(lblTel);
		
		//====================== EVENTS ================================
		btnAjouter.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				if(validateAgeCl() && isValidTel() && isValidMail() && validateSexe()) {
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
			        model = new DefaultTableModel(data, columns);
					table.setModel(model);
				}
		
			}
			
		});

	
	/////////////////////// MODIFIER
		btnModifier.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				if(validateAgeCl() && isValidTel() && isValidMail()) {
					int id = Integer.parseInt (idClient.getText());
					Client c = new ClientDao().getById(id);
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
			        model = new DefaultTableModel(data, columns);
					table.setModel(model);
				}
		
			}
			
		});
		
		/////////////////////// SUPPRIMER
		btnSuppriemr.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				if(validateAgeCl() && isValidTel() && isValidMail()) {
					int id = Integer.parseInt (idClient.getText());
					Client c = new ClientDao().getById(id);
					//Message de confirmation
					int input = JOptionPane.showConfirmDialog(null, "êtes vous sûr ? Vous ne verrez plus cette ligne");
					if (input == 0) {
						//suppression de l'objet avec son id
						new ClientDao().delete(c.getId_client());
					}
			
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
			        model = new DefaultTableModel(data, columns);
					table.setModel(model);
				}
		
			}
			
		});
	}

	


	//========================================= UTILS =================================
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
    
}


