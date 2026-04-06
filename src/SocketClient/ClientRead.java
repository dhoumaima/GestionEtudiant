package SocketClient;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientRead extends Thread{
    BufferedReader br;
    public ClientRead(BufferedReader br){
        this.br=br;
    }

    @Override
    public void run() {
        String msg = null;
        while (true){
            try {
                msg = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(msg);
        }
    }
}
