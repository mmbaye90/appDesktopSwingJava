package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entites.Chambre;
import entites.Db;
import entites.ICatalogue;


//implémentation de l'interface ICatalogue en remplaçant le type générique par l'objet voulu
public class ChambreDao implements ICatalogue<Chambre>{

    //overide de mes méthodes implémentées de l'interface ICatologue
    @Override

    //méthode qui retourne une collection sans parametres
    public ArrayList<Chambre> getAll() {

        //instanciation de la collection arraylist dans un objet de type générique Chambre
        ArrayList <Chambre> listeCh = new ArrayList<>();

        //encapsulation dans un try catch pour une exception (erreur)
        try {

            //req préparée
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * FROM chambre");

            //Execution de la req et assignation de celle-ci dans un objet de type Resulset
            ResultSet resp = ps.executeQuery();


            //Appel de la méthode next() de resulset pour passer à la ligne suivante si elle est évoluée a true
            while (resp.next()) {

                //instanciation de l'objet Chambre avec le mot clef new dans une variable de type Chambre
                Chambre ch = new Chambre();


                //Remplissage des champs de la classe Chambre avec les valeurs obtenues de la BD 
                ch.setId_ch(resp.getInt("id_ch"));
                ch.setNumCh(resp.getInt("numCh"));
                ch.setNbLitChSimp(resp.getInt("nbLitChSimp"));
                ch.setNbLitChDoub(resp.getInt("nbLitChDoub"));
                ch.setSupCh(resp.getInt("supCh"));
                ch.setSalleBainP(resp.getString("salleBainP"));
                ch.setTvCh(resp.getString("tvCh"));
                ch.setBalconCh(resp.getString("balconCh"));
                ch.setRefrigerCh(resp.getString("refrigerCh"));
                ch.setBaignCh(resp.getString("baignCh"));
                ch.setInsonoCh(resp.getString("insonoCh"));
                ch.setPrixNtCh(resp.getFloat("prixNtCh"));
                ch.setId_hotel(resp.getInt("id_hotel"));

                //Ajout de mon objet setté dans l'arraylist
                listeCh.add(ch);
            }

            //récupération de ma collection avec l'objet
            return listeCh;
        } catch (Exception e) {
            System.err.println("Liste des chambres introuvables".toUpperCase());
            e.printStackTrace();
            return null;
        }
    }

    @Override

    //méthode qui retourne un objet de type Chambre avec un parametre de type int
    public Chambre getById(int id) {
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * FROM Chambre WHERE id_ch = ?");
            ps.setInt(1, id);
            ResultSet resp = ps.executeQuery();
            if (resp.next()) {
                Chambre ch = new Chambre();

                ch.setId_ch(resp.getInt("id_ch"));
                ch.setNumCh(resp.getInt("numCh"));
                ch.setNbLitChSimp(resp.getInt("nbLitChSimp"));
                ch.setNbLitChDoub(resp.getInt("nbLitChDoub"));
                ch.setSupCh(resp.getInt("supCh"));
                ch.setSalleBainP(resp.getString("salleBainP"));
                ch.setTvCh(resp.getString("tvCh"));
                ch.setBalconCh(resp.getString("balconCh"));
                ch.setRefrigerCh(resp.getString("refrigerCh"));
                ch.setBaignCh(resp.getString("baignCh"));
                ch.setInsonoCh(resp.getString("insonoCh"));
                ch.setPrixNtCh(resp.getFloat("prixNtCh"));
                ch.setId_hotel(resp.getInt("id_hotel"));

                return ch;                  
            }else return null;
        } catch (Exception e) {
            System.err.println("Objet Chambre introuvable");
            e.printStackTrace();
            return null;
        }
    }

    @Override

    //méthode qui retourne un objet de type Chambre avec un parametre de type Str
    public ArrayList<Chambre> recherche(String w) {
        ArrayList <Chambre> listeCh = new ArrayList<>();
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * from chambre WHERE numCh LIKE ?");
            ps.setString(1, "%"+w+"%");
            ResultSet resp = ps.executeQuery();

            while (resp.next()) {
                Chambre ch = new Chambre();

                ch.setId_ch(resp.getInt("id_ch"));
                ch.setNumCh(resp.getInt("numCh"));
                ch.setNbLitChSimp(resp.getInt("nbLitChSimp"));
                ch.setNbLitChDoub(resp.getInt("nbLitChDoub"));
                ch.setSupCh(resp.getInt("supCh"));
                ch.setSalleBainP(resp.getString("salleBainP"));
                ch.setTvCh(resp.getString("tvCh"));
                ch.setBalconCh(resp.getString("balconCh"));
                ch.setRefrigerCh(resp.getString("refrigerCh"));
                ch.setBaignCh(resp.getString("baignCh"));
                ch.setInsonoCh(resp.getString("insonoCh"));
                ch.setPrixNtCh(resp.getFloat("prixNtCh"));
                ch.setId_hotel(resp.getInt("id_hotel"));

                listeCh.add(ch);
            }
            return listeCh;
        } catch (Exception e) {
            System.err.println("Donnée introuvable".toUpperCase());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    //méthode de type void avec un parametre de type Chambre(objet)
    public void save(Chambre ch) {
    try {
        
        //Si un id est trouvée on update notre objet passé en parametre
        if(ch.getId_ch() != 0) {
            PreparedStatement ps  = Db.con.prepareStatement
            ("UPDATE chambre SET numCh=?,nbLitChSimp=?,nbLitChDoub=?,supCh=?,salleBainP=?,tvCh=?,balconCh=?,refrigerCh=?,baignCh=?,insonoCh=?,prixNtCh=?,id_hotel=? WHERE id_ch=?");
            ps.setInt(1,ch.getNumCh());
            ps.setInt(2,ch.getNbLitChSimp());
            ps.setInt(3,ch.getNbLitChDoub());
            ps.setInt(4,ch.getSupCh());
            ps.setString(5, ch.getSalleBainP());
            ps.setString(6, ch.getTvCh());
            ps.setString(7, ch.getBaignCh());
            ps.setString(8, ch.getRefrigerCh());
            ps.setString(9, ch.getBaignCh());
            ps.setString(10, ch.getInsonoCh());
            ps.setFloat(11, ch.getPrixNtCh());
            ps.setInt(12, ch.getId_hotel());
            ps.setInt(13, ch.getId_ch());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée".toUpperCase());
        }else {

            //si un objet n'est pas trouvé,on insert l'objet en remplaçant ses propriétés par les données reçues  de la BD
            PreparedStatement ps  = Db.con.prepareStatement
            ("INSERT INTO chambre (numCh,nbLitChSimp,nbLitChDoub,supCh,salleBainP,tvCh,balconCh,refrigerCh,baignCh,insonoCh,prixNtCh,id_hotel) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1,ch.getNumCh());
            ps.setInt(2,ch.getNbLitChSimp());
            ps.setInt(3,ch.getNbLitChDoub());
            ps.setInt(4,ch.getSupCh());
            ps.setString(5, ch.getSalleBainP());
            ps.setString(6, ch.getTvCh());
            ps.setString(7, ch.getBaignCh());
            ps.setString(8, ch.getRefrigerCh());
            ps.setString(9, ch.getBaignCh());
            ps.setString(10, ch.getInsonoCh());
            ps.setFloat(11, ch.getPrixNtCh());
            ps.setInt(12, ch.getId_hotel());

            //on appelle la méthode executeUpdate() de la classe PS car on update # Select
            ps.executeUpdate();
            System.out.println("insertion Réussie".toUpperCase());
        }
        
    } catch (Exception e) {
        System.err.println("update ou insertion échouée".toUpperCase());
        e.printStackTrace();
    }
}

    @Override

    //méthode de type void avec un parametre de type int
    public void delete(int id) {
        try {
            
            PreparedStatement ps  = Db.con.prepareStatement
            ("DELETE FROM chambre WHERE id_ch=?");
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Supprimé".toUpperCase());
        
    } catch (Exception e) {
        System.out.println("non suppriùé".toLowerCase());
        e.printStackTrace();
    }
}
    
//Récuperation de la clef etrangere enfin d'acceder aux proprietés de l'Hotel
//si non on aura un null comme retour
    public int getIdHotelByChambre(int id) {
        try {
        
                PreparedStatement ps  = Db.con. prepareStatement
                ("SELECT id_hotel as id_hotel FROM chambre WHERE id_ch =?");
                ps.setInt(1,id);
                
                ResultSet res = ps.executeQuery();
                res.next();

                int idCh = res.getInt( "id_hotel" );
                return idCh;
            
        } catch (Exception e) {
            System.err.println("non trouvé !!!");
            e.printStackTrace();
            return 0;
        }
    }

    //req pour faire des statistiques
    //----------------------------------------- avoir le total des chambres--------
    public int getTotalChbre() {
        try {
        
                PreparedStatement ps  = Db.con. prepareStatement
                ("SELECT COUNT(*) as totalCh FROM chambre");
                ResultSet res = ps.executeQuery();
                res.next();

                int totalCh = res.getInt( "totalCh" );
                return totalCh;
            
        } catch (Exception e) {
            System.err.println("non trouvé !!!");
            e.printStackTrace();
            return 0;
        }
    }

    //----------------------------------------- avoir les chambres occupées--------
    public int getChbrOccup() {
        try {
                //chambre ocupée=> chambre qui se trouve dans réservation
                PreparedStatement ps  = Db.con. prepareStatement
                ("SELECT COUNT(*) AS chOccup FROM chambre WHERE id_ch IN (SELECT id_ch FROM reservation)");
                ResultSet res = ps.executeQuery();
                res.next();

                int totalCh = res.getInt( "chOccup" );
                return totalCh;
            
        } catch (Exception e) {
            System.err.println("non trouvé !!!");
            e.printStackTrace();
            return 0;
        }
    }

//----------------------------------------- CHIFFRE D'affaire --------
   public float getChiffreAffaire() {
            try {
                    PreparedStatement ps  = Db.con. prepareStatement
                    (
                    		"""	
                    		SELECT SUM(c.prixntCh * DATEDIFF(datefinR,dateDebRes)) as chiffreDaffaire FROM reservation r
                    		JOIN chambre c
                    		ON c.id_ch = r.id_ch               
                    		               		
                    		"""
                    );
                    ResultSet res = ps.executeQuery();
                    res.next();
    
                    float ca = res.getFloat( "chiffreDaffaire" );
                    return ca;
                
            } catch (Exception e) {
                System.err.println("non trouvé !!!");
                e.printStackTrace();
                return 0;
            }
        }
   
   

    

}
