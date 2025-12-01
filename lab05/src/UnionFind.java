import java.sql.Array;

public class UnionFind {
    // TODO: Instance variables
    int[] nodes;

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        // TODO: YOUR CODE HERE
        nodes = new int[N];
        for (int i = 0; i < N; i++)
            nodes[i] = -1;
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE
        return -nodes[find(v)];
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        return nodes[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        if (find(v1) == find(v2)) return true;
        else return false;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        // TODO: YOUR CODE HERE
        int start = v;
        if (v < 0 || v >= nodes.length)
            throw new IllegalArgumentException("Node V doesn't exist!");
        if (nodes[v] < 0) return v;

        while (nodes[nodes[v]] >= 0) {
            v = nodes[v];
        }
        pathCompression(nodes[v], start);
        return nodes[v];
    }

    private void pathCompression(int root, int start) {
        while (start != root) {
            int curr = start;
            start = nodes[start];
            nodes[curr] = root;
        }
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
        if (connected(v1, v2)) return;
        int root1 = find(v1);
        int root2 = find(v2);
        if (-nodes[root1] > -nodes[root2]) {
            nodes[root1] += nodes[root2];
            nodes[root2] = root1;
        }
        else {
            nodes[root2] += nodes[root1];
            nodes[root1] = root2;
        }
    }

}
