package Day_9;

import Intcode.Intcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day9Refactor {
    public static void main(String[] args) throws IOException {
        //read the file in
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\gfox\\Java Projects\\AOC-2019\\src\\Day_9\\input.txt"));
        String input = reader.readLine();

        Intcode boost = new Intcode(input);
        boost.inputInstructions.add(2L);
        while(!boost.finished){
            boost.nextStep();
            if(boost.outputInstructions.size() > 0){
                System.out.println(boost.outputInstructions.getFirst());

            }
        }
        System.out.println(boost.outputInstructions.size());
    }
}
