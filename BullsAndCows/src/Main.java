package bullscows;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {


        results();
    }

    public static void results() {

        ArrayList<Character> numbers = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        boolean isTrue = true;


        while (isTrue) {
            System.out.println("Input the length of the secret code:");

            int lengthOfCode = 0;

            try {
                lengthOfCode = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.printf("Error: %d isn't a valid number.", lengthOfCode);
                break;
            }

            System.out.println("Input the number of possible symbols in the code:");
            int possibleSymbols = 0;

            possibleSymbols = sc.nextInt();

            if (lengthOfCode > possibleSymbols) {

                System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.", lengthOfCode, possibleSymbols);

                break;
            } else if (possibleSymbols > 36) {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                break;
            } else if (lengthOfCode == 0) {
                System.out.printf("Error: it's not possible to generate a code with a length of %d.", lengthOfCode);
                break;
            }

            returnString(possibleSymbols, lengthOfCode);

            System.out.println("Okay, let's start a game!");

            if (lengthOfCode <= possibleSymbols) {
                isTrue = false;

            } else {
                System.out.printf("Error: can't generate a secret number with a length of %d because there aren't enough unique digits.", lengthOfCode);
                break;
            }

            String secretNumber = theSecretNumber(lengthOfCode, numbers, possibleSymbols);

            gradeTheNumber(secretNumber);

        }
    }

    public static void returnString(int possibleSymbols, int number) {


        String zeroToZ = "0123456789abcdefghijklmnopqrstuvwxyz";

        StringBuilder sb = new StringBuilder(number);

        for (int i = 0; i < number; i++) {
            sb.append('*');
        }


        char last = zeroToZ.charAt(possibleSymbols - 1);


        if (possibleSymbols == 10) {
            System.out.printf("The secret is prepared: %s (0-9). \n", sb);
        } else {
            System.out.printf("The secret is prepared: %s (0-9, a-%c). \n", sb, last);
        }

    }

    public static String theSecretNumber(int number, ArrayList<Character> numbers, int possibleSymbols) {

        boolean isValue = true;
        int counter = 0;
        String zeroToZ = "0123456789abcdefghijklmnopqrstuvwxyz";


        while (isValue) {
            int number2 = (int) Math.floor(Math.random() * possibleSymbols);

            if (!numbers.contains(zeroToZ.charAt(number2))) {
                numbers.add(zeroToZ.charAt(number2));
                counter++;
            }

            if (counter == number) {
                isValue = false;
                break;
            }
        }

        StringBuilder sb = new StringBuilder(numbers.size());

        for (Character value : numbers) {
            sb.append(value);
        }

        return sb.toString();
    }

    public static void gradeTheNumber(String secretNumber) {


        Scanner scanner = new Scanner(System.in);


        boolean isValue = true;
        int counter = 0;

        while (isValue) {
            int bull = 0;
            int cow = 0;


            counter++;
            System.out.printf("Turn %d: \n", counter);

            String giveTheNumber = scanner.nextLine();

            for (int i = 0; i < giveTheNumber.length(); i++) {
                if (giveTheNumber.charAt(i) == secretNumber.charAt(i)) {
                    bull++;
                }
            }

            for (int i = 0; i < giveTheNumber.length(); i++) {
                for (int j = 0; j < secretNumber.length(); j++) {
                    if (giveTheNumber.charAt(i) == secretNumber.charAt(j)) {
                        cow++;
                        break;
                    }
                }
            }

            if (cow >= bull) {
                cow -= bull;
            }


            if (bull > 0 && cow > 0) {
                System.out.printf("Grade %d bull(s) and %d cow(s).\n", bull, cow);
            } else if (bull > 0 && cow == 0) {
                System.out.printf("Grade %d bull(s) \n", bull);

            } else if (bull == 0 && cow > 0) {
                System.out.printf("Grade %d cow(s). \n", cow);
            } else {
                System.out.printf("Grade: None. \n");
            }

            if (bull == giveTheNumber.length()) {
                isValue = false;
                System.out.println("Congratulations! You guessed the secret code.\n");
                break;
            }


        }

    }

}