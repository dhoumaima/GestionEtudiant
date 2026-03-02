package DataBase;

public class DataBaseConfig {
    public static final String NOM_DRIVER="com.mysql.cj.jdbc.Driver";
    public static final String IPServeur="localhost";
    public static final String PORT="3306" ;//par defaut
    public static final String DataBaseName="tpjavadb" ;//par defaut
    public static final String URL_DB="jdbc:mysql://"+IPServeur+":"+PORT+"/"+DataBaseName;
    public static final String USERNAME="root";
    public static final String PASSWORD="";//my sql n'a pas une mot de passe


}
