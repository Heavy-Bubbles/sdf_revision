package sdf.tan.chelsea.Day6Clients;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class LoopClient {

    public static void main(String[] args) throws Exception{

        Socket socket = new Socket("localhost", 12345);

        Console c = System.console();
        String input = "";
        String msgReceived = "";

        // open streams
        OutputStream os = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);

        InputStream is = socket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);

        while (!input.equalsIgnoreCase("close")){
            input = c.readLine("Enter a command: ");

            // send message to server
            dos.writeUTF(input);
            dos.flush();

            // receive cookie from server
            msgReceived = dis.readUTF();
            System.out.println(msgReceived);
       }

       // close streams in reverse order
       dis.close();
       bis.close();
       is.close();
       dos.close();
       bos.close();
       os.close();

       // close socket
       socket.close();
        
    }
    
}
