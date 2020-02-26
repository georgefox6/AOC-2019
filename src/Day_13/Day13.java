package Day_13;

import Intcode.Intcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day13 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\gfox\\Java Projects\\AOC-2019\\src\\Day_13\\input.txt"));
        String input = reader.readLine();
        Intcode game = new Intcode(input);
        do{
            System.out.println(game.outputInstructions);
            game.nextStep();
        } while(!game.finished);


    }
}
