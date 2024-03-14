package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entites.Client;
import entites.Db;
import entites.ICatalogue;


//Meme commentaire que dans la classe Chambre
public class ClientDao implements ICatalogue<Client>{

    @Override
    public ArrayList<Client> getAll() {
        ArrayList <Client> listec = new ArrayList<>();
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * FROM client");

            ResultSet resp = ps.executeQuery();

            while (resp.next()) {
                Client cl = new Client();

                cl.setId_client(resp.getInt("id_client"));
                cl.setPrenomC(resp.getString("prenomC"));
                cl.setAdC(resp.getString("adC"));
                cl.setAgeC(resp.getInt("ageC"));
                cl.setVilleC(resp.getString("villeC"));
                cl.setMailC(resp.getString("mailC"));
                cl.setSexeC(resp.getString("sexeC"));
                cl.setTelC(resp.getString("telC"));
                cl.setPaysC(resp.getString("paysC"));
                cl.setNomC(resp.getString("nomC"));

                listec.add(cl);
            }
            return listec;
        } catch (Exception e) {
            System.err.println("Liste des clients introuvable".toUpperCase());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Client getById(int id) {
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * FROM client WHERE id_client = ?");
            ps.setInt(1, id);
            ResultSet resp = ps.executeQuery();
            if (resp.next()) {
                Client cl = new Client();

                cl.setId_client(resp.getInt("id_client"));
                cl.setPrenomC(resp.getString("prenomC"));
                cl.setAdC(resp.getString("adC"));
                cl.setAgeC(resp.getInt("ageC"));
                cl.setVilleC(resp.getString("villeC"));
                cl.setMailC(resp.getString("mailC"));
                cl.setSexeC(resp.getString("sexeC"));
                cl.setTelC(resp.getString("telC"));
                cl.setPaysC(resp.getString("paysC"));
                cl.setNomC(resp.getString("nomC"));
                
                return cl;                  
            }else return null;
        } catch (Exception e) {
            System.err.println("Objet Chambre introuvable");
            e.printStackTrace();
            return null;
        }  
          
    }

    @Override
    public ArrayList<Client> recherche(String w) {
        ArrayList <Client> listeCh = new ArrayList<>();
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * from client WHERE prenomC LIKE ?");
            ps.setString(1, "%"+w+"%");
            ResultSet resp = ps.executeQuery();

            while (resp.next()) {
                Client cl = new Client();

                cl.setId_client(resp.getInt("id_client"));
                cl.setPrenomC(resp.getString("prenomC"));
                cl.setAdC(resp.getString("adC"));
                cl.setAgeC(resp.getInt("ageC"));
                cl.setVilleC(resp.getString("villeC"));
                cl.setMailC(resp.getString("mailC"));
                cl.setSexeC(resp.getString("sexeC"));
                cl.setTelC(resp.getString("telC"));
                cl.setPaysC(resp.getString("paysC"));
                cl.setNomC(resp.getString("nomC"));

                listeCh.add(cl);

            }
            return listeCh;
        } catch (Exception e) {
            System.err.println("Donnée introuvable".toUpperCase());
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void save(Client cl) {
    try {
        
        if(cl.getId_client() != 0) {
            PreparedStatement ps  = Db.con.prepareStatement
            ("UPDATE client SET prenomC=?,adC=?,ageC=?,villeC=?,mailC=?,sexeC=?,telC=?,paysC=?,nomC=? WHERE id_client=?");
            ps.setString(1,cl.getPrenomC());
            ps.setString(2,cl.getAdC());
            ps.setInt(3,cl.getAgeC());
            ps.setString(4,cl.getVilleC());
            ps.setString(5, cl.getMailC());
            ps.setString(6, cl.getSexeC());
            ps.setString(7, cl.getTelC());
            ps.setString(8, cl.getPaysC());
            ps.setString(9, cl.getNomC());
            ps.setInt(10, cl.getId_client());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée".toUpperCase());
        }else {
            PreparedStatement ps  = Db.con.prepareStatement
            ("INSERT INTO client (prenomC,adC,ageC,villeC,mailC,sexeC,telC,paysC,nomC) VALUES(?,?,?,?,?,?,?,?,?)");
            ps.setString(1,cl.getPrenomC());
            ps.setString(2,cl.getAdC());
            ps.setInt(3,cl.getAgeC());
            ps.setString(4,cl.getVilleC());
            ps.setString(5, cl.getMailC());
            ps.setString(6, cl.getSexeC());
            ps.setString(7, cl.getTelC());
            ps.setString(8, cl.getPaysC());
            ps.setString(9, cl.getNomC());
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
            ("DELETE FROM client WHERE id_client=?");
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Supprimé".toUpperCase());
        
    } catch (Exception e) {
        System.out.println("non suppriùé".toLowerCase());
        e.printStackTrace();
    }
    }

}
