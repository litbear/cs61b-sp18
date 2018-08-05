public class OffByN implements CharacterComparator {

    private int offset;

    public OffByN(int n) {
        offset = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return diff == offset || diff == -offset;
    }
}
