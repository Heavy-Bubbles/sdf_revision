package sdf.tan.chelsea.Day4Server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Cookie {

    private ArrayList<String> cookies = new ArrayList<>();

    public void readCookieFile(File cookieFile) throws IOException, FileNotFoundException{

        FileReader fr = new FileReader(cookieFile);
        BufferedReader br = new BufferedReader(fr);

        String cookie = "";
        while ((cookie = br.readLine()) != null){
            cookies.add(cookie);
        }

        br.close();
        fr.close();

    }

    public String getRandomCookie(){

        Random random = new Random();
        return cookies.get(random.nextInt(cookies.size()));
    }

    public ArrayList<String> getCookies() {
        return cookies;
    }

    // not necessary as it is implicit if no constructor 
    public Cookie() {
    }

    
    
}
