package DataBase;

import java.sql.*;

import static DataBase.DataBaseConfig.*;


public class Test {
    public static void main(String[] args) {
        System.out.println("Start...");
        Connection con =DataBaseConnection.makeConnection();
        EtudiantImplementation etudiant = new EtudiantImplementation();
        etudiant.modifEtudinat(3,"oumaima","dh",16);
        etudiant.inertEtudiant(1,"fh","^poiuj",15);
       etudiant.deletEtudiant(1);
       String requete_selection="select * from Etudiant";
       ResultSet rs=etudiant.selectEtudiant(requete_selection);
       etudiant.afficheResultSet(rs);





    }
}
