package SocketServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HandelClient extends Thread{
    Socket s;
    public  HandelClient(Socket s){
        this.s=s;
    }
    @Override
    public void run() {
        //ouvrir le socket on mode lecture
        BufferedReader br = null;
        try {
            br = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String id = null;
        try {
            id = br.readLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("le message reçu :   " + id);

        ServerRead serverRead=new ServerRead(br,id);
        serverRead.start();

        CustomSocket customS = new CustomSocket(id,s);
        SocketManager.list_of_socket.add(customS);

    }
}
