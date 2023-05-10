package sdf.tan.chelsea.Day4Server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
    
        String fileName = args[0];
        String port = args [1];
        
        File cookieFile = new File(fileName);

        // test cookie class
        Cookie cookie = new Cookie();
        cookie.readCookieFile(cookieFile);
        System.out.println(cookie.getCookies());
        System.out.println(cookie.getRandomCookie());
        System.out.println(cookie.getRandomCookie());

        // establish server connection
        ServerSocket ss = new ServerSocket(Integer.parseInt(port));
        Socket socket = ss.accept();
        System.out.println("Connection successfully established");

        // store data recieved from client
        String msgReceived = "";

        // open input stream and output stream to read and write data
        InputStream is = socket.getInputStream(); 
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);
        OutputStream os = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);

        // read message from client
        while (!msgReceived.equalsIgnoreCase("close")){
            msgReceived = dis.readUTF();
            System.out.println("Message recieved from client");
            
            if (msgReceived.equals("get-cookie")){
                
                // get random cookie
                String randomCookie = "cookie-text: " + cookie.getRandomCookie();

                // send random cookie
                dos.writeUTF(randomCookie);
                dos.flush();
            } else {
                dos.writeUTF("");
                dos.flush();
            }

        }

         // close all streams in reverse order
         dos.close();
         bos.close();
         os.close();
         dis.close();
         bis.close();
         is.close();

         // close sockets
         ss.close();
         socket.close();
    }
    

}
