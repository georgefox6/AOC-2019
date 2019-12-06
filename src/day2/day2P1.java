package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day2P1 {
    public static void main(String[] args) throws IOException {
        //Read in the file
        BufferedReader reader = new BufferedReader(new FileReader("day2/input.txt"));
        String input = reader.readLine();
        //split the file into an array of integers
        String[] strArray = input.split(",");
        int[] inputArray = new int[strArray.length];
        for(int i=0; i < strArray.length; i++){
            inputArray[i] = Integer.parseInt(strArray[i]);
        }
        //iterate over all the inputs
        for(int i=0; i < inputArray.length; i+=4){
            switch(inputArray[i]){
                case 1:
                    inputArray[inputArray[i+3]] = inputArray[inputArray[i+1]] + inputArray[inputArray[i+2]];
                    break;
                case 2:
                    inputArray[inputArray[i+3]] = inputArray[inputArray[i+1]] * inputArray[inputArray[i+2]];
                    break;
                case 99:
                    i = inputArray.length;
                    break;
                default:
                    i = inputArray.length;
                    System.out.println("Case : not 1, 2 or 99");
                    break;
            }
        }
        //output the result
        if(inputArray[0] == 19690720){
            System.out.println(inputArray[0]);
        }
    }
}

