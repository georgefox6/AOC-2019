package Day_5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import Intcode.Intcode;

public class Day5Refactor {
    public static void main(String[] args) throws IOException {
        //read the file in
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\gfox\\Java Projects\\AOC-2019\\src\\Day_5\\input.txt"));
        String input = reader.readLine();

        Intcode airConditioner = new Intcode(input);
        airConditioner.inputInstructions.add(5L);

        while(!airConditioner.finished){

            airConditioner.nextStep();
        }
        System.out.println(airConditioner.outputInstructions);
    }
}
