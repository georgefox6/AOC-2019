package Day_7;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class Day7 {

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

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        List<Integer> result = new ArrayList<>();
        dfs(nums, results, result);
        return results;
    }

    public static void dfs(int[] nums, List<List<Integer>> results, List<Integer> result) {
        if (nums.length == result.size()) {
            List<Integer> temp = new ArrayList<>(result);
            results.add(temp);
        }
        for (int i=0; i<nums.length; i++) {
            if (!result.contains(nums[i])) {
                result.add(nums[i]);
                dfs(nums, results, result);
                result.remove(result.size() - 1);
            }
        }
    }

    public static int amp(int[] inputArray, String[] strArray, Deque<Integer> queue){
        //create the list of possible outputs
        ArrayList<Integer> possibleOutputs = new ArrayList<>();
        //Sets the increment amount based on the opcode
        int increment = getIncrement(inputArray[0]);
        for(int i=0; i < inputArray.length; i += increment){
            int num1;
            int num2;

            //Format the input to include leading zeroes
            strArray[i] = String.format("%05d" , inputArray[i]);
            //gets the opcode from the last 2 characters of the input
            String opcode = strArray[i].substring(3);

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
                    inputArray[inputArray[i+1]] = queue.pop();
                    increment = getIncrement(inputArray[i]);
                    break;
                case "04":
                    if(parameterMode[0].equals("1")){
                        possibleOutputs.add(inputArray[i+1]);
                    } else if(parameterMode[0].equals("0")){
                        possibleOutputs.add(inputArray[inputArray[i+1]]);
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
                    return possibleOutputs.get(possibleOutputs.size()-1);
                default:
                    System.out.println("opcode: " + opcode);
                    i = inputArray.length;
                    System.out.println("Case : not 1, 2, 3, 4, 5, 6, 7, 8 or 99");
                    break;
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {

        //Create every combination of signal thruster
        int[] nums = {0,1,2,3,4};
        List<List<Integer>> combinations;
        combinations = permute(nums);

        //List containing all of the final outputs
        ArrayList<Integer> finalOutputs = new ArrayList<>();
        for(List<Integer> combination : combinations){
            int output = 0;
            Deque<Integer> queue = new LinkedList<>();
            for(int i=0; i<5; i++){

                //read the file in
                BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\gfox\\Java Projects\\AOC-2019\\src\\Day_7\\input.txt"));
                String input = reader.readLine();
                //Split up the string into an array of integers
                String[] strArray = input.split(",");
                int[] inputArray = new int[strArray.length];
                for(int j=0; j < strArray.length; j++){
                    inputArray[j] = Integer.parseInt(strArray[j]);
                }

                queue.add(combination.get(i));
                queue.add(output);
                output = amp(inputArray, strArray, queue);
                queue.clear();
            }
            System.out.println(output);
            finalOutputs.add(output);


        }
        //Find the highest output in finalOutputs
        System.out.println("AND THE WINNER IS: " + Collections.max(finalOutputs));
    }
}
