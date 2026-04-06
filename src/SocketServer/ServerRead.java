package SocketServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ServerRead extends Thread{
    BufferedReader br;
    String id;
    public ServerRead(BufferedReader br,String id ){
        this.br=br;
        this.id=id;
    }

    @Override
    public void run() {
        String msg ;
       while (true){
           try {
               msg = br.readLine();
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
           System.out.println(msg);
           String Type=msg.substring(0,4);
           if(Type.compareToIgnoreCase("@all")==0) {
               msg=msg.substring(4);
               SocketManager.DiffuserMessage(msg, id);
           }

           else{
               String idRecive=msg.substring(1,msg.indexOf(":"));
               msg=msg.substring(msg.indexOf(":")+1);
                SocketManager.MonoMessage(msg,id,idRecive);
               }
           }
       }
    }
