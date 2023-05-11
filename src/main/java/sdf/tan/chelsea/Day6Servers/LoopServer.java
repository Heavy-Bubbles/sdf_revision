package sdf.tan.chelsea.Day6Servers;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoopServer {

    public static void main(String[] args) throws Exception{

        ExecutorService thrPool = Executors.newFixedThreadPool(2);

        ServerSocket server = new ServerSocket(12345);

        while (true){

            System.out.println("Waiting for new client connection...");

        Socket sock = server.accept();

        System.out.printf("Got a new connection: %s\n\n", sock);

        ClientHandler handler = new ClientHandler(sock);
        System.out.println("Dispatching client to thread pool");
        thrPool.submit(handler);

        }
        
        }
      }
      
      
    
    

