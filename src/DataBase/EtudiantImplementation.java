package DataBase;

import java.sql.*;

public class EtudiantImplementation implements EtudiantDAO{
    Connection con=null;
    public EtudiantImplementation(){
        con = DataBaseConnection.makeConnection();
    }
    @Override


    public int inertEtudiant(int cin, String nom, String prenom, double moyenne) {
        String requete_insertion="insert into etudiant values(?,?,?,?)";
        if (con !=null){
            try {
                PreparedStatement ps = con.prepareStatement(requete_insertion);
                ps.setInt(1,cin);
                ps.setString(2,nom);
                ps.setString(3,prenom);
                ps.setDouble(4,moyenne);
                int a =ps.executeUpdate();
                if (a>0){
                    System.out.println("Done ,Inserted!");
                   return a;
                }
            } catch (SQLException e) {
                System.out.println(("erreur d'insertion "+e.getMessage()));
            }
        }
        return 0;
    }

    @Override
    public int modifEtudinat(int cin, String nom, String prenom, double moyenne) {
        String requete_upDate="update etudiant set nom='"+nom+"',prenom ='"+prenom+"',moyenne = "+moyenne+" where cin ='"+cin+"'";
        if (con !=null){
            try {
                Statement st = con.createStatement();
                int a = st.executeUpdate(requete_upDate);
                if (a>0){
                    System.out.println("Done , modifier");
                    return a;
                }
            } catch (SQLException e) {
                System.out.println(("erreur de modification "+e.getMessage()));
            }
        }
        return 0;
    }

    @Override
    public int deletEtudiant(int cin) {
        String requete_delete="delete from Etudiant where cin ="+cin+"";
        if (con !=null){
            try {
                Statement st = con.createStatement();
                int a = st.executeUpdate(requete_delete);
                if (a>0){
                    return a;
                }
            } catch (SQLException e) {
                System.out.println(("erreur de suppression "+e.getMessage()));
            }
        }
        return 0;
    }

    @Override
    public ResultSet selectEtudiant(String requete_selection) {
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
                ResultSetMetaData rsmd = rs.getMetaData();//les titre des colones

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