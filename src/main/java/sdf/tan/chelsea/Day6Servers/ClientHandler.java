package sdf.tan.chelsea.Day6Servers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable{

    private final Socket sock;

    public ClientHandler(Socket s){
        sock = s;
    }

    @Override
    public void run() {
        try{
            InputStream is = sock.getInputStream(); 
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);
            OutputStream os = sock.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);

        
            String msgReceived = "";

            // read message from client
        while (!msgReceived.equalsIgnoreCase("close")){
            msgReceived = dis.readUTF();
            System.out.println("Message recieved from client");
            
            if (msgReceived.equals("get-cookie")){
                
                // get random cookie
                File cookieFile = new File("cookie.txt");
                Cookie cookie = new Cookie();
                cookie.readCookieFile(cookieFile);
                String randomCookie = "cookie-text: " + cookie.getRandomCookie();

                // send random cookie
                dos.writeUTF(randomCookie);
                dos.flush();
            } else {
                dos.writeUTF("");
                dos.flush();
            }
        }
        } catch (Exception ex){
            System.err.printf("[from client]: %s\n", ex.getMessage());
            ex.printStackTrace();
        } finally {
            System.out.println("In finally block, closing socket");
            try {sock.close();} catch (Exception e) {}
        }
        
    }

    
    
}
