class Knapsack {
    public static void main(String[] args) {
        int[] w = {2, 3, 5, 8};
        int[] v = {3, 4, 6, 8};
        int c = 8;
        // First dimension = capacity.
        // Second dimension = items taken.
        int[][] table = new int[w.length + 1][c + 1];
        for(int i = 0; i <= c; i++) {
            table[0][i] = 0;
        }
        int result = knapsack(w, v, c, table);
        System.out.println(result);
    }

    static int knapsack(int[] w, int[] v, int c, int[][] table) {
        for(int i = 1; i < w.length; i++) {
            for(int j = 0; j <= c; j++) {
                if (w[j] > j) { // Item at j cannot fit in capacity.
                    table[i][j] = table[i - 1][j]; // Take from previous row.
                } else {
                    table[i][j] = Math.max(v[i] + table[i - 1][j - w[i]], table[i - 1][j]);
                }
            }
        }
        return table[w.length][c];
    }
}
