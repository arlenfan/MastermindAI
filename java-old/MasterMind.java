import java.util.*;
import java.util.Random;

public class MasterMind {
    public static int positions;
    public static int numberTokens;
    public static ArrayList<String> mainTokens = new ArrayList<String>();
    public static int guesses = 0;
    public static int guessIndex = 0;
    public static int colorsRightPositionWrong = 0;
    public static int positionsAndColorRight = 0;
    public long startTime = 0, endTime = 0; //

    public MasterMind(String[] x, int positions, int numberTokens) {
        mainTokens.clear();
        for (int i = 0; i < (int) Math.pow(numberTokens, positions); i++) {
            mainTokens.add(x[i]);
        }
        //the previous for loop is to convert the array to arraylist
        Scanner in = new Scanner(System.in);
        System.out.println("Generating guess");
        Random randomGenerator = new Random();
        guessIndex = randomGenerator.nextInt(mainTokens.size());
        guesses++;
        System.out.println("Guess #" + guesses);
        System.out.println("Number of possibilities left: " + mainTokens.size());
        System.out.println("This the computer's guess: " + guessFix(x[guessIndex]) + "  (guessID: " + nullremove(x[guessIndex]) + ")");
        System.out.println("Enter number of right color(s) AND right position(s): ");
        positionsAndColorRight = in.nextInt();
        System.out.println("Enter number of right color(s) BUT wrong position(s): ");
        colorsRightPositionWrong = in.nextInt();

        response(colorsRightPositionWrong, positionsAndColorRight);
    }

    public void response(int colorsRightPositionWrong, int positionsAndColorRight) {
        startTime = System.nanoTime();
        int x = 0, y = 0;
        String temp = mainTokens.get(guessIndex);
        for (int i = 0; i < mainTokens.size(); i++) {
            //This loop is for positionsAndColorRight
            for (int j = 4; j < temp.length(); j++) {
                if (mainTokens.get(i).charAt(j) == temp.charAt(j)) {
                    x++;
                }
            }
            if (x != positionsAndColorRight) {
                mainTokens.remove(i);
                x = 0;
                i--;
            }
            x = 0;
        }
        List<Integer> forbidden = new ArrayList<Integer>();
        List<Integer> doubleForbidden = new ArrayList<Integer>();
        for (int i = 0; i < mainTokens.size(); i++) {
            //This loop is for colorsRightPositionWrong
            if (colorsRightPositionWrong == 0) {
                i = mainTokens.size() - 1;
                break;
            }
            for (int j = 4; j < temp.length(); j++) {
                if (checkArray(doubleForbidden, j)) {
                    continue;
                }

                for (int k = 4; k < temp.length(); k++) {

                    if (j == k) {
                        for (int p = 4; p < temp.length(); p++) {
                            if (mainTokens.get(i).charAt(p) == temp.charAt(p)) {
                                doubleForbidden.add(p);
                            }
                        }
                        continue;
                    }
                    if (checkArray(forbidden, k)) {
                        continue;
                    }
                    if (checkArray(doubleForbidden, k)) {
                        continue;
                    }
                    if (temp.charAt(j) == mainTokens.get(i).charAt(k)) {
                        y++;
                        forbidden.add(k);
                        break;
                    }
                }
            }
            forbidden.clear();
            doubleForbidden.clear();
            if (y != colorsRightPositionWrong) {
                mainTokens.remove(i);
                y = 0;
                i--;
            }
            y = 0;
        }
        nextMove();
    }

    public boolean checkArray(List<Integer> arr, int x) {
        return arr.contains(x);
    }

    public void newGame() {
        guesses = 0;
        Scanner in = new Scanner(System.in);
        System.out.printf(" Enter number of positions: ");
        positions = in.nextInt();
        System.out.printf("The number of positions: " + positions + ". Enter number of tokens: ");
        numberTokens = in.nextInt();
        String[] tokens = new String[(int) Math.pow(numberTokens, positions)];
        generateArray(tokens, positions, numberTokens);
        MasterMind mm = new MasterMind(tokens, positions, numberTokens);

    }

    public void nextMove() {
        //The response method actually removes all the possibilities that are wrong.
        endTime = System.nanoTime();
        System.out.println("The calculation took: " + (endTime - startTime) / 1000000 + " milliseconds");
        Scanner in = new Scanner(System.in);
        if (mainTokens.size() == 1) {
            System.out.println("Your result: " + guessFix(mainTokens.get(0)) + "  --- guessID: " + nullremove(mainTokens.get(0)));
            System.exit(0);
        }

        Random randomGenerator = new Random();
        guessIndex = randomGenerator.nextInt(mainTokens.size());
        guesses++;
        System.out.println("Guess #" + guesses);
        System.out.println("Number of possibilities left: " + mainTokens.size());
        System.out.println("This the computer's guess: " + guessFix(mainTokens.get(guessIndex)) + "  (guessID: " + nullremove(mainTokens.get(guessIndex)) + ")");
        System.out.println("Enter number of right color(s) AND right position(s): ");
        positionsAndColorRight = in.nextInt();
        System.out.println("Enter number of right color(s) BUT wrong position(s): ");
        colorsRightPositionWrong = in.nextInt();

        response(colorsRightPositionWrong, positionsAndColorRight);
    }

    public static String guessFix(String a) {
        //https://www.webgamesonline.com/mastermind/index.php
        String returnString = "";
        String colors[] = {"RED", "GREEN", "BLUE",
                "YELLOW", "BROWN", "ORANGE", "BLACK", "WHITE"};
        int i = 4; //to omit the "null"
        for (i = 4; i < a.length(); i++) {
            returnString += colors[(int) a.charAt(i) - 48];
            returnString += " ";
        }
        return returnString;
    }

    public static String nullremove(String a) {
        //this method removes the first 4 chars null at the beginning of the strings
        String returnString = "";
        for (int i = 4; i < a.length(); i++) {
            returnString += a.charAt(i);
        }
        return returnString;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.printf("Enter number of positions(1-8): ");
        positions = in.nextInt();
        System.out.printf("The number of positions: " + positions + ". Enter number of tokens/colors(1-8): ");
        numberTokens = in.nextInt();
        System.out.printf("The number of tokens/colors used: " + numberTokens + ".");
        System.out.println("Please wait. Generating array...");

        String[] tokens = new String[(int) Math.pow(numberTokens, positions)];
        generateArray(tokens, positions, numberTokens);
        MasterMind mm = new MasterMind(tokens, positions, numberTokens);
    }

    public static String[] generateArray(String[] x, int y, int z) {
        int possibilities = (int) Math.pow(z, y);
        int i = 0, j = 0, k = 0;
        int counter = 0;
        while (counter < y) {
            k = 0;
            for (i = 0; i < (int) Math.pow(z, counter + 1); i++) {
                for (j = 0; j < possibilities / ((int) Math.pow(z, counter + 1)); j++) {
                    x[k] += (String.valueOf(i % z));
                    k++;
                }
            }
            counter++;
        }
        return x;
    }
}
