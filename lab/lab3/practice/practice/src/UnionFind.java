public class UnionFind {
    // Define whatever data you want.
    private int[] id;
    public UnionFind( int N ) {
        // Write a constructor if necessary.
        id = new int[N];
        for(int i=0 ; i<N ; i++){
            id[i]=i;
        }
    }

    public int find( int p ) {
        // Write a find method if you feel like doint so.
        /*while(p != id[p])
            p=id[p];
        return p;*/
        return (id[p]==p)?p:(id[p]=find(id[p]));

    }

    public boolean isConnected( int p, int q ) {
        // Write code here!
        return find(p)==find(q);
    }
    public void union( int p, int q ) {
        // Write code here!
        /*int i = find(p);
        int j = find(q);
        id[i] = j;*/
        id[find(p)]=find(q);
    }
}
