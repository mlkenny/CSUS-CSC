class Fibonacci {
    public static void main(String[] args) {
        int n = 30;
        int[] A = new int[n + 1];
        A[0] = 0; // First solution.
        A[1] = 1; // Second solution.
        int result = fibonacci(A, n);
        System.out.println(result);
    }

    static int fibonacci(int[] A, int n) {
        // Build sums of fibonacci numbers from bottom up.
        for(int i = 2; i <= n; i++) {
            A[i] = A[i - 1] + A[i - 2]; // Instead of recomputing redundant calculations, we can use the table to look it up.
        }
        return A[n]; // Return the fibonacci number at the given n value.
    }
}