import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // TODO: Add any necessary instance variables.
    WeightedQuickUnionUF Nodes;
    boolean[] IsOpen;
    int n;
    int OpenNum;

    private int recToLine(int row, int col) {
        return row * n + col;
    }

    private boolean inBoundary(int a, int row, int col) {
        return (a > 0 && a < this.n * this.n && row >= 0 && row < n && col >= 0 && col < n);
    }

    public Percolation(int N) {
        // TODO: Fill in this constructor.
        n = N;
        OpenNum = 0;
        IsOpen = new boolean[N * N];
        for (int i = 0; i < N * N; i++)
            IsOpen[i] = false;
        Nodes = new WeightedQuickUnionUF(N * N + 1);
        for (int i = 0; i < N; i++) {
            Nodes.union(N * N, i);
        }
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        int temp = recToLine(row, col);
        IsOpen[temp] = true;
        OpenNum++;
        if (inBoundary(temp + 1, row, col + 1) && IsOpen[temp + 1]) Nodes.union(temp, temp + 1);
        if (inBoundary(temp - 1, row, col - 1) && IsOpen[temp - 1]) Nodes.union(temp, temp - 1);
        if (inBoundary(temp + n, row + 1, col) && IsOpen[temp + n]) Nodes.union(temp, temp + n);
        if (inBoundary(temp - n, row - 1, col) && IsOpen[temp - n]) Nodes.union(temp, temp - n);
    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        return IsOpen[recToLine(row, col)];
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        return Nodes.connected(n * n, recToLine(row, col));
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return OpenNum;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        for (int i = 0; i < n; i++) {
            if (isFull(n - 1, i))
                return true;
        }
        return false;
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
