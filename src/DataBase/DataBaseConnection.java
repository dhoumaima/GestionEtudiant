package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static DataBase.DataBaseConfig.*;

public class DataBaseConnection {
    public static Connection makeConnection(){
        try {
            Class.forName(DataBaseConfig.NOM_DRIVER);
            System.out.println("Driver OK");
        } catch (ClassNotFoundException e) {
            System.out.println("erreru driver"+e.getMessage());
        }

        //connection a la base on
        //on doit le declarer en haut de try catch
        Connection con = null;

        try {
            con = DriverManager.getConnection(URL_DB,USERNAME,PASSWORD);
            System.out.println("Connected...");
        } catch (SQLException e) {
            System.out.println("errerur de connexion "+e.getMessage());
        }
        return con;
    }
}
