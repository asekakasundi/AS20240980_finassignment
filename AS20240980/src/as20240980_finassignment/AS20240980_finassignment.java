
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
       
        String[] cities =readCityFile ();
        totCity = cities.length;
   
        
        System.out.println("=============== Logistic Management System ==============");
        System.out.println("1. Manage Cities");
        System.out.println("2. Manage Distance between cities");
        System.out.println("3. Delivery Request");
        System.out.println("4. Performance Report");
        System.out.println("5.Exit");
        System.out.println("Enter your choice: ");
        int choice= sc.nextInt();
       
        while(true){
            switch(choice){
             
                case 1://Manage cities
                    System.out.println("========== Manage Cities ==========");
                    System.out.println("1. Add a new City");
                    System.out.println("2. Remove city ");
                    System.out.println("3. Rename a city");
                    System.out.println("4.Exit");
                    System.out.println("Enter your choice: ");
                    int subChoice = sc.nextInt();
                    sc.nextLine();
                    
                    int k=0;
                    while (k==0){
                        k=+1;
                        switch (subChoice){
                        case 1:// Add a new city
                            
                            if (totCity == 30){
                                System.out.println("Cannot add a new city. Maximum number of cities reached.\n\n");
                                break;
                            }
                              
                            if (totCity<30){
                                
                                System.out.println("Enter city name: ");
                                String newCity = sc.nextLine().toUpperCase();
                                
                                int duplicateval = checkCity(cities,newCity);// Check the uniquness
                                
                                if(duplicateval < 30){
                                    System.out.println("Error! The city already exists \n\n");
                                }
                                else if (newCity.isEmpty()){
                                System.out.println("Please enter a valid city");
                                }
                                else if (newCity.isBlank()){
                                System.out.println("Please enter a valid city");
                                }
                                else{
                                writetoCityFile(newCity);
                                
                                cities = updateCityArray();
                                
                                }
                            }
                            break;
                           
                        case 2:// Delete a city
                            System.out.println("Enter city name: ");
                            String city =sc.nextLine().toUpperCase();
                            
                            cities = deleteCity(city,cities);
                            totCity = cities.length;
                            
                            
                            break;    
                            
                        case 3://rename name a city
                            System.out.println("Enter city name to be replaced: ");
                            String originalCity =sc.nextLine().toUpperCase();
                            
                            System.out.println("Enter new city name: ");
                            String newCity =sc.nextLine().toUpperCase();
                            renameCity(cities, originalCity,newCity );
                            
                            cities = updateCityArray();
                            break;
                            
                        default:
                            System.out.println("Exit for Main Menue successfully!");
                            break;
                        }
                    }
                    
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
            System.out.println("Error! City file not found \n\n");
        }
        catch(IOException e){
            System.out.println("Error"+ e.getMessage()+"\n\n");
        }
        
    return arr;  
    }
     
    
    
    
    
    
    
    
    
    
    
    public static void writetoCityFile(String str){
        try(FileWriter writer = new FileWriter(cityFilePath,true)){
            writer.write(str+" ");
            System.out.println("City has been added Successfully!\n\n");
        }
        catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    
    
    
    
    
    
   
    public static int checkCity(String[] arr,String str){
        int val = 30;
        for (int i=0; i<totCity; i++){
            if (arr[i].equals(str)){
                val = i;
                break;
            }
            
        }
       return val; 
    }
    
    
    
    
    
    
    
    
    public static String[] deleteCity(String str, String[]arr){
        int placeVal = checkCity(arr,str);
        String[] newArr= new String[arr.length-1];
        int j=0;
        
        if (placeVal==30){
                System.out.println("City does not exists!\n\n");
                
            }
        else{
            for (int i=0; i<arr.length; i++){
               if(i!= placeVal){
                   newArr[j]=arr[i];
                   j++;
               }
            } 
            rewritetoCityFile(newArr);
            System.out.println("City Removed Succesfully!\n\n");
        }
        
        return newArr;
    }
    
    
    
    
    
    
    
    
    
    public static void rewritetoCityFile(String[] strArr){
        try(FileWriter writer = new FileWriter(cityFilePath)){
            for (String strArr1 : strArr) {
                writer.write(strArr1 + " "); 
            }
          
        }
        catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    
    
    
    
    
    public static void renameCity(String[] arr, String str1,String str2){
        int i = checkCity(arr, str1);
        if (i==30){
                System.out.println("City does not exists!\n\n");
            }
        else{
            arr[i]=str2;
            System.out.println("City renamed successfully!\n\n");
            rewritetoCityFile(arr);
        }
        
    }
    
    
    
    
    
    
    public static String[] updateCityArray(){
        String[] arr = readCityFile ();
        totCity = arr.length;
        return arr;
    }
    
}
