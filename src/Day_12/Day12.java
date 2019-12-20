package Day_12;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Day12 {

    public static void applyGravity(Moon moon1, Moon moon2){
        if(moon1.xPosition > moon2.xPosition){
            moon1.xVelocity--;
            moon2.xVelocity++;
        }
        if(moon1.yPosition > moon2.yPosition){
            moon1.yVelocity--;
            moon2.yVelocity++;
        }
        if(moon1.zPosition > moon2.zPosition){
            moon1.zVelocity--;
            moon2.zVelocity++;
        }
    }

    public static void updatePosition(Moon moon){
        moon.xPosition = moon.xPosition + moon.xVelocity;
        moon.yPosition = moon.yPosition + moon.yVelocity;
        moon.zPosition = moon.zPosition + moon.zVelocity;
    }

    public static void simulateMoons(ArrayList<Moon> moons){
        //Apply gravity to all moons
        for(Moon moon1 : moons){
            for(Moon moon2 : moons){
                applyGravity(moon1, moon2);
            }
        }
        //Update the moon positions
        for(Moon moon: moons){
            updatePosition(moon);
        }
    }

    public static String returnMoonStatus(ArrayList<Moon> moons){
        String output = "";
        for(Moon moon : moons){
            output += moon.toString();
        }
        return output;
    }

    public static void printMoonStatus(ArrayList<Moon> moons){
        for(Moon moon : moons){
            System.out.println(moon.toString());
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Moon io = new Moon(14,9,14);
        Moon europa = new Moon(9,11,6);
        Moon ganymede = new Moon(-6,14,-4);
        Moon callisto = new Moon(4,-4,-3);

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

        String previousStatus = "";
        String currentStatus;

        for(long i=0L; i < 2100000000; i++){
            if(i%100000000 == 0){
                System.out.println(i%100000000 + "% Done");
            }
            simulateMoons(moons);
            currentStatus = returnMoonStatus(moons);
            if(currentStatus.equals(previousStatus)){
                System.out.println("Match: " + i);
            }
            previousStatus = currentStatus;

        }


    }
}
