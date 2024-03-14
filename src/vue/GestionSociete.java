package vue;

import java.awt.Color;
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
import javax.swing.table.DefaultTableModel;

import Components.Button;
import Components.Password;
import Components.TextField;
import dao.SocieteDao;
import entites.Db;
import entites.Societe;

public class GestionSociete extends JInternalFrame {

	//============================== VARIABLES =============
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Image logo;
	private Button btnAjouter;
	private Button btnModifier;
	private Button btnSuppriemr;
	private String wordCherched;
	private JLabel container;
	
	Image img;
	TextField champRech;
	TextField idChampSociete;
	TextField numSir;
	TextField nomSoc;
	TextField adSoc;
	DefaultTableModel model;
	JTable table;
	//Button btnAdmnm;
	//Button btnRecep;
	TextField champText;
	Password champPwd;
	private JLabel lblNewLabel;
	private JLabel lblAdresse;
	private JLabel lblNewLabel_1;
	private JLabel lblAdresse_1;
	private JLabel lblNom;
	private JLabel lblNewLabel_2;
	private JLabel lblAdresse_2;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionSociete frame = new GestionSociete();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public GestionSociete() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 762, 464);
		//Enlever la décoration de la fenetre JinternalFrame
		putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		//===================  LOGO  =====================================
		//Chargement Logo et configuration Style
        ImageIcon iconlogo = new ImageIcon(this.getClass().getResource("/logo.png"));
        logo = iconlogo.getImage().getScaledInstance(55,70, Image.SCALE_DEFAULT);

		JLabel contLogo = new JLabel(new ImageIcon(logo));
		contLogo.setHorizontalAlignment(SwingConstants.CENTER);
		contLogo.setBounds(10, 0, 55,70);
		//ajout logo
		//contentPane.add(contLogo);
		
	    lblNewLabel = new JLabel("Numéro Siret");
	    lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
	    contentPane.add(lblNewLabel);
	    lblNewLabel.setForeground(new Color(255, 255, 255));
	    lblNewLabel.setBounds(75, 92, 101, 14);
	    contentPane.add(lblNewLabel);
	    
	    lblAdresse_2 = new JLabel("Recherche");
	    lblAdresse_2.setForeground(Color.WHITE);
	    lblAdresse_2.setFont(new Font("Tahoma", Font.BOLD, 14));
	    lblAdresse_2.setBounds(499, 66, 86, 14);
	    contentPane.add(lblAdresse_2);
	    
	    lblAdresse_1 = new JLabel("Adresse ");
	    lblAdresse_1.setForeground(Color.WHITE);
	    lblAdresse_1.setFont(new Font("Tahoma", Font.BOLD, 14));
	    lblAdresse_1.setBounds(75, 164, 101, 14);
	    contentPane.add(lblAdresse_1);
	    
	    lblNom = new JLabel("Nom");
	    lblNom.setForeground(Color.WHITE);
	    lblNom.setFont(new Font("Tahoma", Font.BOLD, 14));
	    lblNom.setBounds(75, 243, 101, 14);
	    contentPane.add(lblNom);
		//=======Titre =========================
		JLabel titre = new JLabel("GESTION DES SOCIETES");
		titre.setForeground(SystemColor.textHighlightText);
		titre.setFont(new Font("Tahoma", Font.BOLD, 16));
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setBounds(231, 22, 256, 28);
		contentPane.add(titre);
		
		
		//Champ Recherche
		champRech = new TextField();
		champRech.setForeground(SystemColor.textHighlightText);
		champRech.setFont(new Font("Tahoma", Font.BOLD, 16));
		champRech.setHorizontalAlignment(SwingConstants.CENTER);
		champRech.setBounds(587, 53, 149, 40);
		contentPane.add(champRech);
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
				
		//============================= Ajout tableau
		Db.connect();
		ArrayList<Societe> societe = new SocieteDao().getAll();
		String columns[] = { "ID", "NumSir", "Nom","AdSoc"};
		String data[][] = new String[societe.size()][columns.length];
		
		int i = 0;
		for (Societe s : societe) {
			data[i][0] = s.getId_soc() + "";
			data[i][1] = s.getNumSiret();
			data[i][2] = s.getNom();
			data[i][3] = s.getAdresseS();
			i++;
		}

		model = new DefaultTableModel(data,columns);
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(342, 107, 394, 111);
		getContentPane().add(scrollPane);
		
		
		//========================== AFFICHAGE SELECTION ==========
		
		//-----numSiret
		numSir = new TextField();
		numSir.setForeground(SystemColor.textHighlightText);
		numSir.setFont(new Font("Tahoma", Font.BOLD, 16));
		numSir.setHorizontalAlignment(SwingConstants.CENTER);
		numSir.setBounds(10, 111, 241, 34);
		contentPane.add(numSir);
		//-----nomSoc
		nomSoc = new TextField();
		nomSoc.setForeground(SystemColor.textHighlightText);
		nomSoc.setFont(new Font("Tahoma", Font.BOLD, 16));
		nomSoc.setHorizontalAlignment(SwingConstants.CENTER);
		nomSoc.setBounds(10, 262, 241, 34);
		contentPane.add(nomSoc);
		
		//-----adresseSoc
		adSoc = new TextField();
		adSoc.setForeground(SystemColor.textHighlightText);
		adSoc.setFont(new Font("Tahoma", Font.BOLD, 16));
		adSoc.setHorizontalAlignment(SwingConstants.CENTER);
		adSoc.setBounds(10, 187, 276, 34);
		contentPane.add(adSoc);
		
		idChampSociete = new TextField();
		idChampSociete.setForeground(SystemColor.textHighlightText);
		idChampSociete.setFont(new Font("Tahoma", Font.BOLD, 16));
		idChampSociete.setHorizontalAlignment(SwingConstants.CENTER);
		idChampSociete.setBounds(10, 342, 241, 34);
		contentPane.add(idChampSociete);
		idChampSociete.setVisible(false);
		
		
		//=========================  BTNS =========================
		//------ Ajouter
		btnAjouter = new Button();
		btnAjouter.setText("Ajouter");
		btnAjouter.setForeground(SystemColor.textHighlightText);
		btnAjouter.setBackground(Color.BLACK);
		btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAjouter.setHorizontalAlignment(SwingConstants.CENTER);
		btnAjouter.setBounds(356, 229, 86, 28);
		contentPane.add(btnAjouter);
		//------ Modifier
		btnModifier = new Button();
		btnModifier.setText("Modifier");
		btnModifier.setForeground(SystemColor.textHighlightText);
		btnModifier.setBackground(Color.BLACK);
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnModifier.setHorizontalAlignment(SwingConstants.CENTER);
		btnModifier.setBounds(464, 229, 95, 28);
		contentPane.add(btnModifier);
		//Supp
		btnSuppriemr = new Button();
		btnSuppriemr.setText("Supprimer");
		btnSuppriemr.setForeground(SystemColor.textHighlightText);
		btnSuppriemr.setBackground(Color.BLACK);
		btnSuppriemr.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSuppriemr.setHorizontalAlignment(SwingConstants.CENTER);
		btnSuppriemr.setBounds(569, 229, 113, 28);
		contentPane.add(btnSuppriemr);
		
		//-------------- Tout mes bouton a disabled
		
		btnModifier.setEnabled(false);
		btnSuppriemr.setEnabled(false);

		//======================== EVENT SELECTION TABLE ==============
		table.getSelectionModel().addListSelectionListener(e -> {
			int rowIndex = table.getSelectedRow();
			if (rowIndex != -1) {
				String idTableSelected = (String) table.getValueAt(rowIndex, 0);
				String numeroSiret = (String) table.getValueAt(rowIndex, 1);
				String nomSociete = (String) table.getValueAt(rowIndex, 2);
				String adresseSociete = (String) table.getValueAt(rowIndex, 3);
				idChampSociete.setText(idTableSelected);
				numSir.setText(numeroSiret);
				nomSoc.setText(nomSociete);
				adSoc.setText(adresseSociete);

				btnModifier.setEnabled(true);
				btnSuppriemr.setEnabled(true);
			} 
			else {
				btnModifier.setEnabled(false);
				btnSuppriemr.setEnabled(false);
			}
		});
		
		//========================= EVENTS BTN ===========================
		btnAjouter.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				boolean bnumS = numSir.getText().isEmpty();
				boolean bnomSocte = nomSoc.getText().isEmpty();
				boolean badrsScte = adSoc.getText().isEmpty();
				if(bnumS || bnomSocte || badrsScte) {
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
				}
				else {
					Societe s = new Societe();
					s.setNumSiret(numSir.getText());
					s.setNom(nomSoc.getText());
					s.setAdresseS(adSoc.getText());
					new SocieteDao().save(s);
					ArrayList<Societe> societe = new SocieteDao().getAll();
					String data[][] = new String[societe.size()][columns.length];
					
					int i = 0;
					for (Societe t : societe) {
						data[i][0] = t.getId_soc() + "";
						data[i][1] = t.getNumSiret();
						data[i][2] = t.getNom();
						data[i][3] = t.getAdresseS();
						i++;
					}

					model = new DefaultTableModel(data,columns);
					table.setModel(model);
					numSir.setText("");
					nomSoc.setText("");
					adSoc.setText("");
				}
				
			}
		});

		
		
	btnModifier.addActionListener(new ActionListener() {
		
		
		public void actionPerformed(ActionEvent e) {

				int id = Integer.parseInt(idChampSociete.getText());
				Societe s = new SocieteDao().getById(id);
				s.setNumSiret(numSir.getText());
				s.setNom(nomSoc.getText());
				s.setAdresseS(adSoc.getText());
				new SocieteDao().save(s);
				
				
				ArrayList<Societe> societe = new SocieteDao().getAll();
				String data[][] = new String[societe.size()][columns.length];
				
				int i = 0;
				for (Societe t : societe) {
					data[i][0] = t.getId_soc() + "";
					data[i][1] = t.getNumSiret();
					data[i][2] = t.getNom();
					data[i][3] = t.getAdresseS();
					i++;
				}

				model = new DefaultTableModel(data,columns);
				table.setModel(model);
				numSir.setText("");
				nomSoc.setText("");
				adSoc.setText("");
				btnModifier.setEnabled(false);
				btnSuppriemr.setEnabled(false);
			}
			
		
	});
	
	btnSuppriemr.addActionListener(new ActionListener() {
		
		
		public void actionPerformed(ActionEvent e) {
				//récupération de l'Id de l'objet selectionné
			
				int id = Integer.parseInt(idChampSociete.getText());
				
				//objet Selectionné
				Societe s = new SocieteDao().getById(id);
				
				//Message de confirmation
				int input = JOptionPane.showConfirmDialog(null, "êtes vous sûr ?");
				if (input == 0) {
					//suppression de l'objet avec son id
					new SocieteDao().delete(s.getId_soc());
				}
				
				//mettre à jour le tableau
				ArrayList<Societe> societe = new SocieteDao().getAll();
				String data[][] = new String[societe.size()][columns.length];
				
				int i = 0;
				for (Societe t : societe) {
					data[i][0] = t.getId_soc() + "";
					data[i][1] = t.getNumSiret();
					data[i][2] = t.getNom();
					data[i][3] = t.getAdresseS();
					i++;
				}

				model = new DefaultTableModel(data,columns);
				table.setModel(model);
				numSir.setText("");
				nomSoc.setText("");
				adSoc.setText("");
				btnModifier.setEnabled(false);
				btnSuppriemr.setEnabled(false);
				JOptionPane.showMessageDialog(null, "vous ne reverez jamais cette ligne");
			}	
		
	});
	
	//=====================================================Mettre un background
	//-----------------------Chargement image
    ImageIcon iconbg = new ImageIcon(this.getClass().getResource("/societe.jpg"));
    img = iconbg.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
    
    //762, 464
    //ajout image en bacgrd
    container = new JLabel(new ImageIcon(img));
    container.setBounds(0, 0, getWidth(), getHeight());
    container.add(contLogo);
    
    

    //ajout du containe dans le Jpanel
    contentPane.add(container);
    
    lblAdresse = new JLabel("Adresse");
    lblAdresse.setForeground(Color.WHITE);
    lblAdresse.setFont(new Font("Tahoma", Font.BOLD, 14));
    lblAdresse.setBounds(75, 162, 101, 14);
    contentPane.add(lblAdresse);
    
    lblNewLabel_1 = new JLabel("Numéro Siret");
    lblNewLabel_1.setForeground(Color.WHITE);
    lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
    lblNewLabel_1.setBounds(75, 164, 101, 14);
    contentPane.add(lblNewLabel_1);
    

}
	
	

	//========================== UTILS ================================
    private void updateFields() {
        wordCherched = champRech.getText();
        

        if (!wordCherched.isEmpty()) {
       
			ArrayList<Societe> societe = new SocieteDao().recherche(wordCherched);
			String columns[] = { "ID", "NumSir", "Nom","AdSoc"};
			String data[][] = new String[societe.size()][columns.length];
			int i = 0;
			for (Societe s : societe) {
				data[i][0] = s.getId_soc() + "";
				data[i][1] = s.getNumSiret();
				data[i][2] = s.getNom();
				data[i][3] = s.getAdresseS();
				i++;
			}
			model = new DefaultTableModel(data, columns);
			table.setModel(model);
        } 
        else {
			ArrayList<Societe> societe = new SocieteDao().getAll();
			String columns[] = { "ID", "NumSir", "Nom","AdSoc"};
			String data[][] = new String[societe.size()][columns.length];
			
			
			int i = 0;
			for (Societe s : societe) {
				data[i][0] = s.getId_soc() + "";
				data[i][1] = s.getNumSiret();
				data[i][2] = s.getNom();
				data[i][3] = s.getAdresseS();
				i++;
			}
			model = new DefaultTableModel(data, columns);
			table.setModel(model);
			
        		
        }
    }
}
