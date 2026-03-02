package Adapter;

import DataBase.DataBaseConnection;
import DataBase.EtudiantImplementation;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class EtudiantTableModel extends AbstractTableModel {

    ResultSetMetaData rsmd =null;
    ArrayList<Object[]> data = new ArrayList<Object[]>();
    EtudiantImplementation implementation;

    public EtudiantTableModel(ResultSet rs, EtudiantImplementation implementation) {
        this.implementation=implementation;
        try {
            rsmd=rs.getMetaData();
            while (rs.next()){
                int nbLigne = rsmd.getColumnCount();
                Object[] ligne = new Object[nbLigne];
                for (int i=0;i<nbLigne;i++){
                    ligne[i]=rs.getObject(i+1);
                }
                data.add(ligne);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        try {
            return  rsmd.getColumnCount();
        } catch (SQLException e) {
            return 0;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       return data.get(rowIndex)[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        try {
            return rsmd.getColumnName(column+1);//pour afficher les nom des colone
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return getColumnName(columnIndex).equalsIgnoreCase("Moyenne");
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        double newMoyenne= Double.parseDouble(aValue+"");
        int cin =(int)data.get(rowIndex)[0];
        String nom=(String)data.get(rowIndex)[1];
        String prenom=(String)data.get(rowIndex)[2];

        int a =implementation.modifEtudinat(cin,nom,prenom,newMoyenne);
        if(a>0)
        data.get(rowIndex)[columnIndex]=aValue;
        else
        {
            System.out.println("Erreur de mis a jour");
        }
    }
    public void ajouterEtudiant(int cin,String nom,String prenom,double moyenne){

           int a= implementation.inertEtudiant(cin,nom,prenom,moyenne);
           if(a>0){
               System.out.println("insertion avec succe");
               Object[] ligne={cin,nom,prenom,moyenne};
               data.add(ligne);
               fireTableDataChanged();
           }

    }

    public void supprimerEtudiant(int cin,int row){

        int a =implementation.deletEtudiant(cin);
        if(a>0){
            System.out.println("Supprission avec succes");
            data.remove(row);
            fireTableDataChanged();
        }
    }
}
