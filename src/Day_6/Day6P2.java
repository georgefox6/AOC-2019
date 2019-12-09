package Day_6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day6P2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Georg\\IdeaProjects\\AOC-2019\\src\\Day_6\\input.txt"));
        String input;
        //read in all of the orbits and store them in an arraylist of lists
        ArrayList<List<String>> arr = new ArrayList<>();
        while((input = reader.readLine()) != null){
            arr.add(Arrays.asList(input.split("\\)")));
        }

        //creates the map
        HashMap<String, String> map = new HashMap<String, String>();
        //iterates over arr to fill the map
        for(int i=0; i<arr.size(); i++){
            String value = arr.get(i).get(0);
            String key = arr.get(i).get(1);
            //if the key doesn't exist in the map then add it to the map
            if(!map.containsKey(key)){
                map.put(key, value);
                //if the key does exist in map then add the value to the List of values for that key
            } else {
                map.put(key, value);
            }
        }

        //Create the santa trail to the top
        ArrayList<String> santaTrail = new ArrayList<>();
        String santa = map.get("SAN");
        santaTrail.add("SAN");
        while(santa != null){
            santaTrail.add(map.get(santa));
            //get the parent of santa
            santa = map.get(santa);
        }

        //Create the YOU trail to the top
        ArrayList<String> youTrail = new ArrayList<>();
        String you = map.get("YOU");
        youTrail.add("YOU");
        while(you != null){
            youTrail.add(map.get(you));
            //get the parent of you
            you = map.get(you);
        }

        //compare the trails - find where they touch
        for(int i=0; i<santaTrail.size(); i++){
            for(int j=0; j<youTrail.size() - 1; j++){
                if(youTrail.get(j).equals(santaTrail.get(i))){
                    System.out.println("I: " + i + "J: " + j);
                }
            }
        }
    }
}
