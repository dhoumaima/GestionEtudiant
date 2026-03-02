package DataBase;

import Model.Profile;
import java.sql.ResultSet;

public interface ProfileDAO {
    int insertProfile(Profile p);
    int updateProfile(Profile p);
    int deleteProfile(String pseudo);
    ResultSet selectProfile(String requete_selection);
    void afficheResultSet(ResultSet rs);
    void updateProfileComplet(Profile p);
}