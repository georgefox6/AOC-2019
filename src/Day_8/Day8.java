package Day_8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day8 {

    private static int countX(int value, ArrayList<Integer> layer){
        int counter = 0;
        for(int num : layer){
            if(num == value){
                counter++;
            }
        }
        return counter;
    }

    public static void main(String[] args) throws IOException {
        final int PIXEL_WIDTH = 25;
        final int PIXEL_HEIGHT = 6;
        final int NUM_IN_LAYER = PIXEL_WIDTH * PIXEL_HEIGHT;

        //read the file in
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\gfox\\Java Projects\\AOC-2019\\src\\Day_8\\input.txt"));
        String input = reader.readLine();

        //Store all of the layers in an ArrayList
        ArrayList<ArrayList<Integer>> layers = new ArrayList<>();

        for(int y=0; y < input.length()/NUM_IN_LAYER; y++){
            ArrayList<Integer> layer = new ArrayList<>();
            for(int x=0; x < NUM_IN_LAYER; x++){
                layer.add((int)input.charAt((y * NUM_IN_LAYER) + x) - 48);
            }
            layers.add(layer);
        }

        //Count number of 0's in each layer
        ArrayList<Integer> numOfZeroes = new ArrayList<>();
        for(ArrayList<Integer> layer : layers){
            numOfZeroes.add(countX(0,layer));
        }

        //Find the layer with the least number of zeroes
        int minZeroes = Collections.min(numOfZeroes);
        //Find the index of the layer with the most zeroes
        int index = numOfZeroes.lastIndexOf(minZeroes);

        //Count the number of 1's and 2's of the layer with the least zeroes
        System.out.println("Final answer: " + countX(1,layers.get(index)) * countX(2, layers.get(index)));
    }
}
