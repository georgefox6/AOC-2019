package Day_10;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Day10 {
    public static int asteroidsInView(ArrayList<Point> asteroidCoordinates, Point targetAsteroid){
        ArrayList<Double> gradients = new ArrayList<>();
        for(Point asteroid : asteroidCoordinates){
            //Gradient between asteroid and target asteroid (Add to ArrayList gradients)
            double angle = Math.toDegrees(Math.atan2(targetAsteroid.getX() - asteroid.getX(), targetAsteroid.getY() - asteroid.getY()));
            gradients.add(angle);
            //Put the array list in a set to remove any duplicate gradients
        }
        Set<Double> uniqueGradients = new HashSet<>(gradients);

        return uniqueGradients.size();
    }

    public static void main(String[] args) throws IOException {
        //read the file in
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Georg\\IdeaProjects\\AOC-2019\\src\\Day_10\\input2.txt"));
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
            x = 0;
            for(char character : lines){
                if(character == '#'){
                    asteroidCoordinates.add(new Point(x,y));
                }
                x++;
            }
            y++;
        }

        ArrayList<Integer> asteroidsInView = new ArrayList<>();
        //Count how many other asteroids can be seen by each asteroid
        for(Point asteroids : asteroidCoordinates){
            asteroidsInView.add(asteroidsInView(asteroidCoordinates, asteroids));
        }

        System.out.println("Winner : " + Collections.max(asteroidsInView));
        System.out.println(asteroidsInView.indexOf(Collections.max(asteroidsInView)));
        System.out.println(asteroidCoordinates.get(205));
    }
}