package Day_10;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day10 {
    public static int asteroidsInView(ArrayList<Point> asteroidCoordinates, Point targetAsteroid){
        int asteroidsInView = 0;
        ArrayList<Double> gradients = new ArrayList<>();
        for(Point asteroid : asteroidCoordinates){
            //Gradient between asteroid and target asteroid (Add to ArrayList gradients
            double gradient = ((targetAsteroid.getX() - asteroid.getX())/(targetAsteroid.getY() - asteroid.getY()));
            gradients.add(gradient);
        }
        return asteroidsInView;
    }

    public static void main(String[] args) throws IOException {
        //read the file in
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\gfox\\Java Projects\\AOC-2019\\src\\Day_10\\input.txt"));
        String input;

        //Store the asteroid map in an array list
        ArrayList<ArrayList<Character>> asteroidMap = new ArrayList<>();
        while((input = reader.readLine()) != null){
            ArrayList<Character> line = new ArrayList<>();
            for(int i=0; i < input.length(); i++){
                line.add(input.charAt(i));
            }
            asteroidMap.add(line);
        }

        //Store each asteroid location in an array list of points
        int x = 0;
        int y = 0;
        ArrayList<Point> asteroidCoordinates = new ArrayList<>();
        for(ArrayList<Character> lines : asteroidMap){
            for(char character : lines){
                if(character == '#'){
                    asteroidCoordinates.add(new Point(x,y));
                }
                x++;
            }
            y++;
        }

        //Find the gradient of every asteroid compared to the target asteroid (Need to take negatives into account)
        //From the list of gradients, count the number of unique gradients
        //Find the asteroid with the highest unique gradients


        //Count how many other asteroids can be seen by each asteroid
        for(Point asteroids : asteroidCoordinates){
            
        }
    }
}
