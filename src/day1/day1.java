package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day1 {
    public static void main(String[] args) throws IOException {
        //Read in the file
        BufferedReader reader = new BufferedReader(new FileReader("day1/input.txt"));
        String input;
        int totalFuel = 0;
        input = reader.readLine();
        while(input != null){
            int mass = Integer.parseInt(input);
            int fuel = mass/3 - 2;
            totalFuel += fuel;
            int additionalFuel = 0;
            while(fuel/3 - 2 > 0){
                fuel = fuel/3 - 2;
                additionalFuel += fuel;
            }
            totalFuel += additionalFuel;
            input = reader.readLine();
        }

        //print out the final result
        System.out.println("Total Fuel: " + totalFuel);
    }
}
