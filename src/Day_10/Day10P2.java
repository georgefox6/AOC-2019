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
            //Gradient between asteroid and target asteroid (Add to ArrayList gradients)
            double angle = Math.toDegrees(Math.atan2(targetAsteroid.getX() - asteroid.getX(), targetAsteroid.getY() - asteroid.getY()));
            angles.add(angle);
            //Put the array list in a set to remove any duplicate gradients
        }
        return angles;
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
            x = 0;
            for(char character : lines){
                if(character == '#'){
                    asteroidCoordinates.add(new Point(x,y));
                }
                x++;
            }
            y++;
        }

        //Stores a list of all of the angles of the asteroids
        ArrayList<Double> angles = anglesOfAsteroids(asteroidCoordinates, asteroidCoordinates.get(281));

        //Turn all of the negative angles into positive angels
        int i=0;
        for(double angle : angles){
            if(angle < 0.0){
                angles.set(i, angle + 360.00);
            }
            i++;
        }

        //Stores a list of the unsorted angles so that we can search indexOf on it
        ArrayList<Double> unsortedAngles = new ArrayList<>(angles);

        //gets list of unique ordered angles so that we find out how many asteroids are destroyed on the first pass when eliminated in the correct order
        Set<Double> uniqueAngles = new HashSet<>(angles);
        ArrayList<Double> uniqueAnglesList = new ArrayList<>(uniqueAngles);
        Collections.sort(uniqueAnglesList);

        //This is the angle of the 200th asteroid
        double winningAngle = uniqueAnglesList.get(199);
        System.out.println(uniqueAnglesList);
        System.out.println(winningAngle);

        //Get the index of the 200th asteroid from the list of unsorted angles
        int indexOfWinningAsteroid = unsortedAngles.indexOf(winningAngle);

        //Print out the coordinates of the asteroid at index of winning asteroid
        System.out.println(asteroidCoordinates.get(indexOfWinningAsteroid));




    }
}
