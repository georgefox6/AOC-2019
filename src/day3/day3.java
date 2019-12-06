package day3;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Point;
import java.util.ArrayList;

public class day3  {
    //Method to create an arraylist of coordinates which contain a line
    public static ArrayList<Point> createMap(String stuff){
        ArrayList<Point> path = new ArrayList<>();
        String[] instructions = stuff.split(",");

        int pointerX = 0;
        int pointerY = 0;

        int distance;
        char direction;

        for(int i=0; i<instructions.length; i++){
            distance = Integer.parseInt(instructions[i].substring(1));
            direction = instructions[i].charAt(0);
            for(int j=0; j<distance; j++){
                switch(direction){
                    case 'R':
                        pointerX++;
                        break;
                    case 'L':
                        pointerX--;
                        break;
                    case 'U':
                        pointerY++;
                        break;
                    case 'D':
                        pointerY--;
                        break;
                }
                path.add(new Point(pointerX, pointerY));
            }
        }
        return path;
    }
    //returns an arraylsit of points where the 2 lines overlap
    public static ArrayList<Point> getUnion(ArrayList<Point> pathA, ArrayList<Point> pathB) {
        ArrayList<Point> result = new ArrayList<>();

        for (Point p : pathA) {
            if (pathB.contains(p)) {
                result.add(p);
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        //Reads in the input from the file
        BufferedReader reader = new BufferedReader(new FileReader("day3/input.txt"));
        ArrayList<Point> pathA = createMap(reader.readLine());
        ArrayList<Point> pathB = createMap(reader.readLine());
        //calculates the point with the shortest distance
        int minDistance = 100000;
        for (Point p : getUnion(pathA, pathB)) {
            if(pathA.indexOf(p) + pathB.indexOf(p) < minDistance){
                minDistance = pathA.indexOf(p) + pathB.indexOf(p);
            }
        }
        //prints the min distance
        minDistance += 2;
        System.out.println(minDistance);

        }
    }


