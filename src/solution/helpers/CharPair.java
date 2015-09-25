package solution.helpers;

/**
 * Created by Thomas on 27-08-2015.
 */
public class CharPair {
    public char x, y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CharPair charPair = (CharPair) o;

        if (x != charPair.x) return false;
        return y == charPair.y;

    }

    @Override
    public int hashCode() {
        int result = (int) x;
        result = 31 * result + (int) y;
        return result;
    }

    public CharPair(char x, char y){
        this.x = x;
        this.y = y;

    }

    @Override
    public String toString() {
        return "solution.CharPair{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
