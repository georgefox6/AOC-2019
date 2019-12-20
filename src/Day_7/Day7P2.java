package Day_7;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Intcode.Intcode;

public class Day7P2 {

    public static List<List<Long>> permute(long[] nums) {
        List<List<Long>> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        List<Long> result = new ArrayList<>();
        dfs(nums, results, result);
        return results;
    }

    public static void dfs(long[] nums, List<List<Long>> results, List<Long> result) {
        if (nums.length == result.size()) {
            List<Long> temp = new ArrayList<>(result);
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

    public static Long runFeedbackLoop(Intcode AmpA, Intcode AmpB, Intcode AmpC, Intcode AmpD, Intcode AmpE, List<Long> combination){
        //Add the phase settings to each AMP (First input is phase setting then output signal)
        AmpA.inputInstructions.add(combination.get(0));
        AmpB.inputInstructions.add(combination.get(1));
        AmpC.inputInstructions.add(combination.get(2));
        AmpD.inputInstructions.add(combination.get(3));
        AmpE.inputInstructions.add(combination.get(4));

        //Add the first input to AmpA
        AmpA.inputInstructions.add(0L);
        while(!AmpE.finished){
            //Run AmpA until it requires an input
            while(!(AmpA.waitingForInput || AmpA.opcode.equals("99"))){
                AmpA.nextStep();
            }
            AmpB.inputInstructions.add(AmpA.outputInstructions.getLast());
            AmpB.waitingForInput = false;
            while(!(AmpB.waitingForInput || AmpB.opcode.equals("99"))){
                AmpB.nextStep();
            }
            AmpC.inputInstructions.add(AmpB.outputInstructions.getLast());
            AmpC.waitingForInput = false;
            while(!(AmpC.waitingForInput || AmpC.opcode.equals("99"))){
                AmpC.nextStep();
            }
            AmpD.inputInstructions.add(AmpC.outputInstructions.getLast());
            AmpD.waitingForInput = false;
            while(!(AmpD.waitingForInput || AmpD.opcode.equals("99"))){
                AmpD.nextStep();
            }
            AmpE.inputInstructions.add(AmpD.outputInstructions.getLast());
            AmpE.waitingForInput = false;
            while(!(AmpE.waitingForInput || AmpE.opcode.equals("99"))){
                AmpE.nextStep();
            }
            AmpA.inputInstructions.add(AmpE.outputInstructions.getLast());
            AmpA.waitingForInput = false;
        }
        return AmpE.outputInstructions.getLast();
    }

    public static void main(String[] args) throws IOException {
        //Create every combination of Phase settings
        long[] nums = {5L,6L,7L,8L,9L};
        List<List<Long>> combinations;
        combinations = permute(nums);

        //read the file in
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\gfox\\Java Projects\\AOC-2019\\src\\Day_7\\input.txt"));
        String input = reader.readLine();

        ArrayList<Long> finalOutputs = new ArrayList<>();

        for(List<Long> combination : combinations){
            Intcode AmpA = new Intcode(input);
            Intcode AmpB = new Intcode(input);
            Intcode AmpC = new Intcode(input);
            Intcode AmpD = new Intcode(input);
            Intcode AmpE = new Intcode(input);
            finalOutputs.add(runFeedbackLoop(AmpA, AmpB, AmpC, AmpD, AmpE, combination));
        }
        System.out.println(Collections.max(finalOutputs));
    }
}
