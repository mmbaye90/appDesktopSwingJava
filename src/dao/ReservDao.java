package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entites.Db;
import entites.ICatalogue;
import entites.Reservation;


//Meme commentaire que dans la classe Chambre
public class ReservDao implements ICatalogue<Reservation>{

    @Override
    public ArrayList<Reservation> getAll() {
        ArrayList <Reservation> listeP = new ArrayList<>();
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * FROM reservation");

            ResultSet resp = ps.executeQuery();

            while (resp.next()) {
                Reservation r = new Reservation();
                r.setId_reserv(resp.getInt("id_reserv"));
                r.setDateDebRes(resp.getString("dateDebRes"));
                r.setDateFinR(resp.getString("dateFinR"));
                r.setNbPerso(resp.getInt("nbPerso"));
                r.setId_ch(resp.getInt("id_ch"));
                r.setId_client(resp.getInt("id_client"));
                listeP.add(r);
            }
            return listeP;
        } catch (Exception e) {
            System.err.println("Liste des reservations introuvable".toUpperCase());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Reservation getById(int id) {
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * FROM reservation WHERE id_reserv = ?");
            ps.setInt(1, id);
            ResultSet resp = ps.executeQuery();
            if (resp.next()) {
                Reservation r = new Reservation();
                r.setId_reserv(resp.getInt("id_reserv"));
                r.setDateDebRes(resp.getString("dateDebRes"));
                r.setDateFinR(resp.getString("dateFinR"));
                r.setNbPerso(resp.getInt("nbPerso"));
                r.setNbPerso(resp.getInt("id_ch"));
                r.setNbPerso(resp.getInt("id_client"));
                return r;                  
            }else return null;
        } catch (Exception e) {
            System.err.println("Objet Reservation introuvable");
            e.printStackTrace();
            return null;
        }  
    }

    @Override
    public ArrayList<Reservation> recherche(String w) {
        ArrayList <Reservation> listeP = new ArrayList<>();
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * FROM reservation WHERE  dateDebRes LIKE ? OR dateFinR LIKE ?");
            ps.setString(1, "%"+w+"%");
            ps.setString(2, "%"+w+"%");


            ResultSet resp = ps.executeQuery();

            while (resp.next()) {
                Reservation r = new Reservation();
                r.setId_reserv(resp.getInt("id_reserv"));
                r.setDateDebRes(resp.getString("dateDebRes"));
                r.setDateFinR(resp.getString("dateFinR"));
                r.setNbPerso(resp.getInt("nbPerso"));
                r.setNbPerso(resp.getInt("id_ch"));
                r.setNbPerso(resp.getInt("id_client"));

                listeP.add(r);

            }
            return listeP;
        } catch (Exception e) {
            System.err.println("Donnée introuvable".toUpperCase());
            e.printStackTrace();
            return null;
        }    }

    @Override
    public void save(Reservation res ) {
    try {
        
        if(res.getId_reserv() != 0) {
            PreparedStatement ps  = Db.con.prepareStatement
            ("UPDATE reservation SET dateDebRes=?,dateFinR=?,nbPerso=?,id_ch=?,id_client=? WHERE id_reserv=?");
            ps.setString(1,res.getDateDebRes());
            ps.setString(2,res.getDateFinR());
            ps.setInt(3,res.getNbPerso());
            ps.setInt(4,res.getId_ch());
            ps.setInt(5,res.getId_client());
            ps.setInt(6,res.getId_reserv());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée".toUpperCase());
        }else {
            PreparedStatement ps  = Db.con.prepareStatement
            ("INSERT INTO reservation (dateDebRes,dateFinR,nbPerso,id_ch,id_client) VALUES(?,?,?,?,?)");
            ps.setString(1,res.getDateDebRes());
            ps.setString(2,res.getDateFinR());
            ps.setInt(3,res.getNbPerso());
            ps.setInt(4,res.getId_ch());
            ps.setInt(5,res.getId_client());

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
            ("DELETE FROM reservation WHERE id_reserv=?");
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Supprimé".toUpperCase());
        
    } catch (Exception e) {
        System.out.println("non suppriùé".toLowerCase());
        e.printStackTrace();
    }
    }


    //ces deux methode qui me permet de récuperer l'ID de la clef étrangere a partir de la
    //table Reservation enfin de pouvoir pointer vers les propriétés de ces tables
    // ===========================================================
    public int getIdChambreByReser(int id) {
        try {
        
                PreparedStatement ps  = Db.con. prepareStatement
                ("SELECT id_ch as id_ch FROM reservation WHERE id_reserv=?");
                ps.setInt(1,id);
                
                ResultSet res = ps.executeQuery();
                res.next();
    
                int idCh = res.getInt( "id_ch" );
                return idCh;
            
        } catch (Exception e) {
            System.err.println("non trouvé !!!");
            e.printStackTrace();
            return 0;
        }
    }

    public int getIdClientreByReser(int id) {
        try {
        
                PreparedStatement ps  = Db.con. prepareStatement
                ("SELECT id_client as id_client FROM reservation WHERE id_reserv=?");
                ps.setInt(1,id);
                
                ResultSet res = ps.executeQuery();
                res.next();
    
                int idCh = res.getInt( "id_client" );
                return idCh;
            
        } catch (Exception e) {
            System.err.println("non trouvé !!!");
            e.printStackTrace();
            return 0;
        }
    }
// ===========================================================
}
