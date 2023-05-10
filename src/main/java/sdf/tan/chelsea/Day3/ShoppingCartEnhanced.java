package sdf.tan.chelsea.Day3;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCartEnhanced {

    public String directory;

    public static void main(String[] args) throws IOException{

        // specify directory
        if (args.length > 0){
            String folderName = args[0];
            ShoppingCartDB namedDB = new ShoppingCartDB(folderName);
            namedDB.getFile().mkdir();
            ArrayList<String> cart = new ArrayList<>();

        // welcome message
        System.out.println("Welcome to your shopping cart!");

        // String to keep track of who is logged in and boolean to check if logged in
        String loggedInUser = "";
        Boolean isLogin = false;

        // set up input console
        Console c = System.console();
        String input = "";

        while (!input.equalsIgnoreCase("quit")){

            input = c.readLine("Type your command or type 'help' to show list of commands: ");

            // display commands
            if (input.equalsIgnoreCase("help")){
                System.out.println("""
                        List of commands:
                        1. 'add <item>' -> add items to cart (separated by commas)
                        2. 'list' -> list cart items
                        3. 'delete <item index>' -> delete item
                        4. 'login <name>' -> login to account
                        5. 'users' -> list registered users
                        6. 'save' -> save cart items
                        7. 'quit' -> quit program""");

            // add multiple items
            } else if (input.toLowerCase().startsWith("add")){

                // to catch error when user only input add without items
                try{

                    input = input.replaceAll(",", " ");
                    Scanner scan = new Scanner(input.substring(4));

                    while (scan.hasNext()){
                        String content = scan.next().toLowerCase();
    
                        // check for duplicates
                        if (cart.contains(content)){
                            System.out.printf("You have %s in your cart!%n", content);
                        } else {
                            cart.add(content);
                            System.out.println(content + " added to cart");
                        }
    
                    }

                } catch (StringIndexOutOfBoundsException e){

                    System.out.println("Invalid input!");

                }
               
            // print cart
            } else if (input.equalsIgnoreCase("list")){

                // check if cart is empty
                if (cart.isEmpty()){
                    System.out.println("Your cart is empty!");
                } else {
                    int i = 1;
                    for (String items : cart){
                        System.out.printf("%d. %s%n", i, items);
                        i++;
                    }
                }

            // delete items from cart
            } else if (input.toLowerCase().startsWith("delete")){

                // check if cart is empty
                if (cart.isEmpty()){
                    System.out.println("Your cart is empty!");
                } else {

                    // catch unexpected input
                    try{
                        // -1 because ArrayList index starts with 0
                        int itemIndex = Integer.parseInt(input.substring(7)) - 1;
                        if (itemIndex < cart.size()){
                            String item = cart.get(itemIndex);
                            cart.remove(itemIndex);
                            System.out.println(item + " removed from cart");
                        } else {
                            System.out.println("Incorrect item index!");
                        } 
                    } catch (StringIndexOutOfBoundsException e){
                        System.out.println("Incorrect item index!");
                    }
                }
                
            // login to account
            } else if(input.toLowerCase().startsWith("login")){
               
                // catch null input
                try{

                    // acquire username
                    Scanner scan = new Scanner(input.substring(6));
                    String loginUser = "";
                    while(scan.hasNext()){
                        loginUser = scan.next();
                    }
                        
                    // check if user file exists, if not create file. If file exists read file.
                    File loginFile = new File(folderName + File.separator + loginUser);
                    if (loginFile.exists()){
                        // set loggedin user and clear cart memory
                        loggedInUser = loginUser;
                        cart.clear();
                        // load cart into an ArrayList
                        ArrayList<String> savedItems = namedDB.loadCart(loginFile);
                        cart.addAll(savedItems);
                        isLogin = true;
                        System.out.println(loginUser + "'s saved cart successully loaded");

                    } else {
                        loggedInUser = loginUser;
                        cart.clear();
                        loginFile.createNewFile();
                        isLogin = true;
                        System.out.println(loginUser + ", your cart is empty");
                    }

                } catch(StringIndexOutOfBoundsException e){
                    System.out.println("Invalid input!");
                }
                
            // save cart
            } else if (input.equalsIgnoreCase("save")){

                if (isLogin == false){
                    System.out.println("Please login first!");
                } else if (cart.isEmpty()){
                    System.out.println("Your cart is empty!");
                } else {
                    namedDB.saveCart(cart, folderName, loggedInUser);
                    System.out.println("Cart saved successfully");
                }

            // print registered users
            } else if (input.equalsIgnoreCase("users")){
                namedDB.printUsers();

            // invalid command message
            } else if (!input.equalsIgnoreCase("quit")){
                System.out.println("Invalid command!");
            } 
        }
        System.out.println("Bye Bye!");
            
        } else {
            // set up default directory
            ShoppingCartDB defaultDB = new ShoppingCartDB("db");
            defaultDB.getFile().mkdir();
        }

    }
    
}
