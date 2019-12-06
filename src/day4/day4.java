package day4;

public class day4 {

    public static void main(String[] args) {
        //The given min and max of the password
        final int MIN = 153517;
        final int MAX = 630395;

        int totalNumber = 0;
        for (int i=MIN; i < MAX; i++){
            //breaks the inputs down into the individual numbers
            int firstNum = Integer.parseInt(Integer.toString(i).substring(0, 1));
            int secondNum = Integer.parseInt(Integer.toString(i).substring(1, 2));
            int thirdNum = Integer.parseInt(Integer.toString(i).substring(2, 3));
            int fourthNum = Integer.parseInt(Integer.toString(i).substring(3, 4));
            int fifthNum = Integer.parseInt(Integer.toString(i).substring(4, 5));
            int sixthNum = Integer.parseInt(Integer.toString(i).substring(5, 6));
            if(firstNum == secondNum || secondNum == thirdNum || thirdNum == fourthNum || fourthNum == fifthNum || fifthNum == sixthNum){
                if(firstNum <= secondNum && secondNum <= thirdNum && thirdNum <= fourthNum && fourthNum <= fifthNum && fifthNum <= sixthNum){
                    totalNumber++;
                }
            }
        }
        System.out.println(totalNumber);

    }
}
