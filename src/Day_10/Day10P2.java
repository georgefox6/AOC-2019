package Day_10;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day10P2 {
    public static ArrayList<Double> anglesOfAsteroids(ArrayList<Point> asteroidCoordinates, Point targetAsteroid){
        ArrayList<Double> angles = new ArrayList<>();
        for(Point asteroid : asteroidCoordinates){
            //angle between asteroid and target asteroid (Add to ArrayList gradients)
            double deltaX = asteroid.getX() - targetAsteroid.getX();
            double deltaY = asteroid.getY() - targetAsteroid.getY();
            double angle = Math.toDegrees(Math.atan2(deltaX , deltaY));
            angles.add(angle);
            //Put the array list in a set to remove any duplicate gradients
        }
        return angles;
    }

    public static void main(String[] args) throws IOException {
        final boolean TEST = false;
        //read the file in
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\gfox\\Java Projects\\AOC-2019\\src\\Day_10\\input.txt"));
        if(TEST){
            reader = new BufferedReader(new FileReader("C:\\Users\\gfox\\Java Projects\\AOC-2019\\src\\Day_10\\input2.txt"));
        }
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
                    if(TEST){
                        asteroidCoordinates.add(new Point(x,20-y));
                    }else {
                        asteroidCoordinates.add(new Point(x,41-y));
                    }
                }
                x++;
            }
            y++;
        }

        //Stores a list of the unsorted angles so that we can search indexOf on it
        ArrayList<Double> unsortedAngles;
        if(TEST){
            unsortedAngles = anglesOfAsteroids(asteroidCoordinates, asteroidCoordinates.get(205));
        } else {
            unsortedAngles = anglesOfAsteroids(asteroidCoordinates, asteroidCoordinates.get(281));
        }

        System.out.println("Target Asteroid: " + asteroidCoordinates.get(281));

        //Turn all of the negative angles into positive angels
        int i=0;
        for(double angle : unsortedAngles){
            if(angle < 0.0){
                unsortedAngles.set(i, angle + 360.00);
            }
            i++;
        }

        //gets list of unique ordered angles so that we find out how many asteroids are destroyed on the first pass when eliminated in the correct order
        Set<Double> uniqueAngles = new HashSet<>(unsortedAngles);
        ArrayList<Double> uniqueAnglesList = new ArrayList<>(uniqueAngles);
        Collections.sort(uniqueAnglesList);

        //This is the angle of the 200th asteroid
        double winningAngle = uniqueAnglesList.get(199);
        System.out.println("Winning Angle: " + winningAngle);

        for(int a=0; a < unsortedAngles.size(); a++){
            if(unsortedAngles.get(a) == winningAngle){
                System.out.println(asteroidCoordinates.get(a));
            }
        }
    }
}
