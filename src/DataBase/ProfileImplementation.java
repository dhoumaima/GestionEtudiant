package DataBase;


import Model.Langue;
import Model.Profile;

import java.sql.*;
import java.util.List;

public class ProfileImplementation implements ProfileDAO {
    Connection con=null;
    public ProfileImplementation(){
        con = DataBaseConnection.makeConnection();
    }

    @Override
    public int insertProfile(Profile p) {
        if (con != null) {
            try {
                String sql = "INSERT INTO profile (pseudo, nom, prenom) VALUES (?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, p.getPseudo());
                ps.setString(2, p.getNom());
                ps.setString(3, p.getPrenom());


                int a = ps.executeUpdate();
                if (a > 0) {
                    System.out.println("Profil inséré avec succès !");
                }
                ps.close();
                return a;
            } catch (SQLException e) {
                System.out.println("Erreur insertion profil : " + e.getMessage());
            }
        }
        return 0;
    }

    @Override
    public int updateProfile(Profile p) {

        String sql = "UPDATE profile SET nom = ?, prenom = ? WHERE pseudo = ?";

        if(con != null){
            try{
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, p.getNom());
                ps.setString(2, p.getPrenom());
                ps.setString(3, p.getPseudo());

                int res = ps.executeUpdate();

                if(res > 0){
                    System.out.println("Profil modifié avec succès !");
                }

                return res;

            }catch(SQLException e){
                System.out.println("Erreur modification profil : " + e.getMessage());
            }
        }

        return 0;
    }

    public void updateProfileComplet(Profile p){

        try{
            String deleteLangues = "DELETE FROM langue WHERE profile_id = ?";
            PreparedStatement psDelete = con.prepareStatement(deleteLangues);
            psDelete.setString(1, p.getPseudo());
            psDelete.executeUpdate();

            String insertLangue =
                    "INSERT INTO langue (nom, niveau, profile_id) VALUES (?, ?, ?)";

            PreparedStatement psInsert = con.prepareStatement(insertLangue);

            for(Langue l : p.getLangues()){
                psInsert.setString(1, l.getNomLangue());
                psInsert.setInt(2, l.getNiveau());
                psInsert.setString(3, p.getPseudo());
                psInsert.executeUpdate();
            }

            System.out.println("Mise à jour complète réussie !");
        }catch(SQLException e){
            System.out.println("Erreur modification complet  profil : " + e.getMessage());
        }
    }


    @Override
    public int deleteProfile(String pseudo) {

        String sqlDeleteLangues = "DELETE FROM langue WHERE profile_id = ?";
        String sqlDeleteProfile = "DELETE FROM profile WHERE pseudo = ?";

        if (con != null) {
            try {
                //langues
                PreparedStatement psLangues = con.prepareStatement(sqlDeleteLangues);
                psLangues.setString(1, pseudo);
                psLangues.executeUpdate();


                PreparedStatement psProfile = con.prepareStatement(sqlDeleteProfile);
                psProfile.setString(1, pseudo);
                int res = psProfile.executeUpdate();

                if(res>0){
                    System.out.println("Profil et langues supprimés avec succès !");
                    return res;
                }

            } catch (SQLException e) {
                System.out.println("Erreur suppression profil : " + e.getMessage());
            }
        }
        return 0;
    }

    @Override
    public ResultSet selectProfile(String requete_selection) {
        if (con != null) {
            Statement st = null;
            try {
                st = con.createStatement();
                ResultSet rs = st.executeQuery(requete_selection);
                return rs;
            } catch (SQLException e) {
                System.out.println("erreur de selection " + e.getMessage());
            }
        }
        return null;

    }

    @Override
    public void afficheResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                ResultSetMetaData rsmd = rs.getMetaData();

                int nbcol = rsmd.getColumnCount();

                for (int i = 0; i < nbcol; i++) {
                    System.out.print(rsmd.getColumnName(i + 1) + "\t\t\t");
                }
                System.out.println("\n__________________________\n");
                while (rs.next()) {
                    for (int i = 0; i < nbcol; i++) {
                        System.out.print(rs.getObject(i + 1) + "\t\t\t");
                    }
                    System.out.println("\n__________________________\n");
                }
            } catch (SQLException e) {
                System.out.println("Erreur de selection " + e.getMessage());
            }
        }
    }
}