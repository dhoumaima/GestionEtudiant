package DataBase;

import java.sql.ResultSet;

public interface EtudiantDAO {
    int inertEtudiant(int cin , String nom , String prenom,double moyenne);
    int modifEtudinat(int cin , String nom , String prenom,double moyenne);
    int deletEtudiant(int cin);
    ResultSet selectEtudiant(String requete_selection);
    void afficheResultSet(ResultSet rs);
}
