package day4;

public class day4_2 {

    public static void main(String[] args) {
        //The given min and max of the password
        final int MIN = 153517;
        final int MAX = 630395;

        int totalNumber = 0;
        for (int i=MIN; i < MAX; i++){
            int firstNum = Integer.parseInt(Integer.toString(i).substring(0, 1));
            int secondNum = Integer.parseInt(Integer.toString(i).substring(1, 2));
            int thirdNum = Integer.parseInt(Integer.toString(i).substring(2, 3));
            int fourthNum = Integer.parseInt(Integer.toString(i).substring(3, 4));
            int fifthNum = Integer.parseInt(Integer.toString(i).substring(4, 5));
            int sixthNum = Integer.parseInt(Integer.toString(i).substring(5, 6));
            //1 and 2 are the same, the rest are different
            if(firstNum == secondNum && secondNum != thirdNum && secondNum != fourthNum && secondNum != fifthNum && secondNum != sixthNum){
                if(secondNum <= thirdNum && thirdNum <= fourthNum && fourthNum <= fifthNum && fifthNum <= sixthNum){
                    totalNumber++;
                }
            //2 and 3 are the same, the rest are different
            }else if(secondNum == thirdNum && firstNum != secondNum && secondNum != fourthNum && secondNum != fifthNum && secondNum != sixthNum){
                if(firstNum <= secondNum && thirdNum <= fourthNum && fourthNum <= fifthNum && fifthNum <= sixthNum){
                    totalNumber++;
                }
            //3 and 4 are the same, the rest are different
            }else if(thirdNum == fourthNum && fourthNum != firstNum && fourthNum != secondNum && fourthNum != fifthNum && fourthNum != sixthNum){
                if(firstNum <= secondNum && secondNum <= thirdNum && fourthNum <= fifthNum && fifthNum <= sixthNum){
                    totalNumber++;
                }
            //4 and 5 are the same, the rest are different
            }else if(fourthNum == fifthNum && fourthNum != firstNum && fourthNum != secondNum && fourthNum != thirdNum && fourthNum != sixthNum){
                if(firstNum <= secondNum && secondNum <= thirdNum && thirdNum <= fourthNum && fifthNum <= sixthNum){
                    totalNumber++;
                }
            //5 and 6 are the same, the rest are different
            }else if(fifthNum == sixthNum && sixthNum != firstNum && sixthNum != secondNum && sixthNum != thirdNum && sixthNum != fourthNum){
                if(firstNum <= secondNum && secondNum <= thirdNum && thirdNum <= fourthNum && fourthNum <= fifthNum){
                    totalNumber++;
                }
            }
        }
        System.out.println(totalNumber);

    }
}
