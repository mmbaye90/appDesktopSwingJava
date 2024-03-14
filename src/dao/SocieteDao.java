package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entites.Db;
import entites.ICatalogue;
import entites.Societe;

//Meme commentaire que dans la classe Chambre
public class SocieteDao implements ICatalogue<Societe>{

    @Override
    public ArrayList<Societe> getAll() {
        ArrayList <Societe> listeS = new ArrayList<>();
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * FROM societe");

            ResultSet resp = ps.executeQuery();

            while (resp.next()) {
                Societe s = new Societe();
                s.setId_soc(resp.getInt("id_soc"));
                s.setNumSiret(resp.getString("numSiret"));
                s.setNom(resp.getString("nom"));
                s.setAdresseS(resp.getString("adresseS"));
                listeS.add(s);
            }
            return listeS;
        } catch (Exception e) {
            System.err.println("Liste des Sociétés introuvable".toUpperCase());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Societe getById(int id) {
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * FROM societe WHERE id_soc = ?");
            ps.setInt(1, id);
            ResultSet resp = ps.executeQuery();
            if (resp.next()) {
                Societe s = new Societe();
                s.setId_soc(resp.getInt("id_soc"));
                s.setNumSiret(resp.getString("numSiret"));
                s.setNom(resp.getString("nom"));
                s.setAdresseS(resp.getString("adresseS"));
                
                return s;                  
            }else return null;
        } catch (Exception e) {
            System.err.println("Objet Chambre introuvable");
            e.printStackTrace();
            return null;
        }  
    }

    @Override
    public ArrayList<Societe> recherche(String w) {
        ArrayList <Societe> listeS = new ArrayList<>();
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * from societe WHERE nom LIKE ?");
            ps.setString(1, "%"+w+"%");
            ResultSet resp = ps.executeQuery();

            while (resp.next()) {
                Societe s = new Societe();
                s.setId_soc(resp.getInt("id_soc"));
                s.setNumSiret(resp.getString("numSiret"));
                s.setNom(resp.getString("nom"));
                s.setAdresseS(resp.getString("adresseS"));
                listeS.add(s);

            }
            return listeS;
        } catch (Exception e) {
            System.err.println("Donnée introuvable".toUpperCase());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Societe soc) {
    try {
        
        if(soc.getId_soc() != 0) {
            PreparedStatement ps  = Db.con.prepareStatement
            ("UPDATE societe SET numSiret=?,nom=?,adresseS=? WHERE id_soc=?");
            ps.setString(1,soc.getNumSiret());
            ps.setString(2,soc.getNom());
            ps.setString(3,soc.getAdresseS());
            ps.setInt(4,soc.getId_soc());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée".toUpperCase());
        }else {
            PreparedStatement ps  = Db.con.prepareStatement
            ("INSERT INTO societe (numSiret,nom,adresseS) VALUES(?,?,?)");
            ps.setString(1,soc.getNumSiret());
            ps.setString(2,soc.getNom());
            ps.setString(3,soc.getAdresseS());
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
            ("DELETE FROM societe WHERE id_soc=?");
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Supprimé".toUpperCase());
        
    } catch (Exception e) {
        System.out.println("non suppriùé".toLowerCase());
        e.printStackTrace();
    }
 }

}
