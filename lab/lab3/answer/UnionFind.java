package exer;

public class UnionFind {
    private int[] parent;

    public UnionFind( int N ) {
        parent = new int[N];
        for( int i = 0; i < N; ++ i )
            parent[i] = i;
    }

    public int find( int p ) {
        return (parent[p] == p) ? p : (parent[p]=find(parent[p]));
    }

    public boolean isConnected( int p, int q ) {
        return find(p) == find(q);
    }
    public void union( int p, int q ) {
        parent[find(p)] = find(q);
    }
}
