package Day_11;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import Intcode.Intcode;

public class Day11 {

    public static void main(String[] args) throws IOException {
        //read the file in
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\gfox\\Java Projects\\AOC-2019\\src\\Day_11\\input.txt"));
        String input = reader.readLine();

        //Create the painting map
        Long[][] map = new Long[100][100];
        for (Long[] row : map) {
            Arrays.fill(row, 0L);
        }
        int pointerX = 50;
        int pointerY = 50;
        map[50][50] = 1l;
        ArrayList<Point> paintedCells = new ArrayList<>();
        String currentDirection = "Up";

        //Create the painting robot
        Intcode paintingRobot = new Intcode(input);
        paintingRobot.inputInstructions.add(map[pointerX][pointerY]);

        while (!paintingRobot.finished) {
            System.out.println("Next step");
            paintingRobot.nextStep();
            //If painting robot is waiting for input, add colour under the robot to input instructions
            if (paintingRobot.waitingForInput) {
                System.out.println("Adding input instruction");
                paintingRobot.inputInstructions.addLast(map[pointerX][pointerY]);
            }
            //If the output instructions contains 2 numbers then paint the cell and move the robot
            if (paintingRobot.outputInstructions.size() == 2) {
                System.out.println("WE PAINTING SOME CELLS");
                //paint cell
                map[pointerX][pointerY] = Long.valueOf(paintingRobot.outputInstructions.removeFirst());
                paintedCells.add(new Point(pointerX, pointerY));
                //move robot
                Long directionInstruction = paintingRobot.outputInstructions.removeFirst();
                if (directionInstruction == 0L) {
                    switch (currentDirection) {
                        case "Up":
                            currentDirection = "Left";
                            pointerX--;
                            break;
                        case "Right":
                            currentDirection = "Up";
                            pointerY++;
                            break;
                        case "Down":
                            currentDirection = "Right";
                            pointerX++;
                            break;
                        case "Left":
                            currentDirection = "Down";
                            pointerY--;
                            break;
                    }
                } else if (directionInstruction == 1L) {
                    switch (currentDirection) {
                        case "Up":
                            currentDirection = "Right";
                            pointerX++;
                            break;
                        case "Right":
                            currentDirection = "Down";
                            pointerY--;
                            break;
                        case "Down":
                            currentDirection = "Left";
                            pointerX--;
                            break;
                        case "Left":
                            currentDirection = "Up";
                            pointerY++;
                            break;
                    }
                }
            }
        }
        //Get unique list of painted cells
        Set<Point> uniquePaintedCells = new HashSet<>(paintedCells);
        System.out.println("NUMBER OF PAINTED CELLS: " + uniquePaintedCells.size());

        for(int q=0; q < 100; q++){
            for(int w=0; w < 100; w++){
                if(map[w][99-q]==0){
                    System.out.print(" ");
                }else{
                    System.out.print("#");
                }
            }
            System.out.println();
        }
    }
}