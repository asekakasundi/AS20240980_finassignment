
package write.and.read;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WriteAndRead {

    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner (System.in);
        
        String cities[] = new String[60];
        
        
        
       try( FileWriter writer = new FileWriter("City.txt")){
           writer.write("Cities\n");
           
           System.out.println("Enter city name. (Enter \"OK\" to stop) \n");
           
           for(int i=0; i<60; i++){
           System.out.println("City "+(i+1)+ " : ");
           String city= sc.nextLine();
          
           
           if(city.equals("OK")){
               System.out.println("Entered data have been saved.");
               break;
           }
          
           else if(!city.isBlank()){
               cities[i]=city;
               writer.write((i+1)+": "+ city+"\n");
           }
           else{
               System.out.println("Invalid city");
               break;
           }
           
           }
       }
       catch(IOException e)//catch if there is a problem while writing
               {
           System.out.println("error: "+e.getMessage());
           
       }
    }
    
}
