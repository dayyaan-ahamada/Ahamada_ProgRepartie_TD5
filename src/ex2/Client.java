package ex2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    static final int port = 6020;
    static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Socket sock;
        try {
            sock = new Socket(args[0], port);
            System.out.println("Socket : " + sock);
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter
                    (new OutputStreamWriter(sock.getOutputStream())), true);
            String message;
            while (true) {
                message = sc.nextLine();
                if (message.equals("stop")) break;
                out.println(message);
            }
            out.println("stop");
            in.close();
            out.close();
            sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
