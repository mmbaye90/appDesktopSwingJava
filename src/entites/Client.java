package entites;

public class Client {
	//Création des propriétées
	private int id_client;
	private String prenomC;
	private String adC;
	private int ageC;
	private String villeC;
	private String mailC;
	private String sexeC;
	private String telC;
	private String paysC;
	private String nomC;
	
	
	//Génération des constructeurs
	public Client() {
		super();
	}


	public Client(String prenomC, String adC, int ageC, String villeC, String mailC, String sexeC, String telC,
			String paysC, String nomC) {
		super();
		this.prenomC = prenomC;
		this.adC = adC;
		this.ageC = ageC;
		this.villeC = villeC;
		this.mailC = mailC;
		this.sexeC = sexeC;
		this.telC = telC;
		this.paysC = paysC;
		this.nomC = nomC;
	}


	public Client(int id_client, String prenomC, String adC, int ageC, String villeC, String mailC, String sexeC,
			String telC, String paysC, String nomC) {
		super();
		this.id_client = id_client;
		this.prenomC = prenomC;
		this.adC = adC;
		this.ageC = ageC;
		this.villeC = villeC;
		this.mailC = mailC;
		this.sexeC = sexeC;
		this.telC = telC;
		this.paysC = paysC;
		this.nomC = nomC;
	}

	//Génération des setters and getters
	public int getId_client() {
		return id_client;
	}


	public void setId_client(int id_client) {
		this.id_client = id_client;
	}


	public String getPrenomC() {
		return prenomC;
	}


	public void setPrenomC(String prenomC) {
		this.prenomC = prenomC;
	}


	public String getAdC() {
		return adC;
	}


	public void setAdC(String adC) {
		this.adC = adC;
	}


	public int getAgeC() {
		return ageC;
	}


	public void setAgeC(int ageC) {
		this.ageC = ageC;
	}


	public String getVilleC() {
		return villeC;
	}


	public void setVilleC(String villeC) {
		this.villeC = villeC;
	}


	public String getMailC() {
		return mailC;
	}


	public void setMailC(String mailC) {
		this.mailC = mailC;
	}


	public String getSexeC() {
		return sexeC;
	}


	public void setSexeC(String sexeC) {
		this.sexeC = sexeC;
	}


	public String getTelC() {
		return telC;
	}


	public void setTelC(String telC) {
		this.telC = telC;
	}


	public String getPaysC() {
		return paysC;
	}


	public void setPaysC(String paysC) {
		this.paysC = paysC;
	}


	public String getNomC() {
		return nomC;
	}


	public void setNomC(String nomC) {
		this.nomC = nomC;
	}

	//overide de la méthode string hétitée de la Classe Object
	@Override
	public String toString() {
		return "Client [id_client=" + id_client + ", prenomC=" + prenomC + ", adC=" + adC + ", ageC=" + ageC
				+ ", villeC=" + villeC + ", mailC=" + mailC + ", sexeC=" + sexeC + ", telC=" + telC + ", paysC=" + paysC
				+ ", nomC=" + nomC + "]";
	}
	
	
	
}
