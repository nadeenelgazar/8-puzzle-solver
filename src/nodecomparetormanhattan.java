import java.util.Comparator;

public class nodecomparetormanhattan implements Comparator<node> {

    @Override
    public int compare(node o1, node o2) {
        if (o1.manhattanh > o2.manhattanh)
            return 1;
        else if (o1.manhattanh < o2.manhattanh)
            return -1;
        return 0;
    }
}
