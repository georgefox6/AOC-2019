package Day_11;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day11 {

    public static Long getIncrement(Long input){
        Long increment;
        Long opcode = input%10;
        if(opcode == 1 || opcode == 2 || opcode == 7 || opcode == 8){
            increment = 4L;
        } else if(opcode == 3 || opcode == 4 || opcode == 9){
            increment = 2L;
        } else if(opcode == 5 || opcode == 6){
            increment = 3L;
        } else{
            increment = 10000L;
        }
        return increment;
    }

    public static Long modeCalc(String parameterMode, Long[] inputArray, int i, int relativeBase){
        Long num;
        switch(parameterMode){
            case "0":
                num = inputArray[Math.toIntExact(inputArray[i])];
                break;
            case "1":
                num = inputArray[i];
                break;
            case "2":
                num = inputArray[Math.toIntExact(inputArray[i] + relativeBase)];
                break;
            default:
                num = 0L;
        }
        return num;
    }

    public static void main(String[] args) throws IOException {
        //read the file in
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Georg\\IdeaProjects\\AOC-2019\\src\\Day_11\\input.txt"));
        String input = reader.readLine();
        //Split up the string into an array of integers
        String[] strArray = input.split(",");
//        Long[] inputArray = new Long[strArray.length];
        Long[] inputArray = new Long[1000000];
        Arrays.fill(inputArray, 0L);
        for(int i=0; i < strArray.length; i++){
            inputArray[i] = Long.parseLong(strArray[i]);
        }

        Long[][] map = new Long[100][100];
        for(Long[] row : map){
            Arrays.fill(row, 0L);
        }
        int pointerX = 50;
        int pointerY = 50;
        int oddEven = 0;
        ArrayList<Point> changedPoints = new ArrayList<>();
        String currentDirection = "Up";

        //Creates the scanner to read user input
        Scanner scanner = new Scanner(System.in);

        //Sets the relative base to 0
        int relativeBase =0;

        //Sets the increment amount based on the opcode
        Long increment = getIncrement(inputArray[0]);

        //Main int code loop
        for(int i=0; i < inputArray.length; i += increment){
            Long num1;
            Long num2;


            //Format the input to include leading zeroes
            strArray[i] = String.format("%05d" , inputArray[i]);
            //gets the opcode from the last 2 characters of the input
            String opcode = strArray[i].substring(3);
//            System.out.println("opcode: " + opcode);

            //get the parameter mode for each number
            String[] parameterMode = new String[3];
            parameterMode[0] = strArray[i].substring(2,3);
            parameterMode[1] = strArray[i].substring(1,2);
            parameterMode[2] = strArray[i].substring(0,1);
            switch(opcode){
                case "01":
                    num1 = modeCalc(parameterMode[0],inputArray,i+1,relativeBase);
                    num2 = modeCalc(parameterMode[1],inputArray,i+2,relativeBase);
                    if(parameterMode[2].equals("0")){
                        inputArray[Math.toIntExact(inputArray[i+3])] = num1 + num2;
                    } else {
                        inputArray[Math.toIntExact(inputArray[i+3] + relativeBase)] = num1 + num2;
                    }
                    increment = getIncrement(inputArray[i]);
                    break;
                case "02":
                    num1 = modeCalc(parameterMode[0],inputArray,i+1,relativeBase);
                    num2 = modeCalc(parameterMode[1],inputArray,i+2,relativeBase);
                    if(parameterMode[2].equals("0")){
                        inputArray[Math.toIntExact(inputArray[i+3])] = num1 * num2;
                    } else{
                        inputArray[Math.toIntExact(inputArray[i+3] + relativeBase)] = num1 * num2;
                    }
                    increment = getIncrement(inputArray[i]);
                    break;
                case "03":
                    System.out.println("Took " + map[pointerX][pointerY] + " as input");
                    if(parameterMode[0].equals("2")){
                        inputArray[Math.toIntExact(inputArray[i+1] + relativeBase)] = map[pointerX][pointerY];
                        increment = getIncrement(inputArray[i]);
                    } else if(parameterMode[0].equals("1")) {
                        inputArray[Math.toIntExact(inputArray[i+1])] = map[pointerX][pointerY];
                        increment = getIncrement(inputArray[i]);
                    } else {
                        inputArray[i+1] = map[pointerX][pointerY];
                        increment = getIncrement(inputArray[i]);
                    }

                    break;
                case "04":
                    Long output = modeCalc(parameterMode[0],inputArray,i+1,relativeBase);
                    System.out.println("Output: " + output);
                    if((oddEven % 2) == 0){
                        if(output != map[pointerX][pointerY]){
                            map[pointerX][pointerY] = output;
                            changedPoints.add(new Point(pointerX,pointerY));
                        }
                    }else{
                        if(output == 0){
                            switch(currentDirection){
                                case "Up":
                                    currentDirection = "Left";
                                    pointerY--;
                                    break;
                                case "Right":
                                    currentDirection = "Up";
                                    pointerX++;
                                    break;
                                case "Down":
                                    currentDirection = "Right";
                                    pointerY++;
                                    break;
                                case "Left":
                                    currentDirection = "Down";
                                    pointerX--;
                                    break;
                            }
                        } else if(output == 1){
                            switch(currentDirection){
                                case "Up":
                                    currentDirection = "Right";
                                    pointerY++;
                                    break;
                                case "Right":
                                    currentDirection = "Down";
                                    pointerX--;
                                    break;
                                case "Down":
                                    currentDirection = "Left";
                                    pointerY--;
                                    break;
                                case "Left":
                                    currentDirection = "Up";
                                    pointerX++;
                                    break;
                            }
                        }
                    }



                    oddEven++;
                    increment = getIncrement(inputArray[i]);
                    break;
                case "05":
                    num1 = modeCalc(parameterMode[0],inputArray,i+1,relativeBase);
                    num2 = modeCalc(parameterMode[1],inputArray,i+2,relativeBase);
                    if(num1 != 0){
                        i = Math.toIntExact(num2);
                        increment = 0L;
                    } else {
                        increment = getIncrement(inputArray[i]);
                    }
                    break;
                case "06":
                    num1 = modeCalc(parameterMode[0],inputArray,i+1,relativeBase);
                    num2 = modeCalc(parameterMode[1],inputArray,i+2,relativeBase);
                    if(num1 == 0){
                        i = Math.toIntExact(num2);
                        increment = 0L;
                    } else{
                        increment = getIncrement(inputArray[i]);
                    }
                    break;
                case "07":
                    num1 = modeCalc(parameterMode[0],inputArray,i+1,relativeBase);
                    num2 = modeCalc(parameterMode[1],inputArray,i+2,relativeBase);
                    if(num1 < num2){
                        if(parameterMode[2].equals("0")){
                            inputArray[Math.toIntExact(inputArray[i+3])] = 1L;
                        } else {
                            inputArray[Math.toIntExact(inputArray[i+3] + relativeBase)] = 1L;
                        }
                    } else {
                        if(parameterMode[2].equals("0")){
                            inputArray[Math.toIntExact(inputArray[i+3])] = 0L;
                        } else {
                            inputArray[Math.toIntExact(inputArray[i+3] + relativeBase)] = 0L;
                        }

                    }
                    increment = getIncrement(inputArray[i]);
                    break;
                case "08":
                    num1 = modeCalc(parameterMode[0],inputArray,i+1,relativeBase);
                    num2 = modeCalc(parameterMode[1],inputArray,i+2,relativeBase);
                    if(num1 == num2){
                        if(parameterMode[2].equals("0")){
                            inputArray[Math.toIntExact(inputArray[i+3])] = 1L;
                        } else {
                            inputArray[Math.toIntExact(inputArray[i+3] + relativeBase)] = 1L;
                        }
                    } else{
                        if(parameterMode[2].equals("0")){
                            inputArray[Math.toIntExact(inputArray[i+3])] = 0L;
                        } else {
                            inputArray[Math.toIntExact(inputArray[i+3] + relativeBase)] = 0L;
                        }
                    }
                    increment = getIncrement(inputArray[i]);
                    break;
                case "09":
                    num1 = modeCalc(parameterMode[0],inputArray,i+1,relativeBase);
                    relativeBase += num1;
                    increment = getIncrement(inputArray[i]);
                    break;
                case "99":
                    System.out.println("99!");
                    i = inputArray.length;
                    break;
                default:
                    System.out.println("opcode: " + opcode);
                    i = inputArray.length;
                    System.out.println("Case : not 1, 2, 3, 4, 5, 6, 7, 8 or 99");
                    break;
            }
        }
        for(Point points : changedPoints){
            System.out.println(points);
        }
        for(Long[] row : map){
            for(Long pos : row){
                System.out.print(pos);
            }
            System.out.println("");
        }
    }
}
