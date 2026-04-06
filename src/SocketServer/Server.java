package SocketServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        System.out.println("Starting Server ....");

        try {
            ServerSocket server = new ServerSocket(9003);
            System.out.println("Server ON ....");

            int nbClient=0;
            while (nbClient<3) {
                Socket s = server.accept();//pour attend le client
                System.out.println("Client accepted....");
                nbClient++;

                HandelClient handelClient = new HandelClient(s);
                handelClient.start();

            }


        } catch (IOException e) {
            System.out.println("Erreur server "+e.getMessage());
        }
        System.out.println("Server process end ....");
    }
}
