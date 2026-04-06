package SocketClient;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket s;
    private PrintWriter pw;
    private BufferedReader br;
    private ClientRead clientRead;
    private ClientWrite clientWrite;

    public Client(String host, int port, String id) throws IOException {
        s = new Socket(host, port);
        System.out.println("Client connecté au serveur ...");

        br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        pw = new PrintWriter(s.getOutputStream(), true);

        // envoyer ID au serveur dès la connexion
        pw.println(id);

        // Threads lecture/écriture
        clientRead = new ClientRead(br);
        clientWrite = new ClientWrite(pw, new Scanner(System.in));
    }

    public void startReading() {
        clientRead.start();
    }

    public void startWriting() {
        clientWrite.start();
    }

    public void sendMessage(String msg) {
        pw.println(msg);
        pw.flush();
    }
}