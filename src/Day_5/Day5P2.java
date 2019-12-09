package Day_5;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Day5P2 {

    public static int getIncrement(int input){
        int increment;
        int opcode = input%10;
        if(opcode == 1 || opcode == 2 || opcode == 7 || opcode == 8){
            increment = 4;
        } else if(opcode == 3 || opcode == 4){
            increment = 2;
        } else if(opcode == 5 || opcode == 6){
          increment = 3;
        } else{
            increment = 10000;
        }
        return increment;
    }

    public static void main(String[] args) throws IOException {
        //read the file in
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\gfox\\Java Projects\\AOC-2019\\src\\Day_5\\input.txt"));
        String input = reader.readLine();
        //Split up the string into an array of integers
        String[] strArray = input.split(",");
        int[] inputArray = new int[strArray.length];
        for(int i=0; i < strArray.length; i++){
            inputArray[i] = Integer.parseInt(strArray[i]);
        }
        //Creates the scanner to read user input
        Scanner scanner = new Scanner(System.in);

        //Sets the increment amount based on the opcode
        int increment = getIncrement(inputArray[0]);
        for(int i=0; i < inputArray.length; i += increment){
            int num1;
            int num2;

            //Format the input to include leading zeroes
            strArray[i] = String.format("%05d" , inputArray[i]);
            //gets the opcode from the last 2 characters of the input
            String opcode = strArray[i].substring(3);
            //System.out.println("opcode: " + opcode);


            //get the parameter mode for each number
            String[] parameterMode = new String[3];
            parameterMode[0] = strArray[i].substring(2,3);
            parameterMode[1] = strArray[i].substring(1,2);
            parameterMode[2] = strArray[i].substring(0,1);
            switch(opcode){
                case "01":
                    num1 = parameterMode[0].equals("1") ? inputArray[i+1] : inputArray[inputArray[i+1]];
                    num2 = parameterMode[1].equals("1") ? inputArray[i+2] : inputArray[inputArray[i+2]];
                    inputArray[inputArray[i+3]] = num1 + num2;
                    increment = getIncrement(inputArray[i]);
                    break;
                case "02":
                    num1 = parameterMode[0].equals("1") ? inputArray[i+1] : inputArray[inputArray[i+1]];
                    num2 = parameterMode[1].equals("1") ? inputArray[i+2] : inputArray[inputArray[i+2]];
                    inputArray[inputArray[i+3]] = num1 * num2;
                    increment = getIncrement(inputArray[i]);
                    break;
                case "03":
                    System.out.println("Enter a number: ");
                    inputArray[inputArray[i+1]] = scanner.nextInt();
                    increment = getIncrement(inputArray[i]);
                    break;
                case "04":
                    if(parameterMode[0].equals("1")){
                        System.out.println(inputArray[i+1]);
                    } else if(parameterMode[0].equals("0")){
                        System.out.println(inputArray[inputArray[i+1]]);
                    }
                    increment = getIncrement(inputArray[i]);
                    break;
                case "05":
                    num1 = parameterMode[0].equals("1") ? inputArray[i+1] : inputArray[inputArray[i+1]];
                    num2 = parameterMode[1].equals("1") ? inputArray[i+2] : inputArray[inputArray[i+2]];
                    if(num1 != 0){
                        i = num2;
                        increment = 0;
                    } else {
                        increment = getIncrement(inputArray[i]);
                    }
                    break;
                case "06":
                    num1 = parameterMode[0].equals("1") ? inputArray[i+1] : inputArray[inputArray[i+1]];
                    num2 = parameterMode[1].equals("1") ? inputArray[i+2] : inputArray[inputArray[i+2]];
                    if(num1 == 0){
                        i = num2;
                        increment = 0;
                    } else{
                        increment = getIncrement(inputArray[i]);
                    }
                    break;
                case "07":
                    num1 = parameterMode[0].equals("1") ? inputArray[i+1] : inputArray[inputArray[i+1]];
                    num2 = parameterMode[1].equals("1") ? inputArray[i+2] : inputArray[inputArray[i+2]];
                    if(num1 < num2){
                        inputArray[inputArray[i+3]] = 1;
                    } else {
                        inputArray[inputArray[i+3]] = 0;
                    }
                    increment = getIncrement(inputArray[i]);
                    break;
                case "08":
                    num1 = parameterMode[0].equals("1") ? inputArray[i+1] : inputArray[inputArray[i+1]];
                    num2 = parameterMode[1].equals("1") ? inputArray[i+2] : inputArray[inputArray[i+2]];
                    if(num1 == num2){
                        inputArray[inputArray[i+3]] = 1;
                    } else{
                        inputArray[inputArray[i+3]] = 0;
                    }
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
