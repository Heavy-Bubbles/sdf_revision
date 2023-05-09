package sdf.tan.chelsea.Day1;

import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCart {

    public static void main(String[] args) {

        // set up shopping cart
        ArrayList<String> cart = new ArrayList<>();

        // welcome message
        System.out.println("Welcome to your shopping cart!");

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
                        4. 'quit' -> quit program""");

            // add multiple items
            } else if (input.toLowerCase().startsWith("add")){

                // to catch error when user only input add without items
                try{

                    input = input.replaceAll("\\p{P}", " ");
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
                
            // invalid command message
            } else if (!input.equalsIgnoreCase("quit")) {
                System.out.println("Invalid command!");
            }
        }
        System.out.println("Bye Bye!");

    }
    
}
