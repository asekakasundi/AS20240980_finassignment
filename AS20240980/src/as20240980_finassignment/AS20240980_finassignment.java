
package as20240980_finassignment;

import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;


public class AS20240980_finassignment {
    
    static int totCity = 0;
    static String cityFilePath = "C:\\Users\\DELL\\Desktop\\AS20240980\\city.txt";
    
  static String[] vehicle = {"Van", "Truck", "Lorry"}; // 1- Van 2- Truck 3- Lorry
  static int[][] vehicleData = {{1000, 30, 60, 12},{5000, 40, 50, 60},{10000, 80, 45, 4}};
  
  /* Type  Capacity(kg)  Rate per km(LKR)  Avg Speed(km/h)  Fuel Efficiency(km/l)
     Van     1000             30                60                12
     Truck   5000             40                50                 6
     Lorry   10000            80                45                 4   */
  
  static Scanner sc = new Scanner(System.in);
  
  
    public static void main(String[] args) {
       
        String cities []=readCityFile ();
        totCity = cities.length;
        
        for(int i=0; i<totCity; i++){
            System.out.println(cities[i]);
        }
        
        System.out.println("=============== Logistic Management System ==============");
        System.out.println("1. Manage Cities");
        System.out.println("2. Manage Distance between cities");
        System.out.println("3. Delivery Request");
        System.out.println("4. Performance Report");
        System.out.println("Enter your choice: ");
        int choice= sc.nextInt();
       
        while(true){
            switch(choice){
             
                case 1:
                    System.out.println("========== Manage Cities ==========");
                    System.out.println("1. Add a new City");
                    System.out.println("2. Remove city ");
                    System.out.println("3. Rename a city");
                    System.out.println("Enter your choice: ");
                    int subChoice = sc.nextInt();
                    sc.nextLine();
                    
                    /*while (true){
                        switch (subChoice){
                        case 1:
                            
                            if (totCity == 30){
                                System.out.println("Cannot add a new city. Maximum number of cities reached.");
                            }
                            else{
                                System.out.println("Enter city name: ");
                                String newCity = sc.nextLine().toUpperCase();
                                
                                checkDuplictaeCity(cities,newCity); // Check the uniquness
                                
                                if (newCity.isEmpty()){
                                        System.out.println("Please enter a valid city");
                                    }
                                else{
                                    writetoCityFile(newCity);
                                    break;
                                    }
                               
                            }
                            break;
                           
                        case 2:
                            
                        }
                    }*/
                    
            }
        }
    }
    
    public static String[] readCityFile (){
        
        String [] arr = new String[30];
        
        try(BufferedReader reader = new BufferedReader(new FileReader(cityFilePath))){
            
            for (int i=0; i<30; i++){
                String line;
                while ((line=reader.readLine())!= null){
                    String str = line;
                    arr = str.split(" ");
                    
                }
                        
                      
            }
           
        }
        
        catch(FileNotFoundException e){
            System.out.println("Error! City file not found ");
        }
        catch(IOException e){
            System.out.println("Error"+ e.getMessage());
        }
        
    return arr;  
    }
    
    public static void writetoCityFile(String str){
        try(FileWriter writer = new FileWriter(cityFilePath)){
            writer.write(str+"\n");
            System.out.println("City has been added Successfully!");
            
        }
        catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public static void checkDuplictaeCity(String arr[],String str){
        for (int i=0; i<totCity; i++){
            if (arr[i].equals(str)){
                System.out.println("Error! The city already exists");
                break;
            }
        }
        
    }
    
}
