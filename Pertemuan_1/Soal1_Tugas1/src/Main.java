import java.util.Scanner; // Get user input
import java.math.BigInteger; // Class that can store more value than long
import java.util.ArrayList; // Provide resizeable array

public class Main {
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        // Using ArrayList because we don't know how much data will the user store
        ArrayList<BigInteger> numbers = new ArrayList<>();

        System.out.print("Amount of data you want to store: ");
        // Checking if the input is a number
        if (!scanner.hasNextInt()) {
            System.out.println("Input must be an integer!");
            return;
        }
        int t = scanner.nextInt();
        scanner.nextLine(); //Important! Cleaning the buffer

        for(int i = 0; i < t; i++){
            System.out.print("Enter Number: ");
            String numberstr = scanner.nextLine(); // getting user input

            try {
                numbers.add(new BigInteger(numberstr)); // converting to BigInteger
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input. Only numbers are allowed!"); // error message if input contains value that are not number
                continue;
            }
        }

        System.out.println("\nResults:");
        for (BigInteger num : numbers) {
            System.out.println(num + " can be fitted in:");

            boolean fitted = false;

            if (num.compareTo(BigInteger.valueOf(Byte.MIN_VALUE)) >= 0 && num.compareTo(BigInteger.valueOf(Byte.MAX_VALUE)) <= 0) {
                System.out.println("*   byte");
                fitted = true;
            }
            if (num.compareTo(BigInteger.valueOf(Short.MIN_VALUE)) >= 0 && num.compareTo(BigInteger.valueOf(Short.MAX_VALUE)) <= 0) {
                System.out.println("*   short");
                fitted = true;
            }
            if (num.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) >= 0 && num.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) <= 0) {
                System.out.println("*   int");
                fitted = true;
            }
            if (num.compareTo(BigInteger.valueOf(Long.MIN_VALUE)) >= 0 && num.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) <= 0) {
                System.out.println("*   long");
                fitted = true;
            }

            if (!fitted) {
                System.out.println("*   datatype");
            }
            System.out.println(); // extra space for other results
        }
        scanner.close(); // prevent leak
    }
}