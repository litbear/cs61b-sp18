package nov.practise.set;

public class QuickFindDS implements DisjointSet {

    private int[] d;

    public QuickFindDS(int n) {
        d = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = i;
        }
    }

    @Override
    public void connect(int p, int q) {
        int pSetID = d[p];
        int qSetID = d[q];

        if (pSetID == qSetID) return;

        for (int i = 0; i < d.length; i++) {
            if (d[i] == qSetID) {
                d[i] = pSetID;
            }
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return d[p] == d[q];
    }
}
