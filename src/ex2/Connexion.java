package ex2;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class Connexion implements Runnable {
    ExecutorService es;
    Socket sock;
    public Connexion(ExecutorService es, Socket sock) {
        this.es = es;
        this.sock = sock;
    }

    @Override
    public void run() {
        try{
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
