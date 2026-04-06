package SocketServer;

import java.net.Socket;

public class CustomSocket {
    //un class pour lier l'id de client avec la socket
    String id;
    Socket s;

    public CustomSocket(String id, Socket s) {
        this.id = id;
        this.s = s;
    }

}
