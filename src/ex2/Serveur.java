package ex2;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Serveur implements Runnable {
    ExecutorService es;
    ServerSocket sockServ;
    Connexion c;

    public Serveur(ExecutorService es) {
        try{
            this.es = es;
            this.sockServ = new ServerSocket(6020);
            System.out.println("START");
        }catch (IOException e){e.printStackTrace();}
    }
    public void run() {
        try {
            while (true) {
                try {
                    Socket sockCli = sockServ.accept();
                    c = new Connexion(es, sockCli);
                    es.execute(c);
                } catch (IOException e) {e.printStackTrace();}
            }
        }
        finally {
            try {
                sockServ.close();
                es.shutdown();
            } catch (IOException e) {e.printStackTrace();}
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(4);
        Serveur serv = new Serveur(es);
        es.execute(serv);
    }
}
