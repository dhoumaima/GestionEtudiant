package SocketClient;

import java.io.PrintWriter;
import java.util.Scanner;

public class ClientWrite extends Thread{
    PrintWriter pw;
    Scanner sc;
    public ClientWrite(PrintWriter pw,Scanner sc){
        this.pw=pw;
        this.sc=sc;
    }

    @Override
    public void run() {
        while (true){
            pw.println(sc.nextLine());
            pw.flush();
        }
    }
}
