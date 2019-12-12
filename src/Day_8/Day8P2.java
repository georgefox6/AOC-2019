package Day_8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day8P2 {

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

        ArrayList<Integer> finalLayer = new ArrayList<>();
        for(int num : layers.get(0)){
            finalLayer.add(num);
        }
        int black = 0;
        int white = 1;
        //for each position
        for(int i = 0; i < NUM_IN_LAYER; i++){
            int counter = 0;
            while(!(finalLayer.get(i) == black || finalLayer.get(i) == white)){
                finalLayer.set(i,layers.get(counter).get(i));
                counter++;
            }
        }
        System.out.println(finalLayer);
        for(int i=0; i < PIXEL_HEIGHT; i++){
            for(int j=0; j < PIXEL_WIDTH; j++){
                if(finalLayer.get(i*PIXEL_WIDTH + j) == 0){
                    System.out.print(" ");
                } else{
                    System.out.print("#");
                }
            }
            System.out.println(" ");
        }
    }
}
