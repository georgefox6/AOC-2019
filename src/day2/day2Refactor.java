package day2;

import Intcode.Intcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day2Refactor {
    public static void main(String[] args) throws IOException {
        //read the file in
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\gfox\\Java Projects\\AOC-2019\\src\\day2\\input.txt"));
        String input = reader.readLine();

        Intcode amp1 = new Intcode(input);
        while(!amp1.finished){
            amp1.nextStep();
        }

        System.out.println(amp1.memory[0]);
    }
}
