package SocketServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class SocketManager {
    public static ArrayList<CustomSocket> list_of_socket = new ArrayList<>();

    //diffuser les message vers tous les client
    public static void DiffuserMessage(String msg,String id){
        for (int i = 0; i < list_of_socket.size(); i++) {
            if(!list_of_socket.get(i).id.equals(id)) {
                Socket s = list_of_socket.get(i).s;
                PrintWriter pw = null;
                try {
                    pw = new PrintWriter(s.getOutputStream());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                pw.println(id + " send: " + msg + "at:" + new Date().toLocaleString());
                pw.flush();
            }
        }
    }

    public static void MonoMessage(String msg, String idsend, String idRecive){
        for (int i = 0; i < list_of_socket.size(); i++) {
            if(idRecive.equals(list_of_socket.get(i).id)) {

                Socket s = list_of_socket.get(i).s;
                try {
                    PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
                    pw.println("(privé) " + idsend + " : " + msg + " at: " + new Date());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
