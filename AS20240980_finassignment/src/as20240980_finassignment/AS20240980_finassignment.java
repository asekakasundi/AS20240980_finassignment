
package as20240980_finassignment;

import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;



public class AS20240980_finassignment {

  static String[] vehicle = {"Van", "Truck", "Lorry"}; // 1- Van 2- Truck 3- Lorry
  static int[][] vehicleData = {{1000, 30, 60, 12},{5000, 40, 50, 60},{10000, 80, 45, 4}};
  
  /* Type  Capacity(kg)  Rate per km(LKR)  Avg Speed(km/h)  Fuel Efficiency(km/l)
     Van     1000             30                60                12
     Truck   5000             40                50                 6
     Lorry   10000            80                45                 4   */
  
  static Scanner sc = new Scanner(System.in);
  
    public static void main(String[] args) {
        System.out.println("=============== Logistic Management System ==============");
        System.out.println("1. Manage Cities");
        System.out.println("2. Manage Distance between cities");
        System.out.println("3. Delivery Request");
        System.out.println("4. Performance Report");
        System.out.println("Enter your choice: ");
        int choice= sc.nextInt();
       
        
    }
    
}
