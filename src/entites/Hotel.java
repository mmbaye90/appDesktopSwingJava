package entites;

public class Hotel {
	//Création des propriétées
	private int id_hotel;
	private String nomH;
	private String adresseH;
	private String villeH;
	private String descriptionH;
	private String parkingH;
	private String wifiH;
	private String heureAaH;
	private String heureDepH;
	private String piscineH;
	private String navetteH;
	private String presenceAniH;
	private int catH;
	private int id_soc;

	//Génération des constructeurs
	public Hotel() {
		super();
	}
	public Hotel(String nomH, String adresseH, String villeH, String descriptionH, String parkingH, String wifiH,
			String heureAaH, String heureDepH, String piscineH, String navetteH, String presenceAniH, int catH,
			int id_soc) {
		super();
		this.nomH = nomH;
		this.adresseH = adresseH;
		this.villeH = villeH;
		this.descriptionH = descriptionH;
		this.parkingH = parkingH;
		this.wifiH = wifiH;
		this.heureAaH = heureAaH;
		this.heureDepH = heureDepH;
		this.piscineH = piscineH;
		this.navetteH = navetteH;
		this.presenceAniH = presenceAniH;
		this.catH = catH;
		this.id_soc = id_soc;
	}
	public Hotel(int id_hotel, String nomH, String adresseH, String villeH, String descriptionH, String parkingH,
			String wifiH, String heureAaH, String heureDepH, String piscineH, String navetteH, String presenceAniH,
			int catH, int id_soc) {
		super();
		this.id_hotel = id_hotel;
		this.nomH = nomH;
		this.adresseH = adresseH;
		this.villeH = villeH;
		this.descriptionH = descriptionH;
		this.parkingH = parkingH;
		this.wifiH = wifiH;
		this.heureAaH = heureAaH;
		this.heureDepH = heureDepH;
		this.piscineH = piscineH;
		this.navetteH = navetteH;
		this.presenceAniH = presenceAniH;
		this.catH = catH;
		this.id_soc = id_soc;
	}


	//Génération des setters and getters
	public int getId_hotel() {
		return id_hotel;
	}
	public void setId_hotel(int id_hotel) {
		this.id_hotel = id_hotel;
	}
	public String getNomH() {
		return nomH;
	}
	public void setNomH(String nomH) {
		this.nomH = nomH;
	}
	public String getAdresseH() {
		return adresseH;
	}
	public void setAdresseH(String adresseH) {
		this.adresseH = adresseH;
	}
	public String getVilleH() {
		return villeH;
	}
	public void setVilleH(String villeH) {
		this.villeH = villeH;
	}
	public String getDescriptionH() {
		return descriptionH;
	}
	public void setDescriptionH(String descriptionH) {
		this.descriptionH = descriptionH;
	}
	public String getParkingH() {
		return parkingH;
	}
	public void setParkingH(String parkingH) {
		this.parkingH = parkingH;
	}
	public String getWifiH() {
		return wifiH;
	}
	public void setWifiH(String wifiH) {
		this.wifiH = wifiH;
	}
	public String getHeureAaH() {
		return heureAaH;
	}
	public void setHeureAaH(String heureAaH) {
		this.heureAaH = heureAaH;
	}
	public String getHeureDepH() {
		return heureDepH;
	}
	public void setHeureDepH(String heureDepH) {
		this.heureDepH = heureDepH;
	}
	public String getPiscineH() {
		return piscineH;
	}
	public void setPiscineH(String piscineH) {
		this.piscineH = piscineH;
	}
	public String getNavetteH() {
		return navetteH;
	}
	public void setNavetteH(String navetteH) {
		this.navetteH = navetteH;
	}
	public String getPresenceAniH() {
		return presenceAniH;
	}
	public void setPresenceAniH(String presenceAniH) {
		this.presenceAniH = presenceAniH;
	}
	public int getCatH() {
		return catH;
	}
	public void setCatH(int catH) {
		this.catH = catH;
	}
	public int getId_soc() {
		return id_soc;
	}
	public void setId_soc(int id_soc) {
		this.id_soc = id_soc;
	}

	//overide de la méthode string hétitée de la Classe Object
	@Override
	public String toString() {
		return "Hotel [id_hotel=" + id_hotel + ", nomH=" + nomH + ", adresseH=" + adresseH + ", villeH=" + villeH
				+ ", descriptionH=" + descriptionH + ", parkingH=" + parkingH + ", wifiH=" + wifiH + ", heureAaH="
				+ heureAaH + ", heureDepH=" + heureDepH + ", piscineH=" + piscineH + ", navetteH=" + navetteH
				+ ", presenceAniH=" + presenceAniH + ", catH=" + catH + ", id_soc=" + id_soc + "]";
	}
	
	
}
