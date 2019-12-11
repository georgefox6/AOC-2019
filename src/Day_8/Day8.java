package Day_8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day8 {

    public static int countX(int value, ArrayList<Integer> layer){
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
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Georg\\IdeaProjects\\AOC-2019\\src\\Day_8\\input.txt"));
        String input = reader.readLine();

        //Store all of the layers in an ArrayList
        ArrayList<ArrayList<Integer>> layers = new ArrayList<>();

        for(int i=1; i <= input.length()/NUM_IN_LAYER; i++){
            ArrayList<Integer> layer = new ArrayList<>();
            for(int j=0; j < NUM_IN_LAYER; j++){
                layer.add((int)input.charAt(i*j) - 48);
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
        int index = numOfZeroes.indexOf(minZeroes);

        //Count the number of 1's and 2's of the layer with the least zeroes
        System.out.println("Final answer: " + countX(1,layers.get(index)) * countX(2, layers.get(index)));

        /* DEBUGGING */

        System.out.println("Some Random Number: " + layers.get(0).get(0));
        System.out.println("num of zeroes: " + numOfZeroes.get(1));
        System.out.println("Min Zeroes: " + minZeroes);
        System.out.println("Index: " + index);

        //zeroes in each layer
        for(int num : numOfZeroes){
            System.out.println(num);
        }
        System.out.println(layers.get(1));

        /* END OF DEBUGGING */
    }
}
