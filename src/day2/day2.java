package day2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class day2 {
    public static void main(String[] args) throws IOException {
        //read the file in
        BufferedReader reader = new BufferedReader(new FileReader("day2/input.txt"));
        String input = reader.readLine();
        //Split up the string into an array of integers
        String[] strArray = input.split(",");
        int[] inputArray = new int[strArray.length];
        for(int i=0; i < strArray.length; i++){
            inputArray[i] = Integer.parseInt(strArray[i]);
        }
        //Increment a and b to find the verb and noun which will give the desired output of 19690720
        for(int a=0; a < 100; a++){
            for(int b=0; b < 100; b++){
                inputArray[1] = a;
                inputArray[2] = b;

                for(int i=0; i < inputArray.length; i+=4){
                    switch(inputArray[i]){
                        case 1:
                            inputArray[inputArray[i+3]] = inputArray[inputArray[i+1]] + inputArray[inputArray[i+2]];
                            break;
                        case 2:
                            inputArray[inputArray[i+3]] = inputArray[inputArray[i+1]] * inputArray[inputArray[i+2]];
                            break;
                        case 99:
                            i = inputArray.length;
                            break;
                        default:
                            i = inputArray.length;
                            System.out.println("Case : not 1, 2 or 99");
                            break;
                    }
                }
                if(inputArray[0] == 19690720){
                    System.out.println("A: " + a + "B: " + b);
                }
            }
        }
    }
}
