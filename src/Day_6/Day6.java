package Day_6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day6 {
    public static int countOrbits(HashMap<String, List<String>> map, String key, int parentOrbits){
        int totalOrbits = 0;
        List<String> values = map.get(key);
        if(values != null){
            for(String value : values){
                totalOrbits += parentOrbits + countOrbits(map, value, parentOrbits+1) + 1;
            }
        }
        return totalOrbits;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\gfox\\Java Projects\\AOC-2019\\src\\Day_6\\input.txt"));
        String input;
        //read in all of the orbits and store them in an arraylist of lists
        ArrayList<List<String>> arr = new ArrayList<>();
        while((input = reader.readLine()) != null){
            arr.add(Arrays.asList(input.split("\\)")));
        }

        //creates the map
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        //iterates over arr to fill the map
        for(int i=0; i<arr.size(); i++){
            String key = arr.get(i).get(0);
            String value = arr.get(i).get(1);
            //if the key doesn't exist in the map then add it to the map
            if(!map.containsKey(key)){
                map.put(key, new ArrayList<String>(Arrays.asList(value)));
            //if the key does exist in map then add the value to the List of values for that key
            } else {
                map.get(key).add(value);
            }
        }

        //Count the number of direct and indirect orbits
        System.out.println(countOrbits(map,"COM",0));

    }
}
