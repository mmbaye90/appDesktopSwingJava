package entites;

import java.sql.Connection;
import java.sql.DriverManager;

public class Db {
	private static String url = "jdbc:mysql://localhost:3306/merise";
	private static String user = "root";
	private static String pwd = "";
	public static Connection con = null;
	
    public static void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion à la bd réussie !!!".toUpperCase());
        } catch (Exception e) {
            System.out.println("connexion à la Bd echouée".toUpperCase());
            e.printStackTrace();
        }
    }

	
}
