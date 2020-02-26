package Day_12;

import java.util.ArrayList;

public class Day12 {

    public static void applyGravity(Moon moon1, Moon moon2) {
        if (moon1.xPosition > moon2.xPosition) {
            moon1.xVelocity--;
            moon2.xVelocity++;
        }
        if (moon1.yPosition > moon2.yPosition) {
            moon1.yVelocity--;
            moon2.yVelocity++;
        }
        if (moon1.zPosition > moon2.zPosition) {
            moon1.zVelocity--;
            moon2.zVelocity++;
        }
    }

    public static void updatePosition(Moon moon) {
        moon.xPosition = moon.xPosition + moon.xVelocity;
        moon.yPosition = moon.yPosition + moon.yVelocity;
        moon.zPosition = moon.zPosition + moon.zVelocity;
    }

    public static void simulateMoons(ArrayList<Moon> moons) {
        //Apply gravity to all moons
        for (Moon moon1 : moons) {
            for (Moon moon2 : moons) {
                applyGravity(moon1, moon2);
            }
        }
        //Update the moon positions
        for (Moon moon : moons) {
            updatePosition(moon);
        }
    }

    public static String returnMoonStatus(ArrayList<Moon> moons) {
        String output = "";
        for (Moon moon : moons) {
            output += moon.toString();
        }
        return output;
    }

    public static void printMoonStatus(ArrayList<Moon> moons) {
        for (Moon moon : moons) {
            System.out.println(moon.toString());
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Moon io = new Moon(14, 9, 14);
        Moon europa = new Moon(9, 11, 6);
        Moon ganymede = new Moon(-6, 14, -4);
        Moon callisto = new Moon(4, -4, -3);

        ArrayList<Moon> moons = new ArrayList<>();
        moons.add(io);
        moons.add(europa);
        moons.add(ganymede);
        moons.add(callisto);

        /* Part 1
        for(Long i=0L; i < 1000; i++){
            simulateMoons(moons);
        }
        System.out.println(io.getTotalEnergy() + europa.getTotalEnergy() + ganymede.getTotalEnergy() + callisto.getTotalEnergy());
        */

        /*    Part 2   */
        ArrayList<ArrayList<Integer>> xPositions = new ArrayList<>();
        ArrayList<ArrayList<Integer>> yPositions = new ArrayList<>();
        ArrayList<ArrayList<Integer>> zPositions = new ArrayList<>();

        System.out.println("X - Position");
        for (int i = 0; i < 1000000; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(io.xPosition);
            temp.add(europa.xPosition);
            temp.add(ganymede.xPosition);
            temp.add(callisto.xPosition);

            temp.add(io.xVelocity);
            temp.add(europa.xVelocity);
            temp.add(ganymede.xVelocity);
            temp.add(callisto.xVelocity);


            if (xPositions.contains(temp)) {
                System.out.println(i);
                break;
            }
            xPositions.add(temp);
            simulateMoons(moons);
        }

        System.out.println("Y - Position");
        for (int i = 0; i < 1000000; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(io.yPosition);
            temp.add(europa.yPosition);
            temp.add(ganymede.yPosition);
            temp.add(callisto.yPosition);

            temp.add(io.yVelocity);
            temp.add(europa.yVelocity);
            temp.add(ganymede.yVelocity);
            temp.add(callisto.yVelocity);

            if (yPositions.contains(temp)) {
                System.out.println(i);
                break;
            }
            yPositions.add(temp);
            simulateMoons(moons);
        }

        System.out.println("Z - Position");
        for (int i = 0; i < 10000000; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(io.zPosition);
            temp.add(europa.zPosition);
            temp.add(ganymede.zPosition);
            temp.add(callisto.zPosition);

            temp.add(io.zVelocity);
            temp.add(europa.zVelocity);
            temp.add(ganymede.zVelocity);
            temp.add(callisto.zVelocity);

            if (zPositions.contains(temp)) {
                System.out.println(i);
                break;
            }
            zPositions.add(temp);
            simulateMoons(moons);
        }
    }
}
