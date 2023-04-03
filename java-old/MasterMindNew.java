import java.util.*;
import java.util.Random;



public class MasterMindNew {


    public static List<List<Integer>> all =new ArrayList<>();

    public static String colors[] = {"RED", "GREEN", "BLUE",
                "YELLOW", "BROWN", "ORANGE", "BLACK", "WHITE"};


    public static void printAsColors(List<Integer> a){
        for (int i=0;i<a.size();++i){
            System.out.printf(colors[a.get(i)]);
            System.out.printf(" ");
        }
        System.out.println();
    }

    public static void generateArray(int y, int z) {
        int possibilities = (int) Math.pow(z, y);
        for (int index=0;index<possibilities;++index){
            all.add(new ArrayList<Integer>());
        }
        int i = 0, j = 0, k = 0;
        int counter = 0;
        while (counter < y) {
            k = 0;
            for (i = 0; i < (int) Math.pow(z, counter + 1); i++) {

                for (j = 0; j < possibilities / ((int) Math.pow(z, counter + 1)); j++) {
                       all.get(k).add(i%z);
                    k++;
                    }
                }
                counter++;
            }
        }


    public static void printAllArray(){
        // all is of type List<int[]>
        for (int i=0;i<all.size();++i){
            for (int j=0;j<all.get(i).size();++j){
                System.out.print(all.get(i).get(j));
            }
            System.out.println();
        }
    }


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
    List<Integer> guess=all.get(guessIndex);
    System.out.printf("Guess : ");
    printAsColors(all.get(guessIndex));
    System.out.println("Enter number of right color(s) AND right position(s): ");
    int rightRight = in.nextInt();
    System.out.println("Enter number of right color(s) BUT wrong position(s): ");
    int rightWrong = in.nextInt();
    prune(rightRight,rightWrong,guess);

    }

    public static void prune(int rightRight, int rightWrong, List<Integer> guess){
        long start1 = System.nanoTime();
        HashSet<Integer> toRemove = new HashSet<>();
        int startSize = all.size();
        for (int i = 0; i < startSize; ++i){
            List<Integer>candidate = all.get(i);
            ArrayList<Boolean> rightRightVector = new ArrayList<>();
            int rightRightCount = 0;
            for (int j = 0; j != candidate.size(); ++j) {
               if (candidate.get(j) == guess.get(j)) {
                rightRightVector.add(true);
                rightRightCount++;
            } else {
                rightRightVector.add(false);
                }
            }
            if (rightRightCount != rightRight) {
                    toRemove.add(i); //TODO need to fix
                    continue;
                }

            List<Integer> maskedCandidate = new ArrayList<>();
            List<Integer> maskedGuess = new ArrayList<>();
            HashSet<Integer> maskedGuessSet = new HashSet<>();

            for (int j = 0; j < rightRightVector.size(); ++j) {
                    if (!rightRightVector.get(j)) {
                        maskedCandidate.add(candidate.get(j));
                        maskedGuess.add(guess.get(j));
                        maskedGuessSet.add(guess.get(j));
                    }
                }
                int rightWrongCount = 0;
                for (int j = 0; j < maskedCandidate.size(); ++j) {
                    if (maskedGuessSet.contains(candidate.get(j))) {
                        rightWrongCount++;
                    }
                }
                if (rightWrongCount != rightWrong) {
                    toRemove.add(i);
                }
        }

            for (int i = all.size() - 1; i >= 0; i--) {
                if (toRemove.contains(i)) {
            all.remove(i);
        }

        }
        long end1 = System.nanoTime();
        System.out.printf("This prune took %.02f milliseconds\n", (double)(end1-start1)/1000000);
        int endSize = all.size();
        System.out.printf("%d elements were removed.\n",(startSize-endSize));
        System.out.printf("%.02f microseconds per element checked.\n",(double)(end1-start1)/(startSize)/1000);
        move();

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.printf("Enter number of positions(1-8): ");
        int positions = in.nextInt();
        System.out.printf("The number of positions: " + positions + ". Enter number of tokens/colors(1-8): ");
        int numberTokens = in.nextInt();
        System.out.printf("The number of tokens/colors used: " + numberTokens + ".");
        System.out.println("\nPlease wait. Generating array...");
        int arr[] = new int[numberTokens];
        for (int i = 0 ;i<arr.length;++i){
            arr[i]=i;
        }

//         CombinationRepetition(arr, numberTokens, positions);
//         int[] tokens = new int[(int) Math.pow(numberTokens, positions)];
        generateArray(positions, numberTokens);
//         System.out.println(all.size());
//         printAllArray();
        move();


    }


}
