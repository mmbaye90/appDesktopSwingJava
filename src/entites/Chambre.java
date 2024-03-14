package entites;

public class Chambre {

	//Création des propriétées
	private int id_ch;
	private int numCh;
	private int nbLitChSimp;
	private int nbLitChDoub;
	private int supCh;
	private String salleBainP;
	private String tvCh;
	private String balconCh;
	private String refrigerCh;
	private String baignCh;
	private String insonoCh;
	private float prixNtCh;
	private int id_hotel;
	

	//Génération des constructeurs
	public Chambre() {
		super();
	}

	public Chambre(int numCh, int nbLitChSimp, int nbLitChDoub, int supCh, String salleBainP, String tvCh,
			String balconCh, String refrigerCh, String baignCh, String insonoCh, float prixNtCh, int id_hotel) {
		super();
		this.numCh = numCh;
		this.nbLitChSimp = nbLitChSimp;
		this.nbLitChDoub = nbLitChDoub;
		this.supCh = supCh;
		this.salleBainP = salleBainP;
		this.tvCh = tvCh;
		this.balconCh = balconCh;
		this.refrigerCh = refrigerCh;
		this.baignCh = baignCh;
		this.insonoCh = insonoCh;
		this.prixNtCh = prixNtCh;
		this.id_hotel = id_hotel;
	}

	public Chambre(int id_ch, int numCh, int nbLitChSimp, int nbLitChDoub, int supCh, String salleBainP, String tvCh,
			String balconCh, String refrigerCh, String baignCh, String insonoCh, float prixNtCh, int id_hotel) {
		super();
		this.id_ch = id_ch;
		this.numCh = numCh;
		this.nbLitChSimp = nbLitChSimp;
		this.nbLitChDoub = nbLitChDoub;
		this.supCh = supCh;
		this.salleBainP = salleBainP;
		this.tvCh = tvCh;
		this.balconCh = balconCh;
		this.refrigerCh = refrigerCh;
		this.baignCh = baignCh;
		this.insonoCh = insonoCh;
		this.prixNtCh = prixNtCh;
		this.id_hotel = id_hotel;
	}


	//Génération des setters and getters
	public int getId_ch() {
		return id_ch;
	}

	public void setId_ch(int id_ch) {
		this.id_ch = id_ch;
	}

	public int getNumCh() {
		return numCh;
	}

	public void setNumCh(int numCh) {
		this.numCh = numCh;
	}

	public int getNbLitChSimp() {
		return nbLitChSimp;
	}

	public void setNbLitChSimp(int nbLitChSimp) {
		this.nbLitChSimp = nbLitChSimp;
	}

	public int getNbLitChDoub() {
		return nbLitChDoub;
	}

	public void setNbLitChDoub(int nbLitChDoub) {
		this.nbLitChDoub = nbLitChDoub;
	}

	public int getSupCh() {
		return supCh;
	}

	public void setSupCh(int supCh) {
		this.supCh = supCh;
	}

	public String getSalleBainP() {
		return salleBainP;
	}

	public void setSalleBainP(String salleBainP) {
		this.salleBainP = salleBainP;
	}

	public String getTvCh() {
		return tvCh;
	}

	public void setTvCh(String tvCh) {
		this.tvCh = tvCh;
	}

	public String getBalconCh() {
		return balconCh;
	}

	public void setBalconCh(String balconCh) {
		this.balconCh = balconCh;
	}

	public String getRefrigerCh() {
		return refrigerCh;
	}

	public void setRefrigerCh(String refrigerCh) {
		this.refrigerCh = refrigerCh;
	}

	public String getBaignCh() {
		return baignCh;
	}

	public void setBaignCh(String baignCh) {
		this.baignCh = baignCh;
	}

	public String getInsonoCh() {
		return insonoCh;
	}

	public void setInsonoCh(String insonoCh) {
		this.insonoCh = insonoCh;
	}

	public float getPrixNtCh() {
		return prixNtCh;
	}

	public void setPrixNtCh(float prixNtCh) {
		this.prixNtCh = prixNtCh;
	}

	public int getId_hotel() {
		return id_hotel;
	}

	public void setId_hotel(int id_hotel) {
		this.id_hotel = id_hotel;
	}


	//overide de la méthode string hétitée de la Classe Object
	@Override
	public String toString() {
		return "Chambre [id_ch=" + id_ch + ", numCh=" + numCh + ", nbLitChSimp=" + nbLitChSimp + ", nbLitChDoub="
				+ nbLitChDoub + ", supCh=" + supCh + ", salleBainP=" + salleBainP + ", tvCh=" + tvCh + ", balconCh="
				+ balconCh + ", refrigerCh=" + refrigerCh + ", baignCh=" + baignCh + ", insonoCh=" + insonoCh
				+ ", prixNtCh=" + prixNtCh + ", id_hotel=" + id_hotel + "]";
	}
	
	
	
	
}
