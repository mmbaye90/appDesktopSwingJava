package entites;

public class Paiement {
	//Création des propriétées
	private int id_p;
	private String dateP;
	private float montP;
	private String methP;
	private int id_reserv;
	public Paiement() {
		super();
	}

	//Génération des constructeurs
	public Paiement(String dateP, float montP, String methP, int id_reserv) {
		super();
		this.dateP = dateP;
		this.montP = montP;
		this.methP = methP;
		this.id_reserv = id_reserv;
	}
	public Paiement(int id_p, String dateP, float montP, String methP, int id_reserv) {
		super();
		this.id_p = id_p;
		this.dateP = dateP;
		this.montP = montP;
		this.methP = methP;
		this.id_reserv = id_reserv;
	}



	//Génération des setters and getters
	public int getId_p() {
		return id_p;
	}
	public void setId_p(int id_p) {
		this.id_p = id_p;
	}
	public String getDateP() {
		return dateP;
	}
	public void setDateP(String dateP) {
		this.dateP = dateP;
	}
	public float getMontP() {
		return montP;
	}
	public void setMontP(float montP) {
		this.montP = montP;
	}
	public String getMethP() {
		return methP;
	}
	public void setMethP(String methP) {
		this.methP = methP;
	}
	public int getId_reserv() {
		return id_reserv;
	}
	public void setId_reserv(int id_reserv) {
		this.id_reserv = id_reserv;
	}


	//overide de la méthode string hétitée de la Classe Object
	@Override
	public String toString() {
		return "Paiement [id_p=" + id_p + ", dateP=" + dateP + ", montP=" + montP + ", methP=" + methP + ", id_reserv="
				+ id_reserv + "]";
	}
	
	
	
}
