package sdf.tan.chelsea.Day3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShoppingCartDB {

    private String directory;
    private File file;
    


    public ShoppingCartDB(String directory) {
        this.directory = directory;
        this.file = new File(directory);
    }
    
    // read cart items as ArrayList
    public ArrayList<String> loadCart(File loginFile) throws IOException{

        //initiate readers
        FileReader fr = new FileReader(loginFile);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        
        // adding items to ArrayList
        ArrayList<String> items = new ArrayList<>();
        while ((line = br.readLine()) != null){
                items.add(line);

        }
        br.close();
        fr.close();
        return items;
    }

    public void saveCart(ArrayList<String> cart, String folderName, String user) throws IOException{

        FileWriter fr = new FileWriter(folderName + File.separator + user);
        BufferedWriter br = new BufferedWriter(fr);
        for (String item : cart){
            br.write(item);
            br.newLine();
        }
        br.flush();
        br.close();
        fr.close();
    }

    public void printUsers(){

        // list files in the directory
        String [] listing = file.list();
        // convert to list so I can use the isEmpty method to check if empty
        List<String> users = Arrays.asList(listing);
        
        if (users.isEmpty()){
            System.out.println("There are no users registerd!");
        } else {
            System.out.println("The following users are registered:");
            int i = 1;
            for (String user : users){
                System.out.println(i + ". " + user);
                i++;
            }
        }

    }

    // getter and setter for folder name
    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
    
}
