package ex1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
    public static void main(String[] args) throws Exception {
        ServerSocket s = new ServerSocket(6020);
        System.out.println("START");
        Socket sock = s.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        PrintWriter out = new PrintWriter(new BufferedWriter
                (new OutputStreamWriter(sock.getOutputStream())), true);
        while(true){
            String message = in.readLine();
            if(message.equals("stop")) break;
            System.out.println("Reçu : " + message);
            out.println(message);
        }
        in.close();
        out.close();
        sock.close();
    }
}
