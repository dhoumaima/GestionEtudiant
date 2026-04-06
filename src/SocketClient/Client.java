package SocketClient;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket s;
    private PrintWriter pw;
    private BufferedReader br;
    private ClientRead clientRead;

    public Client(String host, int port, String id) throws IOException {
        s = new Socket(host, port);
        pw = new PrintWriter(s.getOutputStream(), true);
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        pw.println(id);
    }

    public void sendMessage(String msg) {
        pw.println(msg);
        pw.flush();
    }

    public void startReading(MessageListener listener) {
        clientRead = new ClientRead(br, listener);
        clientRead.start();
    }
}