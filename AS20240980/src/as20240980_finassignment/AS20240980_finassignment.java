
package as20240980_finassignment;

import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;



public class AS20240980_finassignment {
    
    static int totCity = 0;
    static String cityFilePath = "city.txt";
    static String interCityPath = "intercity distance.txt";
    
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
        
        double[][] intercityDistance = readInterCityFile();
        
   
        while(true){
            
        System.out.println("=============== Logistic Management System ==============");// main menue
        System.out.println("1. Manage Cities");
        System.out.println("2. Manage Intercity Distance");
        System.out.println("3. Delivery Request");
        System.out.println("4. Performance Report");
        System.out.println("5.Save data and Exit");
        System.out.println("Enter your choice: ");
        int choice= sc.nextInt();
       
        
            switch(choice){
                
            
                case 1://Manage cities
                    
                    System.out.println("========== Manage Cities ==========");
                    System.out.println("1. Add a new City");
                    System.out.println("2. Remove city ");
                    System.out.println("3. Rename a city");
                    System.out.println("4.Exit");
                    System.out.println("Enter your choice: ");
                    int subChoice1 = sc.nextInt();
                    sc.nextLine();
                    
                        switch (subChoice1){
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
                                
                                cities = addItemToArray(cities, newCity);
                                totCity = cities.length;
                                intercityDistance = addRowAndColumn(intercityDistance);
                                
                                System.out.println("City has been added successfully!");
                                }                                
                            }
                        break;
                           
                        case 2:// Delete a city
                            System.out.println("Enter city name: ");
                            String city =sc.nextLine().toUpperCase();
                            
                            int val = checkCity(cities, city);
                            
                            cities = deleteCity(city,cities);
                            totCity = cities.length;
                            
                            intercityDistance = removeRowAndColumn(intercityDistance, val);
                              
                        break;    
                            
                        case 3://rename name a city
                            System.out.println("Enter city name to be replaced: ");
                            String originalCity =sc.nextLine().toUpperCase();
                            
                            System.out.println("Enter new city name: ");
                            String newCity =sc.nextLine().toUpperCase();
                            renameCity(cities, originalCity,newCity );
                        break;
                            
                        case 4:
                        System.out.println("Exit for Main Menue successfully!");
                        break;
                        
                        default:
                            System.out.println("Please enter a valid input.");
                            break;
                        } 
                    
                break;       
                    
                    
                case 2:{// Manage distance
                    System.out.println("========== Manage Intercity Distance ==========");
                    System.out.println("1. Input distance between two cities ");
                    System.out.println("2. Edit distance between two cities ");
                    System.out.println("3. Distance table");
                    System.out.println("4. Exit");
                    System.out.println("Enter your choice: ");
                    int subChoice2 = sc.nextInt();
                    
                    switch (subChoice2){
                        case 1:
                            
                            System.out.println("Enter the city: ");
                            sc.nextLine();
                            String starCity = sc.nextLine().toUpperCase();
                            int cityNum = checkCity(cities,starCity);
                            
                            for (int i=0; i<totCity; i++){ // elimate the distance from a city to itself
                               if (i == cityNum){
                                   continue;
                                }
                               else{  
                                    System.out.println("Enter distance from "+cities[cityNum]+" to "+cities[i]+" (km):");
                                    double cityDistance = sc.nextDouble();
                                    
                                    intercityDistance[cityNum][i]= intercityDistance[i][cityNum]= cityDistance ;
                                
                               } 
                            System.out.println("Distance input successfull!"); 
                                
                            }
                            
                        break;
                        
                    
                        case 2:
                            
                            System.out.println("Enter the starting city: ");
                            sc.nextLine();
                            String startingCity = sc.nextLine().toUpperCase();
                            
                            System.out.println("Enter the destination city: ");
                            String destinationCity = sc.nextLine().toUpperCase();
                            
                            
                            if (startingCity.equals(destinationCity)){// check whether the input is same
                                System.out.println("Please enter distant cities!");
                            }
                            else{
                                
                                int startingCityVal = checkCity(cities, startingCity);
                                int destinationCityVal = checkCity(cities, destinationCity);

                                if (startingCityVal == 30 || destinationCityVal == 30) {// check if the entered cities exists
                                    System.out.println("Error: One or both cities not found! Please enter valid city names.\n");
                                    } 
                                else {
                                    System.out.println("Current distance: "+intercityDistance[startingCityVal][destinationCityVal]+" km");
                                    
                                    System.out.println("Enter the distance between two cities: ");
                                    double cityDistance = sc.nextDouble();
                                    
                                    intercityDistance = editIntercityDistanceArr(startingCityVal, destinationCityVal, cityDistance, intercityDistance);
                                    }
                            break;
                        
                        }
                    
                        break;
                        
                        case 3:
                            System.out.println("========= Distance Table ==========");
                            
                            System.out.printf("%15s", ""); 
                            // Empty top-left corner
                                for (int i = 0; i < totCity; i++) {
                                    System.out.printf("%12s", cities[i]); // City names as headers
                                }
                                System.out.println(); // Move to next line

                                 // Print each row
                                for (int i = 0; i < totCity; i++) {
                                    System.out.printf("%15s", cities[i]); // Row header (city name)
                                    for (int j = 0; j < totCity; j++) {
                                        System.out.printf("%12.1f", intercityDistance[i][j]); // One decimal place
                                    }
                                    System.out.println(); // Move to next row
                               }

                            System.out.println();
    
                            break;
                        }
                break;
                }
                  
                case 3://Delivery Request
                    
                    break;
                    
                case 4://Performance Report
                    
                    break;
                    
                case 5:// Exit and save data  
                    writetoCityFile(cities);
                    writetointerCityFile(intercityDistance);
                    System.exit(0);
                    break;
                    
                default:
                    
                    break;
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
     
    
    
    
    
    
    
    
    
    
    
    public static void writetoCityFile(String [] str){
        try(FileWriter writer = new FileWriter(cityFilePath)){
            for(int i=0; i<totCity; i++){
                writer.write(str[i]+" ");
                System.out.println("Dava have been saved Successfully.!\n\n");
            }
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
            
            System.out.println("City Removed Succesfully!\n\n");
        }
        
        return newArr;
    }
   
    
    
    
    
    
    
    public static void renameCity(String[] arr, String str1,String str2){
        int i = checkCity(arr, str1);
        int j = checkCity(arr, str2);
        if (i==30){
                System.out.println("City does not exists!\n\n");
            }
        else{
            if(j==30){
                arr[i]=str2;
                System.out.println("City renamed successfully !\n\n");
            }
            else{
                System.out.println("City already exists!\n\n");
            }
        }
        
    }
    
    
    
    
    
    
    public static String[] addItemToArray(String[] oldArray, String str) {
    String[] newArray = new String[oldArray.length + 1]; // +1 for the new item

    // Copy old elements
    for (int i = 0; i < oldArray.length; i++) {
        newArray[i] = oldArray[i];
    }

    // Add the new one at the end
    newArray[oldArray.length] = str;

    return newArray;
}
    
    
     
        


    
    
    
    public static double[][] readInterCityFile() {
    double[][] distances = new double[totCity][totCity];

    try (BufferedReader reader = new BufferedReader(new FileReader(interCityPath))) {
        String line;
        int row = 0;

        while ((line = reader.readLine()) != null && row < totCity) {
            line = line.trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+");

            for (int col = 0; col < parts.length && col < totCity; col++) {
                try {
                    distances[row][col] = Double.parseDouble(parts[col]);
                } catch (NumberFormatException e) {
                    distances[row][col] = 0.0; // fallback
                }
            }
            row++;
        }

        System.out.println("Intercity distances loaded successfully!");

    } catch (FileNotFoundException e) {
        System.out.println("ℹ️ Distance file not found. Starting with an empty table.");
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    }

    return distances;
}
    
    

    
    
    
    
    
    
    
    public static void writetointerCityFile(double[][] arr) {
    if (arr == null || arr.length == 0) {
        System.out.println("️No distance data to save.");
        return;
    }

    try (FileWriter writer = new FileWriter(interCityPath, false)) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                writer.write(arr[i][j] + " ");
            }
            writer.write("\n");
        }
        System.out.println("Intercity distances saved successfully!");
    } catch (IOException e) {
        System.out.println("Error writing file: " + e.getMessage());
    }
}


    
    
    
    
    
    
    public static double[][] addRowAndColumn(double[][] original) {
    int oldSize = original.length;
    double[][] newArray = new double[oldSize + 1][oldSize + 1];
    for (int i = 0; i < oldSize; i++) {
        for (int j = 0; j < oldSize; j++) {
            newArray[i][j] = original[i][j];
        }
    }
    // Leave last row and column initialized as 0.0
    return newArray;
}
    
    
    
    
    
    
    
    
    
    
    public static double[][] editIntercityDistanceArr(int cityVal1, int cityVal2 , double distance, double[][] arr){
        
        arr[cityVal1][cityVal2]= arr[cityVal2][cityVal1]= distance;
        
        System.out.println("City has been added successfully!");
        
        return arr;
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    public static double[][] removeRowAndColumn(double[][] arr, int index) {
    

    double[][] newArr = new double[totCity][totCity];
    int newRow = 0;

    for (int i = 0; i < totCity+1; i++) {
        if (i == index) continue; // skip the removed row
        int newCol = 0;
        for (int j = 0; j < totCity+1; j++) {
            if (j == index) continue; // skip the removed column
            newArr[newRow][newCol] = arr[i][j];
            newCol++;
        }
        newRow++;
    }

    return newArr;
}
}
