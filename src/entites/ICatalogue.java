package entites;

import java.util.ArrayList;

public interface ICatalogue <T>{
//=================================== FUNCTIONS RETURN ===============
    ArrayList<T> getAll();
    T getById(int id);  
    ArrayList <T> recherche(String mot);
    
//=================================== FUNCTIONS VOID ==================
    void save(T obj);
    void delete(int id);
}
