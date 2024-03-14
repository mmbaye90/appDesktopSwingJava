package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entites.Db;
import entites.ICatalogue;
import entites.Paiement;


//Meme commentaire que dans la classe Chambre
public class PaiementDao implements ICatalogue<Paiement>{

    @Override
    public ArrayList<Paiement> getAll() {
        ArrayList <Paiement> listeP = new ArrayList<>();
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * FROM paiement");

            ResultSet resp = ps.executeQuery();

            while (resp.next()) {
                Paiement p = new Paiement();
                p.setId_p(resp.getInt("id_p"));
                p.setDateP(resp.getString("dateP"));
                p.setMontP(resp.getFloat("montP"));
                p.setMethP(resp.getString("methP"));
                p.setId_reserv(resp.getInt("id_reserv"));
                listeP.add(p);
            }
            return listeP;
        } catch (Exception e) {
            System.err.println("Liste des paiements introuvable".toUpperCase());
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Paiement getById(int id) {
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * FROM paiement WHERE id_p = ?");
            ps.setInt(1, id);
            ResultSet resp = ps.executeQuery();
            if (resp.next()) {
                Paiement p = new Paiement();
                p.setId_p(resp.getInt("id_p"));
                p.setDateP(resp.getString("dateP"));
                p.setMontP(resp.getFloat("montP"));
                p.setMethP(resp.getString("methP"));
                p.setId_reserv(resp.getInt("id_reserv"));

                return p;                  
            }else return null;
        } catch (Exception e) {
            System.err.println("Objet Paiement introuvable");
            e.printStackTrace();
            return null;
        }  
    }

    @Override
    public ArrayList<Paiement> recherche(String w) {
        ArrayList <Paiement> listeP = new ArrayList<>();
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * from paiement WHERE dateP LIKE ?");
            ps.setString(1, "%"+w+"%");
            ResultSet resp = ps.executeQuery();

            while (resp.next()) {
                Paiement p = new Paiement();
                p.setId_p(resp.getInt("id_p"));
                p.setDateP(resp.getString("dateP"));
                p.setMontP(resp.getFloat("montP"));
                p.setMethP(resp.getString("methP"));
                p.setId_reserv(resp.getInt("id_reserv"));
                listeP.add(p);
            }
            return listeP;
        } catch (Exception e) {
            System.err.println("Donnée introuvable".toUpperCase());
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void save(Paiement p) {
    try {
        
        if(p.getId_p() != 0) {
            PreparedStatement ps  = Db.con.prepareStatement
            ("UPDATE paiement SET dateP=?,montP=?,methP=?,id_reserv=? WHERE id_p=?");
            ps.setString(1,p.getDateP());
            ps.setFloat(2,p.getMontP());
            ps.setString(3,p.getMethP());
            ps.setInt(4,p.getId_reserv());
            ps.setInt(5,p.getId_p());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée".toUpperCase());
        }else {
            PreparedStatement ps  = Db.con.prepareStatement
            ("INSERT INTO paiement (dateP,montP,methP,id_reserv) VALUES(?,?,?,?)");
            ps.setString(1,p.getDateP());
            ps.setFloat(2,p.getMontP());
            ps.setString(3,p.getMethP());
            ps.setInt(4,p.getId_reserv());
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
            ("DELETE FROM paiement WHERE id_p=?");
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Supprimé".toUpperCase());
        
    } catch (Exception e) {
        System.out.println("non suppriùé".toLowerCase());
        e.printStackTrace();
    }    }


//req pour obtenir la clef etrangéres de ces tables enfin d'avoir acces à leur propriété et 
//d'en afficher quelque
    public int getIdResByPaiement(int id) {
        try {
        
                PreparedStatement ps  = Db.con. prepareStatement
                ("SELECT id_reserv as id_reserv FROM paiement WHERE  id_p =?");
                ps.setInt(1,id);
                
                ResultSet res = ps.executeQuery();
                res.next();
    
                int idCh = res.getInt( "id_reserv" );
                return idCh;
            
        } catch (Exception e) {
            System.err.println("non trouvé !!!");
            e.printStackTrace();
            return 0;
        }
    }

}
