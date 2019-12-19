package Intcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Intcode {
    String[] memory;
    String position1;
    String position2;
    Long relativeBase;
    String[] parameterMode;
    Deque<Long>  outputInstructions;
    Long increment;
    String opcode;

    Intcode(String inputMemory){
        this.memory = new String[10000];
        Arrays.fill(memory,0);
        this.memory = inputMemory.split(",");
        this.position1 = "";
        this.position2 = "";
        this.relativeBase = 0L;
        this.parameterMode = new String[3];
        this.outputInstructions = new LinkedList<>();
        this.opcode = "";
    }

    public void updateIncrement(){
//        Long opcode = input%10;
        if(this.opcode.equals("01") || this.opcode.equals("02") || this.opcode.equals("07") || this.opcode.equals("08")){
            this.increment = 4L;
        } else if(this.opcode.equals("03") || this.opcode.equals("04") || this.opcode.equals("09")){
            this.increment = 2L;
        } else if(this.opcode.equals("05") || this.opcode.equals("06")){
            this.increment = 3L;
        } else{
            this.increment = 10000L;
        }
    }

    public void updateParameterMode(String memoryAtI){
        parameterMode[0] = memoryAtI.substring(2,3);
        parameterMode[1] = memoryAtI.substring(1,2);
        parameterMode[2] = memoryAtI.substring(0,1);
    }

    public void updatePositions(int i){
        switch(parameterMode[0]){
            case "0":
                this.position1 = memory[Integer.parseInt(memory[i+1])];
                break;
            case "1":
                this.position1 = memory[i+1];
                break;
            case "2":
                this.position1 = memory[Integer.parseInt(memory[i+1] + relativeBase)];
                break;
        }
        switch(parameterMode[1]){
            case "0":
                this.position2 = memory[Integer.parseInt(memory[i+2])];
                break;
            case "1":
                this.position2 = memory[i+2];
                break;
            case "2":
                this.position2 = memory[Integer.parseInt(memory[i+2] + relativeBase)];
                break;
        }
    }

    public String addStrings(String str1, String str2){
        return Integer.toString(Integer.parseInt(str1) + Integer.parseInt(str2));
    }

    public void run(){
        for(int i=0; i < memory.length; i += increment) {

            //Format the memory to include leading zeroes
            memory[i] = String.format("%05d", memory[i]);

            //gets the opcode from the last 2 characters of the memory
            this.opcode = memory[i].substring(3);

            //Stores the parameter modes
            updateParameterMode(memory[i]);

            //Calculate the values of position1 and position 2 based on the parameter modes
            updatePositions(i);

            switch(opcode){
                case "01":
                    //if position mode
                    if(parameterMode[2].equals("0")){
                        memory[Integer.parseInt(memory[i+3])] = addStrings(position1, position2);
                    //if relative mode
                    } else {
                        memory[Integer.parseInt(memory[i+3] + relativeBase)] = addStrings(position1, position2);
                    }
                    updateIncrement();
                    break;

                case "02":
                    //if position mode
                    if(parameterMode[2].equals("0")){
                        memory[Integer.parseInt(memory[i+3])] = num1 * num2;
                    //if relative mode
                    } else{
                        memory[Integer.parseInt(memory[i+3] + relativeBase)] = num1 * num2;
                    }
                    updateIncrement();
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
                    System.out.println("i: " + i);
//                    System.out.println("inputArray[i]: " + inputArray[i] + " | inputArray[i+1]: " + inputArray[i+1]);
                    //If the input is the paint colour input
                    if((oddEven % 2) == 0){
                        changedPoints.add(new Point(pointerX,pointerY));
                        if(output != map[pointerX][pointerY]){
                            map[pointerX][pointerY] = output;
//                            changedPoints.add(new Point(pointerX,pointerY));
                        }
                        //else this is the change direction input
                    }else{
                        if(output == 0){
                            switch(currentDirection){
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
                        } else if(output == 1){
                            switch(currentDirection){
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
                    System.out.println("DO WE EVER GET HERE?");
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
        }
    }

}
