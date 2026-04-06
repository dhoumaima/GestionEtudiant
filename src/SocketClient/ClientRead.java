package SocketClient;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientRead extends Thread {
    private BufferedReader br;
    private MessageListener listener;

    public ClientRead(BufferedReader br, MessageListener listener) {
        this.br = br;
        this.listener = listener;
    }

    @Override
    public void run() {
        String msg;
        try {
            while ((msg = br.readLine()) != null) {
                listener.onMessageReceived(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}