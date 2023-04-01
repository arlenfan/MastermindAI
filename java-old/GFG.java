
class GFG {

    static void CombinationRepetitionUtil(int chosen[], int arr[],
            int index, int r, int start, int end) {
        if (index == r) {
            for (int i = 0; i < r; i++) {
                System.out.printf("%d ", arr[chosen[i]]);
            }
            System.out.printf("\n");
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

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4};
        int n = arr.length;
        int r = 5;
        CombinationRepetition(arr, n, r);
    }
}