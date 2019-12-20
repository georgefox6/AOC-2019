package Intcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Intcode {
    public Long[] memory;
    public Long position1;
    public Long position2;
    public Long position1Index;
    public Long position2Index;
    public Long position3Index;
    public Long relativeBase;
    public String[] parameterMode;
    public Deque<Long> outputInstructions;
    public Deque<Long> inputInstructions;
    public Long increment;
    public String opcode;
    public int i;
    public boolean finished;
    public boolean waitingForInput;

    public Intcode(String inputMemory) {
        this.memory = new Long[100000];
        Arrays.fill(memory, 0L);
        String[] tempMemory = inputMemory.split(",");
        int g = 0;
        for (String instructions : tempMemory) {
            memory[g] = Long.parseLong(instructions);
            g++;
        }
        this.position1 = 0L;
        this.position2 = 0L;
        this.position1Index = 0L;
        this.position2Index = 0L;
        this.position3Index = 0L;
        this.relativeBase = 0L;
        this.parameterMode = new String[3];
        this.outputInstructions = new LinkedList<>();
        this.inputInstructions = new LinkedList<>();
        this.opcode = "";
        this.increment = 0L;
        this.i = 0;
        this.finished = false;
        this.waitingForInput = false;
    }

    public void updateIncrement() {
        if (this.opcode.equals("01") || this.opcode.equals("02") || this.opcode.equals("07") || this.opcode.equals("08")) {
            this.increment = 4L;
        } else if (this.opcode.equals("03") || this.opcode.equals("04") || this.opcode.equals("09")) {
            this.increment = 2L;
        } else if (this.opcode.equals("05") || this.opcode.equals("06")) {
            this.increment = 3L;
        } else {
            this.increment = 10000L;
        }
    }

    public void updateParameterMode(String memoryAtI) {
        parameterMode[0] = memoryAtI.substring(2, 3);
        parameterMode[1] = memoryAtI.substring(1, 2);
        parameterMode[2] = memoryAtI.substring(0, 1);
    }

    public void updatePositions(int i) {
        switch (parameterMode[0]) {
            case "0":
                this.position1 = memory[memory[i + 1].intValue()];
                this.position1Index = memory[i + 1];
                break;
            case "1":
                this.position1 = memory[i + 1];
                this.position1Index = (long)(i + 1);
                break;
            case "2":
                this.position1 = memory[memory[i + 1].intValue() + relativeBase.intValue()];
                this.position1Index = memory[i + 1] + relativeBase;
                break;
        }
        switch (parameterMode[1]) {
            case "0":
                this.position2 = memory[memory[i + 2].intValue()];
                this.position2Index = memory[i + 2];
                break;
            case "1":
                this.position2 = memory[i + 2];
                this.position2Index = (long)(i + 2);
                break;
            case "2":
                this.position2 = memory[memory[i + 2].intValue() + relativeBase.intValue()];
                this.position2Index = memory[i + 2] + relativeBase;
                break;
        }
        switch (parameterMode[2]) {
            //position mode
            case "0":
                this.position3Index = memory[i + 3];
                break;
            //immediate mode
            case "1":
                this.position3Index = (long)i;
                break;
            //Relative mode
            case "2":
                this.position3Index = memory[i + 3] + relativeBase;

        }
    }

    public void nextStep() {
        //Format the memory to include leading zeroes
        String fullOpcode = String.format("%05d", memory[i]);

        //gets the opcode from the last 2 characters of the memory
        this.opcode = fullOpcode.substring(3);

        //Stores the parameter modes
        updateParameterMode(fullOpcode);

        //Calculate the values of position1 and position 2 based on the parameter modes
        updatePositions(i);

        //reset the increment back to 0
        increment = 0L;
        switch (opcode) {
            case "01":
                memory[position3Index.intValue()] = position1 + position2;
                updateIncrement();
                break;

            case "02":
                memory[position3Index.intValue()] = position1 * position2;
                updateIncrement();
                break;

            case "03":
                if (inputInstructions.size() < 1) {
                    System.out.println("Awaiting input...");
                    waitingForInput = true;

                } else {
                    waitingForInput = false;
                    memory[position1Index.intValue()] = inputInstructions.removeFirst();
                    updateIncrement();
                }
                break;

            case "04":
                System.out.println("Output: " + position1);
                outputInstructions.addLast(position1);
                updateIncrement();
                break;

            case "05":
                if (position1 != 0L) {
                    i = position2.intValue();
                } else {
                    updateIncrement();
                }
                break;

            case "06":
                if (position1 == 0L) {
                    i = position2.intValue();
                } else {
                    updateIncrement();
                }
                break;

            case "07":
                if (position1 < position2) {
                    memory[position3Index.intValue()] = 1L;
                } else {
                    memory[position3Index.intValue()] = 0L;
                }
                updateIncrement();
                break;

            case "08":
                if (position1 == position2) {
                    memory[position3Index.intValue()] = 1L;
                } else {
                    memory[position3Index.intValue()] = 0L;
                }
                updateIncrement();
                break;

            case "09":
                relativeBase += position1;
                updateIncrement();
                break;

            case "99":
                System.out.println("99!");
                this.finished = true;
                break;
            default:
                System.out.println("Bad: " + memory[i]);
        }
        i += increment;
    }
}