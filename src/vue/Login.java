package vue;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Components.Button;
import Components.Password;
import Components.TextField;


public class Login extends JFrame {
	//================================ Variables globales =======================
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel container;
	private Image img;
	private Image logo;
	private String userName;
	private String pwd;
	Button btnAdmnm;
	Button btnRecep;
	TextField champText;
	Password champPwd;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	public Login() {
		//configuration fenetre globale
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 715, 503);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Chargement image
        ImageIcon iconbg = new ImageIcon(this.getClass().getResource("/bgLogin.jpg"));
        img = iconbg.getImage().getScaledInstance(715, 503, Image.SCALE_DEFAULT);
        
        //ajout image en bacgrd
       
        container = new JLabel(new ImageIcon(img));
        
		//===================LOGO
		//Chargement Logo et configuration Style
        ImageIcon iconlogo = new ImageIcon(this.getClass().getResource("/logo.png"));
        logo = iconlogo.getImage().getScaledInstance(55,70, Image.SCALE_DEFAULT);

		JLabel contLogo = new JLabel(new ImageIcon(logo));
		contLogo.setHorizontalAlignment(SwingConstants.CENTER);
		contLogo.setBounds(620, 0, 55,70);
		//ajout logo
		container.add(contLogo);
		
		//Configuration du style Titre Login 
		JLabel titre = new JLabel("L O G I N");
		titre.setForeground(SystemColor.textHighlightText);
		titre.setFont(new Font("Tahoma", Font.BOLD, 16));
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setBounds(319, 70, 80, 14);
		

		//Champ Auth UserName
		champText = new TextField();
		champText.setText("userName");
		champText.setForeground(SystemColor.textHighlightText);
		champText.setFont(new Font("Tahoma", Font.BOLD, 18));
		champText.setHorizontalAlignment(SwingConstants.CENTER);
		champText.setBounds(280, 168, 185, 34);
		//Champ Auth Password
		champPwd = new Password();
		champPwd.setText("userName");
		champPwd.setForeground(SystemColor.textHighlightText);
		champPwd.setFont(new Font("Tahoma", Font.BOLD, 18));
		champPwd.setHorizontalAlignment(SwingConstants.CENTER);
		champPwd.setBounds(281, 253, 185, 34);
		//ajout dans container
		container.add(champPwd);
		
		//ajout titre au container
		container.add(titre);
        
		//ajout ChmpText
		container.add(champText);
		
		//Champ Auth BTN ADMIN
		btnAdmnm = new Button();
		btnAdmnm.setText("Admin");
		btnAdmnm.setForeground(SystemColor.textHighlightText);
		btnAdmnm.setBackground(Color.BLACK);
		btnAdmnm.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAdmnm.setHorizontalAlignment(SwingConstants.CENTER);
		btnAdmnm.setBounds(281, 349, 85, 34);
		//ajout btnAdm
		container.add(btnAdmnm);
		//Champ Auth BTN RECEP				
		btnRecep = new Button();
		btnRecep.setText("Recep");
		btnRecep.setHorizontalAlignment(SwingConstants.CENTER);
		btnRecep.setForeground(SystemColor.textHighlightText);
		btnRecep.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRecep.setBackground(Color.BLACK);
		btnRecep.setBounds(381, 349, 85, 34);
		container.add(btnRecep);
        
		container.setBounds(0, 0, 715, 503);
		contentPane.add(container);
		
		//============================== EVENTS UPDATING INPUT TEXT=========================
        champText.getDocument().addDocumentListener(new DocumentListener() {

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
        champPwd.getDocument().addDocumentListener(new DocumentListener() {

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
       btnAdmnm.setEnabled(false);
       btnRecep.setEnabled(false);
       //============================= EVENTS CLICK BUTTON ===================
       
       btnAdmnm.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			dispose();
			new DashBoardAdmin().setVisible(true);
		}
       	});
       
       btnRecep.addActionListener(new ActionListener() {
   		
		public void actionPerformed(ActionEvent e) {
			dispose();
			new DashBoardRecep().setVisible(true);
		}
       	});

       
       
	}
	
	
	//-----------------------------------updateFields
    private void updateFields() {
        userName = champText.getText();
        pwd = new String(champPwd.getPassword());

        if (
        		!userName.isEmpty() 
        		&& userName.equalsIgnoreCase("admin")
        		&& !pwd.isEmpty()
        		&& pwd.equalsIgnoreCase("admin")
        		) 
        {
        	btnAdmnm.setEnabled(true);
        } 
        else if (
        		!pwd.isEmpty()
        		&& pwd.equalsIgnoreCase("Recep")
        		&& !userName.isEmpty()
        		&& pwd.equalsIgnoreCase("recep")
        		) 
        {
            btnRecep.setEnabled(true);
        } else {
        	btnAdmnm.setEnabled(false);
            btnRecep.setEnabled(false);
        }
    }

}
