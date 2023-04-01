import java.util.*;
import java.util.Random;



public class MasterMindNew {


    public static List<int[]> all =new ArrayList<>();

    public static String colors[] = {"RED", "GREEN", "BLUE",
                "YELLOW", "BROWN", "ORANGE", "BLACK", "WHITE"};


    public static void printAsColors(int[] a){
        for (int i=0;i<a.length;++i){
            System.out.printf(colors[a[i]]);
            System.out.printf(" ");
        }
        System.out.println();
    }

    public static void CombinationRepetitionUtil(int chosen[], int arr[],
            int index, int r, int start, int end) {
        if (index == r) {
        int[] temp = new int[r];
            for (int i = 0; i < r; i++) {
                temp[i] = arr[chosen[i]];
            }
            all.add(temp);
            return;
        }
        for (int i = start; i <= end; i++) {
            chosen[index] = i;
            CombinationRepetitionUtil(chosen, arr, index + 1,
                    r, i, end);

                   }
        return;
    }

    static void CombinationRepetition(int arr[], int n, int r) {
        int chosen[] = new int[r + 1];
        CombinationRepetitionUtil(chosen, arr, 0, r, 0, n - 1);
    }

    public static void printAllArray(){
        // all is of type List<int[]>
        for (int i=0;i<all.size();++i){
            for (int j=0;j<all.get(i).length;++j){
                System.out.print(all.get(i)[j]);
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
    System.out.printf("Guess : ");
    printAsColors(all.get(guessIndex));
    System.out.println("Enter number of right color(s) AND right position(s): ");
    int rightRight = in.nextInt();
    System.out.println("Enter number of right color(s) BUT wrong position(s): ");
    int wrongWrong = in.nextInt();
    //  PRUNE

    }

    public static void prune(int rightRight, int rightWrong, int[] guess){
        long start1 = System.nanoTime();
        HashSet<Integer> toRemove = new HashSet<>();
        int startSize = all.size();
        for (int i = 0; i < startSize; ++i){
            int candidate[] = all.get(i);
            ArrayList<Boolean> rightRightVector = new ArrayList<>();
            int rightRightCount = 0;
            for (int j = 0; j != candidate.length; ++j) {
               if (candidate[j] == guess[j]) {
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
                        maskedCandidate.add(candidate[j]);
                        maskedGuess.add(guess[j]);
                        maskedGuessSet.add(guess[j]);
                    }
                }
                int rightWrongCount = 0;
                for (int j = 0; j < maskedCandidate.size(); ++j) {
                    if (maskedGuessSet.find(candidate[j])) {
                        rightWrongCount++;
                    }
                }
                if (rightWrongCount != rightWrong) {
                    toRemove.add(i);
                }
        }

            for (int i = all.size() - 1; i >= 0; i--) {
        if (toRemove.find(i) != toRemove.end()) {
            all.erase(all.begin() + i);
        }

        }
        long end1 = System.nanoTime();
        System.out.printf("This prune took %.3f milliseconds", (end1-start1)/1000000);
        int endSize = all.size();
        System.out.printf("%d elements were removed.",(startSize-endSize));
        System.out.printf("%.2f milliseconds per element checked.",(end1-start1)/(startSize));
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

        CombinationRepetition(arr, numberTokens, positions);
//         System.out.println(all.size());
//         printAllArray();
        move();


    }


}
