import java.util.*;
import java.util.Random;



public class MasterMindNew {


    public static List<int[]> all =new ArrayList<>();

    String colors[] = {"RED", "GREEN", "BLUE",
                "YELLOW", "BROWN", "ORANGE", "BLACK", "WHITE"};




    public static void move(){
    int allSize = all.size();
    Scanner in = new Scanner(System.in);
    System.out.println("Possibilities left to consider: ");
    System.out.println(allSize);
    if (allSize==1){
        System.out.println("Solution found: ");
        // TODO: print (all[0])
        System.exit(0);
        }
    Random randomGenerator = new Random();
    int guessIndex = randomGenerator.nextInt(allSize);
    // OUTPUT COMPUTER GUESS
    System.out.println("Enter number of right color(s) AND right position(s): ");
    int rightRight = in.nextInt();
    System.out.println("Enter number of right color(s) BUT wrong position(s): ");
    int wrongWrong = in.nextInt();
    //  PRUNE


    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.printf("Enter number of positions(1-8): ");
        int positions = in.nextInt();
        System.out.printf("The number of positions: " + positions + ". Enter number of tokens/colors(1-8): ");
        int numberTokens = in.nextInt();
        System.out.printf("The number of tokens/colors used: " + numberTokens + ".");
        System.out.println("\nPlease wait. Generating array...");


//         generateArray(positions, numberTokens);
//         move();


    }


}
