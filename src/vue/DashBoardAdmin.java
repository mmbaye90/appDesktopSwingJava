package vue;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Components.Button;
import dao.ChambreDao;
import dao.ClientDao;
import dao.ReservDao;
import entites.Db;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class DashBoardAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Image logo;
	private Button gestionSociete;
	private Button gestionHotel;
	private Button gestionChambre;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashBoardAdmin frame = new DashBoardAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public DashBoardAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 893, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(0,101,252));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JDesktopPane desk = new JDesktopPane();
		desk.setBounds(115, 0, 762, 464);
		
		contentPane.add(desk);
		desk.setLayout(null);
		
		JPanel boxShadow = new JPanel();
		boxShadow.setBounds(157, 25, 9, 138);
		boxShadow.setBackground(new Color(147, 147, 255));
		desk.add(boxShadow);
		
		JPanel boxShadowH = new JPanel();
		boxShadowH.setBackground(new Color(147, 147, 255));
		boxShadowH.setBounds(10, 158, 156, 5);
		desk.add(boxShadowH);
		
		JPanel containerStat1 = new JPanel();
		containerStat1.setBounds(10, 25, 148, 131);
		//containerStat1.setBackground(new Color(0,101,252));
		desk.add(containerStat1);
		containerStat1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Indice de fréquentation");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 0, 148, 25);
		containerStat1.add(lblNewLabel_1);
		
		JLabel indiceFreq = new JLabel("0");
		indiceFreq.setFont(new Font("Tahoma", Font.BOLD, 16));
		indiceFreq.setHorizontalAlignment(SwingConstants.CENTER);
		indiceFreq.setBounds(27, 42, 95, 50);
		containerStat1.add(indiceFreq);
		
		JPanel containerStat2 = new JPanel();
		containerStat2.setBounds(233, 25, 148, 131);
		desk.add(containerStat2);
		containerStat2.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Taux d'occupation (TO)");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(0, 0, 148, 25);
		containerStat2.add(lblNewLabel_1_1);
		
		JLabel tauxOccup = new JLabel("0");
		tauxOccup.setHorizontalAlignment(SwingConstants.CENTER);
		tauxOccup.setFont(new Font("Tahoma", Font.BOLD, 16));
		tauxOccup.setBounds(28, 45, 95, 50);
		containerStat2.add(tauxOccup);
		
		JPanel boxShadow_1 = new JPanel();
		boxShadow_1.setBackground(new Color(147, 147, 255));
		boxShadow_1.setBounds(380, 25, 9, 138);
		desk.add(boxShadow_1);
		
		JPanel boxShadowH_1 = new JPanel();
		boxShadowH_1.setBackground(new Color(147, 147, 255));
		boxShadowH_1.setBounds(233, 158, 156, 5);
		desk.add(boxShadowH_1);
		
		JPanel containerStat3 = new JPanel();
		containerStat3.setBounds(449, 25, 148, 131);
		desk.add(containerStat3);
		containerStat3.setLayout(null);
		
		JLabel lblNewLabel_1_2 = new JLabel("Chiffre d'affaire");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setBounds(0, 0, 148, 25);
		containerStat3.add(lblNewLabel_1_2);
		
		JLabel chiffreA = new JLabel("0");
		chiffreA.setHorizontalAlignment(SwingConstants.CENTER);
		chiffreA.setFont(new Font("Tahoma", Font.BOLD, 16));
		chiffreA.setBounds(27, 42, 95, 50);
		containerStat3.add(chiffreA);
		
		JPanel boxShadow_2 = new JPanel();
		boxShadow_2.setBackground(new Color(147, 147, 255));
		boxShadow_2.setBounds(596, 25, 9, 138);
		desk.add(boxShadow_2);
		
		JPanel boxShadowH_2 = new JPanel();
		boxShadowH_2.setBackground(new Color(147, 147, 255));
		boxShadowH_2.setBounds(449, 158, 156, 5);
		desk.add(boxShadowH_2);
		
		JPanel separator = new JPanel();
		separator.setBounds(10, 172, 591, 5);
		desk.add(separator);
		
		JPanel infoBulle1 = new JPanel();
		infoBulle1.setBounds(49, 233, 519, 78);
		desk.add(infoBulle1);
		infoBulle1.setLayout(null);
		
		JButton btnClose1 = new JButton("X");

		btnClose1.setForeground(new Color(255, 0, 128));
		btnClose1.setHorizontalAlignment(SwingConstants.LEADING);
		btnClose1.setBounds(467, 0, 52, 23);
		infoBulle1.add(btnClose1);
		btnClose1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel msgInfobulle = new JLabel("ATTENTION A VOS CHIFFRES IL FAUDRAIT BOOSTER VOTRE GROUPE");
		msgInfobulle.setForeground(new Color(236, 0, 0));
		msgInfobulle.setHorizontalAlignment(SwingConstants.CENTER);
		msgInfobulle.setBounds(58, 22, 377, 45);
		infoBulle1.add(msgInfobulle);
		
		//Chargement Logo et configuration Style
        ImageIcon iconlogo = new ImageIcon(this.getClass().getResource("/logo.png"));
        logo = iconlogo.getImage().getScaledInstance(55,70, Image.SCALE_DEFAULT);

		JLabel contLogo = new JLabel(new ImageIcon(logo));
		contLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new Login().setVisible(true);
			}
		});
		contLogo.setHorizontalAlignment(SwingConstants.CENTER);
		contLogo.setBounds(26, 0, 55,70);
		contentPane.add(contLogo);
		
		gestionSociete = new Button();
		gestionSociete.setText("SOCIETES");
		gestionSociete.setForeground(SystemColor.textHighlightText);
		gestionSociete.setBackground(Color.BLACK);
		gestionSociete.setFont(new Font("Tahoma", Font.BOLD, 13));
		gestionSociete.setHorizontalAlignment(SwingConstants.CENTER);
		gestionSociete.setBounds(10, 152, 95, 32);
		contentPane.add(gestionSociete);
		
		
		
		
		JLabel lblNewLabel = new JLabel("Dashboard Adm");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(0, 69, 114, 32);
		contentPane.add(lblNewLabel);
		
		gestionHotel = new Button();
		gestionHotel.setText("HOTELS");
		gestionHotel.setHorizontalAlignment(SwingConstants.CENTER);
		gestionHotel.setForeground(SystemColor.textHighlightText);
		gestionHotel.setFont(new Font("Tahoma", Font.BOLD, 13));
		gestionHotel.setBackground(Color.BLACK);
		gestionHotel.setBounds(10, 220, 95, 32);
		contentPane.add(gestionHotel);
		
		gestionChambre = new Button();
		gestionChambre.setText("CHAMBRES");
		gestionChambre.setHorizontalAlignment(SwingConstants.CENTER);
		gestionChambre.setForeground(SystemColor.textHighlightText);
		gestionChambre.setFont(new Font("Tahoma", Font.BOLD, 13));
		gestionChambre.setBackground(Color.BLACK);
		gestionChambre.setBounds(10, 288, 95, 32);
		contentPane.add(gestionChambre);
		
		Button gestionClient = new Button();
		gestionClient.setText("CLIENTS");
		gestionClient.setHorizontalAlignment(SwingConstants.CENTER);
		gestionClient.setForeground(SystemColor.textHighlightText);
		gestionClient.setFont(new Font("Tahoma", Font.BOLD, 13));
		gestionClient.setBackground(Color.BLACK);
		gestionClient.setBounds(10, 347, 95, 32);
		contentPane.add(gestionClient);
		
		//=================== Calcul ==========================
		Db.connect();
		//---- Indice de Frequentation =>nbr Client/nbrChambreLoué
		int indiceFre = new ClientDao().getAll().size()/new ReservDao().getAll().size();		
		indiceFreq.setText(indiceFre+"");

		//---- Taux d'occupation =>  (nbrChambreOccupe/nbrTotalCh)*100
		float to = (new ReservDao().getAll().size()/new ChambreDao().getAll().size())*100;
		tauxOccup.setText(to+" %");

		//--- Chiffre d'affaire
		float ca = new ChambreDao().getChiffreAffaire();
		chiffreA.setText(ca+"");
		

		//===================EVENTS ============================
		//---- fermeture infobulle
		btnClose1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnClose1.setVisible(false);
				infoBulle1.setVisible(false);
				msgInfobulle.setVisible(false);
				}
		});
		
		//apparition de gestion Societe au click du button Societe
		gestionSociete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desk.removeAll();
				GestionSociete gestsoc = new GestionSociete();
				desk.add(gestsoc);
				gestsoc.show();
				desk.repaint();
				try {
					gestsoc.setMaximum(true);
				} catch (PropertyVetoException e1) {
					Logger.getLogger(DashBoardAdmin.class.getName()).log(Level.SEVERE, null, e1);
				}
			}
		});
		
	
		gestionHotel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desk.removeAll();
				GestionHotel gesthot = new GestionHotel();
				desk.add(gesthot);
				gesthot.show();
				desk.repaint();
				try {
					gesthot.setMaximum(true);
				} catch (PropertyVetoException e1) {
					Logger.getLogger(DashBoardAdmin.class.getName()).log(Level.SEVERE, null, e1);
				}
			}
		});
		
		//apparition de gestion Societe au click du button Chambre
		gestionChambre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desk.removeAll();
				GestionChambre gestch = new GestionChambre();
				desk.add(gestch);
				gestch.show();
				desk.repaint();
				try {
					gestch.setMaximum(true);
				} catch (PropertyVetoException e1) {
					Logger.getLogger(DashBoardAdmin.class.getName()).log(Level.SEVERE, null, e1);
				}
			}
		});
		
		//apparition de gestion Societe au click du button Client
		gestionClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desk.removeAll();
				GestionClient gestCl = new GestionClient();
				desk.add(gestCl);
				gestCl.show();
				desk.repaint();
				try {
					gestCl.setMaximum(true);
				} catch (PropertyVetoException e1) {
					Logger.getLogger(DashBoardAdmin.class.getName()).log(Level.SEVERE, null, e1);
				}
			}
		});

		
	}
}
