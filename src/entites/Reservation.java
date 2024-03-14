package entites;

public class Reservation {
	//Création des propriétées
	private int id_reserv;
	private String dateDebRes;
	private String dateFinR;
	private int nbPerso;
	private int id_ch;
	private int id_client;


	//Génération des constructeurs
	public Reservation() {
		super();
	}
	public Reservation(String dateDebRes, String dateFinR, int nbPerso, int id_ch, int id_client) {
		super();
		this.dateDebRes = dateDebRes;
		this.dateFinR = dateFinR;
		this.nbPerso = nbPerso;
		this.id_ch = id_ch;
		this.id_client = id_client;
	}
	public Reservation(int id_reserv, String dateDebRes, String dateFinR, int nbPerso, int id_ch, int id_client) {
		super();
		this.id_reserv = id_reserv;
		this.dateDebRes = dateDebRes;
		this.dateFinR = dateFinR;
		this.nbPerso = nbPerso;
		this.id_ch = id_ch;
		this.id_client = id_client;
	}


	//Génération des setters and getters
	public int getId_reserv() {
		return id_reserv;
	}
	public void setId_reserv(int id_reserv) {
		this.id_reserv = id_reserv;
	}
	public String getDateDebRes() {
		return dateDebRes;
	}
	public void setDateDebRes(String dateDebRes) {
		this.dateDebRes = dateDebRes;
	}
	public String getDateFinR() {
		return dateFinR;
	}
	public void setDateFinR(String dateFinR) {
		this.dateFinR = dateFinR;
	}
	public int getNbPerso() {
		return nbPerso;
	}
	public void setNbPerso(int nbPerso) {
		this.nbPerso = nbPerso;
	}
	public int getId_ch() {
		return id_ch;
	}
	public void setId_ch(int id_ch) {
		this.id_ch = id_ch;
	}
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}


	//overide de la méthode string hétitée de la Classe Object
	@Override
	public String toString() {
		return "Reservation [id_reserv=" + id_reserv + ", dateDebRes=" + dateDebRes + ", dateFinR=" + dateFinR
				+ ", nbPerso=" + nbPerso + ", id_ch=" + id_ch + ", id_client=" + id_client + "]";
	}
	
	
}
