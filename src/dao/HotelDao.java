package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entites.Db;
import entites.Hotel;
import entites.ICatalogue;


//Meme commentaire que dans la classe Chambre
public class HotelDao implements ICatalogue<Hotel>{

    @Override
    public ArrayList<Hotel> getAll() {
        ArrayList <Hotel> listeH = new ArrayList<>();
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * FROM Hotel");

            ResultSet resp = ps.executeQuery();

            while (resp.next()) {
                Hotel h = new Hotel();
                h.setId_hotel(resp.getInt("id_hotel"));
                h.setNomH(resp.getString("nomH"));
                h.setAdresseH(resp.getString("adresseH"));
                h.setVilleH(resp.getString("villeH"));
                h.setDescriptionH(resp.getString("descriptionH"));
                h.setParkingH(resp.getString("parkingH"));
                h.setWifiH(resp.getString("wifiH"));
                h.setHeureAaH(resp.getString("heureAaH"));
                h.setHeureDepH(resp.getString("heureDepH"));
                h.setPiscineH(resp.getString("piscineH"));
                h.setNavetteH(resp.getString("navetteH"));
                h.setPresenceAniH(resp.getString("presenceAniH"));
                h.setCatH(resp.getInt("catH"));
                h.setId_soc(resp.getInt("id_soc"));
                listeH.add(h);
            }
            return listeH;
        } catch (Exception e) {
            System.err.println("Liste des hotels introuvable".toUpperCase());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Hotel getById(int id) {
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * FROM hotel WHERE id_hotel = ?");
            ps.setInt(1, id);
            ResultSet resp = ps.executeQuery();
            if (resp.next()) {
                Hotel h = new Hotel();
                h.setId_hotel(resp.getInt("id_hotel"));
                h.setNomH(resp.getString("nomH"));
                h.setAdresseH(resp.getString("adresseH"));
                h.setVilleH(resp.getString("villeH"));
                h.setDescriptionH(resp.getString("descriptionH"));
                h.setParkingH(resp.getString("parkingH"));
                h.setWifiH(resp.getString("wifiH"));
                h.setHeureAaH(resp.getString("heureAaH"));
                h.setHeureDepH(resp.getString("heureDepH"));
                h.setPiscineH(resp.getString("piscineH"));
                h.setNavetteH(resp.getString("navetteH"));
                h.setPresenceAniH(resp.getString("presenceAniH"));
                h.setCatH(resp.getInt("catH"));
                h.setId_soc(resp.getInt("id_soc"));
                return h;                  
            }
            else return null;
        } catch (Exception e) {
            System.err.println("Objet Chambre introuvable");
            e.printStackTrace();
            return null;
        }  
    }

    @Override
    public ArrayList<Hotel> recherche(String w) {
        ArrayList <Hotel> listeH = new ArrayList<>();
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * from hotel WHERE nomH LIKE ? OR villeH LIKE ?");
            ps.setString(1, "%"+w+"%");
            ps.setString(2, "%"+w+"%");

            ResultSet resp = ps.executeQuery();

            while (resp.next()) {
                Hotel h = new Hotel();
                h.setId_hotel(resp.getInt("id_hotel"));
                h.setNomH(resp.getString("nomH"));
                h.setAdresseH(resp.getString("adresseH"));
                h.setVilleH(resp.getString("villeH"));
                h.setDescriptionH(resp.getString("descriptionH"));
                h.setParkingH(resp.getString("parkingH"));
                h.setWifiH(resp.getString("wifiH"));
                h.setHeureAaH(resp.getString("heureAaH"));
                h.setHeureDepH(resp.getString("heureDepH"));
                h.setPiscineH(resp.getString("piscineH"));
                h.setNavetteH(resp.getString("navetteH"));
                h.setPresenceAniH(resp.getString("presenceAniH"));
                h.setCatH(resp.getInt("catH"));
                h.setId_soc(resp.getInt("id_soc"));
                listeH.add(h);

            }
            return listeH;
        } catch (Exception e) {
            System.err.println("Donnée introuvable".toUpperCase());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Hotel htl) {
    try {
        
        if(htl.getId_hotel() != 0) {
            PreparedStatement ps  = Db.con.prepareStatement
            ("UPDATE hotel SET nomH=?,adresseH=?,villeH=?,descriptionH=?,parkingH=?,wifiH=?,heureAaH=?,heureDepH=?,piscineH=?,navetteH=?,presenceAniH=?,catH=?,id_soc=? WHERE id_hotel=?");
            ps.setString(1,htl.getNomH());
            ps.setString(2,htl.getAdresseH());
            ps.setString(3,htl.getVilleH());
            ps.setString(4,htl.getDescriptionH());
            ps.setString(5,htl.getParkingH());
            ps.setString(6,htl.getWifiH());
            ps.setString(7, htl.getHeureAaH());
            ps.setString(8, htl.getHeureDepH());
            ps.setString(9, htl.getPiscineH());
            ps.setString(10,htl.getNavetteH());
            ps.setString(11, htl.getPresenceAniH());
            ps.setInt(12,htl.getCatH());
            ps.setInt(13,htl.getId_soc());
            ps.setInt(14,htl.getId_hotel());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée".toUpperCase());
        }else {
            PreparedStatement ps  = Db.con.prepareStatement
            ("INSERT INTO hotel (nomH,adresseH,villeH,descriptionH,parkingH,wifiH,heureAaH,heureDepH,piscineH,navetteH,presenceAniH,catH,id_soc) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1,htl.getNomH());
            ps.setString(2,htl.getAdresseH());
            ps.setString(3,htl.getVilleH());
            ps.setString(4,htl.getDescriptionH());
            ps.setString(5,htl.getParkingH());
            ps.setString(6,htl.getWifiH());
            ps.setString(7, htl.getHeureAaH());
            ps.setString(8, htl.getHeureDepH());
            ps.setString(9, htl.getPiscineH());
            ps.setString(10,htl.getNavetteH());
            ps.setString(11, htl.getPresenceAniH());
            ps.setInt(12,htl.getCatH());
            ps.setInt(13,htl.getCatH());
            ps.executeUpdate();
            System.out.println("insertion Réussie".toUpperCase());
        }
        
    } catch (Exception e) {
        System.err.println("update ou insertion échouée".toUpperCase());
        e.printStackTrace();
    }

    }

    @Override
    public void delete(int id) {
        try {
            
            PreparedStatement ps  = Db.con.prepareStatement
            ("DELETE FROM hotel WHERE id_hotel=?");
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Supprimé".toUpperCase());
        
    } catch (Exception e) {
        System.out.println("non suppriùé".toLowerCase());
        e.printStackTrace();
    }
    }

    public int getIdSocieteByHotel(int id) {
        try {
        
                PreparedStatement ps  = Db.con. prepareStatement
                ("SELECT id_soc as id_soc FROM hotel WHERE  id_hotel =?");
                ps.setInt(1,id);
                
                ResultSet res = ps.executeQuery();
                res.next();
    
                int idCh = res.getInt( "id_soc" );
                return idCh;
            
        } catch (Exception e) {
            System.err.println("non trouvé !!!");
            e.printStackTrace();
            return 0;
        }
    }
    
    
    public int getIntIdCatHotel(int id) {
        try {
        
                PreparedStatement ps  = Db.con. prepareStatement
                ("select catH as id_cat from hotel where id_hotel =?");
                ps.setInt(1,id);
                
                ResultSet res = ps.executeQuery();
                res.next();
    
                int idCh = res.getInt( "id_cat" );
                return idCh;
            
        } catch (Exception e) {
            System.err.println("non trouvé !!!");
            e.printStackTrace();
            return 0;
        }
    }
    
    

}
