package IHM;

import Model.Profile;

import java.util.ArrayList;

public class DataProfil {
    //tableau
   public static ArrayList<Profile> data = new ArrayList<>();

   public static void AddProfile(Profile p)
   {

       data.add(p);
       System.out.println(data.toArray().length);

   }



}
