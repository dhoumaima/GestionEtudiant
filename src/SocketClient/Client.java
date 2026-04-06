package SocketClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args) {
        try {
            Socket s =new Socket("127.0.0.1",9003);
            System.out.println("I'm client , I'm connected to the server ....");

            //client read
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));
            ClientRead clientRead =new ClientRead(br);
            clientRead.start();

            //cleint write
            Scanner sc = new Scanner(System.in);
            PrintWriter pw = new PrintWriter(s.getOutputStream());
            ClientWrite clientWrite = new ClientWrite(pw,sc);
            clientWrite.start();



        } catch (IOException e) {
            System.out.println("Erreur client.."+e.getMessage());
        }
    }
}
